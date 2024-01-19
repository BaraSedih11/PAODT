package com.example.project286;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;


public class InDonorA2Handler implements Initializable {

    @FXML
    private JFXCheckBox bloodPressure;

    @FXML
    private JFXComboBox<String> bloodType;

    @FXML
    private JFXCheckBox diabetes;

    @FXML
    private JFXCheckBox drinkingAlcohol;

    @FXML
    private JFXCheckBox hardDiseases;

    @FXML
    private JFXCheckBox heartDiseases;

    @FXML
    private JFXCheckBox liverDiseases;

    @FXML
    private JFXCheckBox mentalIssues;

    @FXML
    private AnchorPane paneA2;

    @FXML
    private JFXCheckBox smoking;


    public AnchorPane getPaneA2() {
        return paneA2;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String types[] = {"A+","A-","B+","B-","AB+","AB-","O+","O-"};
        bloodType.getItems().addAll(types);
    }
    //setters


    public void setBloodPressure(JFXCheckBox bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public void setBloodType(JFXComboBox<String> bloodType) {
        this.bloodType = bloodType;
    }

    public void setDiabetes(JFXCheckBox diabetes) {
        this.diabetes = diabetes;
    }

    public void setDrinkingAlcohol(JFXCheckBox drinkingAlcohol) {
        this.drinkingAlcohol = drinkingAlcohol;
    }

    public void setHardDiseases(JFXCheckBox hardDiseases) {
        this.hardDiseases = hardDiseases;
    }

    public void setHeartDiseases(JFXCheckBox heartDiseases) {
        this.heartDiseases = heartDiseases;
    }

    public void setLiverDiseases(JFXCheckBox liverDiseases) {
        this.liverDiseases = liverDiseases;
    }

    public void setMentalIssues(JFXCheckBox mentalIssues) {
        this.mentalIssues = mentalIssues;
    }

    public void setPaneA2(AnchorPane paneA2) {
        this.paneA2 = paneA2;
    }

    public void setSmoking(JFXCheckBox smoking) {
        this.smoking = smoking;
    }

    //getters


    public JFXCheckBox getBloodPressure() {
        return bloodPressure;
    }

    public JFXComboBox<String> getBloodType() {
        return bloodType;
    }

    public JFXCheckBox getDiabetes() {
        return diabetes;
    }

    public JFXCheckBox getDrinkingAlcohol() {
        return drinkingAlcohol;
    }

    public JFXCheckBox getHardDiseases() {
        return hardDiseases;
    }

    public JFXCheckBox getHeartDiseases() {
        return heartDiseases;
    }

    public JFXCheckBox getLiverDiseases() {
        return liverDiseases;
    }

    public JFXCheckBox getMentalIssues() {
        return mentalIssues;
    }

    public JFXCheckBox getSmoking() {
        return smoking;
    }
    //end of setters and getters

}
