package com.example.blogappdjangorest.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogappdjangorest.Fragment.GroupPostFragment;
import com.example.blogappdjangorest.Models.RetrofitModels.GroupListResponse;
import com.example.blogappdjangorest.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CreatedGroupsAdapter extends RecyclerView.Adapter<CreatedGroupsAdapter.GroupItemHolder> {
    private Context mContext;
    androidx.fragment.app.FragmentManager fm;
    Dialog dialog;
    ArrayList<GroupListResponse> responses;
    ArrayList<String> ids;

    public CreatedGroupsAdapter(Context context, ArrayList<GroupListResponse> responses){
        mContext = context;
        fm =((FragmentActivity) mContext).getSupportFragmentManager();
        this.responses=responses;
        ids=new ArrayList<>();
    }

    @NonNull
    @Override
    public GroupItemHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.created_groups_item, parent, false);
        return new GroupItemHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull GroupItemHolder holder,final int position){

        holder.name.setText(responses.get(position).getGroup_description());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show(responses.get(position).getGroup_code().toString());
            }
        });


    }


    @Override
    public int getItemCount () {
        return responses.size();

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

        MaterialButton button;
        MaterialTextView name,date;
        private GroupItemHolder(@NonNull View itemView) {
            super(itemView);
            button=itemView.findViewById(R.id.group_button);
            name=itemView.findViewById(R.id.group_name);
            date=itemView.findViewById(R.id.group_date);
        }
    }

    public void show(final String code) {

        dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.codeviewdialog);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setAttributes(lp);
        dialog.setCancelable(false);
        TextView passcode;
        MaterialButton done_button;
        MaterialButton share;
        share=dialog.findViewById(R.id.share);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);

                String shareBody = "Hey you can join my group via this group code: "+code;
                intent.setType("text/plain");
                /*Applying information Subject and Body.*/
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT,"My group");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                /*Fire!*/
                mContext.startActivity(Intent.createChooser(intent, "my group"));
            }
        });

        done_button=dialog.findViewById(R.id.done_button);
        passcode=dialog.findViewById(R.id.passcode);
        passcode.setText(code);


        done_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}

