package com.example.blogappdjangorest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blogappdjangorest.Models.RetrofitModels.data.ProfileUser;
import com.example.blogappdjangorest.Models.RetrofitModels.editblog.EditBlogList;
import com.example.blogappdjangorest.Models.RetrofitModels.editblog.Editblogput;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.Retrofit.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfile extends AppCompatActivity {

    TextView Chngephotobtn,firstname_blog,lastname_blog,email_blog,username_blog,descr_blog;
    ApiClient apiClient;
    Button button_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        username_blog = findViewById(R.id.username_blog);
        firstname_blog = findViewById(R.id.firstname_blog);
        lastname_blog = findViewById(R.id.lastnameblog);
        email_blog = findViewById(R.id.email_blog);
        descr_blog = findViewById(R.id.descr_blog);
        button_save = findViewById(R.id.button_save);

        username_blog.setEnabled(false);
        firstname_blog.setEnabled(false);
        lastname_blog.setEnabled(false);
        email_blog.setEnabled(false);


        apiClient=new ApiClient();
        Call<ArrayList<EditBlogList>> call=apiClient.getApiinterface().EditBlogthing(10);

        call.enqueue(new Callback<ArrayList<EditBlogList>>() {
            @Override
            public void onResponse(Call<ArrayList<EditBlogList>> call, Response<ArrayList<EditBlogList>> response) {
                if(response.code()==200){

                    username_blog.setText(response.body().get(0).getUsername().toString());
                    firstname_blog.setText(response.body().get(0).getFirstName().toString());
                    lastname_blog.setText(response.body().get(0).getLastName().toString());
                    email_blog.setText(response.body().get(0).getEmail().toString());
                    descr_blog.setText(response.body().get(0).getUserDetails().get(0).getDescription().toString());

                    Log.d("mannik",response.body().get(0).toString());

                }

                Log.d("mannik",response.toString());



            }

            @Override
            public void onFailure(Call<ArrayList<EditBlogList>> call, Throwable t) {

            }
        });

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("men",descr_blog.getText().toString());


                Call<Editblogput> callput=apiClient.getApiinterface().EditBlogPut("Token b0eec2b06da57b0cc5b84e16dd0d484f1044e802",10,descr_blog.getText().toString(),"evef");

                if(descr_blog.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Description cannot be empty",Toast.LENGTH_LONG).show();

                }
                else {
                    callput.enqueue(new Callback<Editblogput>() {
                        @Override
                        public void onResponse(Call<Editblogput> call, Response<Editblogput> response) {
                            if (response.code() == 201) {
                                Log.d("men", response.body().getDescription().toString());
                            }
                            Log.d("men", response.toString());

                            finish();

                        }

                        @Override
                        public void onFailure(Call<Editblogput> call, Throwable t) {

                        }
                    });
                }




            }
        });







        Chngephotobtn = (TextView) findViewById(R.id.Chngephotobtn);
        Chngephotobtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),1);

            }
        });
    }
}