package com.example.fishingapp.Domains;

public class TomorrowDomain {
    private String day;
    private String imageRes;
    private String status;
    private String highTemp;
    private String lowTemp;

    public TomorrowDomain(String day, String imageRes, String status, String highTemp, String lowTemp) {
        this.day = day;
        this.imageRes = imageRes;
        this.status = status;
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getImageRes() {
        return imageRes;
    }

    public void setImageRes(String imageRes) {
        this.imageRes = imageRes;
    }

    public String getHighTemp() {
        return highTemp;
    }

    public void setHighTemp(String highTemp) {
        this.highTemp = highTemp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLowTemp() {
        return lowTemp;
    }

    public void setLowTemp(String lowTemp) {
        this.lowTemp = lowTemp;
    }
}

