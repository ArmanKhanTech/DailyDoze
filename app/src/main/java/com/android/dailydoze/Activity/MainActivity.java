package com.android.dailydoze.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.dailydoze.Database.DailyDozeDatabase;
import com.android.dailydoze.R;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Button b;
    DrawerLayout drawerLayout;
    CheckBox beans_cb1, beans_cb2, beans_cb3, berries_cb1, greens_cb1, greens_cb2, othervege_cb1, othervege_cb2, of_cb1, of_cb2, of_cb3,
            cv_cb1, flaxseeds_cb1, herbs_cb1, nuts_cb1, grains_cb1, grains_cb2, grains_cb3, beve_cb1, beve_cb2, beve_cb3,
            beve_cb4, beve_cb5, exercise_cb1;
    TextView currDate, textView2;
    DailyDozeDatabase db;
    ImageButton jumpBack, date_prev, date_next;
    FrameLayout frameLayout;
    boolean today, jump = false;
    int inc = 0, dec = 0;

    //TODO:FIX green and othervege, negcal, un_meal,& improve noti icon, delay while switching layout , login black screen
    @SuppressLint({"NonConstantResourceId", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationMenu = findViewById(R.id.nav_menu);
        navigationMenu.setItemIconTintList(null);

        drawerLayout = findViewById(R.id.drawerLayout);
        b = findViewById(R.id.nav_button);
        b.setOnClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));

        currDate = findViewById(R.id.date);
        currDate.setText(setCurrentDate());

        db = new DailyDozeDatabase(this);

        beans_cb1 = findViewById(R.id.beans_cb1);
        beans_cb2 = findViewById(R.id.beans_cb2);
        beans_cb3 = findViewById(R.id.beans_cb3);

        berries_cb1 = findViewById(R.id.berries_cb1);

        greens_cb1 = findViewById(R.id.greens_cb1);
        greens_cb2 = findViewById(R.id.greens_cb2);

        othervege_cb1 = findViewById(R.id.othervege_cb1);
        othervege_cb2 = findViewById(R.id.othervege_cb2);

        of_cb1 = findViewById(R.id.of_cb1);
        of_cb2 = findViewById(R.id.of_cb2);
        of_cb3 = findViewById(R.id.of_cb3);

        cv_cb1 = findViewById(R.id.cv_cb1);

        flaxseeds_cb1 = findViewById(R.id.flaxseeds_cb1);

        herbs_cb1 = findViewById(R.id.herbs_cb1);

        nuts_cb1 = findViewById(R.id.nuts_cb1);

        grains_cb1 = findViewById(R.id.grains_cb1);
        grains_cb2 = findViewById(R.id.grains_cb2);
        grains_cb3 = findViewById(R.id.grains_cb3);

        beve_cb1 = findViewById(R.id.beverages_cb1);
        beve_cb2 = findViewById(R.id.beverages_cb2);
        beve_cb3 = findViewById(R.id.beverages_cb3);
        beve_cb4 = findViewById(R.id.beverages_cb4);
        beve_cb5 = findViewById(R.id.beverages_cb5);

        exercise_cb1 = findViewById(R.id.exercise_cb1);

        jumpBack = findViewById(R.id.jump_back);
        frameLayout = findViewById(R.id.frameLayout);
        date_next = findViewById(R.id.date_next);
        date_prev = findViewById(R.id.date_prev);

        textView2 = findViewById(R.id.textView2);

        setDay();

        beans_cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    incValue("beans");
                }else{
                    decValue("beans");
                }
            }
        });

        beans_cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    incValue("beans");
                }else{
                    decValue("beans");
                }
            }
        });

        beans_cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    incValue("beans");
                }else{
                    decValue("beans");
                }
            }
        });

        berries_cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    incValue("berries");
                }else{
                    decValue("berries");
                }
            }
        });

        greens_cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    incValue("greens");
                }else{
                    decValue("greens");
                }
            }
        });

        greens_cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    incValue("greens");
                }else{
                    decValue("greens");
                }
            }
        });

        othervege_cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    incValue("othervege");
                }else{
                    decValue("othervege");
                }
            }
        });

        othervege_cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    incValue("othervege");
                }else{
                    decValue("othervege");
                }
            }
        });

        of_cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    incValue("otherfruits");
                }else{
                    decValue("otherfruits");
                }
            }
        });

        of_cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    incValue("otherfruits");
                }else{
                    decValue("otherfruits");
                }
            }
        });

        of_cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    incValue("otherfruits");
                }else{
                    decValue("otherfruits");
                }
            }
        });

        cv_cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    incValue("crucivege");
                }else{
                    decValue("crucivege");
                }
            }
        });

        flaxseeds_cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    incValue("flaxseeds");
                }else{
                    decValue("flaxseeds");
                }
            }
        });

        herbs_cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    incValue("herbs");
                }else{
                    decValue("herbs");
                }
            }
        });

        nuts_cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    incValue("nuts");
                }else{
                    decValue("nuts");
                }
            }
        });

        grains_cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    incValue("grains");
                }else{
                    decValue("grains");
                }
            }
        });

        grains_cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    incValue("grains");
                }else{
                    decValue("grains");
                }
            }
        });

        grains_cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    incValue("grains");
                }else{
                    decValue("grains");
                }
            }
        });

        beve_cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    incValue("beve");
                }else{
                    decValue("beve");
                }
            }
        });

        beve_cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    incValue("beve");
                }else{
                    decValue("beve");
                }
            }
        });

        beve_cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    incValue("beve");
                }else{
                    decValue("beve");
                }
            }
        });

        beve_cb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    incValue("beve");
                }else{
                    decValue("beve");
                }
            }
        });

        beve_cb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    incValue("beve");
                }else{
                    decValue("beve");
                }
            }
        });

        exercise_cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    incValue("exercise");
                }else{
                    decValue("exercise");
                }
            }
        });

        date_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        date_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        jumpBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tDate = setCurrentDate();
                currDate.setText(tDate);
                jump = false;
                setDay();
            }
        });

        navigationMenu.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu1:
                    startActivity(new Intent(this, TweakActivity.class));
                    break;

                case R.id.menu2:
                    LayoutInflater layoutInflater = (LayoutInflater)
                            this.getSystemService(LAYOUT_INFLATER_SERVICE);
                    @SuppressLint("InflateParams") View popupView = layoutInflater.inflate(R.layout.date_popup, null);
                    int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                    int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

                    EditText day = popupView.findViewById(R.id.day);
                    EditText month = popupView.findViewById(R.id.month);
                    EditText year = popupView.findViewById(R.id.year);

                    Button enter = popupView.findViewById(R.id.enter);

                    enter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String d = day.getText().toString();
                            String m = month.getText().toString();
                            String y = year.getText().toString();

                            if(d.isEmpty() || m.isEmpty() || y.isEmpty()){
                                android.os.Handler h = new Handler();
                                h.postDelayed(() -> {
                                    enter.setText("Enter");
                                }, 2000);
                                enter.setText("Please Enter all Input/s");
                            } else if(Integer.parseInt(d) > 31 || Integer.parseInt(m) > 12 || Integer.parseInt(y) > 2030){
                                Handler h = new Handler();
                                h.postDelayed(() -> {
                                    enter.setText("Submit");
                                }, 2000);
                                enter.setText("Invalid Date");
                            } else{
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(Integer.parseInt(y) , Integer.parseInt(m) - 1 , Integer.parseInt(d), 0, 0);
                                SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
                                String formattedDate = df.format(calendar.getTime());

                                if(db.getDate(formattedDate)){
                                    currDate.setText(formattedDate);
                                    jump = true;
                                    setDay();
                                    popupWindow.dismiss();
                                } else{
                                    Handler h = new Handler();
                                    h.postDelayed(() -> {
                                        enter.setText("Submit");
                                    }, 2000);
                                    enter.setText("Record Doesn't Exists");
                                }
                            }
                        }
                    });

                    drawerLayout.close();
                    popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
                    dimBehind(popupWindow);
                    break;

                case R.id.menu3:
                    startActivity(new Intent(this, NotificationActivity.class));
                    break;

                case R.id.menu4:
                    startActivity(new Intent(this, FastActivity.class));
                    break;

                case R.id.menu5:
                    startActivity(new Intent(this, MeditationActivity.class));
                    break;

                case R.id.menu6:
                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putBoolean("user", false);
                    myEdit.apply();
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                    break;
            }
            return false;
        });
    }

    public void setPrev(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -1);
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        String formattedDate = df.format(c.getTime());
        //

        if(db.getDate(formattedDate)){
            date_prev.setVisibility(View.VISIBLE);
        } else{
            date_prev.setVisibility(View.GONE);
        }
    }

    public void setNext(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, +1);
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        String formattedDate = df.format(c.getTime());
        //

        if(db.getDate(formattedDate)){
            date_next.setVisibility(View.VISIBLE);
        } else{
            date_next.setVisibility(View.GONE);
        }
    }

    public void setCount(){
        int i = db.getCount(getCurrentDate());
        textView2.setText(String.valueOf(i));
    }

    public void incValue(String value){
        if(today){
            if(db.getDate(getCurrentDate())){
                db.incData(value, getCurrentDate());
            }else{
                db.addDate(getCurrentDate());
                db.incData(value, getCurrentDate());
            }
            setCount();
        }
    }

    public void decValue(String value){
        if(today){
            if(db.getDate(getCurrentDate())){
                db.decData(value, getCurrentDate());
            }else{
                db.addDate(getCurrentDate());
            }
            setCount();
        }
    }

    public void setDay(){
        if(Objects.equals(setCurrentDate(), getCurrentDate())){
            today = true;
            setClickable(true);
            setChecked();
            setCount();
            jumpBack.setVisibility(View.GONE);
            frameLayout.setBackground(getResources().getDrawable(R.drawable.info_img_theme));
            currDate.setTextColor(getColor(R.color.black));
            setNext();
            setPrev();
        }else{
            if(jump){
                today = false;
                setClickable(false);
                setChecked();
                setCount();
                jumpBack.setVisibility(View.VISIBLE);
                frameLayout.setBackground(getResources().getDrawable(R.drawable.date_back_theme));
                currDate.setTextColor(getColor(R.color.white));
                date_prev.setVisibility(View.GONE);
                date_next.setVisibility(View.GONE);
            } else{

            }
        }
    }

    public void setClickable(boolean b){
        beans_cb1.setClickable(b);
        beans_cb2.setClickable(b);
        beans_cb3.setClickable(b);

        berries_cb1.setClickable(b);

        greens_cb1.setClickable(b);
        greens_cb2.setClickable(b);

        othervege_cb1.setClickable(b);
        othervege_cb2.setClickable(b);

        of_cb1.setClickable(b);
        of_cb2.setClickable(b);
        of_cb3.setClickable(b);

        cv_cb1.setClickable(b);

        flaxseeds_cb1.setClickable(b);

        herbs_cb1.setClickable(b);

        nuts_cb1.setClickable(b);

        grains_cb1.setClickable(b);
        grains_cb2.setClickable(b);
        grains_cb3.setClickable(b);

        beve_cb1.setClickable(b);
        beve_cb2.setClickable(b);
        beve_cb3.setClickable(b);
        beve_cb4.setClickable(b);
        beve_cb5.setClickable(b);

        exercise_cb1.setClickable(b);
    }

    public void setChecked(){
        int i = db.getData("beans", getCurrentDate());
        if(i==1){
            beans_cb1.setChecked(true);
        } else if (i==2) {
            beans_cb1.setChecked(true);
            beans_cb2.setChecked(true);
        } else if (i==3) {
            beans_cb1.setChecked(true);
            beans_cb2.setChecked(true);
            beans_cb3.setChecked(true);
        }

        i = db.getData("berries", getCurrentDate());
        if(i==1){
            berries_cb1.setChecked(true);
        }

        i = db.getData("greens", getCurrentDate());
        if(i==1){
            greens_cb1.setChecked(true);
        } else if (i==2) {
            greens_cb1.setChecked(true);
            greens_cb2.setChecked(true);
        }

        i = db.getData("othervege", getCurrentDate());
        if(i==1){
            othervege_cb1.setChecked(true);
        } else if (i==2) {
            othervege_cb1.setChecked(true);
            othervege_cb2.setChecked(true);
        }

        i = db.getData("otherfruits", getCurrentDate());
        if(i==1){
            of_cb1.setChecked(true);
        } else if (i==2) {
            of_cb1.setChecked(true);
            of_cb2.setChecked(true);
        } else if (i==3) {
            of_cb1.setChecked(true);
            of_cb2.setChecked(true);
            of_cb3.setChecked(true);
        }

        i = db.getData("crucivege", getCurrentDate());
        if(i==1){
            cv_cb1.setChecked(true);
        }

        i = db.getData("flaxseeds", getCurrentDate());
        if(i==1){
            flaxseeds_cb1.setChecked(true);
        }

        i = db.getData("herbs", getCurrentDate());
        if(i==1){
            herbs_cb1.setChecked(true);
        }

        i = db.getData("nuts", getCurrentDate());
        if(i==1){
            nuts_cb1.setChecked(true);
        }

        i = db.getData("grains", getCurrentDate());
        if(i==1){
            grains_cb1.setChecked(true);
        } else if (i==2) {
            grains_cb1.setChecked(true);
            grains_cb2.setChecked(true);
        } else if (i==3) {
            grains_cb1.setChecked(true);
            grains_cb2.setChecked(true);
            grains_cb3.setChecked(true);
        }

        i = db.getData("beve", getCurrentDate());
        if(i==1){
            beve_cb1.setChecked(true);
        } else if (i==2) {
            beve_cb1.setChecked(true);
            beve_cb2.setChecked(true);
        } else if (i==3) {
            beve_cb1.setChecked(true);
            beve_cb2.setChecked(true);
            beve_cb3.setChecked(true);
        } else if (i==4){
            beve_cb1.setChecked(true);
            beve_cb2.setChecked(true);
            beve_cb3.setChecked(true);
            beve_cb4.setChecked(true);
        } else if (i==5) {
            beve_cb1.setChecked(true);
            beve_cb2.setChecked(true);
            beve_cb3.setChecked(true);
            beve_cb4.setChecked(true);
            beve_cb5.setChecked(true);
        }

        i = db.getData("exercise", getCurrentDate());
        if(i==1){
            exercise_cb1.setChecked(true);
        }
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

    public String setCurrentDate(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        return formattedDate;
    }

    public String getCurrentDate(){
        return String.valueOf(currDate.getText());
    }

    public void gotoGraph(View v) {
        Intent intent = new Intent(this, GraphActivity.class);
        intent.putExtra("GraphOf", "DailyDoze");
        startActivity(intent);
    }

    // avoid writing boilerplate code
    public void beansInfo(View v){
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("title","beans");
        startActivity(intent);
    }

    public void berriesInfo(View v){
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("title","berries");
        startActivity(intent);
    }

    public void fruitsInfo(View v){
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("title","fruits");
        startActivity(intent);
    }

    public void crucivegeInfo(View v){
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("title","crucivege");
        startActivity(intent);
    }

    public void greensInfo(View v){
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("title","greens");
        startActivity(intent);
    }

    public void othervegeInfo(View v){
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("title","othervege");
        startActivity(intent);
    }

    public void flexseedsInfo(View v){
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("title","flexseeds");
        startActivity(intent);
    }

    public void herbsInfo(View v){
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("title","herbs");
        startActivity(intent);
    }

    public void beveragesInfo(View v){
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("title","beverages");
        startActivity(intent);
    }

    public void nutsInfo(View v){
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("title","nuts");
        startActivity(intent);
    }

    public void grainsInfo(View v){
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("title","grains");
        startActivity(intent);
    }

    public void exerciseInfo(View v){
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("title","exercise");
        startActivity(intent);
    }

    public void beansCal(View v){
        Intent intent = new Intent(this, CalenderActivity.class);
        intent.putExtra("title","beans");
        startActivity(intent);
    }

    public void berriesCal(View v){
        Intent intent = new Intent(this, CalenderActivity.class);
        intent.putExtra("title","berries");
        startActivity(intent);
    }

    public void fruitsCal(View v){
        Intent intent = new Intent(this, CalenderActivity.class);
        intent.putExtra("title","fruits");
        startActivity(intent);
    }

    public void crucivegeCal(View v){
        Intent intent = new Intent(this, CalenderActivity.class);
        intent.putExtra("title","crucivege");
        startActivity(intent);
    }

    public void greensCal(View v){
        Intent intent = new Intent(this, CalenderActivity.class);
        intent.putExtra("title","greens");
        startActivity(intent);
    }

    public void othervegeCal(View v){
        Intent intent = new Intent(this, CalenderActivity.class);
        intent.putExtra("title","othervege");
        startActivity(intent);
    }

    public void flexseedsCal(View v){
        Intent intent = new Intent(this, CalenderActivity.class);
        intent.putExtra("title","flexseeds");
        startActivity(intent);
    }

    public void herbsCal(View v){
        Intent intent = new Intent(this, CalenderActivity.class);
        intent.putExtra("title","herbs");
        startActivity(intent);
    }

    public void beveragesCal(View v){
        Intent intent = new Intent(this, CalenderActivity.class);
        intent.putExtra("title","beverages");
        startActivity(intent);
    }

    public void nutsCal(View v){
        Intent intent = new Intent(this, CalenderActivity.class);
        intent.putExtra("title","nuts");
        startActivity(intent);
    }

    public void grainsCal(View v){
        Intent intent = new Intent(this, CalenderActivity.class);
        intent.putExtra("title","grains");
        startActivity(intent);
    }

    public void exerciseCal(View v){
        Intent intent = new Intent(this, CalenderActivity.class);
        intent.putExtra("title","exercise");
        startActivity(intent);
    }
}

