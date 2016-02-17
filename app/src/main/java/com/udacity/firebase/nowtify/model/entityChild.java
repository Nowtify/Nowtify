package com.udacity.firebase.nowtify.model;

/**
 * Created by MohamedAfiq on 27/1/16.
 */
public class EntityChild {
    String pushId, entityItemDetailsId, title, entityParentName, address;

    public EntityChild() {
    }

    public EntityChild(String pushId, String entityItemDetailsId, String title, String entityParentName, String address) {
        this.pushId = pushId;
        this.entityItemDetailsId = entityItemDetailsId;
        this.title = title;
        this.entityParentName = entityParentName;
        this.address = address;
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

    public String toString(){
        return getPushId()+getEntityItemDetailsId()+getTitle()+getEntityParentName();
    }
}
