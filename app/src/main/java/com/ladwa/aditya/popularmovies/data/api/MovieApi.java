package com.ladwa.aditya.popularmovies.data.api;


import com.ladwa.aditya.popularmovies.data.model.MovieResultListModel;
import com.ladwa.aditya.popularmovies.data.model.MovieReviewListModel;
import com.ladwa.aditya.popularmovies.data.model.MovieVideoListModel;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Aditya on 03-Feb-16.
 */
public interface MovieApi {


    @GET("discover/movie")
    Observable<MovieResultListModel> lodeMoviesRx(@Query("sort_by") String sort, @Query("api_key") String apikey);

    @GET("movie/{id}/videos")
    Observable<MovieVideoListModel> getMovieTrailerRx(@Path("id") String id, @Query("api_key") String apikey);

    @GET("movie/{id}/reviews")
    Observable<MovieReviewListModel> getMovieReviewRx(@Path("id") String id, @Query("api_key") String apikey);

}

