package com.example.project286;

import io.github.palexdev.materialfx.controls.MFXTableView;
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
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import javax.swing.plaf.nimbus.State;

public class RecipientPaneHandler implements Initializable {
    EmployeeLogin emp;

    InRecipientBHandler bb;

    //in recipient-b.fxml================================
    @FXML
    private JFXButton currentRecipients;

    @FXML
    private JFXButton newRecipient;

    @FXML
    private AnchorPane rPane;

    @FXML
    AnchorPane recipientPane;

    @FXML
    private JFXButton bigSave;
    @FXML
    private JFXButton update;

    public AnchorPane getRecipientPane() {
        return recipientPane;
    }


    //local variables.......................................................................
    AnchorPane A;
    AnchorPane B;
    MFXLegacyTableView<Recipient> recipientTable;
    private JFXButton innerSave;
    InRecipientAHandler bigOne;

    private Recipient rec;
    //end of declarations

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initializing the pane A
        FXMLLoader rloaderA = new FXMLLoader(getClass().getResource("inRecipient-A.fxml"));
        try {
            Parent rootA = rloaderA.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        InRecipientAHandler inRecipientAHandler = rloaderA.getController();
        A = inRecipientAHandler.getPaneA();
        innerSave = inRecipientAHandler.getSave();
        bigOne = inRecipientAHandler;




        //initializing the pane B
        FXMLLoader loaderB = new FXMLLoader(getClass().getResource("inRecipient-B.fxml"));
        try {
            Parent rootB = loaderB.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        InRecipientBHandler inRecipientBHandler = loaderB.getController();
        B = inRecipientBHandler.getPaneB();
        recipientTable = inRecipientBHandler.getRecipients_table();
        bb = inRecipientBHandler;

        //setting the default..
        rPane.getChildren().add(A);

        //last
        update.setVisible(false);
        bigSave.setVisible(false);

    }

    public void toCurrentPage(ActionEvent event) {
        //deleting the old pane
        if(! rPane.getChildren().isEmpty()) {
            rPane.getChildren().remove(0);
        }
        //setting the new pane..
        rPane.getChildren().add(B);


        //styling the buttons..
        currentRecipients.setStyle("-fx-border-width: 0px 0px 2px 0px;" +
                "-fx-border-color:white");
        newRecipient.setStyle("-fx-border-width: 0px 0px 0px 0px");

        //last
        update.setVisible(true);
        newRecipient.setText("New Recipient");

        bigSave.setVisible(false);
        innerSave.setVisible(true);

        bb.emp  =emp;


        //adding data to table
        ObservableList<Recipient> recipients = FXCollections.observableArrayList();
        ResultSet rst;

        //importing data from table
        try{
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
            Statement st = con.createStatement();
            Statement st2 = con.createStatement();


            rst = st.executeQuery("select recipient_id, recipient.id, first_name, second_name, third_name, last_name, city, street, blood_type, phone_number, email, recieved, needed_member, REGISTRATION_DATE, PRECEDENCE FROM person, recipient where recipient.id = person.id");
            String unq_id = "";
            while(rst.next()){
                Recipient rec = new Recipient();

                String fname = rst.getString("first_name");
                String sname = rst.getString("second_name");
                String tname = rst.getString("third_name");
                String lname = rst.getString("last_name");
                rec.setName(fname+" "+sname+" "+tname+" "+lname+" ");

                rec.setId(rst.getString("id"));

                String city = rst.getString("city");
                String street  = rst.getString("street");
                rec.setAddress(city+", "+street);
                rec.setBloodType(rst.getString("blood_type"));
                rec.setPhone(rst.getString("phone_number"));
                rec.setEmail(rst.getString("email"));
                rec.setNeededMember(rst.getString("needed_member"));

                String date [] = rst.getString("registration_date").split(" ");

                rec.setRegistrationDate(date[0]);


                rec.setPrecedence(rst.getString("precedence"));
                rec.setRecieved(rst.getString("recieved"));
                rec.setRecipient_id(rst.getString("recipient_id"));

                unq_id = Integer.toString(rst.getInt("recipient_id"));

                recipients.add(rec);
            }

            bb.recipients = recipients;
        recipientTable.setItems(recipients);
        con.close();

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }

    }


    public void toNewPage(ActionEvent event) throws IOException{
        //deleting the old pane
        if(! rPane.getChildren().isEmpty()) {
            rPane.getChildren().remove(0);
        }
        //setting the new pane
        rPane.getChildren().add(A);

        //styling the buttons..
        newRecipient.setStyle("-fx-border-width: 0px 0px 2px 0px;" +
                "-fx-border-color:white");
        currentRecipients.setStyle("-fx-border-width: 0px 0px 0px 0px");


        //last
        update.setVisible(false);


        bigOne.emp = emp;


    }

    @FXML
    void toUpdate(ActionEvent event){
        //checking if there is a recipient selected
        Recipient recipient = recipientTable.getSelectionModel().getSelectedItem();
        rec = recipient;
        if (recipient == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a recipient to update");
            alert.setHeaderText("No recipient selected");
            alert.setTitle("Error");
            alert.show();
            return;
        }

        //switching the scenes
        if (!rPane.getChildren().isEmpty()){
            rPane.getChildren().remove(0);
        }

        //setting the new pane
        rPane.getChildren().add(A);




        //setting the fields
        String []names = recipient.getName().split(" ");
        String []address = recipient.getAddress().split(",");
        String tmp = address[1].trim();

        String bld = "";
        String dataDate = "";
        boolean male = false;
        String phone;
        String email;
        String recieved;
        String precedence = "";
        String registrationDate = "";
        String needed_mem = "";

        ResultSet str, str2, str3;
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");

            //person info----------------------------
            Statement st = con.createStatement();
            str = st.executeQuery("select * from person where id = '" + recipient.getId() + "'");
            while(str.next()){
                Date df = str.getDate("birthdate");
                dataDate = df.toString();
                String gnd = str.getString("gender");
                male = (gnd.equalsIgnoreCase("male"));
                phone = str.getString("PHONE_NUMBER");
                email = str.getString("email");
                bld = str.getString("blood_type");

            }

            //recipient info--------------
            String unq_id = "";
            Statement dd = con.createStatement();
            str3 = dd.executeQuery("select * from recipient where id = '"+ recipient.getId()+"'");
            while(str3.next()){
                recieved = str3.getString("recieved");
                precedence = str3.getString("precedence");
                Date df = str3.getDate("REGISTRATION_DATE");

                String df2 = str3.getString("REGISTRATION_DATE");


                needed_mem = str3.getString("needed_member");
                registrationDate = df.toString();
                unq_id = Integer.toString(str3.getInt("recipient_id"));
            }

            //diseases info----------------
            Statement st2 = con.createStatement();
            str2 = st2.executeQuery("select disease_name, infection from recipient_diseases where recipient_id = " + unq_id);
            while(str2.next()){
                String dName = str2.getString("disease_name");
                String dRes = str2.getString("infection");
                boolean inf = false;
                if (dRes.equalsIgnoreCase("yes")){
                    inf = true;
                }
                switch (dName){
                    case "hard_diseases" :   bigOne.scene2.getHardDiseases().setSelected(inf); break;
                    case "c_mental_issues" : bigOne.scene2.getMentalIssues().setSelected(inf);
                }
            }
            
            con.close();
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        
        LocalDate ld = LocalDate.parse(dataDate);
        bigOne.scene1.getBirthdate().setValue(ld);
        bigOne.scene1.getBirthdate().getEditor().setText(ld.toString());

        LocalDate ld2 = LocalDate.parse(registrationDate);
        bigOne.scene2.getRegDate().setValue(ld2);
        bigOne.scene2.getRegDate().getEditor().setText(registrationDate.toString());
        
        //first page data
        bigOne.scene1.getfName().setText(names[0]); bigOne.scene1.getsName().setText(names[1]); bigOne.scene1.gettName().setText(names[2]); bigOne.scene1.getlName().setText(names[3]);
        bigOne.scene1.getId().setText(recipient.getId());
        bigOne.scene1.getMale().setSelected(male); bigOne.scene1.getFemale().setSelected(!male);
        bigOne.scene1.getEmail().setText(recipient.getEmail()); bigOne.scene1.getPhone().setText(recipient.getPhone());
        bigOne.scene1.getCity().setText(address[0]);
        bigOne.scene1.getStreet().setText(tmp);
        
        //2nd page data
        bigOne.scene2.getBloodType().setValue(bld);
        if (precedence.equalsIgnoreCase("High")){bigOne.scene2.gethPriority().setSelected(true);}
        else if (precedence.equalsIgnoreCase("Medium")){bigOne.scene2.getmPriority().setSelected(true);}
        else {bigOne.scene2.getlPriority().setSelected(true);}

        

        //3rd page data
        bigOne.toPage3(new ActionEvent());


        String organs[] = recipient.getOrgan().split(",",6);
        if(! bigOne.scene3.getLabLiver().getStylesheets().isEmpty()) {
            bigOne.scene3.getLabLiver().getStylesheets().remove(0);
        }
        bigOne.scene3.getLabLiver().getStylesheets().add("labelred.css");

        if(! bigOne.scene3.getLabHeart().getStylesheets().isEmpty()) {
            bigOne.scene3.getLabHeart().getStylesheets().remove(0);
        }
        bigOne.scene3.getLabHeart().getStylesheets().add("labelred.css");

        if(! bigOne.scene3.getLabRetina().getStylesheets().isEmpty()) {
            bigOne.scene3.getLabRetina().getStylesheets().remove(0);
        }
        bigOne.scene3.getLabRetina().getStylesheets().add("labelred.css");

        if(! bigOne.scene3.getLabKidney().getStylesheets().isEmpty()) {
            bigOne.scene3.getLabKidney().getStylesheets().remove(0);
        }
        bigOne.scene3.getLabKidney().getStylesheets().add("labelred.css");

        if(! bigOne.scene3.getLabLungs().getStylesheets().isEmpty()) {
            bigOne.scene3.getLabLungs().getStylesheets().remove(0);
        }
        bigOne.scene3.getLabLungs().getStylesheets().add("labelred.css");

        for (int i=0;i< organs.length;i++) {
            if(organs[i].equalsIgnoreCase("Liver")) {
                if(! bigOne.scene3.getLabLiver().getStylesheets().isEmpty()) {
                    bigOne.scene3.getLabLiver().getStylesheets().remove(0);
                }
                bigOne.scene3.getLabLiver().getStylesheets().add("labelgreen.css");
            }
            else if(organs[i].equalsIgnoreCase("heart")) {
                if(! bigOne.scene3.getLabHeart().getStylesheets().isEmpty()) {
                    bigOne.scene3.getLabHeart().getStylesheets().remove(0);
                }
                bigOne.scene3.getLabHeart().getStylesheets().add("labelgreen.css");
            }
            else if(organs[i].equalsIgnoreCase("Retina")) {
                if(! bigOne.scene3.getLabRetina().getStylesheets().isEmpty()) {
                    bigOne.scene3.getLabRetina().getStylesheets().remove(0);
                }
                bigOne.scene3.getLabRetina().getStylesheets().add("labelgreen.css");
            }
            else if(organs[i].equalsIgnoreCase("Kidney")) {
                if(! bigOne.scene3.getLabKidney().getStylesheets().isEmpty()) {
                    bigOne.scene3.getLabKidney().getStylesheets().remove(0);
                }
                bigOne.scene3.getLabKidney().getStylesheets().add("labelgreen.css");
            }
            else if(organs[i].equalsIgnoreCase("lungs")) {
                if(! bigOne.scene3.getLabLungs().getStylesheets().isEmpty()) {
                    bigOne.scene3.getLabLungs().getStylesheets().remove(0);
                }
                bigOne.scene3.getLabLungs().getStylesheets().add("labelgreen.css");
            }
        }


        bigOne.toPage1(new ActionEvent());

        //styling the buttons..
        newRecipient.setStyle("-fx-border-width: 0px 0px 2px 0px;" +
                "-fx-border-color: white");
        currentRecipients.setStyle("-fx-border-width: 0px 0px 0px 0px");
        update.setVisible(false);
        newRecipient.setText("Update Recipient");
        //the updating code....................


        bigSave.setVisible(true);
        innerSave.setVisible(false);
    }


    @FXML
    void saveUpdate(ActionEvent event){
        //validation
        boolean nameValid = ( bigOne.scene1.getfName().getText() != "" &&  bigOne.scene1.getsName().getText() != "" && bigOne.scene1.gettName().getText() != "" && bigOne.scene1.getlName().getText() != "" ) ;

        boolean idValid;
        char c [] = bigOne.scene1.getId().getText().toCharArray();
        if( bigOne.scene1.getId().getText().length() == 9 ) {
            for (int i = 0; i < c.length; i++) {
                if (!Character.isDigit(c[i])) {
                    idValid = false;
                    break;
                }


            }
            idValid = true;
        }
        else { idValid = false;}

        boolean addressValid = ( !bigOne.scene1.getCity().getText().isEmpty() && ! bigOne.scene1.getStreet().getText().isEmpty() );
        boolean emailPhoneValid = ( !bigOne.scene1.getEmail().getText().isEmpty() && !bigOne.scene1.getPhone().getText().isEmpty() ) ;
        boolean birthdateValid = true;
        if ( bigOne.scene1.getBirthdate().getValue() != null) {
            birthdateValid = ( !bigOne.scene1.getBirthdate().getValue().toString().isEmpty() );
        }
        else { birthdateValid = false;}

        boolean genderValid = ( bigOne.scene1.getMale().isSelected() || bigOne.scene1.getFemale().isSelected() );
        boolean phoneValid;
        char c2 [] = bigOne.scene1.getPhone().getText().toCharArray();
        if( bigOne.scene1.getPhone().getText().length() == 10 ) {
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

        boolean diseasesValid = (bigOne.scene2.getHardDiseases().isSelected() && bigOne.scene2.getMentalIssues().isSelected());
        boolean bloodValid = bigOne.scene2.getBloodType().getValue() != null;

        boolean regDateValid = true;
        if ( bigOne.scene1.getBirthdate().getValue() != null) {
            regDateValid = ( !bigOne.scene1.getBirthdate().getValue().toString().isEmpty() );
        }
        else { regDateValid = false;}
        boolean precedenceValid = ( bigOne.scene2.gethPriority().isSelected() || bigOne.scene2.getmPriority().isSelected() || bigOne.scene2.getlPriority().isSelected() );



        //checking if there is members selected
        boolean boolLiver = (bigOne.scene3.getLabLiver().getStylesheets().get(0) == "labelgreen.css");
        boolean boolHeart = (bigOne.scene3.getLabHeart().getStylesheets().get(0) == "labelgreen.css");
        boolean boolKidney = (bigOne.scene3.getLabKidney().getStylesheets().get(0) == "labelgreen.css");
        boolean boolLungs = (bigOne.scene3.getLabLungs().getStylesheets().get(0) == "labelgreen.css");
        boolean boolRetina = (bigOne.scene3.getLabRetina().getStylesheets().get(0) == "labelgreen.css");


        boolean result = nameValid && idValid && addressValid && emailPhoneValid && birthdateValid && genderValid && phoneValid && diseasesValid && bloodValid  && (boolLiver || boolHeart || boolKidney || boolRetina || boolLungs) && regDateValid && precedenceValid;
        if(! result) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid input, please check the inserted data and try again");
            alert.setTitle("Invalid input");
            alert.show();
            return;
        }


        //--------------------------------------end of validation
        String fname = "'"+ bigOne.scene1.getfName().getText() + "'"; String sname = "'" + bigOne.scene1.getsName().getText() + "'"; String tname = "'" + bigOne.scene1.gettName().getText() + "'" ; String lname  = "'" + bigOne.scene1.getlName().getText() + "'";
        String id ="'"+  bigOne.scene1.getId().getText() + "'";  String phone = "'" + bigOne.scene1.getPhone().getText() + "'" ;
        //gender
        String gender =  (bigOne.scene1.getMale().isSelected()) ? "'Male'" : "'Female'" ;
        String email = "'" + bigOne.scene1.getEmail().getText() + "'";
        String birthdate = "'"+  bigOne.scene1.getBirthdate().getValue().toString() + "'" ;
        String bloodType = "'" + bigOne.scene2.getBloodType().getValue() + "'";
        String city = "'" + bigOne.scene1.getCity().getText() + "'";   String street = "'" +  bigOne.scene1.getStreet().getText() + "'";

        String regdate = "'" + bigOne.scene2.getRegDate().getValue().toString() + "'";
        String precedence = "";
        if (bigOne.scene2.gethPriority().isSelected()){ precedence = "'" + "High" + "'";}
        else if (bigOne.scene2.getmPriority().isSelected()){ precedence = "'" + "Medium" + "'";}
        else if (bigOne.scene2.getlPriority().isSelected()){precedence = "'" + "Low" + "'";}


        String mental_issues = (bigOne.scene2.getMentalIssues().isSelected()) ? "'yes'" : "'no'" ;
        String hard_diseases = (bigOne.scene2.getHardDiseases().isSelected()) ? "'yes'" : "'no'" ;

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "aydigg", "123456");
            con.setAutoCommit(false);

            Statement st = con.createStatement();

            //updating person
            st.executeUpdate("update person set "+"first_name = " + fname+","+"second_name = " + sname+"," +"third_name = "+tname + "," + "last_name ="+lname + ", phone_number=" + phone + ", gender= " +gender +", email = "+email+", blood_type ="+bloodType +", city= " + city+ ", street = " + street + ",birthdate = to_date("+birthdate+",'yyyy/mm/dd')" + "where id = '" +rec.getId() +"'" );

            //updating recipient
            //Statement st2 = con.createStatement();


            //getting the unique id of the dpnor
            Statement q = con.createStatement();
            ResultSet rr = q.executeQuery("Select recipient_id from recipient where id = '" + rec.getId() + "'");
            String unq_id = "";
            while(rr.next()){
                unq_id = Integer.toString(rr.getInt("recipient_id"));
            }
            //--------------


            //updating the diseases...
            Statement st9 = con.createStatement();
            Statement st10 = con.createStatement();

            st9.executeUpdate("update  recipient_diseases set infection = " +  mental_issues +" where recipient_id = " + unq_id + " and disease_name = 'c_mental_issues'" );
            st10.executeUpdate("update  recipient_diseases set infection = " +  hard_diseases +" where recipient_id = " + unq_id + " and disease_name = 'hard_diseases'" );


            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
            String time = simpleDateFormat.format(new java.util.Date());

            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy/MM/dd");
            String date = simpleDateFormat2.format(new java.util.Date()) ;
            st.executeUpdate("insert into operation (operation_number,operation_type,operation_time, person_type,operation_date,employee_id) VALUES (operation_counter.nextval, 'updated recipient' , '" + time + "' , 'donor' , to_date('" + date +  "', 'yyyy/mm/dd') , " + emp.getEmployee_id() + ")");
            st.executeUpdate("insert into operation_recipient(operation_number,recipient_id) values(operation_counter.currval," + unq_id  + ")");


            con.commit();
            con.close();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }



        //restating the fields to default state
        bigOne.scene1.getfName().setText(""); bigOne.scene1.getsName().setText(""); bigOne.scene1.gettName().setText(""); bigOne.scene1.getlName().setText("");
        bigOne.scene1.getId().setText(""); bigOne.scene1.getBirthdate().getEditor().clear();
        bigOne.scene1.getMale().setSelected(false); bigOne.scene1.getFemale().setSelected(false);
        bigOne.scene1.getEmail().setText(""); bigOne.scene1.getPhone().setText("");
        bigOne.scene1.getCity().setText(""); bigOne.scene1.getEmail().setText("");
        bigOne.scene1.getStreet().setText("");

        bigOne.scene2.getHardDiseases().setSelected(false);
        bigOne.scene2.getMentalIssues().setSelected(false);
        bigOne.scene2.getBloodType().setValue("");
        bigOne.scene2.gethPriority().setSelected(false);
        bigOne.scene2.getmPriority().setSelected(false);
        bigOne.scene2.getlPriority().setSelected(false);


        //styling the buttons
        newRecipient.setText("New Recipient");
        bigSave.setVisible(false);
        innerSave.setVisible(true);

    }


}
