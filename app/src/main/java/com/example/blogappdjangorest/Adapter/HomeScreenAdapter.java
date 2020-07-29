package com.example.blogappdjangorest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogappdjangorest.R;

public class HomeScreenAdapter extends RecyclerView.Adapter<HomeScreenAdapter.homescreenholder> {
    private Context context;

    public HomeScreenAdapter(Context context){
        this.context=context;
    }
    @NonNull
    @Override
    public HomeScreenAdapter.homescreenholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.blog_main_item, parent, false);
        return new homescreenholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeScreenAdapter.homescreenholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    class homescreenholder extends RecyclerView.ViewHolder {
        public homescreenholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
