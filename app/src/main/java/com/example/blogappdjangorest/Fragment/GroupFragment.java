package com.example.blogappdjangorest.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogappdjangorest.Adapter.GroupsAdapter;
import com.example.blogappdjangorest.Models.RetrofitModels.GroupListResponse;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.Retrofit.ApiClient;
import com.example.blogappdjangorest.resources.PreferencesHelper;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupFragment extends Fragment {
    ShimmerFrameLayout shimmerFrameLayout;
    RecyclerView groups;
    GroupsAdapter groupsAdapter;
    TextView title;
    ApiClient apiClient;
    PreferencesHelper preferencesHelper;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_groups,container,false);

        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        preferencesHelper = new PreferencesHelper(getContext());
        initViews(view);

    }

    private void initViews(View view) {
        shimmerFrameLayout = view.findViewById(R.id.shimmerFrameLayout);
        groups = view.findViewById(R.id.groups);

        title = view.findViewById(R.id.title);
        title.setText("My Groups");

        apiClient=new ApiClient();
        get_group();
    }

    private void get_group()
    {
        Call<ArrayList<GroupListResponse>> call=apiClient.getApiinterface().get_group(preferencesHelper.getid());
        call.enqueue(new Callback<ArrayList<GroupListResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<GroupListResponse>> call, Response<ArrayList<GroupListResponse>> response) {

                if (response.code()==200)
                {
                    if (!(response.body().size() ==0))
                    {
                        groupsAdapter = new GroupsAdapter(getContext(),response.body());
                        groups.setAdapter(groupsAdapter);
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                    }
                }

            }

            @Override
            public void onFailure(Call<ArrayList<GroupListResponse>> call, Throwable t) {

            }
        });
    }
}
