package com.example.project286;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InScheduleBLiverHandler {

        DonorSchedule donor;
        RecipientSchedule recipient;

        @FXML
        private ToggleGroup d1;

        @FXML
        private ToggleGroup d2;

        @FXML
        private ToggleGroup d3;

        @FXML
        private ToggleGroup d4;

        @FXML
        private JFXCheckBox enzymes;

        @FXML
        private JFXCheckBox hardDiseases;

        @FXML
        private AnchorPane liverPane;

        @FXML
        private JFXRadioButton negativeAT;

        @FXML
        private JFXRadioButton negativeECG;

        @FXML
        private JFXRadioButton negativeEcho;

        @FXML
        private JFXRadioButton negativeUltra;

        @FXML
        private JFXRadioButton positiveAT;

        @FXML
        private JFXRadioButton positiveECG;

        @FXML
        private JFXRadioButton positiveEcho;

        @FXML
        private JFXRadioButton positiveUltra;

        @FXML
        private JFXCheckBox recipientValid;

        @FXML
        private MFXButton save;

        @FXML
        private JFXTextField txtLiverSize;

        @FXML
        private JFXTextField txtTissue;

        @FXML
        void savePressed(ActionEvent event) {
            boolean isUnselected = ( (!positiveAT.isSelected() && !negativeAT.isSelected()) || (!positiveUltra.isSelected() && !negativeUltra.isSelected()) || (!positiveECG.isSelected() && !negativeECG.isSelected()) ||  (!positiveEcho.isSelected() && !negativeEcho.isSelected())   || (!positiveAT.isSelected() && !negativeAT.isSelected()  ) );

            boolean result1 , result2;
            String tmp1 = txtTissue.getText();
            char c1[] = tmp1.toCharArray();
            boolean isDigit = true;

            for(int i = 0; i<c1.length; i++) {
                if(!Character.isDigit(c1[i])){
                    isDigit = false;
                }

            }

            String tmp2 = txtLiverSize.getText();
            char c2[] = tmp2.toCharArray();
            boolean isDigit2 = true;

            for(int i = 0; i<c2.length; i++) {
                if(!Character.isDigit(c2[i])){
                    isDigit2 = false;
                }

            }

            if(isDigit && !tmp1.isEmpty()) {
                int x = Integer.parseInt(tmp1);
                result1 = (x<=100 && x >=0);
            }
            else result1 = isDigit && !tmp1.isEmpty();

            if(isDigit2 && !tmp2.isEmpty()) {
                int x = Integer.parseInt(tmp2);
                result2 = (x<=100 && x >=0);
            }
            else result2 = isDigit2 && !tmp2.isEmpty();


            if (isUnselected || !result1 || !result2) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("please fill all the fields with valid data");
                alert.show();
                return;
            }
            //end of validation

            String ultra  = (positiveUltra.isSelected()) ? "'positive'" : "'negative'";
            String echo= (positiveEcho.isSelected()) ? "'positive'" : "'negative'";
            String ecg = (positiveECG.isSelected()) ? "'positive'" : "'negative'";
            String hardD = (hardDiseases.isSelected()) ? "'yes'" : "'no'" ;
            String fit = (recipientValid.isSelected()) ? "'yes'" :  "'no'";
            String enz = (enzymes.isSelected()) ? "'yes'" :  "'no'";
            String at  = (positiveAT.isSelected()) ? "'positive'" : "'negative'";

            try {
                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
                con.setAutoCommit(false);
                Statement st = con.createStatement();
                Statement st2 = con.createStatement();
                Statement st3 = con.createStatement();
                Statement st4 = con.createStatement();
                Statement st5 = con.createStatement();
                Statement st6 = con.createStatement();
                Statement st7 = con.createStatement();
                Statement st8 = con.createStatement();
                Statement st1 = con.createStatement();

                st.executeUpdate("insert into test (test_number,test_name,donor_id,recipient_id,test_result) values(test_counter.nextval,'Tissue match',"+ donor.getDonor_id() + ", " + recipient.getRecipient_id() + ", '" + tmp1 + "' )");
                st2.executeUpdate("insert into test (test_number,test_name,donor_id,recipient_id,test_result) values(test_counter.nextval,'Liver size match',"+ donor.getDonor_id() + ", " + recipient.getRecipient_id() + ", '" + txtLiverSize.getText() +"' )");
                st3.executeUpdate("insert into test (test_number,test_name,donor_id,recipient_id,test_result) values(test_counter.nextval,'Ultrasound of abdomen/plevis',"+ donor.getDonor_id() + ", " + recipient.getRecipient_id() + ", " +  ultra     + ")");
                st4.executeUpdate("insert into test (test_number,test_name,donor_id,recipient_id,test_result) values(test_counter.nextval,'Electrocardiogram',"+ donor.getDonor_id() + ", " + recipient.getRecipient_id() + ", " + ecg + ")");
                st5.executeUpdate("insert into test (test_number,test_name,donor_id,recipient_id,test_result) values(test_counter.nextval,'Echocardiogram',"+ donor.getDonor_id() + ", " + recipient.getRecipient_id() + ", " +   echo +    ")");
                st6.executeUpdate("insert into test (test_number,test_name,donor_id,recipient_id,test_result) values(test_counter.nextval,'the recipient is fit',"+ donor.getDonor_id() + ", " + recipient.getRecipient_id() +  ", " +   fit +    ")");
                st7.executeUpdate("insert into test (test_number,test_name,donor_id,recipient_id,test_result) values(test_counter.nextval,'clear of hard diseases',"+ donor.getDonor_id() + ", " + recipient.getRecipient_id() +  ", " +  hardD +    ")");
                st8.executeUpdate("insert into test (test_number,test_name,donor_id,recipient_id,test_result) values(test_counter.nextval,'enzymes levels',"+ donor.getDonor_id() + ", " + recipient.getRecipient_id() +  ", " + enz  +    ")");
                st1.executeUpdate("insert into test (test_number,test_name,donor_id,recipient_id,test_result) values(test_counter.nextval,'antibodies',"+ donor.getDonor_id() + ", " + recipient.getRecipient_id() +  ", " + at +    ")");

                con.commit();
                con.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }



            //clearing the fields
            txtLiverSize.setText(""); txtTissue.setText("");
            positiveUltra.setSelected(false); positiveAT.setSelected(false); positiveECG.setSelected(false); positiveEcho.setSelected(false);
            negativeUltra.setSelected(false); negativeAT.setSelected(false); negativeECG.setSelected(false); negativeEcho.setSelected(false);
            hardDiseases.setSelected(false);
            recipientValid.setSelected(false);
            enzymes.setSelected(false);

        }
}
