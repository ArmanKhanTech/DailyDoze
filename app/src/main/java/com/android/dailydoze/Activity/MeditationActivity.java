package com.android.dailydoze.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.dailydoze.R;

public class MeditationActivity extends AppCompatActivity {
    String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medi);

        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.fifmins:
                        time = "15 minutes";
                        break;
                    case R.id.thimins:
                        time = "30 minutes";
                        break;
                    case R.id.foumins:
                        time = "45 minutes";
                        break;
                    case R.id.other:
                        time = "1 hours";
                        break;
                }
            }
        });

    }

    public void openPerformMeditation(View v){
        Intent intent = new Intent(this, PerformMeditationActivity.class);
        intent.putExtra("time", time);
        startActivity(intent);
    }

    public void mediFinish(View v){
        finish();
    }
}