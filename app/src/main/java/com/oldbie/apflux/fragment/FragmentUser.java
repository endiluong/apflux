package com.oldbie.apflux.fragment;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.oldbie.apflux.DetailUserActivity;
import com.oldbie.apflux.LoginActitity;
import com.oldbie.apflux.R;
import com.oldbie.apflux.network.NetworkAPI;
import com.oldbie.apflux.network.ServiceAPI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentUser extends Fragment {
    private static final String TAG = "FragmentUser";
    private NetworkAPI api;
    GoogleSignInClient mGoogleSignInClient;
    String avt;
    LinearLayout lnProfile,lnDetail;
    TextView tvName, tvUsername;
    ImageView imvProfile;
    Button btnLogout;

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

        lnProfile = view.findViewById(R.id.lnProfileSum);
        lnDetail = view.findViewById(R.id.lnDetail);
        tvName = view.findViewById(R.id.tvProfileName);
        tvUsername = view.findViewById(R.id.tvRole);
        imvProfile = view.findViewById(R.id.imvProfile);
        btnLogout = view.findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Exit?");
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        signOut();
//                        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
//                        SharedPreferences.Editor editor = prefs.edit();
//                        editor.remove("user");
//                        editor.remove("pass");
//                        editor.remove("isLoggedIn");
//                        editor.remove("isTempLoggedIn");
//                        editor.apply();
                        Intent a = new Intent(getContext(), LoginActitity.class);
                        startActivity(a);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(), "Canceled", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();



            }
        });

        lnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailUserActivity.class);
//                intent.putExtra("user", user);
//                intent.putExtra("name", name);
//                intent.putExtra("email", email);
//                intent.putExtra("thumb", thumb);
//                intent.putExtra("date", date);
//                startActivityForResult(intent, 10001);
                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        lnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailUserActivity.class);
//                intent.putExtra("user", user);
//                intent.putExtra("name", name);
//                intent.putExtra("email", email);
//                intent.putExtra("thumb", thumb);
//                intent.putExtra("date", date);
//                startActivityForResult(intent, 10001);
                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        getUserDetail();
//
//        getUserProfile(AccessToken.getCurrentAccessToken());
        return view;

    }

    private void signOut() {
        mGoogleSignInClient.signOut().addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                // ...
                Intent i = new Intent(getContext(), LoginActitity.class);
                startActivity(i);
            }
        });
    }

    public void getUserDetail() {
        tvName.setText(LoginActitity.arrSSR.get(0).getName());
        tvUsername.setText(LoginActitity.arrSSR.get(0).getUsername());
        avt = LoginActitity.arrSSR.get(0).getAvatar();

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.person)
                .error(R.mipmap.ic_launcher_round);

        Glide.with(this).load(avt).apply(options).into(imvProfile);
    }
}