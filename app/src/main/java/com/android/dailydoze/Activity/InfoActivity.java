package com.android.dailydoze.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.dailydoze.R;

public class InfoActivity extends AppCompatActivity {
    ImageView img;
    TextView txt, name, infoTitle;
    Drawable drawable;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        img = findViewById(R.id.imageView2);
        txt = findViewById(R.id.textView4);
        name = findViewById(R.id.infoName);
        infoTitle = findViewById(R.id.infoTitle);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        boolean b = intent.getBooleanExtra("tweak",false);

        if(b){
            infoTitle.setText("About Tweak");
        }

        txt.setMovementMethod(new ScrollingMovementMethod());

        switch(title){
            case "beans":
                drawable = getDrawable(R.drawable.beans_picture);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.beans));
                name.setText("Beans");
                break;
            case "berries":
                drawable = getDrawable(R.drawable.berries_picture);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.berries));
                name.setText("Berries");
                break;
            case "fruits":
                drawable = getDrawable(R.drawable.fruits_pictures);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.fruits));
                name.setText("Fruits");
                break;
            case "crucivege":
                drawable = getDrawable(R.drawable.cruciferous_vegetables_pictures);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.crucivege));
                name.setText("Cruciferous Vegetables");
                break;
            case "greens":
                drawable = getDrawable(R.drawable.greens_picture);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.greenvege));
                name.setText("Greens");
                break;
            case "othervege":
                drawable = getDrawable(R.drawable.other_vegetables_picture);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.othervege));
                name.setText("Other Vegetables");
                break;
            case "flexseeds":
                drawable = getDrawable(R.drawable.flaxseeds_picture);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.flexseeds));
                name.setText("Flaxseeds");
                break;
            case "herbs":
                drawable = getDrawable(R.drawable.herbs_picture);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.herbs));
                name.setText("Herbs & Spices");
                break;
            case "beverages":
                drawable = getDrawable(R.drawable.beverages_picture);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.beverages));
                name.setText("Beverages");
                break;
            case "nuts":
                drawable = getDrawable(R.drawable.nuts_picture);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.nuts));
                name.setText("Nuts & Seeds");
                break;
            case "grains":
                drawable = getDrawable(R.drawable.grains_picture);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.grains));
                name.setText("Whole Grains");
                break;
            case "exercise":
                drawable = getDrawable(R.drawable.exercise_picture);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.exercise));
                name.setText("Exercise");
                break;
            case "fast":
                drawable = getDrawable(R.drawable.fast);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.fast));
                name.setText("Fast After 7:00 pm");
                break;
            case "sleep":
                drawable = getDrawable(R.drawable.sleep);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.sleep));
                name.setText("Sufficient Sleep");
                break;
            case "exp":
                drawable = getDrawable(R.drawable.intentions_picture);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.trendelenburg));
                name.setText("Experiment Mild Trendelenburg");
                break;
            case "water":
                drawable = getDrawable(R.drawable.hydrated_picture);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.water));
                name.setText("Preload with Water");
                break;
            case "vinegar":
                drawable = getDrawable(R.drawable.vinegar);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.vinegar));
                name.setText("Incorporate Vinegar");
                break;
            case "neg":
                drawable = getDrawable(R.drawable.negative_calories);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.neg));
                name.setText("Preload with 'Negative Calories' Food");
                break;
            case "un_meal":
                drawable = getDrawable(R.drawable.undistracted_meal);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.un_meal));
                name.setText("Enjoy Meal Undistracted");
                break;
            case "twemin":
                drawable = getDrawable(R.drawable.twenty_minutes);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.twemin));
                name.setText("Follow 20 Minutes Rule");
                break;
            case "garlic":
                drawable = getDrawable(R.drawable.garlic_picture);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.daily_garlic_text));
                name.setText("Garlic Powder");
                break;
            case "cumin":
                drawable = getDrawable(R.drawable.black_cumin_picture);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.daily_blackcumin_text));
                name.setText("Black Cumin");
                break;
            case "ginger":
                drawable = getDrawable(R.drawable.ginger_picture);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.daily_ginger_text));
                name.setText("Ground Ginger");
                break;
            case "yeast":
                drawable = getDrawable(R.drawable.yeast_picture);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.daily_nutriyeast_text));
                name.setText("Yeast");
                break;
            case "cumin2":
                drawable = getDrawable(R.drawable.cumin_picture);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.daily_cumin_text));
                name.setText("Cumin");
                break;
            case "green":
                drawable = getDrawable(R.drawable.green_tea_picture);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.daily_greentea_text));
                name.setText("Green Tea");
                break;
            case "hyd":
                drawable = getDrawable(R.drawable.hydrated_picture);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.daily_hydrate_text));
                name.setText("Stay Hydrated");
                break;
            case "deflour":
                drawable = getDrawable(R.drawable.deflour_picture);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.daily_deflourdiet_text));
                name.setText("Deflour Your Diet");
                break;
            case "front":
                drawable = getDrawable(R.drawable.front_picture);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.daily_frontload_text));
                name.setText("Front Your Diet");
                break;
            case "restrict":
                drawable = getDrawable(R.drawable.restrict_picture);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.daily_timerestrict_text));
                name.setText("Time-Restrict Your Eatings");
                break;
            case "optimize":
                drawable = getDrawable(R.drawable.optimize_picture);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.exercise_timing_text));
                name.setText("Optimize Your Exercise");
                break;
            case "weigh":
                drawable = getDrawable(R.drawable.weigh);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.weigh_twice_text));
                name.setText("Weigh Yourself Twice a Day");
                break;
            case "inten":
                drawable = getDrawable(R.drawable.intentions_picture);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.complete_intentions_text));
                name.setText("Complete Your Implementation Intentions");
                break;
            default:
                break;
        }
    }

    public void infoFinish(View v){
        finish();
    }
}