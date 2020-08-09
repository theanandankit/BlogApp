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
import com.example.blogappdjangorest.Models.RetrofitModels.GroupBlogResponse;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.Retrofit.ApiClient;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupPostFragment extends Fragment {
    ShimmerFrameLayout shimmerFrameLayout;
    RecyclerView groupPosts;
    GroupsPostAdapter groupsPostAdapter;
    TextView title;
    ApiClient apiClient;


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

        title = view.findViewById(R.id.title);
        title.setText("Group Blogs");
        apiClient=new ApiClient();
        get_blog();

    }

    private void get_blog()
    {
        Call<ArrayList<GroupBlogResponse>> call=apiClient.getApiinterface().get_group_blog("1");
        call.enqueue(new Callback<ArrayList<GroupBlogResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<GroupBlogResponse>> call, Response<ArrayList<GroupBlogResponse>> response) {


                if (response.code()==200)
                {
                    if (!(response.body().isEmpty()))
                    {
                        groupsPostAdapter = new GroupsPostAdapter(getContext(),response.body().get(0).getGroup());
                        groupPosts.setAdapter(groupsPostAdapter);
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<GroupBlogResponse>> call, Throwable t) {

            }
        });
    }
}
