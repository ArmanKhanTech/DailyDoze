package com.android.dailydoze.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.android.dailydoze.Fragments.DayFragment;
import com.android.dailydoze.Fragments.EachMealFragment;
import com.android.dailydoze.Fragments.NightFragment;
import com.android.dailydoze.R;
import com.android.dailydoze.Utility.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class TweakActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweak);

        loadFragment();
    }

    private void loadFragment(){
        tabLayout = findViewById(R.id.tablayout_tweaks);
        viewPager = findViewById(R.id.viewPager_tweaks);
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new DayFragment(),"Day");
        viewPagerAdapter.addFragment(new EachMealFragment(),"Meal");
        viewPagerAdapter.addFragment(new NightFragment(),"Night");
        viewPager.setOffscreenPageLimit(0);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void tweaksFinish(View v){
        finish();
    }
}