package com.android.dailydoze.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.dailydoze.R;

public class CalenderActivity extends AppCompatActivity {
    CalendarView calendarView;
    TextView name;
    String title;
    ProgressBar pb;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

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

    public void select(String head){
        switch(head) {
            case "beans":
                name.setText("Beans");
                break;
            case "berries":
                name.setText("Berries");
                break;
            case "fruits":
                name.setText("Fruits");
                break;
            case "crucivege":
                name.setText("Cruciferous Vegetables");
                break;
            case "greens":
                name.setText("Greens");
                break;
            case "othervege":
                name.setText("Other Vegetables");
                break;
            case "flexseeds":
                name.setText("Flaxseeds");
                break;
            case "herbs":
                name.setText("Herbs & Spices");
                break;
            case "beverages":
                name.setText("Beverages");
                break;
            case "nuts":
                name.setText("Nuts & Seeds");
                break;
            case "grains":
                name.setText("Whole Grains");
                break;
            case "exercise":
                name.setText("Exercise");
                break;
            case "fast":
                name.setText("Fast After 7:00 pm");
                break;
            case "sleep":
                name.setText("Sufficient Sleep");
                break;
            case "exp":
                name.setText("Experiment Mild Trendelenburg");
                break;
            case "water":
                name.setText("Preload with Water");
                break;
            case "neg":
                name.setText("Preload with 'Negative Calories' Meal");
                break;
            case "vinegar":
                name.setText("Incorporate Vinegar");
                break;
            case "un_meal":
                name.setText("Enjoy Meal Undistracted");
                break;
            case "twemin":
                name.setText("Follow 20 Minutes Rule");
                break;
            case "cumin":
                name.setText("Black Cumin");
                break;
            case "ginger":
                name.setText("Ground Ginger");
                break;
            case "garlic":
                name.setText("Gralic Powder");
                break;
            case "cumin2":
                name.setText("Cumin");
                break;
            case "Yeast":
                name.setText("Yeast");
                break;
            case "green":
                name.setText("Green Tea");
                break;
            case "hyd":
                name.setText("Stay Hydrated");
                break;
            case "deflour":
                name.setText("Deflour Your Diet");
                break;
            case "optimize":
                name.setText("Optimize Your Exercise");
                break;
            case "restrict":
                name.setText("Time-Restrict Your Eatings");
                break;
            case "front":
                name.setText("Front Load Your Diet");
                break;
            case "weign":
                name.setText("Weigh Yourself Twice a Day");
                break;
            case "inten":
                name.setText("Complete Your Implementation Intentions");
                break;
            default:
                break;
        }
    }

    public void calBack(View v){
        finish();
    }
}