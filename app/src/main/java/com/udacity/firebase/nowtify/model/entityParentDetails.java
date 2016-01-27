package com.udacity.firebase.nowtify.model;

/**
 * Created by MohamedAfiq on 27/1/16.
 */
public class EntityParentDetails {
    String pushId;
    String name;
    String shortDesc;
    String longDesc;
    String address;
    String number;

    public EntityParentDetails() {
    }

    public EntityParentDetails(String pushId, String name, String shortDesc, String longDesc, String address, String number){
        this.pushId=pushId;
        this.name=name;
        this.shortDesc=shortDesc;
        this.longDesc=longDesc;
        this.address=address;
        this.number=number;
    }

    public String getAddress() {
        return address;
    }

    public String getPushId() {
        return pushId;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

}
