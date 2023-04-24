package com.android.dailydoze.Service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.android.dailydoze.Activity.FastActivity;
import com.android.dailydoze.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TimerService extends Service {

    public static final String CHANNEL_ID = "ForegroundServiceChannel";
    public static final String COUNTDOWN_BR = "com.android.dailydoze";
    CountDownTimer cdt = null;
    String timer = "";
    long millis = 10800000;
    Intent intent = new Intent(COUNTDOWN_BR);

    @Override
    public void onCreate() {
        super.onCreate();

        cdt = new CountDownTimer(10800000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                intent.putExtra("countdown", millisUntilFinished);
                NumberFormat f = new DecimalFormat("00");
                long hour = (millisUntilFinished / 3600000) % 24;
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;

                timer = f.format(hour) + ":" + f.format(min) + ":" + f.format(sec);
                intent.putExtra("timer", timer);
                intent.putExtra("status", true);
                intent.putExtra("millisUntilFinished", millis - millisUntilFinished);
                sendBroadcast(intent);
            }

            @Override
            public void onFinish() {
                stopForeground(true);
                stopSelf();
                intent.putExtra("status", false);
                sendBroadcast(intent);
            }
        };
        cdt.start();
    }

    @Override
    public void onDestroy() {
        cdt.cancel();
        sendBroadcast(intent);
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createNotificationChannel();
        Intent notificationIntent = new Intent(this, FastActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Fast-Watch")
                .setContentText(timer)
                .setSmallIcon(R.drawable.app_icon_white)
                .setContentIntent(pendingIntent)
                .setUsesChronometer(true)
                .build();
        startForeground(1, notification);
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    private void createNotificationChannel() {
        NotificationChannel serviceChannel = new NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_HIGH
        );
        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(serviceChannel);
    }
}