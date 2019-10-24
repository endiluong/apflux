package com.oldbie.apflux.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.oldbie.apflux.R;
import com.oldbie.apflux.adapter.TimeTableAdapter;
import com.oldbie.apflux.model.TimeTable;
import com.oldbie.apflux.network.NetworkAPI;
import com.oldbie.apflux.network.ServiceAPI;
import com.oldbie.apflux.adapter.TimeTableAdapter;
import com.oldbie.apflux.model.TimeTable;
import com.ramotion.foldingcell.FoldingCell;

import java.util.List;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTimetable extends Fragment {

    private String TAG = "TimeTable";

    TimeTable timeTable;
    ListView lvMain;

    //.. ..//
    NetworkAPI api;
    List<TimeTable> arrTime;
    TimeTableAdapter adapter;
    //.. ..//
    public FragmentTimetable() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view=inflater.inflate( R.layout.fragment_timetable, container, false );
        lvMain=(ListView) view.findViewById( R.id.lvMain );

        ShowDataJSON(view);

        lvMain.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ((FoldingCell) view).toggle( false );
                adapter.registerToggle( i );
            }
        } );

        return view;

    }


    @Override
    public void onResume() {
        super.onResume();

    }

    private void ShowDataJSON(View view){
        view.getApplicationWindowToken();

        getActivity().runOnUiThread( new Runnable() {
            @Override
            public void run() {
                api = ServiceAPI.getDataJSON();
                Call<List<TimeTable>> call = api.getAllData();
                call.enqueue( new Callback<List<TimeTable>>() {
                    @Override
                    public void onResponse(Call<List<TimeTable>> call, Response<List<TimeTable>> response) {
                        arrTime=response.body();
                        if(response.isSuccessful()) {
                            adapter = new TimeTableAdapter( getContext(),arrTime );
                            lvMain.setAdapter( adapter );
                        }else{
//                            Toast.makeText(getContext(),"Failed!", LENGTH_LONG).show();
                            Log.e(TAG," Response Error " + response.code());

                        }
                    }

                    @Override
                    public void onFailure(Call<List<TimeTable>> call, Throwable t) {
                        Log.e(TAG," Response Error "+ t.getMessage());
                    }
                } );
            }
        } );

    }

}
