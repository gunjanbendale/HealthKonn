package com.healthkonn.healthkonnect.model;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LoginDetails {
    @SerializedName("username")
    @Expose
    private String usrname;
    @SerializedName("password")
    @Expose
    private String password;

    public LoginDetails(String usrname, String password) {
        this.usrname = usrname;
        this.password = password;
    }

    public String getUsrname() {
        return usrname;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}