package com.ladwa.aditya.popularmovies.api;


import com.ladwa.aditya.popularmovies.model.ResultListModel;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Aditya on 03-Feb-16.
 */
public interface MovieApi {


    @GET("discover/movie?sort_by=popularity.desc&api_key=a7cbde4ab474c71f23ea707e454e4b64")
    Call<ResultListModel> lodeMoviesTest();


    @GET("discover/movie")
    Observable<ResultListModel> lodeMoviesRx(@Query("sort_by") String sort, @Query("api_key") String apikey);

}

