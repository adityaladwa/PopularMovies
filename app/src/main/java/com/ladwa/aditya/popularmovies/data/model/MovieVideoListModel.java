package com.ladwa.aditya.popularmovies.data.model;

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


    public static class VideoModel {
        @SerializedName("id")
        private String videoID;
        private String key;
        private String name;
        private String site;
        private String type;


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
    }
}
