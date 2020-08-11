package com.example.blogappdjangorest.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.blogappdjangorest.Adapter.ProfileBlogAdapter;
import com.example.blogappdjangorest.Adapter.ProfileTabLayoutAdapter;
import com.example.blogappdjangorest.Models.RetrofitModels.LoginResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.data.ProfileUser;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.Retrofit.ApiClient;
import com.example.blogappdjangorest.activities.EditProfile;
import com.example.blogappdjangorest.activities.FollowersNFollowing;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment implements TabLayout.OnTabSelectedListener {

    TextView EditProfileBtn;
    TextView FollowerBtn,biodescr,blogcount,flowwercount,name;
    RecyclerView recyclerView;
   TabLayout tabLayout;
   ApiClient apiClient;
    //This is our viewPager
   ViewPager viewPager;
   TextView title;
   CircleImageView image;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        super.onViewCreated(view, savedInstanceState);

        title=view.findViewById(R.id.title);
        title.setText("Profile");
        FollowerBtn = view.findViewById(R.id.followerbtn);
        image=view.findViewById(R.id.profileimage);

//        FollowerBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final Dialog dialog = new Dialog(getContext());
//                dialog.setContentView(R.layout.followers_dialog);
//
//                Button dialogButton = (Button) dialog.findViewById(R.id.CloseBtn);
//                // if button is clicked, close the custom dialog
//                dialogButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });
//                recyclerView=dialog.findViewById(R.id.recycler_follower);
//                ProfileBlogAdapter profileBlogAdapter=new ProfileBlogAdapter(getContext());
//                recyclerView.setHasFixedSize(true);
//                recyclerView.setAdapter(profileBlogAdapter);
//                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
//
//                dialog.show();
//            }
//        });
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

        biodescr = view.findViewById(R.id.biodescr);
        blogcount = view.findViewById(R.id.blogcount);
        name = view.findViewById(R.id.nameprofile);
        flowwercount = view.findViewById(R.id.followercount);


        Call<ArrayList<ProfileUser>> call=apiClient.getApiinterface().profileUser(10);
        call.enqueue(new Callback<ArrayList<ProfileUser>>() {
            @Override
            public void onResponse(Call<ArrayList<ProfileUser>> call, Response<ArrayList<ProfileUser>> response) {
                if (!response.isSuccessful()){
                    Log.d("maniik",response.code() + "");
                    return;
                }
                biodescr.setText(response.body().get(0).getUserDetails().get(0).getDescription().toString());
                flowwercount.setText(response.body().get(0).getPersonList2().size() + "");
                blogcount.setText(response.body().get(0).getAuthorName().size() + "");
                name.setText(response.body().get(0).getFirstName().toString() + " " + response.body().get(0).getLastName().toString());
                Picasso.get().load(response.body().get(0).getUserDetails().get(0).getUrl()).into(image);

            }

            @Override
            public void onFailure(Call<ArrayList<ProfileUser>> call, Throwable t) {

                Log.d("maniik",t.getMessage() + "");
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
