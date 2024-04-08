package com.android.dailydoze.CustomDatePicker;

import com.android.dailydoze.CustomDatePicker.Interface.DateFactoryListener;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("ALL")
public class DatePickerFactory {
    private static final DateFormatSymbols dfs = new DateFormatSymbols();
    private DateModel maxDate;
    private DateModel minDate;
    private DateModel selectedDate;
    private DateFactoryListener listener;
    private int monthMin;

    public DatePickerFactory(DateFactoryListener listener) {
        this.listener = listener;
        this.monthMin = 0;
        this.minDate = new DateModel(DateUtils.getTimeMiles(1970, 0, 1));
        this.maxDate = new DateModel(DateUtils.getTimeMiles(2050, 11, 1));
        this.selectedDate = new DateModel(DateUtils.getCurrentTime());
    }

    public void setSelectedYear(int year) {
        this.selectedDate.setYear(year);
        if (this.selectedDate.getYear() == this.minDate.getYear()) {
            if (this.selectedDate.getMonth() < this.minDate.getMonth()) {
                this.selectedDate.setMonth(this.minDate.getMonth());
            } else if (this.selectedDate.getMonth() > this.maxDate.getMonth()) {
                this.selectedDate.setMonth(this.maxDate.getMonth());
            }
        }

        this.selectedDate.updateModel();
        this.listener.onYearChanged();
    }

    public void setSelectedMonth(int month) {
        this.selectedDate.setMonth(month);
        this.selectedDate.updateModel();
        this.listener.onMonthChanged();
    }

    public void setSelectedDay(int day) {
        this.selectedDate.setDay(day);
        this.selectedDate.updateModel();
        this.listener.onDayChanged();
    }

    public DateModel getMaxDate() {
        return this.maxDate;
    }

    public void setMaxDate(long maxDate) {
        this.maxDate = new DateModel(maxDate);
        this.listener.onConfigsChanged();
    }

    public DateModel getMinDate() {
        return this.minDate;
    }

    public void setMinDate(long minDate) {
        this.minDate = new DateModel(minDate);
        this.listener.onConfigsChanged();
    }

    public DateModel getSelectedDate() {
        return this.selectedDate;
    }

    public void setSelectedDate(long selectedDate) {
        this.selectedDate = new DateModel(selectedDate);
        this.listener.onConfigsChanged();
    }

    public List<String> getDayList() {
        int max = DateUtils.getMonthDayCount(this.selectedDate.getDate());

        int min = 0;
        if (this.selectedDate.getYear() == this.maxDate.getYear() && this.selectedDate.getMonth() == this.maxDate.getMonth()) {
            max = this.maxDate.getDay();
        }

        if (this.selectedDate.getYear() == this.minDate.getYear() && this.selectedDate.getMonth() == this.minDate.getMonth()) {
            min = this.minDate.getDay() - 1;
        }

        List<String> days = new ArrayList();

        for (int i = min; i < max; ++i) {
            days.add("" + (i + 1));
        }

        return days;
    }

    public List<String> getMonthList() {
        String[] monthsArray = dfs.getMonths();
        List<String> monthsList = Arrays.asList(monthsArray);

        int max = monthsList.size();
        if (this.selectedDate.getYear() == this.maxDate.getYear()) {
            max = this.maxDate.getMonth() + 1;
        }

        if (this.selectedDate.getYear() == this.minDate.getYear()) {
            this.monthMin = this.minDate.getMonth();
        } else {
            this.monthMin = 0;
        }

        List<String> months = new ArrayList();

        for (int i = this.monthMin; i < max; ++i) {
            months.add(monthsList.get(i));
        }

        return months;
    }

    public List<String> getYearList() {
        int yearCount = Math.abs(this.minDate.getYear() - this.maxDate.getYear()) + 1;
        List<String> years = new ArrayList();

        for (int i = 0; i < yearCount; ++i) {
            years.add("" + (this.minDate.getYear() + i));
        }

        return years;
    }

    public int getMonthMin() {
        return this.monthMin;
    }
}
