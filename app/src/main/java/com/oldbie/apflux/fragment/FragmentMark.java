package com.oldbie.apflux.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.oldbie.apflux.LoginActivity;
import com.oldbie.apflux.R;
import com.oldbie.apflux.adapter.MarkAdapter;
import com.oldbie.apflux.model.Mark;
import com.oldbie.apflux.model.ResponseMark;
import com.oldbie.apflux.network.NetworkAPI;
import com.oldbie.apflux.network.ServiceAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentMark extends Fragment {

    private ListView lvMark;
    NetworkAPI api;
    MarkAdapter markAdapter;
    ArrayList<Mark> arrMark = new ArrayList<>();

    public FragmentMark() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate( R.layout.fragment_mark, container, false );
        lvMark = view.findViewById( R.id.lvMark );
        api = ServiceAPI.userService( NetworkAPI.class );
        ShowMarkJSON(view);
        return view;

    }

    private void ShowMarkJSON(View view){
        view.getApplicationWindowToken();
        getActivity().runOnUiThread( new Runnable() {
            @Override
            public void run() {
                final String checkId = LoginActivity.arrSSR.get(0).getStudentId();
                final String token = LoginActivity.arrToken.get(0).getToken();
                Call<ResponseMark> call = api.getMark(checkId, token);
                call.enqueue( new Callback<ResponseMark>() {
                    @Override
                    public void onResponse(Call<ResponseMark> call, Response<ResponseMark> response) {
                        if(response.body().getResult()==1){
                            arrMark=response.body().getData();
                            for(int i=0;i<arrMark.size();i++){
                                markAdapter=new MarkAdapter(getContext(), arrMark);
                                lvMark.setAdapter(markAdapter);
                            }
                        }else{
                            Toast.makeText( getContext(),"Errors!",Toast.LENGTH_SHORT ).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseMark> call, Throwable t) {

                    }
                } );
            }
        } );

    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
