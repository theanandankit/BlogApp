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
import com.example.blogappdjangorest.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchBlogAdapter extends RecyclerView.Adapter<SearchBlogAdapter.Searchholder> {

    Context context;
    ArrayList<PublicBlogResponse> response;

    public SearchBlogAdapter(Context context, ArrayList<PublicBlogResponse> response) {
        this.context = context;
        this.response =response;
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

        holder.name.setText(response.get(position).getAuthor().getFirst_name()+" "+response.get(position).getAuthor().getLast_name());
        holder.date.setText(response.get(position).getDate());
        holder.category.setText(response.get(position).getCategory());
        holder.body.setText(response.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return response.size();
    }

    class Searchholder extends RecyclerView.ViewHolder {
        CircleImageView photo;
        ImageView blog_photo;
        TextView name, date, category, body;

        public Searchholder(@NonNull View itemView) {
            super(itemView);

            photo = itemView.findViewById(R.id.image);
            blog_photo = itemView.findViewById(R.id.body_image);
            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            category = itemView.findViewById(R.id.category);
            body = itemView.findViewById(R.id.text);
        }
    }
}
