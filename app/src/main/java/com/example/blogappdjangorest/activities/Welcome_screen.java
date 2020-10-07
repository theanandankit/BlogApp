package com.example.blogappdjangorest.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.blogappdjangorest.Adapter.welcome_viewpager_adapter;
import com.example.blogappdjangorest.R;
import com.google.android.material.button.MaterialButton;

public class Welcome_screen extends AppCompatActivity {

    ViewPager viewPager;
    MaterialButton next_button;
    welcome_viewpager_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        init();
    }

    public void init() {
        viewPager = findViewById(R.id.view_pager);
        next_button = findViewById(R.id.welcome_next_button);
        adapter = new welcome_viewpager_adapter(Welcome_screen.this, (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        bottomProgressDots(0);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new PreferenceHelper(welcome_screen.this).setVisitedWelcome();
//                startActivity(new Intent(getApplicationContext(), module_selection.class));
//                finish();

                if (viewPager.getCurrentItem()==2)
                {
                    startActivity(new Intent(getApplicationContext(),LoginScreen.class));
                    finish();
                }
                else
                {
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                }
            }
        });


    }

    private void bottomProgressDots(int current_index) {
        LinearLayout dotsLayout = (LinearLayout) findViewById(R.id.welcome_layout_dots);
        ImageView[] dots = new ImageView[3];

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
            int width_height = 30;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(15, 15, 15, 15);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.ic_account_circle_black_24dp);
            dots[i].setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
            if (i < current_index) {
                dots[i].setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
            }
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current_index].setImageResource(R.drawable.ic_account_circle_black_24dp);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new LinearLayout.LayoutParams(50, 50));
            dots[current_index].setLayoutParams(params);
            dots[current_index].setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
        }
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {


        @Override
        public void onPageSelected(final int position) {
            bottomProgressDots(position);
        }


        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {


        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };
}

