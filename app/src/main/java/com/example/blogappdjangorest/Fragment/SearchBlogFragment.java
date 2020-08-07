package com.example.blogappdjangorest.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogappdjangorest.Adapter.HomeScreenAdapter;
import com.example.blogappdjangorest.Adapter.SearchBlogAdapter;
import com.example.blogappdjangorest.R;
import com.google.android.material.button.MaterialButton;

public class SearchBlogFragment extends Fragment {

    RecyclerView recyclerView;
    MaterialButton button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_search_blog,container,false);

        recyclerView=v.findViewById(R.id.Search_blog_recycle);
        button=v.findViewById(R.id.Search_blog_button);
        SearchBlogAdapter searchBlogAdapter=new SearchBlogAdapter(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(searchBlogAdapter);



        button.setOnClickListener(new View.OnClickListener() {
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
