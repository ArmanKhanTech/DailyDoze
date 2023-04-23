package com.android.dailydoze.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.dailydoze.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MeditationStats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation_stats);

        TextView tv = findViewById(R.id.textView5);
        ImageButton img = findViewById(R.id.closeStats);

        Intent intent = getIntent();
        NumberFormat f = new DecimalFormat("00");
        long l = Long.parseLong(String.valueOf(intent.getStringExtra("time")));
        long hour = (l / 3600000) % 24;
        long min = (l / 60000) % 60;
        long sec = (l / 1000) % 60;
        tv.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MeditationStats.this, MeditationActivity.class));
                finish();
            }
        });
    }
}