package com.ladwa.aditya.popularmovies.ui.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.ladwa.aditya.popularmovies.data.model.ResultListModel.ResultModel;
import com.ladwa.aditya.popularmovies.ui.MovieDetailActivity;
import com.ladwa.aditya.popularmovies.util.Utility;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

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
        String title = mresultListModel.get(position).getTitle();
        holder.textViewTitle.setText(title);
        Glide.with(holder.imageView.getContext())
                .load(url)
                .error(R.drawable.poster)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return mresultListModel.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.imageview_poster)
        ImageView imageView;
        @Bind(R.id.card_movietitle)
        TextView textViewTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, MovieDetailActivity.class);
            Log.d("Clicked ", mresultListModel.get(getAdapterPosition()).getTitle());
            intent.putExtra(Utility.EXTRA_RESULT_MODEL, mresultListModel.get(getAdapterPosition()));
            mContext.startActivity(intent);
        }
    }
}
