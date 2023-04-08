package com.android.dailydoze.Activity;

import static android.app.PendingIntent.FLAG_IMMUTABLE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TimePicker;

import com.android.dailydoze.R;
import com.android.dailydoze.Receiver.AlarmReceiver;

import java.util.Calendar;

public class NotificationActivity extends AppCompatActivity {
    int time;
    ImageView setTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noti);

        setTime = findViewById(R.id.setTime);

        if (Build.VERSION.SDK_INT >= 33) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS},101);
            }
        }

        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTime();
            }
        });
    }

    private void selectTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, R.style.PickerTheme, (timePicker, i1, i2) -> {
            setAlarm(timePicker);
        }, hour, minute, false);
        timePickerDialog.show();
        timePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(getColor(R.color.blue));
        timePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(getColor(R.color.blue));
    }

    private void setAlarm(TimePicker timePicker) {
       Calendar cal = Calendar.getInstance();

       cal.set (Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
       cal.set (Calendar.MINUTE, timePicker.getCurrentMinute());

       AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
       Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);

       PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, FLAG_IMMUTABLE);

       am.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }
}