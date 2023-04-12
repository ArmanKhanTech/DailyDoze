package com.android.dailydoze.Fragments;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.dailydoze.Activity.CalenderActivity;
import com.android.dailydoze.Activity.InfoActivity;
import com.android.dailydoze.R;

import java.util.Calendar;

public class EachMealFragment extends Fragment {
    ImageView i1, i2, i3, i4, i5, weight;
    ImageView c1, c2, c3, c4, c5;
    TextView textView;
    public EachMealFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_each_meal, container, false);

        textView = (TextView) view.findViewById(R.id.date_tweak_meal);

        i1 = view.findViewById(R.id.waterInfo);
        i2 = view.findViewById(R.id.vinegarInfo);
        i3 = view.findViewById(R.id.negInfo);
        i4 = view.findViewById(R.id.un_mealInfo);
        i5 = view.findViewById(R.id.tweminInfo);

        c1 = view.findViewById(R.id.waterCal);
        c2 = view.findViewById(R.id.vinegarCal);
        c3 = view.findViewById(R.id.negCal);
        c4 = view.findViewById(R.id.un_mealCal);
        c5 = view.findViewById(R.id.tweminCal);

        weight = view.findViewById(R.id.weight_tweak_meal);

        weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = (LayoutInflater)
                        getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
                @SuppressLint("InflateParams") View popupView = layoutInflater.inflate(R.layout.get_weight, null);
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);
                TextView tv = popupView.findViewById(R.id.timing);
                EditText et = popupView.findViewById(R.id.weight);
                Button b = popupView.findViewById(R.id.save);

                Calendar c = Calendar.getInstance();
                int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

                if(timeOfDay >= 6 && timeOfDay < 12){
                    tv.setText("Morning");
                    //
                }else if(timeOfDay >= 18 && timeOfDay < 24){
                    tv.setText("Night");
                    //
                }else{
                    tv.setText("You can only enter your weight in morning or night");
                    et.setVisibility(View.GONE);
                    b.setVisibility(View.GONE);
                }

                popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
                dimBehind(popupWindow);
            }
        });

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                intent.putExtra("title","water");
                intent.putExtra("tweak",true);
                startActivity(intent);
            }
        });

        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                intent.putExtra("title","vinegar");
                intent.putExtra("tweak",true);
                startActivity(intent);
            }
        });

        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                intent.putExtra("title","neg");
                intent.putExtra("tweak",true);
                startActivity(intent);
            }
        });

        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                intent.putExtra("title","un_meal");
                intent.putExtra("tweak",true);
                startActivity(intent);
            }
        });

        i5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                intent.putExtra("title","twemin");
                intent.putExtra("tweak",true);
                startActivity(intent);
            }
        });

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalenderActivity.class);
                intent.putExtra("title","water");
                startActivity(intent);
            }
        });


        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalenderActivity.class);
                intent.putExtra("title","vinegar");
                startActivity(intent);
            }
        });


        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalenderActivity.class);
                intent.putExtra("title","neg");
                startActivity(intent);
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalenderActivity.class);
                intent.putExtra("title","un_meal");
                startActivity(intent);
            }
        });

        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalenderActivity.class);
                intent.putExtra("title","twemin");
                startActivity(intent);
            }
        });

        setCurrentDate();

        return view;
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

    public void setCurrentDate(){
        Calendar c = Calendar.getInstance();
        String[]monthName={"January","February","March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};
        String month=monthName[c.get(Calendar.MONTH)];
        int year=c.get(Calendar.YEAR);
        int date=c.get(Calendar.DATE);
        textView.setText(date+" "+month+" "+year);
    }
}