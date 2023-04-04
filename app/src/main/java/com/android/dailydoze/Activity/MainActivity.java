package com.android.dailydoze.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.android.dailydoze.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    Button b;
    DrawerLayout drawerLayout;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationMenu = findViewById(R.id.nav_menu);
        navigationMenu.setItemIconTintList(null);

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
            }
            return false;
        });
    }

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
}

