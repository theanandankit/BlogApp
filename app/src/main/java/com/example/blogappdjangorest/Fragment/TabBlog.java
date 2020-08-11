package com.example.blogappdjangorest.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogappdjangorest.Adapter.HomeScreenAdapter;
import com.example.blogappdjangorest.Adapter.ProfileBlogAdapter;
import com.example.blogappdjangorest.Models.RetrofitModels.PublicBlogResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.data.ProfileUser;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.Retrofit.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TabBlog extends Fragment {

    RecyclerView recyclerView;
    ApiClient apiClient;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.tab_blog,container,false);


        recyclerView=view.findViewById(R.id.recycler_blog);
        apiClient=new ApiClient();
        Call<ArrayList<ProfileUser>> call=apiClient.getApiinterface().profileUser(10);

        call.enqueue(new Callback<ArrayList<ProfileUser>>() {
            @Override
            public void onResponse(Call<ArrayList<ProfileUser>> call, Response<ArrayList<ProfileUser>> response) {
                if (response.code()==200){
                    ProfileBlogAdapter profileBlogAdapter=new ProfileBlogAdapter(getActivity(),response.body());
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(profileBlogAdapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProfileUser>> call, Throwable t) {
                Log.d("maniik",t.getMessage() + "");
            }
        });


        return view;


    }
}
