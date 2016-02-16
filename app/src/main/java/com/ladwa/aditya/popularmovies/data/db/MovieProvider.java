package com.ladwa.aditya.popularmovies.data.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Aditya on 16-Feb-16.
 */
public class MovieProvider extends ContentProvider {

    public static final int MOVIE = 100;
    public static final int MOVIE_WITH_ID = 101;

    public static final int VIDEO_WITH_MOVIE_ID = 200;
    public static final int VIDEO_WITH_VIDEO_ID = 201;

    public static final int REVIEW_WITH_MOVIE_ID = 300;
    public static final int REVIEW_WITH_VIDEO_ID = 301;

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private MoviDbHelper mOpenHelper;


    private static UriMatcher buildUriMatcher() {

        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = MovieContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, MovieContract.PATH_MOVIE, MOVIE);
        matcher.addURI(authority, MovieContract.PATH_MOVIE + "/*", MOVIE_WITH_ID);

        matcher.addURI(authority, MovieContract.PATH_VIDEO + "/*", VIDEO_WITH_MOVIE_ID);
        matcher.addURI(authority, MovieContract.PATH_VIDEO + "/*", VIDEO_WITH_VIDEO_ID);

        matcher.addURI(authority, MovieContract.PATH_REVIEW + "/*", REVIEW_WITH_MOVIE_ID);
        matcher.addURI(authority, MovieContract.PATH_REVIEW + "/*", REVIEW_WITH_VIDEO_ID);

        return matcher;
    }


    @Override
    public boolean onCreate() {
        mOpenHelper = new MoviDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case MOVIE:
                return MovieContract.Movie.CONTENT_TYPE;
            case MOVIE_WITH_ID:
                return MovieContract.Movie.CONTENT_ITEM_TYPE;
            case VIDEO_WITH_MOVIE_ID:
                return MovieContract.Video.CONTENT_ITEM_TYPE;
            case VIDEO_WITH_VIDEO_ID:
                return MovieContract.Video.CONTENT_TYPE;
            case REVIEW_WITH_MOVIE_ID:
                return MovieContract.Review.CONTENT_ITEM_TYPE;
            case REVIEW_WITH_VIDEO_ID:
                return MovieContract.Review.CONTENT_TYPE;

            default:
                throw new UnsupportedOperationException("Unknown Uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
