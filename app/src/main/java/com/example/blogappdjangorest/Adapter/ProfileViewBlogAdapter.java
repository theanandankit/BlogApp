package com.example.blogappdjangorest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogappdjangorest.Models.RetrofitModels.data.AuthorName;
import com.example.blogappdjangorest.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileViewBlogAdapter extends RecyclerView.Adapter<ProfileViewBlogAdapter.profileholder> {

    Context context;
    List<AuthorName> response;

    public ProfileViewBlogAdapter(Context context,List<AuthorName> response)
    {
        this.context=context;
        this.response=response;
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

        holder.name.setText(String.valueOf(response.get(position).getAuthor()));
        holder.category.setText(String.valueOf(response.get(position).getCategory()));
        holder.body.setText(String.valueOf(response.get(position).getBody()));
    }

    @Override
    public int getItemCount() {
        return response.size();
    }

    class profileholder extends RecyclerView.ViewHolder {

        CircleImageView photo;
        ImageView blog_photo;
        TextView name,date,category,body;

        public profileholder(@NonNull View itemView) {
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
