package com.example.blogappdjangorest.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blogappdjangorest.Models.RetrofitModels.editblog.EditBlogList;
import com.example.blogappdjangorest.Models.RetrofitModels.editblog.Editblogput;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.Retrofit.ApiClient;
import com.example.blogappdjangorest.resources.PreferencesHelper;
import com.example.blogappdjangorest.resources.WaitingDialog;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfile extends AppCompatActivity {

    TextView Chngephotobtn,firstname_blog,lastname_blog,email_blog,username_blog,descr_blog;
    ApiClient apiClient;
    Button button_save;
    PreferencesHelper preferencesHelper;
    CircleImageView circleImageView;
    Uri uri;
    StorageReference folder;
    WaitingDialog waitingDialog;
    StorageReference image_store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        preferencesHelper=new PreferencesHelper(getApplicationContext());
        waitingDialog=new WaitingDialog(EditProfile.this);

        username_blog = findViewById(R.id.username_blog_first);
        firstname_blog = findViewById(R.id.firstname_blog_first);
        lastname_blog = findViewById(R.id.lastnameblog_first);
        email_blog = findViewById(R.id.email_blog_first);
        descr_blog = findViewById(R.id.descr_blog_first);
        button_save = findViewById(R.id.button_save_first);
        circleImageView =findViewById(R.id.pro_image);

        username_blog.setEnabled(false);
        firstname_blog.setEnabled(false);
        lastname_blog.setEnabled(false);
        email_blog.setEnabled(false);



        apiClient=new ApiClient();
        Call<ArrayList<EditBlogList>> call=apiClient.getApiinterface().EditBlogthing(Integer.parseInt(preferencesHelper.getid()));

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

                if (!String.valueOf(uri).isEmpty()&&(!descr_blog.getText().toString().isEmpty()))
                {
                    upload();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please select any image",Toast.LENGTH_LONG).show();
                }

                 }
        });

        Chngephotobtn = (TextView) findViewById(R.id.Chngephotobtn_first);
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
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            uri=data.getData();
            circleImageView.setImageURI(uri);
            Chngephotobtn.setText("Edit Image");
        }
    }
    private void upload()
    {
        folder= FirebaseStorage.getInstance().getReference().child("Profile_pic");
        try {
            image_store = folder.child("image" + uri.getLastPathSegment());
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Please select any photo",Toast.LENGTH_LONG).show();
            return;
        }
        UploadTask uploadTask=image_store.putFile(uri);
        waitingDialog.SetDialog("Uploading Your\nfile...");
        waitingDialog.show();

        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                double progress=(100 * taskSnapshot.getBytesTransferred()) /taskSnapshot.getTotalByteCount();
                waitingDialog.setext((int) progress +" % completed");
            }
        });

        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                image_store.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        add_details(uri);
                    }
                });
            }
        });
    }
    private void add_details(Uri uri)
    {
        Call<Editblogput> callput=apiClient.getApiinterface().addBlogPut("Token "+preferencesHelper.gettoken(),Integer.parseInt(preferencesHelper.getid()),descr_blog.getText().toString(), String.valueOf(uri));

        if(descr_blog.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Description cannot be empty",Toast.LENGTH_LONG).show();

        }
        else {
            callput.enqueue(new Callback<Editblogput>() {
                @Override
                public void onResponse(Call<Editblogput> call, Response<Editblogput> response) {
                    if (response.code() == 201) {

                        Toast.makeText(getApplicationContext(),"Successfully updated",Toast.LENGTH_LONG).show();

                    }

                    finish();
                }
                @Override
                public void onFailure(Call<Editblogput> call, Throwable t) {

                }
            });
        }
    }
}