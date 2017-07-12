package com.nyelito.beersnob.LikeDatabase;

import android.provider.BaseColumns;

public final class LikeTableContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private LikeTableContract() {}

    /* Inner class that defines the table contents */
    public static class LikeEntry implements BaseColumns {
        public static final String TABLE_NAME = "like";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_LIKE = "like";
    }


    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + LikeEntry.TABLE_NAME + " (" +
                    LikeEntry._ID + " INTEGER PRIMARY KEY," +
                    LikeEntry.COLUMN_NAME_NAME + " TEXT," +
                    LikeEntry.COLUMN_NAME_ID + " TEXT," +
                    LikeEntry.COLUMN_NAME_LIKE + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + LikeEntry.TABLE_NAME;
}