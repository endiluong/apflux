package com.oldbie.apflux;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.oldbie.apflux.network.ServiceAPI;
import com.oldbie.apflux.network.NetworkAPI;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import static com.bumptech.glide.request.RequestOptions.centerCropTransform;

public class DetailUserActivity extends AppCompatActivity {
    private static final String TAG = "DetailUserActivity";
    private NetworkAPI api;
    Button btnBack;
    ImageView imvThumb, imvBack;
    TextView name, user;
    String user1, email1, name1, avt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_user_detail);
        //create service for updateUser
        api = ServiceAPI.userService(NetworkAPI.class);
        imvThumb = findViewById(R.id.imvThumb);
        user = findViewById(R.id.tvUser);
        name = findViewById(R.id.tvName);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        setData();

    }

    @SuppressLint("ResourceType")
    public void setData(){
        //set data into edittext
        user.setText(LoginActitity.arrSSR.get(0).getUsername());
        name.setText(LoginActitity.arrSSR.get(0).getName());
        avt = LoginActitity.arrSSR.get(0).getAvatar();
        user.setEnabled(false);

        //load image with Glide
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.person)
                .error(R.mipmap.ic_launcher_round);

        Glide.with(this).load(avt).apply(options).into(imvThumb);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
