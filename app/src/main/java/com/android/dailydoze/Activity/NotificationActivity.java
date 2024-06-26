package com.android.dailydoze.Activity;

import static android.app.PendingIntent.FLAG_IMMUTABLE;

import android.Manifest;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.dailydoze.Adapter.ListAdapter;
import com.android.dailydoze.Database.NotificationDatabase;
import com.android.dailydoze.Model.DataListModel;
import com.android.dailydoze.R;
import com.android.dailydoze.Receiver.AlarmReceiver;
import com.android.dailydoze.Utility.CommonUtility;
import com.google.android.material.materialswitch.MaterialSwitch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("ALL")
public class NotificationActivity extends AppCompatActivity {
    private ImageView setTime;
    private TextView status;
    private ListView list;
    private MaterialSwitch sw;
    private Drawable icon;

    private ArrayList<DataListModel> data = new ArrayList<>();

    private ListAdapter adapter;
    private NotificationDatabase db1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setTime = findViewById(R.id.setTime);
        sw = findViewById(R.id.sw_noti);
        list = findViewById(R.id.notiList);
        status = findViewById(R.id.notiStatus);

        SharedPreferences sharedPrefs = getSharedPreferences("notiSwitch", MODE_PRIVATE);
        sw.setChecked(sharedPrefs.getBoolean("State", false));

        db1 = new NotificationDatabase(this);
        ArrayList<String> time = db1.readNotification();

        icon = getDrawable(R.drawable.noti_vec_icon_black);

        for (int i = 0; i < time.size(); i++) {
            String temp = time.get(i);
            data.add(new DataListModel(temp, icon));
        }

        adapter = new ListAdapter(this, data);
        list.setAdapter(adapter);

        ComponentName componentName = new ComponentName(this, AlarmReceiver.class);
        PackageManager packageManager = this.getPackageManager();

        if (sw.isChecked()) {
            setTime.setVisibility(View.VISIBLE);
            list.setVisibility(View.VISIBLE);

            if (!db1.isDbEmpty()) {
                status.setText("No Notifications");
            } else {
                status.setVisibility(View.GONE);
            }

            packageManager.setComponentEnabledSetting(
                    componentName,
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP);

            sw.setThumbTintList(ContextCompat.getColorStateList(this, R.color.white));
        } else {
            setTime.setVisibility(View.INVISIBLE);
            list.setVisibility(View.INVISIBLE);
            status.setText("Notifications Disabled");

            packageManager.setComponentEnabledSetting(
                    componentName,
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP);

            sw.setThumbTintList(ContextCompat.getColorStateList(this, R.color.customBlue));
        }

        sw.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                SharedPreferences.Editor editor = getSharedPreferences("notiSwitch", MODE_PRIVATE).edit();
                editor.putBoolean("State", true);
                editor.apply();

                setTime.setVisibility(View.VISIBLE);
                list.setVisibility(View.VISIBLE);

                if (!db1.isDbEmpty()) {
                    status.setText("No Notifications");
                } else {
                    status.setVisibility(View.GONE);
                }

                packageManager.setComponentEnabledSetting(
                        componentName,
                        PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                        PackageManager.DONT_KILL_APP);

                sw.setThumbTintList(ContextCompat.getColorStateList(this, R.color.white));
            } else {
                SharedPreferences.Editor editor = getSharedPreferences("notiSwitch", MODE_PRIVATE).edit();
                editor.putBoolean("State", false);
                editor.apply();

                setTime.setVisibility(View.INVISIBLE);
                list.setVisibility(View.INVISIBLE);

                status.setVisibility(View.VISIBLE);
                status.setText("Notifications Disabled");

                packageManager.setComponentEnabledSetting(
                        componentName,
                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                        PackageManager.DONT_KILL_APP);

                sw.setThumbTintList(ContextCompat.getColorStateList(this, R.color.customBlue));
            }
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                    this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
        }

        list.setOnItemClickListener((parent, view, position, id) -> {
            DataListModel notiList = adapter.getItem(position);
            String t = notiList.text();

            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View popupView = layoutInflater.inflate(R.layout.popup_notification, null);

            int width = LinearLayout.LayoutParams.MATCH_PARENT;
            int height = LinearLayout.LayoutParams.WRAP_CONTENT;
            final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

            Button b = popupView.findViewById(R.id.delete);
            b.setOnClickListener(view1 -> {
                int idOf = db1.readID(t);
                cancelNotification(idOf);
                db1.deleteNotification(t);
                popupWindow.dismiss();
                updateList();
            });
            new CommonUtility().dimBehind(popupWindow);
        });
        setTime.setOnClickListener(v -> selectTime());
    }

    private void selectTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this, R.style.PickerTheme, (timePicker, i1, i2
        ) -> {
            setAlarm(timePicker);
            adapter.notifyDataSetChanged();
        }, hour, minute, false);
        timePickerDialog.show();
        timePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(getColor(R.color.customBlue));
        timePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(getColor(R.color.customBlue));
    }

    public void updateList() {
        ArrayList<String> time = db1.readNotification();
        data.clear();

        for (int i = 0; i < time.size(); i++) {
            String temp = time.get(i);
            data.add(new DataListModel(temp, icon));
        }

        adapter = new ListAdapter(this, data);
        adapter.notifyDataSetChanged();
        list.setAdapter(adapter);

        if (!db1.isDbEmpty()) {
            status.setVisibility(View.VISIBLE);
            status.setText("No Notifications");
        } else {
            status.setVisibility(View.GONE);
        }
    }

    private void setAlarm(TimePicker timePicker) {
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
        cal.set(Calendar.MINUTE, timePicker.getCurrentMinute());

        db1.addNotification(convert(cal.getTimeInMillis()), cal.getTimeInMillis());
        int id = db1.readID(convert(cal.getTimeInMillis()));

        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        intent.putExtra("servingsNotification", id);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), id, intent, FLAG_IMMUTABLE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

        updateList();
    }

    public void cancelNotification(int id) {
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getApplicationContext(), id, intent,
                PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );
        am.cancel(pendingIntent);
        pendingIntent.cancel();
    }

    public String convert(long millis) {
        return (new SimpleDateFormat("hh:mm aa")).format(new Date(millis));
    }

    public void finish(View v) {
        finish();
    }
}