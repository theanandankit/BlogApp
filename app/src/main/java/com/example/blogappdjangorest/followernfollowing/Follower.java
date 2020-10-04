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
import com.example.blogappdjangorest.Adapter.HomeScreenAdapter;
import com.example.blogappdjangorest.Models.RetrofitModels.data.ProfileUser;
import com.example.blogappdjangorest.Models.RetrofitModels.follower.followerList;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.Retrofit.ApiClient;
import com.example.blogappdjangorest.resources.PreferencesHelper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Follower extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_follower, container, false);

        recyclerView=view.findViewById(R.id.followerrecycle);
        preferencesHelper = new PreferencesHelper(getContext());
        empty=view.findViewById(R.id.empty);

        apiClient=new ApiClient();
        Call<ArrayList<followerList>> call=apiClient.getApiinterface().followerlistthing(Integer.parseInt(preferencesHelper.getid()));
        call.enqueue(new Callback<ArrayList<followerList>>() {


            @Override
            public void onResponse(Call<ArrayList<followerList>> call, Response<ArrayList<followerList>> response) {
                if(response.code()==200){

                    if (response.body().get(0).getPersonList2Follower().size()==0){
                        empty.setVisibility(View.VISIBLE);
                    }
                    else {
                        FollowListAdapter followListAdapter = new FollowListAdapter(getContext(), response.body());
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(followListAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<followerList>> call, Throwable t) {

            }
        });

        return  view;


    }
}