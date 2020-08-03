package com.example.blogappdjangorest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogappdjangorest.R;

public class GroupsPostAdapter extends RecyclerView.Adapter<GroupsPostAdapter.GroupPostItemHolder> {
    private Context mContext;



    public GroupsPostAdapter(Context context){
        mContext = context;




    }

    @NonNull
    @Override
    public GroupPostItemHolder onCreateViewHolder (@NonNull ViewGroup parent,
                                                                                                   int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.blog_main_item, parent, false);
        return new GroupPostItemHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull GroupPostItemHolder holder, final int position){

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

    class GroupPostItemHolder extends RecyclerView.ViewHolder {




        private GroupPostItemHolder(@NonNull View itemView) {
            super(itemView);
            setGlobals(itemView);
            //setOnClickListeners();
        }

        private void setGlobals(View itemView) {

        }

    }
}
