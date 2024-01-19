package com.example.project286;

public class Employee {
    String id;
    String name;
    String rank;
    String workingTime;
    String phone;
    String email;
    String startDate;
    String address;

    public String getBld() {
        return bld;
    }

    public void setBld(String bld) {
        this.bld = bld;
    }

    String bld;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    String username;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String password;

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    String salary;

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }

    String blood_type;


    public Employee(String id, String name, String rank, String workingTime, String phone, String email, String startDate, String address) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.workingTime = workingTime;
        this.phone = phone;
        this.email = email;
        this.startDate = startDate;
        this.address = address;
    }

    public Employee(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
