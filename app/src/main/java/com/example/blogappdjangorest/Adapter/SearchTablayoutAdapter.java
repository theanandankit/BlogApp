package com.example.blogappdjangorest.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.blogappdjangorest.Fragment.SearchBlogFragment;
import com.example.blogappdjangorest.Fragment.SearchProfileFragment;

public class SearchTablayoutAdapter extends FragmentStatePagerAdapter {

    public SearchTablayoutAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new SearchBlogFragment();
            case 1:
                return new SearchProfileFragment();
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
}
