package com.oldbie.apflux;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.oldbie.apflux.model.ServerResponse;
import com.oldbie.apflux.model.User;
import com.oldbie.apflux.network.NetworkAPI;
import com.oldbie.apflux.network.ServiceAPI;

import java.util.ArrayList;

import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_LONG;

public class LoginActitity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    RelativeLayout rellay1;
    ImageView imageView;
    MaterialSpinner spinner;
    private NetworkAPI api;
    GoogleSignInClient mGoogleSignInClient;
    public static ArrayList<User> arrSSR;
    String email;
    private String[] ITEMS = null;

    //Animation
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            findViewById(R.id.loadingPanel).setVisibility(View.GONE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        rellay1 = findViewById(R.id.rellay1);
        imageView = findViewById(R.id.imgView_logo);
        spinner = findViewById(R.id.spinner);

        //Google Login Services
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //registerUser ServiceAPI and call getJSON from server
        api = ServiceAPI.userService(NetworkAPI.class);

        // Set the dimensions of the sign-in button.
        final SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        //Spinner set
        ITEMS = getResources().getStringArray(R.array.place_arrays);
        // If this is the initial dummy entry, make it hidden
        // Pass convertView as null to prevent reuse of special case views
        // Hide scroll bar because it appears sometimes unnecessarily, this does not prevent scrolling
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ITEMS) {
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v;

                // If this is the initial dummy entry, make it hidden
                if (position == 0) {
                    TextView tv = new TextView(getContext());
                    tv.setHeight(0);
                    tv.setVisibility(View.GONE);
                    v = tv;
                } else {
                    // Pass convertView as null to prevent reuse of special case views
                    v = super.getDropDownView(position, null, parent);
                }

                // Hide scroll bar because it appears sometimes unnecessarily, this does not prevent scrolling
                parent.setVerticalScrollBarEnabled(false);
                return v;
            }
        };

        //Set spinner adapter
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setPaddingSafe(0, 0, 0, 0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.white));
                if (position != 0) {
                    signInButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Animation
        handler.postDelayed(runnable, 2000); //2000 is the timeout for the splash
        Animation fadeOut = AnimationUtils.loadAnimation(LoginActitity.this, R.anim.fade_out);
        imageView.startAnimation(fadeOut);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation fadeIn = AnimationUtils.loadAnimation(LoginActitity.this, R.anim.fade_in);
                imageView.setImageResource(R.drawable.background_color_book);
                imageView.startAnimation(fadeIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        //Google button OnClickListener
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }

        });
    }

    //SignIn with Google
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 101);
    }

    //SignOut with Google
    private void signOut() {
        mGoogleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                // ...
            onRestart();
            }
        });
    }

    public void login(){
        final ProgressDialog dialog = ProgressDialog.show(LoginActitity.this, "Xác nhận",
                "Chờ tí...", true);
        dialog.show();

        //authentication from server
        new Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess
                            onLoginSuccess();
                        dialog.dismiss();
                    }
                }, 2000);
    }

    public void onLoginSuccess() {
        Call<ServerResponse> call = api.checkLogin(email);
        call.enqueue(new Callback<ServerResponse>() {
            @SuppressLint("ResourceType")
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.body().getResult()==1) {
                    arrSSR = response.body().getData();
//                    Toast.makeText(getBaseContext(), arrSSR.get(0).getId(), Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(i);
            } else {
                    Toast.makeText(LoginActitity.this,"Error!", LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),LoginActitity.class));
                    Log.e(TAG, "onResponse: "+ response.body().getResult());
                    finish();

            }
        }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.e(TAG," Response Error "+ t.getMessage());
                Toast.makeText(getBaseContext(),"Tài khoản không khả dụng", Toast.LENGTH_LONG).show();
                signOut();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    private void updateUI(GoogleSignInAccount account) {
        if (account != null){
            email = account.getEmail();
            //Get Google infomation
//            Toast.makeText(LoginActitity.this,
//                    account.getDisplayName() + "\n"+
//                            account.getGivenName() + "\n"+
//                            account.getFamilyName() + "\n"+
//                            account.getEmail() + "\n"+
//                            account.getId(), LENGTH_LONG).show();
        } else {
            Log.d(TAG, "Not logged in");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 101) {
            // The Task returned from this call is always completed, no need to attach a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
            login();
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Rời khỏi?");
        builder.setMessage("Bạn muốn rời khỏi ?");
        builder.setPositiveButton("Đúng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                finish();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(LoginActitity.this, "Huỷ rời khỏi", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
