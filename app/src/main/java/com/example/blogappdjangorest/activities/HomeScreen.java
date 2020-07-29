package com.example.blogappdjangorest.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.blogappdjangorest.Fragment.ActivityFragment;
import com.example.blogappdjangorest.Fragment.AddBlogFragment;
import com.example.blogappdjangorest.Fragment.HomeFragment;
import com.example.blogappdjangorest.Fragment.ProfileFragment;
import com.example.blogappdjangorest.Fragment.SearchFragment;
import com.example.blogappdjangorest.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeScreen extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        init();
    }
    private void init()
    {
        bottomNavigationView=findViewById(R.id.homescreenbottom);
        getSupportFragmentManager().beginTransaction().replace(R.id.homescreenfragment,new HomeFragment()).commit();
        setBottomNavigationView();
    }

    private void setBottomNavigationView()
    {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;

                switch (item.getItemId()){
                    case R.id.menu_home:
                        fragment=new HomeFragment();
                        break;
                    case R.id.menu_activity:
                        fragment=new ActivityFragment();
                        break;
                    case R.id.menu_add:
                        fragment=new AddBlogFragment();
                        break;
                    case R.id.menu_profile:
                        fragment=new ProfileFragment();
                        break;
                    case R.id.menu_search:
                        fragment=new SearchFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.homescreenfragment,fragment).commit();

                return true;
            }
        });
    }
}