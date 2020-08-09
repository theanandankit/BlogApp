package com.example.blogappdjangorest.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.blogappdjangorest.Adapter.GroupsAdapter;
import com.example.blogappdjangorest.Models.RetrofitModels.AddBlogResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.CategoryResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.GroupListResponse;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.Retrofit.ApiClient;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBlogFragment extends Fragment {

    MaterialButton button,category;
    String[] value=new String[22];
    ApiClient apiClient;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    TextView title,body;
    String category_text,status,group_text;
    String[] group,group_id;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_addblog,container,false);

        button=view.findViewById(R.id.next_button);
        category=view.findViewById(R.id.cat_button);
        title=view.findViewById(R.id.title);
        body=view.findViewById(R.id.body);
        apiClient=new ApiClient();
        get_cat();
        get_group();

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showpublishDialog();
            }
        });

        return view;
    }

    public void showpublishDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Publish");
        String[] animals = {"public", "Private (In Group)"};
        status="public";
        builder.setSingleChoiceItems(animals, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which==0)
                {
                    status="public";
                }
                else
                {
                    status="private";
                }

            }
        });


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (status.equals("public")) {
                    add_blog();
                    dialog.dismiss();
                } else {
                    showGroupDialog();
                    if (group.length==0)
                    {
                        Toast.makeText(getContext(),"No group to show, please Create the group or publish it public",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        add_blog();
                        dialog.dismiss();
                    }
                }
            }
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showGroupDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Select the group");
        group_text=group_id[0];
        builder.setSingleChoiceItems(group, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                group_text=group_id[which];
            }
        });


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void get_cat() {
        Call<ArrayList<CategoryResponse>> call = apiClient.getApiinterface().get_categories();

        call.enqueue(new Callback<ArrayList<CategoryResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<CategoryResponse>> call, Response<ArrayList<CategoryResponse>> response) {

                if (response.code() == 200) {
                    if (!(response.body().size() == 0)) {
                        category.setEnabled(true);
                        for (int a = 0; a < response.body().size(); a++) {
                            value[a]=(response.body().get(a).getCategory_name());
                        }
                    } else {
                        category.setText("Something Went Wrong");
                        category.setEnabled(false);
                    }
                    build_dialog();
                }

            }

            @Override
            public void onFailure(Call<ArrayList<CategoryResponse>> call, Throwable t) {

            }
        });
    }
    private void build_dialog()
    {
        category.setText("Technology");
        builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Search By:");
        Log.e("ok","po");
        builder.setSingleChoiceItems(value, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                category.setText(value[which]);
                category_text=value[which];
            }
        });


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setNegativeButton("Cancel", null);
        dialog = builder.create();
    }

    public void showDialog() {

        dialog.show();
    }
    private void add_blog()
    {
        Call<AddBlogResponse> call=apiClient.getApiinterface().add_blog("hgytf",title.getText().toString(),body.getText().toString(),category_text,"10",status,group_text);

        call.enqueue(new Callback<AddBlogResponse>() {
            @Override
            public void onResponse(Call<AddBlogResponse> call, Response<AddBlogResponse> response) {

                if (response.code()==200)
                {
                    if (!response.body().toString().isEmpty())
                    {
                        Toast.makeText(getContext(),"Successfully Added",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<AddBlogResponse> call, Throwable t) {

            }
        });
    }

    private void check()
    {

    }

    private void get_group()
    {
        Call<ArrayList<GroupListResponse>> call=apiClient.getApiinterface().get_group("10");
        call.enqueue(new Callback<ArrayList<GroupListResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<GroupListResponse>> call, Response<ArrayList<GroupListResponse>> response) {

                if (response.code()==200)
                {
                    if (!(response.body().size() ==0))
                    {
                        group=new String[response.body().size()];
                        group_id=new String[response.body().size()];
                        for (int a=0;a<response.body().size();a++)
                        {
                            group[a]=response.body().get(a).getGroup_description();
                            group_id[a]=response.body().get(a).getGroup_id();
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<ArrayList<GroupListResponse>> call, Throwable t) {

            }
        });
    }
}
