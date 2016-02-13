package com.ladwa.aditya.popularmovies.data.api;


import com.facebook.stetho.okhttp.StethoInterceptor;
import com.ladwa.aditya.popularmovies.util.Utility;
import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by Aditya on 03-Feb-16.
 */
public class ServiceGenerator {


    private static OkHttpClient httpClient = new OkHttpClient();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(Utility.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create());


    public static <C> C createService(Class<C> serviceClass) {
        httpClient.networkInterceptors().add(new StethoInterceptor());

        Retrofit retrofit = builder
                .client(httpClient)
                .build();
        return retrofit.create(serviceClass);
    }

}
