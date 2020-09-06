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

public class LoginScreen extends AppCompatActivity {

    MaterialButton login;
    TextInputLayout email,password;
    TextView forgot,signup,login_text;
    ApiClient apiClient;
    WaitingDialog waitingDialog;
    PreferencesHelper preferencesHelper;
    PinView pinView;
    Otp_verification otp_verification;
    String action;

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
        login_text=findViewById(R.id.login_text);
        apiClient = new ApiClient();
        waitingDialog= new WaitingDialog(LoginScreen.this);
        preferencesHelper=new PreferencesHelper(getApplicationContext());
        otp_verification=new Otp_verification();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check()) {
                    waitingDialog.SetDialog("Checking...");
                    waitingDialog.show();
                    upload();
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
                            Toast.makeText(getApplicationContext(), "successfully Login", Toast.LENGTH_LONG).show();
                            if (response.body().getStatus().equals("found")) {
                                preferencesHelper.setprofilesetup(true);
                                preferencesHelper.SetWelcome();
                                preferencesHelper.setlogin(true);
                                startActivity(new Intent(getApplicationContext(),HomeScreen.class));
                                finish();
                            }
                            else
                            {
                                preferencesHelper.setlogin(true);
                                startActivity(new Intent(getApplicationContext(),FirstTimeDetails.class));
                                finish();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"Incorrect email or password",Toast.LENGTH_LONG).show();
                        FirebaseAuth.getInstance().signOut();
                        preferencesHelper.setprofilesetup(false);
                        waitingDialog.dismiss();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Incorrect email or password",Toast.LENGTH_LONG).show();
                    FirebaseAuth.getInstance().signOut();
                    preferencesHelper.setprofilesetup(false);
                    waitingDialog.dismiss();
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
}