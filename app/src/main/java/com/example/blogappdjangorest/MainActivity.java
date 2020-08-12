package com.example.blogappdjangorest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.blogappdjangorest.activities.HomeScreen;
import com.example.blogappdjangorest.activities.LoginScreen;
import com.example.blogappdjangorest.resources.PreferencesHelper;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferencesHelper=new PreferencesHelper(getApplicationContext());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(null))
                {
                    startActivity(new Intent(getApplicationContext(), LoginScreen.class));
                }
                else
                {
                    startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                }

            }
        },2000);
    }
}