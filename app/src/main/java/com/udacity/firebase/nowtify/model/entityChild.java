package com.udacity.firebase.nowtify.model;

/**
 * Created by MohamedAfiq on 27/1/16.
 */
public class EntityChild {
    String pushId, image, title, entityParentName;

    public EntityChild() {
    }

    public EntityChild(String pushId, String image, String title, String entityParentName) {
        this.pushId = pushId;
        this.image = image;
        this.title = title;
        this.entityParentName = entityParentName;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String toString(){
        return getPushId()+getImage()+getTitle()+getEntityParentName();
    }
}
