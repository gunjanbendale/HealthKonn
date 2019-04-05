package com.healthkonn.healthkonnect.model;

import android.os.Parcel;
import android.os.Parcelable;

public class HistoryDetails {
    String date,status,name;

    public HistoryDetails(String date, String status, String name) {
        this.date = date;
        this.status = status;
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
