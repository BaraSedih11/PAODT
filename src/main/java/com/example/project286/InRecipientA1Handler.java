package com.example.project286;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.HttpCookie;
import java.net.URL;
import java.util.ResourceBundle;

public class InRecipientA1Handler {
    @FXML
    private DatePicker birthdate;
    @FXML
    private JFXTextField city;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField fName;
    @FXML
    private JFXRadioButton female;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField lName;
    @FXML
    private JFXRadioButton male;
    @FXML
    private AnchorPane paneA1;
    @FXML
    private JFXTextField phone;
    @FXML
    private JFXTextField sName;
    @FXML
    private JFXTextField street;
    @FXML
    private JFXTextField tName;
    @FXML
    private ToggleGroup gender;


    private AnchorPane bigPane;
    private AnchorPane paneNext;
    private AnchorPane panePrev;

    public AnchorPane getPaneA1() {
        return paneA1;
    }

    //setters

    public void setBirthdate(DatePicker birthdate) {
        this.birthdate = birthdate;
    }

    public void setCity(JFXTextField city) {
        this.city = city;
    }

    public void setEmail(JFXTextField email) {
        this.email = email;
    }

    public void setfName(JFXTextField fName) {
        this.fName = fName;
    }

    public void setFemale(JFXRadioButton female) {
        this.female = female;
    }

    public void setId(JFXTextField id) {
        this.id = id;
    }

    public void setlName(JFXTextField lName) {
        this.lName = lName;
    }

    public void setMale(JFXRadioButton male) {
        this.male = male;
    }

    public void setPaneA1(AnchorPane paneA1) {
        this.paneA1 = paneA1;
    }

    public void setPhone(JFXTextField phone) {
        this.phone = phone;
    }

    public void setsName(JFXTextField sName) {
        this.sName = sName;
    }

    public void setStreet(JFXTextField street) {
        this.street = street;
    }

    public void settName(JFXTextField tName) {
        this.tName = tName;
    }

    public void setGender(ToggleGroup gender) {
        this.gender = gender;
    }

    public void setBigPane(AnchorPane bigPane) {
        this.bigPane = bigPane;
    }

    public void setPaneNext(AnchorPane paneNext) {
        this.paneNext = paneNext;
    }

    public void setPanePrev(AnchorPane panePrev) {
        this.panePrev = panePrev;
    }



    //getters
    public DatePicker getBirthdate() {
        return birthdate;
    }

    public JFXTextField getCity() {
        return city;
    }

    public JFXTextField getEmail() {
        return email;
    }

    public JFXTextField getfName() {
        return fName;
    }

    public JFXRadioButton getFemale() {
        return female;
    }

    public JFXTextField getId() {
        return id;
    }

    public JFXTextField getlName() {
        return lName;
    }

    public JFXRadioButton getMale() {
        return male;
    }

    public JFXTextField getPhone() {
        return phone;
    }

    public JFXTextField getsName() {
        return sName;
    }

    public JFXTextField getStreet() {
        return street;
    }

    public JFXTextField gettName() {
        return tName;
    }

    public ToggleGroup getGender() {
        return gender;
    }

    public AnchorPane getBigPane() {
        return bigPane;
    }

    public AnchorPane getPaneNext() {
        return paneNext;
    }

    public AnchorPane getPanePrev() {
        return panePrev;
    }



    //end of setters and getters


}
