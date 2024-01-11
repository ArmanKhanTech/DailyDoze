package com.android.dailydoze.Receiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;

import com.android.dailydoze.Activity.SplashScreenActivity;
import com.android.dailydoze.R;

@SuppressWarnings("ALL")
public class AlarmReceiver extends BroadcastReceiver {
    public static final String CHANNEL_ID = "NotifitcationChannel";
    NotificationChannel serviceChannel;
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        int id = bundle.getInt("id");

        Intent intentTo = new Intent(context, SplashScreenActivity.class);
        intentTo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intentTo, PendingIntent.FLAG_IMMUTABLE);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "notify_001");
        mBuilder.setSmallIcon(R.drawable.noti_vec_icon_white)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.app_icon_black))
                .setContentTitle("DailyDoze")
                .setContentText("Make Sure to Take & Update Your Servings")
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setOngoing(true)
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_HIGH)
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent)
                .build().flags = Notification.FLAG_NO_CLEAR | Notification.PRIORITY_HIGH;

        serviceChannel = new NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
        );
        notificationManager.createNotificationChannel(serviceChannel);
        mBuilder.setChannelId(CHANNEL_ID);

        Notification notification = mBuilder.build();
        notificationManager.notify(id, notification);
    }
}
