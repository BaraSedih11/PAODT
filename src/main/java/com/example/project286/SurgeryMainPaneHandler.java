package com.example.project286;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class SurgeryMainPaneHandler implements Initializable {

        @FXML
        private MFXButton cancel;

        @FXML
        private MFXButton newSurgery;

        @FXML
        private MFXButton save;
        @FXML
        private AnchorPane inSurgeryPane;
        @FXML
        private AnchorPane main;


    public AnchorPane getMain() {
        return main;
    }

    AnchorPane paneA , paneB;
    SurgeryPaneHandler ser;
    SurgeryPane2Handler ser2;

    TableView <SurgeryData> surgeryTable;
    public TableView<SurgeryData> getSurgeryTable() {
        return surgeryTable;
    }


    @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
             cancel.setVisible(false);
             save.setVisible(false);

        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Surgery-pane.fxml"));
        try {
            Parent root1 = loader1.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SurgeryPaneHandler surgeryPaneHandler = loader1.getController();
        paneA = surgeryPaneHandler.getPaneA();
        ser = surgeryPaneHandler;

        //222
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("Surgery-pane2.fxml"));
        try {
            Parent root2 = loader2.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SurgeryPane2Handler surgeryPane2Handler = loader2.getController();
        paneB = surgeryPane2Handler.getPaneB();
        surgeryTable = surgeryPane2Handler.getSurgeriesTable();

        inSurgeryPane.getChildren().add(paneB);
        ser2 = surgeryPane2Handler;

    }


        @FXML
        void cancelPressed(ActionEvent event) {
            if(! inSurgeryPane.getChildren().isEmpty()){
                inSurgeryPane.getChildren().remove(0);
            }
            inSurgeryPane.getChildren().add(paneB);
            newSurgery.setVisible(true);
            save.setVisible(false);
            cancel.setVisible(false);
        }

        @FXML
        void newPressed(ActionEvent event) {
        if(! inSurgeryPane.getChildren().isEmpty()){
            inSurgeryPane.getChildren().remove(0);
        }
        inSurgeryPane.getChildren().add(paneA);

        newSurgery.setVisible(false);
        save.setVisible(true);
        cancel.setVisible(true);

        }

        @FXML
        void savePresssed(ActionEvent event) {
            //adding a new employee.....

            if(ser.getTxt1().getText() == null || ser.getTxt4().getText() == null ||ser.getTxt6().getText() == null || ser.getTxt5().getText() == null ||ser.getTxt3() == null ||ser.getTxt2().getText() == null ) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please fill all the fields");
                alert.show();

                return;
            }
            else if (ser.getTxt1().getText().isEmpty() || ser.getTxt4().getText().isEmpty()||ser.getTxt6().getText().isEmpty()|| ser.getTxt5().getText().isEmpty()||ser.getTxt3().getText().isEmpty() ||ser.getTxt2().getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please fill all the fields");
                alert.show();
                alert.show();
                return;
            }



            String hospital = "'" + ser.getTxt1().getText() + "'";
            String donor = ser.getTxt4().getText();
            String recipient = ser.getTxt6().getText();
            String organ = "'" +ser.getTxt5().getText()+ "'";
            String date = "'" +ser.getTxt3().getText()+ "'";
            String doctor = "'" +ser.getTxt2().getText()+ "'";


            ResultSet rst;
                try    {
                    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
                    con.setAutoCommit(false);
                    Statement st = con.createStatement();
                    Statement st2 = con.createStatement();

                    rst = st2.executeQuery("select * from surgery where donor_id = " + donor + " and recipient_id = " + recipient);
                    if(rst.next()) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("This surgery has alreay been scheduled");
                        alert.show();


                        con.commit();
                        con.close();
                        return;
                    }






                    st.executeUpdate("insert into surgery (surgery_number,organ,surgery_date,result,hospital_name,doctor_name,donor_id,recipient_id) values(surgery_counter.nextval , " + organ + ", to_date(" + date +",'yyyy-mm-dd') , 'Ongoing', "+ hospital + ", " + doctor + ", " + donor + ", " + recipient +")" );


                    con.commit();
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            //clearing the fiedls
            ser.getTxt1().setText(""); ser.getTxt2().setText(""); ser.getTxt3().setText(""); ser.getTxt4().setText(""); ser.getTxt5().setText(""); ser.getTxt6().setText("");

            //switching scenes
            if(! inSurgeryPane.getChildren().isEmpty()){
                inSurgeryPane.getChildren().remove(0);
            }
            inSurgeryPane.getChildren().add(paneB);

            newSurgery.setVisible(true);
            save.setVisible(false);
            cancel.setVisible(false);



        }



}
