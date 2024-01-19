package com.example.project286;

public class DonorSchedule {

    private String name;
    private String donor_id ;
    private String id;
    private String address;
    private String organs;
    private String gender;
    private String bloodType;
    private String witnessName;
    private String witnessPhone;

    private String donated;

    public String getDonor_id() {
        return donor_id;
    }

    public void setDonor_id(String donor_id) {
        this.donor_id = donor_id;
    }

    public DonorSchedule() {}

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getOrgans() {
        return organs;
    }

    public String getGender() {
        return gender;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getWitnessName() {
        return witnessName;
    }

    public String getWitnessPhone() {
        return witnessPhone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setOrgans(String organs) {
        this.organs = organs;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public void setWitnessName(String witnessName) {
        this.witnessName = witnessName;
    }

    public void setWitnessPhone(String witnessPhone) {
        this.witnessPhone = witnessPhone;
    }

    public String getDonated() {
        return donated;
    }

    public void setDonated(String donated) {
        this.donated = donated;
    }

}
