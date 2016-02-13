package com.ladwa.aditya.popularmovies.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ladwa.aditya.popularmovies.R;
import com.ladwa.aditya.popularmovies.data.model.MovieResultListModel;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {

    @Bind(R.id.viewpager_movie_detail)
    ViewPager viewPager;
    @Bind(R.id.tabs)
    TabLayout tabLayout;
    private boolean twoPane = false;

    MovieResultListModel.ResultModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_movie_detail);
        setSupportActionBar(toolbar);

    }

}
