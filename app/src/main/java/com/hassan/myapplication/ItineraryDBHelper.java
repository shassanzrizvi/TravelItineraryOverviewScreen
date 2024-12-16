package com.hassan.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ItineraryDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "itinerary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "itineraries";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DESTINATION = "destination";
    private static final String COLUMN_DATES = "dates";
    private static final String COLUMN_DESCRIPTION = "description";

    public ItineraryDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_DESTINATION + " TEXT, "
                + COLUMN_DATES + " TEXT, "
                + COLUMN_DESCRIPTION + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Cursor getItineraries() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}
