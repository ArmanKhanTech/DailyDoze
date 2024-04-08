package com.android.dailydoze.CustomDatePicker;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.android.dailydoze.CustomDatePicker.Interface.IPopupInterface;
import com.android.dailydoze.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

@SuppressWarnings("ALL")
public class PickerPopup extends BottomSheetDialog implements IPopupInterface {
    public TextView confirm;
    public TextView cancel;
    private LinearLayout container;

    public PickerPopup(@NonNull Context context) {
        super(context);
        init();
    }

    public PickerPopup(@NonNull Context context, int theme) {
        super(context, theme);
        init();
    }

    private void init() {
        setContentView(R.layout.layout_date_picker);
        confirm = findViewById(R.id.text_confirm);
        cancel = findViewById(R.id.text_cancel);
        container = findViewById(R.id.popup_container);
        cancel.setOnClickListener(view -> dismiss());
    }


    @Override
    public void addView(View view) {
        container.addView(view);
    }
}