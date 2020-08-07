package com.example.blogappdjangorest.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogappdjangorest.Adapter.ProfileViewBlogAdapter;
import com.example.blogappdjangorest.R;
import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileView extends AppCompatActivity {

    RecyclerView recyclerView;
    CircleImageView image;
    TextView name,follow,following,blog,description;
    MaterialButton follow_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        image=findViewById(R.id.circleImageView);
        name=findViewById(R.id.name);
        follow=findViewById(R.id.follower);
        following=findViewById(R.id.following);
        blog=findViewById(R.id.total_blog);
        description=findViewById(R.id.description);
        follow_button=findViewById(R.id.follow_button);


        recyclerView=findViewById(R.id.recycleView);
        ProfileViewBlogAdapter profileViewBlogAdapter=new ProfileViewBlogAdapter(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(profileViewBlogAdapter);
    }
}