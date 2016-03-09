package com.udacity.firebase.nowtify.model;

import java.util.HashMap;

/**
 * Created by MohamedAfiq on 27/1/16.
 */
public class EntityItemDetails {
    String parentPushId;
    String title;
    String desc;
    String tnc;
    private HashMap<String, Object> images;

    public EntityItemDetails() {
    }

    public EntityItemDetails(String parentPushId, String title, String desc, String tnc, HashMap<String, Object> images){
        this.parentPushId=parentPushId;
        this.title=title;
        this.desc=desc;
        this.tnc=tnc;
        this.images=images;
    }

    public String getParentPushId(){
        return parentPushId;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getTnc() {
        return tnc;
    }

    public HashMap<String, Object> getImages() {
        return images;
    }

    public void setParentPushId(String parentPushId){
        this.parentPushId=parentPushId;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTnc(String tnc) {
        this.tnc = tnc;
    }
    
    public void setImages(HashMap<String, Object> images) {
        this.images = images;
    }
}
