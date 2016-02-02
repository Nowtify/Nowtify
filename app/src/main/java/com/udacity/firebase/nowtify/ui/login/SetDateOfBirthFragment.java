package com.udacity.firebase.nowtify.ui.login;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.Calendar;

/**
 * Created by josie on 1/2/16.
 */
public class SetDateOfBirthFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    // Use the current date as the default date in the picker

        DateOfBirthSettings dobSettings = new DateOfBirthSettings(getActivity());
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog;
        dialog = new DatePickerDialog(getActivity(), dobSettings, year, month, day);


    // Create a new instance of DatePickerDialog and return it
        return dialog;
    }

}


