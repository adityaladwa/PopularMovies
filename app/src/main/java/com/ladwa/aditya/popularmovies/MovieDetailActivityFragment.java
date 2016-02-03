package com.ladwa.aditya.popularmovies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ladwa.aditya.popularmovies.model.ResultModel;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailActivityFragment extends Fragment {

    private static final String LOG_TAG = MovieDetailActivityFragment.class.getSimpleName();

    @Bind(R.id.movietitle)
    TextView tvTitle;
    @Bind(R.id.imageposter)
    ImageView imgPoster;

    public MovieDetailActivityFragment() {
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, view);

        ResultModel model = (ResultModel) EventBus.getDefault().removeStickyEvent(ResultModel.class);
        tvTitle.setText(model.getTitle());

        return view;
    }


}
