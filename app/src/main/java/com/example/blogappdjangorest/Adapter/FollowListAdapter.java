package com.example.blogappdjangorest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.blogappdjangorest.Models.RetrofitModels.follower.followerList;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogappdjangorest.Models.RetrofitModels.data.ProfileUser;
import com.example.blogappdjangorest.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FollowListAdapter extends RecyclerView.Adapter<FollowListAdapter.followholder>{

    Context context;
    ArrayList<followerList> response;


    public FollowListAdapter(Context context,ArrayList<followerList> response)
    {
        this.context=context;
        this.response=response;
    }

    @NonNull
    @Override
    public FollowListAdapter.followholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.search_profile_item, parent, false);
        return new followholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowListAdapter.followholder holder, int position) {

        holder.ussername_follow.setText("@" +response.get(0).getPersonList2Follower().get(position).getWho().getUsername());
        holder.name_follow.setText(response.get(0).getPersonList2Follower().get(position).getWho().getFirst_name()+" "+response.get(0).getPersonList2Follower().get(position).getWho().getLast_name());
        holder.bio_follow.setText(response.get(0).getPersonList2Follower().get(position).getWho().getUser_details()[0].getDescription());
        Picasso.get().load(response.get(0).getPersonList2Follower().get(position).getWho().getUser_details()[0].getUrl()).into(holder.follow_img);
    }

    @Override
    public int getItemCount() {
        return response.get(0).getPersonList2Follower().size();
    }

    class followholder extends RecyclerView.ViewHolder {

        TextView name_follow,bio_follow,ussername_follow;
        CircleImageView follow_img;
        public followholder(@NonNull View itemView) {
            super(itemView);
            name_follow=itemView.findViewById(R.id.name_follow);
            bio_follow=itemView.findViewById(R.id.bio_follow);
            ussername_follow=itemView.findViewById(R.id.ussername_follow);
            follow_img=itemView.findViewById(R.id.follow_img);

        }
    }
}
