package com.example.blogappdjangorest.followernfollowing;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.blogappdjangorest.Adapter.FollowListAdapter;
import com.example.blogappdjangorest.Adapter.HomeScreenAdapter;
import com.example.blogappdjangorest.Models.RetrofitModels.data.ProfileUser;
import com.example.blogappdjangorest.Models.RetrofitModels.follower.followerList;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.Retrofit.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Follower extends Fragment {

    RecyclerView recyclerView;
    ApiClient apiClient;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_follower, container, false);

        recyclerView=view.findViewById(R.id.followerrecycle);


        apiClient=new ApiClient();
        Call<ArrayList<followerList>> call=apiClient.getApiinterface().followerlistthing(10);
        call.enqueue(new Callback<ArrayList<followerList>>() {
            @Override
            public void onResponse(Call<ArrayList<followerList>> call, Response<ArrayList<followerList>> response) {
                if(response.code()==200){
                    FollowListAdapter followListAdapter=new FollowListAdapter(getContext(),response.body());
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(followListAdapter);

                }
            }

            @Override
            public void onFailure(Call<ArrayList<followerList>> call, Throwable t) {

            }
        });

        return  view;


    }
}