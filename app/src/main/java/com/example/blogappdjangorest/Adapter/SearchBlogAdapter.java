package com.example.blogappdjangorest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogappdjangorest.R;

public class SearchBlogAdapter extends RecyclerView.Adapter<SearchBlogAdapter.Searchholder> {

    Context context;

    public SearchBlogAdapter(Context context)
    {
        this.context=context;
    }
    @NonNull
    @Override
    public SearchBlogAdapter.Searchholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.blog_main_item, parent, false);
        return new Searchholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchBlogAdapter.Searchholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    class Searchholder extends RecyclerView.ViewHolder {
        public Searchholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
