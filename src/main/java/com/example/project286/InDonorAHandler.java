package com.example.project286;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class InDonorAHandler implements Initializable {

    //inDonor-A.fxml--------------------------------------
    EmployeeLogin emp;
    @FXML
    private AnchorPane paneA;

    @FXML
    AnchorPane inPaneA;
    @FXML
    private ToggleGroup threepages;
    @FXML
    private ToggleButton page1 , page2 , page3;
    @FXML
    JFXButton save, next , back;
    //panes-----------------------------------------
    int currentPage = 1;
    AnchorPane pane[] = new AnchorPane[3];
    Button member[] = new Button[5];

    Label mem[] = new Label[5];

    public AnchorPane getPaneA() {
        return paneA;
    }
    //---------------------------------------------------

    public JFXButton getSave() {
        return save;
    }
    //-----------------------------------------------------
    InDonorA1Handler scene1 ;
    InDonorA2Handler scene2 ;
    InDonorA3Handler scene3 ;

    private boolean fieldsFilled;



    //methods----------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initialinzing the first pane
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("inDonor-A-1.fxml"));
        try {
            Parent root1 = loader1.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InDonorA1Handler inDonorA1Handler = loader1.getController();
        pane[0] = inDonorA1Handler.getPaneA1();
        //moving values...
        scene1 = inDonorA1Handler;



        //initializing the second pane..

        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("inDonor-A-2.fxml"));
        try {
            Parent root2 = loader2.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        InDonorA2Handler inDonorA2Handler = loader2.getController();
        pane[1] = inDonorA2Handler.getPaneA2();

        //moving values...
        scene2 = inDonorA2Handler;


        //initializing the 3rd pane.
        FXMLLoader loader3 = new FXMLLoader(getClass().getResource("inDonor-A-3.fxml"));
        try {
            Parent root3= loader3.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        InDonorA3Handler inDonorA3Handler = loader3.getController();
        pane[2] = inDonorA3Handler.getPaneA3();
        //moving values...
        scene3 = inDonorA3Handler;




        //setting the default
        inPaneA.getChildren().add(pane[0]);

        //buttons initial state..
        save.setDisable(true);
        back.setDisable(true);

        member[0] = scene3.getBtnRetina();
        member[1]  = scene3.getBtnLungs();
        member[2] = scene3.getBtnHeart();
        member[3] = scene3.getBtnLiver();
        member[4] = scene3.getBtnKidney();

        mem[0] = scene3.getLabRetina();
        mem[1]  = scene3.getLabLungs();
        mem[2] = scene3.getLabHeart();
        mem[3] = scene3.getLabLiver();
        mem[4] = scene3.getLabKidney();



    }

    public void toPage1 (ActionEvent e) {
        if(! inPaneA.getChildren().isEmpty()) {
            inPaneA.getChildren().remove(0);
        }
        inPaneA.getChildren().add(pane[0]);
        //setting the buttons..
        back.setDisable(true);
        next.setDisable(false);
        save.setDisable(true);

        currentPage = 1;
    }
    public void toPage2 (ActionEvent e) {
        if(! inPaneA.getChildren().isEmpty()) {
            inPaneA.getChildren().remove(0);
        }
        inPaneA.getChildren().add(pane[1]);
        //setting the buttons..
        back.setDisable(false);
        next.setDisable(false);
        save.setDisable(true);

        currentPage = 2;
    }
    public void toPage3(ActionEvent e) {
        if(! inPaneA.getChildren().isEmpty()) {
            inPaneA.getChildren().remove(0);
        }
        inPaneA.getChildren().add(pane[2]);
        //setting the buttons..
        back.setDisable(false);
        next.setDisable(true);
        save.setDisable(false);

        currentPage = 3;

        //styling the organs buttons

        boolean notValid =  ( !scene2.getMentalIssues().isSelected() || ! scene2.getHardDiseases().isSelected()  );
        if( !scene2.getMentalIssues().isSelected() || ! scene2.getHardDiseases().isSelected()) {
            for(int i = 0;i<5;i++) {
                if (!member[i].getStylesheets().isEmpty())
                    member[i].getStylesheets().remove(0);
                    member[i].getStylesheets().add("donor-recipientButtons-Red.css");

                if (!mem[i].getStylesheets().isEmpty())
                    mem[i].getStylesheets().remove(0);
                     mem[i].getStylesheets().add("labelred.css");



            }
                return;
        }
        else{
            for(int i = 0;i<5;i++) {
                if (!member[i].getStylesheets().isEmpty())
                    member[i].getStylesheets().remove(0);
                    member[i].getStylesheets().add("donor-recipientButtons.css");

                if (!mem[i].getStylesheets().isEmpty())
                    mem[i].getStylesheets().remove(0);
                    mem[i].getStylesheets().add("labelgreen.css");

            }

        }

        if(scene2.getDrinkingAlcohol().isSelected()  || scene2.getLiverDiseases().isSelected() ){
            if (! scene3.getBtnLiver().getStylesheets().isEmpty())
                scene3.getBtnLiver().getStylesheets().remove(0);
                scene3.getBtnLiver().getStylesheets().add("donor-recipientButtons-Red.css");

            if (!mem[3].getStylesheets().isEmpty())
                mem[3].getStylesheets().remove(0);
            mem[3].getStylesheets().add("labelred.css");



        }

        if(scene2.getHeartDiseases().isSelected() || ( scene2.getDiabetes().isSelected() && scene2.getBloodPressure().isSelected() ) ){
            if (! scene3.getBtnHeart().getStylesheets().isEmpty())
                scene3.getBtnHeart().getStylesheets().remove(0);
                scene3.getBtnHeart().getStylesheets().add("donor-recipientButtons-Red.css");

            if (!mem[2].getStylesheets().isEmpty())
                mem[2].getStylesheets().remove(0);
            mem[2].getStylesheets().add("labelred.css");

        }

        if( scene2.getLiverDiseases().isSelected() || ( scene2.getBloodPressure().isSelected() && scene2.getDiabetes().isSelected() )){
            if (! scene3.getBtnKidney().getStylesheets().isEmpty())
                scene3.getBtnKidney().getStylesheets().remove(0);
            scene3.getBtnKidney().getStylesheets().add("donor-recipientButtons-Red.css");

            if (!mem[4].getStylesheets().isEmpty())
                mem[4].getStylesheets().remove(0);
            mem[4].getStylesheets().add("labelred.css");

        }
        if( scene2.getSmoking().isSelected() || scene2.getHeartDiseases().isSelected() || scene2.getLiverDiseases().isSelected() && scene2.getBloodPressure().isSelected() ) {
            if (! scene3.getBtnLungs().getStylesheets().isEmpty())
                scene3.getBtnLungs().getStylesheets().remove(0);
                scene3.getBtnLungs().getStylesheets().add("donor-recipientButtons-Red.css");

            if (!mem[1].getStylesheets().isEmpty())
                mem[1].getStylesheets().remove(0);
            mem[1].getStylesheets().add("labelred.css");
        }













    }

    public void nextPage(ActionEvent e) throws IOException {
        if(! inPaneA.getChildren().isEmpty()){
            inPaneA.getChildren().remove(0);
        }
        if(currentPage<=2) {
            inPaneA.getChildren().add(pane[currentPage]);
            //setting the toggle buttons..
            ToggleButton t[] = {page1,page2,page3} ;
            t[currentPage].setSelected(true);
            //enabling the back button..
            save.setDisable(false);
            if(currentPage == 1) {
                back.setDisable(false);
                save.setDisable(true);
            }
            //the save button..


            currentPage++;
        }
        if(currentPage == 3){
            next.setDisable(true);

            //styling the organs buttons

            if( !scene2.getMentalIssues().isSelected() || ! scene2.getHardDiseases().isSelected()) {
                for(int i = 0;i<5;i++) {
                    if (!member[i].getStylesheets().isEmpty())
                        member[i].getStylesheets().remove(0);
                    member[i].getStylesheets().add("donor-recipientButtons-Red.css");

                    if (!mem[i].getStylesheets().isEmpty())
                        mem[i].getStylesheets().remove(0);
                    mem[i].getStylesheets().add("labelred.css");



                }
                return;
            }
            else{
                for(int i = 0;i<5;i++) {
                    if (!member[i].getStylesheets().isEmpty())
                        member[i].getStylesheets().remove(0);
                    member[i].getStylesheets().add("donor-recipientButtons.css");

                    if (!mem[i].getStylesheets().isEmpty())
                        mem[i].getStylesheets().remove(0);
                    mem[i].getStylesheets().add("labelgreen.css");

                }

            }

            if(scene2.getDrinkingAlcohol().isSelected()  || scene2.getLiverDiseases().isSelected() ){
                if (! scene3.getBtnLiver().getStylesheets().isEmpty())
                    scene3.getBtnLiver().getStylesheets().remove(0);
                scene3.getBtnLiver().getStylesheets().add("donor-recipientButtons-Red.css");

                if (!mem[3].getStylesheets().isEmpty())
                    mem[3].getStylesheets().remove(0);
                mem[3].getStylesheets().add("labelred.css");



            }

            if(scene2.getHeartDiseases().isSelected() || ( scene2.getDiabetes().isSelected() && scene2.getBloodPressure().isSelected() ) ){
                if (! scene3.getBtnHeart().getStylesheets().isEmpty())
                    scene3.getBtnHeart().getStylesheets().remove(0);
                scene3.getBtnHeart().getStylesheets().add("donor-recipientButtons-Red.css");

                if (!mem[2].getStylesheets().isEmpty())
                    mem[2].getStylesheets().remove(0);
                mem[2].getStylesheets().add("labelred.css");

            }

            if( scene2.getLiverDiseases().isSelected() || ( scene2.getBloodPressure().isSelected() && scene2.getDiabetes().isSelected() )){
                if (! scene3.getBtnKidney().getStylesheets().isEmpty())
                    scene3.getBtnKidney().getStylesheets().remove(0);
                scene3.getBtnKidney().getStylesheets().add("donor-recipientButtons-Red.css");

                if (!mem[4].getStylesheets().isEmpty())
                    mem[4].getStylesheets().remove(0);
                mem[4].getStylesheets().add("labelred.css");

            }
            if( scene2.getSmoking().isSelected() || scene2.getHeartDiseases().isSelected() || scene2.getLiverDiseases().isSelected() && scene2.getBloodPressure().isSelected() ) {
                if (! scene3.getBtnLungs().getStylesheets().isEmpty())
                    scene3.getBtnLungs().getStylesheets().remove(0);
                scene3.getBtnLungs().getStylesheets().add("donor-recipientButtons-Red.css");

                if (!mem[1].getStylesheets().isEmpty())
                    mem[1].getStylesheets().remove(0);
                mem[1].getStylesheets().add("labelred.css");
            }

        }

    }

    public void prePage(ActionEvent e) {
        if(! inPaneA.getChildren().isEmpty()){
            inPaneA.getChildren().remove(0);
        }
        if(currentPage >= 2) {
            //switching the page..
            inPaneA.getChildren().add(pane[currentPage-2]);
            //setting the toggle buttons..
            ToggleButton t[] = {page1,page2,page3} ;
            t[currentPage-2].setSelected(true);
            //enabling the next button..
            save.setDisable(true);
            if(currentPage == 3) {
                next.setDisable(false);

            }
            currentPage--;
        }
        if(currentPage  == 1) {
            back.setDisable(true);
        }

    }


    @FXML
    void commitSave(ActionEvent event) {
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

        boolean genderValid = ( scene1.getMale().isSelected() || scene1.getFemale().isSelected() );
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
        boolean diseasesValid = (scene2.getHardDiseases().isSelected() && scene2.getMentalIssues().isSelected());
        boolean bloodValid = scene2.getBloodType().getValue() != null;
        boolean wNameValid = ( ! scene3.getwFName().getText().isEmpty() &&  ! scene3.getwSName().getText().isEmpty() && ! scene3.getwTName().getText().isEmpty() && ! scene3.getwLName().getText().isEmpty() );
        boolean wPhoneValid ;
        char c3 [] = scene3.getwPhone().getText().toCharArray();
        if( scene3.getwPhone().getText().length() == 10 ) {
            for (int i = 0; i < c.length; i++) {
                if (!Character.isDigit(c[i])) {
                    wPhoneValid= false;
                    break;
                }


            }
            wPhoneValid = true;
        }
        else{
            wPhoneValid = false;
        }

        //checking if there is members selected
        boolean boolLiver = (scene3.getLabLiver().getStylesheets().get(0) == "labelgreen.css");
        boolean boolHeart = (scene3.getLabHeart().getStylesheets().get(0) == "labelgreen.css");
        boolean boolKidney = (scene3.getLabKidney().getStylesheets().get(0) == "labelgreen.css");
        boolean boolLungs = (scene3.getLabLungs().getStylesheets().get(0) == "labelgreen.css");
        boolean boolRetina = (scene3.getLabRetina().getStylesheets().get(0) == "labelgreen.css");





        boolean result = nameValid && idValid && addressValid && emailPhoneValid && birthdateValid && genderValid && phoneValid && diseasesValid && bloodValid && wNameValid && wPhoneValid && (boolLiver || boolHeart || boolKidney || boolRetina || boolLungs);
        if(! result) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid input, please check the inserted data and try again");
            alert.setTitle("Invalid input");
            alert.show();
            return;
        }


        //--------------------------------------------------------------------end of validation



        String fname = "'"+ scene1.getfName().getText() + "'"; String sname = "'" + scene1.getsName().getText() + "'"; String tname = "'" + scene1.gettName().getText() + "'" ; String lname  = "'" + scene1.getlName().getText() + "'";
        String id ="'"+  scene1.getId().getText() + "'";  String phone = "'" + scene1.getPhone().getText() + "'";
        //gender
        String gender =  (scene1.getMale().isSelected()) ? "'Male'" : "'Female'" ;
        String email = "'" + scene1.getEmail().getText() + "'";
        String birthdate = "'"+  scene1.getBirthdate().getValue().toString() + "'" ;
        String bloodType = "'" + scene2.getBloodType().getValue() + "'";
        String city = "'" + scene1.getCity().getText() + "'";   String street = "'" +  scene1.getStreet().getText() + "'";

        String wfname = "'" + scene3.getwFName().getText() + "'"; String wsname = "'" + scene3.getwSName().getText() + "'" ;  String wtname = "'"+ scene3.getwTName().getText()+ "'"; String wlname = "'" + scene3.getwLName().getText()+ "'";
        String wphone = "'" + scene3.getwPhone().getText() + "'" ;
        //diseases section;
        String liver = (scene2.getLiverDiseases().isSelected()) ? "'yes'" : "'no'" ;
        String heart = (scene2.getHeartDiseases().isSelected()) ? "'yes'" : "'no'" ;
        String diabetes = (scene2.getDiabetes().isSelected()) ? "'yes'" : "'no'" ;
        String pressure = (scene2.getBloodPressure().isSelected()) ? "'yes'" : "'no'" ;
        String smoking = (scene2.getSmoking().isSelected()) ? "'yes'" : "'no'" ;
        String drinking = (scene2.getDrinkingAlcohol().isSelected()) ? "'yes'" : "'no'" ;

        String mental_issues = (scene2.getMentalIssues().isSelected()) ? "'yes'" : "'no'" ;
        String hard_diseases = (scene2.getHardDiseases().isSelected()) ? "'yes'" : "'no'" ;



        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
            Statement st = con.createStatement();
            Statement f  = con.createStatement();

            //checking the entered ID , EMAIL , PHONE  because they are unique values.
            boolean emailExist= false , phoneExist = false ,personExist = false;

            //checking the id before adding
            ResultSet s = f.executeQuery("select id from donor where id = " +id);
            int i = 0 ;
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

            //end of checking the unique data-------------------------------------------------------------------------------------------


            //inserting data of the person table.
            //before adding check whether the person exists or not
            ResultSet s4 = f.executeQuery("select id from person where id = " +id);
            i = 0 ;
            while(s4.next()) { i++; }
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



            //inserting the data of the donor..
            Statement st2 = con.createStatement();
            st2.executeUpdate("insert into donor (donor_id,id,wfirst_name,wsecond_name,wthird_name,wlast_name,wphone_number,state,donated) values(donor_counter.nextval "+ "," + id +"," + wfname + "," + wsname + "," + wtname + "," + wlname + ","  + wphone + "," + "'alive'" + "," + "'no' )"  );

            //inserting the data of the disease (page 2 of add donors)
            Statement st3 = con.createStatement();
            Statement st4= con.createStatement();
            Statement st5 = con.createStatement();
            Statement st6 = con.createStatement();
            Statement st7 = con.createStatement();
            Statement st8 = con.createStatement();
            Statement st9 = con.createStatement();
            Statement st10 = con.createStatement();
            st3.executeUpdate("insert into donor_diseases (donor_id,disease_name,infection) VALUES(" + "donor_counter.currval" + ", 'liver_diseases' ,"  + liver + ")" );
            st4.executeUpdate("insert into donor_diseases (donor_id,disease_name,infection) VALUES(" + "donor_counter.currval" + ", 'heart_diseases' ,"  + heart + ")" );
            st5.executeUpdate("insert into donor_diseases (donor_id,disease_name,infection) VALUES(" + "donor_counter.currval" + ", 'diabetes' ,"  + diabetes + ")" );
            st6.executeUpdate("insert into donor_diseases (donor_id,disease_name,infection) VALUES(" + "donor_counter.currval" + ", 'blood_pressure' ,"  + pressure + ")" );
            st7.executeUpdate("insert into donor_diseases (donor_id,disease_name,infection) VALUES(" + "donor_counter.currval" + ", 'smoking' ,"  + smoking + ")" );
            st8.executeUpdate("insert into donor_diseases (donor_id,disease_name,infection) VALUES(" + "donor_counter.currval" + ", 'drinking_alcohol' ,"  + drinking + ")" );
            st9.executeUpdate("insert into donor_diseases (donor_id,disease_name,infection) VALUES(" + "donor_counter.currval" + ", 'c_mental_issues' ,"  + mental_issues + ")" );
            st10.executeUpdate("insert into donor_diseases (donor_id,disease_name,infection) VALUES(" + "donor_counter.currval" + ", 'hard_diseases' ,"  + hard_diseases + ")" );
//
//
//            //inserting the data of the chosen organs..
            if (boolLiver) {
                Statement st11 = con.createStatement();
                st11.executeUpdate("insert into donated_organs (id,organ_name)   values(" + "donor_counter.currval" + "," + "'Liver' )" );
            }
            if (boolHeart) {
                Statement st12 = con.createStatement();
                st12.executeUpdate("insert into donated_organs (id,organ_name)   values(" + "donor_counter.currval" + "," + "'Heart' )" );
            }
            if (boolKidney) {
                Statement st13 = con.createStatement();
                st13.executeUpdate("insert into donated_organs (id,organ_name)   values(" +"donor_counter.currval" + "," + "'Kidney' )" );
            }
            if (boolLungs) {
                Statement st14 = con.createStatement();
                st14.executeUpdate("insert into donated_organs (id,organ_name)   values(" + "donor_counter.currval" + "," + "'Lungs' )" );
            }
            if (boolRetina) {
                Statement st15 = con.createStatement();
                st15.executeUpdate("insert into donated_organs (id,organ_name)   values(" + "donor_counter.currval" + "," + "'Retina' )" );
            }






            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
            String time = simpleDateFormat.format(new java.util.Date());

            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy/MM/dd");
            String date = simpleDateFormat2.format(new java.util.Date()) ;
            st2.executeUpdate("insert into operation (operation_number,operation_type,operation_time, person_type,operation_date,employee_id) VALUES (operation_counter.nextval, 'adding donor', '" + time + "' , 'donor' , to_date('" + date +  "', 'yyyy/mm/dd') , " + emp.getEmployee_id() + ")");
            st.executeUpdate("insert into operation_donor (operation_number,donor_id) values(operation_counter.currval, donor_counter.currval)");


            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        scene1.getfName().setText(""); scene1.getsName().setText(""); scene1.gettName().setText(""); scene1.getlName().setText("");
        scene1.getId().setText(""); scene1.getBirthdate().getEditor().clear();
        scene1.getMale().setSelected(false); scene1.getFemale().setSelected(false);
        scene1.getEmail().setText(""); scene1.getPhone().setText("");
        scene1.getCity().setText(""); scene1.getEmail().setText("");
        scene1.getStreet().setText("");

        scene2.getHardDiseases().setSelected(false);
        scene2.getMentalIssues().setSelected(false);
        scene2.getSmoking().setSelected(false);
        scene2.getDrinkingAlcohol().setSelected(false);
        scene2.getDiabetes().setSelected(false);
        scene2.getBloodPressure().setSelected(false);
        scene2.getHeartDiseases().setSelected(false);
        scene2.getLiverDiseases().setSelected(false);

        scene2.getBloodType().setValue("");

        scene3.getwFName().setText("");
        scene3.getwSName().setText("");
        scene3.getwTName().setText("");
        scene3.getwLName().setText("");
        scene3.getwPhone().setText("");


        this.toPage1(new ActionEvent());
    }

}
