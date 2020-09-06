package com.example.blogappdjangorest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogappdjangorest.Models.RetrofitModels.PublicBlogResponse;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.activities.Blog_view;
import com.example.blogappdjangorest.activities.ProfileView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeScreenAdapter extends RecyclerView.Adapter<HomeScreenAdapter.homescreenholder> {
    private Context context;
    ArrayList<PublicBlogResponse> response;

    public HomeScreenAdapter(Context context, ArrayList<PublicBlogResponse> response){
        this.context=context;
        this.response=response;
    }
    @NonNull
    @Override
    public HomeScreenAdapter.homescreenholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.blog_main_item, parent, false);
        return new homescreenholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeScreenAdapter.homescreenholder holder, final int position) {


        holder.name.setText(response.get(position).getAuthor().getFirst_name()+" "+response.get(position).getAuthor().getLast_name());
        holder.date.setText(response.get(position).getDate());
        holder.category.setText(response.get(position).getCategory());
        holder.body.setText(response.get(position).getTitle());
        Picasso.get().load(response.get(position).getAuthor().getUser_details()[0].getUrl()).into(holder.photo);
        Picasso.get().load(response.get(position).getUrl()).into(holder.blog_photo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(new Intent(context, Blog_view.class));
                intent.putExtra("process",1);
                intent.putExtra("blog_id",response.get(position).getId());
                intent.putExtra("url",response.get(position).getAuthor().getUser_details()[0].getUrl());
                context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return response.size();
    }

    class homescreenholder extends RecyclerView.ViewHolder {

        CircleImageView photo;
        CircleImageView blog_photo;
        TextView name,date,category,body;
        public homescreenholder(@NonNull View itemView) {
            super(itemView);

            photo=itemView.findViewById(R.id.image);
            blog_photo=itemView.findViewById(R.id.body_image);
            name=itemView.findViewById(R.id.name);
            date=itemView.findViewById(R.id.date);
            category=itemView.findViewById(R.id.category);
            body=itemView.findViewById(R.id.text);

        }
    }
}
