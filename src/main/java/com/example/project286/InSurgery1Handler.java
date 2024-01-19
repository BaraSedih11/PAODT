package com.example.project286;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class InSurgery1Handler implements Initializable {
        @FXML
        private JFXComboBox<String> hospital;

        @FXML
        private AnchorPane pane1;

    public AnchorPane getPane1() {
        return pane1;
    }

    public JFXComboBox<String> getHospital() {
        return hospital;
    }

    public void setHospital(JFXComboBox<String> hospital) {
        this.hospital = hospital;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void changed(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Surgery-pane.fxml"));
        try {
            Parent root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SurgeryPaneHandler surgeryPaneHandler = loader.getController();


        surgeryPaneHandler.getTxt1().setText("sssssssssss");


    }


}
