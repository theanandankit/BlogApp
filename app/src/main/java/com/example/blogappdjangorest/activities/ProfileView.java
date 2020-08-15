package com.example.blogappdjangorest.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogappdjangorest.Adapter.ProfileViewBlogAdapter;
import com.example.blogappdjangorest.Models.RetrofitModels.StartFollowResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.data.ProfileUser;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.Retrofit.ApiClient;
import com.example.blogappdjangorest.resources.PreferencesHelper;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileView extends AppCompatActivity {

    RecyclerView recyclerView;
    CircleImageView image;
    TextView name,follow,following,blog,description;
    MaterialButton follow_button;
    ApiClient apiClient;
    String user_id;
    PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        image=findViewById(R.id.profileimage);
        name=findViewById(R.id.nameprofile);
        follow=findViewById(R.id.follower);
        following=findViewById(R.id.following);
        blog=findViewById(R.id.total_blog);
        description=findViewById(R.id.description);
        follow_button=findViewById(R.id.follow_button);
        follow_button.setText("checking");
        follow_button.setEnabled(false);
        apiClient=new ApiClient();
        user_id=getIntent().getStringExtra("user_id");
        recyclerView=findViewById(R.id.recycleView);
        preferencesHelper = new PreferencesHelper(getApplicationContext());
        get_info();
        check_follow();


        follow_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start_follow();
            }
        });
    }

    private void get_info()
    {
        Log.e("ok",user_id);
        Call<ArrayList<ProfileUser>> call=apiClient.getApiinterface().profileUser(Integer.parseInt(user_id));
        call.enqueue(new Callback<ArrayList<ProfileUser>>() {
            @Override
            public void onResponse(Call<ArrayList<ProfileUser>> call, Response<ArrayList<ProfileUser>> response) {

                if (response.code()==200)
                {
                    Log.e("ok","1");
                    if (!(response.body().size() ==0))
                    {
                        Log.e("ok","2");
                        ProfileViewBlogAdapter profileViewBlogAdapter=new ProfileViewBlogAdapter(ProfileView.this,response.body().get(0).getAuthorName());
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.setAdapter(profileViewBlogAdapter);
                        set_profile(response.body().get(0));
                        name.setText(response.body().get(0).getFirstName()+" "+response.body().get(0).getLastName());
                    }
                }
                else
                {
                    Log.e("ok","3");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProfileUser>> call, Throwable t) {
                Log.e("error",t.getMessage());
            }
        });
    }
    private void set_profile(ProfileUser response)
    {
        follow.setText(String.valueOf(response.getPersonList1().size()));
        following.setText(String.valueOf(response.getPersonList2().size()));
        blog.setText(String.valueOf(response.getAuthorName().size()));
        description.setText(response.getUserDetails().get(0).getDescription());
        Picasso.get().load(response.getUserDetails().get(0).getUrl()).into(image);
    }

    private void start_follow()
    {
        Log.e("ok","pk");
        Call<StartFollowResponse> call=apiClient.getApiinterface().follow(preferencesHelper.getid(),user_id);
        call.enqueue(new Callback<StartFollowResponse>() {
            @Override
            public void onResponse(Call<StartFollowResponse> call, Response<StartFollowResponse> response) {

                if (response.code()==200) {
                    if (response.body().getResponse().equals("Success")) {

                        follow_button.setText("Already follow");
                        Toast.makeText(getApplicationContext(), "started Following", Toast.LENGTH_LONG).show();
                        follow_button.setEnabled(false);
                    }
                }
            }

            @Override
            public void onFailure(Call<StartFollowResponse> call, Throwable t) {

                Log.e("error","error");
            }
        });
    }
    private void check_follow()
    {
        Call<StartFollowResponse> call=apiClient.getApiinterface().check_follow(preferencesHelper.getid(),user_id);
        call.enqueue(new Callback<StartFollowResponse>() {
            @Override
            public void onResponse(Call<StartFollowResponse> call, Response<StartFollowResponse> response) {

                if (response.code()==200)
                {
                    if (response.body().getResponse().equals("Not exist"))
                    {
                        follow_button.setEnabled(true);
                        follow_button.setText("Follow");
                    }
                    else
                    {
                        follow_button.setText("Already follow");
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<StartFollowResponse> call, Throwable t) {

            }
        });
    }
}