package com.example.project286;

import com.jfoenix.controls.JFXButton;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import oracle.jdbc.OracleDriver;
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainFrameHandler implements Initializable {

    @FXML
    private JFXButton lifeCount;
    public JFXButton getLifeCount() {
        return lifeCount;
    }
    EmployeeLogin emp;
    TableView<History> historyTable;
    @FXML
    private Label lblName;

    public Label getLblName() {
        return lblName;
    }

    public Label getLblUser() {
        return lblUser;
    }
    public void setLblName(Label lblName) {
        this.lblName = lblName;
    }

    public void setLblUser(Label lblUser) {
        this.lblUser = lblUser;
    }

    TestPaneHandler e1;
    SurgeryMainPaneHandler e2;
    EmployeePaneHandler e3;
    HospitalPaneHandler e4;
    HistoryPaneHandler e5;





    @FXML
    private Label lblUser;
    @FXML
    private Button donors;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button recipients;
    @FXML
    private Button employees;
    @FXML
    private Button surgeries;
    @FXML
    private Button schedule;
    @FXML
    private Button hospitals;
    @FXML
    private Button history;
    @FXML
    private Button tests;
    AnchorPane donorPane;
    AnchorPane recipientPane;
    AnchorPane employeePane;
    AnchorPane surgeryPane;

    MFXScrollPane schedulePane;
    AnchorPane hospitalPane;
    AnchorPane historyPane;
    AnchorPane testPane;
    TableView<Employee> employeeTable;
    TableView<Hospital> hospitalTable;
    TableView<Doctor> doctorTable;


    Button buttons[] = new Button[7];
    Button buttons2[] = new Button[2];

    TableView<DonorSchedule> donorTable;
    TableView <SurgeryData> surgeriesTable;

    TableView<Test> testTable;

    RecipientPaneHandler r1;
    DonorPaneHandler d1;

    @FXML
    private MFXButton btnHospitalReport;
    @FXML
    private MFXButton btnSurgeryReport;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Donor-pane.fxml"));
        try {
            Parent root1 = loader1.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DonorPaneHandler donorPaneHandler = loader1.getController();
        donorPane =  donorPaneHandler.getDonorPane();
        d1 = donorPaneHandler;



        //The Recipients pane..
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("Recipient-pane.fxml"));
        try {
            Parent root2 = loader2.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        RecipientPaneHandler recipientPaneHandler = loader2.getController();
        recipientPane =  recipientPaneHandler.getRecipientPane();
        r1 = recipientPaneHandler;


        //the Employee pane
        FXMLLoader loader3 = new FXMLLoader(getClass().getResource("Employee-pane.fxml"));
        try {
            Parent root3= loader3.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        EmployeePaneHandler employeePaneHandler = loader3.getController();
        employeePane = employeePaneHandler.getEmployeePane();
        employeeTable = employeePaneHandler.getEmployeeTable();
        e3 = employeePaneHandler;


        buttons[0] = donors; buttons[1] = recipients ;

        //the Surgeries pane
        FXMLLoader loader4 = new FXMLLoader(getClass().getResource("Surgery-mainPane.fxml"));
        try {
            Parent root4= loader4.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SurgeryMainPaneHandler surgeryMainPaneHandler = loader4.getController();
        surgeryPane = surgeryMainPaneHandler.getMain();
        surgeriesTable = surgeryMainPaneHandler.getSurgeryTable();
        e2 =surgeryMainPaneHandler;

        buttons[3] = surgeries;

        //the schedule pane
        FXMLLoader loader5 = new FXMLLoader(getClass().getResource("Schedule-pane.fxml"));
        try {
            Parent root5 = loader5.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SchedulePaneHandler schedulePaneHandler = loader5.getController();
        schedulePane = schedulePaneHandler.getSchedule();
        donorTable = schedulePaneHandler.getDonorTable();


        buttons[4] = schedule;

        //the hospitals pane
        FXMLLoader loader6 = new FXMLLoader(getClass().getResource("Hospital-pane.fxml"));
        try {
            Parent root6 = loader6.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HospitalPaneHandler hospitalPaneHandler = loader6.getController();
        hospitalPane = hospitalPaneHandler.getHosPane();
        hospitalTable = hospitalPaneHandler.getHospitals_table();
        doctorTable = hospitalPaneHandler.getDoctors_table();
        e4 = hospitalPaneHandler;

        buttons[5] = hospitals;

        //the history pane..
        FXMLLoader loader7 = new FXMLLoader(getClass().getResource("History-pane.fxml"));
        try {
            Parent root7 = loader7.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HistoryPaneHandler historyPaneHandler = loader7.getController();
        historyPane = historyPaneHandler.getPane();
        e5 = historyPaneHandler;
        historyTable = historyPaneHandler.getHistories_table();

        //the tests pane
        FXMLLoader loader8 = new FXMLLoader(getClass().getResource("Test-pane.fxml"));
        try {
            Parent root8 = loader8.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        TestPaneHandler testPaneHandler = loader8.getController();
        testPane = testPaneHandler.getPane();
        buttons[2] = tests;
        testTable = testPaneHandler.getTests_table();
        e1 = testPaneHandler;


        buttons2[0]  =history;
        buttons2[1] = employees;

    }

    @FXML
    void toDonors(ActionEvent event) {

        if(! mainPane.getChildren().isEmpty()){
            mainPane.getChildren().remove(0);
        }
        mainPane.getChildren().add(donorPane);

        donors.getStylesheets().remove(0);
        donors.getStylesheets().add("main-frame-buttons2.css");
        for(int i = 0 ;i<6;i++) {
            if(i != 0) {
                buttons[i].getStylesheets().remove(0);
                buttons[i].getStylesheets().add("main-frame-buttons.css");
            }
        }
        for(int i = 0;i<2;i++) {

                if(!buttons2[i].getStylesheets().isEmpty())
                    buttons2[i].getStylesheets().remove(0);
                buttons2[i].getStylesheets().add("super2.css");


        }

        d1.emp = emp;
        d1.bigOne.emp  =emp;
        d1.bh.emp = emp;

    }

    @FXML
    void toRecipients(ActionEvent event) {
        if(! mainPane.getChildren().isEmpty()){
            mainPane.getChildren().remove(0);
        }
        mainPane.getChildren().add(recipientPane);

        recipients.getStylesheets().remove(0);
        recipients.getStylesheets().add("main-frame-buttons2.css");
        for(int i = 0 ;i<6;i++) {
            if(i != 1) {
                buttons[i].getStylesheets().remove(0);
                buttons[i].getStylesheets().add("main-frame-buttons.css");
            }

        }
        for(int i = 0;i<2;i++) {

            if(!buttons2[i].getStylesheets().isEmpty())
                buttons2[i].getStylesheets().remove(0);
            buttons2[i].getStylesheets().add("super2.css");


        }

        r1.emp = emp;
        r1.bb.emp = emp;
        r1.bigOne.emp = emp;

    }
    @FXML
    void toEmployees(ActionEvent event) {
        if(! mainPane.getChildren().isEmpty()){
            mainPane.getChildren().remove(0);
        }
        mainPane.getChildren().add(employeePane);
        for(int i = 0 ;i<6;i++) {

            buttons[i].getStylesheets().remove(0);
            buttons[i].getStylesheets().add("main-frame-buttons.css");
        }

        if(!employees.getStylesheets().isEmpty())
        employees.getStylesheets().remove(0);
        employees.getStylesheets().add("super.css");
        for(int i = 0;i<2;i++) {
            if(i!=1) {
                if(!buttons2[i].getStylesheets().isEmpty())
                    buttons2[i].getStylesheets().remove(0);
                buttons2[i].getStylesheets().add("super2.css");
            }


        }




        //adding the table's data
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        ResultSet rst;
        //getting hte data from the database
        try{
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
            Statement st = con.createStatement();

            rst = st.executeQuery("select employee_id, employee.id, first_name, second_name, third_name, last_name, city, street, blood_type, phone_number, email, gender, birthdate, start_date, shift, rank, salary, username, password from person, employee where employee.id = person.id");
            String unq_id = "";
            while(rst.next()){
                Employee emp = new Employee();

                String fName = rst.getString("first_name");
                String sName = rst.getString("second_name");
                String tName = rst.getString("third_name");
                String lName = rst.getString("last_name");

                emp.setName(fName + " " + sName + " " + tName + " " + lName + " ");
                emp.setId(rst.getString("id"));

                String city = rst.getString("city");
                String street = rst.getString("street");
                emp.setAddress(city + ", " + street);
                emp.setBlood_type(rst.getString("blood_type"));
                emp.setPhone(rst.getString("phone_number"));
                emp.setEmail(rst.getString("email"));
                unq_id = Integer.toString(rst.getInt("employee_id"));
                emp.setRank(rst.getString("rank"));
                emp.setStartDate(rst.getString("start_date"));
                emp.setWorkingTime(rst.getString("shift"));
                emp.setSalary(rst.getString("salary"));
                emp.setUsername(rst.getString("username"));
                emp.setPassword(rst.getString("password"));



                employees.add(emp);
            }

            e3.bh.employees = employees;
            employeeTable.setItems(employees);
            con.close();

        }catch (SQLException e){
            throw new RuntimeException(e) ;
        }
    }

    @FXML
    void toSurgeries(ActionEvent event) {

        if(! mainPane.getChildren().isEmpty()){
            mainPane.getChildren().remove(0);
        }
        mainPane.getChildren().add(surgeryPane);

        surgeries.getStylesheets().remove(0);
        surgeries.getStylesheets().add("main-frame-buttons2.css");
        for(int i = 0 ;i<6;i++) {
            if(i != 3) {
                buttons[i].getStylesheets().remove(0);
                buttons[i].getStylesheets().add("main-frame-buttons.css");
            }

        }
        for(int i = 0;i<2;i++) {

            if(!buttons2[i].getStylesheets().isEmpty())
                buttons2[i].getStylesheets().remove(0);
            buttons2[i].getStylesheets().add("super2.css");


        }



        //filling the data..
        ObservableList<SurgeryData> surgeries = FXCollections.observableArrayList();
        ResultSet rst;
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
            con.setAutoCommit(false);
            Statement st = con.createStatement();
            rst = st.executeQuery("select * from surgery");

            while(rst.next()) {
                SurgeryData sur = new SurgeryData();

                sur.setSurgeryNumber(Integer.toString(rst.getInt("surgery_number")));
                sur.setResult(rst.getString("result"));
                sur.setOrgan(rst.getString("organ"));
                sur.setHospitalName(rst.getString("hospital_name"));
                sur.setDonorId(rst.getString("donor_id"));
                sur.setRecipientId(rst.getString("recipient_id"));
                sur.setDoctorName(rst.getString("doctor_name"));
                String tmpDate = rst.getString("surgery_date");
                String tmp [] = tmpDate.split(" ");
                sur.setSurgeryDate(tmp[0]);

                surgeries.add(sur);
            }



            con.commit();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        surgeriesTable.setItems(surgeries);
        e2.ser2.surgeries = surgeries;


    }

    @FXML
    void toSchedule(ActionEvent event) {
        if(! mainPane.getChildren().isEmpty()){
            mainPane.getChildren().remove(0);
        }
        mainPane.getChildren().add(schedulePane);

        schedule.getStylesheets().remove(0);
        schedule.getStylesheets().add("main-frame-buttons2.css");

        for(int i = 0 ;i<6;i++) {
            if(i != 4) {
                buttons[i].getStylesheets().remove(0);
                buttons[i].getStylesheets().add("main-frame-buttons.css");
            }

        }
        for(int i = 0;i<2;i++) {

            if(!buttons2[i].getStylesheets().isEmpty())
                buttons2[i].getStylesheets().remove(0);
            buttons2[i].getStylesheets().add("super2.css");


        }
        //filling the table of the donor


        ObservableList<DonorSchedule> donors = FXCollections.observableArrayList();
        ResultSet rst;
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
            con.setAutoCommit(false);
            Statement st = con.createStatement();
            Statement st2 = con.createStatement();
            rst = st.executeQuery("select donor_id ,donor.id,first_name,second_name,third_name,last_name,city,street,blood_type,gender,donated, wfirst_name,wsecond_Name,wthird_name,wlast_name,wphone_number from person, donor where donor.id = person.id and state = 'dead' and donated = 'no'");
            String unq_id= "";
            while(rst.next()) {
                DonorSchedule don = new DonorSchedule();

                String fname = rst.getString("first_name");
                String sname = rst.getString("second_name");
                String tname = rst.getString("third_name");
                String lname = rst.getString("last_name");
                don.setName(fname + " " + sname + " " + tname + " " + lname + " ");
                String wfname = rst.getString("wfirst_name");
                String wsname = rst.getString("wsecond_name");
                String wtname = rst.getString("wthird_name");
                String wlname = rst.getString("wlast_name");
                don.setWitnessName(wfname + " " + wsname + " " + wtname + " " + wlname);
                don.setId(rst.getString("id"));


                String city = rst.getString("city");
                String street = rst.getString("street");
                don.setAddress(city + ", " + street);
                don.setBloodType(rst.getString("blood_type"));
                don.setGender(rst.getString("gender"));
                don.setDonated(rst.getString("donated"));
                don.setWitnessPhone(rst.getString("wphone_number"));
                unq_id = Integer.toString(rst.getInt("donor_id"));
                don.setDonor_id(unq_id);

                ResultSet rst2 = st2.executeQuery("select organ_name from donated_organs where id = "+unq_id);
                String organs = "";
                while (rst2.next()) {
                    organs += rst2.getString("organ_name") + ",";

                }
                don.setOrgans(organs);




                donors.add(don);
            }

            donorTable.setItems(donors);
            con.commit();
            con.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }





    }

    @FXML
    void toHospitals(ActionEvent event) {
        if(! mainPane.getChildren().isEmpty()){
            mainPane.getChildren().remove(0);
        }
        mainPane.getChildren().add(hospitalPane);
        hospitals.getStylesheets().remove(0);
        hospitals.getStylesheets().add("main-frame-buttons2.css");
        for(int i = 0 ;i<6;i++) {
            if(i != 5) {
                buttons[i].getStylesheets().remove(0);
                buttons[i].getStylesheets().add("main-frame-buttons.css");
            }

        }
        for(int i = 0;i<2;i++) {

            if(!buttons2[i].getStylesheets().isEmpty())
                buttons2[i].getStylesheets().remove(0);
            buttons2[i].getStylesheets().add("super2.css");


        }

        //adding the hospital table's data
        ObservableList<Hospital> hospitals = FXCollections.observableArrayList();
        ResultSet rst;
        //getting hte data from the database
        try{
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
            Statement st = con.createStatement();

            rst = st.executeQuery("select * from hospital where valid = 'yes'");
            String unq_id = "";
            while(rst.next()){
                Hospital hos = new Hospital();

                String name = rst.getString("hospital_name");
                //String []names = name.split(" ");

                String city = rst.getString("city");
                String street = rst.getString("street");
                String phone = rst.getString("phone_number");

                hos.setName(name);
                hos.setAddress(city + ", " + street);
                hos.setPhone(phone);


                hospitals.add(hos);
            }
            hospitalTable.setItems(hospitals);
            con.close();

        }catch (SQLException e){
            throw new RuntimeException(e) ;
        }
        e4.hospitals = hospitals;



        //adding the doctor table's data
        ObservableList<Doctor> doctors = FXCollections.observableArrayList();
        ResultSet rst1;
        //getting hte data from the database
        try{
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
            Statement st = con.createStatement();

            rst1 = st.executeQuery("select * from doctor,hospital where valid = 'yes' and hospital.hospital_name = doctor.hospital_name");
            while(rst1.next()){
                Doctor doc = new Doctor();
                doc.setDoctor_id(Integer.toString(rst1.getInt("doctor_id")));
                String fName = rst1.getString("FIRST_NAME");
                String sName = rst1.getString("SECOND_NAME");
                String tName = rst1.getString("THIRD_NAME");
                String lName = rst1.getString("LAST_NAME");
                String hName = rst1.getString("HOSPITAL_NAME");

                String name = fName + " " + sName + " " + tName + " " + lName;
                doc.setName(name);
                doc.setHosp(hName);
                System.out.println(hName);

                doctors.add(doc);
            }
            doctorTable.setItems(doctors);
            con.close();

        }catch (SQLException e){
            throw new RuntimeException(e) ;
        }

        e4.doctors = doctors;

    }

    @FXML
    void toHistory(ActionEvent event) {
        if(! mainPane.getChildren().isEmpty()){
            mainPane.getChildren().remove(0);
        }
        mainPane.getChildren().add(historyPane);
        for(int i = 0 ;i<6;i++) {

                buttons[i].getStylesheets().remove(0);
                buttons[i].getStylesheets().add("main-frame-buttons.css");
        }


        if(!history.getStylesheets().isEmpty())
        history.getStylesheets().remove(0);
        history.getStylesheets().add("super.css");
        for(int i = 0;i<2;i++) {
            if(i!=0) {
                if(!buttons2[i].getStylesheets().isEmpty())
                buttons2[i].getStylesheets().remove(0);
                buttons2[i].getStylesheets().add("super2.css");
            }


        }





        //adding the table's data
        ObservableList<History> histories = FXCollections.observableArrayList();
        ObservableList<History> partial = FXCollections.observableArrayList();

        ResultSet rst ,rst2,rst3;
        //getting hte data from the database
        try{
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
            Statement st = con.createStatement();
            Statement st2 = con.createStatement();
            Statement st3 = con.createStatement();

            rst = st.executeQuery("select * from operation,operation_recipient where operation.operation_number = operation_recipient.operation_number");
            while(rst.next()){
                History his = new History();

                String number = Integer.toString(rst.getInt("operation_number")) ;
                String type = rst.getString("operation_type");
                String recipient = Integer.toString(rst.getInt("recipient_id"));

                String employee = Integer.toString(rst.getInt("employee_id"));

                his.setRecipient(recipient);
                his.setEmployee(employee);
                his.setNumber(number);
                his.setType(type);
                his.setTime(rst.getString("operation_time"));
                String tmp[] = rst.getString("operation_date").split(" ");

                his.setDate(tmp[0]);
                his.setDonor("");

                histories.add(his);
            }



            rst2 = st2.executeQuery("select * from operation,operation_donor where operation.operation_number = operation_donor.operation_number");


            String unq_id = "";

            while(rst2.next()){
                History his = new History();

                String number = Integer.toString(rst2.getInt("operation_number")) ;
                String type = rst2.getString("operation_type");
                String donor = Integer.toString(rst2.getInt("donor_id"));

                String employee = Integer.toString(rst2.getInt("employee_id"));

                his.setDonor(donor);
                his.setEmployee(employee);
                his.setNumber(number);
                his.setType(type);
                his.setTime(rst2.getString("operation_time"));
                String tmp[] = rst2.getString("operation_date").split(" ");

                his.setDate(tmp[0]);
               his.setRecipient("");

                histories.add(his);
            }


            rst3 = st3.executeQuery("select * from operation");
            while (rst3.next()) {
                History his = new History();

                String number = Integer.toString(rst3.getInt("operation_number")) ;
                String type = rst3.getString("operation_type");
                String employee = Integer.toString(rst3.getInt("employee_id"));

                his.setDonor("");
                his.setEmployee(employee);
                his.setNumber(number);
                his.setType(type);
                his.setTime(rst3.getString("operation_time"));
                String tmp[] = rst3.getString("operation_date").split(" ");

                his.setDate(tmp[0]);
                his.setRecipient("");


                partial.add(his);

            }

            for (int i = 0 ; i< partial.size() ; i++) {
                boolean check = true;
                for (int k = 0 ; k< histories.size() ; k++) {
                    if(partial.get(i).getNumber().equalsIgnoreCase(histories.get(k).getNumber() )            ) {
                        check =false;


                    }


                }
                if(check)
                histories.add(partial.get(i));



            }


            con.close();

        }catch (SQLException e){
            throw new RuntimeException(e) ;
        }
        historyTable.setItems(histories);
        historyTable.refresh();
        e5.histories = histories;


    }


    @FXML
    void toTests(ActionEvent e) {
        if(! mainPane.getChildren().isEmpty()){
            mainPane.getChildren().remove(0);
        }
        mainPane.getChildren().add(testPane);
        tests.getStylesheets().remove(0);
        tests.getStylesheets().add("main-frame-buttons2.css");
        for(int i = 0 ;i<6;i++) {
            if(i != 2) {
                buttons[i].getStylesheets().remove(0);
                buttons[i].getStylesheets().add("main-frame-buttons.css");
            }

        }
        for(int i = 0;i<2;i++) {

            if(!buttons2[i].getStylesheets().isEmpty())
                buttons2[i].getStylesheets().remove(0);
            buttons2[i].getStylesheets().add("super2.css");


        }

        //adding the test table's data
        ObservableList<Test> tests = FXCollections.observableArrayList();
        ResultSet rst;
        //getting hte data from the database
        try{
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
            Statement st = con.createStatement();

            rst = st.executeQuery("select * from test");
            while(rst.next()){
                Test test = new Test();

                test.setNumber(Integer.toString(rst.getInt("test_number") ));
                test.setName(rst.getString("test_name"));
                test.setResult(rst.getString("test_result"));
                test.setDonor(Integer.toString(rst.getInt("donor_id")));
                test.setRecipient(Integer.toString(rst.getInt("recipient_id")));


                tests.add(test);
            }
            testTable.setItems(tests);
            con.close();

        }catch (SQLException ex){
            throw new RuntimeException(ex) ;
        }

        e1.tests = tests;





    }

    @FXML
    void recount(ActionEvent event) {
        String life = lifeCount.getText();

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
            Statement st = con.createStatement();
            ResultSet r  =st.executeQuery("select count(*) from surgery where result = 'Succeeded'");
            while (r.next()) {
                life = r.getString("count(*)");
            }

            con.close();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        lifeCount.setText(life);


    }

    public void superStuff(boolean isSuper) {
        if(!isSuper) {
            employees.setVisible(false);
            history.setVisible(false);

        }
        else{
            employees.setVisible(true);
            history.setVisible(true);

        }


    }


    @FXML
    public void hospitalReport(ActionEvent event){
        try {
            OracleDataSource odc = new OracleDataSource();
            odc.setUser("aydigg");
            odc.setPassword("123456");
            odc.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            Connection con = odc.getConnection();
            InputStream input = new FileInputStream(new File("C:\\Users\\HP\\Desktop\\Project267\\Project286\\HospitalBB.jrxml"));
            JasperDesign jd = JRXmlLoader.load(input);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, con);

            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jp));
            frame.pack();
            frame.setVisible(true);


        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void surgeryReport(ActionEvent event){
        try {
            OracleDataSource odc = new OracleDataSource();
            odc.setUser("aydigg");
            odc.setPassword("123456");
            odc.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            Connection con = odc.getConnection();
            InputStream input = new FileInputStream(new File("C:\\Users\\HP\\Desktop\\Project267\\Project286\\surgeryBB1.jrxml"));
            JasperDesign jd = JRXmlLoader.load(input);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, con);

            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jp));
            frame.pack();
            frame.setVisible(true);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}
