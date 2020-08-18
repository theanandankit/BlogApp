package com.example.blogappdjangorest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.blogappdjangorest.Fragment.AddBlogFragment;
import com.example.blogappdjangorest.Fragment.GroupFragment;
import com.example.blogappdjangorest.Fragment.HomeFragment;
import com.example.blogappdjangorest.Fragment.ProfileFragment;
import com.example.blogappdjangorest.Fragment.SearchFragment;
import com.example.blogappdjangorest.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class HomeScreen extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        init();

    }


    private void init()
    {
        bottomNavigationView=findViewById(R.id.homescreenbottom);
        fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.homescreenfragment,new HomeFragment()).addToBackStack("Home").commit();
        setBottomNavigationView();
    }

    private void setBottomNavigationView()
    {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;
                String fragmentId;

                switch (item.getItemId()){
                    case R.id.menu_home:
                        fragment=new HomeFragment();
                        fragmentId="Home";
                        break;
                    case R.id.menu_groups:
                        fragment=new GroupFragment();
                        fragmentId = "Groups";
                        break;
                    case R.id.menu_add:
                        fragment=new AddBlogFragment();
                        fragmentId = "Add Blog";
                        break;
                    case R.id.menu_profile:
                        fragment=new ProfileFragment();
                        fragmentId = "Profile";
                        break;
                    case R.id.menu_search:
                        fragment=new SearchFragment();
                        fragmentId = "Search";
                        break;
                    default:
                        fragment=new HomeFragment();
                        fragmentId="Home";
                }

                if(!(fm.getBackStackEntryAt(fm.getBackStackEntryCount()-1).getName().equals(fragmentId)))
                    fm.beginTransaction().replace(R.id.homescreenfragment,fragment,fragmentId).addToBackStack(fragmentId).commit();
                else
                    fm.beginTransaction().replace(R.id.homescreenfragment,fragment,fragmentId).commit();

                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (fm.getBackStackEntryCount() > 1) {
            if (fm.getBackStackEntryAt(fm.getBackStackEntryCount() - 1).getName().equals("Home")) {
                finish();
            } else {
                fm.popBackStackImmediate();
                Log.d("LastFrag", fm.getBackStackEntryCount() + "");
                for (int i = 0; i < fm.getBackStackEntryCount(); i++) {
                    Log.d("LastFrag", fm.getBackStackEntryAt(i).getName());
                }
                System.out.println(fm.getBackStackEntryCount());
                switch (Objects.requireNonNull(fm.getBackStackEntryAt(fm.getBackStackEntryCount()-1).getName())) {
                    case "Search":
                        bottomNavigationView.setSelectedItemId(R.id.menu_search);
                        break;
                    case "Groups":
                        bottomNavigationView.setSelectedItemId(R.id.menu_groups);
                        break;
                    case "Add Blog":
                        bottomNavigationView.setSelectedItemId(R.id.menu_add);
                        break;
                    case "Profile":
                        bottomNavigationView.setSelectedItemId(R.id.menu_profile);
                        break;
                    default:
                        bottomNavigationView.setSelectedItemId(R.id.menu_home);
                }

            }

        } else {
            super.onBackPressed();
            finish();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}