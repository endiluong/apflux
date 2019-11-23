package com.oldbie.apflux.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.oldbie.apflux.LoginActivity;
import com.oldbie.apflux.R;
import com.oldbie.apflux.adapter.HomeAdapterNews;
import com.oldbie.apflux.adapter.HomeAdapterTimetable;
import com.oldbie.apflux.adapter.RecyclerItemClickListener;
import com.oldbie.apflux.model.News;
import com.oldbie.apflux.model.ResponseNews;
import com.oldbie.apflux.model.ResponseTimeTable;
import com.oldbie.apflux.model.TimeTable;
import com.oldbie.apflux.network.NetworkAPI;
import com.oldbie.apflux.network.ServiceAPI;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment {
    private static final String TAG = "Home";
    private RecyclerView rvTimeTable_home,rvNews_home;
    private NetworkAPI api;

    //.. NEWS ..//
    private HomeAdapterNews adapterNews;
    private ArrayList<News> arrNews;

    //.. TIME TABLE ..//
    private HomeAdapterTimetable adapterTimetable;
    private ArrayList<TimeTable> arrTimetable;

    public FragmentHome() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate( R.layout.fragment_home, container, false );
        rvTimeTable_home = view.findViewById( R.id.rvTimeTable_home );
        rvNews_home = view.findViewById( R.id.rvNews_home );
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
                final String token = LoginActivity.arrToken.get( 0 ).getToken();

                Call<ResponseTimeTable> call = api.getAllData( sid,token );
                call.enqueue( new Callback<ResponseTimeTable>() {
                    @Override
                    public void onResponse(Call<ResponseTimeTable> call, Response<ResponseTimeTable> response) {
                        if (response.body().getResult()==1) {
                            arrTimetable = response.body().getData();
                            for (int i = 0; i < arrTimetable.size(); i++) {
                                adapterTimetable = new HomeAdapterTimetable(getContext(), arrTimetable);
//                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                                GridLayoutManager grid = new GridLayoutManager( getContext(),2 ,GridLayoutManager.VERTICAL,false);
                                rvTimeTable_home.setLayoutManager(grid);
                                rvTimeTable_home.setAdapter(adapterTimetable);
                            }
                        } else {
                            Toast.makeText( getContext(), "Error: "+response.body().getResult(), Toast.LENGTH_SHORT ).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseTimeTable> call, Throwable t) {

                    }
                });
            }
        });

        rvTimeTable_home.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                getActivity().getSupportFragmentManager().popBackStack();
                transaction.replace(R.id.fragment, new FragmentTimetable());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }));
    }

    private void showData_News() {
        api = ServiceAPI.newService(NetworkAPI.class);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
            Call<ResponseNews> call = api.getNewData();
            call.enqueue(new Callback<ResponseNews>() {
                @Override
                public void onResponse(Call<ResponseNews> call, Response<ResponseNews> response) {
                    if (response.body().getResult() == 0){
                        arrNews = response.body().getData();
                        for (int i=0;i<arrNews.size();i++) {
                            adapterNews = new HomeAdapterNews(arrNews,getContext());
//                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                            GridLayoutManager grid = new GridLayoutManager( getContext(),2 ,GridLayoutManager.VERTICAL,false);
                            rvNews_home.setLayoutManager(grid);
                            rvNews_home.setAdapter(adapterNews);
                        }
                    } else {
                        Toast.makeText(getContext(), "Error: "+response.body().getResult(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseNews> call, Throwable t) {

                }
            });
            }
        });

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
        }));
    }
}
