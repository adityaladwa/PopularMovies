package com.ladwa.aditya.popularmovies;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import com.ladwa.aditya.popularmovies.data.db.MoviDbHelper;

/**
 * Created by Aditya on 14-Feb-16.
 */
public class TestDB extends AndroidTestCase {
    public void testCreateDb() throws Throwable {
        mContext.deleteDatabase(MoviDbHelper.DATABASE_NAME);
        SQLiteDatabase db = new MoviDbHelper(mContext).getWritableDatabase();
        assertEquals(true, db.isOpen());
        db.close();
    }
}
