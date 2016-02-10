package com.udacity.firebase.nowtify.utils;

import android.content.Context;
import android.util.Log;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.udacity.firebase.nowtify.model.EntityChild;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by MohamedAfiq on 6/2/16.
 */
public class GeofireUtils {
    Firebase firebase = new Firebase("https://nowtify.firebaseio.com/geofire");
    GeoFire geoFire = new GeoFire(firebase);
    Context context;
    ArrayList<String> testLocs = new ArrayList<String>();
    List<String> splitter;
    ArrayList<EntityChild> testList = new ArrayList<EntityChild>();

    public GeofireUtils(Context context){this.context=context;}

    public void test(){

        for(int i=0;i<=50;i++){
            createLocations(i);
        }

        GeoQuery query = geoFire.queryAtLocation(new GeoLocation(37.7, -122.4), 10);
        query.addGeoQueryEventListener(new GeoQueryEventListener() {


            @Override
            public void onKeyEntered(String key, GeoLocation location) {
                //Log.v("GeoFire",key + " " + location.toString());
                //testLocs.add(key + " " + location.toString());
                testList.add(createEntityChild(key));
                Log.v("GeoFire", "Waiting");
            }

            @Override
            public void onKeyExited(String key) {

            }

            @Override
            public void onKeyMoved(String key, GeoLocation location) {

            }

            @Override
            public void onGeoQueryReady() {
                for(EntityChild string:testList){
                    Log.v("GeoFire", string.toString());
                }
            }

            @Override
            public void onGeoQueryError(FirebaseError error) {

            }
        });
    }

    public void createLocations(int i){
        geoFire.setLocation("testID"+i+",testPicture,testTitle,testEntityTitle"+i, new GeoLocation(90,90));
    }

    public EntityChild createEntityChild(String string){
        EntityChild entityChild;
        splitter = Arrays.asList(string.split(","));
        entityChild = new EntityChild(splitter.get(0),splitter.get(1),splitter.get(2),splitter.get(3));
        return entityChild;
    }

    public ArrayList<EntityChild> convertRawQueryToEntityChild(ArrayList<String> rawQueryKeys){
        ArrayList<EntityChild> toReturn = null;

        if(rawQueryKeys != null){
            toReturn = new ArrayList<EntityChild>();
        }

        for(String string:rawQueryKeys){
            EntityChild entityChild;
            toReturn.add(createEntityChild(string));
        }

        return toReturn;
    }
}