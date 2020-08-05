package com.example.blogappdjangorest.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.blogappdjangorest.Dialog.CreateGroup;
import com.example.blogappdjangorest.Dialog.JoinGroup;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.activities.ChangePassword;
import com.example.blogappdjangorest.activities.EditProfile;
import com.example.blogappdjangorest.activities.HomeScreen;
import com.example.blogappdjangorest.activities.LoginScreen;

import org.w3c.dom.Text;

public class TabSetting extends Fragment {
    RelativeLayout logout,changePassward,joinGroup,createdGroups,createGroup,editProfile;
    JoinGroup join;
    CreateGroup create;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_setting, container, false);

        init(view);

        return view;
    }

    public void init(View view)
    {
        logout =view.findViewById(R.id.logout);
        changePassward =view.findViewById(R.id.change_password);
        joinGroup =view.findViewById(R.id.join);
        createdGroups = view.findViewById(R.id.ygroup);
        createGroup=view.findViewById(R.id.create);
        editProfile =view.findViewById(R.id.edit);
        join=new JoinGroup(getContext());
        create=new CreateGroup(getContext());

        createdGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                create.show();
            }
        });

        joinGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                join.show();
            }
        });

        changePassward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getContext(), ChangePassword.class));
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), EditProfile.class);
                getContext().startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), LoginScreen.class);
                getContext().startActivity(intent);
            }
        });
    }



}