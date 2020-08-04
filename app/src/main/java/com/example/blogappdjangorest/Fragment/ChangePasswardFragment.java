package com.example.blogappdjangorest.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.activities.HomeScreen;
import com.example.blogappdjangorest.activities.LoginScreen;


public class ChangePasswardFragment extends Fragment {
      EditText passward;
      EditText reWritePassward;
      Button done;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_change_passward, container, false);
        passward = (EditText) view.findViewById(R.id.NewPassward);
        reWritePassward = (EditText) view.findViewById(R.id.RewriteNewPassward);
        done = (Button) view.findViewById(R.id.DoneButton);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    startActivity( new Intent(getContext(), HomeScreen.class));
            }
        });
        return  view;
    }


}