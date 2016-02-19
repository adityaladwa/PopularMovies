package com.ladwa.aditya.popularmovies.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Aditya on 03-Feb-16.
 */
public class MovieResultListModel {
    private ArrayList<ResultModel> results;
    private String page;

    public ArrayList<ResultModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<ResultModel> results) {
        this.results = results;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }


    public static class ResultModel implements Parcelable {

        private String title;
        @SerializedName("poster_path")
        private String posterUrl;
        @SerializedName("backdrop_path")
        private String backdropUrl;
        @SerializedName("original_title")
        private String originalTitle;
        @SerializedName("overview")
        private String plot;
        @SerializedName("vote_average")
        private String rating;
        @SerializedName("release_date")
        private String releaseDate;
        @SerializedName("id")
        private String movieId;

        public ResultModel(Parcel in) {
            title = in.readString();
            posterUrl = in.readString();
            backdropUrl = in.readString();
            originalTitle = in.readString();
            plot = in.readString();
            rating = in.readString();
            releaseDate = in.readString();
            movieId = in.readString();
        }

        public ResultModel() {
        }

        public static final Creator<ResultModel> CREATOR = new Creator<ResultModel>() {
            @Override
            public ResultModel createFromParcel(Parcel in) {
                return new ResultModel(in);
            }

            @Override
            public ResultModel[] newArray(int size) {
                return new ResultModel[size];
            }
        };

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }


        public String getPosterUrl() {
            return posterUrl;
        }

        public void setPosterUrl(String posterUrl) {
            this.posterUrl = posterUrl;
        }

        public String getOriginalTitle() {
            return originalTitle;
        }

        public void setOriginalTitle(String originalTitle) {
            this.originalTitle = originalTitle;
        }

        public String getPlot() {
            return plot;
        }

        public void setPlot(String plot) {
            this.plot = plot;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }


        public String getBackdropUrl() {
            return backdropUrl;
        }

        public void setBackdropUrl(String backdropUrl) {
            this.backdropUrl = backdropUrl;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(title);
            dest.writeString(posterUrl);
            dest.writeString(backdropUrl);
            dest.writeString(originalTitle);
            dest.writeString(plot);
            dest.writeString(rating);
            dest.writeString(releaseDate);
            dest.writeString(movieId);
        }

        public String getMovieId() {
            return movieId;
        }

        public void setMovieId(String movieId) {
            this.movieId = movieId;
        }
    }

}
