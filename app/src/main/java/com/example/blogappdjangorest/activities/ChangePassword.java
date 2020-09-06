package com.example.blogappdjangorest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.blogappdjangorest.Models.RetrofitModels.ChangePasswordResponse;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.Retrofit.ApiClient;
import com.example.blogappdjangorest.resources.PreferencesHelper;
import com.example.blogappdjangorest.resources.WaitingDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppCompatActivity {

    TextInputLayout old,newp,renewp;
    MaterialButton button;
    ApiClient apiClient;
    WaitingDialog waitingDialog;
    PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        old=findViewById(R.id.current_password);
        newp=findViewById(R.id.new_password);
        renewp=findViewById(R.id.re_new_password);
        button=findViewById(R.id.change);
        apiClient=new ApiClient();
        waitingDialog=new WaitingDialog(ChangePassword.this);
        waitingDialog.SetDialog("Updating Password");
        preferencesHelper=new PreferencesHelper(getApplicationContext());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change();
            }
        });

    }

    private void change()
    {
        waitingDialog.show();
        Call<ChangePasswordResponse> call=apiClient.getApiinterface().change_password("Token "+preferencesHelper.gettoken(),old.getEditText().getText().toString(),newp.getEditText().getText().toString());
        call.enqueue(new Callback<ChangePasswordResponse>() {
            @Override
            public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {

                if (response.code()==200)
                {
                    if (response.body().getResponse().equals("Password change"))
                    {
                        waitingDialog.dismiss();
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