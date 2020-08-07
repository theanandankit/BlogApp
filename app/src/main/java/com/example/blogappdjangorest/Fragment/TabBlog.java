package com.example.blogappdjangorest.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogappdjangorest.Adapter.HomeScreenAdapter;
import com.example.blogappdjangorest.Adapter.ProfileBlogAdapter;
import com.example.blogappdjangorest.R;

public class TabBlog extends Fragment {

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.tab_blog,container,false);

        recyclerView=view.findViewById(R.id.recycler_blog);
        ProfileBlogAdapter profileBlogAdapter=new ProfileBlogAdapter(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(profileBlogAdapter);
        return view;


    }
}
