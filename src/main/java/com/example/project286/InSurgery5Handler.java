package com.example.project286;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;


public class InSurgery5Handler {

        @FXML
        private JFXComboBox<String> organs;

        public JFXComboBox<String> getOrgans() {
                return organs;
        }

        public void setOrgans(JFXComboBox<String> organs) {
                this.organs = organs;
        }

        @FXML
        private AnchorPane pane5;

        public AnchorPane getPane5() {
                return pane5;
        }
}
