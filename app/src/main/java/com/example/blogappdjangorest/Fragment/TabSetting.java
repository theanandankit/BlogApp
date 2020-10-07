package com.example.blogappdjangorest.Fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.blogappdjangorest.Dialog.CreateGroup;
import com.example.blogappdjangorest.Dialog.JoinGroup;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.activities.ChangePassword;
import com.example.blogappdjangorest.activities.EditProfile;
import com.example.blogappdjangorest.activities.LoginScreen;
import com.example.blogappdjangorest.resources.PreferencesHelper;
import com.google.firebase.auth.FirebaseAuth;

import static android.app.Activity.RESULT_OK;

public class TabSetting extends Fragment {
    RelativeLayout logout,changePassward,joinGroup,createdGroups,createGroup,editProfile;
    JoinGroup join;
    CreateGroup create;
    AlertDialog.Builder builder;
    Dialog dialog;
    PreferencesHelper preferencesHelper;
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
        create=new CreateGroup(getContext(),getActivity());
        preferencesHelper=new PreferencesHelper(getContext());

        createdGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.homescreenfragment,new CreatedGroupsFragment()).addToBackStack("Profile").commit();
            }
        });

        createGroup.setOnClickListener(new View.OnClickListener() {
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

                logout();
            }
        });
    }

    public void logout()
    {

        builder = new AlertDialog.Builder(getContext());
        builder.setTitle("want to logout ?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                preferencesHelper.setlogin(false);
                preferencesHelper.setprofilesetup(false);
                startActivity(new Intent(getContext(),LoginScreen.class));
                getActivity().finish();
            }
        });
        builder.setNegativeButton("Cancel", null);
        dialog = builder.create();
        dialog.show();
    }


}