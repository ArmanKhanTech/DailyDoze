package com.android.dailydoze.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.android.dailydoze.Database.DailyDozeDatabase;
import com.android.dailydoze.Database.MeditationDatabase;
import com.android.dailydoze.Database.NotificationDatabase;
import com.android.dailydoze.Database.TweaksDatabase;
import com.android.dailydoze.R;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        ImageView imageView = findViewById(R.id.splashScreenImage);
        imageView.startAnimation(fadeIn);

        Handler h = new Handler();
        h.postDelayed(() -> {
            SharedPreferences mPrefs = getSharedPreferences("MySharedPref", MODE_PRIVATE);
            boolean b = mPrefs.getBoolean("user",false);

            Intent intent;
            if(b){
                intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            }else {
                intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
            }
            startActivity(intent);
            finish();
        }, 2000);
    }

}