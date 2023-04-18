package com.android.dailydoze.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DailyDozeDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "db2";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "dailyDoze";
    private static final String DATE = "date";
    private static final String BEANS="beans";
    private static final String BERRIES="berries";
    private static final String GREENS="greens";
    private static final String OTHERVEGE="othervege";
    private static final String OTHERFRUITS="otherfruits";
    private static final String CRUCIVEGE="crucivege";
    private static final String FLAXSEEDS="flaxseeds";
    private static final String HERBS="herbs";
    private static final String NUTS="nuts";
    private static final String GRAINS="grains";
    private static final String BEVE="beve";
    private static final String EXERCISE="exercise";
    private static final String SLEEP="sleep";

    public DailyDozeDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ( "
                + DATE + " TEXT, "
                + BEANS + " int, "
                + BERRIES + " int, "
                + GREENS + " int, "
                + OTHERVEGE + " int, "
                + OTHERFRUITS + " int, "
                + CRUCIVEGE + " int, "
                + FLAXSEEDS + " int, "
                + HERBS + " int, "
                + NUTS + " int, "
                + GRAINS + " int, "
                + BEVE + " int, "
                + EXERCISE + " int, "
                + SLEEP + " text) ";
        db.execSQL(query);
    }

    public void addDate(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DATE, date);
        values.put(BEANS, 0);
        values.put(BERRIES, 0);
        values.put(GREENS, 0);
        values.put(OTHERVEGE, 0);
        values.put(OTHERFRUITS, 0);
        values.put(CRUCIVEGE, 0);
        values.put(FLAXSEEDS, 0);
        values.put(HERBS, 0);
        values.put(NUTS, 0);
        values.put(GRAINS, 0);
        values.put(BEVE, 0);
        values.put(EXERCISE, 0);
        values.put(SLEEP, "0");
        db.insert(TABLE_NAME, null, values);
    }

    @SuppressLint("Range")
    public void incData(String dataToUpdate, String dateToUpdate) {
        int i = 0;
        SQLiteDatabase db1 = this.getWritableDatabase();
        Cursor c = db1.rawQuery("SELECT " + dataToUpdate + " FROM " + TABLE_NAME + " WHERE " + DATE + " = '" + dateToUpdate + "'", null);

        if (c.moveToFirst()) {
            i = c.getInt(c.getColumnIndex(dataToUpdate));
            i++;
        }

        SQLiteDatabase db2 = this.getReadableDatabase();
        db2.execSQL("UPDATE " + TABLE_NAME + " SET " + dataToUpdate + " = " + i + " WHERE " + DATE + " = '" + dateToUpdate + "'");
        c.close();
    }

    @SuppressLint("Range")
    public void decData(String dataToUpdate, String dateToUpdate) {
        int i = 0;
        SQLiteDatabase db1 = this.getWritableDatabase();
        Cursor c = db1.rawQuery("SELECT " + dataToUpdate + " FROM " + TABLE_NAME + " WHERE " + DATE + " = '" + dateToUpdate + "'", null);

        if (c.moveToFirst()) {
            i = c.getInt(c.getColumnIndex(dataToUpdate));
            i--;
        }

        SQLiteDatabase db2 = this.getReadableDatabase();
        db2.execSQL("UPDATE " + TABLE_NAME + " SET " + dataToUpdate + " = " + i + " WHERE " + DATE + " = '" + dateToUpdate + "'");
        c.close();
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
    public int getData(String dataToGet, String dateToGet){
        int i = 0;
        SQLiteDatabase db1 = this.getWritableDatabase();
        Cursor c = db1.rawQuery("SELECT " + dataToGet + " FROM " + TABLE_NAME + " WHERE " + DATE + " = '" + dateToGet + "'", null);

        if (c.moveToFirst()) {
            i = c.getInt(c.getColumnIndex(dataToGet));
        }

        c.close();
        return i;
    }

    @SuppressLint("Range")
    public void setSleep(String sleep, String date){
        SQLiteDatabase db2 = this.getReadableDatabase();
        db2.execSQL("UPDATE " + TABLE_NAME + " SET " + SLEEP + " = " + sleep + " WHERE " + DATE + " = '" + date + "'");
    }

    @SuppressLint("Range")
    public int getSleep(String date){
        int i = 0;
        SQLiteDatabase db1 = this.getWritableDatabase();
        Cursor c = db1.rawQuery("SELECT " + SLEEP + " FROM " + TABLE_NAME + " WHERE " + DATE + " = '" + date + "'", null);

        if (c.moveToFirst()) {
            i = c.getInt(c.getColumnIndex(SLEEP));
        }

        c.close();
        return  i;
    }

    @SuppressLint("Range")
    public int getCount(String dateToGet){
        int sum = 0;
        SQLiteDatabase db1 = this.getWritableDatabase();
        Cursor c = db1.rawQuery("SELECT " + BEANS + "," + BERRIES + "," + GREENS + "," + OTHERVEGE + "," + OTHERFRUITS + "," + CRUCIVEGE + "," + FLAXSEEDS + "," + HERBS
                + "," + NUTS + "," + GRAINS + "," + BEVE + "," + EXERCISE + " FROM " + TABLE_NAME + " WHERE " + DATE + " = '" + dateToGet + "'", null);

        if (c.moveToFirst()) {
            String[] columnNames = c.getColumnNames();

            for (String columnName : columnNames) {
                sum += c.getInt(c.getColumnIndex(columnName));
            }
        }

        c.close();
        return sum;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}