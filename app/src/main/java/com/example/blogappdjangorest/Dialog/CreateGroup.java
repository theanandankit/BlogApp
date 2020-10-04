package com.example.blogappdjangorest.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.Retrofit.ApiClient;
import com.example.blogappdjangorest.resources.PreferencesHelper;
import com.example.blogappdjangorest.resources.WaitingDialog;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateGroup {

    private Context context;
    private Dialog dialog;
    ApiClient apiClient;
    WaitingDialog waitingDialog;
    String code;
    PreferencesHelper preferencesHelper;
    CircleImageView circleImageView;
    StorageReference folder;
    StorageReference image_store;
    Uri uri;
    static String image_url="https://firebasestorage.googleapis.com/v0/b/blogic-c360f.appspot.com/o/static%20image%2Fteam.png?alt=media&token=4dd7d40d-69d4-45f9-92f5-c49f17924eec";
    EditText name;
    TextView passcode;
    Activity activity;

    public CreateGroup(Context context,Activity activity)
    {
        this.activity=activity;
        this.context=context;
        apiClient=new ApiClient();
        waitingDialog=new WaitingDialog(context);
        preferencesHelper = new PreferencesHelper(context);
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
        name=dialog.findViewById(R.id.group_name);

        final LinearLayout create_layout,share_layout;
        MaterialButton create_button,done_button;

        create_button=dialog.findViewById(R.id.create_button);
        done_button=dialog.findViewById(R.id.done_button);
        create_layout=dialog.findViewById(R.id.create_layout);
        share_layout=dialog.findViewById(R.id.share_layout);
        passcode=dialog.findViewById(R.id.passcode);
        MaterialButton cancel=dialog.findViewById(R.id.cancel);
        circleImageView=dialog.findViewById(R.id.image);

//        circleImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                activity.startActivityForResult(intent, 1);
//            }
//        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create_layout.setVisibility(View.GONE);
                share_layout.setVisibility(View.VISIBLE);
                info(name.getText().toString(),passcode,image_url);
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

    private void info(String name, final TextView passcode,String uurl)
    {
        waitingDialog.SetDialog("Creating Group");
        waitingDialog.show();
        Call<String> call=apiClient.getApiinterface().create_group(get_code(),name,preferencesHelper.getid(),get_code(),uurl);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                waitingDialog.dismiss();
                if (response.code() == 200) {

                    if (response.body().toString().equals(code)) {
                        Toast.makeText(context, "Successfully created", Toast.LENGTH_LONG).show();
                        waitingDialog.dismiss();
                        passcode.setText(code);
                        dialog.show();
                    }
                    else
                    {
                        Toast.makeText(context,"Something went wrong",Toast.LENGTH_LONG).show();
                    }
                }
                Toast.makeText(context,"Group not created",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                waitingDialog.dismiss();
                Toast.makeText(context,"Something went wrong",Toast.LENGTH_LONG).show();
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

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            uri = data.getData();
//            circleImageView.setImageURI(uri);
//        }
//    }
    private void upload_image()
    {
        waitingDialog.SetDialog("Uploading Your\nInfo");
        waitingDialog.show();
        folder = FirebaseStorage.getInstance().getReference().child("Profile_pic");
        try {
            image_store = folder.child("image" + uri.getLastPathSegment());
        } catch (Exception e) {
            info(name.getText().toString(),passcode,image_url);
            return;
        }
        UploadTask uploadTask = image_store.putFile(uri);

        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                waitingDialog.setext((int) progress + " % completed");
            }
        });

        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                image_store.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        info(name.getText().toString(),passcode,String.valueOf(uri));
                    }
                });
            }
        });
    }
}
