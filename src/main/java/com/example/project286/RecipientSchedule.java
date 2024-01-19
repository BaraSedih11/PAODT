package com.example.project286;

public class RecipientSchedule {
    private String recipient_id;
    private String name;
    private String id;
    private String address;
    private String gender;
    private String bloodType;
    private String phone;
    private String email;
    private String priority;
    private String regDate;
    private String organ;

    public String getRecipient_id() {
        return recipient_id;
    }

    public void setRecipient_id(String recipient_id) {
        this.recipient_id = recipient_id;
    }


    public RecipientSchedule() {}

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
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

    public String getPriority() {
        return priority;
    }

    public String getRegDate() {
        return regDate;
    }

    public String getOrgan() {
        return organ;
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

    public void setGender(String gender) {
        this.gender = gender;
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

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
    }
}
