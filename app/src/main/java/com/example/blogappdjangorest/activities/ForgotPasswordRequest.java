package com.example.blogappdjangorest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.blogappdjangorest.Fragment.ForgetPasswardFragment;
import com.example.blogappdjangorest.Fragment.HomeFragment;
import com.example.blogappdjangorest.R;

public class ForgotPasswordRequest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_passward);
        getSupportFragmentManager().beginTransaction().replace(R.id.ForgetPasswardFragment,new ForgetPasswardFragment()).commit();

    }
}