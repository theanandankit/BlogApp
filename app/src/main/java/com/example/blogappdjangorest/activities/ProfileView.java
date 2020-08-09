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
import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Text;

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
        apiClient=new ApiClient();


        recyclerView=findViewById(R.id.recycleView);
        get_info();

        follow_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start_follow();
            }
        });
    }

    private void get_info()
    {
        Call<ArrayList<ProfileUser>> call=apiClient.getApiinterface().profileUser(10);
        call.enqueue(new Callback<ArrayList<ProfileUser>>() {
            @Override
            public void onResponse(Call<ArrayList<ProfileUser>> call, Response<ArrayList<ProfileUser>> response) {

                if (response.code()==200)
                {
                    if (!(response.body().size() ==0))
                    {
                        ProfileViewBlogAdapter profileViewBlogAdapter=new ProfileViewBlogAdapter(getApplicationContext(),response.body().get(0).getAuthorName());
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.setAdapter(profileViewBlogAdapter);
                        set_profile(response.body().get(0));
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProfileUser>> call, Throwable t) {

            }
        });
    }
    private void set_profile(ProfileUser response)
    {
        name.setText(response.getFirstName()+" "+response.getLastName());
        follow.setText(String.valueOf(response.getPersonList1().size()));
        following.setText(String.valueOf(response.getPersonList2().size()));
        blog.setText(String.valueOf(response.getAuthorName().size()));
        description.setText(response.getUserDetails().get(0).getDescription());
    }

    private void start_follow()
    {
        Call<StartFollowResponse> call=apiClient.getApiinterface().follow("10","9");
        call.enqueue(new Callback<StartFollowResponse>() {
            @Override
            public void onResponse(Call<StartFollowResponse> call, Response<StartFollowResponse> response) {

                if (response.code()==200)
                {
                    follow_button.setText("Already follow");
                    Toast.makeText(getApplicationContext(),"started Following",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<StartFollowResponse> call, Throwable t) {

            }
        });
    }
}