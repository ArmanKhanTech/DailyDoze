package com.android.dailydoze.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.dailydoze.R;

@SuppressWarnings("ALL")
@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView textView = findViewById(R.id.app_name_splash);
        Animation animation = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.fade_in);
        textView.startAnimation(animation);

        Handler h2 = new Handler();
        h2.postDelayed(() -> {
            SharedPreferences mPrefs = getSharedPreferences("MySharedPref", MODE_PRIVATE);
            boolean b = mPrefs.getBoolean("user", false);

            Intent intent;
            if (b) {
                intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            } else {
                intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
            }

            startActivity(intent);
            finish();
        }, 2000);
    }

    void customSystemUI() {
        getWindow().setStatusBarColor(getResources().getColor(R.color.customBlue));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.customBlue));
    }
}