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
import com.example.blogappdjangorest.Models.RetrofitModels.following.FollowingList;
import com.example.blogappdjangorest.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FollowingListAdapter extends RecyclerView.Adapter<FollowingListAdapter.followingholder>{

    Context context;
    ArrayList<FollowingList> response;


    public FollowingListAdapter(Context context,ArrayList<FollowingList> response)
    {
        this.context=context;
        this.response=response;
    }

    @NonNull
    @Override
    public FollowingListAdapter.followingholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.search_profile_item, parent, false);
        return new followingholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowingListAdapter.followingholder holder, int position) {

        holder.ussername_follow.setText("@" +response.get(0).getPersonList1Follow().get(position).getWhom().getUsername());
        holder.name_follow.setText(response.get(0).getPersonList1Follow().get(position).getWhom().getFirst_name()+" "+response.get(0).getPersonList1Follow().get(position).getWhom().getLast_name());
        holder.bio_follow.setText(response.get(0).getPersonList1Follow().get(position).getWhom().getUser_details()[0].getDescription());
        Picasso.get().load(response.get(0).getPersonList1Follow().get(position).getWhom().getUser_details()[0].getUrl()).into(holder.follow_img);

    }

    @Override
    public int getItemCount() {
        return response.get(0).getPersonList1Follow().size();
    }

    class followingholder extends RecyclerView.ViewHolder {

        TextView name_follow,bio_follow,ussername_follow;
        CircleImageView follow_img;
        public followingholder(@NonNull View itemView) {
            super(itemView);
            name_follow=itemView.findViewById(R.id.name_follow);
            bio_follow=itemView.findViewById(R.id.bio_follow);
            ussername_follow=itemView.findViewById(R.id.ussername_follow);
            follow_img=itemView.findViewById(R.id.follow_img);

        }
    }
}
