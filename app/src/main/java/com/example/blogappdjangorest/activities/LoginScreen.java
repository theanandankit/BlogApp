package com.example.blogappdjangorest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.blogappdjangorest.Models.RetrofitModels.LoginResponse;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.Retrofit.ApiClient;
import com.example.blogappdjangorest.Services.LoginFirebase;
import com.example.blogappdjangorest.Services.Otp_verification;
import com.example.blogappdjangorest.Services.SignUpupload;
import com.example.blogappdjangorest.resources.PreferencesHelper;
import com.example.blogappdjangorest.resources.WaitingDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreen extends AppCompatActivity implements Otp_verification.OnSuccess, LoginFirebase.LoginSuccess, SignUpupload.OnSuccess {

    MaterialButton login;
    TextInputLayout email,password;
    TextView forgot,signup;
    ApiClient apiClient;
    WaitingDialog waitingDialog;
    PreferencesHelper preferencesHelper;
    PinView pinView;
    Otp_verification otp_verification;
    LoginFirebase loginFirebase;
    SignUpupload signUpupload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        init();
    }

    private void init() {
        login=findViewById(R.id.login_button);
        email=findViewById(R.id.login_email);
        password=findViewById(R.id.login_password);
        forgot=findViewById(R.id.login_forget_password);
        signup=findViewById(R.id.login_signup_text);
        pinView = findViewById(R.id.otp_input);
        apiClient = new ApiClient();
        waitingDialog= new WaitingDialog(LoginScreen.this);
        preferencesHelper=new PreferencesHelper(getApplicationContext());
        otp_verification=new Otp_verification();
        loginFirebase=new LoginFirebase(LoginScreen.this);
        signUpupload=new SignUpupload(LoginScreen.this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check()) {
                    waitingDialog.SetDialog("Authenticating...");
                    signUpupload.exist(email.getEditText().getText().toString());
//                    upload();
                }
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ForgotPasswordRequest.class));
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignUpScreen.class));
            }
        });

    }

    public void upload()
    {
        Call<LoginResponse> call=apiClient.getApiinterface().login(email.getEditText().getText().toString(),password.getEditText().getText().toString());

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.code()==202)
                {
                    try {
                        if (!response.body().getToken().isEmpty()) {
                            preferencesHelper.setToken(response.body().getToken());
                            preferencesHelper.setid(response.body().getId());
                            Log.e("io", response.body().getId());
                            waitingDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "successfully Login", Toast.LENGTH_LONG).show();
                            if (response.body().getStatus().equals("found")) {

                                startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                                preferencesHelper.setprofilesetup(true);
                                preferencesHelper.SetWelcome();
                                finish();
                            }
                            else
                            {
                                startActivity(new Intent(getApplicationContext(),FirstTimeDetails.class));
                                finish();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"Incorrect email and password",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }
    private boolean check()
    {
        if (!email.getEditText().getText().toString().isEmpty())
        {
            if (!password.getEditText().getText().toString().isEmpty())
            {
                return true;
            }
            else {
                password.setError("This field can't be empty");
                password.getEditText().setText("");
                password.requestFocus();
                return false;
            }
        }
        else
        {
            email.setError("This Field can't be empty");
            email.requestFocus();
            return false;
        }
    }

    @Override
    public void completed() {

        waitingDialog.show();
        upload();
    }

    @Override
    public void get_phone(String Phone) {

        final_confition();
        otp_verification.verify("+91"+Phone,LoginScreen.this,pinView,LoginScreen.this);
    }

    @Override
    public void success() {

    }
    @Override
    public void exist(boolean value) {

        if (value)
        {
            loginFirebase.phone_get(email.getEditText().getText().toString());
        }
        else {
            Toast.makeText(getApplicationContext(),"Email does not registered",Toast.LENGTH_LONG).show();
            waitingDialog.dismiss();
        }
    }

    public void final_confition()
    {
        email.setVisibility(View.GONE);
        password.setVisibility(View.GONE);
        pinView.setVisibility(View.VISIBLE);
    }
}