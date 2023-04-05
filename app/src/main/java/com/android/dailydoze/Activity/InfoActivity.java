package com.android.dailydoze.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.dailydoze.R;

public class InfoActivity extends AppCompatActivity {
    ImageView img;
    TextView txt, name;
    Drawable drawable;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        img = findViewById(R.id.imageView2);
        txt = findViewById(R.id.textView4);
        name = findViewById(R.id.infoName);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");

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
            default:
        }
    }

    public void infoFinish(View v){
        finish();
    }
}