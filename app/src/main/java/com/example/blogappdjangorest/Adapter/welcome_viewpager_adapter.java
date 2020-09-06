package com.example.blogappdjangorest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.blogappdjangorest.R;

public class welcome_viewpager_adapter  extends PagerAdapter {

    public Context context;
    public LayoutInflater layoutInflater;
    public View view1,view2,view3;
    public welcome_viewpager_adapter(Context context,LayoutInflater layoutInflater)
    {
        this.context=context;
        this.layoutInflater=layoutInflater;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {


        view1 = layoutInflater.inflate(R.layout.welcome1, container, false);
        view2 = layoutInflater.inflate(R.layout.welcome2, container, false);
        view3 = layoutInflater.inflate(R.layout.welcome3, container, false);
        if (position==0)
        {
            container.addView(view1);
            return view1;
        }
        else if (position==1)
        {
            container.addView(view2);
            return view2;
        }
        else
        {
            container.addView(view3);
            return view3;
        }
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
