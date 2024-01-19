module com.example.project286 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires com.jfoenix;
    requires MaterialFX;
    requires java.sql;
    requires ojdbc8;
    requires java.naming;
    requires jasperreports;
    requires mysql.connector.java;
    requires java.sql.rowset;


    opens com.example.project286 to javafx.fxml;
    exports com.example.project286;
}