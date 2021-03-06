package com.udacity.firebase.nowtify.utils;

import com.udacity.firebase.nowtify.BuildConfig;

/**
 * Constants class store most important strings and paths of the app
 */
public final class Constants {

    /**
     * Constants related to locations in Firebase, such as the name of the node
     * where active lists are stored (ie "activeLists")
     */
    public static final String FIREBASE_LOCATION_USERS = "users";
    public static final String FIREBASE_LOCATION_GEOFIRE = "geofire";
    public static final String FIREBASE_LOCATION_ENTITY_ITEM_DETAILS = "entityItemDetails";
    public static final String FIREBASE_LOCATION_ENTITY_PARENT_DETAILS = "entityParentDetails";
    public static final String FIREBASE_LOCATION_ENTITY_PARENT = "entityParent";
    public static final String FIREBASE_LOCATION_ENTITY_CHILD = "entityChild";
    public static final String FIREBASE_LOCATION_USER_FOLLOWS = "userfollows";



    /**
     * Constants for Firebase object properties
     */
    public static final String FIREBASE_PROPERTY_LIST_NAME = "listName";
    public static final String FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED = "timestampLastChanged";
    public static final String FIREBASE_PROPERTY_TIMESTAMP = "timestamp";
    public static final String FIREBASE_PROPERTY_ITEM_NAME = "itemName";
    public static final String FIREBASE_PROPERTY_USER_FOLLOW = "follows";
    public static final String FIREBASE_PROPERTY_EMAIL = "email";
    public static final String FIREBASE_PROPERTY_USER_HAS_LOGGED_IN_WITH_PASSWORD = "hasLoggedInWithPassword";

    /**
    * Constants for Firebase URL
    */
    public static final String FIREBASE_URL = BuildConfig.UNIQUE_FIREBASE_ROOT_URL;
    public static final String FIREBASE_URL_USERS = FIREBASE_URL + "/" + FIREBASE_LOCATION_USERS;
    public static final String FIREBASE_URL_GEOFIRE = FIREBASE_URL + "/" + FIREBASE_LOCATION_GEOFIRE;
    public static final String FIREBASE_URL_ENTITY_ITEM_DETAILS = FIREBASE_URL + "/" + FIREBASE_LOCATION_ENTITY_ITEM_DETAILS;
    public static final String FIREBASE_URL_ENTITY_CHILD = FIREBASE_URL + "/" + FIREBASE_LOCATION_ENTITY_CHILD;
    public static final String FIREBASE_URL_ENTITY_PARENT = FIREBASE_URL + "/" + FIREBASE_LOCATION_ENTITY_PARENT;
    public static final String FIREBASE_URL_ENTITY_PARENT_DETAILS = FIREBASE_URL + "/" + FIREBASE_LOCATION_ENTITY_PARENT_DETAILS;
    public static final String FIREBASE_URL_ENTITY_USER_FOLLOWS = FIREBASE_URL + "/" + FIREBASE_LOCATION_USER_FOLLOWS;


     /**
     * Constants for bundles, extras and shared preferences keys
     */
    public static final String KEY_LIST_NAME = "LIST_NAME";
    public static final String KEY_LAYOUT_RESOURCE = "LAYOUT_RESOURCE";
    public static final String KEY_LIST_ID = "LIST_ID";
    public static final String KEY_SIGNUP_EMAIL = "SIGNUP_EMAIL";
    public static final String KEY_LIST_ITEM_NAME = "ITEM_NAME";
    public static final String KEY_LIST_ITEM_ID = "LIST_ITEM_ID";
    public static final String KEY_PROVIDER = "PROVIDER";
    public static final String KEY_ENCODED_EMAIL = "ENCODED_EMAIL";
    public static final String KEY_LIST_OWNER = "LIST_OWNER";
    public static final String KEY_GOOGLE_EMAIL = "GOOGLE_EMAIL";

    /**
     * Constants for FireBase login
     */
    public static final String PASSWORD_PROVIDER = "password";

    /**
     * Constants for GeoFire
     */
    public static final String WALKING_DISTANCE = "0.2";


}
