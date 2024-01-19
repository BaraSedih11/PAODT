package com.example.project286;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;


public class InDonorA3Handler implements Initializable {

    @FXML
    private Button btnHeart;

    @FXML
    private Button btnKidney;

    @FXML
    private Button btnLiver;

    @FXML
    private Button btnLungs;

    @FXML
    private Button btnRetina;

    @FXML
    private Label labHeart;

    @FXML
    private Label labKidney;

    @FXML
    private Label labLiver;

    @FXML
    private Label labLungs;

    @FXML
    private Label labRetina;

    @FXML
    private AnchorPane paneA3;

    @FXML
    private JFXTextField wFName;

    @FXML
    private JFXTextField wLName;

    @FXML
    private JFXTextField wPhone;

    @FXML
    private JFXTextField wSName;

    @FXML
    private JFXTextField wTName;

    //------------
    boolean t[] = new boolean[5];



    //setters

    public void setBtnHeart(Button btnHeart) {
        this.btnHeart = btnHeart;
    }

    public void setBtnKidney(Button btnKidney) {
        this.btnKidney = btnKidney;
    }

    public void setBtnLiver(Button btnLiver) {
        this.btnLiver = btnLiver;
    }

    public void setBtnLungs(Button btnLungs) {
        this.btnLungs = btnLungs;
    }

    public void setBtnRetina(Button btnRetina) {
        this.btnRetina = btnRetina;
    }

    public void setLabHeart(Label labHeart) {
        this.labHeart = labHeart;
    }

    public void setLabKidney(Label labKidney) {
        this.labKidney = labKidney;
    }

    public void setLabLiver(Label labLiver) {
        this.labLiver = labLiver;
    }

    public void setLabLungs(Label labLungs) {
        this.labLungs = labLungs;
    }

    public void setLabRetina(Label labRetina) {
        this.labRetina = labRetina;
    }

    public void setPaneA3(AnchorPane paneA3) {
        this.paneA3 = paneA3;
    }

    public void setwFName(JFXTextField wFName) {
        this.wFName = wFName;
    }

    public void setwLName(JFXTextField wLName) {
        this.wLName = wLName;
    }

    public void setwPhone(JFXTextField wPhone) {
        this.wPhone = wPhone;
    }

    public void setwSName(JFXTextField wSName) {
        this.wSName = wSName;
    }

    public void setwTName(JFXTextField wTName) {
        this.wTName = wTName;
    }
    //getters


    public Button getBtnHeart() {
        return btnHeart;
    }

    public Button getBtnKidney() {
        return btnKidney;
    }

    public Button getBtnLiver() {
        return btnLiver;
    }

    public Button getBtnLungs() {
        return btnLungs;
    }

    public Button getBtnRetina() {
        return btnRetina;
    }

    public Label getLabHeart() {
        return labHeart;
    }

    public Label getLabKidney() {
        return labKidney;
    }

    public Label getLabLiver() {
        return labLiver;
    }

    public Label getLabLungs() {
        return labLungs;
    }

    public Label getLabRetina() {
        return labRetina;
    }

    public AnchorPane getPaneA3() {
        return paneA3;
    }

    public JFXTextField getwFName() {
        return wFName;
    }

    public JFXTextField getwLName() {
        return wLName;
    }

    public JFXTextField getwPhone() {
        return wPhone;
    }

    public JFXTextField getwSName() {
        return wSName;
    }

    public JFXTextField getwTName() {
        return wTName;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(int i=0;i<5;i++) {
            t[i] = true;
        }
    }

    @FXML
    void pressed(ActionEvent event) {
        if (btnLungs == (Button) event.getSource() ) {
            if(  ! btnLungs.getStylesheets().get(0).equalsIgnoreCase("donor-recipientButtons-Red.css" ) ) {

                if(labLungs.getStylesheets().isEmpty() || labLungs.getStylesheets().get(0).equalsIgnoreCase("labelred.css")) {
                    if (!labLungs.getStylesheets().isEmpty())
                        labLungs.getStylesheets().remove(0);
                    labLungs.getStylesheets().add("labelgreen.css");

                }

                else if( labLungs.getStylesheets().get(0).equalsIgnoreCase("labelgreen.css")){
                    if(! labLungs.getStylesheets().isEmpty())
                        labLungs.getStylesheets().remove(0);
                    labLungs.getStylesheets().add("labelred.css");
                }
            }

        }
        else if (btnHeart == (Button) event.getSource() ) {
            if(  ! btnHeart.getStylesheets().get(0).equalsIgnoreCase("donor-recipientButtons-Red.css" ) ) {
                if(labHeart.getStylesheets().isEmpty() || labHeart.getStylesheets().get(0).equalsIgnoreCase("labelred.css")) {
                    if (!labHeart.getStylesheets().isEmpty())
                        labHeart.getStylesheets().remove(0);
                    labHeart.getStylesheets().add("labelgreen.css");

                }

                else if(labHeart.getStylesheets().get(0).equalsIgnoreCase("labelgreen.css")){
                    if(! labHeart.getStylesheets().isEmpty())
                        labHeart.getStylesheets().remove(0);
                    labHeart.getStylesheets().add("labelred.css");
                }
            }

        }

        else if(btnKidney == (Button) event.getSource()) {
            if(  ! btnKidney.getStylesheets().get(0).equalsIgnoreCase("donor-recipientButtons-Red.css" ) ) {

                if(labKidney.getStylesheets().isEmpty() || labKidney.getStylesheets().get(0).equalsIgnoreCase("labelred.css")) {
                    if (!labKidney.getStylesheets().isEmpty())
                        labKidney.getStylesheets().remove(0);
                    labKidney.getStylesheets().add("labelgreen.css");

                }

                else if(labKidney.getStylesheets().get(0).equalsIgnoreCase("labelgreen.css")){
                    if(! labKidney.getStylesheets().isEmpty())
                        labKidney.getStylesheets().remove(0);
                    labKidney.getStylesheets().add("labelred.css");
                }

            }


        }

        else if(btnRetina == (Button) event.getSource() ) {
            if(  ! btnRetina.getStylesheets().get(0).equalsIgnoreCase("donor-recipientButtons-Red.css" ) ) {

                if(labRetina.getStylesheets().isEmpty() || labRetina.getStylesheets().get(0).equalsIgnoreCase("labelred.css")) {
                    if (!labRetina.getStylesheets().isEmpty())
                        labRetina.getStylesheets().remove(0);
                    labRetina.getStylesheets().add("labelgreen.css");

                }

                else if(labRetina.getStylesheets().get(0).equalsIgnoreCase("labelgreen.css")){
                    if(! labRetina.getStylesheets().isEmpty())
                        labRetina.getStylesheets().remove(0);
                    labRetina.getStylesheets().add("labelred.css");
                }

            }


            }
        else if(btnLiver == (Button) event.getSource() ) {

            if(  ! btnLiver.getStylesheets().get(0).equalsIgnoreCase("donor-recipientButtons-Red.css" ) ) {

                if(labLiver.getStylesheets().isEmpty() || labLiver.getStylesheets().get(0).equalsIgnoreCase("labelred.css")) {
                    if (!labLiver.getStylesheets().isEmpty())
                        labLiver.getStylesheets().remove(0);
                    labLiver.getStylesheets().add("labelgreen.css");

                }

                else if(labLiver.getStylesheets().get(0).equalsIgnoreCase("labelgreen.css")){
                    if(! labLiver.getStylesheets().isEmpty())
                        labLiver.getStylesheets().remove(0);
                    labLiver.getStylesheets().add("labelred.css");
                }




            }

        }




        }










    }


