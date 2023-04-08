package com.android.dailydoze.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.dailydoze.Activity.CalenderActivity;
import com.android.dailydoze.Activity.InfoActivity;
import com.android.dailydoze.R;

import java.util.Calendar;

public class DayFragment extends Fragment {
    ImageView i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13;
    ImageView c1, c2, c3, c4, c5, c6, c7,c8, c9, c10, c11, c12, c13;
    TextView textView;

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

        textView = (TextView) view.findViewById(R.id.date_tweak_day);

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

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                intent.putExtra("title","cumin");
                intent.putExtra("tweak",true);
                startActivity(intent);
            }
        });

        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                intent.putExtra("title","garlic");
                intent.putExtra("tweak",true);
                startActivity(intent);
            }
        });

        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                intent.putExtra("title","ginger");
                intent.putExtra("tweak",true);
                startActivity(intent);
            }
        });

        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                intent.putExtra("title","yeast");
                intent.putExtra("tweak",true);
                startActivity(intent);
            }
        });

        i5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                intent.putExtra("title","cumin2");
                intent.putExtra("tweak",true);
                startActivity(intent);
            }
        });

        i6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), InfoActivity.class);
                    intent.putExtra("title","green");
                    intent.putExtra("tweak",true);
                    startActivity(intent);
                }
        });

        i7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), InfoActivity.class);
                    intent.putExtra("title","hyd");
                    intent.putExtra("tweak",true);
                    startActivity(intent);
                }
        });

        i8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), InfoActivity.class);
                    intent.putExtra("title","deflour");
                    intent.putExtra("tweak",true);
                    startActivity(intent);
                }
        });

        i9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                intent.putExtra("title","front");
                intent.putExtra("tweak",true);
                startActivity(intent);
            }
        });

        i10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                intent.putExtra("title","restrict");
                intent.putExtra("tweak",true);
                startActivity(intent);
            }
        });

        i11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                intent.putExtra("title","optimize");
                intent.putExtra("tweak",true);
                startActivity(intent);
            }
        });

        i12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                intent.putExtra("title","weigh");
                intent.putExtra("tweak",true);
                startActivity(intent);
            }

        });

        i13.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), InfoActivity.class);
                    intent.putExtra("title","inten");
                    intent.putExtra("tweak",true);
                    startActivity(intent);
                }

        });

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalenderActivity.class);
                intent.putExtra("title","cumin");
                startActivity(intent);
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalenderActivity.class);
                intent.putExtra("title","garlic");
                startActivity(intent);
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalenderActivity.class);
                intent.putExtra("title","ginger");
                startActivity(intent);
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalenderActivity.class);
                intent.putExtra("title","yeast");
                startActivity(intent);
            }
        });

        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalenderActivity.class);
                intent.putExtra("title","cumin2");
                startActivity(intent);
            }
        });

        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalenderActivity.class);
                intent.putExtra("title","green");
                startActivity(intent);
            }
        });

        c7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalenderActivity.class);
                intent.putExtra("title","hyd");
                startActivity(intent);
            }
        });

        c8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalenderActivity.class);
                intent.putExtra("title","deflour");
                startActivity(intent);
            }
        });

        c9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalenderActivity.class);
                intent.putExtra("title","front");
                startActivity(intent);
            }
        });

        c10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalenderActivity.class);
                intent.putExtra("title","restrict");
                startActivity(intent);
            }
        });

        c11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalenderActivity.class);
                intent.putExtra("title","optimize");
                startActivity(intent);
            }
        });

        c12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalenderActivity.class);
                intent.putExtra("title","weigh");
                startActivity(intent);
            }

        });

        c13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalenderActivity.class);
                intent.putExtra("title","inten");
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