package com.example.blogappdjangorest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogappdjangorest.Models.RetrofitModels.PublicBlogResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.data.ProfileUser;
import com.example.blogappdjangorest.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileBlogAdapter  extends RecyclerView.Adapter<ProfileBlogAdapter.ProfileHolder> {

    Context context;
    ArrayList<ProfileUser> response;


    public ProfileBlogAdapter(Context context,  ArrayList<ProfileUser> response)
    {
        this.context=context;
        this.response=response;
    }
    @NonNull
    @Override
    public ProfileBlogAdapter.ProfileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.blog_profile, parent, false);
        return new ProfileHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileBlogAdapter.ProfileHolder holder, int position) {

        holder.name.setText(response.get(0).getAuthorName().get(position).getTitle().toString());
        holder.body.setText(response.get(0).getAuthorName().get(position).getBody().toString());
        holder.category.setText(response.get(0).getAuthorName().get(position).getCategory().toString());

    }

    @Override
    public int getItemCount() {

        return response.get(0).getAuthorName().size();
    }

    class ProfileHolder extends RecyclerView.ViewHolder {

        CircleImageView photo;
        ImageView blog_photo;
        TextView name,date,category,body;

        public ProfileHolder(@NonNull View itemView) {
            super(itemView);

            photo=itemView.findViewById(R.id.image_blog);
            blog_photo=itemView.findViewById(R.id.blog_image);
            name=itemView.findViewById(R.id.name_blog);
            date=itemView.findViewById(R.id.date_blog);
            category=itemView.findViewById(R.id.category_blog);
            body=itemView.findViewById(R.id.text_blog);

        }
    }
}
