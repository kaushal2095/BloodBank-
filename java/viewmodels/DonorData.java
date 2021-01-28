package com.example.bloodbank.viewmodels;

public class DonorData {

    private int TotalDonate;
    private String LastDonate, Name, Contact, UID, Address;


    public DonorData() {

    }

    public int getTotalDonate() {
        return TotalDonate;
    }

    public void setTotalDonate(int totalDonate) {
        TotalDonate = totalDonate;
    }

    public String getLastDonate() {
        return LastDonate;
    }

    public void setLastDonate(String lastDonate) {
        LastDonate = lastDonate;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
