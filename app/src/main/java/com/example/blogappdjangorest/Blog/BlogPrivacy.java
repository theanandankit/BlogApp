package com.example.blogappdjangorest.Blog;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

import com.example.blogappdjangorest.R;

public class BlogPrivacy extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_blog_privacy, container, false);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        final NavController navController = Navigation.findNavController(view);


        final CheckBox CheckBox = (android.widget.CheckBox)view.findViewById(R.id.publicchecker);
        final CheckBox CheckBox2 = (android.widget.CheckBox)view.findViewById(R.id.privatechecker);

        CheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckBox.isChecked()){
                    navController.navigate(R.id.action_blogPrivacy_to_homeFragment);
                }

            }
        });
        CheckBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckBox2.isChecked()){
                    navController.navigate(R.id.action_blogPrivacy_to_blogGroup);
                }

            }
        });



    }

}