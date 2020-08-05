package com.example.blogappdjangorest.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.blogappdjangorest.Fragment.SearchBlogFragment;
import com.example.blogappdjangorest.Fragment.SearchProfileFragment;
import com.example.blogappdjangorest.Fragment.TabBlog;
import com.example.blogappdjangorest.Fragment.TabSetting;

public class ProfileTabLayoutAdapter extends FragmentStatePagerAdapter {
    public ProfileTabLayoutAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TabBlog();
            case 1:
                return new TabSetting();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
