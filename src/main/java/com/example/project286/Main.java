package com.example.project286;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.sql.*;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignIn-frame.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Image image = new Image("pal.png");
        stage.getIcons().add(image);
        stage.setResizable(false);
        stage.show();



    }






    public static void main(String[] args) {
        launch();
    }
}