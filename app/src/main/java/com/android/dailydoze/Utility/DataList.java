package com.android.dailydoze.Utility;

import android.graphics.drawable.Drawable;

public class DataList {
    private final String text;
    Drawable icon;

    public DataList(String text, Drawable icon) {
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
