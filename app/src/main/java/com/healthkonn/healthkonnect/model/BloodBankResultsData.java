package com.healthkonn.healthkonnect.model;

public class BloodBankResultsData {


        String name,area,contact;

        public BloodBankResultsData(String name, String area, String contact) {
            this.name = name;
            this.area = area;
            this.contact = contact;
        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
