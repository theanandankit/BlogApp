package com.example.blogappdjangorest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.blogappdjangorest.Models.RetrofitModels.LoginResponse;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.Retrofit.ApiClient;
import com.example.blogappdjangorest.resources.PreferencesHelper;
import com.example.blogappdjangorest.resources.WaitingDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreen extends AppCompatActivity {

    MaterialButton login;
    TextInputLayout email,password;
    TextView forgot,signup;
    ApiClient apiClient;
    WaitingDialog waitingDialog;
    PreferencesHelper preferencesHelper;

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
        apiClient = new ApiClient();
        waitingDialog= new WaitingDialog(LoginScreen.this);
        preferencesHelper=new PreferencesHelper(getApplicationContext());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check()) {
                    waitingDialog.SetDialog("Authenticating...");
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

                if (response.code()==200)
                {
                    if (!response.body().getToken().isEmpty()) {
                        preferencesHelper.setToken(response.body().getToken());
                        preferencesHelper.setid(response.body().getId());
                        waitingDialog.dismiss();
                        startActivity(new Intent(getApplicationContext(), HomeScreen.class));
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
}