package com.android.dailydoze.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public TweaksDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
