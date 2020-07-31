package com.example.blogappdjangorest.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.blogappdjangorest.R;


public class ForgetPassward2Fragment extends Fragment {

    TextView otp;
    Button nextButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_forget_passward2, container, false);
        nextButton = view.findViewById(R.id.Nextbutton2);
        otp = view.findViewById(R.id.otp);
        View.OnClickListener listner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ForgetPassward3Fragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.ForgetPasswardFragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        };
        nextButton.setOnClickListener(listner);
        return view;
    }
}