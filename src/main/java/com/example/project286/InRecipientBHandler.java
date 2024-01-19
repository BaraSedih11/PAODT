package com.example.project286;

import com.jfoenix.controls.JFXButton;
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


public class InRecipientBHandler implements Initializable {
    EmployeeLogin emp;
    ObservableList<Recipient> recipients ;
    @FXML
    private AnchorPane paneB;

    public AnchorPane getPaneB() {
        return paneB;
    }

    @FXML
    private JFXButton delete;

    @FXML
    private JFXButton search;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private MFXLegacyComboBox<String> using;

    @FXML
    private MFXLegacyTableView<Recipient> recipients_table;

    public MFXLegacyTableView<Recipient> getRecipients_table() {
        return recipients_table;
    }

    public void setDonors_table(MFXLegacyTableView<Recipient> recipients_table) {
        this.recipients_table = recipients_table;
    }

    @FXML
    TableColumn<Recipient,String> clmId ;
    @FXML
    TableColumn<Recipient,String> clmName ;
    @FXML
    TableColumn<Recipient,String> clmOrgan ;
    @FXML
    TableColumn<Recipient,String> clmPhone ;
    @FXML
    TableColumn<Recipient,String> clmEmail ;
    @FXML
    TableColumn<Recipient,String> clmAddress ;
    @FXML
    TableColumn<Recipient,String> clmPriority ;
    @FXML
    TableColumn<Recipient,String> clmRegDate ;
    @FXML
    TableColumn<Recipient,String> clmRecieved ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clmId.setCellValueFactory(new PropertyValueFactory<Recipient,String>("id"));
        clmName.setCellValueFactory(new PropertyValueFactory<Recipient,String>("name"));
        clmOrgan.setCellValueFactory(new PropertyValueFactory<Recipient,String>("neededMember"));
        clmPhone.setCellValueFactory(new PropertyValueFactory<Recipient,String>("phone"));
        clmEmail.setCellValueFactory(new PropertyValueFactory<Recipient,String>("email"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<Recipient,String>("address"));
        clmPriority.setCellValueFactory(new PropertyValueFactory<Recipient,String>("precedence"));
        clmRegDate.setCellValueFactory(new PropertyValueFactory<Recipient,String>("registrationDate"));
        clmRecieved.setCellValueFactory(new PropertyValueFactory<Recipient,String>("recieved"));

        //values
        String[] values = {"Id", "recipient id", "Name", "recieved", "needed member"};
        using.getItems().addAll(values);
        using.getEditor().setStyle("-fx-text-fill : white");


    }

    @FXML
    void commitDelete(ActionEvent event) {
        Recipient recipient=  recipients_table.getSelectionModel().getSelectedItem();
        if(recipient == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a recipient to delete");
            alert.setHeaderText("No recipient selected");
            alert.setTitle("Error");
            alert.show();
            return;
        }

        ObservableList<Recipient> toDelete = recipients_table.getItems();
        String id= "'"+ recipient.getId()+ "'";
        String id2 = recipient.getId();
        //deleting from the database

        ResultSet rst , rst2;
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
            con.setAutoCommit(false);
            Statement st = con.createStatement();


            rst = st.executeQuery("select * from donor where id = " + id);
            boolean donorExist = rst.next();

            rst2 = st.executeQuery("select * from employee where id = " + id);
            boolean employeeExist = rst2.next();

            if(employeeExist || donorExist) {
                st.executeUpdate("delete from recipient where id = " + id);

            }

            else{
                st.executeUpdate("delete from person where id =" + id );
            }

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
            String time = simpleDateFormat.format(new java.util.Date());

            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy/MM/dd");
            String date = simpleDateFormat2.format(new java.util.Date()) ;
            st.executeUpdate("insert into operation (operation_number,operation_type,operation_time, person_type,operation_date,employee_id) VALUES (operation_counter.nextval, 'deleted recipient-- " + id2   +"' , '" + time + "' , 'donor' , to_date('" + date +  "', 'yyyy/mm/dd') , " + emp.getEmployee_id() + ")");



            con.commit();
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        //deleting from the table and refreshing it
        for (Recipient r: toDelete) {
            if(r.getId().equals(recipient.getId())) {
                toDelete.remove(r);
                break;
            }
        }
        recipients_table.refresh();




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

            recipients_table.setItems(recipients);
            recipients_table.refresh();
            return;
        }

        if (using.getValue().equalsIgnoreCase("recieved")) {
            ObservableList<Recipient> s1 = FXCollections.observableArrayList();
            for (int i = 0 ; i< recipients.size();i++) {
                if (recipients.get(i).getRecieved().equalsIgnoreCase(txtSearch.getText())) {
                    s1.add(recipients.get(i));
                }

            }

            recipients_table.setItems(s1);
            recipients_table.refresh();
        }

        else if (using.getValue().equalsIgnoreCase("id")) {
            ObservableList<Recipient> s2 = FXCollections.observableArrayList();
            for (int i = 0 ; i< recipients.size();i++) {
                if (recipients.get(i).getId().equalsIgnoreCase(txtSearch.getText())) {
                    s2.add(recipients.get(i));
                }

            }

            recipients_table.setItems(s2);
            recipients_table.refresh();
        }

        else if (using.getValue().equalsIgnoreCase("needed member")) {
            ObservableList<Recipient> s3 = FXCollections.observableArrayList();
            for (int i = 0 ; i< recipients.size();i++) {
                if (recipients.get(i).getNeededMember().equalsIgnoreCase(txtSearch.getText())) {
                    s3.add(recipients.get(i));
                }

            }

            recipients_table.setItems(s3);
            recipients_table.refresh();
        }
        else if (using.getValue().equalsIgnoreCase("name")) {
            ObservableList<Recipient> s4 = FXCollections.observableArrayList();
            for (int i = 0 ; i< recipients.size();i++) {
                String fullname = recipients.get(i).getName();
                String names[] = fullname.split(" ");


                if ( names[0].equalsIgnoreCase(txtSearch.getText()) || names[1].equalsIgnoreCase(txtSearch.getText()) || names[2].equalsIgnoreCase(txtSearch.getText()) || names[3].equalsIgnoreCase(txtSearch.getText()) || fullname.trim().equalsIgnoreCase(txtSearch.getText().trim())  ) {
                    s4.add(recipients.get(i));
                }

            }

            recipients_table.setItems(s4);
            recipients_table.refresh();
        }
        else if (using.getValue().equalsIgnoreCase("recipient id")) {
            ObservableList<Recipient> s5 = FXCollections.observableArrayList();
            for (int i = 0 ; i< recipients.size();i++) {
                if (recipients.get(i).getRecipient_id().equalsIgnoreCase(txtSearch.getText())) {
                    s5.add(recipients.get(i));
                }
            }
            recipients_table.setItems(s5);
            recipients_table.refresh();
        }












    }
}
