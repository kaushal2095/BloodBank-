package com.example.bloodbank.viewmodels;

public class UserData {
    private String Name, Email, Contact, Address;
    private int Gender, BloodGroup, Division;

    public UserData(){

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
    }

    public int getBloodGroup() {
        return BloodGroup;
    }

    public void setBloodGroup(int bloodGroup) {
        BloodGroup = bloodGroup;
    }

    public int getDivision() {
        return Division;
    }

    public void setDivision(int division) {
        Division = division;
    }
}
