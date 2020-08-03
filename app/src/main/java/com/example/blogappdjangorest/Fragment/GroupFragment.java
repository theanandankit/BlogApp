package com.example.blogappdjangorest.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogappdjangorest.Adapter.GroupsAdapter;
import com.example.blogappdjangorest.R;
import com.facebook.shimmer.ShimmerFrameLayout;

public class GroupFragment extends Fragment {
    ShimmerFrameLayout shimmerFrameLayout;
    RecyclerView groups;
    GroupsAdapter groupsAdapter;
    TextView title;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_groups,container,false);

        return view;
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
        title.setText("My Groups");
        groups.setAdapter(groupsAdapter);
        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.setVisibility(View.GONE);


    }
}
