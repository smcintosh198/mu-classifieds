package edu.millersville.sdmcinto.utils;

/**
 * Created by Millersville wStudent Shayne McIntosh
 * in reference to android.database.sqlite documentation
 * for CSCI 419 | Dr. Webster
 *
 * DBCalls and content will change, current content is being
 * used to learn Android's SQLite class/interface
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQLiteDBUtility  {

    public static final String KEY_TRACKID = "TrackID";
    public static final String KEY_NAME = "Name";
    public static final String KEY_ALBUMID = "AlbumId";
    public static final String KEY_MEDIA = "MediaTypeId";
    public static final String KEY_GENRE = "GenreId";
    public static final String KEY_COMPOSER = "Composer";
    public static final String KEY_MILLISECONDS = "Milliseconds";
    public static final String KEY_BYTES = "Bytes";
    public static final String KEY_PRICE = "UnitPrice";

    private static final String DB_TABLE = "Track";
    public static final String DB_NAME = "Chinook_Sqlite.sqlite";
    public static final int DB_VERSION = 1;

    private static String TAG = "DBHelper"; //LogCat
    private static String DB_FILEPATH = "";

    private static SQLiteDatabase db;
    private static SQLiteDbHelper dbHelper;
    private static SQLiteCursor cursor;
    private static SQLiteQuery query;

    public static Context mContext;

    //Sublass for opening database on the device
    //remember to use a background thread

    public static class SQLiteDbHelper extends SQLiteOpenHelper{

        private final Context mContext;

        public SQLiteDbHelper (Context context) {
            super(context, DB_NAME, null, DB_VERSION);

            if(android.os.Build.VERSION.SDK_INT >= 17){
                DB_FILEPATH = context.getApplicationInfo().dataDir + "/databases/";
            }
            else
            {
                DB_FILEPATH = "/data/data/" + context.getPackageName() + "/databases/";
            }
            this.mContext = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    public SQLiteDBUtility(Context mContext) {
        this.mContext = mContext;
    }

    public SQLiteDBUtility open() throws SQLiteException {
        dbHelper = new SQLiteDbHelper(mContext);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        if (dbHelper != null){
            dbHelper.close();
        }
    }

    public boolean isOpen() {
        return db.isOpen();
    }

    public SQLiteCursor getTracks() {
        SQLiteCursor cursor;
        cursor = (SQLiteCursor) db.query(DB_TABLE, new String[]{KEY_TRACKID, KEY_NAME, KEY_ALBUMID, KEY_MEDIA, KEY_GENRE, KEY_COMPOSER, KEY_MILLISECONDS, KEY_BYTES, KEY_PRICE}, null, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }








}


