package com.oldbie.apflux;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;
import com.oldbie.apflux.fragment.FragmentHome;
import com.oldbie.apflux.fragment.FragmentMark;
import com.oldbie.apflux.fragment.FragmentNews;
import com.oldbie.apflux.fragment.FragmentTimetable;
import com.oldbie.apflux.fragment.FragmentUser;
import com.oldbie.apflux.network.NetworkAPI;

public class MainActivity extends AppCompatActivity {

    Fragment fragment = null;
    private TextView tvTitle;
    private Button btnLogout;
    private GoogleSignInClient mGoogleSignInClient;
    public static SpaceNavigationView spaceNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new FragmentHome());

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        tvTitle = findViewById(R.id.tvTitle);

        spaceNavigationView = findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("TIMETABLE", R.raw.time_table));
        spaceNavigationView.addSpaceItem(new SpaceItem("MARK", R.raw.grade));
        spaceNavigationView.addSpaceItem(new SpaceItem("NEWS", R.raw.news));
        spaceNavigationView.addSpaceItem(new SpaceItem("USER", R.raw.profile));
        spaceNavigationView.showIconOnly();
        spaceNavigationView.setCentreButtonSelectable(true);
        spaceNavigationView.setCentreButtonSelected();

        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                loadFragment(new FragmentHome());
                tvTitle.setText(R.string.app_name);
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                switch (itemName) {
                case "NEWS":
                    fragment = new FragmentNews();
                    tvTitle.setText("Tin Tức");
                    break;

                case "TIMETABLE":
                    fragment = new FragmentTimetable();
                    tvTitle.setText("Thời Khoá Biểu");

                    break;

                case "USER":
                    fragment = new FragmentUser();
                    tvTitle.setText("Thông Tin Cá Nhân");
                    break;

                case "MARK":
                    fragment = new FragmentMark();
                    tvTitle.setText("Bảng Điểm");
                    break;
                }
                loadFragment(fragment);
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {

            }
        });

        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Đăng xuất?");
                builder.setMessage("Bạn có muốn đăng xuất?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        signOut();
                        Intent a = new Intent(getBaseContext(), LoginActivity.class);
                        startActivity(a);
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                android.app.AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    private void signOut() {
        mGoogleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                // ...
                Intent i = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(i);
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        //switching fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        if (fragment != null) {
            getSupportFragmentManager().popBackStack();
            transaction.replace(R.id.fragment, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.logoc)
                .setTitle(R.string.app_name)
                .setMessage(R.string.exit_app)
                .setPositiveButton("Có", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        moveTaskToBack(true);
                        finish();
                    }
                })
                .setNegativeButton("Không", null)
                .show();
    }
}
