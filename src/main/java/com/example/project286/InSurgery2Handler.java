package com.example.project286;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class InSurgery2Handler {
        @FXML
        private JFXComboBox<String> doctors;

        @FXML
        private AnchorPane pane3;

    public AnchorPane getPane3() {
        return pane3;
    }

    public JFXComboBox<String> getDoctors() {
        return doctors;
    }

    public void setDoctors(JFXComboBox<String> doctors) {
        this.doctors = doctors;
    }
}
