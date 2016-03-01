package com.udacity.firebase.nowtify.utils;

import android.content.Context;
import android.util.Log;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ServerValue;
import com.firebase.client.ValueEventListener;
import com.udacity.firebase.nowtify.model.EntityChild;
import com.udacity.firebase.nowtify.model.EntityItemDetails;
import com.udacity.firebase.nowtify.model.EntityParentDetails;
import com.udacity.firebase.nowtify.model.User;
import com.udacity.firebase.nowtify.model.UserFollows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by MohamedAfiq on 23/1/16.
 */
public class FirebaseUtils{
    Firebase firebase = new Firebase("https://nowtify.firebaseio.com/geofire");
    Context context;
    FirebaseError firebaseError;
    private static final String LOG_TAG = FirebaseUtils.class.getSimpleName();

    public FirebaseUtils(Context context){this.context=context;}

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
        final EntityItemDetails[] entityItemDetails = {new EntityItemDetails()};

        Log.v(LOG_TAG, "getEntityItemDetailsStart");

        final Firebase firebaseRef = new Firebase(Constants.FIREBASE_URL_ENTITY_ITEM_DETAILS).child(pushId);

        /**
         * Check if current user has logged in at least once
         */
        firebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                entityItemDetails[0] = dataSnapshot.getValue(EntityItemDetails.class);
                Log.v("Entity Item Details", entityItemDetails[0].getLongDesc());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

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


    public ArrayList<EntityChild> convertRawQueryToEntityChild(ArrayList<String> rawQueryKeys){
        ArrayList<EntityChild> toReturn = null;

        if(rawQueryKeys != null){
            toReturn = new ArrayList<EntityChild>();
        } else {
            return toReturn;
        }

        for(String string:rawQueryKeys){
            EntityChild entityChild;
            toReturn.add(createEntityChild(string));
        }

        return toReturn;
    }

    public EntityChild createEntityChild(String string){
        List<String> splitter;
        EntityChild entityChild;
        splitter = Arrays.asList(string.split(","));
        entityChild = new EntityChild(splitter.get(0),splitter.get(1),splitter.get(2),splitter.get(3),splitter.get(3));
        return entityChild;
    }

    public ArrayList<EntityChild> convertEntityChildsToFollowedEntityChild(ArrayList<EntityChild> rawEntityChild, ArrayList<String> userFollowList){
        ArrayList<EntityChild> toReturn = new ArrayList<EntityChild>();

        Log.v("checkcheck", ""+rawEntityChild.size());

        if(rawEntityChild==null || userFollowList==null ){
            Log.v("checkcheck", "null");
            toReturn.add(new EntityChild("No Data","No Data","No Data","No Data","No Data"));
            return toReturn;
        }

        for(EntityChild entityChild:rawEntityChild){

            Log.v("checkcheck", entityChild.getEntityParentName());

            if(userFollowList.contains(entityChild.getEntityParentName())){
                toReturn.add(entityChild);
            }
        }

        if(toReturn.size()==0){
            Log.v("checkcheck", "size 0");
            toReturn.add(new EntityChild("No Data","No Data","No Data","No Data","No Data"));
        }

        return toReturn;
    }

    public boolean followEntityParent(String email, String entityParentId, UserFollows userFollows, boolean followOrUnfollow){
        String mEncodedEmail = Utils.encodeEmail(email);
        Firebase firebaseUserFollowsRef = new Firebase(Constants.FIREBASE_URL_ENTITY_USER_FOLLOWS).child(mEncodedEmail);
        HashMap<String, Object> follows = userFollows.getFollows();

        if(followOrUnfollow==true){
            follows.put(entityParentId,true);
        } else {
            follows.remove(entityParentId);
        }
        userFollows.setFollows(follows);

        firebaseUserFollowsRef.setValue(userFollows, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {

                if (firebaseError == null) {
                    Log.v("geofireutils","afiq980 follows updated");
                } else {
                    return;
                }

            }
        });

        return true;
    }

    /*
    public void getImageFromEntityItemId(String entityItemId){
        Firebase firebaseEntityItemDetailsImageRef = new Firebase(Constants.FIREBASE_URL_ENTITY_ITEM_DETAILS).child(entityItemId).child("encodedImage");
        final String[] imageURL = new String[1];

        firebaseEntityItemDetailsImageRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                imageURL[0] = snapshot.getValue().toString().replace(',','.');
                Log.v("firebaseUtils",imageURL[0]);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                //System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

    }
    */
}
