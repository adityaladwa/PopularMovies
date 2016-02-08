package com.ladwa.aditya.popularmovies.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ladwa.aditya.popularmovies.ui.fragments.MovieDetailActivityFragment;
import com.ladwa.aditya.popularmovies.util.Utility;

/**
 * Created by Aditya on 08-Feb-16.
 */
public class MovieDetailsPagerAdapter extends FragmentPagerAdapter {

    public MovieDetailsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new MovieDetailActivityFragment();
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
