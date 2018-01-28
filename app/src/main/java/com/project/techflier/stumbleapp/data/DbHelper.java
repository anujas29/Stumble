package com.project.techflier.stumbleapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by anuja on 1/26/2018.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "techflierapp.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE_ARTICLE = "CREATE TABLE " + StumbleContract.StumbleEntry.TABLE_NAME + "(" +
                StumbleContract.StumbleEntry.COLUMN_ARTICLE_ID + " TEXT PRIMARY KEY," +
                StumbleContract.StumbleEntry.COLUMN_TITLE + " TEXT," +
                StumbleContract.StumbleEntry.COLUMN_CONTENT+ " TEXT," +
                StumbleContract.StumbleEntry.COLUMN_MEDIA+ " TEXT," +
                StumbleContract.StumbleEntry.COLUMN_LINK + " TEXT," +
                StumbleContract.StumbleEntry.COLUMN_DATE+ " TEXT" +
                ")";
        db.execSQL(CREATE_TABLE_ARTICLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + StumbleContract.StumbleEntry.TABLE_NAME);
        onCreate(db);

    }
}
