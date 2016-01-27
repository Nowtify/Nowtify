package com.udacity.firebase.nowtify.model;

/**
 * Created by MohamedAfiq on 27/1/16.
 */
public class EntityItemDetails {
    String pushId;
    String parentPushId;
    String title;
    String shortDesc;
    String longDesc;
    String tnc;
    String encodedImage;

    public EntityItemDetails() {
    }

    public EntityItemDetails(String pushId, String parentPushId, String title, String shortDesc, String longDesc, String tnc, String encodedImage){
        this.pushId=pushId;
        this.parentPushId=parentPushId;
        this.title=title;
        this.shortDesc=shortDesc;
        this.longDesc=longDesc;
        this.tnc=tnc;
        this.encodedImage=encodedImage;
    }

    public String getPushId() {
        return pushId;
    }

    public String getParentPushId() {
        return parentPushId;
    }

    public String getTitle() {
        return title;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public String getTnc() {
        return tnc;
    }

    public String getEncodedImage() {
        return encodedImage;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
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

    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }
}
