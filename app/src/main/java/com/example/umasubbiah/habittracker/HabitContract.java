package com.example.umasubbiah.habittracker;

import android.provider.BaseColumns;


public final class HabitContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private HabitContract() {}

    /**
     * Inner class that defines constant values for the habits database table.
     * Each entry in the table represents a single habit.
     */

    public static final class HabitEntry implements BaseColumns {

        //Table name:

        public final static String TABLE_NAME = "HabitTracker";

        //All the columns of the table:

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_DATE = "date";
        public final static String COLUMN_DAY = "day";
        public final static String COLUMN_HABIT = "habit";
        public final static String COLUMN_FREQUENCY = "frequency";
        public final static String COLUMN_REMARKS = "remarks";

        //Constants for the frequency values:-

        public final static int FREQ_MONTHLY = 2;
        public final static int FREQ_WEEKLY = 1;
        public final static int FREQ_DAILY = 0;
    }

}
