package com.example.blogappdjangorest.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.blogappdjangorest.Adapter.GroupsAdapter;
import com.example.blogappdjangorest.Adapter.GroupsPostAdapter;
import com.example.blogappdjangorest.R;
import com.facebook.shimmer.ShimmerFrameLayout;

public class GroupPostFragment extends Fragment {
    ShimmerFrameLayout shimmerFrameLayout;
    RecyclerView groupPosts;
    GroupsPostAdapter groupsPostAdapter;
    TextView title;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_group_post,container,false);

        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        initViews(view);

    }

    private void initViews(View view) {
        shimmerFrameLayout = view.findViewById(R.id.shimmerFrameLayout);
        groupPosts = view.findViewById(R.id.groupPosts);
        groupsPostAdapter = new GroupsPostAdapter(getContext());
        title = view.findViewById(R.id.title);
        title.setText("Group Blogs");
        groupPosts.setAdapter(groupsPostAdapter);
        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.setVisibility(View.GONE);


    }
}
