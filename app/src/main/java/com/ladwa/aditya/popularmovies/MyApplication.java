package com.ladwa.aditya.popularmovies;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by Aditya on 03-Feb-16.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
