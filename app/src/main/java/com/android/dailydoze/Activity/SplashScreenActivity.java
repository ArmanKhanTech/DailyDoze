package com.android.dailydoze.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.RenderMode;
import com.android.dailydoze.R;

@SuppressWarnings("ALL")
@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        textView = findViewById(R.id.app_name_splash);

        Handler h1 = new Handler();
        h1.postDelayed(() -> {
            textView.setVisibility(View.VISIBLE);
        }, 1000);

        lottieAnimationView = findViewById(R.id.animation_view);
        lottieAnimationView.setRenderMode(RenderMode.HARDWARE);
        lottieAnimationView.playAnimation();

        Handler h2 = new Handler();
        h2.postDelayed(() -> {
            SharedPreferences mPrefs = getSharedPreferences("MySharedPref", MODE_PRIVATE);
            boolean b = mPrefs.getBoolean("user",false);

            Intent intent;
            if(b) {
                intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            } else {
                intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
            }

            startActivity(intent);
            finish();
        }, 2000);
    }
}