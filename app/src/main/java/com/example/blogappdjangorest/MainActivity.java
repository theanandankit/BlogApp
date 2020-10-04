package com.example.blogappdjangorest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blogappdjangorest.activities.FirstTimeDetails;
import com.example.blogappdjangorest.activities.HomeScreen;
import com.example.blogappdjangorest.activities.LoginScreen;
import com.example.blogappdjangorest.activities.Welcome_screen;
import com.example.blogappdjangorest.resources.PreferencesHelper;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    PreferencesHelper preferencesHelper;
    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        imageView=findViewById(R.id.image);
        textView=findViewById(R.id.text);

        TransitionDrawable drawable = (TransitionDrawable) imageView.getDrawable();
        drawable.startTransition(3000);

        preferencesHelper=new PreferencesHelper(getApplicationContext());
        Log.e("a", String.valueOf(preferencesHelper.getprofilesetup()));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Log.e("ugi", String.valueOf(preferencesHelper.getWelcome()));

                try {

                    if (!preferencesHelper.islogin()) {

                        if (!preferencesHelper.getWelcome()) {
                            startActivity(new Intent(getApplicationContext(), Welcome_screen.class));
                        } else {
                            startActivity(new Intent(getApplicationContext(), LoginScreen.class));
                        }
                    }
                    else
                    {
                        if (preferencesHelper.getprofilesetup())
                        {
                            startActivity(new Intent(getApplicationContext(),HomeScreen.class));
                        }
                        else
                        {
                            startActivity(new Intent(getApplicationContext(),FirstTimeDetails.class));
                        }
                    }
                }
                catch (Exception e)
                {
                    if (!preferencesHelper.getWelcome()) {
                        startActivity(new Intent(getApplicationContext(), Welcome_screen.class));
                    } else {
                        startActivity(new Intent(getApplicationContext(), LoginScreen.class));
                    }
                }
                finish();
            }
        },3000);
    }
}