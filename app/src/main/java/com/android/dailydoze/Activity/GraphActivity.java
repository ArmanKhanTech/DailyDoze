package com.android.dailydoze.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
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

@SuppressWarnings("ALL")
public class GraphActivity extends AppCompatActivity {
    private BarData barData;
    private LineData lineData;
    private CombinedChart combinedChart;
    private CombinedData combinedData;
    private BarDataSet barDataSet;
    private LineDataSet lineDataSet;
    private TextView tv;
    private HorizontalScrollView horiScroll;

    private String[] labels;
    private ArrayList<String> dates = new ArrayList<>();

    private String title;

    private ArrayList barEntriesArrayList, lineEntriesArrayList;

    private TweaksDatabase db1;
    private DailyDozeDatabase db2;

    private DecimalFormat decimalFormat = new DecimalFormat("0.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tv = findViewById(R.id.graphTitle);
        horiScroll = findViewById(R.id.horiScroll);

        Intent i = getIntent();
        boolean b = i.getBooleanExtra("tweak", false);

        if (b) {
            tv.setText("Daily Tweaks History");
            db1 = new TweaksDatabase(this);
            getBarEntriesTweaks();
            title = "Average Weight (in kgs)";
        } else {
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
        combinedChart.getXAxis().setAxisMinimum(-combinedData.getBarData().getBarWidth() / 2);
        combinedChart.getXAxis().setAxisMaximum(dates.size() - combinedData.getBarData().getBarWidth() / 2);
        combinedChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        combinedChart.getXAxis().setLabelCount(dates.size());
        combinedChart.getXAxis().setGranularity(1f);
        combinedChart.setData(combinedData);
        combinedChart.setScaleEnabled(false);
        combinedChart.centerViewTo(combinedChart.getXChartMax(), 0, YAxis.AxisDependency.LEFT);
        combinedChart.setVisibleXRangeMaximum(4);
        combinedChart.animateXY(2000, 2000);
        combinedChart.invalidate();

        combinedChart.setOnChartGestureListener(new OnChartGestureListener() {
            @Override
            public void onChartGestureStart(
                    MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture
            ) {
                horiScroll.requestDisallowInterceptTouchEvent(true);
            }

            @Override
            public void onChartGestureEnd(
                    MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture
            ) {
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
                horiScroll.requestDisallowInterceptTouchEvent(
                        combinedChart.getLowestVisibleX() != combinedChart.getXAxis().getAxisMinimum()
                                && combinedChart.getHighestVisibleX() != combinedChart.getXAxis().getAxisMaximum());
            }
        });
    }

    private void getBarEntriesDailyDoze() {
        barEntriesArrayList = new ArrayList<>();
        lineEntriesArrayList = new ArrayList<>();

        float f = 0.00F;
        dates = db2.getAllDate();
        labels = new String[dates.size()];

        for (int i = 0; i < dates.size(); i++) {
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

        float f = 0.00F;
        int weight;
        dates = db1.getAllDate();
        labels = new String[dates.size()];

        for (int i = 0; i < dates.size(); i++) {
            barEntriesArrayList.add(new BarEntry(f, db1.getCount(dates.get(i))));
            weight = (db1.getWeightEvening(dates.get(i)) + db1.getWeightMorning(dates.get(i))) / 2;
            lineEntriesArrayList.add(new Entry(f, weight));
            String date = dates.get(i);
            date = date.substring(0, 6);
            labels[i] = date;
            f++;
        }
    }

    public void finish(View v) {
        finish();
    }
}