package com.example.project286;

import com.jfoenix.controls.JFXTextField;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;

import javax.xml.transform.Result;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HistoryPaneHandler implements Initializable {
    ObservableList <History> histories;
    @FXML
    private TableColumn<History, String> clmDate;

    @FXML
    private TableColumn<History, String> clmDon;

    @FXML
    private TableColumn<History, String> clmEmp;

    @FXML
    private TableColumn<History, String> clmNumber;

    @FXML
    private TableColumn<History, String> clmRec;

    @FXML
    private TableColumn<History, String> clmTime;

    @FXML
    private TableColumn<History, String> clmType;
    @FXML
    private MFXButton search;

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
            histories_table.setItems(histories);
            histories_table.refresh();
            return;
        }

        if (using.getValue().equalsIgnoreCase("operation number")){
            ObservableList<History> h1 = FXCollections.observableArrayList();
            for (int i = 0 ; i < histories.size() ; i++){
                if (histories.get(i).getNumber().equalsIgnoreCase(txtSearch.getText())){
                    h1.add(histories.get(i));
                }
            }
            histories_table.setItems(h1);
            histories_table.refresh();
        }
        else if (using.getValue().equalsIgnoreCase("operation type")){
            ObservableList<History> h2 = FXCollections.observableArrayList();
            for (int i = 0 ; i < histories.size() ; i++){
                if (histories.get(i).getType().equalsIgnoreCase(txtSearch.getText())){
                    h2.add(histories.get(i));
                }
            }
            histories_table.setItems(h2);
            histories_table.refresh();
        }
        else if (using.getValue().equalsIgnoreCase("employee")){
            ObservableList<History> h3 = FXCollections.observableArrayList();
            for (int i = 0 ; i < histories.size() ; i++){
                if (histories.get(i).getEmployee().equalsIgnoreCase(txtSearch.getText())){
                    h3.add(histories.get(i));
                }
            }
            histories_table.setItems(h3);
            histories_table.refresh();
        }
        else if (using.getValue().equalsIgnoreCase("recipient")){
            ObservableList<History> h4 = FXCollections.observableArrayList();
            for (int i = 0 ; i < histories.size() ; i++){
                if (histories.get(i).getRecipient().equalsIgnoreCase(txtSearch.getText())){
                    h4.add(histories.get(i));
                }
            }
            histories_table.setItems(h4);
            histories_table.refresh();
        }
        else if (using.getValue().equalsIgnoreCase("donor")) {
            ObservableList<History> h5 = FXCollections.observableArrayList();
            for (int i = 0 ; i < histories.size() ; i++){
                if (histories.get(i).getDonor().equalsIgnoreCase(txtSearch.getText())){
                    h5.add(histories.get(i));
                }
            }
            histories_table.setItems(h5);
            histories_table.refresh();
        }



    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clmNumber.setCellValueFactory(new PropertyValueFactory<History,String>("number"));
        clmType.setCellValueFactory(new PropertyValueFactory<History,String>("type"));
        clmType.setCellFactory(TextFieldTableCell.forTableColumn());
        clmType.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<History, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<History, String> event) {
                History history = event.getRowValue();
                String newValue = event.getNewValue();
                if(newValue.isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("please insert a value");
                    alert.show();

                    histories_table.refresh();
                    return;
                }

                try {
                    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
                    con.setAutoCommit(false);
                    Statement st = con.createStatement();

                    st.executeUpdate("update operation set operation_type = '" + newValue + "' where operation_number = " + history.getNumber());


                    con.commit();
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                history.setType(newValue);
                histories_table.refresh();




            }
        });

        clmTime.setCellValueFactory(new PropertyValueFactory<History,String>("time"));
        clmDate.setCellValueFactory(new PropertyValueFactory<History,String>("date"));
        clmDon.setCellValueFactory(new PropertyValueFactory<History,String>("donor"));
        clmDon.setCellFactory(TextFieldTableCell.forTableColumn());
        clmDon.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<History, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<History, String> event) {
                History history = event.getRowValue();
                String newValue = event.getNewValue();
                if(newValue.isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("There is no donor having the inserted id.");
                    alert.show();

                    histories_table.refresh();
                    return;
                }

                char c[] = newValue.toCharArray();
                boolean tmp = true;
                for(int i =0 ; i<c.length ;i++) {
                    if(!Character.isDigit(c[i]))
                    tmp = false;
                }

                if(!tmp) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("please insert a value");
                    alert.show();

                    histories_table.refresh();
                    return;

                }



                ResultSet rst;
                try {
                    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
                    con.setAutoCommit(false);
                    Statement st = con.createStatement();
                    Statement st2 = con.createStatement();


                    rst = st2.executeQuery("select * from donor where donor_id = " + newValue);
                    if(!rst.next())  {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("There is no donor having the inserted id.");
                        alert.show();

                        histories_table.refresh();
                        return;
                    }


                    st.executeUpdate("update operation_donor set donor_id = " + newValue + " where operation_number = " + history.getNumber());


                    con.commit();
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                history.setDonor(newValue);
                histories_table.refresh();

            }
        });


        clmEmp.setCellValueFactory(new PropertyValueFactory<History,String>("employee"));
        clmEmp.setCellFactory(TextFieldTableCell.forTableColumn());
        clmEmp.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<History, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<History, String> event) {
                History history = event.getRowValue();
                String newValue = event.getNewValue();
                if(newValue.isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("There is no employe having the inserted id.");
                    alert.show();

                    histories_table.refresh();
                    return;
                }

                char c[] = newValue.toCharArray();
                boolean tmp = true;
                for(int i =0 ; i<c.length ;i++) {
                    if(!Character.isDigit(c[i]))
                        tmp = false;
                }

                if(!tmp) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("please insert a value");
                    alert.show();

                    histories_table.refresh();
                    return;
                }



                ResultSet rst;
                try {
                    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
                    con.setAutoCommit(false);
                    Statement st = con.createStatement();
                    Statement st2 = con.createStatement();


                    rst = st2.executeQuery("select * from employee where employee_id = " + newValue);
                    if(!rst.next())  {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("There is no employee having the inserted id.");
                        alert.show();

                        histories_table.refresh();
                        return;
                    }

                    st.executeUpdate("update operation set employee_id = " + newValue + " where operation_number = " + history.getNumber());

                    con.commit();
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                history.setEmployee(newValue);
                histories_table.refresh();

            }
        });



        clmRec.setCellValueFactory(new PropertyValueFactory<History,String>("recipient"));
        clmRec.setCellFactory(TextFieldTableCell.forTableColumn());
        clmRec.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<History, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<History, String> event) {
                History history = event.getRowValue();
                String newValue = event.getNewValue();
                if(newValue.isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("There is no recipient having the inserted id.");
                    alert.show();

                    histories_table.refresh();
                    return;
                }

                char c[] = newValue.toCharArray();
                boolean tmp = true;
                for(int i =0 ; i<c.length ;i++) {
                    if(!Character.isDigit(c[i]))
                        tmp = false;
                }

                if(!tmp) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("please insert a value");
                    alert.show();

                    histories_table.refresh();
                    return;

                }



                ResultSet rst;
                try {
                    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
                    con.setAutoCommit(false);
                    Statement st = con.createStatement();
                    Statement st2 = con.createStatement();


                    rst = st2.executeQuery("select * from recipient where recipient_id = " + newValue);
                    if(!rst.next())  {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("There is no recipient having the inserted id.");
                        alert.show();

                        histories_table.refresh();
                        return;
                    }

                    st.executeUpdate("update operation_recipient set recipient_id = " + newValue + " where operation_number = " + history.getNumber());


                    con.commit();
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                history.setRecipient(newValue);
                histories_table.refresh();

            }
        });




















        //values
        String[] values = {"Operation number", "Operation type", "Employee", "recipient", "donor"};
        using.getItems().addAll(values);
        using.getEditor().setStyle("-fx-text-fill : white");


    }



    @FXML
    private AnchorPane pane;

    public AnchorPane getPane() {
        return pane;
    }

    @FXML
    private MFXButton btnDelete;

    public TableView<History> getHistories_table() {
        return histories_table;
    }

    @FXML
    private TableView<History> histories_table;

    @FXML
    void deleteHistory(ActionEvent event) {
        History history = histories_table.getSelectionModel().getSelectedItem();
        if (history == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a history to delete");
            alert.setHeaderText("No history selected");
            alert.setTitle("Error");
            alert.show();
            return;
        }
        ObservableList<History> toDelete = histories_table.getItems();

        //deleting from the database
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
            Statement st = con.createStatement();
            st.executeUpdate("delete from operation where operation_number = " + "'" + history.getNumber() + "'");
            con.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        //deleting from the table and refresh
        for (History h : toDelete){
            if (h.getNumber().equals(history.getNumber())){
                toDelete.remove(h);
                break;
            }
        }
        histories_table.refresh();
    }


}
