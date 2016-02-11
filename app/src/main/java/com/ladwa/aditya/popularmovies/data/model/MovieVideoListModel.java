package com.ladwa.aditya.popularmovies.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Aditya on 08-Feb-16.
 */
public class MovieVideoListModel {

    private String id;
    private ArrayList<VideoModel> results;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<VideoModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<VideoModel> results) {
        this.results = results;
    }


    public static class VideoModel implements Parcelable {
        @SerializedName("id")
        private String videoID;
        private String key;
        private String name;
        private String site;
        private String type;


        protected VideoModel(Parcel in) {
            videoID = in.readString();
            key = in.readString();
            name = in.readString();
            site = in.readString();
            type = in.readString();
        }

        public static final Creator<VideoModel> CREATOR = new Creator<VideoModel>() {
            @Override
            public VideoModel createFromParcel(Parcel in) {
                return new VideoModel(in);
            }

            @Override
            public VideoModel[] newArray(int size) {
                return new VideoModel[size];
            }
        };

        public String getVideoID() {
            return videoID;
        }

        public void setVideoID(String videoID) {
            this.videoID = videoID;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(videoID);
            dest.writeString(key);
            dest.writeString(name);
            dest.writeString(site);
            dest.writeString(type);
        }
    }
}
