package com.ladwa.aditya.popularmovies.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ladwa.aditya.popularmovies.R;
import com.ladwa.aditya.popularmovies.data.api.MovieApi;
import com.ladwa.aditya.popularmovies.data.model.MovieReviewListModel;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;

/**
 * Created by Aditya on 08-Feb-16.
 */
public class MovieReviewsFragment extends Fragment {

    private static final String LOG_TAG = MovieReviewsFragment.class.getSimpleName();
    private MovieApi movieApi;
    private Subscription reviewSubscription;
    private ArrayList<MovieReviewListModel.ReviewModel> reviewModelArrayList;
    private LinearLayoutManager linearLayoutManager;

    @Bind(R.id.recycler_view_movie_review)
    RecyclerView mRecyclerView;

    public MovieReviewsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_reviews, container, false);
        ButterKnife.bind(this, view);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);


        return view;
    }


    @Override
    public void onPause() {
        super.onPause();
        reviewSubscription.unsubscribe();
    }
}
