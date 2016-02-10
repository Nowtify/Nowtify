package com.udacity.firebase.nowtify.ui.login;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.udacity.firebase.nowtify.R;

/**
 * Created by smu on 1/2/16.
 */
public class DateOfBirthSettings implements DatePickerDialog.OnDateSetListener {
    Context context;

    public String mth,day;
    public DateOfBirthSettings(Context context){
        this.context =context;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int mth , int day ){

        Log.d("DOB", ""+day+mth+year);

        mth = mth++;

        if(day<10){
            this.day = "0"+day;
        } else {
            this.day = ""+day;
        }

        if(mth<10){
            this.mth = "0"+mth;
        } else {
            this.mth = ""+mth;
        }


        Toast.makeText(context, "Selected date: " + day + " / " + (mth+1) + " / " + year, Toast.LENGTH_LONG).show();
        AddDetailsActivity activity = (AddDetailsActivity) context;
        Button mButton= (Button) activity.findViewById(R.id.dob_button);
        mButton.setText(this.day + " / " + this.mth +  " / " + year);
        activity.setDateOfBirth(""+this.day+ this.mth +year);
    }
}
