package com.example.fishingapp.Domains;

public class Hourly {
    private String hour;
    private int temp;
    private String imageRes;

    public Hourly(String hour, int temp, String imageRes) {
        this.hour = hour;
        this.temp = temp;
        this.imageRes = imageRes;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public String getImageRes() {
        return imageRes;
    }

    public void setImageRes(String imageRes) {
        this.imageRes = imageRes;
    }
}