package com.android.dailydoze.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    ProgressBar pb;
    LinearLayout lll;
    TextView tvv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweak);

        LoadFragment loadFragment = new LoadFragment();
        loadFragment.execute();
    }

    private void loadFragment(){
        tabLayout = findViewById(R.id.tablayout_tweaks);
        viewPager = findViewById(R.id.viewPager_tweaks);
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new DayFragment(),"Day");
        viewPagerAdapter.addFragment(new EachMealFragment(),"Meals");
        viewPagerAdapter.addFragment(new NightFragment(),"Night");
        viewPager.setOffscreenPageLimit(0);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void gotoGraphTweaks(View v){
        Intent i = new Intent(this, GraphActivity.class);
        i.putExtra("tweak",true);
        startActivity(i);
    }

    private final class LoadFragment extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pb = findViewById(R.id.loadingFragment);
            pb.setVisibility(View.VISIBLE);
            lll = findViewById(R.id.lll);
            lll.setVisibility(View.GONE);
            tvv = findViewById(R.id.tvv);
            tvv.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            loadFragment();
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            lll.setVisibility(View.VISIBLE);
            pb.setVisibility(View.GONE);
            tvv.setVisibility(View.GONE);
        }
    }

    public void tweaksFinish(View v){
        finish();
    }
}