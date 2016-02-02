package com.udacity.firebase.nowtify.ui.login;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.udacity.firebase.nowtify.R;

/**
 * Created by smu on 1/2/16.
 */
public class DateOfBirthSettings implements DatePickerDialog.OnDateSetListener {
    Context context;

    public DateOfBirthSettings(Context context){
        this.context =context;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int mth , int day ){
        Toast.makeText(context, "Selected date: " + day + " / " + mth + " / " + year, Toast.LENGTH_LONG).show();
        Activity activity = (Activity) context;
        Button mButton= (Button) activity.findViewById(R.id.dob_button);
        mButton.setText(day + " / " + mth +  " / " + year);

    }
}
