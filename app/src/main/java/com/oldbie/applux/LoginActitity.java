package com.oldbie.applux;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.oldbie.applux.model.ServerResponse;
import com.oldbie.applux.network.NetworkAPI;
import com.oldbie.applux.network.ServiceAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

public class LoginActitity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    RelativeLayout rellay1;
    ImageView imageView;
    EditText etUser,etPass;
    TextInputLayout TILPass,TILUser;
    Button btnLogin;
    private NetworkAPI api;
    GoogleSignInClient mGoogleSignInClient;

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

        //Google Login Services
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //registerUser ServiceAPI and call getJSON from server
        api = ServiceAPI.userService(NetworkAPI.class);

        rellay1 = findViewById(R.id.rellay1);
        imageView = findViewById(R.id.imgView_logo);
        btnLogin = findViewById(R.id.btnLogin);
        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);
        TILPass = findViewById(R.id.TILPass);
        TILUser = findViewById(R.id.TILUser);
        // Set the dimensions of the sign-in button.
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

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

        //button OnClickListener
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }

        });

        //google button OnClickListener
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }

        });

        //press Enter to login
        etPass.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            login();
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        OnTextChanged();
    }

    //OnTextChangedListener
    private void OnTextChanged(){
        etUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TILUser.setErrorEnabled(false); // disable error
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        etPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TILPass.setErrorEnabled(false); // disable error
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 101);
    }

    public void login(){
        final ProgressDialog dialog = ProgressDialog.show(LoginActitity.this, "Authenticating",
                "Wait a bit mate!", true);
        dialog.show();

        //authentication from server
        new Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        if (validate()){
                            onLoginSuccess();
                        } else {
                            onLoginFailed();
                        }
                        // onLoginFailed();
                        dialog.dismiss();
                    }
                }, 2000);
    }

    public void onLoginSuccess() {
        final String user = etUser.getText().toString();
        final String pass = etPass.getText().toString();

        Call<ServerResponse> call = api.checkLogin(user, pass);
        call.enqueue(new Callback<ServerResponse>() {
            @SuppressLint("ResourceType")
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.body().getResult() == 1) {
                    Toast.makeText(LoginActitity.this,"Successful", LENGTH_LONG).show();
                    Intent i = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(i);
            } else {
                    Toast.makeText(LoginActitity.this,"Failed!", LENGTH_LONG).show();
                Log.e(TAG," Response Error " + response.code());
            }
        }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.e(TAG," Response Error "+ t.getMessage());
            }
        });
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Username Or Password Isn't Correct!", LENGTH_SHORT).show();
        btnLogin.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String user = etUser.getText().toString();
        String password = etPass.getText().toString();

        if (user.isEmpty()) {
            TILUser.setError("User can't be empty!");
            valid = false;
        }
        if (password.length() <= 8) {
            TILPass.setError("Password must from 8 characters!");

            valid = false;
        }
        return valid;
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
//            Toast.makeText(LoginActitity.this,"Google already logged in", LENGTH_SHORT).show();
            Toast.makeText(LoginActitity.this,
                    account.getDisplayName() + "\n"+
                            account.getGivenName() + "\n"+
                            account.getFamilyName() + "\n"+
                            account.getEmail() + "\n"+
                            account.getId(), LENGTH_LONG).show();
            Intent i = new Intent(getBaseContext(), MainActivity.class);
            startActivity(i);
            finish();
        } else {
            Log.d(TAG, "Not logged in");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 101) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
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
        builder.setTitle("Exit?");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(LoginActitity.this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
//        super.onBackPressed();
    }
}
