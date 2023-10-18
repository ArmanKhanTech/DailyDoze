package com.android.dailydoze.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class NotificationDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "db1";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "notiInfo";
    private static final String ID_COL = "id";
    private static final String TIME="time";
    private static final String MILLIS="millis";

    public NotificationDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ( "
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TIME + " Text, "
                + MILLIS + " LONG ) ";
        db.execSQL(query);
    }

    public void addNotification(String time, long millis) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TIME, time);
        values.put(MILLIS, millis);
        db.insert(TABLE_NAME, null, values);
    }

    public void deleteNotification(String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME+ " WHERE "+TIME+"='"+time+"'");
    }

    public ArrayList<String> readNotification() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + TIME + " FROM " + TABLE_NAME, null);

        ArrayList<String> packs = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                packs.add(cursor.getString(cursor.getColumnIndexOrThrow(TIME)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return packs;
    }

    public int readID(String time) {
        int id = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT " + ID_COL + " FROM " + TABLE_NAME +
                " WHERE " + TIME + "='" + time + "'", null);

        if (cursor.moveToFirst()) {
            id = cursor.getInt(cursor.getColumnIndexOrThrow(ID_COL));
        }
        cursor.close();
        return id;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean isDbEmpty() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        boolean rowExists;

        rowExists = mCursor.moveToFirst();
        mCursor.close();
        return rowExists;
    }
}
