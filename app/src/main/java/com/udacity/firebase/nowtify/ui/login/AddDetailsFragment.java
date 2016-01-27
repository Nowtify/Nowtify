package com.udacity.firebase.nowtify.ui.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.firebase.nowtify.R;

/**
 * Created by smu on 27/1/16.
 */
public class AddDetailsFragment extends Fragment{

    private static final String LOG_TAG = AddDetailsFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Build view
        View rootView = inflater.inflate(R.layout.fragment_add_details, container, false);
        return rootView;
    }
}
