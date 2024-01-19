package com.example.project286;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.sql.StatementEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SignInPaneHandler implements Initializable {
    String life;
    String superRank;

    @FXML
    private AnchorPane LoginPane;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtUsername;
    Stage stage = new Stage();
    Stage stage2 = new Stage();

    public EmployeeLogin getEmp() {
        return emp;
    }

    public void setEmp(EmployeeLogin emp) {
        this.emp = emp;
    }

    EmployeeLogin emp;

    @FXML
    void Login(ActionEvent event) throws IOException {

        //checking the username and the password
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String fName = "";
        String sName = "";
        String tName = "";
        String lName = "";
        String id = "";
        String unq_id = "";



        ResultSet rst, rst1;
        try{
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");

            Statement st = con.createStatement();
            rst = st.executeQuery("select * from employee where username = " + "'" + username + "'" + " and password = " + "'" + password + "'");


            int i = 0;
            while (rst.next()){
                i++;
                unq_id = Integer.toString(rst.getInt("employee_id"));
                id = rst.getString("id");
                superRank = rst.getString("rank");

            }

            if (i == 0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please enter a valid username and password to login");
                alert.setHeaderText("No employee found");
                alert.setTitle("Error");
                alert.show();
                con.close();
                return;
            }

            rst1 = st.executeQuery("select first_name, second_name, third_name, last_name from person where id = " + id);

            while (rst1.next()){
                fName = rst1.getString("first_name");
                sName = rst1.getString("second_name");
                tName = rst1.getString("third_name");
                lName = rst1.getString("last_name");



            }


            ResultSet r  =st.executeQuery("select count(*) from surgery where result = 'Succeeded'");
            while (r.next()) {
                life = r.getString("count(*)");
            }

            con.close();

        }catch (SQLException e){
            throw new IOException(e);
        }

        emp = new EmployeeLogin();
        emp.setId(id);
        emp.setUsername(username);
        emp.setPassword(password);
        emp.setEmployee_id(unq_id);
        emp.setName(fName + " " + sName + " " + tName + " " + lName);



        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main-frame.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        MainFrameHandler mainFrameHandler = loader.getController();
        mainFrameHandler.emp = emp;

        mainFrameHandler.getLifeCount().setText(life);
        mainFrameHandler.getLblName().setText(emp.getName());
        mainFrameHandler.getLblUser().setText(emp.getUsername());

        boolean b = superRank.equalsIgnoreCase("supervisor");
        mainFrameHandler.superStuff(b);
        Image image = new Image("pal.png");
        stage.getIcons().add(image);
        stage2 = (Stage) LoginPane.getScene().getWindow();
        stage2.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
