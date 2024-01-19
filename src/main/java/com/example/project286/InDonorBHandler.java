package com.example.project286;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;


public class InDonorBHandler implements Initializable {
    EmployeeLogin emp;

    ObservableList<Donor> donors;
    @FXML
    private AnchorPane paneB;

    public AnchorPane getPaneB() {
        return paneB;
    }

    @FXML
    private JFXButton delete;

    @FXML
    private MFXLegacyTableView<Donor> donors_table;

    public MFXLegacyTableView<Donor> getDonors_table() {
        return donors_table;
    }

    public void setDonors_table(MFXLegacyTableView<Donor> donors_table) {
        this.donors_table = donors_table;
    }

    @FXML
    TableColumn<Donor, String> clmId;
    @FXML
    TableColumn<Donor, String> clmName;
    @FXML
    TableColumn<Donor, String> clmOrgan;
    @FXML
    TableColumn<Donor, String> clmWName;
    @FXML
    TableColumn<Donor, String> clmBloodType;
    @FXML
    TableColumn<Donor, String> clmPhone;
    @FXML
    TableColumn<Donor, String> clmEmail;
    @FXML
    TableColumn<Donor, String> clmAddress;
    @FXML
    TableColumn<Donor, String> clmState;
    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXButton search;

    @FXML
    private MFXLegacyComboBox<String> using;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clmId.setCellValueFactory(new PropertyValueFactory<Donor, String>("id"));
        clmName.setCellValueFactory(new PropertyValueFactory<Donor, String>("name"));
        clmWName.setCellValueFactory(new PropertyValueFactory<Donor, String>("witnessName"));
        clmOrgan.setCellValueFactory(new PropertyValueFactory<Donor, String>("organ"));
        clmBloodType.setCellValueFactory(new PropertyValueFactory<Donor, String>("bloodType"));
        clmPhone.setCellValueFactory(new PropertyValueFactory<Donor, String>("phone"));
        clmEmail.setCellValueFactory(new PropertyValueFactory<Donor, String>("email"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<Donor, String>("address"));
        clmState.setCellValueFactory(new PropertyValueFactory<Donor, String>("state"));

        //values
        String[] values = {"Id", "Donor id", "Name", "State", "Blood type"};
        using.getItems().addAll(values);
        using.getEditor().setStyle("-fx-text-fill : white");


    }

    @FXML
    void commitDelete(ActionEvent event) {
        Donor donor = donors_table.getSelectionModel().getSelectedItem();
        if (donor == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a donor to delete");
            alert.setHeaderText("No donor selected");
            alert.setTitle("Error");
            alert.show();
            return;
        }

        ObservableList<Donor> toDelete = donors_table.getItems();
        String id = "'" + donor.getId() + "'";
        String id2 = donor.getId();

        //deleting from the database
        ResultSet rst, rst2;
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "aydigg", "123456");
            con.setAutoCommit(false);
            Statement st = con.createStatement();

            rst = st.executeQuery("select * from recipient where id = " + id);
            boolean recipientExist = rst.next();

            rst2 = st.executeQuery("select * from employee where id = " + id);
            boolean employeeExist = rst2.next();

            if (employeeExist || recipientExist) {
                st.executeUpdate("delete from donor where id = " + id);

            } else {
                st.executeUpdate("delete from person where id =" + id);
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
            String time = simpleDateFormat.format(new java.util.Date());

            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy/MM/dd");
            String date = simpleDateFormat2.format(new java.util.Date()) ;
            st.executeUpdate("insert into operation (operation_number,operation_type,operation_time, person_type,operation_date,employee_id) VALUES (operation_counter.nextval, 'deleted donor-- " +  id2   +   "' , '" + time + "' , 'donor' , to_date('" + date +  "', 'yyyy/mm/dd') , " + emp.getEmployee_id() + ")");
            //st.executeUpdate("insert into operation_donor (operation_number,donor_id) values(operation_counter.currval, donor_counter.currval)");



            con.commit();
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        //deleting from the table and refreshing it
        for (Donor d : toDelete) {
            if (d.getId().equals(donor.getId())) {
                toDelete.remove(d);
                break;
            }
        }
        donors_table.refresh();


    }

    @FXML
    void commitSearch(ActionEvent event) {
        if (using.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("choose something to search with");
            alert.show();

            return;
        }

        if(txtSearch.getText().isEmpty()) {

            donors_table.setItems(donors);
            donors_table.refresh();

            return;


        }


        if (using.getValue().equalsIgnoreCase("state")) {
            ObservableList<Donor> s1 = FXCollections.observableArrayList();
            for (int i = 0 ; i< donors.size();i++) {
                if (donors.get(i).getState().equalsIgnoreCase(txtSearch.getText())) {
                    s1.add(donors.get(i));
                }

            }

            donors_table.setItems(s1);
            donors_table.refresh();

        }

        else if (using.getValue().equalsIgnoreCase("id")) {
            ObservableList<Donor> s2 = FXCollections.observableArrayList();
            for (int i = 0 ; i< donors.size();i++) {
                if (donors.get(i).getId().equalsIgnoreCase(txtSearch.getText())) {
                    s2.add(donors.get(i));
                }

            }

            donors_table.setItems(s2);
            donors_table.refresh();
        }

        else if (using.getValue().equalsIgnoreCase("Blood type")) {
            ObservableList<Donor> s3 = FXCollections.observableArrayList();
            for (int i = 0 ; i< donors.size();i++) {
                if (donors.get(i).getBloodType().equalsIgnoreCase(txtSearch.getText())) {
                    s3.add(donors.get(i));
                }

            }

            donors_table.setItems(s3);
            donors_table.refresh();
        }

        else if (using.getValue().equalsIgnoreCase("name")) {
            ObservableList<Donor> s4 = FXCollections.observableArrayList();
            for (int i = 0 ; i< donors.size();i++) {
                String names[] = donors.get(i).getName().split(" ");
                String fullName = donors.get(i).getName();

                if ( names[0].equalsIgnoreCase(txtSearch.getText()) || names[1].equalsIgnoreCase(txtSearch.getText()) || names[2].equalsIgnoreCase(txtSearch.getText()) || names[3].equalsIgnoreCase(txtSearch.getText()) || fullName.trim().equalsIgnoreCase(txtSearch.getText().trim())  ) {
                    s4.add(donors.get(i));
                }

            }

            donors_table.setItems(s4);
            donors_table.refresh();
        }
        else if (using.getValue().equalsIgnoreCase("donor id")) {
            ObservableList<Donor> s5 = FXCollections.observableArrayList();
            for (int i = 0 ; i< donors.size();i++) {
                if (donors.get(i).getDonor_id().equalsIgnoreCase(txtSearch.getText())) {
                    s5.add(donors.get(i));
                }
            }
            donors_table.setItems(s5);
            donors_table.refresh();
        }


    }
}
