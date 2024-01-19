package com.example.project286;

import com.jfoenix.controls.JFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SurgeryPane2Handler implements Initializable {

    ObservableList<SurgeryData> surgeries;


    @FXML
    private MFXButton search;
        @FXML
        private TableColumn<SurgeryData, String> clmDate;

        @FXML
        private TableColumn<SurgeryData, String> clmDoctor;

        @FXML
        private TableColumn<SurgeryData, String> clmDonor;

        @FXML
        private TableColumn<SurgeryData, String> clmHospital;

        @FXML
        private TableColumn<SurgeryData, String> clmOrgan;

        @FXML
        private TableColumn<SurgeryData, String> clmRecipient;

        @FXML
        private TableColumn<SurgeryData, String> clmResult;

        @FXML
        private TableColumn<SurgeryData, String> clmSNumber;

        @FXML
        private MFXButton delete;

        @FXML
        private AnchorPane paneB;

        public TableView<SurgeryData> getSurgeriesTable() {
                return surgeriesTable;
        }

        @FXML
        private TableView<SurgeryData> surgeriesTable;
    @FXML
    private JFXTextField txtSearch;

    @FXML
    private MFXLegacyComboBox<String> using;



    public AnchorPane getPaneB() {
        return paneB;
    }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            clmSNumber.setCellValueFactory(new PropertyValueFactory<SurgeryData,String>("surgeryNumber"));
            clmDate.setCellValueFactory(new PropertyValueFactory<SurgeryData,String>("surgeryDate")); //yes
            clmDate.setCellFactory(TextFieldTableCell.forTableColumn());
            clmDate.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SurgeryData, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<SurgeryData, String> event) {
                    SurgeryData surgery = event.getRowValue();
                    String newDate = "'" + event.getNewValue() + "'";
                    String tmp [] = event.getNewValue().split("-");

                    if(tmp.length != 3) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Invalid date, please try again");
                        alert.setTitle("Error");
                        alert.show();

                        surgeriesTable.refresh();
                        return;
                    }



                    try {
                        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
                        con.setAutoCommit(false);
                        Statement st = con.createStatement();
                        st.executeUpdate("update surgery set surgery_date = to_date(" + newDate + ", 'yyyy-mm-dd') where surgery_number = " + surgery.getSurgeryNumber() );

                        con.commit();
                        con.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    surgery.setSurgeryDate(event.getNewValue());
                    surgeriesTable.refresh();



                }
            });

                clmOrgan.setCellValueFactory(new PropertyValueFactory<SurgeryData,String>("organ"));
                clmResult.setCellValueFactory(new PropertyValueFactory<SurgeryData,String>("result")); //yes
                clmResult.setCellFactory(TextFieldTableCell.forTableColumn());
                clmResult.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SurgeryData, String>>() {

                    @Override
                    public void handle(TableColumn.CellEditEvent<SurgeryData, String> event) {
                        SurgeryData surgery = event.getRowValue();
                        String newResult = "'" +  event.getNewValue() + "'";
                        String tmp = event.getNewValue();
                        if(! tmp.equals("Ongoing")  && !tmp.equals("Succeeded" ) && !tmp.equals("Failed") ) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Invalid date, please try again");
                            alert.setTitle("Error");
                            alert.show();

                            surgeriesTable.refresh();
                            return;

                        }

                        try {
                            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
                            con.setAutoCommit(false);
                            Statement st = con.createStatement();
                            st.executeUpdate("update surgery set result = " + newResult + " where surgery_number = " + surgery.getSurgeryNumber() );

                            con.commit();
                            con.close();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }

                        surgery.setResult(event.getNewValue());
                        surgeriesTable.refresh();

                    }

                });
                clmHospital.setCellValueFactory(new PropertyValueFactory<SurgeryData,String>("hospitalName")); //yes
                clmHospital.setCellFactory(TextFieldTableCell.forTableColumn());
                clmHospital.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SurgeryData, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<SurgeryData, String> event) {
                    SurgeryData surgery = event.getRowValue();
                    String newHospital = "'" +  event.getNewValue() + "'";

                    ResultSet rst;
                    try {
                        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
                        con.setAutoCommit(false);
                        Statement st = con.createStatement();
                        rst = st.executeQuery("select * from hospital where hospital_name = " + newHospital );





                        if( !rst.next() ) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Invalid hospital name, please try again");
                            alert.setTitle("Error");
                            alert.show();

                            surgeriesTable.refresh();
                            return;

                        }
                        st.executeUpdate("update surgery set hospital_name = " + newHospital + " where surgery_number = " + surgery.getSurgeryNumber() );

                        con.commit();
                        con.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    surgery.setHospitalName(event.getNewValue());
                    surgeriesTable.refresh();

                }

            });



                clmDonor.setCellValueFactory(new PropertyValueFactory<SurgeryData,String>("donorId"));
                clmRecipient.setCellValueFactory(new PropertyValueFactory<SurgeryData,String>("recipientId"));
                clmDoctor.setCellValueFactory(new PropertyValueFactory<SurgeryData,String>("doctorName")); //yes
                clmDoctor.setCellFactory(TextFieldTableCell.forTableColumn());
                clmDoctor.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SurgeryData, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<SurgeryData, String> event) {
                    SurgeryData surgery = event.getRowValue();
                    String newDoctor = "'" +  event.getNewValue() + "'";

                    String tmp [] = event.getNewValue().split(" ");

                    if( tmp.length != 4 ) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Invalid doctor name, please try again");
                        alert.setTitle("Error");
                        alert.show();

                        surgeriesTable.refresh();
                        return;
                    }

                    ResultSet rst;
                    try {
                        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
                        con.setAutoCommit(false);
                        Statement st = con.createStatement();
                        rst = st.executeQuery("select * from doctor where hospital_name = '" + surgery.getHospitalName() + "' and first_name = '" + tmp[0] + "' and second_name = '" + tmp[1] + "' and third_name = '" + tmp[2] + "' and last_name = '" + tmp[3] + "'");


                        if( !rst.next() ) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Invalid input, this hospital doesn't have a doctor with the inserted name");
                            alert.setTitle("Error");
                            alert.show();

                            surgeriesTable.refresh();
                            return;

                        }
                        st.executeUpdate("update surgery set doctor_name = " + newDoctor + " where surgery_number = " + surgery.getSurgeryNumber() );

                        con.commit();
                        con.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    surgery.setDoctorName(event.getNewValue());
                    surgeriesTable.refresh();

                }

            });

            //values
            String[] values = {"surgery number", "organ", "date", "result", "hospital", "doctor", "donor", "recipient"};
            using.getItems().addAll(values);
            using.getEditor().setStyle("-fx-text-fill : white");


        }
    @FXML
    void deletePressed(ActionEvent event) {
        SurgeryData surgery = surgeriesTable.getSelectionModel().getSelectedItem();
        if(surgery == null) {
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("please choose a surgery to delete");
            alert.show();
            return;
        }

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
            con.setAutoCommit(false);
            Statement st = con.createStatement();

            st.executeUpdate("delete from surgery where surgery_number = " + surgery.getSurgeryNumber());

            con.commit();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObservableList<SurgeryData> current = surgeriesTable.getItems();
        for(SurgeryData d : current) {
            if(d.getSurgeryNumber().equalsIgnoreCase(surgery.getSurgeryNumber()) ){
                current.remove(d);
                break;
            }

        }

        surgeriesTable.setItems(current);
        surgeriesTable.refresh();


    }

    @FXML
    void commitSearch(ActionEvent event) {
        if (using.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Choose something to search with");
            alert.show();
            return;
        }

        if (txtSearch.getText().isEmpty()){
            surgeriesTable.setItems(surgeries);
            surgeriesTable.refresh();
            return;
        }

        if (using.getValue().equalsIgnoreCase("surgery number")){
            ObservableList<SurgeryData> s1 = FXCollections.observableArrayList();
            for (int i = 0 ; i < surgeries.size() ; i++){
                if (surgeries.get(i).getSurgeryNumber().equalsIgnoreCase(txtSearch.getText())){
                    s1.add(surgeries.get(i));
                }
            }
            surgeriesTable.setItems(s1);
            surgeriesTable.refresh();
        }

        else if (using.getValue().equalsIgnoreCase("organ")){
            ObservableList<SurgeryData> s2 = FXCollections.observableArrayList();
            for (int i = 0 ; i < surgeries.size() ; i++){
                if (surgeries.get(i).getOrgan().equalsIgnoreCase(txtSearch.getText())){
                    s2.add(surgeries.get(i));
                }
            }
            surgeriesTable.setItems(s2);
            surgeriesTable.refresh();
        }
        else if (using.getValue().equalsIgnoreCase("date")){
            ObservableList<SurgeryData> s3 = FXCollections.observableArrayList();
            for (int i = 0 ; i < surgeries.size() ; i++){
                if (surgeries.get(i).getSurgeryDate().equalsIgnoreCase(txtSearch.getText())){
                    s3.add(surgeries.get(i));
                }
            }
            surgeriesTable.setItems(s3);
            surgeriesTable.refresh();
        }
        else if (using.getValue().equalsIgnoreCase("result")){
            ObservableList<SurgeryData> s4 = FXCollections.observableArrayList();
            for (int i = 0 ; i < surgeries.size() ; i++){
                if (surgeries.get(i).getResult().equalsIgnoreCase(txtSearch.getText())){
                    s4.add(surgeries.get(i));
                }
            }
            surgeriesTable.setItems(s4);
            surgeriesTable.refresh();
        }
        else if (using.getValue().equalsIgnoreCase("hospital")){
            ObservableList<SurgeryData> s5 = FXCollections.observableArrayList();
            for (int i = 0 ; i < surgeries.size() ; i++){
                if (surgeries.get(i).getHospitalName().equalsIgnoreCase(txtSearch.getText())){
                    s5.add(surgeries.get(i));
                }
            }
            surgeriesTable.setItems(s5);
            surgeriesTable.refresh();
        }
        else if (using.getValue().equalsIgnoreCase("doctor")){
            ObservableList<SurgeryData> s6 = FXCollections.observableArrayList();
            for (int i = 0 ; i < surgeries.size() ; i++){
                if (surgeries.get(i).getDoctorName().equalsIgnoreCase(txtSearch.getText())){
                    s6.add(surgeries.get(i));
                }
            }
            surgeriesTable.setItems(s6);
            surgeriesTable.refresh();
        }
        else if (using.getValue().equalsIgnoreCase("donor")){
            ObservableList<SurgeryData> s7 = FXCollections.observableArrayList();
            for (int i = 0 ; i < surgeries.size() ; i++){
                if (surgeries.get(i).getDonorId().equalsIgnoreCase(txtSearch.getText())){
                    s7.add(surgeries.get(i));
                }
            }
            surgeriesTable.setItems(s7);
            surgeriesTable.refresh();
        }
        else if (using.getValue().equalsIgnoreCase("recipient")){
            ObservableList<SurgeryData> s8 = FXCollections.observableArrayList();
            for (int i = 0 ; i < surgeries.size() ; i++){
                if (surgeries.get(i).getRecipientId().equalsIgnoreCase(txtSearch.getText())){
                    s8.add(surgeries.get(i));
                }
            }
            surgeriesTable.setItems(s8);
            surgeriesTable.refresh();
        }



    }




}
