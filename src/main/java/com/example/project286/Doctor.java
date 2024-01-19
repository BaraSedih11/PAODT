package com.example.project286;

public class Doctor {

    private String doctor_id;
    private String name;

    public String getHosp() {
        return hosp;
    }

    public void setHosp(String hosp) {
        this.hosp = hosp;
    }

    private String hosp;

    public Doctor(){
    }

    public Doctor(String fName, String sName, String tName, String lName) {
        this.name = fName + " " + sName + " " + tName + " " + lName;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
