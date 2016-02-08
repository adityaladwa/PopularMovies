package com.ladwa.aditya.popularmovies.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ladwa.aditya.popularmovies.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Aditya on 08-Feb-16.
 */
public class MovieTrailerFragment extends Fragment {

    @Bind(R.id.plot)
    TextView textViewPlot;

    public MovieTrailerFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plot, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
}
