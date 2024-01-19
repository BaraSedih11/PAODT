package com.example.project286;

public class Recipient {

    private String id;
    private String name;
    private String neededMember;
    private String phone;
    private String email;
    private String address;
    private String registrationDate;
    private String precedence;
    private String recieved;

    public String getRecipient_id() {
        return recipient_id;
    }

    public void setRecipient_id(String recipient_id) {
        this.recipient_id = recipient_id;
    }

    private String recipient_id;

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    private String bloodType;

    public Recipient(String id, String name, String organ, String phone, String email, String address, String registrationDate, String precedence, String recieved) {
        this.id = id;
        this.name = name;
        this.neededMember = organ;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.registrationDate = registrationDate;
        this.precedence = precedence;
        this.recieved = recieved;
    }
    public Recipient(){}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOrgan() {
        return neededMember;
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

    public String getNeededMember() {
        return neededMember;
    }

    public String getPrecedence() {
        return precedence;
    }

    public String getRecieved() {
        return recieved;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    //----------


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setOrgan(String organ) {
        this.neededMember = organ;
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

    public void setNeededMember(String neededMember) {
        this.neededMember = neededMember;
    }

    public void setPrecedence(String precedence) {
        this.precedence = precedence;
    }

    public void setRecieved(String recieved) {
        this.recieved = recieved;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
}
