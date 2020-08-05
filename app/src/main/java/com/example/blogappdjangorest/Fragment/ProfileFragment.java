package com.example.blogappdjangorest.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.blogappdjangorest.Adapter.HomeScreenAdapter;
import com.example.blogappdjangorest.Adapter.Pager;
import com.example.blogappdjangorest.Adapter.ProfileTabLayoutAdapter;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.activities.EditProfile;
import com.example.blogappdjangorest.activities.FollowersNFollowing;
import com.google.android.material.tabs.TabLayout;

public class ProfileFragment extends Fragment implements TabLayout.OnTabSelectedListener {

    TextView EditProfileBtn;
    TextView FollowerBtn;
    RecyclerView recyclerView;
   TabLayout tabLayout;
    //This is our viewPager
   ViewPager viewPager;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        super.onViewCreated(view, savedInstanceState);

        FollowerBtn = view.findViewById(R.id.followerbtn);

        FollowerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.followers_dialog);



                Button dialogButton = (Button) dialog.findViewById(R.id.CloseBtn);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                recyclerView=dialog.findViewById(R.id.recycler_follower);
                HomeScreenAdapter homeScreenAdapter=new HomeScreenAdapter(getContext());
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(homeScreenAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

                dialog.show();
            }
        });
  FollowerBtn = view.findViewById(R.id.followerbtn);

        FollowerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), FollowersNFollowing.class);
                startActivity(i);
            }
        });



        // When swiping between pages, select the
        // corresponding tab.

        //Initializing the tablayout
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Blogs"));
        tabLayout.addTab(tabLayout.newTab().setText("Settings"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        //Initializing viewPager
        viewPager = (ViewPager)view.findViewById(R.id.pager);

        //Creating our pager adapter
        ProfileTabLayoutAdapter profileTabLayoutAdapter=new ProfileTabLayoutAdapter(getFragmentManager());

        //Adding adapter to pager
        viewPager.setAdapter(profileTabLayoutAdapter);

        //Adding onTabSelectedListener to swipe views
        tabLayout.addOnTabSelectedListener(this);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
         //actionBar.setSelectedNavigationItem(postion);
                tabLayout.setScrollPosition(position,0,true);
                tabLayout.setSelected(true);

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });


    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

}
