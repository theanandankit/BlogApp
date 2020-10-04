package com.example.blogappdjangorest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.blogappdjangorest.Models.RetrofitModels.BlogInfoResponse;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.Retrofit.ApiClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Blog_view extends AppCompatActivity {

    CircleImageView user_image;
    CircleImageView blog_image;
    TextView name,date,category,title,body;
    ApiClient apiClient;
    ProgressBar progressBar;
    String blog_id,url;
    int process;
    LinearLayout profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_view);

        init();
        progressBar.setVisibility(View.VISIBLE);
        blog_id=getIntent().getStringExtra("blog_id");
        process=getIntent().getIntExtra("process",1);
        url=getIntent().getStringExtra("url");
        Picasso.get().load(url).into(user_image);
        call();

    }
    private void init()
    {
        profile=findViewById(R.id.profile_section);
        user_image=findViewById(R.id.image);
        blog_image=findViewById(R.id.blog_image);
        name=findViewById(R.id.name);
        date=findViewById(R.id.date);
        category=findViewById(R.id.category);
        title=findViewById(R.id.title);
        body=findViewById(R.id.body);
        progressBar=findViewById(R.id.progress);
        apiClient=new ApiClient();

    }
    private void call()
    {
        Call<ArrayList<BlogInfoResponse>> call=apiClient.getApiinterface().bloginfo(blog_id);

        call.enqueue(new Callback<ArrayList<BlogInfoResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<BlogInfoResponse>> call, final Response<ArrayList<BlogInfoResponse>> response) {

                if (response.code()==200)
                {
                    if (!response.body().isEmpty())
                    {
                        progressBar.setVisibility(View.INVISIBLE);
                        Picasso.get().load(response.body().get(0).getUrl()).into(blog_image);
                        name.setText(response.body().get(0).getAuthor().getFirst_name()+" "+response.body().get(0).getAuthor().getLast_name());
                        category.setText(response.body().get(0).getCategory());
                        title.setText(response.body().get(0).getTitle());
                        body.setText(response.body().get(0).getBody());
                        date.setText(response.body().get(0).getDate());
                        Log.e("ok",String.valueOf(response.body().get(0).getAuthor().getId()));
                        profile.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(getApplicationContext(),ProfileView.class);
                                intent.putExtra("user_id",response.body().get(0).getAuthor().getUser_details()[0].getUser_id());
                                startActivity(intent);
                            }
                        });
                    }
                }
                else
                {
                    body.setText("Something Went Wrong\n Please check your internet connection");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<BlogInfoResponse>> call, Throwable t) {

            }
        });
    }
}