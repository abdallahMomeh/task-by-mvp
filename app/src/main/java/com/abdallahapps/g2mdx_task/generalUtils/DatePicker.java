package com.abdallahapps.g2mdx_task.generalUtils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DatePicker extends DialogFragment {

    CustomListenter.OnDatePicked onDatePicked;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), dateSetListener, year, month, day);
    }

    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int day) {
            Calendar calendar  = Calendar.getInstance();
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,month+1);
            calendar.set(Calendar.DAY_OF_MONTH,day);
            onDatePicked.onDate(calendar.getTimeInMillis() , year+"-"+(month+1)+"-"+day);
        }
    };

    public void setOnDatePicked(CustomListenter.OnDatePicked onDatePicked) {
        this.onDatePicked = onDatePicked;
    }


}
