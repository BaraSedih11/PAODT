package com.example.project286;
import com.jfoenix.controls.JFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;


public class InSurgery6Handler {
        @FXML
        private AnchorPane pane6;

        public ComboBox<String> getRecipients() {
                return recipients;
        }

        public void setRecipients(ComboBox<String> recipients) {
                this.recipients = recipients;
        }

        @FXML
        ComboBox<String> recipients;

        public AnchorPane getPane6() {
                return pane6;
        }
}
