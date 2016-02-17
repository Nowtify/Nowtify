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

    public void createLocations(){
        geoFire.setLocation("testID,testPicture,1-for-1 Coffee with Breakfast and Lunch! Table and chair included!,MoonBucks Coffee,Address", new GeoLocation(1.2965249, 103.8498663));
        geoFire.setLocation("testID,testPicture,Holy shit this discount is damn good!!! FREE LUNCH!!!,Koufu Cafe,Address", new GeoLocation(1.2965249, 103.8498663));
        geoFire.setLocation("testID,testPicture,40% off nothing much in this store! 5% off on everything outside of this store!,1992 Restaurant,Address", new GeoLocation(1.2965249, 103.8498663));
    }


}