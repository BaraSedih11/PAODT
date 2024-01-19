package com.example.project286;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;


public class InRecipientA2Handler implements Initializable {

    @FXML
    private JFXComboBox<String> bloodType;
    @FXML
    private JFXRadioButton hPriority;
    @FXML
    private JFXRadioButton lPriority;
    @FXML
    private JFXRadioButton mPriority;
    @FXML
    private DatePicker RegDate;
    @FXML
    private JFXCheckBox hardDiseases;

    @FXML
    private JFXCheckBox mentalIssues;

    @FXML
    private AnchorPane paneA2;

    public AnchorPane getPaneA2() {
        return paneA2;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String types[] = {"A+","A-","B+","B-","AB+","AB-","O+","O-"};
        bloodType.getItems().addAll(types);
    }
    //setters


    public void setBloodType(JFXComboBox<String> bloodType) {
        this.bloodType = bloodType;
    }

    public void setHardDiseases(JFXCheckBox hardDiseases) {
        this.hardDiseases = hardDiseases;
    }

    public void setMentalIssues(JFXCheckBox mentalIssues) {
        this.mentalIssues = mentalIssues;
    }

    public void setPaneA2(AnchorPane paneA2) {
        this.paneA2 = paneA2;
    }

    public void setRegDate(DatePicker regDate) {
        RegDate = regDate;
    }

    public void sethPriority(JFXRadioButton hPriority) {
        this.hPriority = hPriority;
    }

    public void setlPriority(JFXRadioButton lPriority) {
        this.lPriority = lPriority;
    }

    public void setmPriority(JFXRadioButton mPriority) {
        this.mPriority = mPriority;
    }

    //getters
    public JFXComboBox<String> getBloodType() {
        return bloodType;
    }
    public JFXCheckBox getHardDiseases() {
        return hardDiseases;
    }
    public JFXCheckBox getMentalIssues() {
        return mentalIssues;
    }
    public DatePicker getRegDate() {
        return RegDate;
    }

    public JFXRadioButton gethPriority() {
        return hPriority;
    }

    public JFXRadioButton getlPriority() {
        return lPriority;
    }

    public JFXRadioButton getmPriority() {
        return mPriority;
    }

//end of setters and getters


    //initialization
    public String getPriority(){
        if (hPriority.isSelected()){
            return "High";
        }
        else if (mPriority.isSelected()){
            return "Medium";
        }
        else if (lPriority.isSelected()){
            return "Low";
        }
        else {
            return "";
        }
    }


}
