package com.ladwa.aditya.popularmovies.ui.fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ladwa.aditya.popularmovies.R;
import com.ladwa.aditya.popularmovies.data.api.MovieApi;
import com.ladwa.aditya.popularmovies.data.api.ServiceGenerator;
import com.ladwa.aditya.popularmovies.data.model.MovieResultListModel;
import com.ladwa.aditya.popularmovies.ui.MovieDetailActivity;
import com.ladwa.aditya.popularmovies.ui.adapter.RecyclerViewMoviesAdapter;
import com.ladwa.aditya.popularmovies.util.Utility;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @Bind(R.id.recycler_view_movie)
    RecyclerView mRecyclerView;

    private static final String TAG = MainActivityFragment.class.getSimpleName();
    private ArrayList<MovieResultListModel.ResultModel> mPosterList;
    private GridLayoutManager mlayoutManager;
    private RecyclerViewMoviesAdapter moviesAdapter;
    private MovieApi movieApi;
    private Subscription movieSubscription;
    private ActionBar mActionBar;
    private int mOrientation;
    private RecyclerViewMoviesAdapter.MovieOnClickHandler movieOnClickHandler;
    private boolean multiPane = false;


    private StaggeredGridLayoutManager staggeredGridLayoutManager;


    public MainActivityFragment() {
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();


        if (getActivity().findViewById(R.id.multipan) != null) {
            multiPane = true;
            Toast.makeText(getActivity(), "TwoPane", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        //Check if orientation is Landscape or portrate
        mOrientation = getActivity().getResources().getConfiguration().orientation;


        movieOnClickHandler = new RecyclerViewMoviesAdapter.MovieOnClickHandler() {

            @Override
            public void onClick(RecyclerViewMoviesAdapter.MoviesViewHolder moviesViewHolder, MovieResultListModel.ResultModel model, RecyclerViewMoviesAdapter.MoviesViewHolder holder) {
                if (!multiPane) {
                    Intent intent = new Intent(getContext(), MovieDetailActivity.class);
                    intent.putExtra(Utility.EXTRA_RESULT_MODEL, model);
                    ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity());
                    ActivityCompat.startActivity(getActivity(), intent, activityOptionsCompat.toBundle());
                } else {
                    Fragment fragment = new MovieDetailMainFragment();
                    Bundle arg = new Bundle();
                    arg.putParcelable(Utility.EXTRA_RESULT_MODEL, model);
                    fragment.setArguments(arg);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_movie_detail, fragment).commit();
                }

            }
        };

        //Create the layout manager based on orientation
        if (mOrientation == Configuration.ORIENTATION_PORTRAIT) {
            mlayoutManager = new GridLayoutManager(getActivity(), 2);
            staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        } else {
            mlayoutManager = new GridLayoutManager(getActivity(), 3);
            staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1);

        }

        //Setup layout manager to Recycler View
        mRecyclerView.setLayoutManager(mlayoutManager);

        //Restore saved instance
        if (savedInstanceState != null && savedInstanceState.containsKey(Utility.EXTRA_MOVIE)) {
            mPosterList = savedInstanceState.getParcelableArrayList(Utility.EXTRA_MOVIE);
            moviesAdapter = new RecyclerViewMoviesAdapter(getActivity(), mPosterList, movieOnClickHandler);
            moviesAdapter.notifyDataSetChanged();
            mRecyclerView.setAdapter(moviesAdapter);
        }
        //Create a REST service endpoint
        movieApi = ServiceGenerator.createService(MovieApi.class);

        //Call movie API
        callMovieApi(Utility.SORT_POPULAR_DESC);


        return view;
    }


    private void callMovieApi(String sort) {
        movieSubscription = movieApi.lodeMoviesRx(sort, getString(R.string.api_key))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<MovieResultListModel>() {
                    @Override
                    public void onCompleted() {
                        moviesAdapter = new RecyclerViewMoviesAdapter(getActivity(), mPosterList, movieOnClickHandler);
                        moviesAdapter.notifyDataSetChanged();
                        mRecyclerView.setAdapter(moviesAdapter);
                        Log.d(TAG, "Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(MovieResultListModel movieResultListModel) {
                        mPosterList = new ArrayList<>();
                        for (int i = 0; i < movieResultListModel.getResults().size(); i++) {
                            mPosterList.add(movieResultListModel.getResults().get(i));
                            Log.d(TAG, Utility.URL_IMAGE_BASE + movieResultListModel.getResults().get(i).getPosterUrl());
                        }

                    }
                });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.action_sort_popular:
                callMovieApi(Utility.SORT_POPULAR_DESC);
                mActionBar.setTitle(getString(R.string.app_name));
                break;
            case R.id.action_sort_rated:
                callMovieApi(Utility.SORT_RATING_DESC);
                mActionBar.setTitle(getString(R.string.top_rated_movies));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();
        movieSubscription.unsubscribe();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(Utility.EXTRA_MOVIE, mPosterList);
    }

}
