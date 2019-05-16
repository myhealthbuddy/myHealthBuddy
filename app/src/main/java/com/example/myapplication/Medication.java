package com.example.myapplication;

public class Medication {
    private String med_startDate;
    private String med_name;
    private String med_dose;
    private String med_interval;
    private String med_endDate;
    private String uID;

    public Medication() {
    }

    public Medication(String startDate, String medName, String medDose, String medInterval, String endDate, String uID) {
        this.med_startDate = startDate;
        this.med_name = medName;
        this.med_dose = medDose;
        this.med_interval = medInterval;
        this.med_endDate = endDate;
        this.uID = uID;
    }

    public String getStartDate() {
        return med_startDate;
    }

    public void setStartDate(String startDate) {
        this.med_startDate = startDate;
    }

    public String getMedName() {
        return med_name;
    }

    public void setMedName(String medName) {
        this.med_name = medName;
    }

    public String getMedDose() {
        return med_dose;
    }

    public void setMedDose(String medDose) {
        this.med_dose = medDose;
    }

    public String getMedIntl() {
        return med_interval;
    }

    public void setMedIntl(String medInterval) {
        this.med_interval = medInterval;
    }

    public String getEndDate() {
        return med_endDate;
    }

    public void setEndDate(String endDate) {
        this.med_endDate = endDate;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }
}
