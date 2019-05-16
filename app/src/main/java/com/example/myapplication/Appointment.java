package com.example.myapplication;

public class Appointment {

    private String date;
    private String speciality;
    private String location;
    private String time;
    private String doctor;
    private String description;
    private String uID;

    public Appointment() {
    }

    public Appointment(String date, String speciality, String location, String time, String uID, String doctor, String description) {
        this.date = date;
        this.speciality = speciality;
        this.location = location;
        this.time = time;
        this.uID = uID;
        this.doctor = doctor;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
