package com.ladwa.aditya.popularmovies.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Aditya on 14-Feb-16.
 */
public class MoviDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "movie.db";


    private static final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + MovieContract.Movie.TABLE_NAME + " (" +
            MovieContract.Movie._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MovieContract.Movie.COLUMN_TITLE + " TEXT NOT NULL, " +
            MovieContract.Movie.COLUMN_POSTER_URL + " TEXT NOT NULL, " +
            MovieContract.Movie.COLUMN_BACK_DROP_URL + " TEXT NOT NULL ," +
            MovieContract.Movie.COLUMN_ORIGINAL_TITLE + " TEXT NOT NULL, " +
            MovieContract.Movie.COLUMN_PLOT + " TEXT NOT NULL, " +
            MovieContract.Movie.COLUMN_RATING + " TEXT NOT NULL, " +
            MovieContract.Movie.COLUMN_RELEASE_DATE + " TEXT NOT NULL, " +
            MovieContract.Movie.COLUMN_MOVIE_ID + " TEXT NOT NULL " +
            "UNIQUE (" + MovieContract.Movie.COLUMN_MOVIE_ID + ") ON CONFLICT REPLACE);";

    public static final String SQL_CREATE_VIDEO_TABLE = "CREATE TABLE " + MovieContract.Video.TABLE_NAME + " (" +
            MovieContract.Video._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MovieContract.Video.COLUMN_MOVIE_ID + " TEXT NOT NULL, " +
            MovieContract.Video.COLUMN_VIDEO_URL + " TEXT NOT NULL, " +
            MovieContract.Video.COLUMN_VIDEO_NAME + " TEXT NOT NULL, " +
            MovieContract.Video.COLUMN_VIDEO_ID + " TEXT NOT NULL, " +
            " FOREIGN KEY (" + MovieContract.Video.COLUMN_MOVIE_ID + ") REFERENCES " +
            MovieContract.Movie.TABLE_NAME + " (" + MovieContract.Movie.COLUMN_MOVIE_ID + "), " +
            " UNIQUE (" + MovieContract.Video.COLUMN_VIDEO_ID + ") ON CONFLICT REPLACE);";

    public static final String SQL_CREATE_REVIEW_TABLE = "CREATE TABLE " + MovieContract.Review.TABLE_NAME + " (" +
            MovieContract.Review._ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MovieContract.Review.COLUMN_MOVIE_ID + " TEXT NOT NULL, " +
            MovieContract.Review.COLUMN_REVIEW_ID + " TEXT NOT NULL, " +
            MovieContract.Review.COLUMN_AUTHOR + " TEXT NOT NULL, " +
            MovieContract.Review.COLUMN_CONTENT + " TEXT NOT NULL, " +
            MovieContract.Review.COLUMN_URL + " TEXT NOT NULL, " +
            " FOREIGN KEY (" + MovieContract.Review.COLUMN_MOVIE_ID + ") REFERENCES " +
            MovieContract.Movie.TABLE_NAME + " (" + MovieContract.Movie.COLUMN_MOVIE_ID + "), " +
            " UNIQUE (" + MovieContract.Review.COLUMN_REVIEW_ID + ") ON CONFLICT REPLACE);";

    public MoviDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_MOVIE_TABLE);
        db.execSQL(SQL_CREATE_VIDEO_TABLE);
        db.execSQL(SQL_CREATE_REVIEW_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.Movie.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.Video.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.Review.TABLE_NAME);
        onCreate(db);
    }
}
