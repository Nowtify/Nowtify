package com.udacity.firebase.nowtify;

import com.firebase.client.Firebase;

/**
 * Includes one-time initialization of Firebase related code
 */
public class NowtifyApplication extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        /* Initialize Firebase */
        Firebase.setAndroidContext(this);
    }

}