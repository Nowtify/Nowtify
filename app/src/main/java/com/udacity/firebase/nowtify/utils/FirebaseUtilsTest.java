package com.udacity.firebase.nowtify.utils;

import android.util.Log;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.udacity.firebase.nowtify.model.EntityItemDetails;

/**
 * Created by MohamedAfiq on 27/1/16.
 */
public class FirebaseUtilsTest extends FirebaseUtils{

    private static final String LOG_TAG = FirebaseUtilsTest.class.getSimpleName();

    public void getEntityItemDetailsTest(){
        addExampleEntity("123456");
        EntityItemDetails entityItemDetails = getEntityItemDetails("123456");
        Log.v(LOG_TAG, entityItemDetails.getTitle());
    }

    public void addExampleEntity(String pushId){
        final Firebase userRef = new Firebase(Constants.FIREBASE_URL_ENTITY_ITEM_DETAILS).child(pushId);

        /* build the User object */
        EntityItemDetails entityItemDetails = new EntityItemDetails(pushId,"Test","Test","Test","Test","Test","Test");

        /* add user details */
        userRef.setValue(entityItemDetails, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
            }
        });
    }
}
