package com.android.dailydoze.CustomDatePicker;

import java.util.Calendar;

@SuppressWarnings("ALL")
public class DateUtils {
    public DateUtils() {
        //
    }

    public static long getTimeMiles(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, year);
        calendar.set(2, month);
        int maxDayCount = calendar.getActualMaximum(5);
        calendar.set(5, Math.min(day, maxDayCount));
        return calendar.getTimeInMillis();
    }

    public static long getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static int getMonthDayCount(long timeStamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        int day_count = calendar.getActualMaximum(5);
        return day_count;
    }

    public static int getDay(long timeStamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        int day = calendar.get(5);
        return day;
    }

    public static int getMonth(long timeStamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        int month = calendar.get(2);
        return month;
    }

    public static int getYear(long timeStamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        int year = calendar.get(1);
        return year;
    }

    public static int getCurrentHour() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(11);
        return hour;
    }

    public static int getCurrentMinute() {
        Calendar calendar = Calendar.getInstance();
        int minute = calendar.get(12);
        return minute;
    }
}
