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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.oldbie.apflux.fragment.FragmentHome;
import com.oldbie.apflux.fragment.FragmentMark;
import com.oldbie.apflux.fragment.FragmentNews;
import com.oldbie.apflux.fragment.FragmentTimetable;
import com.oldbie.apflux.fragment.FragmentUser;
import com.oldbie.apflux.network.NetworkAPI;

public class MainActivity extends AppCompatActivity {

    Fragment fragment = null;
    Button btnLogout;
    private NetworkAPI api;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new FragmentHome());

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        BottomNavigationView navigation = findViewById(R.id.nav_view);
        btnLogout = findViewById(R.id.btnLogout);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Logout?");
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        signOut();
                        Intent a = new Intent(getBaseContext(), LoginActitity.class);
                        startActivity(a);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getBaseContext(), "Canceled", Toast.LENGTH_SHORT).show();
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
                Intent i = new Intent(getBaseContext(), LoginActitity.class);
                startActivity(i);
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new FragmentHome();
                    break;

                case R.id.navigation_news:
                    fragment = new FragmentNews();
                    break;

                case R.id.navigation_timeTable:
                    fragment = new FragmentTimetable();
                    break;

                case R.id.navigation_user:
                    fragment = new FragmentUser();
                    break;

                case R.id.navigation_mark:
                    fragment = new FragmentMark();
                    break;
            }
            return loadFragment(fragment);
        }
    };

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

        if (fragment != null) {
            getSupportFragmentManager().popBackStack();
            transaction.replace(R.id.fragment, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            return true;
        }
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.raw.loading)
                .setTitle(R.string.app_name)
                .setMessage(R.string.exit_app)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        moveTaskToBack(true);
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
