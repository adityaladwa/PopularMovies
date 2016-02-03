package com.ladwa.aditya.popularmovies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ladwa.aditya.popularmovies.adapter.RecyclerViewMoviesAdapter;
import com.ladwa.aditya.popularmovies.api.MovieApi;
import com.ladwa.aditya.popularmovies.api.ServiceGenerator;
import com.ladwa.aditya.popularmovies.model.MoviePosterModel;
import com.ladwa.aditya.popularmovies.model.ResultListModel;

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
    private ArrayList<MoviePosterModel> mPosterList = null;
    private GridLayoutManager mlayoutManager;
    private RecyclerViewMoviesAdapter moviesAdapter;
    private MovieApi movieApi;
    private Subscription movieSubscription;
    private static final String URL_IMAGE_BASE = "http://image.tmdb.org/t/p/w185/";

    private static final String SORT_POPULAR_DESC = "popularity.desc";


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        //  Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT).show();


        mPosterList = new ArrayList<>();


        mlayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mlayoutManager);


        movieApi = ServiceGenerator.createService(MovieApi.class);

        movieSubscription = movieApi.lodeMoviesRx(SORT_POPULAR_DESC, getString(R.string.api_key))
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

                        for (int i = 0; i < resultListModel.getResults().size(); i++) {
                            String url = URL_IMAGE_BASE + resultListModel.getResults().get(i).getPoster_path();
                            MoviePosterModel moviePosterModel = new MoviePosterModel(url);
                            mPosterList.add(moviePosterModel);
                            Log.d(TAG, URL_IMAGE_BASE + resultListModel.getResults().get(i).getPoster_path());

                        }
                    }
                });


        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        movieSubscription.unsubscribe();
    }
}
