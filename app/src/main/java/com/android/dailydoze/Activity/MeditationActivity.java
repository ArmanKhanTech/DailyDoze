package com.android.dailydoze.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.dailydoze.Database.MeditationDatabase;
import com.android.dailydoze.R;
import com.android.dailydoze.Utility.DataList;
import com.android.dailydoze.Utility.ListAdapter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class MeditationActivity extends AppCompatActivity {
    String time;
    long millis;
    ListView list;
    ArrayList<DataList> data = new ArrayList<>();
    ListAdapter adapter;
    MeditationDatabase db1;
    Drawable icon;
    TextView hisStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);

        time = "Meditation for 15 minutes";
        millis = 900000;

        RadioGroup rg = findViewById(R.id.radioGroup);
        list = findViewById(R.id.mediList);

        db1 = new MeditationDatabase(this);
        ArrayList<String> dates = db1.getAllDate();

        icon = getDrawable(R.drawable.medi_icon);

        for(int i = 0; i < dates.size(); i++){
            String temp = dates.get(i);
            data.add(new DataList(temp, icon));
        }

        adapter = new ListAdapter(this,data);
        list.setAdapter(adapter);

        hisStatus = findViewById(R.id.mediHisStatus);

        if(data.isEmpty()){
            hisStatus.setText("Nothing to Show");
        } else{
            hisStatus.setVisibility(View.GONE);
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataList mediList = adapter.getItem(position);
                String t = mediList.getText();
                String d = db1.getDuration(t);
                d = millisToTime(Long.parseLong(d));
                LayoutInflater layoutInflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
                @SuppressLint("InflateParams") View popupView = layoutInflater.inflate(R.layout.noti_popup, null);
                int width = LinearLayout.LayoutParams.MATCH_PARENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                Button bb = popupView.findViewById(R.id.delete);
                TextView tv = popupView.findViewById(R.id.notiPopText);
                tv.setText("You Meditated for " + d + " on " + t);
                bb.setText("Okay");
                bb.setOnClickListener(view1 -> {
                    popupWindow.dismiss();
                });
                dimBehind(popupWindow);
            }
        });


        rg.setOnCheckedChangeListener((group, checkedId) -> {
            switch(checkedId){
                case R.id.fifmins:
                    time = "Meditation for 15 minutes";
                    millis = 900000;
                    break;
                case R.id.thimins:
                    time = "Meditation for 30 minutes";
                    millis = 1800000;
                    break;
                case R.id.foumins:
                    time = "Meditation for 45 minutes";
                    millis = 2700000;
                    break;
                case R.id.other:
                    time = "Meditation for 1 hour";
                    millis = 3600000;
                    break;
            }
        });
    }

    public String millisToTime(long t){
        NumberFormat f = new DecimalFormat("00");
        long hour = (t / 3600000) % 24;
        long min = (t / 60000) % 60;
        long sec = (t / 1000) % 60;

        return f.format(hour) + ":" + f.format(min) + ":" + f.format(sec);
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

    public void openPerformMeditation(View v){
        Intent intent = new Intent(this, PerformMeditationActivity.class);
        intent.putExtra("text", time);
        intent.putExtra("time",millis);
        startActivity(intent);
        finish();
    }

    public void mediFinish(View v){
        finish();
    }
}