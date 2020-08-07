package com.example.blogappdjangorest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogappdjangorest.R;

public class ProfileBlogAdapter  extends RecyclerView.Adapter<ProfileBlogAdapter.ProfileHolder> {

    Context context;

    public ProfileBlogAdapter(Context context)
    {
        this.context=context;
    }
    @NonNull
    @Override
    public ProfileBlogAdapter.ProfileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.blog_main_item, parent, false);
        return new ProfileHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileBlogAdapter.ProfileHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    class ProfileHolder extends RecyclerView.ViewHolder {
        public ProfileHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
