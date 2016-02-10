package com.udacity.firebase.nowtify.model;

import java.util.HashMap;

/**
 * Created by MohamedAfiq on 27/1/16.
 */
public class UserFollows {
    private HashMap<String, Object> follows;

    public UserFollows() {
    }

    public void setFollows(HashMap<String, Object> follows) {
        this.follows = follows;
    }

    public HashMap<String, Object> getFollows(){
        return follows;
    }


/*
    public ArrayList<EntityChild> returnFollowedEntities(ArrayList<EntityChild> rawQuery){
        ArrayList<EntityChild> toReturn = new ArrayList<EntityChild>();
        for(EntityChild entityChild:rawQuery){
            if(isFollowing(entityChild.getEntityParentName())){
                toReturn.add(entityChild);
            }
        }
        return toReturn;
    }*/
}
