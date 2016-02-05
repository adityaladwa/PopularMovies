package com.ladwa.aditya.popularmovies;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ladwa.aditya.popularmovies.adapter.RecyclerViewMoviesAdapter;
import com.ladwa.aditya.popularmovies.api.MovieApi;
import com.ladwa.aditya.popularmovies.api.ServiceGenerator;
import com.ladwa.aditya.popularmovies.model.ResultListModel;
import com.ladwa.aditya.popularmovies.model.ResultModel;

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

    private String TAG = MainActivityFragment.class.getSimpleName();
    private ArrayList<ResultModel> mPosterList;
    private GridLayoutManager mlayoutManager;
    private RecyclerViewMoviesAdapter moviesAdapter;
    private MovieApi movieApi;
    private Subscription movieSubscription;
    private ActionBar mActionBar;
    private int mOrientation;


    public MainActivityFragment() {
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        //Check if orientation is Landscape or portrate
        mOrientation = getActivity().getResources().getConfiguration().orientation;

        //Create the layout manager based on orientation
        if (mOrientation == Configuration.ORIENTATION_PORTRAIT)
            mlayoutManager = new GridLayoutManager(getActivity(), 2);
        else
            mlayoutManager = new GridLayoutManager(getActivity(), 3);

        mRecyclerView.setLayoutManager(mlayoutManager);

        if (savedInstanceState != null) {
            mPosterList = savedInstanceState.getParcelableArrayList(Utility.EXTRA_MOVIE);
            moviesAdapter = new RecyclerViewMoviesAdapter(getActivity(), mPosterList);
            moviesAdapter.notifyDataSetChanged();
            mRecyclerView.setAdapter(moviesAdapter);
        }
        movieApi = ServiceGenerator.createService(MovieApi.class);
        callMovieApi(Utility.SORT_POPULAR_DESC);


        return view;
    }


    private void callMovieApi(String sort) {

//        mPosterList.removeAll(mPosterList);


        movieSubscription = movieApi.lodeMoviesRx(sort, getString(R.string.api_key))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultListModel>() {
                    @Override
                    public void onCompleted() {
                        moviesAdapter = new RecyclerViewMoviesAdapter(getActivity(), mPosterList);
                        moviesAdapter.notifyDataSetChanged();
                        mRecyclerView.setAdapter(moviesAdapter);
                        Log.d(TAG, "Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(ResultListModel resultListModel) {
                        mPosterList = new ArrayList<>();
                        for (int i = 0; i < resultListModel.getResults().size(); i++) {
                            mPosterList.add(resultListModel.getResults().get(i));
                            Log.d(TAG, Utility.URL_IMAGE_BASE + resultListModel.getResults().get(i).getPosterUrl());
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
        outState.putParcelableArrayList(Utility.EXTRA_MOVIE, (ArrayList<? extends Parcelable>) mPosterList);
    }


}
