package com.android.dailydoze.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.dailydoze.Activity.CalenderActivity;
import com.android.dailydoze.Activity.InfoActivity;
import com.android.dailydoze.R;

import java.util.Calendar;


public class NightFragment extends Fragment {

    ImageView i1, i2, i3;
    ImageView c1, c2, c3;
    TextView textView;
    public NightFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_night, container, false);

        textView = (TextView) view.findViewById(R.id.date_tweak_night);

        i1 = view.findViewById(R.id.fastInfo);
        i2 = view.findViewById(R.id.sleepInfo);
        i3 = view.findViewById(R.id.expInfo);

        c1 = view.findViewById(R.id.fastCal);
        c2 = view.findViewById(R.id.sleepCal);
        c3 = view.findViewById(R.id.expCal);

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                intent.putExtra("title","fast");
                intent.putExtra("tweak",true);
                startActivity(intent);
            }
        });

        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                intent.putExtra("title","sleep");
                intent.putExtra("tweak",true);
                startActivity(intent);
            }
        });

        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                intent.putExtra("title","exp");
                intent.putExtra("tweak",true);
                startActivity(intent);
            }
        });

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalenderActivity.class);
                intent.putExtra("title","fast");
                startActivity(intent);
            }
        });


        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalenderActivity.class);
                intent.putExtra("title","sleep");
                startActivity(intent);
            }
        });


        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalenderActivity.class);
                intent.putExtra("title","exp");
                startActivity(intent);
            }
        });

        setCurrentDate();

        return view;
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
        textView.setText(date+" "+month+" "+year);
    }
}