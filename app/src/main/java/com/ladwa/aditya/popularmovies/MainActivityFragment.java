package com.ladwa.aditya.popularmovies;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ladwa.aditya.popularmovies.adapter.RecyclerViewMoviesAdapter;
import com.ladwa.aditya.popularmovies.api.MovieApi;
import com.ladwa.aditya.popularmovies.api.ServiceGenerator;
import com.ladwa.aditya.popularmovies.model.MoviePosterModel;
import com.ladwa.aditya.popularmovies.model.ResultListModel;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import rx.Observer;
import rx.Scheduler;
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

    private static final String SORT_POPULAR_DESC = "popularity.desc";


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
      //  Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT).show();

        MoviePosterModel moviePosterModel = new MoviePosterModel();
        moviePosterModel.setImageUrl("http://www.planwallpaper.com/static/images/Winter-Tiger-Wild-Cat-Images.jpg");
        mPosterList = new ArrayList<>();
        mPosterList.add(moviePosterModel);

        mlayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mlayoutManager);
        moviesAdapter = new RecyclerViewMoviesAdapter(getActivity(), mPosterList);
        moviesAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(moviesAdapter);

        movieApi = ServiceGenerator.createService(MovieApi.class);

        movieSubscription = movieApi.lodeMoviesRxTest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultListModel>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(ResultListModel resultListModel) {
                        Log.d(TAG, resultListModel.getResults().get(0).getTitle());
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
