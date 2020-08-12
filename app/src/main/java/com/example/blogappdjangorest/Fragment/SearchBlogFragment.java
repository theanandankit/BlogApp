package com.example.blogappdjangorest.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogappdjangorest.Models.RetrofitModels.CategoryResponse;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.Retrofit.ApiClient;
import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchBlogFragment extends Fragment {

    public static RecyclerView blog_recyclerView;
    public static MaterialButton blog_button;
    public static ProgressBar blog_progress;
    public static ImageView blog_caution_image;
    public static TextView blog_caution_text;
    ApiClient apiClient;
    String[] value=new String[22];
    AlertDialog.Builder builder;
    AlertDialog dialog;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search_blog, container, false);

        blog_recyclerView = v.findViewById(R.id.Search_blog_recycle);
        blog_button = v.findViewById(R.id.Search_blog_button);
        blog_progress = v.findViewById(R.id.progress);
        blog_progress.setVisibility(View.INVISIBLE);
        blog_caution_image = v.findViewById(R.id.caution);
        blog_caution_text = v.findViewById(R.id.error);
        blog_caution_image.setVisibility(View.INVISIBLE);
        blog_caution_text.setText("Search and learn something new");
        blog_button.setEnabled(false);
        apiClient = new ApiClient();
        get_cat();


        blog_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFilterDialog();
            }
        });


        return v;
    }

    public void showFilterDialog() {

        dialog.show();
    }
    public void get_cat() {
        Call<ArrayList<CategoryResponse>> call = apiClient.getApiinterface().get_categories();

        call.enqueue(new Callback<ArrayList<CategoryResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<CategoryResponse>> call, Response<ArrayList<CategoryResponse>> response) {

                if (response.code() == 200) {
                    if (!(response.body().size() == 0)) {
                        blog_button.setEnabled(true);
                        for (int a = 0; a < response.body().size(); a++) {
                            value[a]=(response.body().get(a).getCategory_name());
                        }
                    } else {
                        blog_button.setText("Something Went Wrong");
                        blog_button.setEnabled(false);
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
        blog_button.setText("Technology");
        builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Search By:");
        builder.setSingleChoiceItems(value, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                blog_button.setText(value[which]);
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
}
