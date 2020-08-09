package com.example.blogappdjangorest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogappdjangorest.Models.RetrofitModels.ProfileSearchResponse;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.activities.ProfileView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchProfileAdapter extends RecyclerView.Adapter<SearchProfileAdapter.blogviewhloder> {

    private Context context;
    ArrayList<ProfileSearchResponse> responses;

    public SearchProfileAdapter(Context context,ArrayList<ProfileSearchResponse> responses)
    {
        this.context=context;
        this.responses=responses;
    }


    @NonNull
    @Override
    public SearchProfileAdapter.blogviewhloder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.search_profile_item, parent, false);
        return new blogviewhloder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchProfileAdapter.blogviewhloder holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ProfileView.class));
            }
        });

        holder.name.setText(responses.get(position).getFirst_name()+" "+responses.get(position).getLast_name());
        holder.username.setText(responses.get(position).getUsername());
        if (!(responses.get(position).getUser_details().length ==0))
          holder.description.setText(responses.get(position).getUser_details()[0].getDescription());
        else
            holder.description.setText("Check my new Blogs and learn something new");
    }

    @Override
    public int getItemCount() {
        return responses.size();
    }



    class blogviewhloder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView name,username,description;
        public blogviewhloder(@NonNull View itemView) {
            super(itemView);

            circleImageView=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            username=itemView.findViewById(R.id.username);
            description=itemView.findViewById(R.id.description);

        }
    }
}
