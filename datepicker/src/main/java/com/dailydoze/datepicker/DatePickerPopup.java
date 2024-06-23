package com.dailydoze.datepicker;

import android.content.Context;

import androidx.annotation.NonNull;

public class DatePickerPopup extends PickerPopup {
    private OnDateSelectListener listener;

    public DatePickerPopup(@NonNull Context context, @NonNull DatePicker picker) {
        super(context);
        init(picker);
    }

    private void init(DatePicker picker) {
        setCancelable(false);
        setCanceledOnTouchOutside(true);
        confirm.setOnClickListener(view -> {
            if (listener != null) {
                DateModel dateModel = new DateModel(picker.getDate());
                listener.onDateSelected(
                        picker, dateModel.getDate(), dateModel.getDay(), dateModel.getMonth(), dateModel.getYear()
                );
            }
            dismiss();
        });
        addView(picker);
    }

    public void setListener(OnDateSelectListener listener) {
        this.listener = listener;
    }

    public interface OnDateSelectListener {
        void onDateSelected(DatePicker dp, long date, int day, int month, int year);
    }

    public static final class Builder {
        private Context context;
        private DatePicker datePicker;
        private OnDateSelectListener listener;

        public Builder() {
        }

        public Builder from(Context context) {
            this.context = context;
            this.datePicker = new DatePicker(context);
            return this;
        }

        public Builder textSize(int textSize) {
            this.datePicker.setTextSize(textSize);
            return this;
        }

        public Builder startDate(long startDate) {
            this.datePicker.setMinDate(startDate);
            return this;
        }

        public Builder endDate(long endDate) {
            this.datePicker.setMaxDate(endDate);
            return this;
        }

        public Builder currentDate(long currentDate) {
            this.datePicker.setDate(currentDate);
            return this;
        }

        public Builder offset(int offset) {
            this.datePicker.setOffset(offset);
            return this;
        }

        public Builder pickerMode(int appearanceMode) {
            this.datePicker.setPickerMode(appearanceMode);
            return this;
        }

        public Builder listener(OnDateSelectListener listener) {
            this.listener = listener;
            return this;
        }

        public DatePickerPopup build() {
            DatePickerPopup popup = new DatePickerPopup(context, datePicker);
            popup.setListener(listener);
            return popup;
        }
    }
}