package com.example.blogappdjangorest.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.blogappdjangorest.Adapter.HomeScreenAdapter;
import com.example.blogappdjangorest.Adapter.Pager;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.activities.EditProfile;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

public class ProfileFragment extends Fragment implements TabLayout.OnTabSelectedListener {

    TextView EditProfileBtn;
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
        Pager adapter = new Pager(getParentFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views
        tabLayout.addOnTabSelectedListener(this);



        EditProfileBtn = view.findViewById(R.id.EditProfileBtn);

        EditProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), EditProfile.class);
                getContext().startActivity(intent);
            }
        });

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
