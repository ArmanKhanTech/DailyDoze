package com.android.dailydoze.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class TweaksDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "db3";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "tweaks";
    private static final String DATE = "date";
    private static final String BLACK_CUMIN="black_cumin";
    private static final String GARLIC="garlic";
    private static final String GINGER="ginger";
    private static final String YEAST="yeast";
    private static final String CUMIN="cumin";
    private static final String TEA="tea";
    private static final String HYDRATED="hydrated";
    private static final String DEFLOUR="deflour";
    private static final String FRONTLOAD="frontload";
    private static final String TIMERESTRICT="timerestrict";
    private static final String OPTIMIZE="optimize";
    private static final String WEIGH="weigh";
    private static final String INTENTIONS="intentions";
    private static final String WATER="water";
    private static final String VINEGAR="vinegar";
    private static final String NEG_CAL="neg_cal";
    private static final String UN_MEAL="un_meal";
    private static final String TWE_MIN="twe_min";
    private static final String FAST="fast";
    private static final String SLEEP="sleep";
    private static final String EXP="exp";
    private static final String WEIGHT_MORNING="weight_morning";
    private static final String WEIGHT_EVENING="weight_evening";

    public TweaksDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ( "
                + DATE + " TEXT, "
                + BLACK_CUMIN + " int, "
                + GARLIC + " int, "
                + GINGER + " int, "
                + YEAST + " int, "
                + CUMIN + " int, "
                + TEA + " int, "
                + HYDRATED + " int, "
                + DEFLOUR + " int, "
                + FRONTLOAD + " int, "
                + TIMERESTRICT + " int, "
                + OPTIMIZE + " int, "
                + WEIGH + " int, "
                + INTENTIONS + " int, "
                + WATER + " int, "
                + VINEGAR + " int, "
                + NEG_CAL + " int, "
                + UN_MEAL + " int, "
                + TWE_MIN + " int, "
                + FAST + " int, "
                + SLEEP + " int, "
                + EXP + " int, "
                + WEIGHT_MORNING + " text, "
                + WEIGHT_EVENING + " text) ";
        db.execSQL(query);
    }

    public void addDate(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DATE, date);
        values.put(BLACK_CUMIN, 0);
        values.put(GARLIC, 0);
        values.put(GINGER, 0);
        values.put(YEAST, 0);
        values.put(CUMIN, 0);
        values.put(TEA, 0);
        values.put(HYDRATED, 0);
        values.put(DEFLOUR, 0);
        values.put(FRONTLOAD, 0);
        values.put(TIMERESTRICT, 0);
        values.put(OPTIMIZE, 0);
        values.put(WEIGH, 0);
        values.put(INTENTIONS, 0);
        values.put(WATER, 0);
        values.put(VINEGAR, 0);
        values.put(NEG_CAL, 0);
        values.put(UN_MEAL, 0);
        values.put(TWE_MIN, 0);
        values.put(FAST, 0);
        values.put(SLEEP, 0);
        values.put(EXP, 0);
        values.put(WEIGHT_MORNING, "0");
        values.put(WEIGHT_EVENING, "0");
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

    public boolean getDate(String date) {
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
    public int getData(String dataToGet, String dateToGet) {
        int i = 0;
        SQLiteDatabase db1 = this.getWritableDatabase();
        Cursor c = db1.rawQuery("SELECT " + dataToGet + " FROM " + TABLE_NAME + " WHERE " + DATE + " = '" + dateToGet + "'", null);

        if (c.moveToFirst()) {
            i = c.getInt(c.getColumnIndex(dataToGet));
        }

        c.close();
        return i;
    }

    public ArrayList<String> getDates() {
        ArrayList<String> dates = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT " + DATE + " FROM " + TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                dates.add(c.getString(c.getColumnIndex(DATE)));
            } while (c.moveToNext());
        }

        return dates;
    }

    @SuppressLint("Range")
    public int getCount(String dateToGet) {
        int sum = 0;
        SQLiteDatabase db1 = this.getWritableDatabase();
        Cursor c = db1.rawQuery("SELECT " + BLACK_CUMIN + "," + GARLIC + "," + GINGER + "," + YEAST + "," + CUMIN + "," + TEA + "," + HYDRATED + "," + DEFLOUR
                + "," + FRONTLOAD + "," + TIMERESTRICT + "," + OPTIMIZE + "," + WEIGH + "," + INTENTIONS + "," + WATER + "," + VINEGAR + "," + NEG_CAL +
                "," + UN_MEAL + "," + TWE_MIN + "," + FAST + "," + SLEEP + "," + EXP + " FROM " + TABLE_NAME + " WHERE " + DATE + " = '" + dateToGet + "'", null);

        if (c.moveToFirst()) {
            String[] columnNames = c.getColumnNames();

            for (String columnName : columnNames) {
                sum += c.getInt(c.getColumnIndex(columnName));
            }
        }

        c.close();
        return sum;
    }

    @SuppressLint("Range")
    public ArrayList<String> getAllDate() {
        ArrayList<String> dates = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT " + DATE + " FROM " + TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                dates.add(c.getString(c.getColumnIndex(DATE)));
            } while (c.moveToNext());
        }

        c.close();
        return dates;
    }

    public void setWeight(String time, String date, String weight) {
        SQLiteDatabase db2 = this.getReadableDatabase();
        db2.execSQL("UPDATE " + TABLE_NAME + " SET " + time + " = " + weight + " WHERE " + DATE + " = '" + date + "'");
    }

    @SuppressLint("Range")
    public int getWeightMorning(String date){
        int i = 0;
        SQLiteDatabase db1 = this.getWritableDatabase();
        Cursor c = db1.rawQuery("SELECT " + WEIGHT_MORNING + " FROM " + TABLE_NAME + " WHERE " + DATE + " = '" + date + "'", null);

        if (c.moveToFirst()) {
            i = c.getInt(c.getColumnIndex(WEIGHT_MORNING));
        }

        c.close();
        return  i;
    }

    @SuppressLint("Range")
    public int getWeightEvening(String date) {
        int i = 0;
        SQLiteDatabase db1 = this.getWritableDatabase();
        Cursor c = db1.rawQuery("SELECT " + WEIGHT_EVENING + " FROM " + TABLE_NAME + " WHERE " + DATE + " = '" + date + "'", null);

        if (c.moveToFirst()) {
            i = c.getInt(c.getColumnIndex(WEIGHT_EVENING));
        }

        c.close();
        return  i;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
