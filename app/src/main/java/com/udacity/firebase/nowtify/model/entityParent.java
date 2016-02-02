package com.udacity.firebase.nowtify.model;

import java.util.ArrayList;

/**
 * Created by MohamedAfiq on 27/1/16.
 */
public class EntityParent {
    String pushId;
    ArrayList<String> childPushId;

    public EntityParent() {
    }

    public EntityParent(String pushId, ArrayList<String> childPushId){
        this.pushId=pushId;
        this.childPushId=childPushId;
    }

    public String getPushId(){
        return pushId;
    }

    public ArrayList<String> getChildPushId(){
        return childPushId;
    }
}
