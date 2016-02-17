package com.udacity.firebase.nowtify.ui.EntityItem;

import android.os.Bundle;

import com.firebase.client.Firebase;
import com.udacity.firebase.nowtify.R;
import com.udacity.firebase.nowtify.ui.BaseActivity;
import com.udacity.firebase.nowtify.utils.Constants;

/**
 * Created by smu on 17/2/16.
 */
public class EntityItemDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entity_item_details);

        /**
         * Create Firebase references
         */
        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);

        /**
         * Link layout elements from XML and setup the progress dialog
         */

    }


}
