package com.example.umasubbiah.habittracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.umasubbiah.habittracker.HabitContract.HabitEntry;

public class HabitDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = HabitDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "HabitTrackerApp.db";
    private static final int DATABASE_VERSION = 1;

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_HABITS_TABLE = "CREATE TABLE " + HabitContract.HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_DATE + " TEXT NOT NULL,"
                + HabitEntry.COLUMN_DAY + " TEXT NOT NULL,"
                + HabitEntry.COLUMN_HABIT + " TEXT NOT NULL,"
                + HabitEntry.COLUMN_FREQUENCY + " INTEGER NOT NULL DEFAULT 0,"
                + HabitEntry.COLUMN_REMARKS + " TEXT);";

       //To log the statement and verify it has been created:
        Log.e(LOG_TAG, "Table created " + SQL_CREATE_HABITS_TABLE);
        // Execute the SQL statement
        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }


    public void insertHabit(String date, String day, String habitName, int frequency, String remarks) {
        // Gets the database in write mode


        SQLiteDatabase db = getWritableDatabase();

        // Create a ContentValues object where column names are the keys:
        ContentValues values = new ContentValues();

        values.put(HabitEntry.COLUMN_DATE, date);
        values.put(HabitEntry.COLUMN_DAY, day);
        values.put(HabitEntry.COLUMN_HABIT, habitName);
        values.put(HabitEntry.COLUMN_FREQUENCY, frequency);
        values.put(HabitEntry.COLUMN_REMARKS, remarks);

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
    }


}
