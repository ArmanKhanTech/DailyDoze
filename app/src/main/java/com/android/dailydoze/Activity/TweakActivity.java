package com.android.dailydoze.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.dailydoze.Database.TweaksDatabase;
import com.android.dailydoze.Fragments.DayFragment;
import com.android.dailydoze.Fragments.EachMealFragment;
import com.android.dailydoze.Fragments.NightFragment;
import com.android.dailydoze.R;
import com.android.dailydoze.Utility.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class TweakActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    ProgressBar pb;
    LinearLayout lll;
    TextView tvv;
    @SuppressLint("StaticFieldLeak")
    static TextView currDate;
    @SuppressLint("StaticFieldLeak")
    static TextView textView10;
    static TweaksDatabase db;
    public static boolean today = true;
    ImageButton weight;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweak);

        textView10 = findViewById(R.id.textView10);
        currDate = findViewById(R.id.date_tweak);
        weight = findViewById(R.id.weight_tweak);

        currDate.setText(setCurrentDate());

        db = new TweaksDatabase(this);

        setCount(getCurrentDate());

        weight.setOnClickListener(v -> {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            @SuppressLint("InflateParams") View popupView = layoutInflater.inflate(R.layout.get_weight, null);
            int width = LinearLayout.LayoutParams.WRAP_CONTENT;
            int height = LinearLayout.LayoutParams.WRAP_CONTENT;
            final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);
            TextView tv = popupView.findViewById(R.id.timing);
            EditText et = popupView.findViewById(R.id.weight);
            Button b = popupView.findViewById(R.id.save);

            final String[] kg = {"0"};

            Calendar c = Calendar.getInstance();
            int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

            if(today){
                tv.setVisibility(View.VISIBLE);
                et.setVisibility(View.VISIBLE);
                if(timeOfDay >= 6 && timeOfDay < 12) {
                    tv.setText("Enter your morning weight");
                } else if (timeOfDay >= 18) {
                    tv.setText("Enter your evening weight");
                }
                b.setText("Save");
            }

            b.setOnClickListener(v1 -> {
                if(today) {
                    kg[0] = et.getText().toString();
                    if(!kg[0].isEmpty()){
                        if (timeOfDay >= 6 && timeOfDay < 12) {
                            if (db.getDate(getCurrentDate())) {
                                db.setWeight("weight_morning", getCurrentDate(), kg[0]);
                            } else {
                                db.addDate(getCurrentDate());
                                db.setWeight("weight_morning", getCurrentDate(), kg[0]);
                            }
                        } else if (timeOfDay >= 18) {
                            if (db.getDate(getCurrentDate())) {
                                db.setWeight("weight_evening", getCurrentDate(), kg[0]);
                            } else {
                                db.addDate(getCurrentDate());
                                db.setWeight("weight_evening", getCurrentDate(), kg[0]);
                            }
                        }
                        db.incData("weigh", getCurrentDate());
                        popupWindow.dismiss();
                    }else{
                        popupWindow.dismiss();
                    }
                }
            });

            popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
            dimBehind(popupWindow);
        });

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

    public static String setCurrentDate(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        return df.format(c);
    }

    public static String getCurrentDate(){
        return String.valueOf(currDate.getText());
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

    public static void setCount(String date){
        int i = db.getCount(date);
        textView10.setText(String.valueOf(i));
    }

    public void tweaksFinish(View v){
        finish();
    }
}