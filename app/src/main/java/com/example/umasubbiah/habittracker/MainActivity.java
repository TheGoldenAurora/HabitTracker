package com.example.umasubbiah.habittracker;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.umasubbiah.habittracker.HabitContract.HabitEntry;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new HabitDbHelper(this);


        Date dateValue = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
        String dateString = formatter.format(dateValue);

        //Writing dummy data to the database:-
        mDbHelper.insertHabit(dateString, "Monday", "Walking the dog" , HabitEntry.FREQ_DAILY, "30 minute walk to the park");
        mDbHelper.insertHabit(dateString, "Tuesday", "Cycling with friends" , HabitEntry.FREQ_WEEKLY, "30 minute walk to the park");
        mDbHelper.insertHabit(dateString, "Wednesday", "Iron supplements" , HabitEntry.FREQ_MONTHLY, "30 minute walk to the park");

    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    public void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                HabitContract.HabitEntry._ID,
                HabitContract.HabitEntry.COLUMN_DATE,
                HabitContract.HabitEntry.COLUMN_DAY,
                HabitContract.HabitEntry.COLUMN_HABIT,
                HabitContract.HabitEntry.COLUMN_FREQUENCY,
                HabitContract.HabitEntry.COLUMN_REMARKS
        };

        // Perform a query on the pets table
        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        TextView displayView = (TextView) findViewById(R.id.text_view_habit);

        try {
            // Create a header in the Text View that looks like this:
            //
            // The habits table contains <number of rows in Cursor> pets.
            // _id - name - breed - gender - weight
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The Habit Tracker table currently contains " + cursor.getCount() + " habit entries.\n\n");
            displayView.append(HabitEntry._ID + " - " +
                    HabitEntry.COLUMN_DATE + " - " +
                    HabitEntry.COLUMN_DAY + " - " +
                    HabitEntry.COLUMN_HABIT + " - " +
                    HabitEntry.COLUMN_FREQUENCY + " - " +
                    HabitEntry.COLUMN_REMARKS + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int dateColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_DATE);
            int dayColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_DAY);
            int habitColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT);
            int freqColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_FREQUENCY);
            int remarksColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_REMARKS);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentDate = cursor.getString(dateColumnIndex);
                String currentDay = cursor.getString(dayColumnIndex);
                String currentHabit = cursor.getString(habitColumnIndex);
                int currentFreq = cursor.getInt(freqColumnIndex);
                String currentRemarks = cursor.getString(remarksColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentDate + " - " +
                        currentDay + " - " +
                        currentHabit + " - " +
                        currentFreq + " - " +
                        currentRemarks));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    private Cursor readData(){
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitContract.HabitEntry._ID,
                HabitContract.HabitEntry.COLUMN_DATE,
                HabitContract.HabitEntry.COLUMN_DAY,
                HabitContract.HabitEntry.COLUMN_HABIT,
                HabitContract.HabitEntry.COLUMN_FREQUENCY,
                HabitContract.HabitEntry.COLUMN_REMARKS
        };

        // Perform a query on the pets table
        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int dateColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_DATE);
            int dayColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_DAY);
            int habitColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT);
            int freqColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_FREQUENCY);
            int remarksColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_REMARKS);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentDate = cursor.getString(dateColumnIndex);
                String currentDay = cursor.getString(dayColumnIndex);
                String currentHabit = cursor.getString(habitColumnIndex);
                int currentFreq = cursor.getInt(freqColumnIndex);
                String currentRemarks = cursor.getString(remarksColumnIndex);
            }
        return cursor;
    }

}

