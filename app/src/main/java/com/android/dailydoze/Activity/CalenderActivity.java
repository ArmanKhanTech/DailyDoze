package com.android.dailydoze.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
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
        calendarView = (CalendarView) findViewById(R.id.calendarView);

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
                name.setText("Flexseeds");
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
                name.setText("Grains");
                break;
            case "exercise":
                name.setText("Exercise");
                break;
            default:
        }
    }

    public void calBack(View v){
        finish();
    }
}