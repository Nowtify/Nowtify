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
        EntityItemDetails entityItemDetails = (EntityItemDetails) getEntityItemDetails("-K91IGtm1KkUcw0p7cqJ");
        Log.v(LOG_TAG, entityItemDetails.toString());
    }

    public void addExampleEntity(String pushId){
        final Firebase userRef = new Firebase(Constants.FIREBASE_URL_ENTITY_ITEM_DETAILS);
        Firebase newListRef = userRef.push();

        /* Save listsRef.push() to maintain same random Id */
        final String listId = newListRef.getKey();

        Log.v(LOG_TAG, "addExampleEntity Ran");

        /* build the User object */
        EntityItemDetails entityItemDetails = new EntityItemDetails("Test","Test","Test","Test","Test","Test");


        /* Add the shopping list */
        newListRef.setValue(entityItemDetails);

        /* add user details */
        newListRef.setValue(entityItemDetails, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                Log.v(LOG_TAG, "Added Sample Object");
            }
        });
    }
}