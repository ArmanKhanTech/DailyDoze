package com.android.dailydoze.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.android.dailydoze.R;

public class MeditationActivity extends AppCompatActivity {
    String time;
    long millis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medi);

        time = "Meditation for 15 minutes";
        millis = 900000;

        RadioGroup rg = findViewById(R.id.radioGroup);

        rg.setOnCheckedChangeListener((group, checkedId) -> {
            switch(checkedId){
                case R.id.fifmins:
                    time = "Meditation for 15 minutes";
                    millis = 900000;
                    break;
                case R.id.thimins:
                    time = "Meditation for 30 minutes";
                    millis = 1800000;
                    break;
                case R.id.foumins:
                    time = "Meditation for 45 minutes";
                    millis = 2700000;
                    break;
                case R.id.other:
                    time = "Meditation for 1 hour";
                    millis = 3600000;
                    break;
            }
        });
    }

    public void openPerformMeditation(View v){
        Intent intent = new Intent(this, PerformMeditationActivity.class);
        intent.putExtra("text", time);
        intent.putExtra("time",millis);
        startActivity(intent);
    }

    public void mediFinish(View v){
        finish();
    }
}