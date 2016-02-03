package com.ladwa.aditya.popularmovies;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ladwa.aditya.popularmovies.adapter.RecyclerViewMoviesAdapter;
import com.ladwa.aditya.popularmovies.model.MoviePosterModel;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @Bind(R.id.imageview_poster)
    private ImageView mimageView;

    @Bind(R.id.recycler_view_movie)
    private RecyclerView mRecyclerView;

    private ArrayList<MoviePosterModel> mPosterList;
    private RecyclerView.LayoutManager mlayoutManager;
    private RecyclerViewMoviesAdapter moviesAdapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT).show();


        mPosterList.add(new MoviePosterModel());

        mlayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mlayoutManager);
        moviesAdapter = new RecyclerViewMoviesAdapter();
        moviesAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(moviesAdapter);


        Glide.with(this).load("http://www.planwallpaper.com/static/images/Winter-Tiger-Wild-Cat-Images.jpg").into(mimageView);

        return view;
    }
}
