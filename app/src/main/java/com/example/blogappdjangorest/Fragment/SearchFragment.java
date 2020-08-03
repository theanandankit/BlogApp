package com.example.blogappdjangorest.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.blogappdjangorest.Adapter.SearchTablayoutAdapter;
import com.example.blogappdjangorest.R;
import com.google.android.material.tabs.TabLayout;

public class SearchFragment extends Fragment {


    ViewPager viewPager;
    TabLayout tabLayout;
    SearchTablayoutAdapter searchTablayoutAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search,container,false);
        viewPager=view.findViewById(R.id.viewpager);
        tabLayout=view.findViewById(R.id.tabLayout);
        searchTablayoutAdapter = new SearchTablayoutAdapter(getFragmentManager());
        viewPager.setAdapter(searchTablayoutAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}
