package com.oldbie.apflux.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.oldbie.apflux.LoginActivity;
import com.oldbie.apflux.R;
import com.oldbie.apflux.adapter.HomeAdapter_news;
import com.oldbie.apflux.adapter.HomeAdapter_timetable;
import com.oldbie.apflux.adapter.NewsAdapter;
import com.oldbie.apflux.adapter.RecyclerItemClickListener;
import com.oldbie.apflux.model.News;
import com.oldbie.apflux.model.ResponseNews;
import com.oldbie.apflux.model.ResponseTimeTable;
import com.oldbie.apflux.model.TimeTable;
import com.oldbie.apflux.network.NetworkAPI;
import com.oldbie.apflux.network.ServiceAPI;

import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment {
    private static final String TAG = "Home";
    public FragmentHome() {
        // Required empty public constructor
    }
    private String sid,token;
    private RecyclerView rvTimeTable_home,rvNews_home;
    private NetworkAPI api;

    //.. NEWS ..//
    private HomeAdapter_news adapterNews;
    private News news;
    private ArrayList<News> arrNews;

    //.. TIME TABLE ..//
    private HomeAdapter_timetable adapterTimetable;
    private ArrayList<TimeTable> arrTimetable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate( R.layout.fragment_home, container, false );
        rvTimeTable_home=(RecyclerView)view.findViewById( R.id.rvTimeTable_home );
        rvNews_home=(RecyclerView)view.findViewById( R.id.rvNews_home );

//        lvMain=(ListView) view.findViewById( R.id.lvMain );
        showData_News();
        showData_timeTable();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

    }

    private void showData_timeTable(){
        api = ServiceAPI.userService( NetworkAPI.class );

        getActivity().runOnUiThread( new Runnable() {
            @Override
            public void run() {
                final String sid = LoginActivity.arrSSR.get( 0 ).getStudentId();
                final String token = LoginActivity.arrSSR.get( 0 ).getToken();

                Call<ResponseTimeTable> call = api.getAllData( sid,token );
                call.enqueue( new Callback<ResponseTimeTable>() {
                    @Override
                    public void onResponse(Call<ResponseTimeTable> call, Response<ResponseTimeTable> response) {
//                        Toast.makeText( getContext(),sid+"\n"+token,Toast.LENGTH_SHORT ).show();
                        if (response.body().getResult()==1){
                            arrTimetable=response.body().getData();
                            for (int i=0;i<arrTimetable.size();i++){
                                adapterTimetable = new HomeAdapter_timetable( getContext(),arrTimetable );
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                                rvTimeTable_home.setLayoutManager(layoutManager);
                                rvTimeTable_home.setAdapter( adapterTimetable );
                            }
                        }else{
                            Toast.makeText( getContext(), "Error: "+response.body().getResult(), Toast.LENGTH_SHORT ).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseTimeTable> call, Throwable t) {

                    }
                } );
            }
        } );

        rvTimeTable_home.addOnItemTouchListener( new RecyclerItemClickListener( getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

                getActivity().getSupportFragmentManager().popBackStack();
                transaction.replace(R.id.fragment, new FragmentTimetable());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        } ) );

    }

    private void showData_News(){
        api = ServiceAPI.newService( NetworkAPI.class );

        getActivity().runOnUiThread( new Runnable() {
            @Override
            public void run() {
                Call<ResponseNews> call = api.getNewData();

                call.enqueue( new Callback<ResponseNews>() {
                    @Override
                    public void onResponse(Call<ResponseNews> call, Response<ResponseNews> response) {
                        if (response.body().getResult()==0){
                            arrNews=response.body().getData();
                            for (int i=0;i<arrNews.size();i++){
                                adapterNews = new HomeAdapter_news( arrNews,getContext() );
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                                rvNews_home.setLayoutManager(layoutManager);
                                rvNews_home.setAdapter( adapterNews );
                            }
                        }else{
                            Toast.makeText( getContext(), "Error: "+response.body().getResult(), Toast.LENGTH_SHORT ).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseNews> call, Throwable t) {

                    }
                } );
            }
        } );

        rvNews_home.addOnItemTouchListener( new RecyclerItemClickListener( getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

                getActivity().getSupportFragmentManager().popBackStack();
                transaction.replace(R.id.fragment, new FragmentNews());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        } ) );
    }
}
