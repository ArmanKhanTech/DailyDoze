package com.android.dailydoze.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.android.dailydoze.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class PerformMeditationActivity extends AppCompatActivity {
    MediaPlayer music;
    ImageView imageView, play, pause, stop;
    TextView timer, textView;
    ProgressBar pB;
    long l;
    CountDownTimer stopWatch;
    ImageButton close;
    boolean b = false;
    String mediTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perform_meditation);

        imageView = findViewById(R.id.imageView);
        play = findViewById(R.id.play);
        pause = findViewById(R.id.pause);
        stop = findViewById(R.id.stop);
        timer = findViewById(R.id.timer);
        pB = findViewById(R.id.progress);
        textView = findViewById(R.id.text);
        close = findViewById(R.id.close);

        music = MediaPlayer.create(this, R.raw.epic_flute);
        music.setLooping(true);
        pause.setVisibility(View.GONE);

        showCustomUI();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Intent intent = getIntent();
        l = intent.getLongExtra("time", 900000);
        String t = intent.getStringExtra("text");
        textView.setText(t);

        pB.setMax((int)l);

        play.setOnClickListener(v -> {
            startTimer(l);
            play.setVisibility(View.GONE);
            pause.setVisibility(View.VISIBLE);
        });

        pause.setOnClickListener(v -> {
            timer.setText("Paused");
            music.pause();
            stopWatch.cancel();
            pause.setVisibility(View.GONE);
            play.setVisibility(View.VISIBLE);
        });

        stop.setOnClickListener(v -> showWarning());

        close.setOnClickListener(v -> showWarning());

        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (b) {
                    showWarning();
                } else {
                    finish();
                }
            }
        };

        getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);
    }

    public void showWarning(){
        LayoutInflater layoutInflater = (LayoutInflater)
                this.getSystemService(LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View popupView = layoutInflater.inflate(R.layout.medi_finish, null);
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

        Button b1 = popupView.findViewById(R.id.cancel);
        Button b2 = popupView.findViewById(R.id.confirm);

        b1.setOnClickListener(v -> popupWindow.dismiss());

        b2.setOnClickListener(v -> finish());

        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
        dimBehind(popupWindow);
    }

    public void startTimer(long millis){
        music.start();
        stopWatch = new CountDownTimer(millis, 1000) {
            public void onTick(long millisUntilFinished) {
                NumberFormat f = new DecimalFormat("00");
                long hour = (millisUntilFinished / 3600000) % 24;
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                timer.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
                pB.setProgress((int)millisUntilFinished);
                l = millisUntilFinished;
                mediTime = f.format(hour) + ":" + f.format(min) + ":" + f.format(sec);
            }
            public void onFinish() {
                timer.setText("00:00:00");
            }
        }.start();
        b = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(b){
            music.stop();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(b){
            music.pause();
            stopWatch.cancel();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(b){
            music.pause();
            stopWatch.cancel();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(b){
            music.start();
            startTimer(l);
        }
    }

    public void selectBg1(){
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.mediwall1));
    }

    public void selectBg2(){
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.mediwall2));
    }

    public void selectBg3(){
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.mediwall3));
    }

    public void selectBg4(){
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.mediwall4));
    }

    public void selectBg5(){
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.mediwall5));
    }

    public void mediSettings(View v){
        LayoutInflater layoutInflater = (LayoutInflater)
                this.getSystemService(LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View popupView = layoutInflater.inflate(R.layout.meditation_settings, null);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

        ImageView iv1 = popupView.findViewById(R.id.mediwall1);
        ImageView iv2 = popupView.findViewById(R.id.mediwall2);
        ImageView iv3 = popupView.findViewById(R.id.mediwall3);
        ImageView iv4 = popupView.findViewById(R.id.mediwall4);
        ImageView iv5 = popupView.findViewById(R.id.mediwall5);

        ImageView m1 = popupView.findViewById(R.id.m1);
        ImageView m2 = popupView.findViewById(R.id.m2);
        ImageView m3 = popupView.findViewById(R.id.m3);
        ImageView m4 = popupView.findViewById(R.id.m4);
        ImageView m5 = popupView.findViewById(R.id.m5);
        ImageView m6 = popupView.findViewById(R.id.m6);

        ImageButton ib = popupView.findViewById(R.id.close);

        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBg1();
            }
        });

        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBg2();
            }
        });

        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBg3();
            }
        });

        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBg4();
            }
        });

        iv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBg5();
            }
        });

        m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectMusic("rainfall");
            }
        });

        m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectMusic("epic_flute");
            }
        });

        m3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectMusic("mindfulness_relaxation");
            }
        });

        m4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectMusic("healing");
            }
        });

        m5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectMusic("peaceful_garden");
            }
        });

        m6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectMusic("portal");
            }
        });

        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
        dimBehind(popupWindow);
    }

    public void selectMusic(String file){
        music.reset();
        music = MediaPlayer.create(getApplicationContext(), getResources().getIdentifier(file.toLowerCase(),"raw",getPackageName()));
        music.setLooping(true);
        music.start();
    }

    public static void dimBehind(PopupWindow popupWindow) {
        View container = popupWindow.getContentView().getRootView();
        Context context = popupWindow.getContentView().getContext();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.5f;
        wm.updateViewLayout(container, p);
    }

    private void showCustomUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
}