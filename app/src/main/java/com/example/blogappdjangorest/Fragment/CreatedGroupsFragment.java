package com.example.blogappdjangorest.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.blogappdjangorest.Adapter.GroupsAdapter;
import com.example.blogappdjangorest.R;
import com.facebook.shimmer.ShimmerFrameLayout;


public class CreatedGroupsFragment extends Fragment {
    ShimmerFrameLayout shimmerFrameLayout;
    RecyclerView groups;
    GroupsAdapter groupsAdapter;
    TextView title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_created_groups, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        initViews(view);

    }

    private void initViews(View view) {
        shimmerFrameLayout = view.findViewById(R.id.shimmerFrameLayout);
        groups = view.findViewById(R.id.groups);
        groupsAdapter = new GroupsAdapter(getContext());
        title = view.findViewById(R.id.title);
        title.setText("Created By You");
        groups.setAdapter(groupsAdapter);
        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.setVisibility(View.GONE);

    }
}