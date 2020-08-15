package com.example.blogappdjangorest.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogappdjangorest.Adapter.HomeScreenAdapter;
import com.example.blogappdjangorest.Models.RetrofitModels.Pagination.HomePagePaginationResponse;
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
    ProgressBar progressBar,loading;
    TextView title;
    TextView nothingtoshow;
    boolean Isscroll=false,stop=true;
    ArrayList<PublicBlogResponse> list;
    int total,current=1;
    public static int size=8;
    int currentItem,totalItem,ScrollItem;
    LinearLayoutManager linearLayoutManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recycleView);
        progressBar = view.findViewById(R.id.progress);
        apiClient = new ApiClient();
        progressBar.setVisibility(View.VISIBLE);
        loading=view.findViewById(R.id.loading);

        title = view.findViewById(R.id.title);
        title.setText("Home");

        nothingtoshow = view.findViewById(R.id.nothingtoshow);
        nothingtoshow.setVisibility(View.INVISIBLE);
        list=new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(getContext());

        get_data();

        homeScreenAdapter = new HomeScreenAdapter(getActivity(),list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(homeScreenAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState== AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                {
                    Isscroll=true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItem=linearLayoutManager.getChildCount();
                totalItem=linearLayoutManager.getItemCount();
                ScrollItem=linearLayoutManager.findFirstCompletelyVisibleItemPosition();

                if (Isscroll&&(currentItem + ScrollItem == totalItem)&&stop)
                {
                    get_data();
                    loading.setVisibility(View.VISIBLE);
                    stop=false;
                }
            }
        });




        return view;
    }

    private void get_data() {
//        Call<ArrayList<PublicBlogResponse>> call=apiClient.getApiinterface().public_blog();
//
//        call.enqueue(new Callback<ArrayList<PublicBlogResponse>>() {
//            @Override
//            public void onResponse(Call<ArrayList<PublicBlogResponse>> call, Response<ArrayList<PublicBlogResponse>> response) {
//                if (response.code()==200)
//                {
//                    if (response.body().size()==0){
//
//                        nothingtoshow.setVisibility(View.VISIBLE);
//                    }
//                    else {
//                        nothingtoshow.setVisibility(View.INVISIBLE);
//                        progressBar.setVisibility(View.INVISIBLE);
//                        homeScreenAdapter = new HomeScreenAdapter(getActivity(), response.body());
//                        recyclerView.setHasFixedSize(true);
//                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//                        recyclerView.setAdapter(homeScreenAdapter);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<PublicBlogResponse>> call, Throwable t) {
//
//            }
//        });

        Call<HomePagePaginationResponse> call = apiClient.getApiinterface().public_blog(String.valueOf(current));
        call.enqueue(new Callback<HomePagePaginationResponse>() {
            @Override
            public void onResponse(Call<HomePagePaginationResponse> call, Response<HomePagePaginationResponse> response) {

                if (response.code() == 200) {
                    nothingtoshow.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    total = Integer.parseInt(response.body().getCount());

                    for (int a = 0; a < response.body().getResults().size(); a++) {
                        list.add(response.body().getResults().get(a));
                    }

                    stop = true;
                    if (response.body().getNext() != null) {
                        current = current + 1;
                    } else {
                        stop = false;
                    }
                    loading.setVisibility(View.GONE);
                    homeScreenAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<HomePagePaginationResponse> call, Throwable t) {

            }
        });
    }
}
