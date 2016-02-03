package com.ladwa.aditya.popularmovies.adapter;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ladwa.aditya.popularmovies.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Aditya on 03-Feb-16.
 */
public class RecyclerViewMoviesAdapter extends RecyclerView.Adapter<RecyclerViewMoviesAdapter.MyViewHolder> {

    public RecyclerViewMoviesAdapter() {
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageview_poster)
        private ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
