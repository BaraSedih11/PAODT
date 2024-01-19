package com.example.project286;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.print.Doc;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class InHospitalDHandler {
    TableView<Doctor> doctorTable;
    @FXML
    private AnchorPane paneC;

    public JFXTextField gethName() {
        return hName;
    }

    public void sethName(JFXTextField hName) {
        this.hName = hName;
    }

    @FXML
    private JFXTextField hName;
    @FXML
    private JFXButton cancelDoc;

    @FXML
    private JFXTextField fName;

    @FXML
    private JFXTextField lName;

    @FXML
    private JFXTextField sName;

    @FXML
    private JFXButton saveDoc;

    @FXML
    private JFXTextField tName;
    Stage stage;
    public AnchorPane getPaneC() {
        return paneC;
    }

    @FXML
    void cancel2(ActionEvent event) throws IOException {
        stage = (Stage) paneC.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save2(ActionEvent event) {
        boolean hNameValid = this.hName.getText() != null;
        boolean nameValid = (this.fName.getText() != "" && this.sName.getText() != null && this.tName.getText() != null && this.lName.getText() != null);

        boolean result = hNameValid && nameValid;
        if (!result){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid input, please check the inserted data and try again");
            alert.setTitle("Invalid input");
            alert.show();
            return;
        }

        //end of validation .....
        String hname = "'" + this.hName.getText() + "'";
        String fname = "'" + this.fName.getText() + "'";
        String sname = "'" + this.sName.getText() + "'";
        String lname = "'" + this.lName.getText() + "'";
        String tname = "'" + this.tName.getText() + "'";

        String hname2 =  this.hName.getText() ;
        String fname2 =  this.fName.getText() ;
        String sname2 =  this.sName.getText() ;
        String lname2 = this.lName.getText() ;
        String tname2 =  this.tName.getText() ;


        try{
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "aydigg", "123456");
            con.setAutoCommit(false);
            Statement st = con.createStatement();
            Statement f = con.createStatement();

            //checking the doctor full name before adding
            ResultSet s = f.executeQuery("select first_name, second_name, third_name, last_name from Doctor where first_name = " + fname + " and " + "second_name =" + sname + " and " + "third_name =" + tname + " and " + "last_name =" + lname);

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

            //check if the hospital exist
            ResultSet s1 = f.executeQuery("select hospital_name from hospital where hospital_name =" + hname);
            i = 0;
            while (s1.next()){i++;}
            if (i != 1){
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Insertion error");
                alert1.setHeaderText("Hospital Name does not exists");
                alert1.setContentText("The inserted hospital name does not exists, please check it and try again");
                alert1.show();
                return;
            }

            //inserting the data of the doctor table
            st.executeUpdate("insert into doctor (doctor_id,hospital_name, first_name, second_name, third_name, last_name) values (doctor_counter.nextval ," + hname + "," + fname + "," + sname + "," + tname + "," + lname + ")" );

            con.commit();
            con.close();


        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        //------------------------------
        String id = "";
        ObservableList<Doctor> doctors = FXCollections.observableArrayList();
        ResultSet r;
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "aydigg", "123456");
            con.setAutoCommit(false);
            Statement st = con.createStatement();


            r = st.executeQuery("select * from doctor,hospital where valid = 'yes' and doctor.hospital_name = hospital.hospital_name");
            while(r.next()) {
                Doctor doctor = new Doctor();
                doctor.setDoctor_id(Integer.toString(r.getInt("doctor_id")));
                String fname3 = r.getString("first_name");
                String sname3 = r.getString("second_name");
                String tname3 = r.getString("third_name");
                String lname3 = r.getString("last_name");
                doctor.setName(fname3 + " " + sname3 + " " + tname3 + " " + lname3);

                doctor.setHosp(r.getString("hospital_name"));

                doctors.add(doctor);
            }

            con.commit();
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        //updating table
        doctorTable.setItems(doctors);
        doctorTable.refresh();

        //closing stage and clearing fields..
        stage = (Stage) paneC.getScene().getWindow();
        stage.close();

        hName.setText(""); fName.setText(""); sName.setText(""); tName.setText(""); lName.setText("");
    }

}
