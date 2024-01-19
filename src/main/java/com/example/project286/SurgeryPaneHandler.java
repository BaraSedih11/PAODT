package com.example.project286;

import com.jfoenix.controls.JFXTextArea;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SurgeryPaneHandler implements Initializable {
    @FXML
    private AnchorPane datePane;

    @FXML
    private AnchorPane doctorPane;

    @FXML
    private AnchorPane donorPane;

    @FXML
    private AnchorPane hospitalPane;

    @FXML
    private AnchorPane organPane;

    @FXML
    private AnchorPane recipientPane;

    @FXML
    private AnchorPane paneA;

    public AnchorPane getPaneA() {
        return paneA;
    }
    @FXML
    private AnchorPane inSurgeryPane;

    @FXML
    private JFXTextArea txt1;

    @FXML
    private JFXTextArea txt2;

    @FXML
    private JFXTextArea txt3;

    @FXML
    private JFXTextArea txt4;

    @FXML
    private JFXTextArea txt5;

    @FXML
    private JFXTextArea txt6;

    public void setTxt1(JFXTextArea txt1) {
        this.txt1 = txt1;
    }

    public void setTxt2(JFXTextArea txt2) {
        this.txt2 = txt2;
    }

    public void setTxt3(JFXTextArea txt3) {
        this.txt3 = txt3;
    }

    public void setTxt4(JFXTextArea txt4) {
        this.txt4 = txt4;
    }

    public void setTxt5(JFXTextArea txt5) {
        this.txt5 = txt5;
    }

    public void setTxt6(JFXTextArea txt6) {
        this.txt6 = txt6;
    }

    public JFXTextArea getTxt1() {
        return txt1;
    }

    AnchorPane pane1 ,pane2,pane3, pane4,pane5,pane6;

    public JFXTextArea getTxt2() {
        return txt2;
    }

    public JFXTextArea getTxt3() {
        return txt3;
    }

    public JFXTextArea getTxt4() {
        return txt4;
    }

    public JFXTextArea getTxt5() {
        return txt5;
    }

    public JFXTextArea getTxt6() {
        return txt6;
    }

    InSurgery1Handler c1; InSurgery2Handler c2; InSurgery3Handler c3; InSurgery4Handler c4; InSurgery5Handler c5; InSurgery6Handler c6;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //n111
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("inSurgery-1.fxml"));
        try {
            Parent root1 = loader1.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InSurgery1Handler inSurgery1Handler = loader1.getController();
        pane1 = inSurgery1Handler.getPane1();
        c1 = inSurgery1Handler;

        //n222
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("inSurgery-2.fxml"));
        try {
            Parent root2 = loader2.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InSurgery2Handler inSurgery2Handler = loader2.getController();
        pane2 = inSurgery2Handler.getPane3();
        c2 = inSurgery2Handler;


        //n33
        FXMLLoader loader3 = new FXMLLoader(getClass().getResource("inSurgery-3.fxml"));
        try {
            Parent root3 = loader3.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InSurgery3Handler inSurgery3Handler =loader3.getController();
        pane3 = inSurgery3Handler.getPane3();
        c3 = inSurgery3Handler;
        //n444
        FXMLLoader loader4 = new FXMLLoader(getClass().getResource("inSurgery-4.fxml"));
        try {
            Parent root4 = loader4.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InSurgery4Handler inSurgery4Handler =loader4.getController();
        pane4 = inSurgery4Handler.getPane4();
        c4 = inSurgery4Handler;

        //n555
        FXMLLoader loader5 = new FXMLLoader(getClass().getResource("inSurgery-5.fxml"));
        try {
            Parent root5 = loader5.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InSurgery5Handler inSurgery5Handler =loader5.getController();
        pane5 = inSurgery5Handler.getPane5();
        c5 = inSurgery5Handler;




        //n666
        FXMLLoader loader6 = new FXMLLoader(getClass().getResource("inSurgery-6.fxml"));
        try {
            Parent root6 = loader6.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InSurgery6Handler inSurgery6Handler = loader6.getController();
        pane6 = inSurgery6Handler.getPane6();
        c6 = inSurgery6Handler;

        //connection to the database in order to fill the comboboxes

        String hospitalValues = ""; String donorValues = "";
        ResultSet rst1, rst2 ;
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
            con.setAutoCommit(false);
            Statement st = con.createStatement();

            rst1= st.executeQuery("select hospital_name from hospital");
            while(rst1.next()) {
                String hos = rst1.getString("hospital_name");
                hospitalValues += hos + "#";
            }

            rst2 = st.executeQuery("select donor.donor_id from donor,recipient_donor where donor.donor_id = recipient_donor.donor_id  and state = 'dead' and donated = 'no'");
            while(rst2.next()) {
                String don = Integer.toString(rst2.getInt("donor_id"));
                donorValues += don + "#";
            }


            con.commit();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //filling the comboboxes
        String hosValues []= hospitalValues.split("#");
        c1.getHospital().getItems().addAll(hosValues);

        String donValues [] = donorValues.split("#");
        c4.getDonors().getItems().addAll(donValues);
    }

    @FXML
    void toHospital(MouseEvent event) {
        if(!inSurgeryPane.getChildren().isEmpty()) {
            inSurgeryPane.getChildren().remove(0);

        }

        inSurgeryPane.getChildren().add(pane1);

        String hospitalValues = "";
        ResultSet rst1;
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
            con.setAutoCommit(false);
            Statement st = con.createStatement();

            rst1= st.executeQuery("select hospital_name from hospital where valid = 'yes'");
            while(rst1.next()) {
                String hos = rst1.getString("hospital_name");
                hospitalValues += hos + "#";
            }


            con.commit();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //filling the comboboxes
        String hosValues []= hospitalValues.split("#");
        c1.getHospital().getItems().clear();
        c1.getHospital().getItems().addAll(hosValues);

    }
    @FXML
    void toDate(MouseEvent event) {
            if(!inSurgeryPane.getChildren().isEmpty()) {
                inSurgeryPane.getChildren().remove(0);

            }

            inSurgeryPane.getChildren().add(pane3);

    }
    @FXML
    void toDoctor(MouseEvent event) {
        if(!inSurgeryPane.getChildren().isEmpty()) {
            inSurgeryPane.getChildren().remove(0);

        }
        inSurgeryPane.getChildren().add(pane2);

        //adding the data
        ResultSet rst;
        String doctors = "";
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
            con.setAutoCommit(false);
            Statement st = con.createStatement();


            if(c1.getHospital().getValue() == null){
                Alert alert  =new Alert(Alert.AlertType.ERROR);
                alert.setContentText("choose a hospital first");
                alert.setTitle("Invalid input");
                alert.show();

                c1.getHospital().getItems().clear();
                txt1.setText("");

                con.commit();
                con.close();
                return;
            }

            rst = st.executeQuery("select * from doctor where hospital_name = '" + c1.getHospital().getValue() + "'" ) ;
            while (rst.next()) {
                String fname = rst.getString("first_name");
                String sname = rst.getString("second_name");
                String tname = rst.getString("third_name");
                String lname = rst.getString("last_name");

                String docName =  fname+ " " + sname + " " + tname + " "  + lname;

                doctors += docName + "#";

            }


            con.commit();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String data [] = doctors.split("#");
        c2.getDoctors().getItems().clear();
        c2.getDoctors().getItems().addAll(data);





    }

    @FXML
    void toDonor(MouseEvent event) {
        if(!inSurgeryPane.getChildren().isEmpty()) {
            inSurgeryPane.getChildren().remove(0);

        }

        inSurgeryPane.getChildren().add(pane4);

         String donorValues = "";
        ResultSet rst2 ;
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
            con.setAutoCommit(false);
            Statement st = con.createStatement();


            rst2 = st.executeQuery("select distinct donor.donor_id from donor,recipient_donor where donor.donor_id = recipient_donor.donor_id  and state = 'dead' and donated = 'yes'");
            while(rst2.next()) {
                String don = Integer.toString(rst2.getInt("donor_id"));
                donorValues += don + "#";
            }


            con.commit();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //filling the combobox

        String donValues [] = donorValues.split("#");
        c4.getDonors().getItems().clear();
        c4.getDonors().getItems().addAll(donValues);



    }


    @FXML
    void toOrgan(MouseEvent event) {
        if(!inSurgeryPane.getChildren().isEmpty()) {
            inSurgeryPane.getChildren().remove(0);

        }

        inSurgeryPane.getChildren().add(pane5);
        //setting the organs data

        String organ = "" ;
        ResultSet rst;
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
            con.setAutoCommit(false);
            Statement st = con.createStatement();

            if(c4.getDonors().getValue() == null || c6.getRecipients().getValue() == null ){
                Alert alert  =new Alert(Alert.AlertType.ERROR);
                alert.setContentText("choose a donor and a recipient first");
                alert.setTitle("Invalid input");
                alert.show();
                c5.getOrgans().getItems().clear();
                txt5.setText("");

                con.commit();
                con.close();
                return;
            }

            rst = st.executeQuery("select organ from recipient_donor where donor_id = " + c4.getDonors().getValue() + " and recipient_id = " + c6.getRecipients().getValue());
            while(rst.next()) {
                String r = rst.getString("organ");
                organ = r;
            }

            con.commit();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        c5.getOrgans().getItems().clear();
        c5.getOrgans().getItems().add(organ);



    }

    @FXML
    void toRecipient(MouseEvent event) {
        if(!inSurgeryPane.getChildren().isEmpty()) {
            inSurgeryPane.getChildren().remove(0);

        }

        inSurgeryPane.getChildren().add(pane6);

        //setting the data

        String recipients = "" ;
        ResultSet rst;
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
            con.setAutoCommit(false);
            Statement st = con.createStatement();


            if(c4.getDonors().getValue() == null){
                Alert alert  =new Alert(Alert.AlertType.ERROR);
                alert.setContentText("choose a donor first");
                alert.setTitle("Invalid input");
                alert.show();
                c6.getRecipients().getItems().clear();
                txt6.setText("");

                con.commit();
                con.close();
                return;
            }

            rst = st.executeQuery("select recipient_id from recipient_donor where donor_id = '" + c4.getDonors().getValue() +"'" );
            while(rst.next()) {
                String r = rst.getString("recipient_id");
                recipients += r + "#";
            }
            con.commit();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String data []= recipients.split("#");

        c6.getRecipients().getItems().clear();
        c6.getRecipients().getItems().addAll(data);



    }


    @FXML
    void clicked(MouseEvent event) {
        String n = c4.getDonors().getValue();
        txt4.setText(n);
        txt1.setText(c1.getHospital().getValue());
        txt2.setText(c2.getDoctors().getValue());
        if(c3.getDate().getValue() != null)
        txt3.setText(c3.getDate().getValue().toString());
        txt5.setText(c5.getOrgans().getValue());
        txt6.setText(c6.getRecipients().getValue());
    }


}
