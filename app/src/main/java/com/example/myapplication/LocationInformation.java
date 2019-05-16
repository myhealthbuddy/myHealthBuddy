package com.example.myapplication;

public class LocationInformation {

    private String name;
    private double latitude;
    private double longitude;

    public LocationInformation(){

    }

    public LocationInformation(String name,double latitude,double longitude){
        this.name=name;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        name = Name;
    }

    public double getLatitude() {return latitude;}

    public void setlatitude(Double Latitiude){ latitude = Latitiude;}

    public double getLongtitude() {return longitude;}

    public void setLongitude(Double Longtitude){ longitude = Longtitude;}



}

