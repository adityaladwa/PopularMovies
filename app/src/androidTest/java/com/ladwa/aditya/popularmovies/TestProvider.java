package com.ladwa.aditya.popularmovies;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.util.Log;

import com.ladwa.aditya.popularmovies.data.db.MoviDbHelper;
import com.ladwa.aditya.popularmovies.data.db.MovieContract;

/**
 * Created by Aditya on 16-Feb-16.
 */
public class TestProvider extends AndroidTestCase {
    public static final String LOG_TAG = TestProvider.class.getSimpleName();

    public void testDeleteAllRecords() throws Throwable {
        mContext.getContentResolver().delete(MovieContract.Movie.CONTENT_URI,
                null,
                null
        );

        Cursor cursor = mContext.getContentResolver().query(MovieContract.Movie.CONTENT_URI,
                null,
                null,
                null,
                null
        );
        assertEquals(cursor.getCount(), 0);
        cursor.close();
    }

    public void testInsertProvider() throws Throwable {
        SQLiteDatabase db = new MoviDbHelper(mContext).getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(MovieContract.Movie.COLUMN_TITLE, "The Revenant");
        values.put(MovieContract.Movie.COLUMN_POSTER_URL, "/oXUWEc5i3wYyFnL1Ycu8ppxxPvs.jpg");
        values.put(MovieContract.Movie.COLUMN_BACK_DROP_URL, "/uS1SkjVviraGfFNgkDwe7ohTm8B.jpg");
        values.put(MovieContract.Movie.COLUMN_ORIGINAL_TITLE, "The Revenant");
        values.put(MovieContract.Movie.COLUMN_PLOT, "In the 1820s, a frontiersman, Hugh Glass, sets out on a path of vengeance against those who left him for dead after a bear mauling.");
        values.put(MovieContract.Movie.COLUMN_RATING, "7.26");
        values.put(MovieContract.Movie.COLUMN_RELEASE_DATE, "2015-12-25");
        values.put(MovieContract.Movie.COLUMN_MOVIE_ID, "281952");

        Uri rowUri;
        rowUri = mContext.getContentResolver().insert(MovieContract.Movie.CONTENT_URI, values);
        long rowId = ContentUris.parseId(rowUri);

        Log.d(LOG_TAG, "New row id inserted via provider: " + rowId);

        ContentValues values1 = new ContentValues(values);
        values1.put(MovieContract.Movie.COLUMN_MOVIE_ID, "281953");
        values1.put(MovieContract.Movie.COLUMN_TITLE, "The Revenant1");

        int count = mContext.getContentResolver().update(MovieContract.Movie.CONTENT_URI, values1, MovieContract.Movie.COLUMN_MOVIE_ID + "= ?", new String[]{"281952"});

        assertEquals(count, 1);

        String[] colums = {
                MovieContract.Movie._ID,
                MovieContract.Movie.COLUMN_TITLE,
                MovieContract.Movie.COLUMN_POSTER_URL,
                MovieContract.Movie.COLUMN_BACK_DROP_URL,
                MovieContract.Movie.COLUMN_ORIGINAL_TITLE,
                MovieContract.Movie.COLUMN_PLOT,
                MovieContract.Movie.COLUMN_RATING,
                MovieContract.Movie.COLUMN_RELEASE_DATE,
                MovieContract.Movie.COLUMN_MOVIE_ID
        };


        Cursor c1 = mContext.getContentResolver().query(
                MovieContract.Movie.CONTENT_URI,
                null,
                null,
                null,
                null
        );

        Cursor c = mContext.getContentResolver().query(
                MovieContract.Movie.buildMoviewithId("281952"),
                null,
                null,
                null,
                null
        );

        Log.d(LOG_TAG, String.valueOf(c1.getCount()));

        while (c.moveToNext()) {
            c.getString(c.getColumnIndex(MovieContract.Movie.COLUMN_TITLE));
            c.getString(c.getColumnIndex(MovieContract.Movie.COLUMN_POSTER_URL));
            c.getString(c.getColumnIndex(MovieContract.Movie.COLUMN_BACK_DROP_URL));
            c.getString(c.getColumnIndex(MovieContract.Movie.COLUMN_ORIGINAL_TITLE));
            c.getString(c.getColumnIndex(MovieContract.Movie.COLUMN_PLOT));
            String id = c.getString(c.getColumnIndex(MovieContract.Movie.COLUMN_MOVIE_ID));
            Log.d(LOG_TAG, " Movie id " + id);

            assertEquals("281952", id);

        }
    }

    public void testGetType() throws Throwable {

        String type = mContext.getContentResolver().getType(MovieContract.Movie.CONTENT_URI);
        assertEquals(MovieContract.Movie.CONTENT_TYPE, type);

        Log.d(LOG_TAG, type);

        String testMovieID = "281952";
        type = mContext.getContentResolver().getType(MovieContract.Movie.buildMoviewithId(testMovieID));
        assertEquals(MovieContract.Movie.CONTENT_ITEM_TYPE, type);

        Log.d(LOG_TAG, type);


    }
}
