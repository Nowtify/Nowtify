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
 * Created by HeraX on 4/2/2016.
 */
public class SetOccupationFragment extends DialogFragment{

    final CharSequence[] occupationList = {"Student", "Homemaker", "Blue Collar","White Collar","Unemployed","Retired"};
    private static String selection = "ENTER YOUR OCCUPATION";
    private static final String LOG_TAG = SetOccupationFragment.class.getSimpleName();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.v(LOG_TAG, "Entered onCreateDialog method");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        Log.v(LOG_TAG, "Built" );
        builder.setTitle("Select your occupation").setSingleChoiceItems(occupationList, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        selection = (String) occupationList[which];
                        break;
                    case 1:
                        selection = (String) occupationList[which];
                        break;
                    case 2:
                        selection = (String) occupationList[which];
                        break;
                    case 3:
                        selection = (String) occupationList[which];
                        break;
                    case 4:
                        selection = (String) occupationList[which];
                        break;
                    case 5:
                        selection = (String) occupationList[which];
                        break;
                    default:
                        break;
                }
            }


        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((AddDetailsActivity) getActivity()).setOccupation(selection);
                //Toast.makeText(getActivity(), "You chose " + selection, Toast.LENGTH_SHORT).show();
                Button mButton = (Button) getActivity().findViewById(R.id.occupation_button);
                mButton.setText(selection);
            }
        });

        return builder.create();

    }
}
