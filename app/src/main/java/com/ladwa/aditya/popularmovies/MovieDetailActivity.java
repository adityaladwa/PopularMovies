package com.ladwa.aditya.popularmovies;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailActivityFragment.onFragmentInteraction {

    @Bind(R.id.toolbar_image_backdrop)
    ImageView imgBackdrop;

    Drawable imgCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_movie_detail);
        setSupportActionBar(toolbar);

    }


    @Override
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void setBackdropImage(String backdropUrl) {
        Glide.with(MovieDetailActivity.this)
                .load(backdropUrl)
                .error(R.drawable.poster)
                .crossFade()
                .centerCrop()
                .into(imgBackdrop);

    }


}
