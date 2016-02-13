package com.ladwa.aditya.popularmovies.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ladwa.aditya.popularmovies.R;
import com.ladwa.aditya.popularmovies.data.model.MovieResultListModel;
import com.ladwa.aditya.popularmovies.ui.adapter.MovieDetailsPagerAdapter;
import com.ladwa.aditya.popularmovies.util.Utility;

import butterknife.ButterKnife;

/**
 * Created by Aditya on 12-Feb-16.
 */
public class DetaiActivityFragment extends Fragment {

    ImageView imgPoster;

    private MovieResultListModel.ResultModel model;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private boolean fromactivity = true;
    private boolean twoPane = false;


    public DetaiActivityFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        imgPoster = (ImageView) getActivity().findViewById(R.id.toolbar_image_backdrop);
        if (getActivity().findViewById(R.id.multipan) != null)
            twoPane = true;


        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(model.getOriginalTitle());

        try {
            setViewpagerAndBackDrop();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    private void setViewpagerAndBackDrop() {


        viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager_movie_detail);
        tabLayout = (TabLayout) getActivity().findViewById(R.id.tabs);

        MovieDetailsPagerAdapter pagerAdapter = new MovieDetailsPagerAdapter(getActivity().getSupportFragmentManager(), model);
        pagerAdapter.notifyDataSetChanged();

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        Glide.with(DetaiActivityFragment.this)
                .load(Utility.URL_IMAGE_BACKDROP_BASE + model.getBackdropUrl())
                .error(R.drawable.poster)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgPoster);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_main, container, false);
        ButterKnife.bind(this, view);


        model = getActivity().getIntent().getParcelableExtra(Utility.EXTRA_RESULT_MODEL);

        if (model == null) {
            model = this.getArguments().getParcelable(Utility.EXTRA_RESULT_MODEL);
            fromactivity = false;
        }


        return view;

    }


}
