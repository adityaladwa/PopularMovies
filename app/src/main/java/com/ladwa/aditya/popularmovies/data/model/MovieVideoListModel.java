package com.ladwa.aditya.popularmovies.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Aditya on 08-Feb-16.
 */
public class MovieVideoListModel {

    private String id;
    private ArrayList<VideoModel> results;

    public static class VideoModel {
        @SerializedName("id")
        private String videoID;
        private String key;
        private String name;
        private String site;
        private String type;
    }
}
