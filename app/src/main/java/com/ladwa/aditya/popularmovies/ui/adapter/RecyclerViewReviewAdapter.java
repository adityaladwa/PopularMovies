package com.ladwa.aditya.popularmovies.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ladwa.aditya.popularmovies.R;
import com.ladwa.aditya.popularmovies.data.model.MovieReviewListModel;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Aditya on 11-Feb-16.
 */
public class RecyclerViewReviewAdapter extends RecyclerView.Adapter<RecyclerViewReviewAdapter.ReviewViewHolder> {

    private static final String LOG_TAG = RecyclerViewReviewAdapter.class.getSimpleName();

    private ArrayList<MovieReviewListModel.ReviewModel> mReviewList;
    private Context mContext;

    public RecyclerViewReviewAdapter(ArrayList<MovieReviewListModel.ReviewModel> mReviewList, Context mContext) {
        this.mReviewList = mReviewList;
        this.mContext = mContext;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_review_item, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {

        String author = mReviewList.get(position).getAuthor();
        String content = mReviewList.get(position).getContent();
        String link = mReviewList.get(position).getUrl();


        holder.tvAuthor.setText(author);
        holder.tvContent.setText(content);
        holder.tvLink.setText(link);

        Linkify.addLinks(holder.tvLink, Linkify.WEB_URLS);

    }

    @Override
    public int getItemCount() {
        return mReviewList.size();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.textview_review_author)
        TextView tvAuthor;
        @Bind(R.id.textview_review_content)
        TextView tvContent;
        @Bind(R.id.textview_review_link)
        TextView tvLink;

        public ReviewViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }


    }
}
