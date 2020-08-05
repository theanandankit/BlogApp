package com.example.blogappdjangorest.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogappdjangorest.Fragment.GroupPostFragment;
import com.example.blogappdjangorest.R;
import com.google.android.material.button.MaterialButton;

public class CreatedGroupsAdapter extends RecyclerView.Adapter<CreatedGroupsAdapter.GroupItemHolder> {
    private Context mContext;
    androidx.fragment.app.FragmentManager fm;
    Dialog dialog;

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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
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

    public void show() {

        dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.creategroupdialog);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setAttributes(lp);
        dialog.setCancelable(false);

        final LinearLayout create_layout,share_layout;
        MaterialButton done_button;

        done_button=dialog.findViewById(R.id.done_button);
        create_layout=dialog.findViewById(R.id.create_layout);
        share_layout=dialog.findViewById(R.id.share_layout);

                create_layout.setVisibility(View.GONE);
                share_layout.setVisibility(View.VISIBLE);

        done_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}

