package com.udacity.firebase.nowtify.utils;

import android.content.Context;

import com.firebase.client.Firebase;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.udacity.firebase.nowtify.model.EntityChild;

import java.util.ArrayList;
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

    public void createLocations(int i){
        geoFire.setLocation("testID,testPicture,testTitle,testEntityTitle,Address" + i, new GeoLocation(90, 90));
    }


}