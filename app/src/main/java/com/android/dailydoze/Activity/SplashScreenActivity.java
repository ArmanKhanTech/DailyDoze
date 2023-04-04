package com.android.dailydoze.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.android.dailydoze.R;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        ImageView imageView = findViewById(R.id.splashScreenImage);
        imageView.startAnimation(fadeIn);

        Handler h = new Handler();
        h.postDelayed(() -> {
            SharedPreferences mPrefs = getSharedPreferences("MySharedPref", MODE_PRIVATE);
            SharedPreferences.Editor prefsEditor = mPrefs.edit();
            Boolean b = mPrefs.getBoolean("user",false);

            if(b){
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }else {
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}