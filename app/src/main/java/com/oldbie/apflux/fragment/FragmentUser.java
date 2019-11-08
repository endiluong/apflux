package com.oldbie.apflux.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.oldbie.apflux.LoginActivity;
import com.oldbie.apflux.R;
import com.oldbie.apflux.network.NetworkAPI;
import com.oldbie.apflux.network.ServiceAPI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentUser extends Fragment {
    private static final String TAG = "FragmentUser";
    private TextView tvName, tvUsername;
    private ImageView imvProfile;
    private TextView mName, mUser, mPhone, mAddress, mIdent, mStudentId, mBirthday,
            mGender, mMajor,mSpecialize, mClass, mStadate, mStatus;
    private Date date;
    private SimpleDateFormat fmtOut = new SimpleDateFormat("dd-MM-yyyy");
    private SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        //registerUser ServiceAPI and call getJSON from server
        tvName = view.findViewById(R.id.tvProfileName);
        tvUsername = view.findViewById(R.id.tvRole);
        imvProfile = view.findViewById(R.id.imvProfile);
        mName = view.findViewById(R.id.tvName);
        mUser = view.findViewById(R.id.tvUser);
        mPhone = view.findViewById(R.id.tvPhone);
        mAddress = view.findViewById(R.id.tvAddress);
        mIdent = view.findViewById(R.id.tvIdentification);
        mStudentId = view.findViewById(R.id.tvStudentId);
        mBirthday = view.findViewById(R.id.tvBirthday);
        mGender = view.findViewById(R.id.tvGender);
        mMajor = view.findViewById(R.id.tvMajor);
        mSpecialize = view.findViewById(R.id.tvSpecialize);
        mClass = view.findViewById(R.id.tvClass);
        mStadate = view.findViewById(R.id.tvStartDate);
        mStatus = view.findViewById(R.id.tvStatus);

        setData();
        return view;
    }

    private void setData(){
        //Set data into TextView
        mUser.setText(LoginActivity.arrSSR.get(0).getEmail());
        mName.setText(LoginActivity.arrSSR.get(0).getName());
        mPhone.setText(LoginActivity.arrSSR.get(0).getPhone());
        mAddress.setText(LoginActivity.arrSSR.get(0).getAddress());
        mIdent.setText(LoginActivity.arrSSR.get(0).getIdentification());
        mStudentId.setText(LoginActivity.arrSSR.get(0).getStudentId());
        try {
            date = fmt.parse(LoginActivity.arrSSR.get(0).getBirthday());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mBirthday.setText(fmtOut.format(date));
        if (LoginActivity.arrSSR.get(0).getGender().equals("1")) {
            mGender.setText("Nam");
        } else {
            mGender.setText("Nữ");
        }
        switch (LoginActivity.arrSSR.get(0).getMajor()) {
            case "1":
                mMajor.setText("Công Nghệ Thông Tin");
                break;
            case "2":
                mMajor.setText("Du lịch - Nhà hàng - Khách sạn");
                break;
            case "3":
                mMajor.setText("Kinh Tế Kinh Doanh");
                break;
        }
        switch (LoginActivity.arrSSR.get(0).getSpecialize()) {
            case "1":
                mSpecialize.setText("Lập Trình Máy Tính/Thiết Bị Di Động");
                break;
            case "2":
                mSpecialize.setText("Thiết Kế Website");
                break;
            case "3":
                mSpecialize.setText("CNTT/Ứng Dụng Phần Mềm");
                break;
            case "4":
                mSpecialize.setText("Thiết Kế Đồ Hoạ");
                break;
            case "5":
                mSpecialize.setText("Digital/Online Marketing");
                break;
            case "6":
                mSpecialize.setText("Tổ Chức Sự Kiện");
                break;
            case "7":
                mSpecialize.setText("Marketing & Sales");
                break;
            case "8":
                mSpecialize.setText("Du Lịch/Nhà Hàng/Khách Sạn");
                break;
        }
        switch (LoginActivity.arrSSR.get(0).getCourse()) {
            case "1":
                mClass.setText("PT13301");
                break;
            case "2":
                mClass.setText("PT13302");
                break;
            case "3":
                mClass.setText("PT13303");
                break;
            case "4":
                mClass.setText("PT13304");
                break;
            case "5":
                mClass.setText("PT13305");
                break;
            case "6":
                mClass.setText("PT13306");
                break;
            case "7":
                mClass.setText("PT13307");
                break;
            case "8":
                mClass.setText("PT13308");
                break;
        }
        try {
            date = fmt.parse(LoginActivity.arrSSR.get(0).getStartDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        fmtOut = new SimpleDateFormat("dd-MM-yyyy");
        mStadate.setText(fmtOut.format(date));
        if (LoginActivity.arrSSR.get(0).getStatus().equals("1")) {
            mStatus.setText("Học đi");
        } else {
            mStatus.setText("Học lại");
        }
        tvName.setText(LoginActivity.arrSSR.get(0).getName());
        tvUsername.setText(LoginActivity.arrSSR.get(0).getEmail());
        String avt = LoginActivity.arrSSR.get(0).getAvatar();
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.person)
                .error(R.mipmap.ic_launcher_round);
        Glide.with(this).load(avt).apply(options).into(imvProfile);
    }
}