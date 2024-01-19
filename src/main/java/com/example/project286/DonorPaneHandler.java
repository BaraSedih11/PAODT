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
import java.util.Calendar;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class DonorPaneHandler implements Initializable {
    //inDonor-B.fxml-----------------------------------
    @FXML
    private JFXButton newDonor;
    @FXML
    private JFXButton currentDonors;
    @FXML
    private AnchorPane dPane;
    @FXML
    AnchorPane donorPane;
    @FXML
    private JFXButton update;
    @FXML
    private JFXButton bigSave;

    public AnchorPane getDonorPane() {
        return donorPane;
    }


    //local variables.......................................................................
    EmployeeLogin emp;
    InDonorBHandler bh;
    private AnchorPane A;
    private AnchorPane B;
    MFXLegacyTableView<Donor> donorTable;
    private JFXButton innerSave;
    InDonorAHandler bigOne;

    private Donor dnr ;
    //end of variables

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle ) {
        //initializing the pane A
        FXMLLoader loaderA = new FXMLLoader(getClass().getResource("inDonor-A.fxml"));
        try {
            Parent rootA= loaderA.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InDonorAHandler inDonorAHandler = loaderA.getController();
        A = inDonorAHandler.getPaneA();
        innerSave = inDonorAHandler.getSave();
        bigOne = inDonorAHandler;





        //initializing the pane B
        FXMLLoader loaderB = new FXMLLoader(getClass().getResource("inDonor-B.fxml"));
        try {
            Parent rootB = loaderB.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        InDonorBHandler inDonorBHandler = loaderB.getController();
        B = inDonorBHandler.getPaneB();
        donorTable = inDonorBHandler.getDonors_table();
        bh = inDonorBHandler;
        //setting initial interface..
        dPane.getChildren().add(A);

        //ss
        update.setVisible(false);
        bigSave.setVisible(false);

    }

    public void toNewPage(ActionEvent e) throws IOException {
        //deleting the old pane
        if(! dPane.getChildren().isEmpty()) {
            dPane.getChildren().remove(0);
        }
        //setting the new pane
        dPane.getChildren().add(A);

        //styling the buttons..
        newDonor.setStyle("-fx-border-width: 0px 0px 2px 0px;" +
                "-fx-border-color:white");
        currentDonors.setStyle("-fx-border-width: 0px 0px 0px 0px");
        //s
        update.setVisible(false);
        bigOne.emp = emp;


    }

    public void toCurrentPage(ActionEvent e) {
        //deleting the old pane
        if(! dPane.getChildren().isEmpty()) {
            dPane.getChildren().remove(0);
        }
        //setting the new pane..
        dPane.getChildren().add(B);


        //styling the buttons..
        currentDonors.setStyle("-fx-border-width: 0px 0px 2px 0px;" +
                "-fx-border-color:white");
        newDonor.setStyle("-fx-border-width: 0px 0px 0px 0px");

        //ss
        update.setVisible(true);
        newDonor.setText("New Donor");

        bigSave.setVisible(false);
        innerSave.setVisible(true);

        bh.emp = emp;

        //adding the table's  data...
        ObservableList<Donor> donors = FXCollections.observableArrayList();
        ResultSet rst ;
        //getting the data from the database to add it...
        try {

            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
            Statement st = con.createStatement();
            Statement st2 = con.createStatement();


            rst = st.executeQuery("select donor_id ,donor.id,first_name,second_name,third_name,last_name,city,street,blood_type,phone_number,email, state,wfirst_name,wsecond_Name,wthird_name,wlast_name from person, donor where donor.id = person.id");
            String unq_id= "";
            while(rst.next()){
                Donor don = new Donor();

                String fname = rst.getString("first_name");
                String sname = rst.getString("second_name");
                String tname = rst.getString("third_name");
                String lname = rst.getString("last_name");
                don.setName(fname+" "+sname+" "+tname+" "+lname+" ");
                String wfname = rst.getString("wfirst_name");
                String wsname = rst.getString("wsecond_name");
                String wtname = rst.getString("wthird_name");
                String wlname = rst.getString("wlast_name");
                don.setWitnessName(wfname+" "+wsname+" "+wtname+" "+wlname);
                don.setId(rst.getString("id"));
                don.setDonor_id(rst.getString("donor_id"));


                String city = rst.getString("city");
                String street  = rst.getString("street");
                don.setAddress(city+", "+street);
                don.setBloodType(rst.getString("blood_type"));
                don.setPhone(rst.getString("phone_number"));
                don.setEmail(rst.getString("email"));
                don.setState(rst.getString("state"));
                unq_id = Integer.toString(rst.getInt("donor_id"));


                ResultSet rst2 = st2.executeQuery("select organ_name from donated_organs where id = "+unq_id);
                String organs = "";
                while (rst2.next()) {
                    organs += rst2.getString("organ_name") + ",";


                }




                don.setOrgan(organs);
                donors.add(don);
            }

            con.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        bh.donors = donors;
        donorTable.setItems(donors);

    }

    @FXML
    void toUpdate(ActionEvent event) {
        //checking if there is a donor selected.
         Donor donor =  donorTable.getSelectionModel().getSelectedItem();
         dnr = donor;
         if(donor == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a donor to update");
            alert.setHeaderText("No donor selected");
            alert.setTitle("Error");
            alert.show();
            return;
        }
        //switching the scenes
        if(! dPane.getChildren().isEmpty()) {
            dPane.getChildren().remove(0);
        }
        //setting the new pane
        dPane.getChildren().add(A);





        //setting the fields

        String names[] = donor.getName().split(" ");
        String addr[] = donor.getAddress().split(",");
        String tmp = addr[1].trim();
        String wnames [] = donor.getWitnessName().split(" ");

        String dataDate = "";
        String bld = "";
        String wphone = "";
        boolean male = false;
        ResultSet str, str2 , str3;
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");

            //person info-----------------------------------------------------------
            Statement st = con.createStatement();
            str = st.executeQuery("select * from person where id = '" + donor.getId()+"'");
            while(str.next()){
                Date df = str.getDate("birthdate");
                dataDate = df.toString();
                bld = str.getString("blood_type");
                String gnd = str.getString("gender");;
                male = (gnd.equalsIgnoreCase("male")) ;

            }
            //donor info------------------------------------------------------------
            String unq_id = "";
            Statement dd = con.createStatement();
            str3 = dd.executeQuery("select wphone_number,donor_id from donor where id = '"+ donor.getId()+"'" );
            while(str3.next()) {
                wphone = str3.getString("wphone_number");
                unq_id = Integer.toString(str3.getInt("donor_id"));
            }

            //diseases info---------------------------------------------------------
            Statement st2 = con.createStatement();
            str2 = st2.executeQuery("select disease_name, infection from donor_diseases where donor_id = " + unq_id );
            while(str2.next()) {
                String dname = str2.getString("disease_name");
                String dres = str2.getString("infection");
                boolean inf = false;
                if(dres.equalsIgnoreCase("yes"))
                    inf = true;

                switch (dname) {
                    case "heart_diseases": bigOne.scene2.getHeartDiseases().setSelected(inf); break;
                    case "liver_diseases": bigOne.scene2.getLiverDiseases().setSelected(inf); break;
                    case "diabetes" :  bigOne.scene2.getDiabetes().setSelected(inf); break;
                    case "blood_pressure" :  bigOne.scene2.getBloodPressure().setSelected(inf); break;
                    case "smoking" : bigOne.scene2.getSmoking().setSelected(inf); break;
                    case "drinking_alcohol" :  bigOne.scene2.getDrinkingAlcohol().setSelected(inf); break;
                    case "hard_diseases" :   bigOne.scene2.getHardDiseases().setSelected(inf); break;
                    case "c_mental_issues" : bigOne.scene2.getMentalIssues().setSelected(inf);
                }
            }


            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        LocalDate ld = LocalDate.parse(dataDate);
        bigOne.scene1.getBirthdate().setValue(ld);

        //first page data
        bigOne.scene1.getfName().setText(names[0]); bigOne.scene1.getsName().setText(names[1]); bigOne.scene1.gettName().setText(names[2]); bigOne.scene1.getlName().setText(names[3]);
        bigOne.scene1.getId().setText(donor.getId());
        bigOne.scene1.getMale().setSelected(male); bigOne.scene1.getFemale().setSelected(!male);
        bigOne.scene1.getEmail().setText(donor.getEmail()); bigOne.scene1.getPhone().setText(donor.getPhone());
        bigOne.scene1.getCity().setText(addr[0]);
        bigOne.scene1.getStreet().setText(tmp);
        //2nd page data
        bigOne.scene2.getBloodType().setValue(bld);
        //3rd page data
        bigOne.toPage3(new ActionEvent());
        bigOne.scene3.getwFName().setText(wnames[0]);
        bigOne.scene3.getwSName().setText(wnames[1]);
        bigOne.scene3.getwTName().setText(wnames[2]);
        bigOne.scene3.getwLName().setText(wnames[3]);
        bigOne.scene3.getwPhone().setText(wphone);

        String organs[] = donor.getOrgan().split(",",6);
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
        newDonor.setStyle("-fx-border-width: 0px 0px 2px 0px;" +
                "-fx-border-color:white");
        currentDonors.setStyle("-fx-border-width: 0px 0px 0px 0px");
        update.setVisible(false);
        newDonor.setText("Update Donor");
        // the updating code.........................


        bigSave.setVisible(true);
        innerSave.setVisible(false);
    }

    @FXML
    void saveUpdate(ActionEvent event) {
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
        boolean wNameValid = ( ! bigOne.scene3.getwFName().getText().isEmpty() &&  ! bigOne.scene3.getwSName().getText().isEmpty() && ! bigOne.scene3.getwTName().getText().isEmpty() && ! bigOne.scene3.getwLName().getText().isEmpty() );
        boolean wPhoneValid ;
        char c3 [] = bigOne.scene3.getwPhone().getText().toCharArray();
        if( bigOne.scene3.getwPhone().getText().length() == 10 ) {
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
        boolean boolLiver = (bigOne.scene3.getLabLiver().getStylesheets().get(0) == "labelgreen.css");
        boolean boolHeart = (bigOne.scene3.getLabHeart().getStylesheets().get(0) == "labelgreen.css");
        boolean boolKidney = (bigOne.scene3.getLabKidney().getStylesheets().get(0) == "labelgreen.css");
        boolean boolLungs = (bigOne.scene3.getLabLungs().getStylesheets().get(0) == "labelgreen.css");
        boolean boolRetina = (bigOne.scene3.getLabRetina().getStylesheets().get(0) == "labelgreen.css");





        boolean result = nameValid && idValid && addressValid && emailPhoneValid && birthdateValid && genderValid && phoneValid && diseasesValid && bloodValid && wNameValid && wPhoneValid && (boolLiver || boolHeart || boolKidney || boolRetina || boolLungs);
        if(! result) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid input, please check the inserted data and try again");
            alert.setTitle("Invalid input");
            alert.show();
            return;
        }


        //--------------------------------------------------------------------end of validation
        String fname = "'"+ bigOne.scene1.getfName().getText() + "'"; String sname = "'" + bigOne.scene1.getsName().getText() + "'"; String tname = "'" + bigOne.scene1.gettName().getText() + "'" ; String lname  = "'" + bigOne.scene1.getlName().getText() + "'";
        String id ="'"+  bigOne.scene1.getId().getText() + "'";  String phone = "'" + bigOne.scene1.getPhone().getText() + "'" ;
        //gender
        String gender =  (bigOne.scene1.getMale().isSelected()) ? "'Male'" : "'Female'" ;
        String email = "'" + bigOne.scene1.getEmail().getText() + "'";
        String birthdate = "'"+  bigOne.scene1.getBirthdate().getValue().toString() + "'" ;
        String bloodType = "'" + bigOne.scene2.getBloodType().getValue() + "'";
        String city = "'" + bigOne.scene1.getCity().getText() + "'";   String street = "'" +  bigOne.scene1.getStreet().getText() + "'";

        String wfname = "'" + bigOne.scene3.getwFName().getText() + "'"; String wsname = "'" + bigOne.scene3.getwSName().getText() + "'" ;  String wtname = "'"+ bigOne.scene3.getwTName().getText()+ "'"; String wlname = "'" + bigOne.scene3.getwLName().getText()+ "'";
        String wphone = "'" + bigOne.scene3.getwPhone().getText() + "'" ;
        //diseases section;
        String liver = (bigOne.scene2.getLiverDiseases().isSelected()) ? "'yes'" : "'no'" ;
        String heart = (bigOne.scene2.getHeartDiseases().isSelected()) ? "'yes'" : "'no'" ;
        String diabetes = (bigOne.scene2.getDiabetes().isSelected()) ? "'yes'" : "'no'" ;
        String pressure = (bigOne.scene2.getBloodPressure().isSelected()) ? "'yes'" : "'no'" ;
        String smoking = (bigOne.scene2.getSmoking().isSelected()) ? "'yes'" : "'no'" ;
        String drinking = (bigOne.scene2.getDrinkingAlcohol().isSelected()) ? "'yes'" : "'no'" ;

        String mental_issues = (bigOne.scene2.getMentalIssues().isSelected()) ? "'yes'" : "'no'" ;
        String hard_diseases = (bigOne.scene2.getHardDiseases().isSelected()) ? "'yes'" : "'no'" ;


        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
            con.setAutoCommit(false);

            Statement st = con.createStatement();
            //updating person
            st.executeUpdate("update person set "+"first_name = " + fname+","+"second_name = " + sname+"," +"third_name = "+tname + "," + "last_name ="+lname + ", phone_number=" + phone + ", gender= " +gender +", email = "+email+", blood_type ="+bloodType +", city= " + city+ ", street = " + street + ",birthdate = to_date("+birthdate+",'yyyy/mm/dd')" + "where id = '" +dnr.getId() +"'" );
            //updating donor
            Statement st2 = con.createStatement();
            st2.executeUpdate("update donor set wfirst_name ="+ wfname+ ", wsecond_name =" +wsname+", wthird_name =" + wtname+ ", wlast_name =" + wlname+", wphone_number = "+wphone +" where id = '" + dnr.getId()+"'");

            //getting the unique id of the donor
            Statement q = con.createStatement();
            ResultSet rr = q.executeQuery("select donor_id from donor where id = '" + dnr.getId()+"'");
            String unq_id= "";
            while(rr.next()) {
                unq_id = Integer.toString(rr.getInt("donor_id")) ;


            }
            //-----------

            //updating the diseases..
            Statement st3 = con.createStatement();
            Statement st4= con.createStatement();
            Statement st5 = con.createStatement();
            Statement st6 = con.createStatement();
            Statement st7 = con.createStatement();
            Statement st8 = con.createStatement();
            Statement st9 = con.createStatement();
            Statement st10 = con.createStatement();
            st3.executeUpdate("update donor_diseases set infection = " +  liver +" where donor_id = " + unq_id + " and disease_name = 'liver_diseases'" );
            st4.executeUpdate("update  donor_diseases set infection = " +  heart +" where donor_id = " + unq_id + " and disease_name = 'heart_diseases'" );
            st5.executeUpdate("update  donor_diseases set infection = " +  diabetes +" where donor_id = " + unq_id + " and disease_name = 'diabetes'" );
            st6.executeUpdate("update  donor_diseases set infection = " +  pressure +" where donor_id = " + unq_id + " and disease_name = 'blood_pressure'" );
            st7.executeUpdate("update  donor_diseases set infection = " +  smoking +" where donor_id = " + unq_id + " and disease_name = 'smoking'" );
            st8.executeUpdate("update  donor_diseases set infection = " +  drinking +" where donor_id = " + unq_id + " and disease_name = 'drinking_alcohol'" );
            st9.executeUpdate("update  donor_diseases set infection = " +  mental_issues +" where donor_id = " + unq_id + " and disease_name = 'c_mental_issues'" );
            st10.executeUpdate("update  donor_diseases set infection = " +  hard_diseases +" where donor_id = " + unq_id + " and disease_name = 'hard_diseases'" );

            //updating donated organs..
            Statement dd = con.createStatement();
            dd.executeUpdate("delete from donated_organs where id = " + unq_id);



            //inserting the data of the chosen organs..
            if (boolLiver) {
                Statement st11 = con.createStatement();
                st11.executeUpdate("insert into donated_organs (id,organ_name)   values(" + unq_id + "," + "'Liver' )" );
            }
            if (boolHeart) {
                Statement st12 = con.createStatement();
                st12.executeUpdate("insert into donated_organs (id,organ_name)   values(" + unq_id + "," + "'Heart' )" );
            }
            if (boolKidney) {
                Statement st13 = con.createStatement();
                st13.executeUpdate("insert into donated_organs (id,organ_name)   values(" + unq_id + "," + "'Kidney' )" );
            }
            if (boolLungs) {
                Statement st14 = con.createStatement();
                st14.executeUpdate("insert into donated_organs (id,organ_name)   values(" + unq_id + "," + "'Lungs' )" );
            }
            if (boolRetina) {
                Statement st15 = con.createStatement();
                st15.executeUpdate("insert into donated_organs (id,organ_name)   values(" + unq_id + "," + "'Retina' )" );
            }



            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
            String time = simpleDateFormat.format(new java.util.Date());

            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy/MM/dd");
            String date = simpleDateFormat2.format(new java.util.Date()) ;
            st2.executeUpdate("insert into operation (operation_number,operation_type,operation_time, person_type,operation_date,employee_id) VALUES (operation_counter.nextval, 'updating donor', '" + time + "' , 'donor' , to_date('" + date +  "', 'yyyy/mm/dd') , " + emp.getEmployee_id() + ")");
            st.executeUpdate("insert into operation_donor (operation_number,donor_id) values(operation_counter.currval," + unq_id + ")");


            con.commit();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }










        //restoring the fields to default state
        bigOne.scene1.getfName().setText(""); bigOne.scene1.getsName().setText(""); bigOne.scene1.gettName().setText(""); bigOne.scene1.getlName().setText("");
        bigOne.scene1.getId().setText(""); bigOne.scene1.getBirthdate().getEditor().clear();
        bigOne.scene1.getMale().setSelected(false); bigOne.scene1.getFemale().setSelected(false);
        bigOne.scene1.getEmail().setText(""); bigOne.scene1.getPhone().setText("");
        bigOne.scene1.getCity().setText(""); bigOne.scene1.getEmail().setText("");
        bigOne.scene1.getStreet().setText("");

        bigOne.scene2.getHardDiseases().setSelected(false);
        bigOne.scene2.getMentalIssues().setSelected(false);
        bigOne.scene2.getSmoking().setSelected(false);
        bigOne.scene2.getDrinkingAlcohol().setSelected(false);
        bigOne.scene2.getDiabetes().setSelected(false);
        bigOne.scene2.getBloodPressure().setSelected(false);
        bigOne.scene2.getHeartDiseases().setSelected(false);
        bigOne.scene2.getLiverDiseases().setSelected(false);
        bigOne.scene2.getBloodType().setValue("");

        bigOne.scene3.getwFName().setText("");
        bigOne.scene3.getwSName().setText("");
        bigOne.scene3.getwTName().setText("");
        bigOne.scene3.getwLName().setText("");
        bigOne.scene3.getwPhone().setText("");

        //styling the Buttons
        newDonor.setText("New Donor");
        bigSave.setVisible(false);
        innerSave.setVisible(true);
    }


}
