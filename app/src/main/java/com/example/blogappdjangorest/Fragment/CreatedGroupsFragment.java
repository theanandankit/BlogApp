package com.example.blogappdjangorest.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.blogappdjangorest.Adapter.CreatedGroupsAdapter;
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


public class CreatedGroupsFragment extends Fragment {
    ShimmerFrameLayout shimmerFrameLayout;
    RecyclerView groups;
    CreatedGroupsAdapter groupsAdapter;
    TextView title;
    ApiClient apiClient;
    PreferencesHelper preferencesHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_created_groups, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        initViews(view);
        preferencesHelper = new PreferencesHelper(getContext());
        apiClient=new ApiClient();
        get_list();

    }

    private void initViews(View view) {
        shimmerFrameLayout = view.findViewById(R.id.shimmerFrameLayout);
        groups = view.findViewById(R.id.groups);

        title = view.findViewById(R.id.title);
        title.setText("Created By You");
    }

    private void get_list()
    {
        Call<ArrayList<GroupListResponse>> call=apiClient.getApiinterface().get_group(preferencesHelper.getid());
        call.enqueue(new Callback<ArrayList<GroupListResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<GroupListResponse>> call, Response<ArrayList<GroupListResponse>> response) {

                if (response.code()==200)
                {
                    if (!(response.body().size()==0))
                    {
                        groupsAdapter = new CreatedGroupsAdapter(getContext(),response.body());
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