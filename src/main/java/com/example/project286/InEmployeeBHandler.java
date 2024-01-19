package com.example.project286;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import javax.xml.transform.Result;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class InEmployeeBHandler implements Initializable {

    ObservableList<Employee> employees;
    @FXML
    private TableColumn<Employee, String> addressClm;

    @FXML
    private JFXButton delete;
    @FXML
    private TableColumn<Employee, String> emailClm;

    @FXML
    private TableColumn<Employee, String> idClm;

    @FXML
    private TableColumn<Employee, String> nameClm;

    @FXML
    private TableView<Employee> employees_table;

    public AnchorPane getPaneB() {
        return paneB;
    }

    public void setPaneB(AnchorPane paneB) {
        this.paneB = paneB;
    }

    public TableView<Employee> getEmployees_table() {
        return employees_table;
    }

    public void setEmployees_table(TableView<Employee> employees_table) {
        this.employees_table = employees_table;
    }

    @FXML
    private AnchorPane paneB;
    @FXML
    private TableColumn<Employee, String> phoneClm;
    @FXML
    private TableColumn<Employee, String> rankClm;
    @FXML
    private MFXButton search;
    @FXML
    private TableColumn<Employee, String> startdateClm;
    @FXML
    private TableColumn<Employee, String> wtClm;
    @FXML
    private JFXTextField txtSearch;

    @FXML
    private MFXLegacyComboBox<String> using;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        idClm.setCellValueFactory(new PropertyValueFactory<Employee, String>("id"));
        nameClm.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        addressClm.setCellValueFactory(new PropertyValueFactory<Employee, String>("address"));
        emailClm.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
        phoneClm.setCellValueFactory(new PropertyValueFactory<Employee, String>("phone"));
        rankClm.setCellValueFactory(new PropertyValueFactory<Employee, String>("rank"));
        startdateClm.setCellValueFactory(new PropertyValueFactory<Employee, String>("startDate"));
        wtClm.setCellValueFactory(new PropertyValueFactory<Employee, String>("workingTime"));

        //values
        String[] values = {"id", "name", "rank", "working time", "address", "phone", "email"};
        using.getItems().addAll(values);
        using.getEditor().setStyle("-fx-text-fill : white");

    }


    @FXML
    public void deleteEmp(ActionEvent event) {
        Employee employee = employees_table.getSelectionModel().getSelectedItem();
        if (employee == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select an employee to delete");
            alert.setHeaderText("No employee selected");
            alert.setTitle("Error");
            alert.show();
            return;
        }
        ObservableList<Employee> toDelete = employees_table.getItems();
        String id = "'" + employee.getId() + "'";


        ResultSet rst , rst2;
        //deleting form database
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe\",\"aydigg\",\"123456");
            con.setAutoCommit(false);
            Statement st = con.createStatement();

            rst = st.executeQuery("select * from recipient where id = " + id);
            boolean recipientExist = rst.next();

            rst2 = st.executeQuery("select * from donor where id = " + id);
            boolean donorExist = rst2.next();

            if(donorExist || recipientExist) {
                st.executeUpdate("delete from employee where id = " + id);

            }

            else{
                st.executeUpdate("delete from person where id =" + id );
            }


            con.commit();
            con.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        //deleting from the table and refreshing it
        for (Employee e : toDelete){
            if (e.getId().equals(employee.getId())){
                toDelete.remove(e);
                break;
            }
        }
        employees_table.refresh();

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
            employees_table.setItems(employees);
            employees_table.refresh();
            return;
        }
        if (using.getValue().equalsIgnoreCase("id")){
            ObservableList<Employee> e1 = FXCollections.observableArrayList();
            for (int i = 0 ; i < employees.size() ; i++){
                if (employees.get(i).getId().equalsIgnoreCase(txtSearch.getText())){
                    e1.add(employees.get(i));
                }
            }
            employees_table.setItems(e1);
            employees_table.refresh();
        }
        else if (using.getValue().equalsIgnoreCase("name")){
            ObservableList<Employee> e2 = FXCollections.observableArrayList();
            for (int i = 0 ; i < employees.size() ; i++){
                if (employees.get(i).getName().trim().equalsIgnoreCase(txtSearch.getText().trim())){
                    e2.add(employees.get(i));
                }
            }
            employees_table.setItems(e2);
            employees_table.refresh();
        }
        else if (using.getValue().equalsIgnoreCase("rank")){
            ObservableList<Employee> e3 = FXCollections.observableArrayList();
            for (int i = 0 ; i < employees.size() ; i++){
                if (employees.get(i).getRank().equalsIgnoreCase(txtSearch.getText())){
                    e3.add(employees.get(i));
                }
            }
            employees_table.setItems(e3);
            employees_table.refresh();
        }
        else if (using.getValue().equalsIgnoreCase("working time")){
            ObservableList<Employee> e4 = FXCollections.observableArrayList();
            for (int i = 0 ; i < employees.size() ; i++){
                if (employees.get(i).getWorkingTime().equalsIgnoreCase(txtSearch.getText())){
                    e4.add(employees.get(i));
                }
            }
            employees_table.setItems(e4);
            employees_table.refresh();
        }
        else if (using.getValue().equalsIgnoreCase("address")){
            ObservableList<Employee> e5 = FXCollections.observableArrayList();
            for (int i = 0 ; i < employees.size() ; i++){
                if (employees.get(i).getAddress().equalsIgnoreCase(txtSearch.getText())){
                    e5.add(employees.get(i));
                }
            }
            employees_table.setItems(e5);
            employees_table.refresh();
        }
        else if (using.getValue().equalsIgnoreCase("phone")){
            ObservableList<Employee> e6 = FXCollections.observableArrayList();
            for (int i = 0 ; i < employees.size() ; i++){
                if (employees.get(i).getPhone().equalsIgnoreCase(txtSearch.getText())){
                    e6.add(employees.get(i));
                }
            }
            employees_table.setItems(e6);
            employees_table.refresh();
        }
        else if (using.getValue().equalsIgnoreCase("email")){
            ObservableList<Employee> e7 = FXCollections.observableArrayList();
            for (int i = 0 ; i < employees.size() ; i++){
                if (employees.get(i).getEmail().equalsIgnoreCase(txtSearch.getText())){
                    e7.add(employees.get(i));
                }
            }
            employees_table.setItems(e7);
            employees_table.refresh();
        }


    }
}
