package com.android.dailydoze.CustomDatePicker;

@SuppressWarnings("ALL")
public class DateModel {
    private int year;
    private int month;
    private int day;
    private long date;

    public DateModel() {
        //
    }

    public DateModel(long date) {
        this.setDate(date);
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public long getDate() {
        return this.date;
    }

    public void setDate(long date) {
        this.date = date;
        this.day = DateUtils.getDay(date);
        this.month = DateUtils.getMonth(date);
        this.year = DateUtils.getYear(date);
    }

    public void updateModel() {
        this.setDate(DateUtils.getTimeMiles(this.year, this.month, this.day));
    }

    public String toString() {
        return "DateModel{year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", date=" + this.date + '}';
    }

}
