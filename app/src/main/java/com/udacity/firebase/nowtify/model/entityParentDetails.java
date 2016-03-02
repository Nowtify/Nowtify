package com.udacity.firebase.nowtify.model;

import java.util.HashMap;

/**
 * Created by MohamedAfiq on 27/1/16.
 */
public class EntityParentDetails {
    String pushId;
    private HashMap<String, Object> entityItemDetailsID;
    String name;
    String desc;
    String address;
    String number;
    private HashMap<String, Object> tags;


    public EntityParentDetails() {
    }

    public EntityParentDetails(String pushId, String name, String desc, String address, String number){
        this.pushId=pushId;
        this.name=name;
        this.desc=desc;
        this.address=address;
        this.number=number;
    }

    public String getAddress() {
        return address;
    }

    public String getPushId() {
        return pushId;
    }

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public HashMap<String, Object> getEntityItemDetailsID(){
        return entityItemDetailsID;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDesc(String longDesc) {
        this.desc = desc;
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

    public void setEntityItemDetailsID(HashMap<String, Object> entityItemDetailsID){
        this.entityItemDetailsID = entityItemDetailsID;
    }

}
