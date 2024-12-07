package com.example.fishingapp;

/**
 * @author Devon Alonzo
 * @date 11-15-2003
 * This is a class that represents a catch a user made
 */
public class Catch {
    private String title;
    private String location;
    private String weight;
    private String baitUsed;
    private String length;

    public Catch(String title, String location, String weight, String baitUsed, String length) {
        this.title = title;
        this.location = location;
        this.weight = weight;
        this.baitUsed = baitUsed;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getWeight() {
        return weight;
    }

    public String getBaitUsed() {
        return baitUsed;
    }

    public String getLength() {
        return length;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String location) {
        this.location = location;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setBaitUsed(String baitUsed) {
        this.baitUsed = baitUsed;
    }

    public void setLength(String length) {
        this.length = length;
    }
}
