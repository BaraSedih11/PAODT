package com.example.project286;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.sql.*;

public class InHospitalHHandler {
    TableView<Hospital> hospitalTable;
    @FXML
    private AnchorPane paneB;

    @FXML
    private JFXButton cancelHos;

    public AnchorPane getPaneB() {
        return paneB;
    }

    @FXML
    private TextField hCity;

    @FXML
    private TextField hName;

    @FXML
    private TextField hPhone;

    @FXML
    private TextField hStreet;

    @FXML
    private JFXButton saveHos;
    Stage stage;

    @FXML
    void cancel1(ActionEvent event) throws IOException {
        stage = (Stage) paneB.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save1(ActionEvent event) {
        boolean nameValid = this.hName.getText() != "";
        boolean cityValid = this.hCity.getText() != "";
        boolean streetValid = this.hStreet.getText() != "";

        boolean phoneValid;
        char c2 [] = this.hPhone.getText().toCharArray();
        if( this.hPhone.getText().length() == 10 ) {
            for (int i = 0; i < c2.length; i++) {
                if (!Character.isDigit(c2[i])) {
                    phoneValid= false;
                    break;
                }


            }
            phoneValid = true;
        }
        else{
            phoneValid = false;
        }

        boolean result = nameValid && phoneValid && cityValid && streetValid;
        if (!result){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid input, please check the inserted data and try again");
            alert.setTitle("Invalid input");
            alert.show();
            return;
        }

        //end of validation .....
        String name = "'" + this.hName.getText() + "'";
        String phone = "'" + this.hPhone.getText() + "'";
        String city = "'" + this.hCity.getText() + "'";
        String street = "'" + this.hStreet.getText() + "'";

        String name2 = this.hName.getText();
        String city2 = this.hCity.getText();
        String street2 = this.hStreet.getText();
        String phone2 = this.hPhone.getText();

        try{
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "aydigg", "123456");
            con.setAutoCommit(false);
            Statement st = con.createStatement();
            Statement f = con.createStatement();

            //checking the hospital name before adding
            ResultSet s = f.executeQuery("select HOSPITAL_NAME from hospital where valid = 'yes' and  HOSPITAL_NAME = " + name);
            int i = 0;
            while (s.next()){ i++;}
            if (i == 1){
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Insertion error");
                alert1.setHeaderText("ID duplication");
                alert1.setContentText("The inserted id already exists, please check it and try again");
                alert1.show();
                return;
            }
            //checking the phone before adding
            ResultSet s3 = f.executeQuery("select phone_number from hospital where valid = 'yes' and phone_number = " + phone);
            i = 0 ;
            while(s3.next()) { i++; }
            if(i == 1){
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Insertion error");
                alert1.setHeaderText("ID duplication");
                alert1.setContentText("The inserted id already exists, please check it and try again");
                alert1.show();
                return;
            }
            //checking if the hospital is valid = no ..
            s3  = f.executeQuery("select * from hospital where valid = 'no'  and hospital_name = " + name);
            if(s3.next()) {

             st.executeUpdate("update hospital set valid = 'yes' , hospital_name = " + name +", street = " + street + ", city = " + city +" , phone_number = " + phone);

            }

            else {
                //inserting the data of the hospital table
                st.executeUpdate("insert into hospital (hospital_name, street, city, phone_number,valid) values (" + name + "," + street + "," + city + "," + phone + ", 'yes')");
            }

            con.commit();
            con.close();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        Hospital hospital = new Hospital();
        hospital.setAddress(city2+","+street2);
        hospital.setName(name2);
        hospital.setPhone(phone2);


        ObservableList<Hospital> dd = hospitalTable.getItems();
        dd.add(hospital);
        hospitalTable.setItems(dd);
        hospitalTable.refresh();


        //end


        hName.setText(""); hPhone.setText(""); hCity.setText(""); hStreet.setText("");
        stage = (Stage) paneB.getScene().getWindow();
        stage.close();
    }

}
