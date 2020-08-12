package com.example.blogappdjangorest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogappdjangorest.Models.RetrofitModels.GroupBlogResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.GroupListResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.Group_related;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.activities.Blog_view;
import com.squareup.picasso.Picasso;

import java.security.acl.Group;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class GroupsPostAdapter extends RecyclerView.Adapter<GroupsPostAdapter.GroupPostItemHolder> {
    private Context mContext;
    Group_related[] responses;

    public GroupsPostAdapter(Context context, Group_related[] responses){
        mContext = context;
        this.responses=responses;
    }

    @NonNull
    @Override
    public GroupPostItemHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.blog_main_item, parent, false);
        return new GroupPostItemHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull GroupPostItemHolder holder, final int position){

        holder.name.setText(responses[position].getAuthor().getFirst_name());
        holder.date.setText(responses[position].getDate());
        holder.category.setText(responses[position].getCategory());
        holder.body.setText(responses[position].getTitle());
        Picasso.get().load(responses[position].getUrl()).into(holder.blog_photo);
        Picasso.get().load(responses[position].getAuthor().getUser_details()[0].getUrl()).into(holder.photo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, Blog_view.class);
                intent.putExtra("blog_id",responses[position].getId().toString());
                intent.putExtra("url",responses[position].getAuthor().getUser_details()[0].getUrl());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount () {
        return responses.length;

    }

    @Override
    public long getItemId ( int position){
        return position;
    }

    @Override
    public int getItemViewType ( int position){
        return position;
    }

    class GroupPostItemHolder extends RecyclerView.ViewHolder {

        CircleImageView photo;
        CircleImageView blog_photo;
        TextView name,date,category,body;

        private GroupPostItemHolder(@NonNull View itemView) {
            super(itemView);
            setGlobals(itemView);
            //setOnClickListeners();

            photo=itemView.findViewById(R.id.image);
            blog_photo=itemView.findViewById(R.id.body_image);
            name=itemView.findViewById(R.id.name);
            date=itemView.findViewById(R.id.date);
            category=itemView.findViewById(R.id.category);
            body=itemView.findViewById(R.id.text);
        }

        private void setGlobals(View itemView) {

        }

    }
}
