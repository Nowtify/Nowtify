package com.udacity.firebase.nowtify.ui.EntityItem;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.udacity.firebase.nowtify.R;
import com.udacity.firebase.nowtify.model.EntityItemDetails;
import com.udacity.firebase.nowtify.ui.BaseActivity;
import com.udacity.firebase.nowtify.utils.Constants;
import com.udacity.firebase.nowtify.utils.FirebaseUtils;

/**
 * Created by smu on 17/2/16.
 */
public class EntityItemDetailsActivity extends BaseActivity {
    private static final String LOG_TAG = EntityItemDetailsActivity.class.getSimpleName();
    private FirebaseUtils fireBaseUtils = new FirebaseUtils(getParent());
    private Firebase firebaseRef;
    private EntityItemDetails entityItemDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entity_item_details);
        
        getEntityItemDetailsFromId("adidasGroupOffer");

        /**
         * Link layout elements from XML and setup the progress dialog
         */

    }

    public void getEntityItemDetailsFromId (String entityItemDetailsId){
        firebaseRef = new Firebase(Constants.FIREBASE_URL_ENTITY_ITEM_DETAILS).child(entityItemDetailsId);

        firebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                entityItemDetails = dataSnapshot.getValue(EntityItemDetails.class);
                setDetails();
                Log.v(LOG_TAG,entityItemDetails.getParentPushId());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    public void setDetails(){
        //edit here to set views
        //use entityItemDetails.[get]
        TextView textViewListName = (TextView) findViewById(R.id.entity_parent_name);
        textViewListName.setText();

    }
}
