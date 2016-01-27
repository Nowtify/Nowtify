package com.udacity.firebase.nowtify.model;

/**
 * Created by MohamedAfiq on 27/1/16.
 */
public class EntityChild {
    String pushId;
    double coordinateX;
    double coordinateY;

    public EntityChild() {
    }

    public EntityChild(String pushId, double coordinateX, double coordinateY){
        this.pushId=pushId;
        this.coordinateX=coordinateX;
        this.coordinateY=coordinateY;
    }

    public String getPushId() {
        return pushId;
    }

    public double getCoordinateX() {
        return coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateX(double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public void setCoordinateY(double coordinateY) {
        this.coordinateY = coordinateY;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

}
