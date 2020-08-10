package com.example.blogappdjangorest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.blogappdjangorest.Models.RetrofitModels.BlogInfoResponse;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.Retrofit.ApiClient;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Blog_view extends AppCompatActivity {

    CircleImageView user_image;
    ImageView blog_image;
    TextView name,date,category,title,body;
    ApiClient apiClient;
    ProgressBar progressBar;
    String blog_id;
    int process;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_view);

        init();
        progressBar.setVisibility(View.VISIBLE);
        blog_id=getIntent().getStringExtra("blog_id");
        process=getIntent().getIntExtra("process",1);
        Log.e("oki",blog_id);
        call();

    }
    private void init()
    {
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
            public void onResponse(Call<ArrayList<BlogInfoResponse>> call, Response<ArrayList<BlogInfoResponse>> response) {

                if (response.code()==200)
                {
                    if (!response.body().isEmpty())
                    {
                        progressBar.setVisibility(View.INVISIBLE);
                        name.setText(response.body().get(0).getAuthor().getFirst_name()+" "+response.body().get(0).getAuthor().getLast_name());
                        category.setText(response.body().get(0).getCategory());
                        title.setText(response.body().get(0).getTitle());
                        body.setText(response.body().get(0).getBody());
                        date.setText(response.body().get(0).getDate());
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