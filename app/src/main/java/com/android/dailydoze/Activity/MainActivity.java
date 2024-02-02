package com.android.dailydoze.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.dailydoze.CustomDatePicker.DatePicker;
import com.android.dailydoze.CustomDatePicker.DatePickerPopup;
import com.android.dailydoze.Database.DailyDozeDatabase;
import com.android.dailydoze.R;
import com.google.firebase.auth.FirebaseAuth;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {
    Button b;
    CheckBox beans_cb1, beans_cb2, beans_cb3, berries_cb1, greens_cb1, greens_cb2, othervege_cb1, othervege_cb2, of_cb1, of_cb2, of_cb3,
            cv_cb1, flaxseeds_cb1, herbs_cb1, nuts_cb1, grains_cb1, grains_cb2, grains_cb3, beve_cb1, beve_cb2, beve_cb3,
            beve_cb4, beve_cb5, exercise_cb1;
    TextView currDate, textView2, back_to_today;
    DailyDozeDatabase db;
    ImageButton date_prev, date_next, sleep;
    FrameLayout frameLayout;
    LinearLayout jumpBack;
    boolean today, jump = false;

    public static void dimBehind(PopupWindow popupWindow) {
        View container = popupWindow.getContentView().getRootView();
        Context context = popupWindow.getContentView().getContext();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.5f;
        wm.updateViewLayout(container, p);
    }

    @SuppressLint({"NonConstantResourceId", "ClickableViewAccessibility", "UseCompatLoadingForDrawables"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        db = new DailyDozeDatabase(this);

        b = findViewById(R.id.nav_button);

        currDate = findViewById(R.id.date);
        currDate.setText(setCurrentDate());

        beans_cb1 = findViewById(R.id.beans_cb1);
        beans_cb2 = findViewById(R.id.beans_cb2);
        beans_cb3 = findViewById(R.id.beans_cb3);

        berries_cb1 = findViewById(R.id.berries_cb1);

        greens_cb1 = findViewById(R.id.greens_cb1);
        greens_cb2 = findViewById(R.id.greens_cb2);

        othervege_cb1 = findViewById(R.id.othervege_cb1);
        othervege_cb2 = findViewById(R.id.othervege_cb2);

        of_cb1 = findViewById(R.id.of_cb1);
        of_cb2 = findViewById(R.id.of_cb2);
        of_cb3 = findViewById(R.id.of_cb3);

        cv_cb1 = findViewById(R.id.cv_cb1);

        flaxseeds_cb1 = findViewById(R.id.flaxseeds_cb1);

        herbs_cb1 = findViewById(R.id.herbs_cb1);

        nuts_cb1 = findViewById(R.id.nuts_cb1);

        grains_cb1 = findViewById(R.id.grains_cb1);
        grains_cb2 = findViewById(R.id.grains_cb2);
        grains_cb3 = findViewById(R.id.grains_cb3);

        beve_cb1 = findViewById(R.id.beverages_cb1);
        beve_cb2 = findViewById(R.id.beverages_cb2);
        beve_cb3 = findViewById(R.id.beverages_cb3);
        beve_cb4 = findViewById(R.id.beverages_cb4);
        beve_cb5 = findViewById(R.id.beverages_cb5);

        exercise_cb1 = findViewById(R.id.exercise_cb1);

        jumpBack = findViewById(R.id.jump_back);
        frameLayout = findViewById(R.id.frameLayout);
        date_next = findViewById(R.id.date_next);
        date_prev = findViewById(R.id.date_prev);
        sleep = findViewById(R.id.sleep);

        textView2 = findViewById(R.id.textView2);
        back_to_today = findViewById(R.id.back_to_today);

        setDay();

        if (!db.getDate(getCurrentDate())) {
            db.addDate(getCurrentDate());
        }

        b.setOnClickListener(v -> {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            @SuppressLint("InflateParams") View popupView = layoutInflater.inflate(R.layout.main_menu, null);

            int width = LinearLayout.LayoutParams.MATCH_PARENT;
            int height = LinearLayout.LayoutParams.WRAP_CONTENT;

            final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

            TextView name, email;

            name = popupView.findViewById(R.id.name);
            email = popupView.findViewById(R.id.email);

            SharedPreferences userData = getSharedPreferences("MySharedPref", MODE_PRIVATE);
            email.setText(userData.getString("email", "Email"));
            name.setText(getGreetings());

            LinearLayout l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11;

            l1 = popupView.findViewById(R.id.twe_tweaks);
            l2 = popupView.findViewById(R.id.jump_to_date);
            l3 = popupView.findViewById(R.id.notifications);
            l4 = popupView.findViewById(R.id.meditation);
            l5 = popupView.findViewById(R.id.fastwatch);
            l6 = popupView.findViewById(R.id.logout);
            l7 = popupView.findViewById(R.id.about);
            l8 = popupView.findViewById(R.id.openSource);
            l9 = popupView.findViewById(R.id.backup);
            l10 = popupView.findViewById(R.id.recommend);
            l11 = popupView.findViewById(R.id.faq);

            ImageButton ib1 = popupView.findViewById(R.id.closeMenu);
            ib1.setOnClickListener(v12 -> popupWindow.dismiss());

            l1.setOnClickListener(v13 -> {
                startActivity(new Intent(MainActivity.this, TweakActivity.class));
                popupWindow.dismiss();
            });

            l2.setOnClickListener(v14 -> {
                openJumpDate();
                popupWindow.dismiss();
            });

            l3.setOnClickListener(v18 -> {
                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
                popupWindow.dismiss();
            });

            l4.setOnClickListener(v15 -> {
                startActivity(new Intent(MainActivity.this, MeditationActivity.class));
                popupWindow.dismiss();
            });

            l5.setOnClickListener(v16 -> {
                startActivity(new Intent(MainActivity.this, FastActivity.class));
                popupWindow.dismiss();
            });

            l6.setOnClickListener(v17 -> {
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putBoolean("user", false);
                myEdit.apply();

                FirebaseAuth.getInstance().signOut();

                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                popupWindow.dismiss();
                finish();
            });

            l7.setOnClickListener(v16 -> {
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                popupWindow.dismiss();
            });

            l8.setOnClickListener(v16 -> {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ArmanKhanTech/DailyDoze"));
                startActivity(browserIntent);
                popupWindow.dismiss();
            });

            l9.setOnClickListener(v16 -> {
                startActivity(new Intent(MainActivity.this, BackupActivity.class));
                popupWindow.dismiss();
            });

            l10.setOnClickListener(v16 -> {
                startActivity(new Intent(MainActivity.this, RecommendationActivity.class));
                popupWindow.dismiss();
            });

            l11.setOnClickListener(v16 -> {
                startActivity(new Intent(MainActivity.this, FAQActivity.class));
                popupWindow.dismiss();
            });

            popupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
            popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
            dimBehind(popupWindow);
        });

        beans_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("beans");
            } else {
                decValue("beans");
            }
        });

        beans_cb2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("beans");
            } else {
                decValue("beans");
            }
        });

        beans_cb3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("beans");
            } else {
                decValue("beans");
            }
        });

        berries_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("berries");
            } else {
                decValue("berries");
            }
        });

        greens_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("greens");
            } else {
                decValue("greens");
            }
        });

        greens_cb2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("greens");
            } else {
                decValue("greens");
            }
        });

        othervege_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("othervege");
            } else {
                decValue("othervege");
            }
        });

        othervege_cb2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("othervege");
            } else {
                decValue("othervege");
            }
        });

        of_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("otherfruits");
            } else {
                decValue("otherfruits");
            }
        });

        of_cb2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("otherfruits");
            } else {
                decValue("otherfruits");
            }
        });

        of_cb3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("otherfruits");
            } else {
                decValue("otherfruits");
            }
        });

        cv_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("crucivege");
            } else {
                decValue("crucivege");
            }
        });

        flaxseeds_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("flaxseeds");
            } else {
                decValue("flaxseeds");
            }
        });

        herbs_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("herbs");
            } else {
                decValue("herbs");
            }
        });

        nuts_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("nuts");
            } else {
                decValue("nuts");
            }
        });

        grains_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("grains");
            } else {
                decValue("grains");
            }
        });

        grains_cb2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("grains");
            } else {
                decValue("grains");
            }
        });

        grains_cb3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("grains");
            } else {
                decValue("grains");
            }
        });

        beve_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("beve");
            } else {
                decValue("beve");
            }
        });

        beve_cb2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("beve");
            } else {
                decValue("beve");
            }
        });

        beve_cb3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("beve");
            } else {
                decValue("beve");
            }
        });

        beve_cb4.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("beve");
            } else {
                decValue("beve");
            }
        });

        beve_cb5.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("beve");
            } else {
                decValue("beve");
            }
        });

        exercise_cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                incValue("exercise");
            } else {
                decValue("exercise");
            }
        });

        jumpBack.setOnClickListener(v -> {
            String tDate = setCurrentDate();
            currDate.setText(tDate);
            jumpBack.setBackground(getDrawable(R.drawable.back_to_today_theme));
            back_to_today.setTextColor(Color.WHITE);
            setDay();
        });

        sleep.setOnClickListener(v -> {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            @SuppressLint("InflateParams") View popupView = layoutInflater.inflate(R.layout.get_weight, null);
            int width = LinearLayout.LayoutParams.MATCH_PARENT;
            int height = LinearLayout.LayoutParams.WRAP_CONTENT;
            final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

            EditText hrs = popupView.findViewById(R.id.weight);
            TextView tv = popupView.findViewById(R.id.timing);
            Button save = popupView.findViewById(R.id.save);

            final String[] duration = {"0"};

            if (today) {
                tv.setText("How many hours did you sleep today?");
                hrs.setHint("Enter Duration in Hours");
            } else {
                duration[0] = String.valueOf(db.getSleep(getCurrentDate()));
                hrs.setVisibility(View.GONE);
                tv.setText("You slept for " + duration[0] + " hours on this day.");
                save.setText("Okay");
            }

            save.setOnClickListener(v1 -> {
                if (today) {
                    duration[0] = hrs.getText().toString();
                    if (!duration[0].isEmpty()) {
                        if (db.getDate(getCurrentDate())) {
                            db.setSleep(duration[0], getCurrentDate());
                        } else {
                            db.addDate(getCurrentDate());
                            db.setSleep(duration[0], getCurrentDate());
                        }
                    }
                    popupWindow.dismiss();
                } else {
                    popupWindow.dismiss();
                }
            });

            popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
            dimBehind(popupWindow);
        });
    }

    public void openJumpDate() {
        final String[] formattedDate = new String[1];

        DatePickerPopup datePickerPopup = new DatePickerPopup.Builder()
                .from(this)
                .textSize(18)
                .endDate(System.currentTimeMillis())
                .currentDate(System.currentTimeMillis())
                .startDate(946665000000L)
                .pickerMode(DatePicker.DAY_ON_FIRST)
                .offset(5)
                .listener(new DatePickerPopup.OnDateSelectListener() {
                    @Override
                    public void onDateSelected(DatePicker dp, long date, int day, int month, int year) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, day, 0, 0);
                        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
                        formattedDate[0] = df.format(calendar.getTime());

                        if (db.getDate(formattedDate[0])) {
                            currDate.setText(formattedDate[0]);
                            jump = true;
                            setDay();
                        } else {
                            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                            @SuppressLint("InflateParams") View popupView = layoutInflater.inflate(R.layout.get_weight, null);
                            int width = LinearLayout.LayoutParams.MATCH_PARENT;
                            int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                            final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

                            EditText hrs = popupView.findViewById(R.id.weight);
                            TextView tv = popupView.findViewById(R.id.timing);
                            Button okay = popupView.findViewById(R.id.save);

                            hrs.setVisibility(View.GONE);
                            okay.setText("Okay");
                            tv.setText("Record dosen't exists.");

                            okay.setOnClickListener(v1 -> {
                                popupWindow.dismiss();
                            });

                            popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
                            dimBehind(popupWindow);
                        }
                    }
                })
                .build();

        datePickerPopup.show();
    }

    public void setPrev() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        try {
            c.setTime(Objects.requireNonNull(df.parse(currDate.getText().toString())));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        c.add(Calendar.DATE, -1);
        String formattedDate = df.format(c.getTime());

        if (db.getDate(formattedDate)) {
            date_prev.setVisibility(View.VISIBLE);
        } else {
            date_prev.setVisibility(View.GONE);
        }

        date_prev.setOnClickListener(v -> {
            currDate.setText(formattedDate);
            setDay();
        });
    }

    public void setNext() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        try {
            c.setTime(Objects.requireNonNull(df.parse(currDate.getText().toString())));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        c.add(Calendar.DATE, +1);
        String formattedDate = df.format(c.getTime());

        if (db.getDate(formattedDate)) {
            date_next.setVisibility(View.VISIBLE);
        } else {
            date_next.setVisibility(View.GONE);
        }

        date_next.setOnClickListener(v -> {
            currDate.setText(formattedDate);
            setDay();
        });
    }

    public void setCount() {
        int i = db.getCount(getCurrentDate());
        textView2.setText(String.valueOf(i));
    }

    public void incValue(String value) {
        if (today) {
            if (db.getDate(getCurrentDate())) {
                db.incData(value, getCurrentDate());
            } else {
                db.addDate(getCurrentDate());
                db.incData(value, getCurrentDate());
            }
            setCount();
        }
    }

    public void decValue(String value) {
        if (today) {
            if (db.getDate(getCurrentDate())) {
                db.decData(value, getCurrentDate());
            } else {
                db.addDate(getCurrentDate());
            }
            setCount();
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void setDay() {
        if (Objects.equals(setCurrentDate(), getCurrentDate())) {
            setClickable(true);
            unCheckAll();
            setChecked();
            setCount();

            today = true;
            jump = false;

            frameLayout.setBackground(getResources().getDrawable(R.drawable.info_img_theme));
            currDate.setTextColor(getColor(R.color.black));
            jumpBack.setVisibility(View.GONE);

            setNext();
            setPrev();
        } else {
            if (today) {
                final Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.slide_down);
                jumpBack.startAnimation(slide_down);
            }
            jumpBack.setVisibility(View.VISIBLE);

            today = false;

            setClickable(false);
            unCheckAll();
            setChecked();
            setCount();
            setPrev();
            setNext();

            if (jump) {
                final Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.slide_down);
                jumpBack.startAnimation(slide_down);
                jumpBack.setBackground(getDrawable(R.drawable.back_to_today_jump_theme));
                back_to_today.setTextColor(Color.BLACK);
                jumpBack.setVisibility(View.VISIBLE);

                frameLayout.setBackground(getResources().getDrawable(R.drawable.date_back_theme));
                currDate.setTextColor(getColor(R.color.white));
                date_prev.setVisibility(View.GONE);
                date_next.setVisibility(View.GONE);
            }
        }
    }

    public void setClickable(boolean b) {
        beans_cb1.setClickable(b);
        beans_cb2.setClickable(b);
        beans_cb3.setClickable(b);

        berries_cb1.setClickable(b);

        greens_cb1.setClickable(b);
        greens_cb2.setClickable(b);

        othervege_cb1.setClickable(b);
        othervege_cb2.setClickable(b);

        of_cb1.setClickable(b);
        of_cb2.setClickable(b);
        of_cb3.setClickable(b);

        cv_cb1.setClickable(b);

        flaxseeds_cb1.setClickable(b);

        herbs_cb1.setClickable(b);

        nuts_cb1.setClickable(b);

        grains_cb1.setClickable(b);
        grains_cb2.setClickable(b);
        grains_cb3.setClickable(b);

        beve_cb1.setClickable(b);
        beve_cb2.setClickable(b);
        beve_cb3.setClickable(b);
        beve_cb4.setClickable(b);
        beve_cb5.setClickable(b);

        exercise_cb1.setClickable(b);
    }

    public void unCheckAll() {
        beans_cb1.setChecked(false);
        beans_cb2.setChecked(false);
        beans_cb3.setChecked(false);

        berries_cb1.setChecked(false);

        greens_cb1.setChecked(false);
        greens_cb2.setChecked(false);

        othervege_cb1.setChecked(false);
        othervege_cb2.setChecked(false);

        of_cb1.setChecked(false);
        of_cb2.setChecked(false);
        of_cb3.setChecked(false);

        cv_cb1.setChecked(false);

        flaxseeds_cb1.setChecked(false);

        herbs_cb1.setChecked(false);

        nuts_cb1.setChecked(false);

        grains_cb1.setChecked(false);
        grains_cb2.setChecked(false);
        grains_cb3.setChecked(false);

        beve_cb1.setChecked(false);
        beve_cb2.setChecked(false);
        beve_cb3.setChecked(false);
        beve_cb4.setChecked(false);
        beve_cb5.setChecked(false);

        exercise_cb1.setChecked(false);
    }

    public void setChecked() {
        int i = db.getData("beans", getCurrentDate());
        if (i == 1) {
            beans_cb1.setChecked(true);
        } else if (i == 2) {
            beans_cb1.setChecked(true);
            beans_cb2.setChecked(true);
        } else if (i == 3) {
            beans_cb1.setChecked(true);
            beans_cb2.setChecked(true);
            beans_cb3.setChecked(true);
        }

        i = db.getData("berries", getCurrentDate());
        if (i == 1) {
            berries_cb1.setChecked(true);
        }

        i = db.getData("greens", getCurrentDate());
        if (i == 1) {
            greens_cb1.setChecked(true);
        } else if (i == 2) {
            greens_cb1.setChecked(true);
            greens_cb2.setChecked(true);
        }

        i = db.getData("othervege", getCurrentDate());
        if (i == 1) {
            othervege_cb1.setChecked(true);
        } else if (i == 2) {
            othervege_cb1.setChecked(true);
            othervege_cb2.setChecked(true);
        }

        i = db.getData("otherfruits", getCurrentDate());
        if (i == 1) {
            of_cb1.setChecked(true);
        } else if (i == 2) {
            of_cb1.setChecked(true);
            of_cb2.setChecked(true);
        } else if (i == 3) {
            of_cb1.setChecked(true);
            of_cb2.setChecked(true);
            of_cb3.setChecked(true);
        }

        i = db.getData("crucivege", getCurrentDate());
        if (i == 1) {
            cv_cb1.setChecked(true);
        }

        i = db.getData("flaxseeds", getCurrentDate());
        if (i == 1) {
            flaxseeds_cb1.setChecked(true);
        }

        i = db.getData("herbs", getCurrentDate());
        if (i == 1) {
            herbs_cb1.setChecked(true);
        }

        i = db.getData("nuts", getCurrentDate());
        if (i == 1) {
            nuts_cb1.setChecked(true);
        }

        i = db.getData("grains", getCurrentDate());
        if (i == 1) {
            grains_cb1.setChecked(true);
        } else if (i == 2) {
            grains_cb1.setChecked(true);
            grains_cb2.setChecked(true);
        } else if (i == 3) {
            grains_cb1.setChecked(true);
            grains_cb2.setChecked(true);
            grains_cb3.setChecked(true);
        }

        i = db.getData("beve", getCurrentDate());
        if (i == 1) {
            beve_cb1.setChecked(true);
        } else if (i == 2) {
            beve_cb1.setChecked(true);
            beve_cb2.setChecked(true);
        } else if (i == 3) {
            beve_cb1.setChecked(true);
            beve_cb2.setChecked(true);
            beve_cb3.setChecked(true);
        } else if (i == 4) {
            beve_cb1.setChecked(true);
            beve_cb2.setChecked(true);
            beve_cb3.setChecked(true);
            beve_cb4.setChecked(true);
        } else if (i == 5) {
            beve_cb1.setChecked(true);
            beve_cb2.setChecked(true);
            beve_cb3.setChecked(true);
            beve_cb4.setChecked(true);
            beve_cb5.setChecked(true);
        }

        i = db.getData("exercise", getCurrentDate());
        if (i == 1) {
            exercise_cb1.setChecked(true);
        }
    }

    public String setCurrentDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        return df.format(c);
    }

    public String getCurrentDate() {
        return String.valueOf(currDate.getText());
    }

    public void gotoGraph(View v) {
        Intent intent = new Intent(this, GraphActivity.class);
        intent.putExtra("GraphOf", "DailyDoze");
        startActivity(intent);
    }

    public void beansInfo(View v) {
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("title", "beans");
        startActivity(intent);
    }

    public void berriesInfo(View v) {
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("title", "berries");
        startActivity(intent);
    }

    public void fruitsInfo(View v) {
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("title", "fruits");
        startActivity(intent);
    }

    public void crucivegeInfo(View v) {
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("title", "crucivege");
        startActivity(intent);
    }

    public void greensInfo(View v) {
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("title", "greens");
        startActivity(intent);
    }

    public void othervegeInfo(View v) {
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("title", "othervege");
        startActivity(intent);
    }

    public void flexseedsInfo(View v) {
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("title", "flexseeds");
        startActivity(intent);
    }

    public void herbsInfo(View v) {
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("title", "herbs");
        startActivity(intent);
    }

    public void beveragesInfo(View v) {
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("title", "beverages");
        startActivity(intent);
    }

    public void nutsInfo(View v) {
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("title", "nuts");
        startActivity(intent);
    }

    public void grainsInfo(View v) {
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("title", "grains");
        startActivity(intent);
    }

    public void exerciseInfo(View v) {
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("title", "exercise");
        startActivity(intent);
    }

    public void beansCal(View v) {
        Intent intent = new Intent(this, CalenderActivity.class);
        intent.putExtra("title", "beans");
        startActivity(intent);
    }

    public void berriesCal(View v) {
        Intent intent = new Intent(this, CalenderActivity.class);
        intent.putExtra("title", "berries");
        startActivity(intent);
    }

    public void fruitsCal(View v) {
        Intent intent = new Intent(this, CalenderActivity.class);
        intent.putExtra("title", "fruits");
        startActivity(intent);
    }

    public void crucivegeCal(View v) {
        Intent intent = new Intent(this, CalenderActivity.class);
        intent.putExtra("title", "crucivege");
        startActivity(intent);
    }

    public void greensCal(View v) {
        Intent intent = new Intent(this, CalenderActivity.class);
        intent.putExtra("title", "greens");
        startActivity(intent);
    }

    public void othervegeCal(View v) {
        Intent intent = new Intent(this, CalenderActivity.class);
        intent.putExtra("title", "othervege");
        startActivity(intent);
    }

    public void flexseedsCal(View v) {
        Intent intent = new Intent(this, CalenderActivity.class);
        intent.putExtra("title", "flexseeds");
        startActivity(intent);
    }

    public void herbsCal(View v) {
        Intent intent = new Intent(this, CalenderActivity.class);
        intent.putExtra("title", "herbs");
        startActivity(intent);
    }

    public void beveragesCal(View v) {
        Intent intent = new Intent(this, CalenderActivity.class);
        intent.putExtra("title", "beverages");
        startActivity(intent);
    }

    public void nutsCal(View v) {
        Intent intent = new Intent(this, CalenderActivity.class);
        intent.putExtra("title", "nuts");
        startActivity(intent);
    }

    public void grainsCal(View v) {
        Intent intent = new Intent(this, CalenderActivity.class);
        intent.putExtra("title", "grains");
        startActivity(intent);
    }

    public void exerciseCal(View v) {
        Intent intent = new Intent(this, CalenderActivity.class);
        intent.putExtra("title", "exercise");
        startActivity(intent);
    }

    public String getGreetings() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay >= 0 && timeOfDay < 12) {
            return " Good Morning";
        } else if (timeOfDay >= 12 && timeOfDay < 16) {
            return " Good Afternoon";
        } else if (timeOfDay >= 16 && timeOfDay < 21) {
            return " Good Evening";
        } else if (timeOfDay >= 21 && timeOfDay < 24) {
            return " Good Night";
        }
        return "";
    }
}

