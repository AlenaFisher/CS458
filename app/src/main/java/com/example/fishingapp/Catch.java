package com.example.fishingapp;

/**
 * @author Devon Alonzo
 * @date 11-15-2003
 * This is a class that represents a catch a user made
 */
public class Catch {
    private int unique_ID;
    private int id;
    private String title;
    private String location;
    private String weight;
    private String baitUsed;
    private String length;

    public Catch(int id, String title, String location, String weight, String baitUsed, String length) {
        this.title = title;
        this.location = location;
        this.weight = weight;
        this.baitUsed = baitUsed;
        this.length = length;
        this.id = id;
    }

    public int getUnique_ID() {
        return unique_ID;
    }

    public int getId() {
        return id;
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

    public void setUnique_ID(int unique_ID) {
        this.unique_ID = unique_ID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public void setAllItems(String title, String length, String weight, String baitUsed, String location){
        this.title = title;
        this.location = location;
        this.weight = weight;
        this.baitUsed = baitUsed;
        this.length = length;
    }
}

