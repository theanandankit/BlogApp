package com.example.blogappdjangorest.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogappdjangorest.Adapter.HomeScreenAdapter;
import com.example.blogappdjangorest.Models.RetrofitModels.PublicBlogResponse;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.Retrofit.ApiClient;
import com.example.blogappdjangorest.Services.SignUpupload;
import com.example.blogappdjangorest.resources.PreferencesHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    ApiClient apiClient;
    HomeScreenAdapter homeScreenAdapter;
    ProgressBar progressBar;

    TextView title;

    TextView nothingtoshow;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);

        recyclerView=view.findViewById(R.id.recycleView);
        progressBar=view.findViewById(R.id.progress);
        apiClient=new ApiClient();
        progressBar.setVisibility(View.VISIBLE);

        title=view.findViewById(R.id.title);
        title.setText("Home");

        nothingtoshow = view.findViewById(R.id.nothingtoshow);
        nothingtoshow.setVisibility(View.INVISIBLE);



        Call<ArrayList<PublicBlogResponse>> call=apiClient.getApiinterface().public_blog();

        call.enqueue(new Callback<ArrayList<PublicBlogResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<PublicBlogResponse>> call, Response<ArrayList<PublicBlogResponse>> response) {
                if (response.code()==200)
                {
                    if (response.body().size()==0){

                        nothingtoshow.setVisibility(View.VISIBLE);
                    }
                    else {
                        nothingtoshow.setVisibility(View.INVISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                        homeScreenAdapter = new HomeScreenAdapter(getActivity(), response.body());
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(homeScreenAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PublicBlogResponse>> call, Throwable t) {

            }
        });

        return view;
    }
}
