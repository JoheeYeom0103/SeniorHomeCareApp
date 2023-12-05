package com.example.seniorhomecareapp;


public class receiver_profile {
    private String name;
    private String bio;
    private String time;
    private String location;
    private boolean isSelected;

    // Constructors, getters, and setters

    public receiver_profile(String name, String location, String time, String bio) {
        this.name = name;
        this.bio = bio;
        this.time = time;
        this.location = location;
        this.isSelected = false;
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

}
