package com.example.blogappdjangorest.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.blogappdjangorest.Fragment.SearchBlogFragment;
import com.example.blogappdjangorest.Fragment.SearchFragment;
import com.example.blogappdjangorest.Fragment.SearchProfileFragment;

public class SearchTablayoutAdapter extends FragmentStatePagerAdapter {

    Fragment fragment;
    Context context;

    public SearchTablayoutAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                fragment = new SearchBlogFragment();
                return fragment;
            }
            case 1: {
                fragment = new SearchProfileFragment();
                return fragment;
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Blog";
            case 1:
                return "Profile";
            default:
                return null;
        }
    }
    public Fragment get_Fragment()
    {
        return fragment;
    }
}
