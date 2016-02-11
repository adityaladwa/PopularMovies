package com.ladwa.aditya.popularmovies.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ladwa.aditya.popularmovies.data.model.MovieResultListModel;
import com.ladwa.aditya.popularmovies.ui.fragments.MovieDetailActivityFragment;
import com.ladwa.aditya.popularmovies.ui.fragments.MovieReviewsFragment;
import com.ladwa.aditya.popularmovies.ui.fragments.MovieTrailerFragment;
import com.ladwa.aditya.popularmovies.util.Utility;

/**
 * Created by Aditya on 08-Feb-16.
 */
public class MovieDetailsPagerAdapter extends FragmentPagerAdapter {

    private MovieResultListModel.ResultModel resultModel;

    public MovieDetailsPagerAdapter(FragmentManager fm, MovieResultListModel.ResultModel model) {
        super(fm);
        this.resultModel = model;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MovieDetailActivityFragment();

            case 1:
                MovieTrailerFragment trailerFragment = new MovieTrailerFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("movie", resultModel);
                trailerFragment.setArguments(bundle);
                return trailerFragment;

            case 2:
                return new MovieReviewsFragment();

            default:
                return new Fragment();

        }
    }

    @Override
    public int getCount() {
        return Utility.TABS_MOVIE_DETAIL.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position >= 0 && position < Utility.TABS_MOVIE_DETAIL.length) {
            return Utility.TABS_MOVIE_DETAIL[position];
        }
        return null;
    }
}
