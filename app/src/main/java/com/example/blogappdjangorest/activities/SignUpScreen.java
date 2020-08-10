package com.example.blogappdjangorest.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
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

public class SignUpScreen extends AppCompatActivity implements Otp_verification.OnSuccess, SignUpupload.OnSuccess {
    TextInputLayout email,password,username,firstname,phone;
    MaterialButton button;
    TextView text;
    WaitingDialog waitingDialog;
    PreferencesHelper preferencesHelper;
    ApiClient apiClient;
    PinView pinView;
    SignUpupload signUpupload;
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
        phone=findViewById(R.id.signup_phone_layout);
        text=findViewById(R.id.signup_main_text);
        button=findViewById(R.id.signup_register);
        preferencesHelper=new PreferencesHelper(getApplicationContext());
        waitingDialog=new WaitingDialog(SignUpScreen.this);
        apiClient=new ApiClient();
        pinView=findViewById(R.id.otp_input);
        signUpupload=new SignUpupload(SignUpScreen.this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getVisibility()==View.VISIBLE&&check_initial()&&check_final())
                    signUpupload.exist(email.getEditText().getText().toString());
                else
                {
//                    if (check_final())
//                    { signUpupload.exist(email.getEditText().getText().toString());
//                    }

                }
            }
        });
    }
    public void final_condition()
    {
        email.setVisibility(View.GONE);
        password.setVisibility(View.GONE);
        username.setVisibility(View.GONE);
        firstname.setVisibility(View.GONE);
        phone.setVisibility(View.GONE);
        pinView.setVisibility(View.VISIBLE);
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
                if ((!phone.getEditText().getText().toString().isEmpty())&&phone.getEditText().getText().length()==10)
                {
                    return true;
                }
                else
                {
                    phone.setError("This field Is required");
                    phone.requestFocus();
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
        Call<SignUpResponse> call=apiClient.getApiinterface().register(email.getEditText().getText().toString(),password.getEditText().getText().toString(),username.getEditText().getText().toString(),firstname.getEditText().getText().toString(),"");
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {

                if (response.code()==200)
                {
                    if (!response.body().toString().isEmpty())
                    {
                        signUpupload.upload(new SignupFirestoreModel(email.getEditText().getText().toString(),response.body().getToken(),phone.getEditText().getText().toString(),firstname.getEditText().getText().toString()));
                    }
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void completed() {
        register();
    }

    @Override
    public void success() {
        Toast.makeText(getApplicationContext(),"successfully added",Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void exist(boolean value) {
        if (value)
        {
            Toast.makeText(getApplicationContext(),"user already exist",Toast.LENGTH_LONG).show();
        }
        else
        {
            final_condition();
            Otp_verification otp=new Otp_verification();
            otp.verify("+91"+phone.getEditText().getText().toString(),getApplicationContext(),pinView,SignUpScreen.this);
        }
    }
}