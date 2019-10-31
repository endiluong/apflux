package com.oldbie.apflux;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.oldbie.apflux.adapter.MarkAdapter;
import com.oldbie.apflux.model.Mark;
import com.oldbie.apflux.model.ResponseMark;
import com.oldbie.apflux.network.NetworkAPI;
import com.oldbie.apflux.network.ServiceAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarkActivity extends AppCompatActivity {
    private ListView lvMark;
    NetworkAPI api;
    MarkAdapter markAdapter;
    ArrayList<Mark> arrMark = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark);
        api = ServiceAPI.userService(NetworkAPI.class);

        lvMark = findViewById(R.id.lvMark);
        ShowMarkJSON();
    }

    private void ShowMarkJSON(){
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                final String checkId = LoginActitity.arrSSR.get(0).getStudentId();
                final String token = LoginActitity.arrSSR.get(0).getToken();

                Toast.makeText(getBaseContext(),checkId,Toast.LENGTH_SHORT).show();

                Call<ResponseMark> call = api.getMark(checkId, token);
                call.enqueue( new Callback<ResponseMark>() {
                    @Override
                    public void onResponse(Call<ResponseMark> call, Response<ResponseMark> response) {
                        if(response.body().getResult()==1){
                            arrMark=response.body().getData();
                            for(int i=0;i<arrMark.size();i++){
                                markAdapter=new MarkAdapter(getBaseContext(),R.layout.mark_item, arrMark);
                                lvMark.setAdapter( markAdapter );
                            }
                        }else{
                            Toast.makeText( getBaseContext(),"Errors:...",Toast.LENGTH_SHORT ).show();
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
