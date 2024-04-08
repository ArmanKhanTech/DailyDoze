package com.android.dailydoze.Fragments;

import static com.android.dailydoze.Activity.TweakActivity.getCurrentDate;
import static com.android.dailydoze.Activity.TweakActivity.setCurrentDate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.android.dailydoze.Activity.CalenderActivity;
import com.android.dailydoze.Activity.InfoActivity;
import com.android.dailydoze.Activity.TweakActivity;
import com.android.dailydoze.Database.TweaksDatabase;
import com.android.dailydoze.R;

import java.util.Objects;


public class NightFragment extends Fragment {
    @SuppressLint("StaticFieldLeak")
    private static CheckBox fast_cb1, sleep_cb1, exp_cb1;
    private static TweaksDatabase db;

    public NightFragment() {
    }

    public static void setDay() {
        setClickable(Objects.equals(setCurrentDate(), getCurrentDate()));
        unCheckAll();
        setChecked();
    }

    public static void setClickable(boolean b) {
        fast_cb1.setClickable(b);
        sleep_cb1.setClickable(b);
        exp_cb1.setClickable(b);
    }

    public static void unCheckAll() {
        fast_cb1.setChecked(false);
        sleep_cb1.setChecked(false);
        exp_cb1.setChecked(false);
    }

    public static void setChecked() {
        int i = db.getData("fast", getCurrentDate());
        if (i == 1) {
            fast_cb1.setChecked(true);
        }

        i = db.getData("sleep", getCurrentDate());
        if (i == 1) {
            sleep_cb1.setChecked(true);
        }

        i = db.getData("exp", getCurrentDate());
        if (i == 1) {
            exp_cb1.setChecked(true);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_night, container, false);

        ImageView i1 = view.findViewById(R.id.fastInfo);
        ImageView i2 = view.findViewById(R.id.sleepInfo);
        ImageView i3 = view.findViewById(R.id.expInfo);

        ImageView c1 = view.findViewById(R.id.fastCal);
        ImageView c2 = view.findViewById(R.id.sleepCal);
        ImageView c3 = view.findViewById(R.id.expCal);

        fast_cb1 = view.findViewById(R.id.fast_cb1);
        sleep_cb1 = view.findViewById(R.id.sleep_cb1);
        exp_cb1 = view.findViewById(R.id.exp_cb1);

        db = new TweaksDatabase(getActivity());

        setDay();

        fast_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("fast");
            } else {
                decValue("fast");
            }
        });

        sleep_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("sleep");
            } else {
                decValue("sleep");
            }
        });

        exp_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("exp");
            } else {
                decValue("exp");
            }
        });

        i1.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InfoActivity.class);
            intent.putExtra("title", "fast");
            intent.putExtra("tweak", true);
            startActivity(intent);
        });

        i2.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InfoActivity.class);
            intent.putExtra("title", "sleep");
            intent.putExtra("tweak", true);
            startActivity(intent);
        });

        i3.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InfoActivity.class);
            intent.putExtra("title", "exp");
            intent.putExtra("tweak", true);
            startActivity(intent);
        });

        c1.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CalenderActivity.class);
            intent.putExtra("title", "fast");
            startActivity(intent);
        });

        c2.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CalenderActivity.class);
            intent.putExtra("title", "sleep");
            startActivity(intent);
        });

        c3.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CalenderActivity.class);
            intent.putExtra("title", "exp");
            startActivity(intent);
        });

        return view;
    }

    public void incValue(String value) {
        if (TweakActivity.today) {
            if (db.getDate(getCurrentDate())) {
                db.incData(value, getCurrentDate());
            } else {
                db.addDate(getCurrentDate());
                db.incData(value, getCurrentDate());
            }

            TweakActivity.setCount(getCurrentDate());
        }
    }

    public void decValue(String value) {
        if (TweakActivity.today) {
            if (db.getDate(getCurrentDate())) {
                db.decData(value, getCurrentDate());
            } else {
                db.addDate(getCurrentDate());
            }

            TweakActivity.setCount(getCurrentDate());
        }
    }
}