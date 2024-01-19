package com.example.project286;

import com.jfoenix.controls.JFXTextArea;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class InSurgery4Handler {
        public ComboBox<String> getDonors() {
                return donors;
        }

        public void setDonors(ComboBox<String> donors) {
                this.donors = donors;
        }

        @FXML
        ComboBox<String> donors;

        @FXML
        private AnchorPane pane4;

        public AnchorPane getPane4() {
                return pane4;
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
