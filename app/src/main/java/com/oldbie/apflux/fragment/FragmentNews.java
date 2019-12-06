package com.oldbie.apflux.fragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.oldbie.apflux.R;
import com.oldbie.apflux.adapter.NewsAdapter;
import com.oldbie.apflux.adapter.RecyclerItemClickListener;
import com.oldbie.apflux.model.News;
import com.oldbie.apflux.model.ResponseNews;
import com.oldbie.apflux.network.NetworkAPI;
import com.oldbie.apflux.network.ServiceAPI;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNews extends Fragment {

    private NetworkAPI api;
    private ArrayList<News> arrNew;
    private NewsAdapter adapter;
    private RecyclerView rvMain;
    private ProgressBar progressBar;

    //Page
    private int pageNumber=0;

    private int currentItem, totalItem, scrollItem, previousTotalItem;

//    private boolean isScroll = false;
    private LinearLayoutManager layoutManager;

    private boolean isLoaddingPage=true;
//    private int viewPerpage=10;

    public FragmentNews() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        api = ServiceAPI.newService(NetworkAPI.class);
        rvMain= view.findViewById(R.id.rvMain);
        progressBar = view.findViewById( R.id.progressBar );
        getNews();

        return view;
    }

    private void getNews(){
        getActivity().runOnUiThread(new Runnable(){
            @Override
            public void run(){
                Call<ResponseNews> call = api.getNewData( pageNumber );
                call.enqueue(new Callback<ResponseNews>() {
                    @Override
                    public void onResponse(Call<ResponseNews> call, Response<ResponseNews>response) {
                        if (response.body().getResult() == 0){
                            arrNew=response.body().getData();
                            for (int i=0;i<arrNew.size();i++){
                                adapter=new NewsAdapter(arrNew,getContext());
                                layoutManager = new LinearLayoutManager(getContext());
                                rvMain.setLayoutManager(layoutManager);
                                rvMain.setAdapter(adapter);
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseNews> call, Throwable t){

                    }
                });
                //..
            }
        });
        rvMain.addOnScrollListener( new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged( recyclerView, newState );
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled( recyclerView, dx, dy );

                currentItem=layoutManager.getChildCount();
                totalItem=layoutManager.getItemCount();
                scrollItem=layoutManager.findFirstVisibleItemPosition();

                if(dy>0){
                    if (isLoaddingPage){
                        if (totalItem>previousTotalItem){
                            isLoaddingPage=false;
                            previousTotalItem=totalItem;
                        }
                    }

                    if(!isLoaddingPage&& (totalItem-currentItem==scrollItem)){

                        pageNumber++;
                        delayPost();
                        isLoaddingPage=true;
                    }
                }
            }
        } );

        rvMain.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position){
                String mContent = arrNew.get(position).getmContent();
                String mTitle = arrNew.get(position).getmTitle();
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(view.getContext());
                mBuilder.setTitle(mTitle).setMessage(mContent).setCancelable(true).setNegativeButton("DONE", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                        dialog.dismiss();
                    }
                }).show();
            }
        }));

    }

    private void loadingData(){
        getActivity().runOnUiThread( new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility( View.VISIBLE );
                Call<ResponseNews> call = api.getNewData( pageNumber );
                call.enqueue( new Callback<ResponseNews>() {
                    @Override
                    public void onResponse(Call<ResponseNews> call, Response<ResponseNews> response) {
                        if(response.body().getResult() == 0){
                            arrNew=response.body().getData();

                            adapter.loadingDataFetch( arrNew );

                        }else{
                            Toast.makeText( getContext(),"Lỗi vui lòng thử lại",Toast.LENGTH_SHORT ).show();
                        }
//                        progressBar.setVisibility( View.GONE );
                    }

                    @Override
                    public void onFailure(Call<ResponseNews> call, Throwable t) {

                    }
                } );
            }
        } );
    }

    private void delayPost(){
        progressBar.setVisibility( View.VISIBLE );
        new Handler(  ).postDelayed( new Runnable() {
            @Override
            public void run() {
                loadingData();
                adapter.notifyDataSetChanged();
                progressBar.setVisibility( View.GONE );
            }
        }, 3500 );
    }
    @Override
    public void onResume() {
        super.onResume();
//        getNews();
    }

}
