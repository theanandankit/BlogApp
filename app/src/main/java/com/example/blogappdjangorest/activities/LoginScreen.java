package com.example.blogappdjangorest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.blogappdjangorest.Dialog.JoinGroup;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.resources.WaitingDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class LoginScreen extends AppCompatActivity {

    MaterialButton login;
    TextInputLayout email,password;
    TextView forgot,signup;
    WaitingDialog dialog;
    JoinGroup joinGroup;

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
        joinGroup = new JoinGroup(LoginScreen.this);
        joinGroup.show();
//        dialog=new WaitingDialog(LoginScreen.this);
//        dialog.SetDialog("Loading...");
//        dialog.show();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HomeScreen.class));
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ForgotPasswordRequest.class));
            }
        });

    }
}