package com.example.blogappdjangorest.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.blogappdjangorest.R;
import com.google.android.material.button.MaterialButton;

public class JoinGroup {

    private Context context;
    private Dialog dialog;

    public JoinGroup(Context context)
    {
        this.context=context;
    }

    public void show() {

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.joingroupdialog);
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
        MaterialButton create_button,done_button;

        create_button=dialog.findViewById(R.id.create_button);
        done_button=dialog.findViewById(R.id.done_button);
        create_layout=dialog.findViewById(R.id.create_layout);
        share_layout=dialog.findViewById(R.id.share_layout);

        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create_layout.setVisibility(View.GONE);
                share_layout.setVisibility(View.VISIBLE);
            }
        });

        done_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

}
