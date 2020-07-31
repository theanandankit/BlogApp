package com.example.blogappdjangorest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.blogappdjangorest.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpScreen extends AppCompatActivity {
    TextInputLayout email,password,repassword,username,firstname,lastname;
    MaterialButton button;
    TextView text;
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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getVisibility()==View.VISIBLE)
                    final_condition();
                else
                {
                    initial_condition();
                    finish();
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
}