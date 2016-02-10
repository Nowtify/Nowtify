package com.udacity.firebase.nowtify.ui.login;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.Button;

import com.udacity.firebase.nowtify.R;

/**
 * Created by Josie on 27/1/16.
 * To be called from AddDetailsActivity
 */

public class SetGenderFragment extends DialogFragment {

    final CharSequence[] genderList = {"Female", "Male", "Others"};
    private static String selection = "ENTER YOUR GENDER";
    private static final String LOG_TAG = SetGenderFragment.class.getSimpleName();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.v(LOG_TAG, "Entered onCreateDialog method" );
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        Log.v(LOG_TAG, "Built" );
        builder.setTitle("Select your gender").setSingleChoiceItems(genderList, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        selection = (String) genderList[which];
                        break;
                    case 1:
                        selection = (String) genderList[which];
                        break;
                    case 2:
                        selection = (String) genderList[which];
                        break;
                    default:
                        break;
                }
            }


        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((AddDetailsActivity) getActivity()).setGender(selection);
                //Toast.makeText(getActivity(), "You chose " + selection, Toast.LENGTH_SHORT).show();

                Button mButton = (Button) getActivity().findViewById(R.id.gender_button);
                mButton.setText(selection);
            }
        });

        return builder.create();

    }



}
