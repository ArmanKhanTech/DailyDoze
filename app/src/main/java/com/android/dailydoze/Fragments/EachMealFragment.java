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

public class EachMealFragment extends Fragment {
    @SuppressLint("StaticFieldLeak")
    private static CheckBox water_cb1, water_cb2, water_cb3, vinegar_cb1, vinegar_cb2,
            vinegar_cb3, neg_cb1, neg_cb2, neg_cb3, un_meal_cb1, un_meal_cb2,
            un_meal_cb3, twe_min_cb1, twe_min_cb2, twe_min_cb3;
    private static TweaksDatabase db;

    public EachMealFragment() {
    }

    public static void setDay() {
        setClickable(Objects.equals(setCurrentDate(), getCurrentDate()));
        unCheckAll();
        setChecked();
    }

    public static void setClickable(boolean b) {
        water_cb1.setClickable(b);
        water_cb2.setClickable(b);
        water_cb3.setClickable(b);

        vinegar_cb1.setClickable(b);
        vinegar_cb2.setClickable(b);
        vinegar_cb3.setClickable(b);

        neg_cb1.setClickable(b);
        neg_cb2.setClickable(b);
        neg_cb3.setClickable(b);

        un_meal_cb1.setClickable(b);
        un_meal_cb2.setClickable(b);
        un_meal_cb3.setClickable(b);

        twe_min_cb1.setClickable(b);
        twe_min_cb2.setClickable(b);
        twe_min_cb3.setClickable(b);
    }

    public static void unCheckAll() {
        water_cb1.setChecked(false);
        water_cb2.setChecked(false);
        water_cb3.setChecked(false);

        vinegar_cb1.setChecked(false);
        vinegar_cb2.setChecked(false);
        vinegar_cb3.setChecked(false);

        neg_cb1.setChecked(false);
        neg_cb2.setChecked(false);
        neg_cb3.setChecked(false);

        un_meal_cb1.setChecked(false);
        un_meal_cb2.setChecked(false);
        un_meal_cb3.setChecked(false);

        twe_min_cb1.setChecked(false);
        twe_min_cb2.setChecked(false);
        twe_min_cb3.setChecked(false);
    }

