package com.poly.testlogin;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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

import com.poly.testlogin.model.ServerResponse;
import com.poly.testlogin.network.NetworkAPI;
import com.poly.testlogin.network.ServiceAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_LONG;

public class LoginActitity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    RelativeLayout rellay1;
    ImageView imageView;
    EditText etUser,etPass;
    Button btnLogin;
    private NetworkAPI api;

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
//        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //registerUser ServiceAPI and call getJSON from server
        api = ServiceAPI.userService(NetworkAPI.class);

        rellay1 = findViewById(R.id.rellay1);
        imageView = findViewById(R.id.imgView_logo);
        btnLogin = findViewById(R.id.btnLogin);
        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);

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

        //button clickListener
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
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
        Toast.makeText(getBaseContext(), "Username Or Password Isn't Correct!", Toast.LENGTH_SHORT).show();
        btnLogin.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String user = etUser.getText().toString();
        String password = etPass.getText().toString();

        if (user.isEmpty()) {
            etUser.setError("User can't be empty!");
            valid = false;
        }
        if (password.isEmpty()) {
            etPass.setError("Password must from 8 characters!");
            valid = false;
        }
        return valid;
    }

    @Override
    public void onBackPressed() {
//        Intent intent = new Intent(getBaseContext(), LoginActitity.class);
//        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
