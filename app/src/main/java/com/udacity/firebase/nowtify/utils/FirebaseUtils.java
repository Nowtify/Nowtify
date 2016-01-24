package com.udacity.firebase.nowtify.utils;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ServerValue;
import com.udacity.firebase.nowtify.model.User;

import java.util.HashMap;

/**
 * Created by MohamedAfiq on 23/1/16.
 */
public class FirebaseUtils{

    FirebaseError firebaseError;

    public FirebaseError updateUserDetails(String email, AuthData authData, String gender, String occupation){

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
        User user = new User(unprocessedEmail, timestampCreated, gender, occupation);

        /* add user details */
        userRef.setValue(user, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                changeUtilsMessage(firebaseError);
            }
        });

        return firebaseError;
    }

    private void changeUtilsMessage(FirebaseError firebaseError) {
        this.firebaseError = firebaseError;
    }

}
