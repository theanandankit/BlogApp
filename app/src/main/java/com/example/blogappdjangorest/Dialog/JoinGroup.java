package com.example.blogappdjangorest.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.blogappdjangorest.Models.RetrofitModels.JoinGroupResponse;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.Retrofit.ApiClient;
import com.example.blogappdjangorest.resources.PreferencesHelper;
import com.example.blogappdjangorest.resources.WaitingDialog;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinGroup {

    Dialog dialog;
    Context context;
    ApiClient apiClient;
    WaitingDialog waitingDialog;
    PreferencesHelper preferencesHelper;

     public JoinGroup(Context context)
     {

         this.context=context;
         apiClient=new ApiClient();
         preferencesHelper=new PreferencesHelper(context);
     }

    public void show()
    {
        {

            waitingDialog=new WaitingDialog(context);
            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.joingroupdialog);
            dialog.setCancelable(true);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().setAttributes(lp);
            dialog.setCancelable(false);
            final EditText code=dialog.findViewById(R.id.code);

            MaterialButton join = dialog.findViewById(R.id.join_button);
            MaterialButton cancel = dialog.findViewById(R.id.cancel);

            join.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    join_group(code.getText().toString());
                }
            });

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            dialog.show();
        }
    }

    private void join_group(String code)
    {
        waitingDialog.SetDialog("Please Wait");
        waitingDialog.show();
        Call<JoinGroupResponse> call=apiClient.getApiinterface().join_group("Token "+preferencesHelper.gettoken(),code);
        call.enqueue(new Callback<JoinGroupResponse>() {
            @Override
            public void onResponse(Call<JoinGroupResponse> call, Response<JoinGroupResponse> response) {

                if (response.code()==200)
                {
                    if (response.body().getStatus().equals("Saved"))
                    {
                        Toast.makeText(context,"Registered Successfully",Toast.LENGTH_LONG).show();
                    }
                    else if (response.body().getStatus().equals("Already a member"))
                    {
                        Toast.makeText(context,"Already Registered",Toast.LENGTH_LONG).show();
                    }
                    waitingDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<JoinGroupResponse> call, Throwable t) {

            }
        });
    }
}
