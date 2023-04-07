package com.android.dailydoze.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.android.dailydoze.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button b;
    DrawerLayout drawerLayout;

    //TODO:FIX green and othervege, negcal, un_meal, frontload image(getting streched)
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationMenu = findViewById(R.id.nav_menu);
        navigationMenu.setItemIconTintList(null);

        setCurrentDate();

        drawerLayout = findViewById(R.id.drawerLayout);
        b = findViewById(R.id.nav_button);
        b.setOnClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));

        navigationMenu.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu1:
                    startActivity(new Intent(this, TweakActivity.class));
                    break;

                case R.id.menu2:
                    break;

                case R.id.menu3:
                    break;

                case R.id.menu4:
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

    public void setCurrentDate(){
        Calendar c = Calendar.getInstance();
        String[]monthName={"January","February","March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};
        String month=monthName[c.get(Calendar.MONTH)];
        System.out.println("Month name:"+month);
        int year=c.get(Calendar.YEAR);
        int date=c.get(Calendar.DATE);
        TextView textView = (TextView)findViewById(R.id.date);
        textView.setText(date+" "+month+" "+year);
    }

    public void gotoGraph(View v) {
        startActivity(new Intent(MainActivity.this, GraphActivity.class));
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

