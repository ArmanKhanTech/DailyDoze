package com.android.dailydoze.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.dailydoze.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    Button b;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationMenu = findViewById(R.id.nav_menu);
        drawerLayout = findViewById(R.id.drawerLayout);
        b = findViewById(R.id.nav_button);
        b.setOnClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));

        /*navigationMenu.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu1:
                    startActivity(new Intent(this, SettingActivity.class));
                    break;

                case R.id.menu2:
                    startActivity(new Intent(MainActivity.this, AnalysisActivity.class));
                    break;

                case R.id.menu4:
                    startActivity(new Intent(MainActivity.this, ParentalControlActivity.class));
                    break;

                case R.id.menu5:
                    startActivity(new Intent(MainActivity.this, AboutActivity.class));
                    break;
            }
            return false;
        });*/
    }
}

