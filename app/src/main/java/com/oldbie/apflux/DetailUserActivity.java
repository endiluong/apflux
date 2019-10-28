package com.oldbie.apflux;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.oldbie.apflux.network.ServiceAPI;
import com.oldbie.apflux.network.NetworkAPI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DetailUserActivity extends AppCompatActivity {
    private static final String TAG = "DetailUserActivity";
    private NetworkAPI api;
    Button btnBack;
    ImageView imvThumb;
    TextView mName, mUser, mPhone, mAddress, mIdent, mStudentId, mBirthday,
            mGender, mMajor,mSpecialize, mClass, mStadate, mStatus;
    String avt;
    Date date;

    SimpleDateFormat fmtOut = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_user_detail);
        //create service for updateUser
        api = ServiceAPI.userService(NetworkAPI.class);

        imvThumb = findViewById(R.id.imvThumb);
        mName = findViewById(R.id.tvName);
        mUser = findViewById(R.id.tvUser);
        mPhone = findViewById(R.id.tvPhone);
        mAddress = findViewById(R.id.tvAddress);
        mIdent = findViewById(R.id.tvIdentification);
        mStudentId = findViewById(R.id.tvStudentId);
        mBirthday = findViewById(R.id.tvBirthday);
        mGender = findViewById(R.id.tvGender);
        mMajor = findViewById(R.id.tvMajor);
        mSpecialize = findViewById(R.id.tvSpecialize);
        mClass = findViewById(R.id.tvClass);
        mStadate = findViewById(R.id.tvStartDate);
        mStatus = findViewById(R.id.tvStatus);
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
        //Set data into TextView
        mUser.setText(LoginActitity.arrSSR.get(0).getEmail());
        mName.setText(LoginActitity.arrSSR.get(0).getName());
        mPhone.setText(LoginActitity.arrSSR.get(0).getPhone());
        mAddress.setText(LoginActitity.arrSSR.get(0).getAddress());
        mIdent.setText(LoginActitity.arrSSR.get(0).getIdentification());
        mStudentId.setText(LoginActitity.arrSSR.get(0).getStudentId());
//        mBirthday.setText(LoginActitity.arrSSR.get(0).getBirthday());
        try {
            date = fmt.parse(LoginActitity.arrSSR.get(0).getBirthday());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mBirthday.setText(fmtOut.format(date));

        if (LoginActitity.arrSSR.get(0).getGender().equals("1")) {
            mGender.setText("Male");
        } else {
            mGender.setText("Female");
        }
        mMajor.setText(LoginActitity.arrSSR.get(0).getMajor());
        mSpecialize.setText(LoginActitity.arrSSR.get(0).getSpecialize());
        mClass.setText(LoginActitity.arrSSR.get(0).getCourse());
//        mStadate.setText(LoginActitity.arrSSR.get(0).getStartDate());
        try {
            date = fmt.parse(LoginActitity.arrSSR.get(0).getStartDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        fmtOut = new SimpleDateFormat("dd-MM-yyyy");
        mStadate.setText(fmtOut.format(date));
        if (LoginActitity.arrSSR.get(0).getStatus().equals("1")) {
            mStatus.setText("Studying");
        } else {
            mStatus.setText("Relearning");
        }

        avt = LoginActitity.arrSSR.get(0).getAvatar();
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
