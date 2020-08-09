package com.example.blogappdjangorest.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogappdjangorest.R;
import com.google.android.material.button.MaterialButton;

public class SearchBlogFragment extends Fragment {

    public static RecyclerView blog_recyclerView;
    public static MaterialButton blog_button;
    public static ProgressBar blog_progress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_search_blog,container,false);

        blog_recyclerView=v.findViewById(R.id.Search_blog_recycle);
        blog_button=v.findViewById(R.id.Search_blog_button);
        blog_progress=v.findViewById(R.id.progress);
        blog_progress.setVisibility(View.INVISIBLE);
//        SearchBlogAdapter searchBlogAdapter=new SearchBlogAdapter(getContext());
//        blog_recyclerView.setHasFixedSize(true);
//        blog_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        blog_recyclerView.setAdapter(searchBlogAdapter);



        blog_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFilterDialog();
            }
        });


        return v;
    }
    public void showFilterDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Search By:");
        String[] animals = {"Parameter 1", "Parameter 2", "Parameter 3", "Parameter 4", "Parameter 5"};
        builder.setSingleChoiceItems(animals, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
