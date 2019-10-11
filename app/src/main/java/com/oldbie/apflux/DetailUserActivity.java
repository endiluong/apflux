package com.oldbie.apflux;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
    String user1, email1, name1, thumb1;

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
        Bundle extras = getIntent().getExtras();

        //get intent data
        user1 = extras.getString("user");
        email1 = extras.getString("email");
        name1 = extras.getString("name");
        thumb1 = extras.getString("thumb");

        //set data into edittext
        user.setText(user1);
        name.setText(name1);
        user.setEnabled(false);

        //load image with Glide
        Glide.with(getBaseContext()).load(thumb1)
                .apply(centerCropTransform()
                        .placeholder(R.drawable.person)
//                        .error(R.raw.error)
                        .priority(Priority.HIGH)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                .transition(withCrossFade())
                .onlyRetrieveFromCache(true)
                .into(imvThumb);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
