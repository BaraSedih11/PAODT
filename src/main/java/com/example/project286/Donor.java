package com.example.project286;

public class Donor {

    private String id;
    private String name;
    private String organ;
    private String witnessName;
    private String bloodType;
    private String phone;
    private String email;
    private String address;
    private String state;

    public String getDonor_id() {
        return donor_id;
    }

    public void setDonor_id(String donor_id) {
        this.donor_id = donor_id;
    }

    private String donor_id;

    public Donor(String id, String name, String organ, String wName ,String bloodType, String phone, String email, String address) {
        this.id = id;
        this.name = name;

        this.organ = organ;
        this.witnessName = wName;

        this.bloodType = bloodType;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
    public Donor(){}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOrgan() {
        return organ;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getState() {
        return state;
    }

    //----------


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setOrgan(String organ) {
        this.organ = organ;
    }

    public String getWitnessName() {
        return witnessName;
    }

    public void setWitnessName(String witnessName) {
        this.witnessName = witnessName;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setState(String state) {
        this.state = state;
    }
}
