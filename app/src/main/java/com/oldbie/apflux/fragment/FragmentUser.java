package com.oldbie.apflux.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.oldbie.apflux.DetailUserActivity;
import com.oldbie.apflux.LoginActitity;
import com.oldbie.apflux.R;
import com.oldbie.apflux.model.ModelUser;
import com.oldbie.apflux.network.NetworkAPI;
import com.oldbie.apflux.network.ServiceAPI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import static com.bumptech.glide.request.RequestOptions.centerCropTransform;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentUser extends Fragment {
    private static final String TAG = "FragmentUser";
    private NetworkAPI api;
    GoogleSignInClient mGoogleSignInClient;
    String name, email, thumb, user;
    Date date;
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
                final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor editor = prefs.edit();
                editor.remove("user");
                editor.remove("pass");
                editor.remove("isLoggedIn");
                editor.remove("isTempLoggedIn");
                editor.commit();
                signOut();
                Intent i = new Intent(getContext(), LoginActitity.class);
                startActivity(i);
            }
        });

        lnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailUserActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("thumb", thumb);
                intent.putExtra("date", date);
//                startActivityForResult(intent, 10001);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        lnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailUserActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("thumb", thumb);
                intent.putExtra("date", date);
//                startActivityForResult(intent, 10001);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        getUserDetail(view);
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

    public void getUserDetail(View view) {
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        user = prefs.getString("user", null);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Call<ModelUser> call = api.getUser(user);
                call.enqueue(new Callback<ModelUser>() {
                    @SuppressLint("ResourceType")
                    @Override
                    public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
                        if (response.isSuccessful()) {
                            //get all value for intent purpose
                            name = response.body().getName();
                            email = response.body().getEmail();
                            thumb = response.body().getThumb();
                            date = response.body().getDate_add();

                            tvName.setText(response.body().getName());
                            tvUsername.setText(response.body().getUsername());

                            Glide.with(getContext()).load(response.body().getThumb())
                                    .apply(centerCropTransform()
                                            .placeholder(R.raw.loading)
//                                            .error(R.raw.error)
//                                            .priority(RenderScript.Priority.HIGH)
                                            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                                    .transition(withCrossFade())
                                    .thumbnail(0.5f)
                                    .into(imvProfile);

                        } else {
                            Log.e(TAG, " Response Error " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<ModelUser> call, Throwable t) {
                        Log.e(TAG, " Response Error " + t.getMessage());
                    }
                });
            }
        });
    }
}