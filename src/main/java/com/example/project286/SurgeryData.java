package com.example.project286;

public class SurgeryData {
    private String surgeryNumber;
    private String surgeryDate;
    private String organ;
    private String result;
    private String hospitalName;
    private String donorId;
    private String recipientId;
    private String doctorName;


    public void setSurgeryNumber(String surgeryNumber) {
        this.surgeryNumber = surgeryNumber;
    }

    public void setSurgeryDate(String surgeryDate) {
        this.surgeryDate = surgeryDate;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSurgeryNumber() {
        return surgeryNumber;
    }

    public String getSurgeryDate() {
        return surgeryDate;
    }

    public String getOrgan() {
        return organ;
    }

    public String getResult() {
        return result;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getDonorId() {
        return donorId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public String getDoctorName() {
        return doctorName;
    }
}
