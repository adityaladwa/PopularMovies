package com.ladwa.aditya.popularmovies.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Aditya on 11-Feb-16.
 */
public class MovieReviewListModel {

    private String id;
    private String page;
    private ArrayList<ReviewModel> results;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public ArrayList<ReviewModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<ReviewModel> results) {
        this.results = results;
    }

    public static class ReviewModel implements Parcelable {

        @SerializedName("id")
        private String reviewId;
        private String author;
        private String content;
        private String url;

        protected ReviewModel(Parcel in) {
            reviewId = in.readString();
            author = in.readString();
            content = in.readString();
            url = in.readString();
        }

        public static final Creator<ReviewModel> CREATOR = new Creator<ReviewModel>() {
            @Override
            public ReviewModel createFromParcel(Parcel in) {
                return new ReviewModel(in);
            }

            @Override
            public ReviewModel[] newArray(int size) {
                return new ReviewModel[size];
            }
        };

        public String getReviewId() {
            return reviewId;
        }

        public void setReviewId(String reviewId) {
            this.reviewId = reviewId;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(reviewId);
            dest.writeString(author);
            dest.writeString(content);
            dest.writeString(url);
        }
    }
}
