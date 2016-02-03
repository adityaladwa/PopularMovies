package com.ladwa.aditya.popularmovies.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ladwa.aditya.popularmovies.R;
import com.ladwa.aditya.popularmovies.model.MoviePosterModel;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Aditya on 03-Feb-16.
 */
public class RecyclerViewMoviesAdapter extends RecyclerView.Adapter<RecyclerViewMoviesAdapter.MyViewHolder> {

    private ArrayList<MoviePosterModel> mModelurl;
    private Context mContext;

    public RecyclerViewMoviesAdapter(Context context, ArrayList<MoviePosterModel> model) {
        this.mModelurl = model;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(mContext).load(mModelurl.get(0).getImageUrl()).into(holder.imageView);
       // Glide.with(this).load("http://www.planwallpaper.com/static/images/Winter-Tiger-Wild-Cat-Images.jpg").into(mimageView);
        Log.d("onBindViewHolder", "called-" + mModelurl.get(0).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return mModelurl.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageview_poster)
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
