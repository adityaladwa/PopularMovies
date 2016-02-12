package com.ladwa.aditya.popularmovies.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ladwa.aditya.popularmovies.R;
import com.ladwa.aditya.popularmovies.data.model.MovieResultListModel;
import com.ladwa.aditya.popularmovies.ui.adapter.MovieDetailsPagerAdapter;
import com.ladwa.aditya.popularmovies.ui.fragments.MovieDetailActivityFragment;
import com.ladwa.aditya.popularmovies.util.Utility;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailActivityFragment.onFragmentInteraction {

    @Bind(R.id.toolbar_image_backdrop)
    ImageView imgBackdrop;

    @Bind(R.id.viewpager_movie_detail)
    ViewPager viewPager;
    @Bind(R.id.tabs)
    TabLayout tabLayout;

    MovieResultListModel.ResultModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_movie_detail);
        setSupportActionBar(toolbar);





        model = getIntent().getParcelableExtra(Utility.EXTRA_RESULT_MODEL);

        viewPager.setAdapter(new MovieDetailsPagerAdapter(getSupportFragmentManager(), model));
        tabLayout.setupWithViewPager(viewPager);


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
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgBackdrop);


    }


}
