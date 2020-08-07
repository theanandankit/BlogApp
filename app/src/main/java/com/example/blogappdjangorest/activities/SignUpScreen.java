package com.example.blogappdjangorest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blogappdjangorest.Models.RetrofitModels.SignUpResponse;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.Retrofit.ApiClient;
import com.example.blogappdjangorest.resources.PreferencesHelper;
import com.example.blogappdjangorest.resources.WaitingDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpScreen extends AppCompatActivity {
    TextInputLayout email,password,repassword,username,firstname,lastname;
    MaterialButton button;
    TextView text;
    WaitingDialog waitingDialog;
    PreferencesHelper preferencesHelper;
    ApiClient apiClient;
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
        repassword=findViewById(R.id.signup_reenter_email_layout);
        username=findViewById(R.id.signup_username_layout);
        firstname=findViewById(R.id.signup_first_layout);
        lastname=findViewById(R.id.signup_Last_layout);
        text=findViewById(R.id.signup_main_text);
        button=findViewById(R.id.signup_register);
        preferencesHelper=new PreferencesHelper(getApplicationContext());
        waitingDialog=new WaitingDialog(SignUpScreen.this);
        apiClient=new ApiClient();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getVisibility()==View.VISIBLE&&check_initial())
                    final_condition();
                else
                {
                    if (check_final())
                    {
                        register();
                    }
                }
            }
        });
    }
    public void initial_condition()
    {
        username.setVisibility(View.GONE);
        firstname.setVisibility(View.GONE);
        lastname.setVisibility(View.GONE);
        email.setVisibility(View.VISIBLE);
        password.setVisibility(View.VISIBLE);
        repassword.setVisibility(View.VISIBLE);
    }
    public void final_condition()
    {
        email.setVisibility(View.GONE);
        password.setVisibility(View.GONE);
        repassword.setVisibility(View.GONE);
        username.setVisibility(View.VISIBLE);
        firstname.setVisibility(View.VISIBLE);
        lastname.setVisibility(View.VISIBLE);
    }
    private boolean check_initial()
    {
        if (!email.getEditText().getText().toString().isEmpty())
        {
            if (!password.getEditText().getText().toString().isEmpty())
            {
                if (!repassword.getEditText().getText().toString().isEmpty())
                {
                    if (password.getEditText().getText().toString().equals(repassword.getEditText().getText().toString()))
                    {
                        return true;
                    }
                    else
                    {
                        repassword.setError("Password must be same");
                        repassword.getEditText().setText("");
                        repassword.requestFocus();
                        return false;
                    }
                }
                else
                {
                    repassword.setError("password is required");
                    repassword.requestFocus();
                    return false;
                }
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
                if (!lastname.getEditText().getText().toString().isEmpty())
                {
                    return true;
                }
                else
                {
                    lastname.setError("This field Is required");
                    lastname.requestFocus();
                    return false;
                }
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
        Call<SignUpResponse> call=apiClient.getApiinterface().register(email.getEditText().getText().toString(),password.getEditText().getText().toString(),username.getEditText().getText().toString(),firstname.getEditText().getText().toString(),lastname.getEditText().getText().toString());
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {

                if (response.code()==200)
                {
                    if (!response.body().toString().isEmpty())
                    {
                        waitingDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Successfully registered",Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {

            }
        });
    }
}