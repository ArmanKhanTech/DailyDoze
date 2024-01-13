package com.android.dailydoze.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.dailydoze.Database.DailyDozeDatabase;
import com.android.dailydoze.Database.TweaksDatabase;
import com.android.dailydoze.R;
import com.android.dailydoze.Utility.EventDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

@SuppressWarnings("ALL")
public class CalenderActivity extends AppCompatActivity {
    MaterialCalendarView calendarView;
    TextView name;
    String title;
    ProgressBar pb;
    TweaksDatabase db1;
    DailyDozeDatabase db2;
    ArrayList<String> dates = new ArrayList<>();

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        pb = findViewById(R.id.loading);
        name = findViewById(R.id.calName);
        calendarView = findViewById(R.id.calendarView);

        calendarView.setVisibility(View.GONE);

        LoadCal loadCal = new LoadCal();
        loadCal.execute();
    }

    private final class LoadCal extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Intent intent = getIntent();
            title = intent.getStringExtra("title");
            select(title);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            pb.setVisibility(View.GONE);
            calendarView.setVisibility(View.VISIBLE);
        }
    }

    public void select(String head) {
        switch(head) {
            case "beans":
                name.setText("Beans");
                highlightDailyDoze("beans");
                break;
            case "berries":
                name.setText("Berries");
                highlightDailyDoze("berries");
                break;
            case "fruits":
                name.setText("Fruits");
                highlightDailyDoze("otherfruits");
                break;
            case "crucivege":
                name.setText("Cruciferous Vegetables");
                highlightDailyDoze("crucivege");
                break;
            case "greens":
                name.setText("Greens");
                highlightDailyDoze("greens");
                break;
            case "othervege":
                name.setText("Other Vegetables");
                highlightDailyDoze("othervege");
                break;
            case "flexseeds":
                name.setText("Flaxseeds");
                highlightDailyDoze("flaxseeds");
                break;
            case "herbs":
                name.setText("Herbs & Spices");
                highlightDailyDoze("herbs");
                break;
            case "beverages":
                name.setText("Beverages");
                highlightDailyDoze("beve");
                break;
            case "nuts":
                name.setText("Nuts & Seeds");
                highlightDailyDoze("nuts");
                break;
            case "grains":
                name.setText("Whole Grains");
                highlightDailyDoze("grains");
                break;
            case "exercise":
                name.setText("Exercise");
                highlightDailyDoze("exercise");
                break;
            case "fast":
                name.setText("Fast After 7:00 pm");
                highlightTweaks("fast");
                break;
            case "sleep":
                name.setText("Sufficient Sleep");
                highlightTweaks("sleep");
                break;
            case "exp":
                name.setText("Experiment Mild Trendelenburg");
                highlightTweaks("exp");
                break;
            case "water":
                name.setText("Preload with Water");
                highlightTweaks("water");
                break;
            case "neg":
                name.setText("Preload with 'Negative Calories' Meal");
                highlightTweaks("neg_cal");
                break;
            case "vinegar":
                name.setText("Incorporate Vinegar");
                highlightTweaks("vinegar");
                break;
            case "un_meal":
                name.setText("Enjoy Meal Undistracted");
                highlightTweaks("un_meal");
                break;
            case "twemin":
                name.setText("Follow 20 Minutes Rule");
                highlightTweaks("twe_min");
                break;
            case "cumin":
                name.setText("Black Cumin");
                highlightTweaks("black_cumin");
                break;
            case "ginger":
                name.setText("Ground Ginger");
                highlightTweaks("ginger");
                break;
            case "garlic":
                name.setText("Gralic Powder");
                highlightTweaks("garlic");
                break;
            case "cumin2":
                name.setText("Cumin");
                highlightTweaks("cumin");
                break;
            case "Yeast":
                name.setText("Yeast");
                highlightTweaks("Yeast");
                break;
            case "green":
                name.setText("Green Tea");
                highlightTweaks("tea");
                break;
            case "hyd":
                name.setText("Stay Hydrated");
                highlightTweaks("hydrated");
                break;
            case "deflour":
                name.setText("Deflour Your Diet");
                highlightTweaks("deflour");
                break;
            case "optimize":
                name.setText("Optimize Your Exercise");
                highlightTweaks("optimize");
                break;
            case "restrict":
                name.setText("Time-Restrict Your Eatings");
                highlightTweaks("timerestrict");
                break;
            case "front":
                name.setText("Front Load Your Diet");
                highlightTweaks("frontload");
                break;
            case "weign":
                name.setText("Weigh Yourself Twice a Day");
                highlightTweaks("weign");
                break;
            case "inten":
                name.setText("Complete Your Implementation Intentions");
                highlightTweaks("intentions");
                break;
            default:
                break;
        }
    }

    public void highlightDailyDoze(String name) {
        db2 = new DailyDozeDatabase(this);
        dates = db2.getDates();
        Collection<CalendarDay> datesToMark = new ArrayList<>();

        for (int i = 0; i < dates.size(); i++) {
            String date = dates.get(i);
            int servings = db2.getData(name, date);
            if (servings != 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
                Date date1 = null;

                try {
                    date1 = sdf.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                CalendarDay day = CalendarDay.from(date1);
                datesToMark.add(day);
                Log.d("date", date);
            }
        }
        calendarView.addDecorator(new EventDecorator(datesToMark));
        return;
    }

    public void highlightTweaks(String name) {
        db1 = new TweaksDatabase(this);
        dates = db1.getDates();
        Collection<CalendarDay> datesToMark = new ArrayList<>();

        for (int i = 0; i < dates.size(); i++) {
            String date = dates.get(i);
            int servings = db1.getData(name, date);
            if (servings != 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
                Date date1 = null;

                try {
                    date1 = sdf.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                CalendarDay day = CalendarDay.from(date1);
                datesToMark.add(day);
                Log.d("date", date);
            }
        }
        calendarView.addDecorator(new EventDecorator(datesToMark));
        return;
    }

    public void calBack(View v) {
        finish();
    }
}