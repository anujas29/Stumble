package com.project.techflier.stumbleapp.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by anuja on 1/26/2018.
 */

public class StumbleContract {

    public static final String AUTHORITY = "com.project.techflier.stumbleapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_TASKS = "article";



    public static final class StumbleEntry implements BaseColumns {
        public static final String TABLE_NAME = "article";
        //public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TASKS).build();


        public static Uri buildMovieUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + TABLE_NAME;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "/" + TABLE_NAME;

        public static final String COLUMN_ARTICLE_ID = "article_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_CONTENT = "content";
        public static final String COLUMN_MEDIA = "featured_media";
        public static final String COLUMN_LINK = "link";
        public static final String COLUMN_DATE = "date";


        public static final String[] ARTICLE_COLUMNS = {
                COLUMN_ARTICLE_ID,
                COLUMN_TITLE,
                COLUMN_CONTENT,
                COLUMN_MEDIA,
                COLUMN_LINK,
                COLUMN_DATE

        };

        public static final int COL_ARTICLE_ID = 0;
        public static final int COL_TITLE = 1;
        public static final int COL_CONTENT = 2;
        public static final int COL_MEDIA = 3;
        public static final int COL_LINK = 4;
        public static final int COLDATE = 5;



    }




}
