package com.example.blogappdjangorest.followernfollowing;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.blogappdjangorest.Adapter.FollowListAdapter;
import com.example.blogappdjangorest.Adapter.FollowingListAdapter;
import com.example.blogappdjangorest.Adapter.HomeScreenAdapter;
import com.example.blogappdjangorest.Models.RetrofitModels.follower.followerList;
import com.example.blogappdjangorest.Models.RetrofitModels.following.FollowingList;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.Retrofit.ApiClient;
import com.example.blogappdjangorest.resources.PreferencesHelper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Following extends Fragment {

    RecyclerView recyclerView;
    ApiClient apiClient;
    PreferencesHelper preferencesHelper;
    LinearLayout empty;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_following, container, false);
        recyclerView=view.findViewById(R.id.followingrecycle);
        preferencesHelper = new PreferencesHelper(getContext());
        empty=view.findViewById(R.id.empty);


        apiClient=new ApiClient();
        Call<ArrayList<FollowingList>> call=apiClient.getApiinterface().followinglistthing(Integer.parseInt(preferencesHelper.getid()));
        call.enqueue(new Callback<ArrayList<FollowingList>>() {
            @Override
            public void onResponse(Call<ArrayList<FollowingList>> call, Response<ArrayList<FollowingList>> response) {
                if(response.code()==200){

                    if (response.body().get(0).getPersonList1Follow().size()==0){
                        empty.setVisibility(View.VISIBLE);
                    }
                    else {
                        FollowingListAdapter followingListAdapter = new FollowingListAdapter(getContext(), response.body());
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(followingListAdapter);
                    }

                }
            }

            @Override
            public void onFailure(Call<ArrayList<FollowingList>> call, Throwable t) {

            }
        });
        return view;

    }
}