package com.example.project286;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class InScheduleAHandler implements Initializable {

    @FXML
    private JFXRadioButton alive;

    @FXML
    private JFXRadioButton dead;

    @FXML
    private TableView<DonorSchedule> donorTable1;
    @FXML
    private TableView<DonorSchedule> donorTable2;

    @FXML
    private MFXButton add;
    @FXML
    private JFXTextField txtSearch;

    @FXML
    private TableColumn<DonorSchedule, String> clmAddress;
    @FXML
    private TableColumn<DonorSchedule, String> clmBloodType;
    @FXML
    private TableColumn<DonorSchedule, String> clmGender;
    @FXML
    private TableColumn<DonorSchedule, String> clmName;
    @FXML
    private TableColumn<DonorSchedule, String> clmOrgans;
    @FXML
    private TableColumn<DonorSchedule, String> clmWName;
    @FXML
    private TableColumn<DonorSchedule, String> clmWPhone;
    //--------------------------------------------------------
    @FXML
    private TableColumn<DonorSchedule, String> clmAddress2;
    @FXML
    private TableColumn<DonorSchedule, String> clmId2;
    @FXML
    private TableColumn<DonorSchedule, String> clmBloodType2;
    @FXML
    private TableColumn<DonorSchedule, String> clmGender2;
    @FXML
    private TableColumn<DonorSchedule, String> clmName2;
    @FXML
    private TableColumn<DonorSchedule, String> clmOrgans2;
    @FXML
    private TableColumn<DonorSchedule, String> clmWName2;
    @FXML
    private TableColumn<DonorSchedule, String> clmWPhone2;


    public TableView<DonorSchedule> getDonorTable2() {
        return donorTable2;
    }

    @FXML
    private ToggleGroup g1;
    @FXML
    private MFXButton search;

    @FXML
    AnchorPane paneA;

    public AnchorPane getPaneA() {
        return paneA;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clmAddress.setCellValueFactory(new PropertyValueFactory<DonorSchedule,String>("address"));
        clmName.setCellValueFactory(new PropertyValueFactory<DonorSchedule,String>("name"));
        clmBloodType.setCellValueFactory(new PropertyValueFactory<DonorSchedule,String>("bloodType"));
        clmGender.setCellValueFactory(new PropertyValueFactory<DonorSchedule,String>("gender"));
        clmOrgans.setCellValueFactory(new PropertyValueFactory<DonorSchedule,String>("organs"));
        clmWName.setCellValueFactory(new PropertyValueFactory<DonorSchedule,String>("witnessName"));
        clmWPhone.setCellValueFactory(new PropertyValueFactory<DonorSchedule,String>("witnessPhone"));

        //clmBloodType.setStyle( "-fx-alignment: CENTER;");

        clmAddress2.setCellValueFactory(new PropertyValueFactory<DonorSchedule,String>("address"));
        clmName2.setCellValueFactory(new PropertyValueFactory<DonorSchedule,String>("name"));
        clmBloodType2.setCellValueFactory(new PropertyValueFactory<DonorSchedule,String>("bloodType"));
        clmGender2.setCellValueFactory(new PropertyValueFactory<DonorSchedule,String>("gender"));
        clmOrgans2.setCellValueFactory(new PropertyValueFactory<DonorSchedule,String>("organs"));
        clmWName2.setCellValueFactory(new PropertyValueFactory<DonorSchedule,String>("witnessName"));
        clmWPhone2.setCellValueFactory(new PropertyValueFactory<DonorSchedule,String>("witnessPhone"));
        clmId2.setCellValueFactory(new PropertyValueFactory<DonorSchedule,String>("id"));



    }

    @FXML
    void commitSearch(ActionEvent event) {
        String toSearch = txtSearch.getText();
        txtSearch.setText("");
        //checking the inserted id...
        char c[] = toSearch.toCharArray();

        if(c.length != 9) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText("Invalid input, the id consists of 9 digits only");
            alert2.setTitle("Invalid input");
            alert2.show();
            return;
        }

        for (int i =0;i<c.length;i++) {
            if(!Character.isDigit(c[i])) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid input, the id consists of digits only");
                alert.setTitle("Invalid input");
                alert.show();

                return;
            }

        }

        ObservableList<DonorSchedule> donors = FXCollections.observableArrayList();
        DonorSchedule donor = new DonorSchedule();
        String unq_id = "";
        int flag = 0;
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
            con.setAutoCommit(false);
            Statement st = con.createStatement();

            ResultSet rst =  st.executeQuery("select donated, donor_id ,donor.id,first_name,second_name,third_name,last_name,city,street,gender,blood_type,wfirst_name,wsecond_Name,wthird_name,wlast_name,wphone_number from person, donor where donor.id = person.id  and donor.id = '" +toSearch+"' and state = 'alive' and donated = 'no'");
            while (rst.next()) {
                String fname = rst.getString("first_name");
                String sname = rst.getString("second_name");
                String tname = rst.getString("third_name");
                String lname = rst.getString("last_name");
                donor.setName(fname+" "+sname+" "+tname+" "+lname);
                donor.setId(rst.getString("id"));
                donor.setGender(rst.getString("gender"));

                String city = rst.getString("city");
                String street  = rst.getString("street");
                donor.setAddress(city+", "+street);

                String wfname = rst.getString("wfirst_name");
                String wsname = rst.getString("wsecond_name");
                String wtname = rst.getString("wthird_name");
                String wlname = rst.getString("wlast_name");
                donor.setWitnessName(wfname+" "+wsname+" "+wtname+" "+wlname);
                donor.setWitnessPhone(rst.getString("wphone_number"));
                donor.setBloodType(rst.getString("blood_type"));
                donor.setDonated(rst.getString("donated"));


                unq_id = Integer.toString(rst.getInt("donor_id"));
                flag++;
            }


            Statement st2  = con.createStatement();
            if(flag>0) {
                ResultSet rst2 = st2.executeQuery("select organ_name from donated_organs where id = " + unq_id);
                String organs = "";
                while (rst2.next()) {
                    organs += rst2.getString("organ_name") + ",";
                }
                donor.setOrgans(organs);


            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Couldn't find an alive donor having the inserted id, please check it and try again");
                alert.setTitle("Invalid input");
                alert.setHeaderText("Wrong id");
                alert.show();

                return;
            }


            donors.add(donor);
            donorTable1.setItems(donors);
            con.commit();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }







    }


    @FXML
    void addAvailable(ActionEvent event) {
        DonorSchedule donor =  donorTable1.getSelectionModel().getSelectedItem();
        if(donor == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a donor to add");
            alert.setHeaderText("No donor selected");
            alert.setTitle("Error");
            alert.show();
            return;
        }



        if(!dead.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("you should change the state of the donor to dead in order to add him to the available donors.");
            alert.setHeaderText("insertion error");
            alert.setTitle("Error");
            alert.show();
            return;
        }


        ObservableList<DonorSchedule>  donors = donorTable2.getItems();
        for(DonorSchedule d : donors) {
            if(d.getId().equalsIgnoreCase(donor.getId())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("This donor has already been added");
                alert.setTitle("Error");
                alert.show();

                return;
            }


        }


        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
            con.setAutoCommit(false);
            Statement st = con.createStatement();

            st.executeUpdate("update donor set state = 'dead' where id = '"+donor.getId() +"'"  );

            con.commit();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }






        //adding to the table
       donors.add(donor);
       donorTable2.setItems(donors);
       //clearing the old table
       donorTable1.getItems().clear();
       alive.setSelected(true);

    }




}
