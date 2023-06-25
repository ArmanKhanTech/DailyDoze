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

public class DayFragment extends Fragment {
    ImageView i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13;
    ImageView c1, c2, c3, c4, c5, c6, c7,c8, c9, c10, c11, c12, c13;
    @SuppressLint("StaticFieldLeak")
    static CheckBox cumin_cb1, garlic_cb1, ginger_cb1, yeast_cb1, cumin2_cb1, cumin2_cb2, green_cb1, green_cb2, green_cb3, hyd_cb1, deflour_cb1,
            front_cb1, restrict_cb1, optimize_cb1, weigh_cb1, weigh_cb2, inten_cb1, inten_cb2, inten_cb3;
    static TweaksDatabase db;

    public DayFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day, container, false);

        cumin_cb1 = view.findViewById(R.id.cumin_cb1);
        garlic_cb1 = view.findViewById(R.id.garlic_cb1);
        ginger_cb1 = view.findViewById(R.id.ginger_cb1);
        yeast_cb1 = view.findViewById(R.id.yeast_cb1);
        cumin2_cb1 = view.findViewById(R.id.cumin2_cb1);
        cumin2_cb2 = view.findViewById(R.id.cumin2_cb2);
        green_cb1 = view.findViewById(R.id.green_cb1);
        green_cb2 = view.findViewById(R.id.green_cb2);
        green_cb3 = view.findViewById(R.id.green_cb3);
        hyd_cb1 = view.findViewById(R.id.hyd_cb1);
        deflour_cb1 = view.findViewById(R.id.deflour_cb1);
        front_cb1 = view.findViewById(R.id.front_cb1);
        restrict_cb1 = view.findViewById(R.id.restrict_cb1);
        optimize_cb1 = view.findViewById(R.id.optimize_cb1);
        weigh_cb1 = view.findViewById(R.id.weigh_cb1);
        weigh_cb2 = view.findViewById(R.id.weigh_cb2);
        inten_cb1 = view.findViewById(R.id.inten_cb1);
        inten_cb2 = view.findViewById(R.id.inten_cb2);
        inten_cb3 = view.findViewById(R.id.inten_cb3);

        db = new TweaksDatabase(getActivity());

        setDay();

        if(!db.getDate(getCurrentDate())){
            db.addDate(getCurrentDate());
        }

        i1 = view.findViewById(R.id.cuminInfo);
        i2 = view.findViewById(R.id.garlicInfo);
        i3 = view.findViewById(R.id.gingerInfo);
        i4 = view.findViewById(R.id.yeastInfo);
        i5 = view.findViewById(R.id.cumin2Info);
        i6 = view.findViewById(R.id.greenInfo);
        i7 = view.findViewById(R.id.hydInfo);
        i8 = view.findViewById(R.id.deflourInfo);
        i9 = view.findViewById(R.id.frontInfo);
        i10 = view.findViewById(R.id.restrictInfo);
        i11 = view.findViewById(R.id.optimizeInfo);
        i12 = view.findViewById(R.id.weighInfo);
        i13 = view.findViewById(R.id.intenInfo);

        c1 = view.findViewById(R.id.cuminCal);
        c2 = view.findViewById(R.id.garlicCal);
        c3 = view.findViewById(R.id.gingerCal);
        c4 = view.findViewById(R.id.yeastCal);
        c5 = view.findViewById(R.id.cumin2Cal);
        c6 = view.findViewById(R.id.greenCal);
        c7 = view.findViewById(R.id.hydCal);
        c8 = view.findViewById(R.id.deflourCal);
        c9 = view.findViewById(R.id.frontCal);
        c10 = view.findViewById(R.id.restrictCal);
        c11 = view.findViewById(R.id.optimizeCal);
        c12 = view.findViewById(R.id.weighCal);
        c13 = view.findViewById(R.id.intenCal);

        cumin_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                incValue("black_cumin");
            }else{
                decValue("black_cumin");
            }
        });

        ginger_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                incValue("ginger");
            }else{
                decValue("ginger");
            }
        });

        garlic_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                incValue("garlic");
            }else{
                decValue("garlic");
            }
        });

        yeast_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                incValue("yeast");
            }else{
                decValue("yeast");
            }
        });

        cumin2_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                incValue("cumin");
            }else{
                decValue("cumin");
            }
        });

        cumin2_cb2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                incValue("cumin");
            }else{
                decValue("cumin");
            }
        });

        green_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                incValue("tea");
            }else{
                decValue("tea");
            }
        });

        green_cb2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                incValue("tea");
            }else{
                decValue("tea");
            }
        });

        green_cb3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                incValue("tea");
            }else{
                decValue("tea");
            }
        });

        hyd_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                incValue("hydrated");
            }else{
                decValue("hydrated");
            }
        });

        deflour_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                incValue("deflour");
            }else{
                decValue("deflour");
            }
        });

        front_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                incValue("frontload");
            }else{
                decValue("frontload");
            }
        });

        restrict_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                incValue("timerestrict");
            }else{
                decValue("timerestrict");
            }
        });

        optimize_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                incValue("optimize");
            }else{
                decValue("optimize");
            }
        });

        inten_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                incValue("intentions");
            }else{
                decValue("intentions");
            }
        });

        inten_cb2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                incValue("intentions");
            }else{
                decValue("intentions");
            }
        });

        inten_cb3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                incValue("intentions");
            }else{
                decValue("intentions");
            }
        });

        i1.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InfoActivity.class);
            intent.putExtra("title","cumin");
            intent.putExtra("tweak",true);
            startActivity(intent);
        });

        i2.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InfoActivity.class);
            intent.putExtra("title","garlic");
            intent.putExtra("tweak",true);
            startActivity(intent);
        });

        i3.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InfoActivity.class);
            intent.putExtra("title","ginger");
            intent.putExtra("tweak",true);
            startActivity(intent);
        });

        i4.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InfoActivity.class);
            intent.putExtra("title","yeast");
            intent.putExtra("tweak",true);
            startActivity(intent);
        });

        i5.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InfoActivity.class);
            intent.putExtra("title","cumin2");
            intent.putExtra("tweak",true);
            startActivity(intent);
        });

        i6.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InfoActivity.class);
            intent.putExtra("title","green");
            intent.putExtra("tweak",true);
            startActivity(intent);
        });

        i7.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InfoActivity.class);
            intent.putExtra("title","hyd");
            intent.putExtra("tweak",true);
            startActivity(intent);
        });

        i8.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InfoActivity.class);
            intent.putExtra("title","deflour");
            intent.putExtra("tweak",true);
            startActivity(intent);
        });

        i9.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InfoActivity.class);
            intent.putExtra("title","front");
            intent.putExtra("tweak",true);
            startActivity(intent);
        });

        i10.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InfoActivity.class);
            intent.putExtra("title","restrict");
            intent.putExtra("tweak",true);
            startActivity(intent);
        });

        i11.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InfoActivity.class);
            intent.putExtra("title","optimize");
            intent.putExtra("tweak",true);
            startActivity(intent);
        });

        i12.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InfoActivity.class);
            intent.putExtra("title","weigh");
            intent.putExtra("tweak",true);
            startActivity(intent);
        });

        i13.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InfoActivity.class);
            intent.putExtra("title","inten");
            intent.putExtra("tweak",true);
            startActivity(intent);
        });

        c1.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CalenderActivity.class);
            intent.putExtra("title","cumin");
            startActivity(intent);
        });

        c2.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CalenderActivity.class);
            intent.putExtra("title","garlic");
            startActivity(intent);
        });

        c3.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CalenderActivity.class);
            intent.putExtra("title","ginger");
            startActivity(intent);
        });

        c4.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CalenderActivity.class);
            intent.putExtra("title","yeast");
            startActivity(intent);
        });

        c5.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CalenderActivity.class);
            intent.putExtra("title","cumin2");
            startActivity(intent);
        });

        c6.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CalenderActivity.class);
            intent.putExtra("title","green");
            startActivity(intent);
        });

        c7.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CalenderActivity.class);
            intent.putExtra("title","hyd");
            startActivity(intent);
        });

        c8.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CalenderActivity.class);
            intent.putExtra("title","deflour");
            startActivity(intent);
        });

        c9.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CalenderActivity.class);
            intent.putExtra("title","front");
            startActivity(intent);
        });

        c10.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CalenderActivity.class);
            intent.putExtra("title","restrict");
            startActivity(intent);
        });

        c11.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CalenderActivity.class);
            intent.putExtra("title","optimize");
            startActivity(intent);
        });

        c12.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CalenderActivity.class);
            intent.putExtra("title","weigh");
            startActivity(intent);
        });

        c13.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CalenderActivity.class);
            intent.putExtra("title","inten");
            startActivity(intent);
        });

        return view;
    }

    public static void setDay(){
        setClickable(Objects.equals(setCurrentDate(), getCurrentDate()));
        unCheckAll();
        setChecked();
    }

    public static void setClickable(boolean b){
        cumin_cb1.setClickable(b);

        garlic_cb1.setClickable(b);

        ginger_cb1.setClickable(b);

        cumin2_cb1.setClickable(b);
        cumin2_cb2.setClickable(b);

        yeast_cb1.setClickable(b);

        green_cb1.setClickable(b);
        green_cb2.setClickable(b);
        green_cb3.setClickable(b);

        hyd_cb1.setClickable(b);

        deflour_cb1.setClickable(b);

        front_cb1.setClickable(b);

        restrict_cb1.setClickable(b);

        optimize_cb1.setClickable(b);

        weigh_cb1.setClickable(b);
        weigh_cb2.setClickable(b);

        inten_cb1.setClickable(b);
        inten_cb2.setClickable(b);
        inten_cb3.setClickable(b);
    }

    public static void unCheckAll(){
        cumin_cb1.setChecked(false);

        ginger_cb1.setChecked(false);

        garlic_cb1.setChecked(false);

        cumin2_cb1.setChecked(false);
        cumin2_cb2.setChecked(false);

        yeast_cb1.setChecked(false);

        green_cb1.setChecked(false);
        green_cb2.setChecked(false);
        green_cb3.setChecked(false);

        hyd_cb1.setChecked(false);

        deflour_cb1.setChecked(false);

        front_cb1.setChecked(false);

        restrict_cb1.setChecked(false);

        optimize_cb1.setChecked(false);

        weigh_cb1.setChecked(false);
        weigh_cb1.setChecked(false);

        inten_cb1.setChecked(false);
        inten_cb2.setChecked(false);
        inten_cb3.setChecked(false);
    }

    public static void setChecked(){
        int i = db.getData("black_cumin", getCurrentDate());
        if(i==1) {
            cumin_cb1.setChecked(true);
        }

        i = db.getData("garlic", getCurrentDate());
        if(i==1){
            garlic_cb1.setChecked(true);
        }

        i = db.getData("ginger", getCurrentDate());
        if(i==1){
            ginger_cb1.setChecked(true);
        }

        i = db.getData("yeast", getCurrentDate());
        if(i==1){
            yeast_cb1.setChecked(true);
        }

        i = db.getData("cumin", getCurrentDate());
        if(i==1){
            cumin2_cb1.setChecked(true);
        } else if (i==2) {
            cumin2_cb1.setChecked(true);
            cumin2_cb2.setChecked(true);
        }

        i = db.getData("tea", getCurrentDate());
        if(i==1){
            green_cb1.setChecked(true);
        } else if (i==2) {
            green_cb1.setChecked(true);
            green_cb2.setChecked(true);
        } else if (i==3) {
            green_cb1.setChecked(true);
            green_cb2.setChecked(true);
            green_cb3.setChecked(true);
        }

        i = db.getData("hydrated", getCurrentDate());
        if(i==1){
           hyd_cb1.setChecked(true);
        }

        i = db.getData("deflour", getCurrentDate());
        if(i==1){
            deflour_cb1.setChecked(true);
        }

        i = db.getData("frontload", getCurrentDate());
        if(i==1){
            front_cb1.setChecked(true);
        }

        i = db.getData("weigh", getCurrentDate());
        if(i==1){
            weigh_cb1.setChecked(true);
        } else if (i==2) {
            weigh_cb1.setChecked(true);
            weigh_cb2.setChecked(true);
        }

        i = db.getData("intentions", getCurrentDate());
        if(i==1){
            inten_cb1.setChecked(true);
        } else if (i==2) {
            inten_cb1.setChecked(true);
            inten_cb2.setChecked(true);
        } else if (i==3) {
            inten_cb1.setChecked(true);
            inten_cb2.setChecked(true);
            inten_cb3.setChecked(true);
        }

        i = db.getData("timerestrict", getCurrentDate());
        if(i==1){
            restrict_cb1.setChecked(true);
        }
    }

    public void incValue(String value){
        if(TweakActivity.today){
            if(db.getDate(getCurrentDate())){
                db.incData(value, getCurrentDate());
            }else{
                db.addDate(getCurrentDate());
                db.incData(value, getCurrentDate());
            }
            TweakActivity.setCount(getCurrentDate());
        }
    }

    public void decValue(String value){
        if(TweakActivity.today){
            if(db.getDate(getCurrentDate())){
                db.decData(value, getCurrentDate());
            }else{
                db.addDate(getCurrentDate());
            }
            TweakActivity.setCount(getCurrentDate());
        }
    }

}