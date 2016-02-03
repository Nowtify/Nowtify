package com.udacity.firebase.nowtify.ui.Explore;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.firebase.nowtify.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class ExploreActivityFragment extends Fragment {

    public ExploreActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_explore, container, false);
    }
}
