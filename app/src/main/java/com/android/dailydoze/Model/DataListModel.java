package com.android.dailydoze.Model;

import android.graphics.drawable.Drawable;

public class DataListModel {
    private final String text;
    Drawable icon;

    public DataListModel(String text, Drawable icon) {
        this.text = text;
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public Drawable getIcon() {
        return icon;
    }
}
