package com.android.dailydoze.Activity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.dailydoze.Adapter.ListAdapter;
import com.android.dailydoze.Database.FastDatabase;
import com.android.dailydoze.Model.DataListModel;
import com.android.dailydoze.R;
import com.android.dailydoze.Service.TimerService;
import com.android.dailydoze.Utility.CommonUtility;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

@SuppressWarnings("ALL")
public class FastActivity extends AppCompatActivity {
    private String time;
    private long millis = 10800000, millisDone = 0;

    private Button fast;
    private TextView start, end, fastStatus, timer;
    private ListView list;
    private Drawable icon;
    private ListAdapter adapter;
    private Boolean b = false;
    private FastDatabase db;
    private LinearLayout linearLayout;
    private RadioGroup rg;
    private ProgressBar pb;

    private ArrayList<DataListModel> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        start = findViewById(R.id.startTime);
        end = findViewById(R.id.endTime);

        start.setText(getCurrentTime());
        end.setText(addTime(3));

        pb = findViewById(R.id.progressFast);
        pb.setMax((int) millis);
        pb.setProgress((int) millis);

        fastStatus = findViewById(R.id.fastHisStatus);

        fast = findViewById(R.id.fast_start_btn);

        fast.setOnClickListener(v -> {
            if (fast.getText().equals("Start")) {
                pb.setMax((int) millis);
                pb.setProgress((int) millis);

                Intent intent = new Intent(this, TimerService.class);
                intent.putExtra("millis", millis);
                startService(intent);
            } else if (fast.getText().equals("Stop")) {
                cancelTimer();
            }
        });

        timer = findViewById(R.id.timerFast);

        linearLayout = findViewById(R.id.llll);
        rg = findViewById(R.id.radioGroup2);
        list = findViewById(R.id.fastList);

        list.setOnTouchListener((v, event) -> {
            v.getParent().requestDisallowInterceptTouchEvent(true);
            return false;
        });

        db = new FastDatabase(this);

        ArrayList<String> dates = db.getAllDate();
        icon = getDrawable(R.drawable.fastwatch_icon);

        for (int i = 0; i < dates.size(); i++) {
            String temp = dates.get(i);
            data.add(new DataListModel(temp, icon));
        }

        Collections.reverse(data);

        adapter = new ListAdapter(this, data);
        list.setAdapter(adapter);

        if (data.isEmpty()) {
            fastStatus.setText("Nothing to Show");
        } else {
            fastStatus.setVisibility(View.GONE);
        }

        rg.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.thehrs:
                    millis = 10800000;
                    start.setText(getCurrentTime());
                    end.setText(addTime(3));
                    timer.setText("03:00:00");
                    break;

                case R.id.sixhrs:
                    millis = 21600000;
                    start.setText(getCurrentTime());
                    end.setText(addTime(6));
                    timer.setText("06:00:00");
                    break;

                case R.id.ninhrs:
                    millis = 32400000;
                    start.setText(getCurrentTime());
                    end.setText(addTime(9));
                    timer.setText("09:00:00");
                    break;

                case R.id.twehrs:
                    millis = 43200000;
                    start.setText(getCurrentTime());
                    end.setText(addTime(12));
                    timer.setText("12:00:00");
                    break;
            }
        });

        if (Build.VERSION.SDK_INT >= 33) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                        this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
            }
        }

        list.setOnItemClickListener((parent, view, position, id) -> {
            DataListModel fastList = adapter.getItem(position);
            String t = fastList.text();
            String d = db.getDuration(t);

            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View popupView = layoutInflater.inflate(R.layout.popup_notification, null);

            int width = LinearLayout.LayoutParams.MATCH_PARENT;
            int height = LinearLayout.LayoutParams.WRAP_CONTENT;
            final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

            Button b = popupView.findViewById(R.id.delete);
            TextView tv = popupView.findViewById(R.id.notiPopText);
            tv.setText("You fasted for " + d + " on " + t);

            b.setText("Okay");
            b.setOnClickListener(view1 -> popupWindow.dismiss());
            new CommonUtility().dimBehind(popupWindow);
        });
    }

    final private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateGUI(intent);
        }
    };

    public static String getCurrentTime() {
        DateFormat dateFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    public void updateList() {
        ArrayList<String> time = db.getAllDate();
        data.clear();

        for (int i = 0; i < time.size(); i++) {
            String temp = time.get(i);
            data.add(new DataListModel(temp, icon));
        }

        Collections.reverse(data);

        adapter = new ListAdapter(this, data);
        adapter.notifyDataSetChanged();
        list.setAdapter(adapter);

        if (time.isEmpty()) {
            fastStatus.setVisibility(View.VISIBLE);
            fastStatus.setText("Nothing to Show");
        } else {
            fastStatus.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, new IntentFilter(TimerService.COUNTDOWN_BR));
    }

    @Override
    public void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onStop() {
        try {
            unregisterReceiver(broadcastReceiver);
        } catch (Exception e) {
        }

        super.onStop();
    }

    public void cancelTimer() {
        if (db.getDate(getCurrentDate())) {
            db.changeDuration(millisToTime(millisDone), getCurrentDate());
        } else {
            db.addData(getCurrentDate());
            db.changeDuration(millisToTime(millisDone), getCurrentDate());
        }

        rg.check(R.id.thehrs);
        timer.setText("03:00:00");
        fast.setText("Start");

        rg.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.VISIBLE);

        pb.setMax((int) millis);
        pb.setProgress((int) millis);

        Intent intent = new Intent(this, TimerService.class);
        stopService(intent);

        updateList();
    }

    public String millisToTime(long t) {
        NumberFormat f = new DecimalFormat("00");
        long hour = (t / 3600000) % 24;
        long min = (t / 60000) % 60;
        long sec = (t / 1000) % 60;
        return f.format(hour) + ":" + f.format(min) + ":" + f.format(sec);
    }

    public String addTime(int h) {
        DateFormat dateFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, h);
        return dateFormat.format(cal.getTime());
    }

    private void updateGUI(Intent intent) {
        if (intent.getExtras() != null) {
            time = intent.getStringExtra("timer");
            b = intent.getBooleanExtra("status", false);
            millisDone = intent.getLongExtra("millisUntilFinished", 0);

            if (b) {
                timer.setText(time);
                fast.setText("Stop");

                rg.setVisibility(View.GONE);
                linearLayout.setVisibility(View.GONE);

                pb.setProgress((int) ((int) millis - millisDone));
            }
        }
    }

    public String getCurrentDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        return df.format(c);
    }

    public void finish(View v) {
        finish();
    }
}