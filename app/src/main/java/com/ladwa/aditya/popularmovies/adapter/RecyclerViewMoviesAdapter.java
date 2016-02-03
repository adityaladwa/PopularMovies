package com.ladwa.aditya.popularmovies.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ladwa.aditya.popularmovies.MovieDetailActivity;
import com.ladwa.aditya.popularmovies.R;
import com.ladwa.aditya.popularmovies.Utility;
import com.ladwa.aditya.popularmovies.model.ResultModel;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by Aditya on 03-Feb-16.
 */
public class RecyclerViewMoviesAdapter extends RecyclerView.Adapter<RecyclerViewMoviesAdapter.MyViewHolder> {

    private ArrayList<ResultModel> mresultListModel;
    private Context mContext;

    public RecyclerViewMoviesAdapter(Context context, ArrayList<ResultModel> model) {
        this.mresultListModel = model;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String url = Utility.URL_IMAGE_BASE + mresultListModel.get(position).getPosterUrl();
        Glide.with(mContext).load(url).fitCenter().crossFade().into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mresultListModel.size();
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
            // Toast.makeText(mContext, "Clicked " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(mContext, MovieDetailActivity.class);
            EventBus.getDefault().postSticky(mresultListModel.get(getAdapterPosition()));
            Log.d("Clicked ", mresultListModel.get(getAdapterPosition()).getTitle());
            mContext.startActivity(intent);
        }
    }
}
