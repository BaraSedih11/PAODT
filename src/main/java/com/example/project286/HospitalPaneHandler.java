package com.example.project286;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.print.Doc;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class HospitalPaneHandler implements Initializable{
    ObservableList<Hospital> hospitals;
    ObservableList<Doctor> doctors;
    @FXML
    private JFXButton doctor;

    @FXML
    private AnchorPane hosPane;

    @FXML
    private JFXButton hospital;

    public TableView<Hospital> getHospitals_table() {
        return hospitals_table;
    }

    public void setHospitals_table(TableView<Hospital> hospitals_table) {
        this.hospitals_table = hospitals_table;
    }

    @FXML
    public TableView<Hospital> hospitals_table;

    public TableView<Doctor> getDoctors_table() {
        return doctors_table;
    }

    public void setDoctors_table(TableView<Doctor> doctors_table) {
        this.doctors_table = doctors_table;
    }

    @FXML
    public TableView<Doctor> doctors_table;

    private Hospital hos;
    private Doctor doc;

    @FXML
    private TableColumn<Hospital, String> hAddress;

    @FXML
    private TableColumn<Hospital, String> hName;
    @FXML
    private TableColumn<Doctor, String> ClmHosp;
    @FXML
    private JFXButton btnDeleteDoc;

    @FXML
    private JFXButton btnDeleteHos;

    @FXML
    private TableColumn<Hospital, String> hPhone;

    @FXML
    private TableColumn<Doctor, String> dName;

    public AnchorPane getHosPane() {
        return hosPane;
    }

    @FXML
    private AnchorPane paneH , paneD;
    InHospitalHHandler sceneH;
    InHospitalDHandler sceneD;


    @FXML
    private JFXButton search;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private MFXLegacyComboBox<String> using;

    @FXML
    void commitSearch(ActionEvent event) {

        if (using.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Choose something to search with");
            alert.show();
            return;
        }

        if (txtSearch.getText().isEmpty()){
            hospitals_table.setItems(hospitals);
            hospitals_table.refresh();
            doctors_table.setItems(doctors);
            doctors_table.refresh();
            return;
        }
        if (using.getValue().equalsIgnoreCase("hospital name")){
            ObservableList<Hospital> h1 = FXCollections.observableArrayList();
            for (int i = 0 ; i < hospitals.size() ; i++){
                if (hospitals.get(i).getName().equalsIgnoreCase(txtSearch.getText())){
                    h1.add(hospitals.get(i));
                }
            }
            hospitals_table.setItems(h1);
            hospitals_table.refresh();

            ObservableList<Doctor> d1 = FXCollections.observableArrayList();
            for (int i = 0 ; i < doctors.size() ; i++){
                if (doctors.get(i).getHosp().equalsIgnoreCase(txtSearch.getText())){
                    d1.add(doctors.get(i));
                }
            }
            doctors_table.setItems(doctors);
            doctors_table.refresh();
        }
        else if (using.getValue().equalsIgnoreCase("hospital address")){
            ObservableList<Hospital> h2 = FXCollections.observableArrayList();
            for (int i = 0 ; i < hospitals.size() ; i++){
                if (hospitals.get(i).getAddress().equalsIgnoreCase(txtSearch.getText())){
                    h2.add(hospitals.get(i));
                }
            }
            hospitals_table.setItems(h2);
            hospitals_table.refresh();
        }
        else if (using.getValue().equalsIgnoreCase("hospital phone")){
            ObservableList<Hospital> h3 = FXCollections.observableArrayList();
            for (int i = 0 ; i < hospitals.size() ; i++){
                if (hospitals.get(i).getPhone().equalsIgnoreCase(txtSearch.getText())){
                    h3.add(hospitals.get(i));
                }
            }
            hospitals_table.setItems(h3);
            hospitals_table.refresh();
        }
        else if (using.getValue().equalsIgnoreCase("doctor's name")){
            ObservableList<Doctor> d2 = FXCollections.observableArrayList();
            for (int i = 0 ; i < doctors.size() ; i++){
                if (doctors.get(i).getName().equalsIgnoreCase(txtSearch.getText())){
                    d2.add(doctors.get(i));
                }
            }
            doctors_table.setItems(d2);
            doctors_table.refresh();
        }





    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        //values
        String[] values = {"hospital name", "Hospital address", "Hospital phone", "doctor's name"};
        using.getItems().addAll(values);
        using.getEditor().setStyle("-fx-text-fill : white");


        hospitals_table.setEditable(true);
        doctors_table.setEditable(true);

        hName.setCellValueFactory(new PropertyValueFactory<Hospital, String>("name"));

        hAddress.setCellValueFactory(new PropertyValueFactory<Hospital, String>("address"));
        hAddress.setCellFactory(TextFieldTableCell.forTableColumn());
        hAddress.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Hospital, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Hospital, String> hospitalStringCellEditEvent) {
                Hospital hospital1 = hospitalStringCellEditEvent.getRowValue();
                String []address = hospitalStringCellEditEvent.getNewValue().split(",");
                if(address.length != 2) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("invalid address, the acceptable format is: (city, street )");
                    alert.show();
                    hospitals_table.refresh();
                    return;
                }
                address[1] = address[1].trim();

                //editing on the database
                try {
                    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
                    con.setAutoCommit(false);
                    Statement st = con.createStatement();
                    st.executeUpdate("update hospital set " + "city =" + "'" + address[0] + "'" + " where hospital_name =" + "'" + hospital1.getName() + "'");
                    st.executeUpdate("update  hospital set " + "street =" + "'" + address[1] + "'" + " where hospital_name =" + "'" + hospital1.getName() + "'");
                    con.close();

                }catch (SQLException e){
                    throw new RuntimeException(e);
                }
                hospital1.setAddress(hospitalStringCellEditEvent.getNewValue());
                hospitals_table.refresh();
            }
        });

        hPhone.setCellValueFactory(new PropertyValueFactory<Hospital, String>("phone"));
        hPhone.setCellFactory(TextFieldTableCell.forTableColumn());
        hPhone.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Hospital, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Hospital, String> hospitalStringCellEditEvent) {

                Hospital hospital1 = hospitalStringCellEditEvent.getRowValue();
                String newValue = hospitalStringCellEditEvent.getNewValue();

                //check phone validation

                boolean phoneValid = true;
                char c2 [] = hospital1.getPhone().toCharArray();

                    for (int i = 0; i < c2.length; i++) {
                        if (!Character.isDigit(c2[i])) {
                            phoneValid= false;
                            break;
                        }

                }




                if (!phoneValid){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Invalid input, please check the inserted data and try again");
                    alert.setTitle("Invalid input");
                    alert.show();
                    hospitals_table.refresh();
                    return;
                }



                //editing on the database
                try {
                    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
                    con.setAutoCommit(false);
                    Statement st = con.createStatement();
                    Statement f = con.createStatement();

                    //check if the phone exists
                    boolean phoneExist = false;
                    ResultSet s3 = f.executeQuery("select phone_number from hospital where valid = 'yes' and phone_number = " + "'" + newValue + "'");
                    int i = 0 ;
                    while(s3.next()) { i++; }
                    if(i > 0){
                        phoneExist = true;
                    }

                    if (phoneExist){
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("Insertion error");
                        alert2.setHeaderText("PHONE duplication");
                        alert2.setContentText("The inserted phone already exists, please check it and try again");
                        alert2.show();

                        hospitals_table.refresh();
                        con.close();
                        return;
                    }

                    if(newValue.isEmpty()){
                        Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                        alert3.setTitle("Insertion error");
                        alert3.setHeaderText("PHONE duplication");
                        alert3.setContentText("invalid value");
                        alert3.show();

                        hospitals_table.refresh();
                        return;
                    }


                    st.executeUpdate("update hospital set " + "phone_number =" + "'" + newValue + "'" + " where hospital_name =" + "'" + hospital1.getName() + "'");
                    con.commit();
                    con.close();

                }catch (SQLException e){
                    throw new RuntimeException(e);
                }

                hospital1.setPhone(hospitalStringCellEditEvent.getNewValue());
                hospitals_table.refresh();
            }
        });

        //doctors columns ---------------------------------------------------------------------------------------------------

        dName.setCellValueFactory(new PropertyValueFactory<Doctor, String>("name"));
        dName.setCellFactory(TextFieldTableCell.forTableColumn());
        dName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Doctor, String>>() {
                                  @Override
                                  public void handle(TableColumn.CellEditEvent<Doctor, String> event) {
                                      Doctor doctor = event.getRowValue();

                                      if(event.getNewValue().isEmpty()) {
                                          Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                                          alert2.setTitle("Insertion error");
                                          alert2.setHeaderText("Invalid Name");
                                          alert2.setContentText("The inserted doctor name is invalid, please check it and try again");
                                          alert2.show();


                                          doctors_table.refresh();
                                          return;
                                      }

                                      String tmp[] = event.getNewValue().split(" ");

                                      if(tmp.length != 4) {

                                          Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                                          alert2.setTitle("Insertion error");
                                          alert2.setHeaderText("Invalid Name");
                                          alert2.setContentText("The inserted doctor name is invalid, please check it and try again");
                                          alert2.show();

                                          doctors_table.refresh();
                                          return;

                                      }

                                      try {
                                          DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                                          Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
                                          Statement st = con.createStatement();
                                          Statement f = con.createStatement();



                                          st.executeUpdate("update doctor set " + "first_name =" + "'"+ tmp[0] + "' , second_name = '" + tmp[1] + "', third_name = '" + tmp[2] + "' , last_name = '" + tmp[3] + "' where doctor_id = " + doctor.getDoctor_id());
                                          con.close();

                                      }catch (SQLException e){
                                          throw new RuntimeException(e);
                                      }

                                      doctor.setName(event.getNewValue());
                                      doctors_table.refresh();



                                  }
                              } );


        ClmHosp.setCellValueFactory(new PropertyValueFactory<Doctor, String>("hosp"));
        ClmHosp.setCellFactory(TextFieldTableCell.forTableColumn());
        ClmHosp.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Doctor, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Doctor, String> doctorStringCellEditEvent) {
                Doctor doctor1 = doctorStringCellEditEvent.getRowValue();
                String hospName = doctorStringCellEditEvent.getNewValue();

                if(hospitals.isEmpty()) {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Insertion error");
                    alert2.setHeaderText("Invalid Name");
                    alert2.setContentText("The inserted hospital name is invalid, please check it and try again");
                    alert2.show();

                    doctors_table.refresh();
                    return;
                }

                //editing on the database
                try {
                    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
                    Statement st = con.createStatement();
                    Statement f = con.createStatement();

                    //check if the phone exists
                    boolean hosExist = false;
                    ResultSet s3 = f.executeQuery("select hospital_name from hospital where valid = 'yes' and hospital_name = " + "'" + hospName + "'" );
                    int i = 0 ;
                    while(s3.next()) { i++; }
                    if(i == 1){
                        hosExist = true;
                    }

                    if (!hosExist){
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("Insertion error");
                        alert2.setHeaderText("Hospital not found");
                        alert2.setContentText("The inserted hospital name does not exists, please check it and try again");
                        alert2.show();
                        doctors_table.refresh();
                        con.close();
                        return;
                    }


                    st.executeUpdate("update doctor set " + "hospital_name =" + "'" + hospName + "'" + " where doctor_id = " + doctor1.getDoctor_id());
                    con.close();

                }catch (SQLException e){
                    throw new RuntimeException(e);
                }

                doctor1.setHosp(hospName);
                doctors_table.refresh();

            }
        });

    }


    @FXML
    void toHospital(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("inHospital-B.fxml"));


        Parent root1 = loader1.load();
        Scene scene = new Scene(root1);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        InHospitalHHandler inHospitalHHandler = loader1.getController();
        inHospitalHHandler.hospitalTable = hospitals_table;

        



    }

    @FXML
    void toDoctor(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("inHospital-C.fxml"));
        Parent root2  = loader2.load();
        Scene scene = new Scene(root2);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

        InHospitalDHandler inHospitalDHandler = loader2.getController();
        inHospitalDHandler.doctorTable = doctors_table;


    }

    @FXML
    void deleteDoc(ActionEvent event) {
        Doctor doctor1 = doctors_table.getSelectionModel().getSelectedItem();
        if (doctor1 == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a doctor to delete");
            alert.setHeaderText("No doctor selected");
            alert.setTitle("Error");
            alert.show();
            return;
        }

        ObservableList<Doctor> toDelete = doctors_table.getItems();
        String []name = doctor1.getName().split(" ");
        String hName = "'" + doctor1.getHosp() + "'";

        //deleting from the datebase
        try{
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
            Statement st2 = con.createStatement();

            st2.executeUpdate("delete from doctor where doctor_id =" + doctor1.getDoctor_id() );


                    con.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        //deleting from the interface
        toDelete.remove(doctor1);
        doctors_table.setItems(toDelete);
        doctors_table.refresh();
    }


    @FXML
    void deleteHos(ActionEvent event) {
        Hospital hospital1 = hospitals_table.getSelectionModel().getSelectedItem();
        if (hospital1 == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a hospital to delete");
            alert.setHeaderText("No hospital selected");
            alert.setTitle("Error");
            alert.show();
            return;
        }

        ObservableList<Hospital> toDelete = hospitals_table.getItems();
        String name = "'" + hospital1.getName() + "'";

        //deleting from the datebase
        try{
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
            Statement st = con.createStatement();

            st.executeUpdate("update hospital set valid = 'no' where hospital_name = "+ name );

            con.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        //deleting from the interface
        for (Hospital h : toDelete){
            if (h.getName().equals(hospital1.getName())){
                toDelete.remove(h);
                break;
            }
        }


        hospitals_table.setItems(toDelete);
        hospitals_table.refresh();
        doctors_table.refresh();
    }

}
