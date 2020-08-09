package com.example.blogappdjangorest.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.Retrofit.ApiClient;
import com.example.blogappdjangorest.resources.WaitingDialog;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateGroup {

    private Context context;
    private Dialog dialog;
    ApiClient apiClient;
    WaitingDialog waitingDialog;
    String code;

    public CreateGroup(Context context)
    {
        this.context=context;
        apiClient=new ApiClient();
        waitingDialog=new WaitingDialog(context);
    }

    public void show() {

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.creategroupdialog);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setAttributes(lp);
        dialog.setCancelable(false);
        final EditText name=dialog.findViewById(R.id.group_name);

        final LinearLayout create_layout,share_layout;
        MaterialButton create_button,done_button;

        create_button=dialog.findViewById(R.id.create_button);
        done_button=dialog.findViewById(R.id.done_button);
        create_layout=dialog.findViewById(R.id.create_layout);
        share_layout=dialog.findViewById(R.id.share_layout);
        final TextView passcode=dialog.findViewById(R.id.passcode);

        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create_layout.setVisibility(View.GONE);
                share_layout.setVisibility(View.VISIBLE);
                info(name.getText().toString(),passcode);
                dialog.dismiss();
            }
        });

        done_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void info(String name, final TextView passcode)
    {
        waitingDialog.SetDialog("Creating Group");
        waitingDialog.show();
        Call<String> call=apiClient.getApiinterface().create_group(get_code(),name,"10",get_code());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.code() == 200) {

                    if (response.body().toString().equals(code)) {
                        Toast.makeText(context, "Successfully created", Toast.LENGTH_LONG).show();
                        waitingDialog.dismiss();
                        passcode.setText(code);
                        dialog.show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private String get_code()
    {
        long unix=System.currentTimeMillis()/1000L;
        long value=unix%100000000;
        value=(value*17)+2916;
        value=value%1000000;
        code= String.valueOf(value);
        return String.valueOf(value);

    }
}
