package com.example.blogappdjangorest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogappdjangorest.R;

public class FollowListAdapter extends RecyclerView.Adapter<FollowListAdapter.followholder>{

    Context context;

    public FollowListAdapter(Context context)
    {
        this.context=context;
    }

    @NonNull
    @Override
    public FollowListAdapter.followholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.blog_main_item, parent, false);
        return new followholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowListAdapter.followholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 7;
    }
    class followholder extends RecyclerView.ViewHolder {
        public followholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
