package com.example.project286;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SchedulePaneHandler implements Initializable {

    @FXML
    private AnchorPane inSchedule;

    @FXML
    private MFXButton permit;

    @FXML
    private MFXButton save;
    @FXML
    private MFXScrollPane schedule;



    public MFXScrollPane getSchedule() {
        return schedule;
    }

    InScheduleBHandler K;
    AnchorPane paneA , paneB;
    TableView<DonorSchedule> donorTable;
    AnchorPane show1, show2, show3;
    DonorSchedule dd;


    ObservableList<RecipientSchedule> winner;

    public TableView<DonorSchedule> getDonorTable() {
        return donorTable;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("inSchedule-A.fxml"));
        try {
            Parent root1 = loader1.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InScheduleAHandler inScheduleAHandler = loader1.getController();
        paneA = inScheduleAHandler.getPaneA();
        donorTable = inScheduleAHandler.getDonorTable2();


        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("inSchedule-B.fxml"));
        try {
            Parent root2 = loader2.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InScheduleBHandler inScheduleBHandler = loader2.getController();
        K = inScheduleBHandler;
        paneB = inScheduleBHandler.getPaneB();
        show1 = inScheduleBHandler.getShow1();
        show2 = inScheduleBHandler.getShow2();
        show3 = inScheduleBHandler.getShow3();
        winner = inScheduleBHandler.getWinner();
        dd = inScheduleBHandler.getDd();

        inSchedule.getChildren().add(paneA);
        save.setVisible(false);
        permit.setVisible(true);


    }


    @FXML
    void permitPressed(ActionEvent event) {
        if(donorTable.getSelectionModel().getSelectedItem() == null) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("choose a donor please");
            alert.show();


            return;
        }


        if(! inSchedule.getChildren().isEmpty()) {
            inSchedule.getChildren().remove(0);

        }
        inSchedule.getChildren().add(paneB);

        save.setVisible(true);
        permit.setVisible(false);



        K.dd = donorTable.getSelectionModel().getSelectedItem();

    }

    @FXML
    void savePressed(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("" + winner.size() + "\n" + K.winner.size() +"");
        alert.show();




        if (!inSchedule.getChildren().isEmpty()) {
            inSchedule.getChildren().remove(0);

        }
        inSchedule.getChildren().add(paneA);

        save.setVisible(false);
        permit.setVisible(true);
        schedule.setVvalue(0);
        show1.setVisible(false);
        show2.setVisible(false);
        show3.setVisible(false);


        int i = 0;
        while (i < winner.size()) {
            RecipientSchedule recipient = winner.get(i);

            if (recipient != null) {

                ResultSet str;
                try {
                    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "aydigg", "123456");
                    con.setAutoCommit(false);
                    Statement st = con.createStatement();


                    str = st.executeQuery("select needed_member from recipient where recipient_id = " + recipient.getRecipient_id());
                    String member = "";
                    while (str.next()) {
                        member = str.getString("needed_member");
                    }

                    st.executeUpdate("insert into recipient_donor (recipient_id,donor_id,organ) values(" + recipient.getRecipient_id() + " , " + K.dd.getDonor_id() + ", '" + member + "' )");
                    st.executeUpdate("update recipient set recieved = 'yes' where recipient_id = " + recipient.getRecipient_id() );
                    st.executeUpdate("update donor set donated = 'yes' where donor_id = " + K.dd.getDonor_id());


                    con.commit();
                    con.close();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }



            i++;
        }

        while(!winner.isEmpty())
            winner.remove(0);




    }
}
