package org.flauschhaus.traces.journal.entry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

public class JournalEntryOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = JournalEntryOpenHelper.class
            .getSimpleName();

    private static final String DATABASE_NAME = "traces.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "journal";

    static final String _ID = "_id";
    static final String DATE = "date";
    static final String TEXT = "text";
    static final String HIGHLIGHT = "highlight";
    static final String RATING = "rating";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DATE + " INTEGER, "
            + TEXT + " TEXT, "
            + HIGHLIGHT + " TEXT, "
            + RATING + " INTEGER" +
            ");";

    private static final String DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public JournalEntryOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + "; all data will be deleted.");
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void insert(Date date, String text, String highlight, int rating) {
        long rowId = -1;
        try {
            SQLiteDatabase db = getWritableDatabase();
            Log.d(TAG, "Path: " + db.getPath());

            ContentValues values = new ContentValues();
            values.put(DATE, date.getTime());
            values.put(TEXT, text);
            values.put(HIGHLIGHT, highlight);
            values.put(RATING, rating);

            rowId = db.insert(TABLE_NAME, null, values);
        } catch (SQLiteException e) {
            Log.e(TAG, "insert()", e);
        } finally {
            Log.d(TAG, "insert(): rowId=" + rowId);
        }
    }

    public Cursor query() {
        SQLiteDatabase db = getWritableDatabase();
        return db.query(TABLE_NAME,
                null, null, null,
                null, null,
                DATE + " DESC");
    }

    //TODO update delete
}
