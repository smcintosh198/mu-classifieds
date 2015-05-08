package edu.millersville.sdmcinto.utils;

/**
 * Created by Millersville wStudent Shayne McIntosh
 * in reference to android.database.sqlite documentation
 * for CSCI 419 | Dr. Webster
 *
 * DBCalls and content will change, current content is being
 * used to learn Android's SQLite class/interface
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class SQLiteDBUtility  {

    public static final String KEY_ID = "_id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_PRICE = "price";
    public static final String KEY_CATEGORY = "category";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_POSTED = "posted";
    public static final String KEY_EXPIRES = "expires";
    public static final String KEY_EMAIL = "email";

    private static final String DB_TABLE = "Classifieds";
    private static final String DB_NAME = "MuClassifieds";
    public  static int version = Integer.parseInt("1");


    public SQLiteCursor cursor;
    private static String TAG = "SQL_DB_UTILITY";
    private static Throwable tr;

    final Context mContext;

    private static final String DATABASE_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + DB_TABLE + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    KEY_TITLE + "TEXT" + "," +
                    KEY_PRICE + "TEXT" + "," +
                    KEY_CATEGORY + "TEXT" +  "," +
                    KEY_DESCRIPTION + "TEXT" +  "," +
                    KEY_POSTED + "TEXT" +  "," +
                    KEY_EXPIRES + "TEXT" +  "," +
                    KEY_EMAIL + "TEXT" +  ")";

    private static SQLiteDatabase db;

    private static class SQLiteDatabaseHelper extends SQLiteOpenHelper {

        SQLiteDatabaseHelper(Context context) {
            super(context, DB_NAME, null, version);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.w(TAG, DATABASE_CREATE_TABLE);
            db.execSQL(DATABASE_CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

    public SQLiteDBUtility(Context context) {
        mContext = context;
    }

    public void openDB(){
        SQLiteDatabaseHelper dbHelper = new SQLiteDatabaseHelper(mContext);
        db = dbHelper.getWritableDatabase();

    }

    public void closeDB() {
        db.close();
    }

    public boolean isOpen(){
        return db.isOpen();
    }

    public SQLiteCursor getTracks() {


        if (db.isOpen()){
            System.out.println("database is open!");
            cursor = (SQLiteCursor) db.query(DB_TABLE, new String[]{KEY_ID, KEY_TITLE, KEY_PRICE, KEY_CATEGORY, KEY_DESCRIPTION, KEY_POSTED, KEY_EXPIRES, KEY_EMAIL}, null, null, null, null, null);

            if (cursor != null){
                cursor.moveToFirst();
            }
            return cursor;
        } else {
            throw new Error("Database not open", tr);
        }
    }



    public long insert(String title, String price, String category, String description, String posted, String expires, String email) {
        if (db.isOpen()){

            ContentValues values = new ContentValues();
            values.put(KEY_TITLE, title);
            values.put(KEY_PRICE, price);
            values.put(KEY_CATEGORY, category);
            values.put(KEY_DESCRIPTION, description);
            values.put(KEY_POSTED, posted);
            values.put(KEY_EXPIRES, expires);
            values.put(KEY_EMAIL, email);
            return db.insert(DB_TABLE, null, values);
        }else {
            System.out.print("DATABASE NOT OPEn!");
            return -1;
        }
    }
}