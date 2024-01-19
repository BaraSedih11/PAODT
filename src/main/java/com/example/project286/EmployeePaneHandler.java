package com.example.project286;
import com.jfoenix.controls.JFXButton;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmployeePaneHandler implements Initializable {
    @FXML
    private JFXButton add;
    @FXML
    private AnchorPane employeePane;

    public AnchorPane getEmployeePane() {
        return employeePane;
    }

    @FXML
    private AnchorPane inEmployeePane;

    public TableView<Employee> getEmployeeTable() {
        return employeeTable;
    }

    public void setEmployeeTable(TableView<Employee> employeeTable) {
        this.employeeTable = employeeTable;
    }

    private TableView<Employee> employeeTable;
    private Employee emp;
    @FXML
    private JFXButton save;
    @FXML
    private JFXButton saveChanges;


    @FXML
    private JFXButton cancel;
    @FXML
    private JFXButton up;
    AnchorPane paneA;
    AnchorPane paneB;

    InEmployeeAHandler scene1;
    InEmployeeBHandler bh;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        save.setVisible(false);
        cancel.setVisible(false);
        up.setVisible(true);
        saveChanges.setVisible(false);




        FXMLLoader loaderA = new FXMLLoader(getClass().getResource("inEmployee-A.fxml"));
        try {
            Parent rootA = loaderA.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InEmployeeAHandler inEmployeeAHandler = loaderA.getController();
        paneA= inEmployeeAHandler.getPaneA();
        //moving values
        scene1 = inEmployeeAHandler;
        //save = ;

        FXMLLoader loaderB = new FXMLLoader(getClass().getResource("inEmployee-B.fxml"));
        try {
            Parent rootB = loaderB.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InEmployeeBHandler inEmployeeBHandler = loaderB.getController();
        paneB = inEmployeeBHandler.getPaneB();
        employeeTable = inEmployeeBHandler.getEmployees_table();
        bh = inEmployeeBHandler;

        //setting initial interface ...







        inEmployeePane.getChildren().add(paneB);


    }

    public void addEmp(ActionEvent e) {
        save.setVisible(true);
        add.setVisible(false);
        cancel.setVisible(true);
        up.setVisible(false);
        saveChanges.setVisible(false);


        if(! inEmployeePane.getChildren().isEmpty()){
            inEmployeePane.getChildren().remove(0);
        }
        inEmployeePane.getChildren().add(paneA);


    }
    //save btn
    public void currentEmp(ActionEvent e) {


        //start here

        //validation
        boolean nameValid = ( scene1.getfName().getText() != "" &&  scene1.getsName().getText() != "" && scene1.gettName().getText() != "" && scene1.getlName().getText() != "" ) ;
        boolean idValid;
        char c [] = scene1.getId().getText().toCharArray();
        if( scene1.getId().getText().length() == 9 ) {
            for (int i = 0; i < c.length; i++) {
                if (!Character.isDigit(c[i])) {
                    idValid = false;
                    break;
                }


            }
            idValid = true;
        }
        else { idValid = false;}

        boolean addressValid = ( !scene1.getCity().getText().isEmpty() && ! scene1.getStreet().getText().isEmpty() );
        boolean emailPhoneValid = ( !scene1.getEmail().getText().isEmpty() && !scene1.getPhone().getText().isEmpty() ) ;
        boolean birthdateValid = true;
        if ( scene1.getBirthdate().getValue() != null) {
            birthdateValid = ( !scene1.getBirthdate().getValue().toString().isEmpty() );
        }
        else { birthdateValid = false;}

        boolean startDateValid = true;
        if ( scene1.getStartdate().getValue() != null) {
            startDateValid = ( !scene1.getStartdate().getValue().toString().isEmpty() );
        }
        else { startDateValid = false;}

        boolean genderValid = ( scene1.getMale().isSelected() || scene1.getFemale().isSelected() );
        boolean wtValid = ( scene1.getMorning().isSelected() || scene1.getEvening().isSelected() );
        boolean rankValid = ( scene1.getSupervisee().isSelected() || scene1.getSuperviser().isSelected() );

        boolean salaryValid = true;
        char ch [] = scene1.getSalary().getText().toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (!Character.isDigit(c[i])) {
                salaryValid= false;
                break;
            }
        }

        boolean phoneValid;
        char c2 [] = scene1.getPhone().getText().toCharArray();
        if( scene1.getPhone().getText().length() == 10 ) {
            for (int i = 0; i < c.length; i++) {
                if (!Character.isDigit(c[i])) {
                    phoneValid= false;
                    break;
                }


            }
            phoneValid = true;
        }
        else{
            phoneValid = false;
        }


        boolean bloodValid = scene1.getBloodtype().getValue() != null;
        boolean passwordValid = scene1.getPassword().getText() != null;
        boolean usernameValid = scene1.getUsername().getText() != "";

        boolean result = nameValid && idValid && addressValid && emailPhoneValid && birthdateValid && genderValid && phoneValid && wtValid && bloodValid && rankValid && salaryValid && usernameValid && passwordValid;
        if (!result){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid input, please check the inserted data and try again");
            alert.setTitle("Invalid input");
            alert.show();
            return;
        }


        //------------ end of validation
        String fname = "'"+ scene1.getfName().getText() + "'";
        String sname = "'" + scene1.getsName().getText() + "'";
        String tname = "'" + scene1.gettName().getText() + "'" ;
        String lname  = "'" + scene1.getlName().getText() + "'";

        String id ="'"+  scene1.getId().getText() + "'";
        String phone = "'" + scene1.getPhone().getText() + "'";
        String gender =  (scene1.getMale().isSelected()) ? "'Male'" : "'Female'" ;
        String wt =  (scene1.getEvening().isSelected()) ? "'evening'" : "'morning'" ;
        String rank =  (scene1.getSupervisee().isSelected()) ? "'supervisee'" : "'supervisor'" ;
        String salary = "'" + scene1.getSalary().getText() + "'";
        String email = "'" + scene1.getEmail().getText() + "'";
        String birthdate = "'"+  scene1.getBirthdate().getValue().toString() + "'" ;
        String startdate = "'"+  scene1.getStartdate().getValue().toString() + "'" ;
        String bloodType = "'" + scene1.getBloodtype().getValue() + "'";
        String city = "'" + scene1.getCity().getText() + "'";   String street = "'" +  scene1.getStreet().getText() + "'";
        String username = "'" + scene1.getUsername().getText() + "'";
        String password = "'" + scene1.getPassword().getText() + "'";


        try{
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
            Statement st = con.createStatement();
            Statement f = con.createStatement();


            //checking the id , email, phone, username because the are unique values;
            boolean emailExist= false , phoneExist = false ,personExist = false, usernameExist = false;

            //checking the id before adding
            ResultSet s = f.executeQuery("select id from Employee where id = " + id);
            int i = 0;
            while(s.next()) { i++; }
            if(i == 1){
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Insertion error");
                alert1.setHeaderText("ID duplication");
                alert1.setContentText("The inserted id already exists, please check it and try again");
                alert1.show();
                return;
            }

            //checking the email before adding
            ResultSet s2 = f.executeQuery("select email from person where email = " + email);
            i = 0 ;
            while(s2.next()) { i++; }
            if(i == 1){
                emailExist = true;
            }

            //checking the phone before adding
            ResultSet s3 = f.executeQuery("select phone_number from person where phone_number = " +phone);
            i = 0 ;
            while(s3.next()) { i++; }
            if(i == 1){
                phoneExist = true;
            }

            //checking the username before adding
            ResultSet s4 = f.executeQuery("select username from employee where username = " + username);
            i = 0 ;
            while(s4.next()) { i++; }
            if(i == 1){
                usernameExist = true;
            }

            //inserting data of the person table
            //before adding check whether the person exists or not
            ResultSet s5 = f.executeQuery("select id from person where id = " + id);
            i = 0;
            while(s5.next()) { i++; }
            if(i == 1){
                personExist = true;
            }

            if(!personExist) {
                if(emailExist) {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Insertion erorr");
                    alert2.setHeaderText("EMAIL duplication");
                    alert2.setContentText("The inserted email already exists, please check it and try again");
                    alert2.show();
                    return;
                }
                if(phoneExist) {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Insertion error");
                    alert2.setHeaderText("PHONE duplication");
                    alert2.setContentText("The inserted email already exists, please check it and try again");
                    alert2.show();

                    return;


                }

            }

            if(!personExist) //if person doesn't exist, add him otherwise skip
                st.executeUpdate("insert into person (first_name,second_name,third_name,last_name,id,phone_number,gender,email,birthdate,blood_type,city,street)  values (" + fname + "," +sname+","+tname+ "," +lname + "," + id +"," + phone + ","+ gender + "," + email + "," + "TO_DATE(" + birthdate  + ",'yyyy-mm-dd')" + "," + bloodType + "," + city + "," + street +")"   ) ;


            //inserting the data of the employee
            Statement st2 = con.createStatement();
            st2.executeUpdate("insert into employee (employee_id, id, start_date, shift, rank, salary, username, password) values (" + " employee_counter.nextval" + "," + id + "," + "to_date(" + startdate + ",'yyyy/mm/dd') ," + wt + "," + rank + "," + salary + "," + username + "," + password + ")");

            con.close();

        }catch (SQLException exception){
            throw new RuntimeException(exception);
        }

        scene1.getfName().setText(""); scene1.getsName().setText(""); scene1.gettName().setText(""); scene1.getlName().setText("");
        scene1.getId().setText(""); scene1.getBirthdate().getEditor().clear();
        scene1.getMale().setSelected(false); scene1.getFemale().setSelected(false);
        scene1.getEmail().setText(""); scene1.getPhone().setText("");
        scene1.getCity().setText(""); scene1.getEmail().setText("");
        scene1.getStreet().setText("");
        scene1.getMale().setSelected(false); scene1.getFemale().setSelected(false);
        scene1.getStartdate().getEditor().clear();
        scene1.getMorning().setSelected(false); scene1.getEvening().setSelected(false);
        scene1.getSuperviser().setSelected(false); scene1.getSupervisee().setSelected(false);
        scene1.getBloodtype().setValue("");
        scene1.getSalary().setText("");
        scene1.getUsername().setText(""); scene1.getPassword().setText("");



        //end
        save.setVisible(false);
        add.setVisible(true);
        cancel.setVisible(false);
        up.setVisible(true);
        //update.setVisible(true);
        if(! inEmployeePane.getChildren().isEmpty()){
            inEmployeePane.getChildren().remove(0);
        }
        inEmployeePane.getChildren().add(paneB);
    }

    //no update
    public void currentEmp2(ActionEvent e) {
        save.setVisible(false);
        add.setVisible(true);
        cancel.setVisible(false);
        up.setVisible(true);
        //update.setVisible(true);

        if(! inEmployeePane.getChildren().isEmpty()){
            inEmployeePane.getChildren().remove(0);
        }
        inEmployeePane.getChildren().add(paneB);

    }

    @FXML
    void upPressed(ActionEvent event){


        //checking if there is an employee selected
        Employee employee = employeeTable.getSelectionModel().getSelectedItem();
        emp = employee;
        if (employee == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select an employee to update");
            alert.setHeaderText("No employee selected");
            alert.setTitle("Error");
            alert.show();
            return;
        }
        //switching the scenes
        if (!inEmployeePane.getChildren().isEmpty()){
            inEmployeePane.getChildren().remove(0);
        }
        //setting the new pane
        inEmployeePane.getChildren().add(paneA);

        save.setVisible(false);
        add.setVisible(false);
        up.setVisible(false);
        saveChanges.setVisible(true);



        //scene1.getId().setEditable(false);

        //setting the fields

        String names[] = employee.getName().split(" ");
        String addr[] = employee.getAddress().split(",");
        String tmp = addr[1].trim();
        String dataDate = "";
        String startDate = "";
        String bld = "";
        String unq_id = "";
        boolean male = false;
        boolean morning = false;
        boolean superviser = false;
        String email = "";
        String phone = "";
        String salary = "";
        String password = "";
        String username = "";

        ResultSet str, str2, str3;
        try{
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");

            //person info ...
            Statement st = con.createStatement();
            str = st.executeQuery("select * from person where id = '" + employee.getId() + "'");
            while(str.next()){
                Date df = str.getDate("birthdate");
                dataDate = df.toString();
                bld = str.getString("blood_type");
                String gnd = str.getString("gender");;
                male = (gnd.equalsIgnoreCase("male")) ;
                phone = str.getString("phone_number");
                email = str.getString("email");

            }
            //employee info .........
            Statement ee = con.createStatement();
            str3 = ee.executeQuery("select * from employee where id = '" + employee.getId() + "'");
            while (str3.next()){
                Date df = str3.getDate("start_date");
                startDate = df.toString();
                salary = str3.getString("salary");
                username = str3.getString("username");
                password = str3.getString("password");
                unq_id = Integer.toString(str3.getInt("employee_id"));

                String tmp2 = str3.getString("rank");
                superviser = (tmp2.equalsIgnoreCase("supervisor"));

                String tmp3 = str3.getString("shift");
                morning = tmp3.equalsIgnoreCase("morning");

            }
            con.close();

        }catch (SQLException exception){
            throw new RuntimeException(exception);
        }

        LocalDate ld1 = LocalDate.parse(dataDate);
        LocalDate ld2 = LocalDate.parse(startDate);

        scene1.getBirthdate().setValue(ld1);
        scene1.getStartdate().setValue(ld2);
        scene1.getBirthdate().getEditor().setText(ld1.toString());
        scene1.getStartdate().getEditor().setText(ld2.toString());

        scene1.getfName().setText(names[0]); scene1.getsName().setText(names[1]); scene1.gettName().setText(names[2]); scene1.getlName().setText(names[3]);
        scene1.getId().setText(employee.getId());
        scene1.getMale().setSelected(male); scene1.getFemale().setSelected(!male);
        scene1.getMorning().setSelected(morning); scene1.getEvening().setSelected(!morning);
        scene1.getSuperviser().setSelected(superviser); scene1.getSupervisee().setSelected(!superviser);
        scene1.getCity().setText(addr[0]); scene1.getStreet().setText(tmp);
        scene1.getBloodtype().setValue(bld);
        scene1.getEmail().setText(email); scene1.getPhone().setText(phone);
        scene1.getSalary().setText(salary);
        scene1.getUsername().setText(username);
        scene1.getPassword().setText(password);









    }

    @FXML
    public void updateEmp2(ActionEvent event){
        //validation
        boolean nameValid = ( scene1.getfName().getText() != "" &&  scene1.getsName().getText() != "" && scene1.gettName().getText() != "" && scene1.getlName().getText() != "" ) ;

        boolean addressValid = ( !scene1.getCity().getText().isEmpty() && ! scene1.getStreet().getText().isEmpty() );
        boolean emailPhoneValid = ( !scene1.getEmail().getText().isEmpty() && !scene1.getPhone().getText().isEmpty() ) ;
        boolean birthdateValid = true;
        if ( scene1.getBirthdate().getValue() != null) {
            birthdateValid = ( !scene1.getBirthdate().getValue().toString().isEmpty() );
        }
        else { birthdateValid = false;}

        boolean startDateValid = true;
        if ( scene1.getStartdate().getValue() != null) {
            startDateValid = ( !scene1.getStartdate().getValue().toString().isEmpty() );
        }
        else { startDateValid = false;}

        boolean genderValid = ( scene1.getMale().isSelected() || scene1.getFemale().isSelected() );
        boolean wtValid = ( scene1.getMorning().isSelected() || scene1.getEvening().isSelected() );
        boolean rankValid = ( scene1.getSupervisee().isSelected() || scene1.getSuperviser().isSelected() );

        boolean salaryValid = true;
        char ch [] = scene1.getSalary().getText().toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (!Character.isDigit(ch[i])) {
                salaryValid= false;
                break;
            }
        }

        boolean phoneValid;
        char c2 [] = scene1.getPhone().getText().toCharArray();
        if( scene1.getPhone().getText().length() == 10 ) {
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


        boolean bloodValid = scene1.getBloodtype().getValue() != null;
        boolean passwordValid = scene1.getPassword().getText() != null;
        boolean usernameValid = scene1.getUsername().getText() != "";

        boolean result = nameValid && addressValid && emailPhoneValid && birthdateValid && genderValid && phoneValid && wtValid && bloodValid && rankValid && salaryValid && usernameValid && passwordValid;
        if (!result){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid input, please check the inserted data and try again");
            alert.setTitle("Invalid input");
            alert.show();
            return;
        }


        //------------ end of validation
        String fname = "'"+ scene1.getfName().getText() + "'";
        String sname = "'" + scene1.getsName().getText() + "'";
        String tname = "'" + scene1.gettName().getText() + "'" ;
        String lname  = "'" + scene1.getlName().getText() + "'";

        String id ="'"+  scene1.getId().getText() + "'";
        String phone = "'" + scene1.getPhone().getText() + "'";
        String gender =  (scene1.getMale().isSelected()) ? "'Male'" : "'Female'" ;
        String wt =  (scene1.getEvening().isSelected()) ? "'evening'" : "'morning'" ;
        String rank =  (scene1.getSupervisee().isSelected()) ? "'supervisee'" : "'supervisor'" ;
        String salary = "'" + scene1.getSalary().getText() + "'";
        String email = "'" + scene1.getEmail().getText() + "'";
        String birthdate = "'"+  scene1.getBirthdate().getValue().toString() + "'" ;
        String startdate = "'"+  scene1.getStartdate().getValue().toString() + "'" ;
        String bloodType = "'" + scene1.getBloodtype().getValue() + "'";
        String city = "'" + scene1.getCity().getText() + "'";   String street = "'" +  scene1.getStreet().getText() + "'";
        String username = "'" + scene1.getUsername().getText() + "'";
        String password = "'" + scene1.getPassword().getText() + "'";


        try{
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
            con.setAutoCommit(false);
            Statement st = con.createStatement();
            Statement f = con.createStatement();


            //checking the id , email, phone, username because the are unique values;
            boolean emailExist= false , phoneExist = false ,personExist = false, usernameExist = false;

            //checking the email before adding
            ResultSet s2 = f.executeQuery("select email from person where email = " + email);
            int i = 0 ;
            while(s2.next()) { i++; }
            if(i == 1){
                emailExist = true;
            }

            //checking the phone before adding
            ResultSet s3 = f.executeQuery("select phone_number from person where phone_number = " +phone);
            i = 0 ;
            while(s3.next()) { i++; }
            if(i == 1){
                phoneExist = true;
            }

            //checking the username before adding
            ResultSet s4 = f.executeQuery("select username from employee where username = " + username);
            i = 0 ;
            while(s4.next()) { i++; }
            if(i == 1){
                usernameExist = true;
            }

            //updating data of the person table
            //before adding check whether the person exists or not
            //updating person
            st.executeUpdate("update person set "+"first_name = " + fname+","+"second_name = " + sname+"," +"third_name = "+tname + "," + "last_name ="+lname + ", phone_number=" + phone + ", gender= " +gender +", email = "+email+", blood_type ="+bloodType +", city= " + city+ ", street = " + street + ",birthdate = to_date("+birthdate+",'yyyy/mm/dd')" + "where id = '" +emp.getId() +"'" );
            //updating employee
            Statement st2 = con.createStatement();
            st2.executeUpdate("update employee set "+" rank = " + rank + "," + "shift = " + wt + "," + "start_date = to_date(" + startdate + ",'yyyy/mm/dd'), salary = " + salary + ", username =" + username +", password = " + password);

            con.commit();
            con.close();

        }catch (SQLException exception){
            throw new RuntimeException(exception);
        }


        scene1.getfName().setText(""); scene1.getsName().setText(""); scene1.gettName().setText(""); scene1.getlName().setText("");
        scene1.getId().setText(""); scene1.getBirthdate().getEditor().clear();
        scene1.getMale().setSelected(false); scene1.getFemale().setSelected(false);
        scene1.getEmail().setText(""); scene1.getPhone().setText("");
        scene1.getCity().setText(""); scene1.getEmail().setText("");
        scene1.getStreet().setText("");
        scene1.getMale().setSelected(false); scene1.getFemale().setSelected(false);
        scene1.getStartdate().getEditor().clear();
        scene1.getMorning().setSelected(false); scene1.getEvening().setSelected(false);
        scene1.getSuperviser().setSelected(false); scene1.getSupervisee().setSelected(false);
        scene1.getBloodtype().setValue("");
        scene1.getSalary().setText("");
        scene1.getUsername().setText(""); scene1.getPassword().setText("");


        //end
        save.setVisible(false);
        add.setVisible(true);
        cancel.setVisible(false);
        up.setVisible(true);
        saveChanges.setVisible(false);

        if(! inEmployeePane.getChildren().isEmpty()){
            inEmployeePane.getChildren().remove(0);
        }
        inEmployeePane.getChildren().add(paneB);
    }


}
