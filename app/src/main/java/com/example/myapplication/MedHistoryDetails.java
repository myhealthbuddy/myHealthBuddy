package com.example.myapplication;

public class MedHistoryDetails {
    private String sDate;
    private String eDate;
    private String mName;
    private String mDose;
    private String mIntl;

    public String geteDate() {
        return eDate;
    }

    public void seteDate(String eDate) {
        this.eDate = eDate;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDose() {
        return mDose;
    }

    public void setmDose(String mDose) {
        this.mDose = mDose;
    }

    public String getmIntl() {
        return mIntl;
    }

    public void setmIntl(String mIntl) {
        this.mIntl = mIntl;
    }

    public String getsDate() {
        return sDate;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }
}
