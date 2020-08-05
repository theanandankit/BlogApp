package com.example.blogappdjangorest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogappdjangorest.Fragment.GroupPostFragment;
import com.example.blogappdjangorest.R;

public class CreatedGroupsAdapter extends RecyclerView.Adapter<CreatedGroupsAdapter.GroupItemHolder> {
    private Context mContext;
    androidx.fragment.app.FragmentManager fm;

    public CreatedGroupsAdapter(Context context){
        mContext = context;
        fm =((FragmentActivity) mContext).getSupportFragmentManager();
    }

    @NonNull
    @Override
    public GroupItemHolder onCreateViewHolder (@NonNull ViewGroup parent,
                                               int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.created_groups_item, parent, false);
        return new GroupItemHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull GroupItemHolder holder,final int position){
        setOnClickListener(holder, position);

    }

    private void setOnClickListener(GroupItemHolder holder, int position) {

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

