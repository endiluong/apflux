package com.oldbie.apflux.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.oldbie.apflux.LoginActitity;
import com.oldbie.apflux.R;
import com.oldbie.apflux.adapter.TimeTableAdapter;
import com.oldbie.apflux.model.ResponseTimeTable;
import com.oldbie.apflux.model.TimeTable;
import com.oldbie.apflux.network.NetworkAPI;
import com.oldbie.apflux.network.ServiceAPI;
import com.oldbie.apflux.adapter.TimeTableAdapter;
import com.oldbie.apflux.model.TimeTable;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
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

    ListView lvMain;

    ArrayList<TimeTable> arrTimeTable;
    //.. ..//
    FoldingCell fc;
    NetworkAPI api;
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
        api = ServiceAPI.userService( NetworkAPI.class );

        fc = (FoldingCell) view.findViewById(R.id.folding_cell);

        lvMain.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // toggle clicked cell state
                ((FoldingCell) view).toggle(false);
                // register in adapter that state for selected cell is toggled
                adapter.registerToggle(position);
            }
        } );

        ShowDataJSON(view);

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
                final String checkId = LoginActitity.arrSSR.get( 0 ).getStudentId();

                Toast.makeText( getContext(),checkId,Toast.LENGTH_SHORT ).show();

                Call<ResponseTimeTable> call = api.getAllData( checkId );
                call.enqueue( new Callback<ResponseTimeTable>() {
                    @Override
                    public void onResponse(Call<ResponseTimeTable> call, Response<ResponseTimeTable> response) {
                        if(response.body().getResult()==1){
                            arrTimeTable=response.body().getData();
                            for(int i=0;i<arrTimeTable.size();i++){
                                adapter=new TimeTableAdapter( getContext(),arrTimeTable );
                                lvMain.setAdapter( adapter );
                            }
                        }else{
                            Toast.makeText( getContext(),"Errors:...",Toast.LENGTH_SHORT ).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseTimeTable> call, Throwable t) {

                    }
                } );
            }
        } );

    }

}
