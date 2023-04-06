package com.android.dailydoze.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
                drawable = getDrawable(R.drawable.beans);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.beans));
                name.setText("Beans");
                break;
            case "berries":
                drawable = getDrawable(R.drawable.berries);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.berries));
                name.setText("Berries");
                break;
            case "fruits":
                drawable = getDrawable(R.drawable.fruits);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.fruits));
                name.setText("Fruits");
                break;
            case "crucivege":
                drawable = getDrawable(R.drawable.cruciferous);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.crucivege));
                name.setText("Cruciferous Vegetables");
                break;
            case "greens":
                drawable = getDrawable(R.drawable.green_vegetables);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.greenvege));
                name.setText("Greens");
                break;
            case "othervege":
                drawable = getDrawable(R.drawable.other_vegetables);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.othervege));
                name.setText("Other Vegetables");
                break;
            case "flexseeds":
                drawable = getDrawable(R.drawable.flexseeds);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.flexseeds));
                name.setText("Flexseeds");
                break;
            case "herbs":
                drawable = getDrawable(R.drawable.herbs);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.herbs));
                name.setText("Herbs & Spices");
                break;
            case "beverages":
                drawable = getDrawable(R.drawable.beverages);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.beverages));
                name.setText("Beverages");
                break;
            case "nuts":
                drawable = getDrawable(R.drawable.nuts);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.nuts));
                name.setText("Nuts & Seeds");
                break;
            case "grains":
                drawable = getDrawable(R.drawable.grains);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.grains));
                name.setText("Grains");
                break;
            case "exercise":
                drawable = getDrawable(R.drawable.exercise);
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
                drawable = getDrawable(R.drawable.sleep2);
                img.setImageDrawable(drawable);
                txt.setText(getResources().getString(R.string.trendelenburg));
                name.setText("Experiment Mild Trendelenburg");
                break;
            case "water":
                drawable = getDrawable(R.drawable.water);
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
            default:
                break;
        }
    }

    public void infoFinish(View v){
        finish();
    }
}