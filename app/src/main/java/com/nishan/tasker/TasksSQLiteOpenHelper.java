package com.nishan.tasker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nishan on 8/18/17.
 */

public class TasksSQLiteOpenHelper {

    public class DatabaseHelper extends SQLiteOpenHelper {

        public static final String DATABASE_NAME = "Tasks.db";

        public static final String TABLE_NAME = "Tasks.table";

        public static final String COL_ID = "ID";
        public static final String COL_Latitude = "Latitude";
        public static final String COL_Longitude = "Longitude";
        public static final String COL_Date = "Date";
        public static final String COL_Time = "Time";
        public static final String COL_Task_details = "Task_details";

        public DatabaseHelper(Context context) {

            super(context, DATABASE_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table " + TABLE_NAME + " ( " +
                    COL_ID + " integer primary key autoincrement , " +
                    COL_Latitude + " double , " +
                    COL_Longitude + " double , " +
                    COL_Date + " date, " +
                    COL_Time + " time, " +
                    COL_Task_details + " text " +
                    " ) ");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXITS" + TABLE_NAME);
            onCreate(db);
        }

        public boolean insertData(String lat, String lng, String date, String time, String task_details) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_Latitude, lat);
            contentValues.put(COL_Longitude, lng);
            contentValues.put(COL_Date, date);
            contentValues.put(COL_Time, time);
            contentValues.put(COL_Task_details, task_details);

            long result = db.insert(TABLE_NAME, null, contentValues);
            if (result == -1)
                return false;
            else
                return true;
        }

        public Cursor getAllData() {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res = db.rawQuery("select*from" + TABLE_NAME, null);
            return res;
        }

        public boolean updateData(String ID, String lat, String lng, String date, String time, String task_details) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            String where = COL_ID + "=" + ID;
            contentValues.put(COL_Latitude, lat);
            contentValues.put(COL_Longitude, lng);
            contentValues.put(COL_Date, date);
            contentValues.put(COL_Time, time);
            contentValues.put(COL_Task_details, task_details);

            // Insert it into the database.
            return db.update(TABLE_NAME, contentValues, where, null) != 0;

        }

        public Integer deleteData(String id) {
            SQLiteDatabase db = this.getWritableDatabase();
            return db.delete(TABLE_NAME, "ID=?", new String[]{id});
        }

        public int getMYInquiryCount() {
            SQLiteDatabase db = this.getReadableDatabase();

            String countquery = "Select * from" + TABLE_NAME;
            Cursor cursor = db.rawQuery(countquery, null);
            return cursor.getCount();
        }
    }
}
