package com.android.dailydoze.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.dailydoze.Database.DailyDozeDatabase;
import com.android.dailydoze.Database.TweaksDatabase;
import com.android.dailydoze.R;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {

    BarData barData;
    LineData lineData;
    CombinedChart combinedChart;
    CombinedData combinedData;
    BarDataSet barDataSet;
    LineDataSet lineDataSet;
    ArrayList barEntriesArrayList, lineEntriesArrayList;
    TextView tv;
    TweaksDatabase db1;
    DailyDozeDatabase db2;
    String[] labels;
    ArrayList<String> dates = new ArrayList<>();
    DecimalFormat decimalFormat = new DecimalFormat("0.##");
    HorizontalScrollView horiScroll;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        tv = findViewById(R.id.graphTitle);
        horiScroll = findViewById(R.id.horiScroll);

        Intent i = getIntent();
        boolean b = i.getBooleanExtra("tweak",false);

        if(b){
            tv.setText("Daily Tweaks History");
            db1 = new TweaksDatabase(this);
            getBarEntriesTweaks();
            title = "Average Weight (in kgs)";
        } else{
            tv.setText("Daily Servings History");
            db2 = new DailyDozeDatabase(this);
            getBarEntriesDailyDoze();
            title = "Sleep Time (in hrs)";
        }

        combinedChart = findViewById(R.id.idCombineChart);
        combinedData = new CombinedData();

        barDataSet = new BarDataSet(barEntriesArrayList, "Total Servings");
        lineDataSet = new LineDataSet(lineEntriesArrayList, title);

        lineData = new LineData(lineDataSet);
        barData = new BarData(barDataSet);

        barData.setBarWidth(0.5f);
        barData.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return decimalFormat.format(value);
            }
        });

        lineData.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return decimalFormat.format(value);
            }
        });

        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.WHITE);
        barDataSet.setValueTextSize(15f);

        lineDataSet.setColor(Color.BLACK);
        lineDataSet.setLineWidth(2f);
        lineDataSet.setCircleColor(Color.BLACK);
        lineDataSet.setCircleRadius(6f);
        lineDataSet.setFillColor(Color.rgb(240, 238, 70));
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet.setDrawValues(true);
        lineDataSet.setValueTextSize(15f);

        combinedData.setData(barData);
        combinedData.setData(lineData);

        combinedChart.getXAxis().setTextSize(11f);
        combinedChart.getAxisRight().setTextSize(11f);
        combinedChart.setDrawValueAboveBar(false);
        combinedChart.getAxisLeft().setDrawGridLines(false);
        combinedChart.getAxisRight().setDrawGridLines(false);
        combinedChart.getDescription().setEnabled(false);
        combinedChart.getAxisLeft().setDrawLabels(false);
        combinedChart.getAxisLeft().setAxisMinimum(0f);
        combinedChart.getAxisRight().setAxisMinimum(0f);
        combinedChart.getXAxis().setAxisMinimum(-combinedData.getBarData().getBarWidth()/2);
        combinedChart.getXAxis().setAxisMaximum(dates.size()-combinedData.getBarData().getBarWidth()/2);
        combinedChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        combinedChart.setData(combinedData);
        combinedChart.setScaleEnabled(false);
        combinedChart.centerViewTo(combinedChart.getXChartMax(), 0, YAxis.AxisDependency.RIGHT);
        combinedChart.setVisibleXRangeMaximum(3);
        combinedChart.animateXY(2000, 2000);
        combinedChart.invalidate();

        combinedChart.setOnChartGestureListener(new OnChartGestureListener() {
            @Override
            public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
                horiScroll.requestDisallowInterceptTouchEvent(true);
            }

            @Override
            public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
                horiScroll.requestDisallowInterceptTouchEvent(false);
            }

            @Override
            public void onChartLongPressed(MotionEvent me) {

            }

            @Override
            public void onChartDoubleTapped(MotionEvent me) {

            }

            @Override
            public void onChartSingleTapped(MotionEvent me) {

            }

            @Override
            public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

            }

            @Override
            public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

            }

            @Override
            public void onChartTranslate(MotionEvent me, float dX, float dY) {
                Log.i("GESTURE", "onChartTranslate");
                horiScroll.requestDisallowInterceptTouchEvent(combinedChart.getLowestVisibleX() != combinedChart.getXAxis().getAxisMinimum() && combinedChart.getHighestVisibleX() != combinedChart.getXAxis().getAxisMaximum());
            }
        });
    }



    private void getBarEntriesDailyDoze() {
        barEntriesArrayList = new ArrayList<>();
        lineEntriesArrayList = new ArrayList<>();

        dates = db2.getAllDate();
        float f = 0.00F;
        labels = new String[dates.size()];
        for(int i = 0; i < dates.size(); i++){
            barEntriesArrayList.add(new BarEntry(f, db2.getCount(dates.get(i))));
            lineEntriesArrayList.add(new Entry(f, db2.getSleep(dates.get(i))));
            String date = dates.get(i);
            date = date.substring(0, 6);
            labels[i] = date;
            f++;
        }
    }

    private void getBarEntriesTweaks() {
        barEntriesArrayList = new ArrayList<>();
        lineEntriesArrayList = new ArrayList<>();

        dates = db1.getAllDate();
        float f = 0.00F;
        int weight;
        labels = new String[dates.size()];
        for(int i = 0; i < dates.size(); i++){
            barEntriesArrayList.add(new BarEntry(f, db1.getCount(dates.get(i))));
            weight = (db1.getWeightEvening(dates.get(i))+db1.getWeightMorning(dates.get(i)))/2;
            lineEntriesArrayList.add(new Entry(f, weight));
            String date = dates.get(i);
            date = date.substring(0, 6);
            labels[i] = date;
            f++;
        }
    }

    public void graphBack(View v){
        finish();
    }
}