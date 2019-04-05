package com.healthkonn.healthkonnect.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class History{
    @SerializedName("appointments")
    @Expose
    private List<HistoryDetails> orders = null;


    public List<HistoryDetails> getOrders() {
        return orders;
    }

    public void setOrders(List<HistoryDetails> orders) {
        this.orders = orders;
    }


}
