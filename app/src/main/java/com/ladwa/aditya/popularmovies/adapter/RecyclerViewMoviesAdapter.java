package com.ladwa.aditya.popularmovies.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ladwa.aditya.popularmovies.MovieDetailActivity;
import com.ladwa.aditya.popularmovies.R;
import com.ladwa.aditya.popularmovies.model.MoviePosterModel;
import com.ladwa.aditya.popularmovies.model.ResultModel;

import org.parceler.Parcels;

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
        Glide.with(mContext).load(mModelurl.get(position).getImageUrl()).fitCenter().crossFade().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mModelurl.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.imageview_poster)
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "Clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(mContext, MovieDetailActivity.class);

            mContext.startActivity(intent);
        }
    }
}
