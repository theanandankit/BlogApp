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
import com.example.blogappdjangorest.Models.RetrofitModels.GroupInfoResponse;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.Retrofit.ApiClient;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupPostFragment extends Fragment {
    ShimmerFrameLayout shimmerFrameLayout;
    CircleImageView group_image;
    RecyclerView groupPosts;
    GroupsPostAdapter groupsPostAdapter;
    TextView title,group_name,group_creator;
    ApiClient apiClient;
    String group_id;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_group_post,container,false);

        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        group_id=getArguments().getString("group_id");
        initViews(view);

    }

    private void initViews(View view) {
        shimmerFrameLayout = view.findViewById(R.id.shimmerFrameLayout);
        groupPosts = view.findViewById(R.id.groupPosts);
        title = view.findViewById(R.id.title);
        group_image=view.findViewById(R.id.group_image);
        group_creator=view.findViewById(R.id.group_creator);
        group_name=view.findViewById(R.id.group_name);
        title.setText("Group Blogs");
        apiClient=new ApiClient();
        get_blog();
        get_group_info();

    }

    private void get_blog()
    {
        Call<ArrayList<GroupBlogResponse>> call=apiClient.getApiinterface().get_group_blog(group_id);
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

    private void get_group_info()
    {
        Call<ArrayList<GroupInfoResponse>> call=apiClient.getApiinterface().group_info(group_id);

        call.enqueue(new Callback<ArrayList<GroupInfoResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<GroupInfoResponse>> call, Response<ArrayList<GroupInfoResponse>> response) {

                group_name.setText(response.body().get(0).getGroup_description());
                Picasso.get().load(response.body().get(0).getUrl()).into(group_image);
                group_creator.setText(response.body().get(0).getCreator_id().getFirst_name());
            }

            @Override
            public void onFailure(Call<ArrayList<GroupInfoResponse>> call, Throwable t) {

            }
        });
    }
}
