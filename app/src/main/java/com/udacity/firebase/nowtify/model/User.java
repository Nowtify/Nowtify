package com.udacity.firebase.nowtify.model;

import java.util.HashMap;

/**
 * Defines the data structure for User objects.
 */
public class User {
    private String name;
    private String email;
    private String gender;
    private String occupation;
    private String dateOfBirth;
    private HashMap<String, Object> timestampJoined;
    private boolean hasLoggedInWithPassword;


    /**
     * Required public constructor
     */
    public User() {
    }

    /**
     * Use this constructor to create new User.
     * Takes user name, email and timestampJoined as params
     *
     * @param email
     * @param timestampJoined
     */
    public User(String email, HashMap<String, Object> timestampJoined, String gender, String dateOfBirth, String occupation) {
        this.email = email;
        this.timestampJoined = timestampJoined;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.occupation = occupation;
        this.hasLoggedInWithPassword = false;
    }

    /**
     * Use this constructor to create new User.
     * Takes user name, email and timestampJoined as params
     *
     * @param email
     * @param timestampJoined
     */
    public User(String email, HashMap<String, Object> timestampJoined, String gender, String dateOfBirth, String occupation, boolean hasLoggedInWithPassword) {
        this.email = email;
        this.timestampJoined = timestampJoined;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.occupation = occupation;
        this.hasLoggedInWithPassword = hasLoggedInWithPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public HashMap<String, Object> getTimestampJoined() {
        return timestampJoined;
    }

    public boolean isHasLoggedInWithPassword() {
        return hasLoggedInWithPassword;
    }
}
