package com.ladwa.aditya.popularmovies.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ladwa.aditya.popularmovies.data.model.MovieResultListModel;
import com.ladwa.aditya.popularmovies.ui.fragments.MovieDetailActivityFragment;
import com.ladwa.aditya.popularmovies.ui.fragments.ReviewsFragment;
import com.ladwa.aditya.popularmovies.ui.fragments.TrailerFragment;
import com.ladwa.aditya.popularmovies.util.Utility;

/**
 * Created by Aditya on 08-Feb-16.
 */
public class MovieDetailsPagerAdapter extends FragmentStatePagerAdapter {

    private MovieResultListModel.ResultModel resultModel;

    public MovieDetailsPagerAdapter(FragmentManager fm, MovieResultListModel.ResultModel model) {
        super(fm);
        this.resultModel = model;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                MovieDetailActivityFragment detailActivityFragment = new MovieDetailActivityFragment();
                Bundle arg = new Bundle();
                arg.putParcelable(Utility.EXTRA_DETAIL_FRAGMENT, resultModel);
                detailActivityFragment.setArguments(arg);
                return detailActivityFragment;

            case 1:
                TrailerFragment trailerFragment = new TrailerFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable(Utility.EXTRA_TRAILER_FRAGMENT, resultModel);
                trailerFragment.setArguments(bundle);
                return trailerFragment;

            case 2:
                ReviewsFragment reviewsFragment = new ReviewsFragment();
                Bundle args = new Bundle();
                args.putParcelable(Utility.EXTRA_REVIEW_FRAGMENT, resultModel);
                reviewsFragment.setArguments(args);
                return reviewsFragment;

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
