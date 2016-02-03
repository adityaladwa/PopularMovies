package com.ladwa.aditya.popularmovies.api;


import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by Aditya on 03-Feb-16.
 */
public class ServiceGenerator {

    public static final String API_BASE_URL = "http://api.themoviedb.org/3/";
    private static OkHttpClient httpClient = new OkHttpClient();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create());


    public static <C> C createService(Class<C> serviceClass) {
        Retrofit retrofit = builder
                .client(httpClient)
                .build();
        return retrofit.create(serviceClass);
    }

}
