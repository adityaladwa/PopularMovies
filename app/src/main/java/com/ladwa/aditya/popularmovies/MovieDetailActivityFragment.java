package com.ladwa.aditya.popularmovies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
    @Bind(R.id.releasedate)
    TextView tvReleaseDate;
    @Bind(R.id.rating)
    TextView tvRating;
    @Bind(R.id.plot)
    TextView tvPlot;

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
        tvTitle.setText(model.getOriginalTitle());
        tvReleaseDate.setText(String.format(getString(R.string.release_date), model.getReleaseDate()));
        tvRating.setText(String.format(getString(R.string.rating), model.getRating()));
        tvPlot.setText(model.getPlot());
        Glide.with(this).load(Utility.URL_IMAGE_BASE + model.getPosterUrl()).crossFade().fitCenter().into(imgPoster);

        return view;
    }


}
