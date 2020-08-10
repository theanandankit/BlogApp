package com.example.blogappdjangorest.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.example.blogappdjangorest.Adapter.SearchBlogAdapter;
import com.example.blogappdjangorest.Adapter.SearchProfileAdapter;
import com.example.blogappdjangorest.Adapter.SearchTablayoutAdapter;
import com.example.blogappdjangorest.Models.RetrofitModels.ProfileSearchResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.PublicBlogResponse;
import com.example.blogappdjangorest.R;
import com.example.blogappdjangorest.Retrofit.ApiClient;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.blogappdjangorest.Fragment.SearchBlogFragment.blog_progress;
import static com.example.blogappdjangorest.Fragment.SearchBlogFragment.blog_recyclerView;
import static com.example.blogappdjangorest.Fragment.SearchProfileFragment.profile_progress;
import static com.example.blogappdjangorest.Fragment.SearchProfileFragment.profile_recyclerView;

public class SearchFragment extends Fragment {


    ViewPager viewPager;
    TabLayout tabLayout;
    SearchTablayoutAdapter searchTablayoutAdapter;
    EditText editText;
    public static MaterialCardView bbcardView;
    ApiClient apiClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        viewPager = view.findViewById(R.id.viewpager);
        tabLayout = view.findViewById(R.id.tabLayout);
        searchTablayoutAdapter = new SearchTablayoutAdapter(getFragmentManager());
        viewPager.setAdapter(searchTablayoutAdapter);
        tabLayout.setupWithViewPager(viewPager);
        apiClient = new ApiClient();
        bbcardView = view.findViewById(R.id.monitor_search);
        editText=view.findViewById(R.id.search_field);

        bbcardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                blog_progress.setVisibility(View.VISIBLE);
                profile_progress.setVisibility(View.VISIBLE);

                Call<ArrayList<PublicBlogResponse>> call = apiClient.getApiinterface().blogsearch(editText.getText().toString());
                call.enqueue(new Callback<ArrayList<PublicBlogResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<PublicBlogResponse>> call, Response<ArrayList<PublicBlogResponse>> response) {


                        if (response.code() == 200) {
                            if (!response.body().toString().isEmpty()) {
                                SearchBlogAdapter searchBlogAdapter = new SearchBlogAdapter(getContext(), response.body());
                                blog_recyclerView.setHasFixedSize(true);
                                blog_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                blog_recyclerView.setAdapter(searchBlogAdapter);
                            }
                        }
                        blog_progress.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<PublicBlogResponse>> call, Throwable t) {

                    }
                });
                Call<ArrayList<ProfileSearchResponse>> call1 = apiClient.getApiinterface().profilesearch(editText.getText().toString());
                call1.enqueue(new Callback<ArrayList<ProfileSearchResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ProfileSearchResponse>> call, Response<ArrayList<ProfileSearchResponse>> response) {

                        if (response.code() == 200) {
                            if (!response.body().isEmpty()) {
                                SearchProfileAdapter searchProfileAdapter = new SearchProfileAdapter(getContext(),response.body());
                                profile_recyclerView.setHasFixedSize(true);
                                profile_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                profile_recyclerView.setAdapter(searchProfileAdapter);
                            }
                        }
                        profile_progress.setVisibility(View.INVISIBLE);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ProfileSearchResponse>> call, Throwable t) {

                    }
                });
            }
        });
        return view;
    }
}