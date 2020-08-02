package com.example.blogappdjangorest.Adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogappdjangorest.Fragment.GroupPostFragment;
import com.example.blogappdjangorest.Fragment.HomeFragment;
import com.example.blogappdjangorest.R;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.GroupItemHolder> {
    private Context mContext;
    androidx.fragment.app.FragmentManager fm;



    public GroupsAdapter(Context context){
        mContext = context;
        fm =((FragmentActivity) mContext).getSupportFragmentManager();




    }

    @NonNull
    @Override
    public GroupItemHolder onCreateViewHolder (@NonNull ViewGroup parent,
                                                 int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.groups_item_layout, parent, false);
        return new GroupItemHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull GroupItemHolder holder,final int position){
        setOnClickListener(holder, position);

    }

    private void setOnClickListener(GroupItemHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().replace(R.id.homescreenfragment,new GroupPostFragment()).addToBackStack("Groups").commit();

            }
        });
    }


    @Override
    public int getItemCount () {
        return 15;

    }

    @Override
    public long getItemId ( int position){
        return position;
    }

    @Override
    public int getItemViewType ( int position){
        return position;
    }

    class GroupItemHolder extends RecyclerView.ViewHolder {




        private GroupItemHolder(@NonNull View itemView) {
            super(itemView);
            setGlobals(itemView);
            //setOnClickListeners();
        }

        private void setGlobals(View itemView) {

        }

    }
}
