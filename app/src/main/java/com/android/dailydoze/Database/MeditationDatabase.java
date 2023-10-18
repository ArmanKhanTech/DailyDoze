package com.android.dailydoze.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class MeditationDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "db4";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "meditation";
    private static final String DATE = "date";
    private static final String DURATION = "duration";

    public MeditationDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ( "
                + DATE + " TEXT, "
                + DURATION + " text) ";
        db.execSQL(query);

    }

    public void addData(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DATE, date);
        values.put(DURATION, "0");
        db.insert(TABLE_NAME, null, values);
    }

    @SuppressLint("Range")
    public void changeDuration(String dataToUpdate, String dateToUpdate) {
        SQLiteDatabase db2 = this.getReadableDatabase();
        db2.execSQL("UPDATE " + TABLE_NAME + " SET " + DURATION + " = " + "'" + dataToUpdate + "'" + " WHERE " + DATE + " = '" + dateToUpdate + "'");
    }

    public boolean getDate(String date){
        boolean b = false;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME +
                " WHERE " + DATE + "='" + date + "'", null);

        if (c.moveToFirst()) {
            b = true;
        }

        c.close();
        return b;
    }

    @SuppressLint("Range")
    public ArrayList<String> getAllDate() {
        int i = 0;
        ArrayList<String> dates = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT " + DATE + " FROM " + TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                dates.add(c.getString(c.getColumnIndex(DATE)));
                i++;
            } while (c.moveToNext());
        }

        c.close();
        return dates;
    }

    @SuppressLint("Range")
    public String getDuration(String date) {
        String dura = "";
        SQLiteDatabase db1 = this.getWritableDatabase();
        Cursor c = db1.rawQuery("SELECT " + DURATION + " FROM " + TABLE_NAME + " WHERE " + DATE + " = '" + date + "'", null);

        if (c.moveToFirst()) {
            dura = c.getString(c.getColumnIndex(DURATION));
        }

        c.close();
        return dura;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
