package com.udacity.firebase.nowtify.model;

/**
 * Created by MohamedAfiq on 27/1/16.
 */
public class EntityItemDetails {
    String parentPushId;
    String title;
    String desc;
    String tnc;
    String imageURL;

    public EntityItemDetails() {
    }

    public EntityItemDetails(String parentPushId, String title, String desc, String tnc, String imageURL){
        this.parentPushId=parentPushId;
        this.title=title;
        this.desc=desc;
        this.tnc=tnc;
        this.imageURL=imageURL;
    }

    public String getParentPushId() {
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

    public String getImageURL() {
        return imageURL;
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

    public void setParentPushId(String parentPushId) {
        this.parentPushId = parentPushId;
    }

    public void setImageURL(String encodedImage) {
        this.imageURL = imageURL;
    }
}
