package com.ladwa.aditya.popularmovies;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

import com.ladwa.aditya.popularmovies.data.db.MoviDbHelper;
import com.ladwa.aditya.popularmovies.data.db.MovieContract;

/**
 * Created by Aditya on 14-Feb-16.
 */
public class TestDB extends AndroidTestCase {
    public static final String LOG_TAG = TestDB.class.getSimpleName();

    public void testCreateDb() throws Throwable {
        mContext.deleteDatabase(MoviDbHelper.DATABASE_NAME);
        SQLiteDatabase db = new MoviDbHelper(mContext).getWritableDatabase();
        assertEquals(true, db.isOpen());
        db.close();
    }


    public void testInsertDb() throws Throwable {
        SQLiteDatabase db = new MoviDbHelper(mContext).getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(MovieContract.Movie.COLUMN_TITLE, "The Revenant");
        values.put(MovieContract.Movie.COLUMN_POSTER_URL, "/oXUWEc5i3wYyFnL1Ycu8ppxxPvs.jpg");
        values.put(MovieContract.Movie.COLUMN_BACK_DROP_URL, "/uS1SkjVviraGfFNgkDwe7ohTm8B.jpg");
        values.put(MovieContract.Movie.COLUMN_ORIGINAL_TITLE, "The Revenant");
        values.put(MovieContract.Movie.COLUMN_PLOT, "In the 1820s, a frontiersman, Hugh Glass, sets out on a path of vengeance against those who left him for dead after a bear mauling.");
        values.put(MovieContract.Movie.COLUMN_RATING, "7.26");
        values.put(MovieContract.Movie.COLUMN_RELEASE_DATE, "2015-12-25");
        values.put(MovieContract.Movie.COLUMN_MOVIE_ID, "2819527");

        long rowId;
        rowId = db.insert(MovieContract.Movie.TABLE_NAME, null, values);

        assertTrue(rowId != -1);
        Log.d(LOG_TAG, "New row id: " + rowId);
    }
}
