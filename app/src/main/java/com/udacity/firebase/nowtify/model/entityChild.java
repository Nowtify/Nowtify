package com.udacity.firebase.nowtify.model;

import java.util.HashMap;

/**
 * Created by MohamedAfiq on 27/1/16.
 */
public class EntityChild {
    String pushId, entityItemDetailsId, title, entityParentName, address;
    private HashMap<String, Object> tags;

    public EntityChild() {
    }

    public EntityChild(String pushId, String entityItemDetailsId, String title, String entityParentName, String address, HashMap<String, Object> tags) {
        this.pushId = pushId;
        this.entityItemDetailsId = entityItemDetailsId;
        this.title = title;
        this.entityParentName = entityParentName;
        this.address = address;
        this.tags = tags;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getEntityItemDetailsId() {
        return entityItemDetailsId;
    }

    public void setEntityItemDetailsId(String entityItemDetailsId) {
        this.entityItemDetailsId = entityItemDetailsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEntityParentName() {
        return entityParentName;
    }

    public void setEntityParentName(String entityParentName) {
        this.entityParentName = entityParentName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HashMap<String, Object> getTags (){
        return tags;
    }

    public void setTags (HashMap<String, Object> tags){
        this.tags = tags;
    }

    public String toString(){
        return getPushId()+""+getEntityItemDetailsId()+""+getTitle()+""+getEntityParentName()+""+getAddress();
    }
}
