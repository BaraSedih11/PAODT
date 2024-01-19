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

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class TestPaneHandler implements Initializable {

    ObservableList<Test> tests;
    @FXML
    private AnchorPane pane;

    @FXML
    private MFXButton btnDelete;

    public TableView<Test> getTests_table() {
        return tests_table;
    }

    @FXML
    private TableView<Test> tests_table;

    @FXML
    private TableColumn<Test, String> clmDonor;

    @FXML
    private TableColumn<Test, String> clmName;

    @FXML
    private TableColumn<Test, String> clmNumber;

    @FXML
    private TableColumn<Test, String> clmRecipient;

    @FXML
    private TableColumn<Test, String> clmResult;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private MFXButton search;

    @FXML
    private MFXLegacyComboBox<String> using;

    public AnchorPane getPane() {
        return pane;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        clmDonor.setCellValueFactory(new PropertyValueFactory<Test, String>("donor"));
        clmRecipient.setCellValueFactory(new PropertyValueFactory<Test, String>("recipient"));
        clmName.setCellValueFactory(new PropertyValueFactory<Test, String>("name"));
        clmName.setCellFactory(TextFieldTableCell.forTableColumn());
        clmName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Test, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Test, String> event) {
                Test test = event.getRowValue();
                String name = event.getNewValue();

                if(name.isEmpty()){

                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Insertion error");
                    alert2.setHeaderText("Invalid Name");
                    alert2.setContentText("The inserted doctor name is invalid, please check it and try again");
                    alert2.show();
                    tests_table.refresh();
                    return;
                }



                //editing on the database
                try {
                    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
                    Statement st = con.createStatement();
                    Statement f = con.createStatement();

                    st.executeUpdate("update test set " + "test_name =" + "'" + name + "'" + " where test_number =" + "'" + test.getNumber() + "'" );

                    con.close();

                }catch (SQLException e){
                    throw new RuntimeException(e);
                }
                test.setName(event.getNewValue());
                tests_table.refresh();
            }
        });

        clmNumber.setCellValueFactory(new PropertyValueFactory<Test, String>("number"));

        clmResult.setCellValueFactory(new PropertyValueFactory<Test, String>("result"));
        clmResult.setCellFactory(TextFieldTableCell.forTableColumn());
        clmResult.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Test, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Test, String> event) {
                Test test = event.getRowValue();
                String result = event.getNewValue();

                if(result.isEmpty()){

                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Insertion error");
                    alert2.setHeaderText("Invalid Name");
                    alert2.setContentText("The inserted doctor name is invalid, please check it and try again");
                    alert2.show();
                    tests_table.refresh();
                    return;
                }


                //editing on the database
                try {
                    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
                    con.setAutoCommit(false);
                    Statement st = con.createStatement();


                    st.executeUpdate("update test set " + "test_result =" + "'" + result + "'" + " where test_number =" + "'" + test.getNumber() + "'" );
                    con.commit();
                    con.close();

                }catch (SQLException e){
                    throw new RuntimeException(e);
                }
                test.setResult(event.getNewValue());
                tests_table.refresh();

            }
        });

        //values
        String[] values = {"test number", "test name", "Result", "recipient", "donor"};
        using.getItems().addAll(values);
        using.getEditor().setStyle("-fx-text-fill : white");


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
            tests_table.setItems(tests);
            tests_table.refresh();
            return;
        }
        if (using.getValue().equalsIgnoreCase("test number")){
            ObservableList<Test> t1 = FXCollections.observableArrayList();
            for (int i = 0 ; i < tests.size() ; i++){
                if (tests.get(i).getNumber().equalsIgnoreCase(txtSearch.getText())){
                    t1.add(tests.get(i));
                }
            }
            tests_table.setItems(t1);
            tests_table.refresh();
        }
        else if (using.getValue().equalsIgnoreCase("test name")){
            ObservableList<Test> t2 = FXCollections.observableArrayList();
            for (int i = 0 ; i < tests.size() ; i++){
                if (tests.get(i).getName().equalsIgnoreCase(txtSearch.getText())){
                    t2.add(tests.get(i));
                }
            }
            tests_table.setItems(t2);
            tests_table.refresh();
        }
        else if (using.getValue().equalsIgnoreCase("result")){
            ObservableList<Test> t3 = FXCollections.observableArrayList();
            for (int i = 0 ; i < tests.size() ; i++){
                if (tests.get(i).getResult().equalsIgnoreCase(txtSearch.getText())){
                    t3.add(tests.get(i));
                }
            }
            tests_table.setItems(t3);
            tests_table.refresh();
        }
        else if (using.getValue().equalsIgnoreCase("recipient")){
            ObservableList<Test> t4 = FXCollections.observableArrayList();
            for (int i = 0 ; i < tests.size() ; i++){
                if (tests.get(i).getRecipient().equalsIgnoreCase(txtSearch.getText())){
                    t4.add(tests.get(i));
                }
            }
            tests_table.setItems(t4);
            tests_table.refresh();
        }
        else if (using.getValue().equalsIgnoreCase("donor")){
            ObservableList<Test> t5 = FXCollections.observableArrayList();
            for (int i = 0 ; i < tests.size() ; i++){
                if (tests.get(i).getDonor().equalsIgnoreCase(txtSearch.getText())){
                    t5.add(tests.get(i));
                }
            }
            tests_table.setItems(t5);
            tests_table.refresh();
        }


    }


    @FXML
    void deleteTest(ActionEvent event) {
        Test test = tests_table.getSelectionModel().getSelectedItem();
        if (test == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a test to delete");
            alert.setHeaderText("No test selected");
            alert.setTitle("Error");
            alert.show();
            return;
        }
        ObservableList<Test> toDelete = tests_table.getItems();

        //deleting from the database
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
            Statement st = con.createStatement();
            st.executeUpdate("delete from test where test_number = " + "'" + test.getNumber() + "'");
            con.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        //deleting from the table and refresh
        for (Test t : toDelete){
            if (t.getNumber().equals(test.getNumber())){
                toDelete.remove(t);
                break;
            }
        }
        tests_table.refresh();
    }
}
