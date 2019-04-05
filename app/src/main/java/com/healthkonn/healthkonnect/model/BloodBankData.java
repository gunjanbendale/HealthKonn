package com.healthkonn.healthkonnect.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BloodBankData {
    @SerializedName("bloodbanks")
    @Expose
    private List<BloodBankResultsData> bloodbanks = null;

    public List<BloodBankResultsData> getBloodbanks() {
        return bloodbanks;
    }

    public void setBloodbanks(List<BloodBankResultsData> bloodbanks) {
        this.bloodbanks = bloodbanks;
    }
}
