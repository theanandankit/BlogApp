package com.example.blogappdjangorest.activities;

import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.example.blogappdjangorest.Models.RetrofitModels.LoginResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.SignUpResponse;
import com.example.blogappdjangorest.Models.model.SignupFirestoreModel;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.Retrofit.ApiClient;
import com.example.blogappdjangorest.Services.Otp_verification;
import com.example.blogappdjangorest.Services.SignUpupload;
import com.example.blogappdjangorest.resources.PreferencesHelper;
import com.example.blogappdjangorest.resources.WaitingDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpScreen extends AppCompatActivity {
    TextInputLayout email,password,username,firstname;
    MaterialButton button;
    TextView text,login_text;
    WaitingDialog waitingDialog;
    PreferencesHelper preferencesHelper;
    ApiClient apiClient;
    PinView pinView;
    SignUpupload signUpupload;
    Otp_verification otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
        init();
    }
    public void init()
    {
        email=findViewById(R.id.signup_email_layout);
        password=findViewById(R.id.signup_password_layout);
        username=findViewById(R.id.signup_username_layout);
        firstname=findViewById(R.id.signup_first_layout);
        text=findViewById(R.id.signup_main_text);
        button=findViewById(R.id.signup_register);
        login_text=findViewById(R.id.signup_login_text);
        preferencesHelper=new PreferencesHelper(getApplicationContext());
        waitingDialog=new WaitingDialog(SignUpScreen.this);
        apiClient=new ApiClient();
        pinView=findViewById(R.id.otp_input);
        otp=new Otp_verification();
        login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_initial()&&check_final()) {
                    waitingDialog.SetDialog("Creating you account\nPlease wait..");
                    waitingDialog.show();
                    register();
                }
            }
        });
    }
    private boolean check_initial()
    {
        if (!email.getEditText().getText().toString().isEmpty())
        {
            if (!password.getEditText().getText().toString().isEmpty())
            {
                return true;
            }
            else
            {
                password.setError("password is required");
                password.requestFocus();
                return false;
            }
        }
        else
        {
            email.setError("Enter your Email");
            email.requestFocus();
            return false;
        }
    }
    private boolean check_final()
    {
        if (!username.getEditText().getText().toString().isEmpty())
        {
            if (!firstname.getEditText().getText().toString().isEmpty())
            {
               return true;
            }
            else
            {
                firstname.setError("This field Is required");
                firstname.requestFocus();
                return false;
            }
        }
        else
        {
            username.setError("Username is required");
            username.requestFocus();
            return false;
        }
    }
    private void register()
    {
        Call<SignUpResponse> call=apiClient.getApiinterface().register(email.getEditText().getText().toString(),password.getEditText().getText().toString(),username.getEditText().getText().toString(),firstname.getEditText().getText().toString(),"");
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {

                if (response.code()==200)
                {
                    if (!response.body().toString().isEmpty())
                    {
                        try {
                            if (!response.body().getToken().isEmpty())
                            {
                                login();
                            }
                        }
                        catch (Exception e)
                        {
                            Toast.makeText(getApplicationContext(),"username or email already exist",Toast.LENGTH_LONG).show();
                            waitingDialog.dismiss();
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {

            }
        });
    }

    public void login()
    {
        Call<LoginResponse> call=apiClient.getApiinterface().login(email.getEditText().getText().toString(),password.getEditText().getText().toString());
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.code()==202)
                {
                    try {
                        if (!response.body().getToken().isEmpty()&&response.body().getStatus().equals("Not")) {
                            preferencesHelper.setToken(response.body().getToken());
                            preferencesHelper.setid(response.body().getId());
                            waitingDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "successfully Registered", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),FirstTimeDetails.class));
                            preferencesHelper.SetWelcome();
                            preferencesHelper.setlogin(true);
                            finish();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"Incorrect email of password",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }
}