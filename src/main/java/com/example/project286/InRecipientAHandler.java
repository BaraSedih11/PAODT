package com.example.project286;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class InRecipientAHandler implements Initializable {
    @FXML
    AnchorPane inPaneA;
    @FXML
    private ToggleButton page1, page2, page3;

    @FXML
    private AnchorPane paneA;

    @FXML
    JFXButton save, next, back;

    @FXML
    private ToggleGroup threepages;

    //panes-----------------------------------------
    EmployeeLogin emp;
    int currentPage = 1;
    AnchorPane pane[] = new AnchorPane[3];
    Button[]member = new Button[5];
    Label[]mem = new Label[5];

    public AnchorPane getPaneA(){ return paneA; }

    public JFXButton getSave() {
        return save;
    }

    //--------------------

    InRecipientA1Handler scene1;
    InRecipientA2Handler scene2;
    InRecipientA3Handler scene3;
    private boolean filedFilled;


    //methods
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initialinzing the first pane
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("inRecipient-A-1.fxml"));
        try {
            Parent root1 = loader1.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InRecipientA1Handler inRecipientA1Handler = loader1.getController();
        pane[0] = inRecipientA1Handler.getPaneA1();

        //moving values...
        scene1 = inRecipientA1Handler;




        //initializing the second pane..

        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("inRecipient-A-2.fxml"));
        try {
            Parent root2 = loader2.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        InRecipientA2Handler inRecipientA2Handler = loader2.getController();
        pane[1] = inRecipientA2Handler.getPaneA2();

        //moving values
        scene2 = inRecipientA2Handler;



        //initializing the 3rd pane.
        FXMLLoader loader3 = new FXMLLoader(getClass().getResource("inRecipient-A-3.fxml"));
        try {
            Parent root3= loader3.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        InRecipientA3Handler inRecipientA3Handler = loader3.getController();
        pane[2] = inRecipientA3Handler.getPaneA3();

        //moving values...
        scene3 = inRecipientA3Handler;



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

    public void nextPage(ActionEvent event) {
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
        if(currentPage == 3) {
            next.setDisable(true);


            //styling the organs button

            if (!scene2.getMentalIssues().isSelected() || !scene2.getHardDiseases().isSelected()) {
                for (int i = 0; i < 5; i++) {
                    if (!member[i].getStylesheets().isEmpty())
                        member[i].getStylesheets().remove(0);
                    member[i].getStylesheets().add("donor-recipientButtons-Red.css");

                    if (!mem[i].getStylesheets().isEmpty())
                        mem[i].getStylesheets().remove(0);
                    mem[i].getStylesheets().add("labelred.css");

                }
                return;
            } else {
                for (int i = 0; i < 5; i++) {
                    if (!member[i].getStylesheets().isEmpty())
                        member[i].getStylesheets().remove(0);
                    member[i].getStylesheets().add("donor-recipientButtons.css");

                    if (!mem[i].getStylesheets().isEmpty())
                        mem[i].getStylesheets().remove(0);
                    mem[i].getStylesheets().add("labelgreen.css");

                }
            }


        }


    }

    public void prePage(ActionEvent event) {
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

    public void toPage1(ActionEvent event) {
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


    public void toPage2(ActionEvent event) {
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


    public void toPage3(ActionEvent event) {
        if (!inPaneA.getChildren().isEmpty()) {
            inPaneA.getChildren().remove(0);
        }
        inPaneA.getChildren().add(pane[2]);
        //setting the buttons..
        back.setDisable(false);
        next.setDisable(true);
        save.setDisable(false);

        currentPage = 3;


        //styling the organs buttons

        boolean notValid = (!scene2.getMentalIssues().isSelected() || scene2.getHardDiseases().isSelected());
        if (!scene2.getMentalIssues().isSelected() || !scene2.getHardDiseases().isSelected()){
            for (int i = 0; i < 5; i++) {
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
            for (int i = 0; i < 5; i++)
                if (!member[i].getStylesheets().isEmpty()) {
                    member[i].getStylesheets().remove(0);
                    member[i].getStylesheets().add("donor-recipientButtons.css");

                if (!mem[i].getStylesheets().isEmpty())
                    mem[i].getStylesheets().remove(0);
                    mem[i].getStylesheets().add("labelgreen.css");


            }
        }
    }


    @FXML
    void commitSave(ActionEvent event){
        //validation
        boolean nameValid = (scene1.getfName().getText() != "" && scene1.getsName().getText() != "" && scene1.gettName().getText() != "" && scene1.getlName().getText() != "");

        boolean idValid ;
        char []c = scene1.getId().getText().toCharArray();
        if (scene1.getId().getText().length() == 9) {
            for (int i = 0; i < c.length; i++) {
                if (!Character.isDigit(c[i])) {
                    idValid = false;
                    break;
                }
            }
            idValid = true;
        }
        else {
            idValid = false;
        }

        boolean addressValid = (!scene1.getCity().getText().isEmpty() && !scene1.getStreet().getText().isEmpty());
        boolean emailPhoneValid = (!scene1.getEmail().getText().isEmpty());
        boolean birthdateValid = true;

        if (scene1.getBirthdate().getValue() != null){
            birthdateValid = (!scene1.getBirthdate().getValue().toString().isEmpty());
        }
        else{
            birthdateValid = false;
        }
        boolean genderValid = (scene1.getMale().isSelected() || scene1.getFemale().isSelected());
        boolean phoneValid;
        char [] c2 = scene1.getPhone().getText().toCharArray();
        if (scene1.getPhone().getText().length() == 10){
            for (int i = 0 ; i < c.length ; i++){
                if (!Character.isDigit(c[i])){
                    phoneValid = false ;
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


        //checking if there is member selected
        boolean boolLiver = (scene3.getLabLiver().getStylesheets().get(0) == "labelgreen.css");
        boolean boolHeart = (scene3.getLabHeart().getStylesheets().get(0) == "labelgreen.css");
        boolean boolKidney = (scene3.getLabKidney().getStylesheets().get(0) == "labelgreen.css");
        boolean boolLungs = (scene3.getLabLungs().getStylesheets().get(0) == "labelgreen.css");
        boolean boolRetina = (scene3.getLabRetina().getStylesheets().get(0) == "labelgreen.css");

        boolean lastd[] = {boolKidney,boolHeart,boolRetina,boolLiver,boolLungs};



        //checking for the signing date validation
        boolean regDateValid = true;
        if ( scene2.getRegDate().getValue() != null) {
            regDateValid = ( !scene2.getRegDate().getValue().toString().isEmpty() );
        }
        else { regDateValid = false;}


        //checking for the priority validation
        boolean priorityValid = ( scene2.gethPriority().isSelected() || scene2.getmPriority().isSelected() || scene2.getlPriority().isSelected() );


        boolean result = nameValid && idValid && addressValid && emailPhoneValid && birthdateValid && genderValid && phoneValid && diseasesValid && bloodValid && (boolLiver || boolHeart || boolKidney || boolRetina || boolLungs) && regDateValid && priorityValid;

        if (!result){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid input, please check the inserted data and try again");
            alert.setTitle("Invalid input");
            alert.show();
            return;
        }

        int counter = 0;
        for(int i = 0; i< 5;i++) {
            if(lastd[i]) {
                counter++;
            }

        }

        if(counter > 1)
        {Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid input, please check the inserted data and try again");
            alert.setTitle("Invalid input");
            alert.show();
            return;


        }



        //----------------------------end of validation


        String fName = "'" + scene1.getfName().getText() + "'";
        String sName = "'" + scene1.getsName().getText() + "'";
        String tName = "'" + scene1.gettName().getText() + "'";
        String lName = "'" + scene1.getlName().getText() + "'";

        String id = "'"+  scene1.getId().getText() + "'";
        String phone = "'" + scene1.getPhone().getText() + "'";
        String gender =  (scene1.getMale().isSelected()) ? "'Male'" : "'Female'" ;
        String email = "'" + scene1.getEmail().getText() + "'";
        String birthdate = "'"+  scene1.getBirthdate().getValue().toString() + "'" ;
        String bloodType = "'" + scene2.getBloodType().getValue() + "'";
        String city = "'" + scene1.getCity().getText() + "'";   String street = "'" +  scene1.getStreet().getText() + "'";
        String signDate = "'" + scene2.getRegDate().getValue().toString() + "'";


        String member = "";
        //String recieved = "'" + scene1.getRecieved().getText() + "'";
        String recieved = "'no'";
        String priority = "'" + scene2.getPriority() + "'";

        String mental_issues = (scene2.getMentalIssues().isSelected()) ? "'yes'" : "'no'" ;
        String hard_diseases = (scene2.getHardDiseases().isSelected()) ? "'yes'" : "'no'" ;


        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
            Statement st = con.createStatement();
            Statement f = con.createStatement();


            //checking the entered ID, email, phone, because they are unique values
            boolean emailExist = false, phoneExist = false, personExist = false;

            //checking the id before adding
            ResultSet s = f.executeQuery("select  id from recipient where id = " + id);
            int i = 0;
            while(s.next()){ i++; }
            if (i == 1){
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Insertion error");
                alert1.setHeaderText("ID duplication");
                alert1.setContentText("The inserted id already exists, please check it and try again");
                alert1.show();
                return;
            }


            //checking the email before adding
            ResultSet s2 = f.executeQuery("select email from person where email = " + email);
            i = 0;
            while(s2.next()){ i++;}

            //checking the phone before adding
            ResultSet s3 = f.executeQuery("select phone_number from person where phone_number = " + phone);
            i = 0;
            while (s3.next()){i++;}
            if (i == 1){
                phoneExist = true;
            }
            //end of checking the unique data---------------------------



            //inserting data of the person table
            //before adding check whether the person exists or not
            ResultSet s4 = f.executeQuery("select id from person where id = " + id);
            i = 0;
            while(s4.next()){ i++;}
            if (i == 1){
                personExist = true;
            }
            if (!personExist){
                if (emailExist){
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Insertion error");
                    alert2.setHeaderText("EMAIL duplication");
                    alert2.setContentText("The inserted email already exists, please check it and try again");
                    alert2.show();
                    return;
                }

                if (phoneExist){
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Insertion error");
                    alert2.setHeaderText("PHONE duplication");
                    alert2.setContentText("The inserted email already exists, please check it and try again");
                    alert2.show();
                    return;
                }

            }
            if (!personExist) {
                st.executeQuery("insert into person (first_name,second_name,third_name,last_name,id,phone_number,gender,email,birthdate,blood_type,city,street)  values (" + fName + "," + sName + "," + tName + "," + lName + "," + id + "," + phone + "," + gender + "," + email + "," + "TO_DATE(" + birthdate + ",'yyyy-mm-dd')" + "," + bloodType + "," + city + "," + street + ")");
            }


            //inserting the data of the chosen organs...
            if (boolLiver){
                member = "'Liver'";
            }
            if (boolHeart){
                member = "'Heart'";
            }
            if (boolKidney){
                member = "'Kidney'";
            }
            if (boolLungs){
                member = "'Lungs'";
            }
            if (boolRetina){
                member = "'Retina'";
            }

                //inserting the data of the recipient ..
                Statement st2 = con.createStatement();
                st2.executeQuery("insert into recipient (RECIPIENT_ID, id, needed_member, PRECEDENCE, REGISTRATION_DATE, RECIEVED) values ( recipient_counter.nextval "+ "," + id + "," + member + "," + priority + "," + "TO_DATE(" + signDate + ", 'yyyy-mm-dd')" + "," +  "'no' )" );


                //inserting the data of the disease (page 2 of add recipient)
                Statement st3 = con.createStatement();
                Statement st4= con.createStatement();

                st3.executeQuery("insert into recipient_diseases(recipient_id, disease_name, infection) values (" + "recipient_counter.currval" + ", 'c_mental_issues' ," + mental_issues +  ")");
                st4.executeQuery("insert into recipient_diseases(recipient_id, disease_name, infection) values (" + "recipient_counter.currval" + ", 'hard_diseases' ," + hard_diseases +  ")");

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
            String time = simpleDateFormat.format(new java.util.Date());

            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy/MM/dd");
            String date = simpleDateFormat2.format(new java.util.Date()) ;
            st.executeUpdate("insert into operation (operation_number,operation_type,operation_time, person_type,operation_date,employee_id) VALUES (operation_counter.nextval, 'added recipient' , '" + time + "' , 'donor' , to_date('" + date +  "', 'yyyy/mm/dd') , " + emp.getEmployee_id() + ")");
            st.executeUpdate("insert into operation_recipient(operation_number,recipient_id) values(operation_counter.currval,recipient_counter.currval)");



                con.close();

        }catch (SQLException e){
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
        scene2.getBloodType().setValue("");

        scene2.getRegDate().getEditor().clear();

        scene2.gethPriority().setSelected(false);
        scene2.getmPriority().setSelected(false);
        scene2.getlPriority().setSelected(false);


        this.toPage1(new ActionEvent());
    }




}
