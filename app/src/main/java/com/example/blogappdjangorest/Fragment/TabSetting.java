package com.example.blogappdjangorest.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.activities.EditProfile;
import com.example.blogappdjangorest.activities.LoginScreen;

import org.w3c.dom.Text;

public class TabSetting extends Fragment {
    TextView logout;
    TextView changePassward;
    TextView joinGroup;
    TextView createGroup;
    TextView createdGroups;
    TextView editProfile;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_setting, container, false);
        logout = (TextView) view.findViewById(R.id.Logout);
        changePassward = (TextView) view.findViewById(R.id.changePassward);
        joinGroup = (TextView) view.findViewById(R.id.JoinGroup);
        createdGroups = (TextView) view.findViewById(R.id.Createdgrp);
        createGroup= (TextView) view.findViewById(R.id.Creategrp);
        editProfile = (TextView) view.findViewById(R.id.EditProfile);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), EditProfile.class);
                getContext().startActivity(intent);
            }
        });

        View.OnClickListener listner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ChangePasswardFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.TabSetting1, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        };
        changePassward.setOnClickListener(listner);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), LoginScreen.class);
                getContext().startActivity(intent);
            }
        });
        return view;
    }



}