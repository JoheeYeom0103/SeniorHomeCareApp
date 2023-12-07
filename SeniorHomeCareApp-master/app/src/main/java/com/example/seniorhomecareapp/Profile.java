package com.example.seniorhomecareapp;

import java.io.Serializable;

public class Profile  implements Serializable {
    private String name;
    private String bio;
    private String time;
    private String location;
    private boolean isSelected;
    private int rating;
    private boolean certified;
    private boolean verified;
    // Constructors, getters, and setters

    public Profile(String name, String location, String time, String bio, int rating, boolean certified, boolean verified) {
        //ken File to load user profiles, gonna get DB to work with it
        this.name = name;
        this.bio = bio;
        this.time = time;
        this.location = location;
        this.isSelected = false;
        this.rating = rating;
        this.verified = verified;
        this.certified = certified;
    }
    public String getName() {
        return name;
    }
    public String getBio() {
        return bio;
    }
    public String getTime() {
        return time;
    }
    public String getLocation() {
        return location;
    }
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getRating() {
        return rating;
    }
    public boolean getCertified(){return certified;}
    public boolean getVerified(){return verified;}

}

