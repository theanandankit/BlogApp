package com.example.blogappdjangorest.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.blogappdjangorest.Adapter.HomeScreenAdapter;
import com.example.blogappdjangorest.Adapter.ProfileViewBlogAdapter;
import com.example.blogappdjangorest.R;

public class ProfileView extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        recyclerView=findViewById(R.id.recycleView);
        ProfileViewBlogAdapter profileViewBlogAdapter=new ProfileViewBlogAdapter(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(profileViewBlogAdapter);
    }
}