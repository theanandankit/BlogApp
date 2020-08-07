package com.example.blogappdjangorest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogappdjangorest.R;

public class ProfileViewBlogAdapter extends RecyclerView.Adapter<ProfileViewBlogAdapter.profileholder> {

    Context context;

    public ProfileViewBlogAdapter(Context context)
    {
        this.context=context;
    }


    @NonNull
    @Override
    public ProfileViewBlogAdapter.profileholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.blog_main_item, parent, false);
        return new profileholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewBlogAdapter.profileholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    class profileholder extends RecyclerView.ViewHolder {
        public profileholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
