package com.udacity.firebase.nowtify.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by MohamedAfiq on 23/1/16.
 */
public class Nowtify extends Application{

    private static Context context;

    public void onCreate() {
        super.onCreate();
        Nowtify.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return Nowtify.context;
    }
}