    public static void setChecked() {
        int i = db.getData("water", getCurrentDate());
        if (i == 1) {
            water_cb1.setChecked(true);
        } else if (i == 2) {
            water_cb1.setChecked(true);
            water_cb2.setChecked(true);
        } else if (i == 3) {
            water_cb1.setChecked(true);
            water_cb2.setChecked(true);
            water_cb3.setChecked(true);
        }

        i = db.getData("vinegar", getCurrentDate());
        if (i == 1) {
            vinegar_cb1.setChecked(true);
        } else if (i == 2) {
            vinegar_cb1.setChecked(true);
            vinegar_cb2.setChecked(true);
        } else if (i == 3) {
            vinegar_cb1.setChecked(true);
            vinegar_cb2.setChecked(true);
            vinegar_cb3.setChecked(true);
        }

        i = db.getData("neg_cal", getCurrentDate());
        if (i == 1) {
            neg_cb1.setChecked(true);
        } else if (i == 2) {
            neg_cb1.setChecked(true);
            neg_cb2.setChecked(true);
        } else if (i == 3) {
            neg_cb1.setChecked(true);
            neg_cb2.setChecked(true);
            neg_cb3.setChecked(true);
        }

        i = db.getData("un_meal", getCurrentDate());
        if (i == 1) {
            un_meal_cb1.setChecked(true);
        } else if (i == 2) {
            un_meal_cb1.setChecked(true);
            un_meal_cb2.setChecked(true);
        } else if (i == 3) {
            un_meal_cb1.setChecked(true);
            un_meal_cb2.setChecked(true);
            un_meal_cb3.setChecked(true);
        }

        i = db.getData("twe_min", getCurrentDate());
        if (i == 1) {
            twe_min_cb1.setChecked(true);
        } else if (i == 2) {
            twe_min_cb1.setChecked(true);
            twe_min_cb2.setChecked(true);
        } else if (i == 3) {
            twe_min_cb1.setChecked(true);
            twe_min_cb2.setChecked(true);
            twe_min_cb3.setChecked(true);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_each_meal, container, false);

        ImageView i1 = view.findViewById(R.id.waterInfo);
        ImageView i2 = view.findViewById(R.id.vinegarInfo);
        ImageView i3 = view.findViewById(R.id.negInfo);
        ImageView i4 = view.findViewById(R.id.un_mealInfo);
        ImageView i5 = view.findViewById(R.id.tweminInfo);

        ImageView c1 = view.findViewById(R.id.waterCal);
        ImageView c2 = view.findViewById(R.id.vinegarCal);
        ImageView c3 = view.findViewById(R.id.negCal);
        ImageView c4 = view.findViewById(R.id.un_mealCal);
        ImageView c5 = view.findViewById(R.id.tweminCal);

        water_cb1 = view.findViewById(R.id.water_cb1);
        water_cb2 = view.findViewById(R.id.water_cb2);
        water_cb3 = view.findViewById(R.id.water_cb3);
        vinegar_cb1 = view.findViewById(R.id.vinegar_cb1);
        vinegar_cb2 = view.findViewById(R.id.vinegar_cb2);
        vinegar_cb3 = view.findViewById(R.id.vinegar_cb3);
        neg_cb1 = view.findViewById(R.id.neg_cb1);
        neg_cb2 = view.findViewById(R.id.neg_cb2);
        neg_cb3 = view.findViewById(R.id.neg_cb3);
        un_meal_cb1 = view.findViewById(R.id.un_meal_cb1);
        un_meal_cb2 = view.findViewById(R.id.un_meal_cb2);
        un_meal_cb3 = view.findViewById(R.id.un_meal_cb3);
        twe_min_cb1 = view.findViewById(R.id.twe_min_cb1);
        twe_min_cb2 = view.findViewById(R.id.twe_min_cb2);
        twe_min_cb3 = view.findViewById(R.id.twe_min_cb3);

        db = new TweaksDatabase(getActivity());

        setDay();

        water_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("water");
            } else {
                decValue("water");
            }
        });

        water_cb2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("water");
            } else {
                decValue("water");
            }
        });

        vinegar_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("vinegar");
            } else {
                decValue("vinegar");
            }
        });

        vinegar_cb2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("vinegar");
            } else {
                decValue("vinegar");
            }
        });

        vinegar_cb3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("vinegar");
            } else {
                decValue("vinegar");
            }
        });

        water_cb3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("water");
            } else {
                decValue("water");
            }
        });

        neg_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("neg_cal");
            } else {
                decValue("neg_cal");
            }
        });

        neg_cb2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("neg_cal");
            } else {
                decValue("neg_cal");
            }
        });

        neg_cb3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("neg_cal");
            } else {
                decValue("neg_cal");
            }
        });

        un_meal_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("un_meal");
            } else {
                decValue("un_meal");
            }
        });

        un_meal_cb2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("un_meal");
            } else {
                decValue("un_meal");
            }
        });

        un_meal_cb3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("un_meal");
            } else {
                decValue("un_meal");
            }
        });

        twe_min_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("twe_min");
            } else {
                decValue("twe_min");
            }
        });

        twe_min_cb2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("twe_min");
            } else {
                decValue("twe_min");
            }
        });

        twe_min_cb3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("twe_min");
            } else {
                decValue("twe_min");
            }
        });

        i1.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InfoActivity.class);
            intent.putExtra("title", "water");
            intent.putExtra("tweak", true);
            startActivity(intent);
        });

        i2.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InfoActivity.class);
            intent.putExtra("title", "vinegar");
            intent.putExtra("tweak", true);
            startActivity(intent);
        });

        i3.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InfoActivity.class);
            intent.putExtra("title", "neg");
            intent.putExtra("tweak", true);
            startActivity(intent);
        });

        i4.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InfoActivity.class);
            intent.putExtra("title", "un_meal");
            intent.putExtra("tweak", true);
            startActivity(intent);
        });

        i5.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InfoActivity.class);
            intent.putExtra("title", "twemin");
            intent.putExtra("tweak", true);
            startActivity(intent);
        });

        c1.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CalenderActivity.class);
            intent.putExtra("title", "water");
            startActivity(intent);
        });

        c2.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CalenderActivity.class);
            intent.putExtra("title", "vinegar");
            startActivity(intent);
        });

        c3.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CalenderActivity.class);
            intent.putExtra("title", "neg");
            startActivity(intent);
        });

        c4.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CalenderActivity.class);
            intent.putExtra("title", "un_meal");
            startActivity(intent);
        });

        c5.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CalenderActivity.class);
            intent.putExtra("title", "twemin");
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