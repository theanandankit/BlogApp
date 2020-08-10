package com.example.blogappdjangorest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.blogappdjangorest.Models.RetrofitModels.ChangePasswordResponse;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.Retrofit.ApiClient;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppCompatActivity {

    TextInputLayout old,newp,renewp;
    MaterialButton button;
    ApiClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        old=findViewById(R.id.current_password);
        newp=findViewById(R.id.new_password);
        renewp=findViewById(R.id.re_new_password);
        apiClient=new ApiClient();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change();
            }
        });

    }

    private void change()
    {
        Call<ChangePasswordResponse> call=apiClient.getApiinterface().change_password("Token b0eec2b06da57b0cc5b84e16dd0d484f1044e802",old.getEditText().getText().toString(),newp.getEditText().getText().toString());
        call.enqueue(new Callback<ChangePasswordResponse>() {
            @Override
            public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {

                if (response.code()==200)
                {
                    if (response.body().getResponse().equals("Password change"))
                    {
                        Toast.makeText(getApplicationContext(),"Successfully password Updated",Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {

            }
        });
    }
}