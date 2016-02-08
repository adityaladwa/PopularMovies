package com.ladwa.aditya.popularmovies.data.api;


import com.ladwa.aditya.popularmovies.data.model.ResultListModel;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Aditya on 03-Feb-16.
 */
public interface MovieApi {


    @GET("discover/movie")
    Observable<ResultListModel> lodeMoviesRx(@Query("sort_by") String sort, @Query("api_key") String apikey);



}

