package com.example.blogappdjangorest.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
//package com.example.blogappdjangorest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.resources.WaitingDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.activities.ForgotPasswordRequest;
import com.example.blogappdjangorest.activities.LoginScreen;


public class ForgetPassward3Fragment extends Fragment {

EditText newPassward;
EditText rewriteNewPassward;
Button done;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forget_passward3, container, false);
        newPassward = view.findViewById(R.id.NewPassward);
        rewriteNewPassward = view.findViewById(R.id.RewriteNewPassward);
        done = view.findViewById(R.id.DoneButton);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getContext(),LoginScreen.class));
            }
        });
        return view;
    }
}