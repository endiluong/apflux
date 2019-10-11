package com.oldbie.applux;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.oldbie.applux.network.NetworkAPI;
import com.oldbie.applux.network.ServiceAPI;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import static com.bumptech.glide.request.RequestOptions.centerCropTransform;

public class DetailUserActivity extends AppCompatActivity {
    private static final String TAG = "DetailUserActivity";
    private NetworkAPI api;
    Button btnUpdate, btnUpdate1;
    ImageView imvThumb, imvBack;
    EditText email, name, etNew, etRe_Pass;
    TextView date, user;
    String user1, email1, name1, thumb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_user_detail);

        //create service for updateUser
        api = ServiceAPI.userService(NetworkAPI.class);

        imvThumb = findViewById(R.id.imvThumb);
        user = findViewById(R.id.tvUser);
        email = findViewById(R.id.etMail);
        name = findViewById(R.id.etName);
        etNew = findViewById(R.id.etNewPass);
        etRe_Pass = findViewById(R.id.etRePass2);
        btnUpdate = findViewById(R.id.btnUpdateUser);
        btnUpdate1 = findViewById(R.id.btnUpdatePass);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnUpdate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_OK);
                getFragmentManager().popBackStackImmediate();
                finish();
                overridePendingTransition(R.anim.push_left_out, R.anim.push_left_in);
            }
        });

        doThings();

    }

    @SuppressLint("ResourceType")
    public void doThings(){
        Bundle extras = getIntent().getExtras();

        //get intent data
        user1 = extras.getString("user");
        email1 = extras.getString("email");
        name1 = extras.getString("name");
        thumb1 = extras.getString("thumb");

        //set data into edittext
        user.setText(user1);
        email.setText(email1);
        name.setText(name1);
        user.setEnabled(false);

        //load image with Glide
        Glide.with(getBaseContext()).load(thumb1)
                .apply(centerCropTransform()
                        .placeholder(R.raw.loading)
                        .error(R.raw.error)
                        .priority(Priority.HIGH)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                .transition(withCrossFade())
                .onlyRetrieveFromCache(true)
                .into(imvThumb);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(Activity.RESULT_OK);
        getFragmentManager().popBackStackImmediate();
        finish();
        overridePendingTransition(R.anim.push_left_out, R.anim.push_left_in);
    }
}
