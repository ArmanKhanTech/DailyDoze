package com.android.dailydoze.Service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.android.dailydoze.Activity.FastActivity;
import com.android.dailydoze.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TimerService extends Service {
    private static final String CHANNEL_ID = "DailyDozeForegroundServiceChannel";
    public static final String COUNTDOWN_BR = "com.android.dailydoze";
    private final Intent intent = new Intent(COUNTDOWN_BR);
    private CountDownTimer cdt = null;
    private String timer = "";
    private long millis, remaining;
    private NotificationManager manager;
    private Notification notification;
    private RemoteViews notificationLayout, notificationLayoutExpanded;

    @Override
    public void onDestroy() {
        super.onDestroy();
        cdt.cancel();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        millis = intent.getLongExtra("millis", 10800000);

        Intent notificationIntent = new Intent(this, FastActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        NotificationChannel serviceChannel = new NotificationChannel(
                CHANNEL_ID,
                "Foreground Service",
                NotificationManager.IMPORTANCE_HIGH
        );
        manager = getSystemService(NotificationManager.class);

        notificationLayout = new RemoteViews(getPackageName(), R.layout.notification_small);
        notificationLayoutExpanded = new RemoteViews(getPackageName(), R.layout.notification_large);

        notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.noti_channel_icon)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.app_icon_black))
                .setContentTitle("Fast-Watch")
                .setContentText(timer)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setCustomContentView(notificationLayout)
                .setCustomBigContentView(notificationLayoutExpanded)
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .build();

        startForeground(1, notification);
        manager.createNotificationChannel(serviceChannel);
        startTimer();

        return START_STICKY;
    }

    public void startTimer() {
        cdt = new CountDownTimer(millis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                intent.putExtra("countdown", millisUntilFinished);

                NumberFormat f = new DecimalFormat("00");
                long hour = (millisUntilFinished / 3600000) % 24;
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;

                remaining = millisUntilFinished;

                timer = f.format(hour) + ":" + f.format(min) + ":" + f.format(sec);

                intent.putExtra("timer", timer);
                intent.putExtra("status", true);
                intent.putExtra("millisUntilFinished", millis - millisUntilFinished);
                sendBroadcast(intent);

                refreshNotification();
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

    private void refreshNotification() {
        notificationLayout.setTextViewText(R.id.notiTimerSmall, timer);
        notificationLayoutExpanded.setTextViewText(R.id.notiTimerLarge, timer);
        notificationLayoutExpanded.setProgressBar(R.id.notiTimerProgress, (int) millis, (int) remaining, false);
        manager.notify(1, notification);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
}