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
import com.oldbie.apflux.LoginActitity;
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
    private NetworkAPI api;
    private GoogleSignInClient mGoogleSignInClient;
    private TextView tvName, tvUsername;
    private ImageView imvProfile;

    TextView mName, mUser, mPhone, mAddress, mIdent, mStudentId, mBirthday,
            mGender, mMajor,mSpecialize, mClass, mStadate, mStatus;
    String avt;
    Date date;

    SimpleDateFormat fmtOut = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        //registerUser ServiceAPI and call getJSON from server
        api = ServiceAPI.userService(NetworkAPI.class);

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
//        mMajor.setText(LoginActitity.arrSSR.get(0).getMajor());
        if(LoginActitity.arrSSR.get(0).getMajor().equals("1")) {
            mMajor.setText("Công Nghệ Thông Tin");
        } else if (LoginActitity.arrSSR.get(0).getMajor().equals("2")) {
            mMajor.setText("Du lịch - Nhà hàng - Khách sạn");
        } else if (LoginActitity.arrSSR.get(0).getMajor().equals("3")) {
            mMajor.setText("Kinh Tế Kinh Doanh");
        }

//        mSpecialize.setText(LoginActitity.arrSSR.get(0).getSpecialize());
        if(LoginActitity.arrSSR.get(0).getSpecialize().equals("1")) {
            mSpecialize.setText("Lập Trình Máy Tính/Thiết Bị Di Động");
        } else if (LoginActitity.arrSSR.get(0).getSpecialize().equals("2")) {
            mSpecialize.setText("Thiết Kế Website");
        } else if (LoginActitity.arrSSR.get(0).getSpecialize().equals("3")) {
            mSpecialize.setText("CNTT/ Ứng Dụng Phần Mềm");
        } else if (LoginActitity.arrSSR.get(0).getSpecialize().equals("4")) {
            mSpecialize.setText("Thiết Kế Đồ Hoạ");
        } else if (LoginActitity.arrSSR.get(0).getSpecialize().equals("5")) {
            mSpecialize.setText("Digital/ Online Marketing");
        } else if (LoginActitity.arrSSR.get(0).getSpecialize().equals("6")) {
            mSpecialize.setText("Tổ Chức Sự Kiện");
        } else if (LoginActitity.arrSSR.get(0).getSpecialize().equals("7")) {
            mSpecialize.setText("Marketing & Sales");
        } else if (LoginActitity.arrSSR.get(0).getSpecialize().equals("8")) {
            mSpecialize.setText("Digital/ Online Marketing");
        }
//        mClass.setText(LoginActitity.arrSSR.get(0).getCourse());
        if(LoginActitity.arrSSR.get(0).getCourse().equals("1")) {
            mClass.setText("PT13301");
        } else if (LoginActitity.arrSSR.get(0).getCourse().equals("2")) {
            mClass.setText("PT13302");
        } else if (LoginActitity.arrSSR.get(0).getCourse().equals("3")) {
            mClass.setText("PT13303");
        } else if (LoginActitity.arrSSR.get(0).getCourse().equals("4")) {
            mClass.setText("PT13304");
        } else if (LoginActitity.arrSSR.get(0).getCourse().equals("5")) {
            mClass.setText("PT13305");
        } else if (LoginActitity.arrSSR.get(0).getCourse().equals("6")) {
            mClass.setText("PT13306");
        } else if (LoginActitity.arrSSR.get(0).getCourse().equals("7")) {
            mClass.setText("PT13307");
        } else if (LoginActitity.arrSSR.get(0).getCourse().equals("8")) {
            mClass.setText("PT13308");
        }
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
        tvName.setText(LoginActitity.arrSSR.get(0).getName());
        tvUsername.setText(LoginActitity.arrSSR.get(0).getEmail());

        avt = LoginActitity.arrSSR.get(0).getAvatar();
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.person)
                .error(R.mipmap.ic_launcher_round);

        Glide.with(this).load(avt).apply(options).into(imvProfile);
    }
}