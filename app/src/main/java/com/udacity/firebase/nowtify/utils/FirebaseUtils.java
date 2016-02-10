package com.udacity.firebase.nowtify.utils;

import android.util.Log;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ServerValue;
import com.firebase.client.ValueEventListener;
import com.udacity.firebase.nowtify.model.EntityItemDetails;
import com.udacity.firebase.nowtify.model.EntityParentDetails;
import com.udacity.firebase.nowtify.model.User;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by MohamedAfiq on 23/1/16.
 */
public class FirebaseUtils{

    FirebaseError firebaseError;
    private static final String LOG_TAG = FirebaseUtils.class.getSimpleName();


    public FirebaseError updateUserDetails(String email, AuthData authData, String gender, String dateOfBirth, String occupation){

        String mEncodedEmail;

        final String unprocessedEmail = authData.getProviderData().get(Constants.FIREBASE_PROPERTY_EMAIL).toString().toLowerCase();

        /**
         * Encode user email replacing "." with ","
         * to be able to use it as a Firebase db key
         */
        mEncodedEmail = Utils.encodeEmail(unprocessedEmail);

        final Firebase userRef = new Firebase(Constants.FIREBASE_URL_USERS).child(mEncodedEmail);

        /**
         * Set raw version of date to the ServerValue.TIMESTAMP value and save into
         * timestampCreatedMap
         */
        HashMap<String, Object> timestampCreated = new HashMap<>();
        timestampCreated.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);

        /* build the User object */
        User user = new User(unprocessedEmail, timestampCreated, gender, dateOfBirth, occupation);

        /* add user details */
        userRef.setValue(user, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                changeUtilsMessage(firebaseError);
            }
        });

        return firebaseError;
    }

    public EntityItemDetails getEntityItemDetails (String pushId){
        final EntityItemDetails[] entityItemDetails = new EntityItemDetails[1];
        final boolean[] checker = {false};

        Log.v(LOG_TAG, "getEntityItemDetailsStart");

        final Firebase firebaseRef = new Firebase(Constants.FIREBASE_URL_ENTITY_ITEM_DETAILS);
        Query queryRef = firebaseRef.equalTo(pushId);


        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                entityItemDetails[0] = dataSnapshot.getValue(EntityItemDetails.class);
                Log.v(LOG_TAG, "onDataChange");
                checker[0] = true;

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                entityItemDetails[0] = null;
                Log.v(LOG_TAG, "onCancelled");
            }
        });

        while(checker[0] ==false){
            Log.v(LOG_TAG, "getEntityItemDetailsEnd");
        }


        return entityItemDetails[0];
    }

    public ArrayList<EntityItemDetails> getEntityItemDetailsList (ArrayList<String> pushIds){
        final ArrayList<EntityItemDetails> entityItemDetailsList = new ArrayList<EntityItemDetails>();

        for(int i = 0; i<pushIds.size();i++){
            final EntityItemDetails[] entityItemDetails = new EntityItemDetails[1];

            final Firebase firebaseRef = new Firebase(Constants.FIREBASE_URL_ENTITY_ITEM_DETAILS);
            Query queryRef = firebaseRef.equalTo(pushIds.get(i));


            queryRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    entityItemDetails[0] = dataSnapshot.getValue(EntityItemDetails.class);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    entityItemDetails[0] = null;
                }
            });

            entityItemDetailsList.add(entityItemDetails[0]);
        }


        return entityItemDetailsList;
    }

    public EntityParentDetails getEntityParentDetails (String pushID){
        final EntityParentDetails[] entityParentDetails = new EntityParentDetails[1];

        final Firebase firebaseRef = new Firebase(Constants.FIREBASE_URL_ENTITY_PARENT_DETAILS).child(pushID);
        firebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                entityParentDetails[0] = dataSnapshot.getValue(EntityParentDetails.class);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        return entityParentDetails[0];
    }

    private void changeUtilsMessage(FirebaseError firebaseError) {
        this.firebaseError = firebaseError;
    }

}
