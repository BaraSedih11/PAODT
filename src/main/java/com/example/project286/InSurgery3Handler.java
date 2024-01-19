package com.example.project286;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;

public class InSurgery3Handler {
    public DatePicker getDate() {
        return date;
    }

    @FXML
        private DatePicker date;

        @FXML
        private AnchorPane pane3;

    public AnchorPane getPane3() {
        return pane3;
    }

}
