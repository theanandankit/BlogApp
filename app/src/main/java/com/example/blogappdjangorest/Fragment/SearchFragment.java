package com.example.blogappdjangorest.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

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
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.blogappdjangorest.Fragment.SearchBlogFragment.blog_button;
import static com.example.blogappdjangorest.Fragment.SearchBlogFragment.blog_caution_image;
import static com.example.blogappdjangorest.Fragment.SearchBlogFragment.blog_caution_text;
import static com.example.blogappdjangorest.Fragment.SearchBlogFragment.blog_progress;
import static com.example.blogappdjangorest.Fragment.SearchBlogFragment.blog_recyclerView;
import static com.example.blogappdjangorest.Fragment.SearchProfileFragment.profile_caution_image;
import static com.example.blogappdjangorest.Fragment.SearchProfileFragment.profile_caution_text;
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
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_search, container, false);
        viewPager = view.findViewById(R.id.viewpager);
        tabLayout = view.findViewById(R.id.tabLayout);
        searchTablayoutAdapter = new SearchTablayoutAdapter(getFragmentManager());
        viewPager.setAdapter(searchTablayoutAdapter);
        tabLayout.setupWithViewPager(viewPager);
        apiClient = new ApiClient();
        bbcardView = view.findViewById(R.id.monitor_search);
        editText=view.findViewById(R.id.search_field);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                get_data(view);

                return true;
            }
        });

        bbcardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                get_data(view);
            }
        });
        return view;
    }
    private void get_data(View view)
    {
        View view1=getActivity().getCurrentFocus();
        if (view!=null)
        {
            InputMethodManager inputMethodManager= (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view1.getWindowToken(),0);
        }

        blog_progress.setVisibility(View.VISIBLE);
        profile_progress.setVisibility(View.VISIBLE);
        blog_caution_image.setVisibility(View.INVISIBLE);
        blog_caution_text.setVisibility(View.INVISIBLE);

        Call<ArrayList<PublicBlogResponse>> call = apiClient.getApiinterface().blogsearch(editText.getText().toString(),blog_button.getText().toString());
        call.enqueue(new Callback<ArrayList<PublicBlogResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<PublicBlogResponse>> call, Response<ArrayList<PublicBlogResponse>> response) {


                if (response.code() == 200) {
                    if (!(response.body().size() ==0)) {
                        blog_caution_image.setVisibility(View.INVISIBLE);
                        blog_caution_text.setVisibility(View.INVISIBLE);
                    }
                    else
                    {
                        blog_caution_image.setVisibility(View.VISIBLE);
                        blog_caution_text.setVisibility(View.VISIBLE);
                        blog_caution_text.setText("No Blog related to "+editText.getText().toString()+"in "+blog_button.getText().toString()+"\nplease try something else");
                    }

                    SearchBlogAdapter searchBlogAdapter = new SearchBlogAdapter(getContext(), response.body());
                    blog_recyclerView.setHasFixedSize(true);
                    blog_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    blog_recyclerView.setAdapter(searchBlogAdapter);
                }
                else
                {
                    blog_caution_image.setVisibility(View.VISIBLE);
                    blog_caution_text.setVisibility(View.VISIBLE);
                    blog_caution_text.setText("something went wrong");
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
                        profile_caution_image.setVisibility(View.INVISIBLE);
                        profile_caution_text.setVisibility(View.INVISIBLE);
                    }
                    else
                    {
                        profile_caution_image.setVisibility(View.VISIBLE);
                        profile_caution_text.setVisibility(View.VISIBLE);
                        profile_caution_text.setText("No Profile related to "+editText.getText().toString()+"\nplease try something else");
                    }
                    SearchProfileAdapter searchProfileAdapter = new SearchProfileAdapter(getContext(),response.body());
                    profile_recyclerView.setHasFixedSize(true);
                    profile_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    profile_recyclerView.setAdapter(searchProfileAdapter);
                }
                else
                {
                    profile_caution_text.setVisibility(View.VISIBLE);
                    profile_caution_image.setVisibility(View.VISIBLE);
                    profile_caution_text.setText("Something Went Wrong");
                }
                profile_progress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ArrayList<ProfileSearchResponse>> call, Throwable t) {

            }
        });
    }
}