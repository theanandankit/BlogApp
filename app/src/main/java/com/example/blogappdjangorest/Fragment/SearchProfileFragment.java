package com.example.blogappdjangorest.Fragment;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogappdjangorest.Adapter.SearchProfileAdapter;
import com.example.blogappdjangorest.R;

public class SearchProfileFragment extends Fragment {

    public static RecyclerView profile_recyclerView;
    SearchProfileAdapter searchProfileAdapter;
    public static ProgressBar profile_progress;
    public static ImageView profile_caution_image;
    public static TextView profile_caution_text;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search_profile,container,false);

          profile_recyclerView=view.findViewById(R.id.recycleView);
          profile_progress=view.findViewById(R.id.progress);
          profile_progress.setVisibility(View.INVISIBLE);
          profile_caution_image=view.findViewById(R.id.caution);
          profile_caution_text=view.findViewById(R.id.error);
          profile_caution_image.setVisibility(View.INVISIBLE);
          profile_caution_text.setText("Search the profile");
//        searchProfileAdapter=new SearchProfileAdapter(getContext());
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(searchProfileAdapter);

        return view;
    }
}
