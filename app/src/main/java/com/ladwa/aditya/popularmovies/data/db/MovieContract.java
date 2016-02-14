package com.ladwa.aditya.popularmovies.data.db;

import android.provider.BaseColumns;

/**
 * Created by Aditya on 14-Feb-16.
 */
public class MovieContract {
    public static final class Movie implements BaseColumns {
        public static final String TABLE_NAME = "movie";
        public static final String _ID = "id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_POSTER_URL = "poster_url";
        public static final String COLUMN_BACK_DROP_URL = "backdrop_url";
        public static final String COLUMN_ORIGINAL_TITLE = "original_title";
        public static final String COLUMN_PLOT = "plot";
        public static final String COLUMN_RATING = "rating";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        public static final String COLUMN_MOVIE_ID = "movie_id";
    }

    public static final class Video implements BaseColumns {
        public static final String TABLE_NAME = "video";
        public static final String _ID = "vid";
        public static final String COLUMN_MOVIE_ID = "movie_id";
        public static final String COLUMN_VIDEO_URL = "key";
        public static final String COLUMN_VIDEO_NAME = "name";
        public static final String COLUMN_VIDEO_ID = "video_id";
    }

    public static final class Review implements BaseColumns {
        public static final String TABLE_NAME = "review";
        public static final String _ID = "rid";
        public static final String COLUMN_MOVIE_ID = "movie_id";
        public static final String COLUMN_REVIEW_ID = "review_id";
        public static final String COLUMN_AUTHOR = "author";
        public static final String COLUMN_CONTENT = "content";
        public static final String COLUMN_URL = "url";
    }
}
