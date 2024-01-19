package com.example.project286;
import com.jfoenix.controls.JFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class InScheduleBHandler implements Initializable {

        boolean testsValid[] ;
        @FXML
        private TableView<RecipientSchedule> recipientTable;
        @FXML
        private TableColumn<RecipientSchedule, String> clmAddress;

        @FXML
        private TableColumn<RecipientSchedule, String> clmBloodType;

        @FXML
        private TableColumn<RecipientSchedule, String> clmEmail;

        @FXML
        private TableColumn<RecipientSchedule, String> clmGender;

        @FXML
        private TableColumn<RecipientSchedule, String> clmId;

        @FXML
        private TableColumn<RecipientSchedule, String> clmName;

        @FXML
        private TableColumn<RecipientSchedule, String> clmOrgan;

        @FXML
        private TableColumn<RecipientSchedule, String> clmPhone;

        @FXML
        private TableColumn<RecipientSchedule, String> clmPriority;

        @FXML
        private TableColumn<RecipientSchedule, String> clmRegDate;

        @FXML
        private TableView<RecipientSchedule> winnerTable;

        @FXML
        private TableColumn<RecipientSchedule, String> clmAddress2;

        @FXML
        private TableColumn<RecipientSchedule, String> clmBloodType2;

        @FXML
        private TableColumn<RecipientSchedule, String> clmEmail2;

        @FXML
        private TableColumn<RecipientSchedule, String> clmGender2;

        @FXML
        private TableColumn<RecipientSchedule, String> clmId2;

        @FXML
        private TableColumn<RecipientSchedule, String> clmName2;

        @FXML
        private TableColumn<RecipientSchedule, String> clmOrgan2;

        @FXML
        private TableColumn<RecipientSchedule, String> clmPhone2;

        @FXML
        private TableColumn<RecipientSchedule, String> clmPriority2;

        @FXML
        private TableColumn<RecipientSchedule, String> clmRegDate2;



        @FXML
        private MFXButton done;

        @FXML
        private ToggleGroup g1;

        @FXML
        private ToggleGroup g2;

        @FXML
        private ToggleGroup g3;

        @FXML
        private ToggleGroup g4;

        @FXML
        private ToggleGroup g5;

        @FXML
        private MFXButton insert;

        @FXML
        private Button o1;

        @FXML
        private Button o2;

        @FXML
        private Button o3;

        @FXML
        private Button o4;

        @FXML
        private Button o5;

        @FXML
        private ImageView i1;

        @FXML
        private ImageView i2;

        @FXML
        private ImageView i3;

        @FXML
        private ImageView i4;

        @FXML
        private ImageView i5;


        public AnchorPane getShow1() {
                return show1;
        }

        public AnchorPane getShow2() {
                return show2;
        }

        public AnchorPane getShow3() {
                return show3;
        }

        @FXML
        private AnchorPane show1;

        @FXML
        private AnchorPane show2;

        @FXML
        private AnchorPane show3;

        @FXML
        private MFXButton view;

        @FXML
        AnchorPane paneB;
        @FXML
        private JFXRadioButton notEligibleHeart;

        @FXML
        private JFXRadioButton notEligibleKidney;

        @FXML
        private JFXRadioButton notEligibleLiver;

        @FXML
        private JFXRadioButton notEligibleLungs;

        @FXML
        private JFXRadioButton notEligibleRetina;

        @FXML
        private JFXRadioButton eligibleHeart;

        @FXML
        private JFXRadioButton eligibleKidney;

        @FXML
        private JFXRadioButton eligibleLiver;

        @FXML
        private JFXRadioButton eligibleLungs;

        @FXML
        private JFXRadioButton eligibleRetina;

        public ObservableList<RecipientSchedule> getWinner() {
                return winner;
        }

        ObservableList<RecipientSchedule> winner;


        //extra variables and methods
        Button button[] = new Button[5];
        ImageView imageView[] = new ImageView[5];



        public DonorSchedule dd;
        String chosenOrgan;
        ObservableList<RecipientSchedule> finalRecipients ;


        public AnchorPane getPaneB() {
                return paneB;
        }
        public DonorSchedule getDd() {
                return dd;
        }

        TableView<DonorSchedule> donorTable;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                testsValid = new boolean[10];
                for(int i = 0;i< 10;i++)
                        testsValid[i] = false;


                //-------------------------------
                show1.setVisible(false);
                show2.setVisible(false);
                show3.setVisible(false);
                //------------------------------------
                clmId.setCellValueFactory(new PropertyValueFactory<RecipientSchedule, String>("id"));
                clmName.setCellValueFactory(new PropertyValueFactory<RecipientSchedule, String>("name"));
                clmAddress.setCellValueFactory(new PropertyValueFactory<RecipientSchedule, String>("address"));
                clmBloodType.setCellValueFactory(new PropertyValueFactory<RecipientSchedule, String>("bloodType"));
                clmGender.setCellValueFactory(new PropertyValueFactory<RecipientSchedule, String>("gender"));
                clmEmail.setCellValueFactory(new PropertyValueFactory<RecipientSchedule, String>("email"));
                clmPhone.setCellValueFactory(new PropertyValueFactory<RecipientSchedule, String>("phone"));
                clmRegDate.setCellValueFactory(new PropertyValueFactory<RecipientSchedule, String>("regDate"));
                clmOrgan.setCellValueFactory(new PropertyValueFactory<RecipientSchedule, String>("organ"));
                clmPriority.setCellValueFactory(new PropertyValueFactory<RecipientSchedule, String>("priority"));

                clmId2.setCellValueFactory(new PropertyValueFactory<RecipientSchedule, String>("id"));
                clmName2.setCellValueFactory(new PropertyValueFactory<RecipientSchedule, String>("name"));
                clmAddress2.setCellValueFactory(new PropertyValueFactory<RecipientSchedule, String>("address"));
                clmBloodType2.setCellValueFactory(new PropertyValueFactory<RecipientSchedule, String>("bloodType"));
                clmGender2.setCellValueFactory(new PropertyValueFactory<RecipientSchedule, String>("gender"));
                clmEmail2.setCellValueFactory(new PropertyValueFactory<RecipientSchedule, String>("email"));
                clmPhone2.setCellValueFactory(new PropertyValueFactory<RecipientSchedule, String>("phone"));
                clmRegDate2.setCellValueFactory(new PropertyValueFactory<RecipientSchedule, String>("regDate"));
                clmOrgan2.setCellValueFactory(new PropertyValueFactory<RecipientSchedule, String>("organ"));
                clmPriority2.setCellValueFactory(new PropertyValueFactory<RecipientSchedule, String>("priority"));

                winner = FXCollections.observableArrayList();

                button[0] = o1;
                button[1] = o2;
                button[2] = o3;
                button[3] = o4;
                button[4] = o5;
                imageView[0] = i1;
                imageView[1] = i2;
                imageView[2] = i3;
                imageView[3] = i4;
                imageView[4] = i5;

        }

        @FXML
        void buttonDone(ActionEvent event) {
                //checking the radiobuttons first..
                if (((!eligibleHeart.isSelected() && !notEligibleHeart.isSelected()) || (!eligibleKidney.isSelected() && !notEligibleKidney.isSelected()) || (!eligibleLiver.isSelected() && !notEligibleLiver.isSelected()) || (!eligibleLungs.isSelected() && !notEligibleLungs.isSelected()) || (!eligibleRetina.isSelected() && !notEligibleRetina.isSelected()))) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Invalid input");
                        alert.setHeaderText("Missing required fields");
                        alert.setContentText("Missing required fields, please choose the state of all organs");
                        alert.show();
                        return;
                }


                //getting the table ..
                FXMLLoader loader1 = new FXMLLoader(getClass().getResource("inSchedule-A.fxml"));
                try {
                        Parent root1 = loader1.load();
                } catch (IOException e) {
                        throw new RuntimeException(e);
                }
                InScheduleAHandler inScheduleAHandler = loader1.getController();
                donorTable = inScheduleAHandler.getDonorTable2();
                DonorSchedule donor = dd;
                if (donor == null) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Invalid input");
                        alert.setHeaderText("No donor selected");
                        alert.setContentText("please choose a donor from the previous page in order to update his data");
                        alert.show();
                        return;
                }


                //matching with the database
                String organs[] = donor.getOrgans().split(",", 6);
                Boolean[] os = {false, false, false, false, false};
                for (int i = 0; i < organs.length; i++) {
                        if (eligibleRetina.isSelected() && organs[i].equalsIgnoreCase("Retina")) {
                                os[0] = true;
                        }
                        if (eligibleLungs.isSelected() && organs[i].equalsIgnoreCase("Lungs")) {
                                os[1] = true;
                        }
                        if (eligibleKidney.isSelected() && organs[i].equalsIgnoreCase("Kidney")) {
                                os[2] = true;
                        }
                        if (eligibleHeart.isSelected() && organs[i].equalsIgnoreCase("Heart")) {
                                os[3] = true;
                        }
                        if (eligibleLiver.isSelected() && organs[i].equalsIgnoreCase("Liver")) {
                                os[4] = true;
                        }

                }

                if (eligibleRetina.isSelected())
                        notEligibleRetina.setSelected(!os[0]);

                if (eligibleLungs.isSelected())
                        notEligibleLungs.setSelected(!os[1]);

                if (eligibleKidney.isSelected())
                        notEligibleKidney.setSelected(!os[2]);

                if (eligibleHeart.isSelected())
                        notEligibleHeart.setSelected(!os[3]);

                if (eligibleLiver.isSelected())
                        notEligibleLiver.setSelected(!os[4]);
                //end matching data with the database..
                String liver = (eligibleLiver.isSelected()) ? "'Eligible'" : "'not Eligible'";
                String kidney = (eligibleKidney.isSelected()) ? "'Eligible'" : "'not Eligible'";
                String heart = (eligibleHeart.isSelected()) ? "'Eligible'" : "'not Eligible'";
                String retina = (eligibleRetina.isSelected()) ? "'Eligible'" : "'not Eligible'";
                String lungs = (eligibleLungs.isSelected()) ? "'Eligible'" : "'not Eligible'";


                try {
                        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "aydigg", "123456");
                        con.setAutoCommit(false);
                        Statement st = con.createStatement();
                        Statement st2 = con.createStatement();
                        Statement st3 = con.createStatement();
                        Statement st4 = con.createStatement();
                        Statement st5 = con.createStatement();
                        st.executeUpdate("update donated_organs set organ_state = " + liver + "where    organ_name = 'Liver' and id = " + donor.getDonor_id());
                        st2.executeUpdate("update donated_organs set organ_state = " + kidney + "where    organ_name = 'Kidney' and id = " + donor.getDonor_id());
                        st3.executeUpdate("update donated_organs set organ_state = " + heart + "where    organ_name = 'Heart' and id = " + donor.getDonor_id());
                        st4.executeUpdate("update donated_organs set organ_state = " + lungs + "where    organ_name = 'Lungs' and id = " + donor.getDonor_id());
                        st5.executeUpdate("update donated_organs set organ_state = " + retina + "where    organ_name = 'Retina' and id = " + donor.getDonor_id());

                        con.commit();
                        con.close();

                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }


                //showing the next pane and setting its content..
                String organ[] = donor.getOrgans().split(",");


                String tmpValidOrgans = "";
                for (int i = 0; i < organ.length; i++) {
                        if (eligibleRetina.isSelected() && organ[i].equalsIgnoreCase("Retina")) {
                                tmpValidOrgans += "Retina ";
                        }
                        if (eligibleLungs.isSelected() && organ[i].equalsIgnoreCase("Lungs")) {
                                tmpValidOrgans += "Lungs ";
                        }
                        if (eligibleKidney.isSelected() && organ[i].equalsIgnoreCase("Kidney")) {
                                tmpValidOrgans += "Kidney ";
                        }
                        if (eligibleHeart.isSelected() && organ[i].equalsIgnoreCase("Heart")) {
                                tmpValidOrgans += "Heart ";
                        }
                        if (eligibleLiver.isSelected() && organ[i].equalsIgnoreCase("Liver")) {
                                tmpValidOrgans += "Liver ";
                        }

                }

                String tmpValidOrgans2 = tmpValidOrgans.trim();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText(tmpValidOrgans2 + "\n" + donor.getOrgans());
                alert.show();
                String validOrgans[] = tmpValidOrgans2.split(" ");
                int numberOragns = validOrgans.length;  //because there is extra comma at the end

                for (int i = 0; i < 5; i++) {
                        if (i >= numberOragns) {
                                button[i].setVisible(false);
                        } else button[i].setVisible(true);

                }

                for (int i = 0; i < numberOragns; i++) {
                        button[i].setText(validOrgans[i]);
                        imageView[i].setImage(new Image(validOrgans[i].toLowerCase() + ".png"));
                }


                show1.setVisible(true);
        }

        @FXML
        void showRecipients(ActionEvent event) {
                show3.setVisible(false);

                Button tmp = (Button) event.getSource();
                String organName = tmp.getText();
                chosenOrgan = organName;

                ObservableList<RecipientSchedule> allRecipients = FXCollections.observableArrayList();
                ResultSet rst;
                try {
                        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "aydigg", "123456");
                        con.setAutoCommit(false);
                        Statement st = con.createStatement();

                        rst = st.executeQuery("select recipient_id, recipient.id, first_name,second_name,third_name, last_name,gender,city,street,blood_type,email,phone_number,precedence,registration_date,needed_member from person, recipient where recipient.id = person.id and recieved = 'no' and needed_member = '" + organName + "'");
                        while (rst.next()) {
                                RecipientSchedule recipient = new RecipientSchedule();
                                recipient.setRecipient_id(rst.getString("recipient_id"));
                                recipient.setId(rst.getString("id"));
                                String fname = rst.getString("first_name");
                                String sname = rst.getString("second_name");
                                String tname = rst.getString("third_name");
                                String lname = rst.getString("last_name");
                                String name = fname + " " + sname + " " + tname + " " + lname;
                                recipient.setName(name);
                                String city = rst.getString("city");
                                String street = rst.getString("street");
                                recipient.setAddress(city + ", " + street);
                                recipient.setBloodType(rst.getString("blood_type"));
                                recipient.setEmail(rst.getString("email"));
                                recipient.setPhone(rst.getString("phone_number"));
                                recipient.setPriority(rst.getString("precedence"));

                                String datet[] = rst.getString("registration_date").split(" ");
//                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                                alert.setContentText(datet[0]);
//                                alert.show();
                                recipient.setRegDate(datet[0]);
                                recipient.setOrgan(rst.getString("needed_member"));

                                String gnd = rst.getString("gender");
                                recipient.setGender(gnd);

                                allRecipients.add(recipient);
                        }

                        con.commit();
                        con.close();

                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }

                //allRecipients contains all now.

                //checking blood type-----------------------------------------------------------------------------------------------
                DonorSchedule donor = dd;
                String bloodType = donor.getBloodType();

                String tmp2 = "";
                switch (bloodType) {
                        case "O-":
                                tmp2 = "O- O+ A- A+ B- B+ AB- AB+"; break;
                        case "O+":
                                tmp2 = "O+ A+ B+ AB+"; break;
                        case "A-":
                                tmp2 = "A- A+ AB- AB+"; break;
                        case "A+":
                                tmp2 = "A+ AB+"; break;
                        case "B-":
                                tmp2 = "B- B+ AB- AB+"; break;
                        case "B+":
                                tmp2 = "B+ AB+"; break;
                        case "AB-":
                                tmp2 = "AB- AB+"; break;
                        case "AB+":
                                tmp2 = "AB+"; break;
                }
                String validTypes[] = tmp2.split(" ");

                boolean check = false;
                for (int k=0 ; k <allRecipients.size() ; k++) {


                        check = false;
                        for (int i = 0; i < validTypes.length; i++) {
                                if (validTypes[i].equalsIgnoreCase(allRecipients.get(k).getBloodType())) {
                                        check = true;
                                }
                        }
                        if (!check) {
                                allRecipients.remove(allRecipients.get(k));
                        }


                }
                //end of checking blood type , now allRecipients contains recipients with valid blood types--------------------------------------------------------------------------------


                //now checking the date and the precedence to get top 10 or less than 10.
                ObservableList<RecipientSchedule> candidates = FXCollections.observableArrayList();
                int i = 0;
                while (i++ < 10 && !allRecipients.isEmpty()) {
                        RecipientSchedule max = allRecipients.get(0);



                        String tmpDate[] = max.getRegDate().split("-", 3);
                        Alert aa = new Alert(Alert.AlertType.CONFIRMATION);

                        int year = Integer.parseInt(tmpDate[0]);
                        int month = Integer.parseInt(tmpDate[1]);
                        int day = Integer.parseInt(tmpDate[2]);


                        if (max.getPriority().equalsIgnoreCase("medium")) {
                                year -= 3;
                        } else if (max.getPriority().equalsIgnoreCase("high")) {
                                year -= 6;
                        }
                        for (RecipientSchedule r : allRecipients) {
                                if (max == r) {
                                        continue;
                                }
                                //modifying date
                                String tmpDate2[] = r.getRegDate().split("-", 3);


                                int year2 = Integer.parseInt(tmpDate2[0]);
                                int month2 = Integer.parseInt(tmpDate2[1]);
                                int day2 = Integer.parseInt(tmpDate2[2]);


                                if (r.getPriority().equalsIgnoreCase("medium")) {
                                        year2 -= 3;
                                } else if (r.getPriority().equalsIgnoreCase("high")) {
                                        year2 -= 6;
                                }
                                //end of modifying, and now comparing..

                                if (year2 < year) {
                                        max = r;
                                }
                                if (year == year2 && month2 < month) {
                                        max = r;
                                }
                                if (year == year2 && month == month2 && day2 < day) {
                                        max = r;
                                }


                        }

                        //adding the highest priority
                        candidates.add(max);
                        allRecipients.remove(max);

                }
                //end of getting the top (10 at most) candidates..

                //filling the table with candidates..
                recipientTable.setItems(candidates);
                finalRecipients = candidates;


                //showing next slide..
                show2.setVisible(true);
        }

        @FXML
        void toInsertion(ActionEvent event) throws IOException {


                String toOpen = "inSchedule-B-" + chosenOrgan.toLowerCase() + ".fxml";
                //opening the window
                Stage stage1 = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource(toOpen));
                Parent root = loader.load();

                if (chosenOrgan.equalsIgnoreCase("kidney")) {
                        InScheduleBKidneyHandler inScheduleBKidneyHandler = loader.getController();
                        inScheduleBKidneyHandler.donor = dd;
                        inScheduleBKidneyHandler.recipient = recipientTable.getSelectionModel().getSelectedItem();
                        if (inScheduleBKidneyHandler.recipient == null) {
                                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                                alert2.setContentText("please select a recipient");
                                alert2.show();
                                return;
                        }
                } else if (chosenOrgan.equalsIgnoreCase("liver")) {
                        InScheduleBLiverHandler inScheduleBKidneyHandler = loader.getController();
                        inScheduleBKidneyHandler.donor = dd;
                        inScheduleBKidneyHandler.recipient = recipientTable.getSelectionModel().getSelectedItem();
                        if (inScheduleBKidneyHandler.recipient == null) {
                                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                                alert2.setContentText("please select a recipient");
                                alert2.show();
                                return;
                        }


                }
                else if (chosenOrgan.equalsIgnoreCase("heart")) {
                        InScheduleBHeartHandler inScheduleBKidneyHandler = loader.getController();
                        inScheduleBKidneyHandler.donor = dd;
                        inScheduleBKidneyHandler.recipient = recipientTable.getSelectionModel().getSelectedItem();
                        if (inScheduleBKidneyHandler.recipient == null) {
                                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                                alert2.setContentText("please select a recipient");
                                alert2.show();
                                return;
                        }

                        //---------------------------------
                }
                else if (chosenOrgan.equalsIgnoreCase("lungs")) {
                        InScheduleBLungsHandler inScheduleBKidneyHandler = loader.getController();
                        inScheduleBKidneyHandler.donor = dd;
                        inScheduleBKidneyHandler.recipient = recipientTable.getSelectionModel().getSelectedItem();
                        if (inScheduleBKidneyHandler.recipient == null) {
                                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                                alert2.setContentText("please select a recipient");
                                alert2.show();
                                return;
                        }


                }
                else if (chosenOrgan.equalsIgnoreCase("retina")) {
                        InScheduleBRetinaHandler inScheduleBKidneyHandler = loader.getController();
                        inScheduleBKidneyHandler.donor = dd;
                        inScheduleBKidneyHandler.recipient = recipientTable.getSelectionModel().getSelectedItem();
                        if (inScheduleBKidneyHandler.recipient == null) {
                                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                                alert2.setContentText("please select a recipient");
                                alert2.show();
                                return;
                        }


                }



                Scene scene = new Scene(root);
                stage1.setScene(scene);
                stage1.show();

                }


        public void viewTable(ActionEvent event) {
                int count = finalRecipients.size();
                String organ = finalRecipients.get(0).getOrgan();

                ResultSet ajx;
                try {
                        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
                        con.setAutoCommit(false);
                        Statement st2 = con.createStatement();

                        boolean checker[] = new boolean[finalRecipients.size()];

                        for(int i = 0;i< finalRecipients.size();i++) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setContentText("" + i);
                                alert.show();

                                ajx = st2.executeQuery("select distinct * from test where recipient_id = " + finalRecipients.get(i).getRecipient_id() + " and donor_id = " + dd.getDonor_id() );
                                if(ajx.next()) checker[i] = true;
                                else checker[i] = false;

                        }

                        boolean finalChecker = true;
                        for(int i = 0;i< finalRecipients.size();i++) {
                                if(!checker[i])
                                {
                                        finalChecker = false;
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setContentText("insert the test results of all candidates in order to proceed");
                                        alert.show();
                                        con.commit();
                                        con.close();
                                        return;
                                }

                        }



                        con.commit();
                        con.close();

                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }

                //checking if recipient is already added




                //in the kidney case
                if(organ.equalsIgnoreCase("kidney")) {
                        ObservableList<KidneyTests> persons = FXCollections.observableArrayList();

                        try {
                                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
                                con.setAutoCommit(false);
                                Statement st2 = con.createStatement();
                                ResultSet rst;

                                for(int i=0;i<finalRecipients.size();i++) {
                                        rst = st2.executeQuery("select recipient_id, test_result, test_name from test where recipient_id = " + finalRecipients.get(i).getRecipient_id() +" and donor_id = " + dd.getDonor_id() );

                                        KidneyTests kk = new KidneyTests();
                                        while(rst.next()) {
                                                kk.setRec_id(Integer.toString(rst.getInt("recipient_id")) );

                                                if(rst.getString("test_name").equalsIgnoreCase("Serum Crossmatch")) {
                                                        kk.setSerum(rst.getString("test_result"));

                                                }
                                                else if(rst.getString("test_name").equalsIgnoreCase("Human leukocyte antigen")) {
                                                        kk.setHla(Integer.parseInt(rst.getString("test_result")) );
                                                }
                                                else if(rst.getString("test_name").equalsIgnoreCase("Antibodies for HLA")) {
                                                        kk.setAhla(rst.getString("test_result"));
                                                }
                                                else if(rst.getString("test_name").equalsIgnoreCase("Electrocardiogram")) {
                                                        kk.setEcg(rst.getString("test_result"));
                                                }
                                                else if(rst.getString("test_name").equalsIgnoreCase("Echocardiogram")) {
                                                        kk.setEcho(rst.getString("test_result"));
                                                }
                                                else if(rst.getString("test_name").equalsIgnoreCase("the recipient is fit")) {
                                                        kk.setNotFit(rst.getString("test_result"));
                                                }
                                                else if(rst.getString("test_name").equalsIgnoreCase("clear of hard diseases")) {
                                                        kk.setNoHardDiseases(rst.getString("test_result"));
                                                }


                                        }
                                        persons.add(kk);
                                        //calculating score--------------------------------------------------------------------
                                        kk.setScore(0);

                                        if(kk.getEcg().equalsIgnoreCase("positive") ) {
                                                kk.setScore(kk.getScore()+3);
                                        }
                                        if(kk.getEcho().equalsIgnoreCase("positive") ) {
                                                kk.setScore(kk.getScore()+3);
                                        }
                                        kk.setScore(kk.getScore()+kk.getHla());

                                        if(kk.getNoHardDiseases().equalsIgnoreCase("no") || kk.getNotFit().equalsIgnoreCase("no") || kk.getAhla().equalsIgnoreCase("negative") || kk.getSerum().equalsIgnoreCase("negative") )
                                                kk.setScore(0);

                                }

                                con.commit();
                                con.close();
                        } catch (SQLException e) {
                                throw new RuntimeException(e);
                        }
                        //the result
                        KidneyTests best = persons.get(0);
                        for(KidneyTests t : persons) {
                                if(t.getScore() > best.getScore()) {
                                        best = t;
                                }


                        }

                        for(RecipientSchedule r: finalRecipients) {
                                if(r.getRecipient_id().equalsIgnoreCase(best.getRec_id()) ) {
                                        boolean cc = false;
                                        for(int i = 0 ; i < winner.size() ; i++) {
                                                if(winner.get(i).getRecipient_id().equalsIgnoreCase(r.getRecipient_id())) {
                                                        cc = true;
                                                }

                                        }
                                        if(!cc) {
                                                winner.add(r);
                                                break;
                                        }

                                }


                        }




                        winnerTable.setItems(winner);


                }

                //in liver case
                else if(organ.equalsIgnoreCase("liver")) {
                        ObservableList<LiverTests> persons = FXCollections.observableArrayList();

                        try {
                                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
                                con.setAutoCommit(false);
                                Statement st2 = con.createStatement();
                                ResultSet rst;

                                for(int i=0;i<finalRecipients.size();i++) {
                                        rst = st2.executeQuery("select recipient_id, test_result, test_name from test where recipient_id = " + finalRecipients.get(i).getRecipient_id() +" and donor_id = " + dd.getDonor_id() );

                                        LiverTests kk = new LiverTests();
                                        while(rst.next()) {
                                                String name = rst.getString("test_name");
                                                String result = rst.getString("test_result");

                                              if(name.equalsIgnoreCase("Tissue match")) {
                                                      kk.setTissue(result);
                                              }
                                              else if(name.equalsIgnoreCase("Liver size match")) {
                                                      kk.setLiverSize(result);
                                              }
                                                else if(name.equalsIgnoreCase("Ultrasound of abdomen/plevis")) {
                                                        kk.setUltrasound(result);
                                                }
                                                else if(name.equalsIgnoreCase("Electrocardiogram")) {
                                                        kk.setEcg(result);
                                                }
                                                else if(name.equalsIgnoreCase("Echocardiogram")) {
                                                        kk.setEcho(result);
                                                }
                                                else if(name.equalsIgnoreCase("the recipient is fit")) {
                                                        kk.setFit(result);
                                                }
                                                else if(name.equalsIgnoreCase("clear of hard diseases")) {
                                                        kk.setHardDiseases(result);
                                                }
                                                else if(name.equalsIgnoreCase("enzymes levels")) {
                                                        kk.setEnzymes(result);
                                                }
                                              else if(name.equalsIgnoreCase("antibodies")) {
                                                      kk.setAnti(result);
                                              }

                                                kk.setRec_id(Integer.toString(rst.getInt("recipient_id")) );

                                        }

                                        persons.add(kk);

                                        //calculating score ....
                                        kk.setScore(0);
                                        if(kk.getEcg().equalsIgnoreCase("positive") ) {
                                                kk.setScore(kk.getScore()+10);
                                        }
                                        if(kk.getEcho().equalsIgnoreCase("positive") ) {
                                                kk.setScore(kk.getScore() + 10);
                                        }
                                        if(kk.getUltrasound().equalsIgnoreCase("positive") ) {
                                                kk.setScore(kk.getScore() + 5);
                                        }
                                        if(kk.getEnzymes().equalsIgnoreCase("yes") ) {
                                                kk.setScore(kk.getScore() + 7);
                                        }

                                        kk.setScore(kk.getScore() + Integer.parseInt(kk.getTissue() ) );

                                        if(kk.getHardDiseases().equalsIgnoreCase("no") || kk.getFit().equalsIgnoreCase("no") || (Integer.parseInt(kk.getLiverSize()) < 80 )  || kk.getAnti().equalsIgnoreCase("negative") || Integer.parseInt(kk.getTissue()) < 60  )
                                                kk.setScore(0);





                                }

                                //end calculating score


                                con.commit();
                                con.close();

                        } catch (SQLException e) {
                                throw new RuntimeException(e);
                        }


                        //the result
                        LiverTests best = persons.get(0);
                        for(LiverTests t : persons) {
                                if(t.getScore() > best.getScore()) {
                                        best = t;
                                }
                        }

                        for(RecipientSchedule r: finalRecipients) {
                                if(r.getRecipient_id().equalsIgnoreCase(best.getRec_id()) ) {
                                        boolean cc = false;
                                        for(int i = 0 ; i < winner.size() ; i++) {
                                                if(winner.get(i).getRecipient_id().equalsIgnoreCase(r.getRecipient_id())) {
                                                        cc = true;
                                                }

                                        }
                                        if(!cc) {
                                                winner.add(r);
                                                break;
                                        }

                                }


                        }


                        winnerTable.setItems(winner);

                }

                else if (organ.equalsIgnoreCase("heart")) {
                        ObservableList<HeartTests> persons = FXCollections.observableArrayList();

                        try {
                                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
                                con.setAutoCommit(false);
                                Statement st2 = con.createStatement();
                                ResultSet rst;

                                for(int i=0;i<finalRecipients.size();i++) {

                                        rst = st2.executeQuery("select recipient_id, test_result, test_name from test where recipient_id = " + finalRecipients.get(i).getRecipient_id() +" and donor_id = " + dd.getDonor_id() );

                                        HeartTests kk = new HeartTests();
                                        while(rst.next()) {
                                                String name = rst.getString("test_name");
                                                String result = rst.getString("test_result");

                                                if(name.equalsIgnoreCase("tissue match")) {
                                                        kk.setTissue(result);
                                                }
                                                else if(name.equalsIgnoreCase("cavity size cor.")) {
                                                        kk.setCavity(result);
                                                }
                                                else if(name.equalsIgnoreCase("heart size cor.")) {
                                                        kk.setHeartSize(result);
                                                }
                                                else if(name.equalsIgnoreCase("lung diseases")) {
                                                        kk.setLungDiseases(result);
                                                }
                                                else if(name.equalsIgnoreCase("renal faliure")) {
                                                        kk.setRenalFailure(result);
                                                }
                                                else if(name.equalsIgnoreCase("the recipient is fit")) {
                                                        kk.setFit(result);
                                                }
                                                else if(name.equalsIgnoreCase("clear of hard diseases")) {
                                                        kk.setHardDiseases(result);
                                                }
                                                else if(name.equalsIgnoreCase("acceptable blood vessel")) {
                                                        kk.setVessels(result);
                                                }

                                                kk.setRec_id(Integer.toString(rst.getInt("recipient_id")) );

                                        }

                                        persons.add(kk);

                                        //claculating points..
                                        if(kk.getVessels().equalsIgnoreCase("yes")){
                                                kk.setScore(kk.getScore()+10);
                                        }
                                        if(kk.getRenalFailure().equalsIgnoreCase("yes")){
                                                kk.setScore(kk.getScore()-7);
                                        }
                                        if(kk.getLungDiseases().equalsIgnoreCase("yes")){
                                                kk.setScore(kk.getScore()-7);
                                        }
                                        kk.setScore(kk.getScore() + Integer.parseInt(kk.getTissue() ) );

                                        if(kk.getHardDiseases().equalsIgnoreCase("no") || kk.getFit().equalsIgnoreCase("no") || (Integer.parseInt(kk.getHeartSize()) < 90 ) || Integer.parseInt(kk.getTissue()) < 60 || Integer.parseInt(kk.getCavity()) < 85  )
                                        kk.setScore(0);


                                }//end of for





                                con.commit();
                                con.close();
                        } catch (SQLException e) {
                                throw new RuntimeException(e);
                        }

                        //the result
                        HeartTests best = persons.get(0);
                        for(HeartTests t : persons) {
                                if(t.getScore() > best.getScore()) {
                                        best = t;
                                }
                        }

                        for(RecipientSchedule r: finalRecipients) {
                                if(r.getRecipient_id().equalsIgnoreCase(best.getRec_id()) ) {
                                        boolean cc = false;
                                        for(int i = 0 ; i < winner.size() ; i++) {
                                                if(winner.get(i).getRecipient_id().equalsIgnoreCase(r.getRecipient_id())) {
                                                        cc = true;
                                                }

                                        }
                                        if(!cc) {
                                                winner.add(r);
                                                break;
                                        }

                                }


                        }

                        winnerTable.setItems(winner);


                }

                else if(organ.equalsIgnoreCase("Retina")) {
                        ObservableList<RetinaTests> persons = FXCollections.observableArrayList();

                        try {
                                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
                                con.setAutoCommit(false);
                                Statement st2 = con.createStatement();
                                ResultSet rst;
                                for(int i=0;i<finalRecipients.size();i++) {
                                        rst = st2.executeQuery("select recipient_id, test_result, test_name from test where recipient_id = " + finalRecipients.get(i).getRecipient_id() + " and donor_id = " + dd.getDonor_id());

                                        RetinaTests kk = new RetinaTests();
                                        while (rst.next()) {
                                                String name = rst.getString("test_name");
                                                String result = rst.getString("test_result");

                                                if (name.equalsIgnoreCase("tissue match")) {
                                                        kk.setTissue(result);
                                                } else if (name.equalsIgnoreCase("eye size cor.")) {
                                                        kk.setEyeSize(result);
                                                } else if (name.equalsIgnoreCase("antibody test")) {
                                                        kk.setAt(result);
                                                } else if (name.equalsIgnoreCase("electrocardiogram")) {
                                                        kk.setEcg(result);
                                                } else if (name.equalsIgnoreCase("echocardiogram")) {
                                                        kk.setEcho(result);
                                                } else if (name.equalsIgnoreCase("the recipient is fit")) {
                                                        kk.setFit(result);
                                                } else if (name.equalsIgnoreCase("clear of hard diseases")) {
                                                        kk.setHardDiseases(result);
                                                }

                                                kk.setRec_id(Integer.toString(rst.getInt("recipient_id")));

                                        }

                                        persons.add(kk);
                                        //calculating
                                        kk.setScore(0);
                                        if(kk.getEcg().equalsIgnoreCase("positive") ) {
                                                kk.setScore(kk.getScore()+10);
                                        }
                                        if(kk.getEcho().equalsIgnoreCase("positive") ) {
                                                kk.setScore(kk.getScore() + 10);
                                        }
                                        kk.setScore(kk.getScore() + Integer.parseInt(kk.getTissue() ) );

                                        if(Integer.parseInt(kk.getEyeSize()) > 90 ) {

                                                kk.setScore(kk.getScore() + (Integer.parseInt(kk.getEyeSize())  - 90 ) );
                                        }

                                        if(kk.getHardDiseases().equalsIgnoreCase("negative") || kk.getFit().equalsIgnoreCase("negative") || (Integer.parseInt(kk.getEyeSize()) < 90 )  || kk.getAt().equalsIgnoreCase("negative") || Integer.parseInt(kk.getTissue()) < 60  )
                                                kk.setScore(0);

                                        //end of calculating.



                                }

                                con.commit();
                                con.close();

                        } catch (SQLException e) {
                                throw new RuntimeException(e);
                        }

                        //the result
                        RetinaTests best = persons.get(0);
                        for(RetinaTests t : persons) {
                                if(t.getScore() > best.getScore()) {
                                        best = t;
                                }
                        }

                        for(RecipientSchedule r: finalRecipients) {
                                if(r.getRecipient_id().equalsIgnoreCase(best.getRec_id()) ) {
                                        boolean cc = false;
                                        for(int i = 0 ; i < winner.size() ; i++) {
                                                if(winner.get(i).getRecipient_id().equalsIgnoreCase(r.getRecipient_id())) {
                                                        cc = true;
                                                }

                                        }
                                        if(!cc) {
                                                winner.add(r);
                                                break;
                                        }

                                }


                        }



                        winnerTable.setItems(winner);



                }

                else if (organ.equalsIgnoreCase("Lungs")) {
                        ObservableList<LungsTests> persons = FXCollections.observableArrayList();

                        try {
                                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
                                con.setAutoCommit(false);
                                Statement st2 = con.createStatement();
                                ResultSet rst;

                                for(int i=0;i<finalRecipients.size();i++) {
                                        rst = st2.executeQuery("select recipient_id, test_result, test_name from test where recipient_id = " + finalRecipients.get(i).getRecipient_id() + " and donor_id = " + dd.getDonor_id());

                                        LungsTests kk = new LungsTests();
                                        while (rst.next()) {
                                                String name = rst.getString("test_name");
                                                String result = rst.getString("test_result");

                                                if (name.equalsIgnoreCase("tissue match")) {
                                                        kk.setTissue(result);
                                                } else if (name.equalsIgnoreCase("lungs size cor.")) {
                                                        kk.setSize(result);

                                                } else if (name.equalsIgnoreCase("cavity size cor.")) {
                                                        kk.setCavity(result);
                                                }
                                                else if (name.equalsIgnoreCase("antibody test")) {
                                                        kk.setAT(result);
                                                } else if (name.equalsIgnoreCase("electrocardiogram")) {
                                                        kk.setEcg(result);
                                                } else if (name.equalsIgnoreCase("echocardiogram")) {
                                                        kk.setEcho(result);
                                                } else if (name.equalsIgnoreCase("the recipient is fit")) {
                                                        kk.setFit(result);
                                                } else if (name.equalsIgnoreCase("clear of hard diseases")) {
                                                        kk.setHardDiseases(result);
                                                }

                                                kk.setRec_id(Integer.toString(rst.getInt("recipient_id")));

                                        }
                                        persons.add(kk);
                                        //calculating the score..
                                        kk.setScore(0);
                                        if(kk.getEcg().equalsIgnoreCase("positive") ) {
                                                kk.setScore(kk.getScore()+10);
                                        }
                                        if(kk.getEcho().equalsIgnoreCase("positive") ) {
                                                kk.setScore(kk.getScore() + 10);
                                        }
                                        kk.setScore(kk.getScore() + Integer.parseInt(kk.getTissue() ) );
                                        if(Integer.parseInt(kk.getSize()) - 90 > 0)
                                                kk.setScore(kk.getScore() + Integer.parseInt(kk.getSize()) - 90 );

                                        if(Integer.parseInt(kk.getCavity()) - 90 > 0)
                                                kk.setScore(kk.getScore() + Integer.parseInt(kk.getCavity()) - 90 );

                                        if(kk.getHardDiseases().equalsIgnoreCase("negative") || kk.getFit().equalsIgnoreCase("negative") || (Integer.parseInt(kk.getSize()) < 90 )  || kk.getAT().equalsIgnoreCase("negative") || Integer.parseInt(kk.getTissue()) < 60  || Integer.parseInt(kk.getCavity()) < 85 )
                                                kk.setScore(0);

                                        con.commit();
                                        con.close();

                                }


                        } catch (SQLException e) {
                                throw new RuntimeException(e);
                        }

                        //the result
                        LungsTests best = persons.get(0);
                        for(LungsTests t : persons) {
                                if(t.getScore() > best.getScore()) {
                                        best = t;
                                }
                        }

                        for(RecipientSchedule r: finalRecipients) {
                                if(r.getRecipient_id().equalsIgnoreCase(best.getRec_id()) ) {
                                        boolean cc = false;
                                        for(int i = 0 ; i < winner.size() ; i++) {
                                                if(winner.get(i).getRecipient_id().equalsIgnoreCase(r.getRecipient_id())) {
                                                        cc = true;
                                                }

                                        }
                                        if(!cc) {
                                                winner.add(r);
                                                break;
                                        }

                                }


                        }




                        winnerTable.setItems(winner);







                }



                show3.setVisible(true);
        }
}

