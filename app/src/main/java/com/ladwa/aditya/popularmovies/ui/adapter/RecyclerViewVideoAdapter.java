package com.ladwa.aditya.popularmovies.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ladwa.aditya.popularmovies.R;
import com.ladwa.aditya.popularmovies.data.model.MovieVideoListModel;
import com.ladwa.aditya.popularmovies.util.Utility;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Aditya on 10-Feb-16.
 */
public class RecyclerViewVideoAdapter extends RecyclerView.Adapter<RecyclerViewVideoAdapter.VideoViewHolder> {

    private static final String LOG_TAG = RecyclerViewVideoAdapter.class.getSimpleName();

    private ArrayList<MovieVideoListModel.VideoModel> mVideoList;
    private Context mContext;

    public RecyclerViewVideoAdapter(ArrayList<MovieVideoListModel.VideoModel> mVideoList, Context mContext) {
        this.mVideoList = mVideoList;
        this.mContext = mContext;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_trailer_item, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {

        String url = Utility.YOUTUBE_THUMBNAIL_URL_BASE + mVideoList.get(position).getKey() + "/default.jpg";
        String title = mVideoList.get(position).getName();
        holder.textViewTrailerTitle.setText(title);
        Glide.with(holder.imageViewThumbnails.getContext())
                .load(url)
                .error(R.drawable.poster)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageViewThumbnails);
    }

    @Override
    public int getItemCount() {
        if (mVideoList != null)
            return mVideoList.size();
        else
            return 0;
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.imageview_trailer_thumbnail)
        ImageView imageViewThumbnails;
        @Bind(R.id.textview_trailername)
        TextView textViewTrailerTitle;

        public VideoViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {
            String vId = mVideoList.get(getAdapterPosition()).getKey();
            String url = Utility.YOUTUBE_PLAYER_URL_BASE + vId;
            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            Log.d(LOG_TAG, "Playing video with URL " + url);
        }
    }
}
