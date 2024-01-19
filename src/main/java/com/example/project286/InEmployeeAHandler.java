package com.example.project286;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class InEmployeeAHandler implements Initializable {
    @FXML
    private ToggleGroup G;
    @FXML
    private DatePicker birthdate;
    @FXML
    private JFXComboBox<String> bloodtype;
    @FXML
    private JFXTextField city;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXRadioButton evening;

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
    private JFXRadioButton morning;

    @FXML
    private AnchorPane paneA;

    @FXML
    private JFXTextField password;

    @FXML
    private JFXTextField phone;

    @FXML
    private ToggleGroup rank;

    @FXML
    private JFXTextField sName;

    @FXML
    private JFXTextField salary;

    @FXML
    private DatePicker startdate;

    @FXML
    private JFXTextField street;

    @FXML
    private JFXRadioButton supervisee;

    @FXML
    private JFXRadioButton superviser;

    @FXML
    private JFXTextField tName;

    @FXML
    private JFXTextField username;

    @FXML
    private ToggleGroup wt;


    public ToggleGroup getG() {
        return G;
    }

    public void setG(ToggleGroup g) {
        G = g;
    }

    public DatePicker getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(DatePicker birthdate) {
        this.birthdate = birthdate;
    }

    public JFXComboBox<String> getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(JFXComboBox<String> bloodtype) {
        this.bloodtype = bloodtype;
    }

    public JFXTextField getCity() {
        return city;
    }

    public void setCity(JFXTextField city) {
        this.city = city;
    }

    public JFXTextField getEmail() {
        return email;
    }

    public void setEmail(JFXTextField email) {
        this.email = email;
    }

    public JFXRadioButton getEvening() {
        return evening;
    }

    public void setEvening(JFXRadioButton evening) {
        this.evening = evening;
    }

    public JFXTextField getfName() {
        return fName;
    }

    public void setfName(JFXTextField fName) {
        this.fName = fName;
    }

    public JFXRadioButton getFemale() {
        return female;
    }

    public void setFemale(JFXRadioButton female) {
        this.female = female;
    }

    public JFXTextField getId() {
        return id;
    }

    public void setId(JFXTextField id) {
        this.id = id;
    }

    public JFXTextField getlName() {
        return lName;
    }

    public void setlName(JFXTextField lName) {
        this.lName = lName;
    }

    public JFXRadioButton getMale() {
        return male;
    }

    public void setMale(JFXRadioButton male) {
        this.male = male;
    }

    public JFXRadioButton getMorning() {
        return morning;
    }

    public void setMorning(JFXRadioButton morning) {
        this.morning = morning;
    }

    public AnchorPane getPaneA() {
        return paneA;
    }

    public void setPaneA(AnchorPane paneA) {
        this.paneA = paneA;
    }

    public JFXTextField getPassword() {
        return password;
    }

    public void setPassword(JFXTextField password) {
        this.password = password;
    }

    public JFXTextField getPhone() {
        return phone;
    }

    public void setPhone(JFXTextField phone) {
        this.phone = phone;
    }

    public ToggleGroup getRank() {
        return rank;
    }

    public void setRank(ToggleGroup rank) {
        this.rank = rank;
    }

    public JFXTextField getsName() {
        return sName;
    }

    public void setsName(JFXTextField sName) {
        this.sName = sName;
    }

    public JFXTextField getSalary() {
        return salary;
    }

    public void setSalary(JFXTextField salary) {
        this.salary = salary;
    }

    public DatePicker getStartdate() {
        return startdate;
    }

    public void setStartdate(DatePicker startdate) {
        this.startdate = startdate;
    }

    public JFXTextField getStreet() {
        return street;
    }

    public void setStreet(JFXTextField street) {
        this.street = street;
    }

    public JFXRadioButton getSupervisee() {
        return supervisee;
    }

    public void setSupervisee(JFXRadioButton supervisee) {
        this.supervisee = supervisee;
    }

    public JFXRadioButton getSuperviser() {
        return superviser;
    }

    public void setSuperviser(JFXRadioButton superviser) {
        this.superviser = superviser;
    }

    public JFXTextField gettName() {
        return tName;
    }

    public void settName(JFXTextField tName) {
        this.tName = tName;
    }

    public JFXTextField getUsername() {
        return username;
    }

    public void setUsername(JFXTextField username) {
        this.username = username;
    }

    public ToggleGroup getWt() {
        return wt;
    }

    public void setWt(ToggleGroup wt) {
        this.wt = wt;
    }
    //    public JFXRadioButton getGender(){
//        if (male.isSelected()){ return male;}
//        else  { return female;}
//    }

//    public JFXRadioButton getRank(){
//        if (superviser.isSelected()){ return superviser; }
//        else { return supervisee; }
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String types[] = {"A+","A-","B+","B-","AB+","AB-","O+","O-"};
        bloodtype.getItems().addAll(types);
    }
}
