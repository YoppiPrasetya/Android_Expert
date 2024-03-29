package com.yoppi.myalarmmanager;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    DialogDateListener mListener;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context != null){
            mListener = (DialogDateListener) context;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);
        return new DatePickerDialog(getActivity(), this, year, month, date);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        mListener.onDialogDateSet(getTag(), year, month, dayOfMonth);
    }

    public interface DialogDateListener{
        void onDialogDateSet(String tag, int year, int month, int dayOfMonth);
    }
}
