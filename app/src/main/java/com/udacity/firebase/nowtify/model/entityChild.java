package com.udacity.firebase.nowtify.model;

import java.util.ArrayList;

/**
 * Created by MohamedAfiq on 27/1/16.
 */
public class EntityChild {
    String pushId, entityItemDetailsId, title, entityParentId, entityParentName, address;
    private ArrayList<String> tags;

    public EntityChild() {
    }

    public EntityChild(String pushId, String entityItemDetailsId, String title, String entityParentId, String entityParentName, String address, ArrayList<String> tags) {
        this.pushId = pushId;
        this.entityItemDetailsId = entityItemDetailsId;
        this.title = title;
        this.entityParentId = entityParentId;
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

    public String getEntityParentId() {
        return entityParentId;
    }

    public void setEntityParentId(String entityParentId) {
        this.entityParentId = entityParentId;
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

    public ArrayList<String> getTags (){
        return tags;
    }

    public void setTags (ArrayList<String> tags){
        this.tags = tags;
    }

    public String toString(){
        return getPushId()+""+getEntityItemDetailsId()+""+getTitle()+""+getEntityParentName()+""+getAddress();
    }
}
