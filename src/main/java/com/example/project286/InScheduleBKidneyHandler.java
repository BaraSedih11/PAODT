package com.example.project286;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InScheduleBKidneyHandler {
        DonorSchedule donor;
        RecipientSchedule recipient;
        @FXML
        private JFXCheckBox hardDiseases;
        @FXML
        private AnchorPane kidneyPane;
        @FXML
        private JFXRadioButton negativeECG;
        @FXML
        private JFXRadioButton negativeEcho;
        @FXML
        private JFXRadioButton negativeHLAT;
        @FXML
        private JFXRadioButton negativeSCT;
        @FXML
        private JFXRadioButton positiveECG;
        @FXML
        private JFXRadioButton positiveEcho;
        @FXML
        private JFXRadioButton positiveHLAT;
        @FXML
        private JFXRadioButton positiveSCT;
        @FXML
        private ToggleGroup q1;
        @FXML
        private ToggleGroup q2;
        @FXML
        private JFXCheckBox recipientValid;
        @FXML
        private MFXButton save;
        @FXML
        private ToggleGroup t1;
        @FXML
        private ToggleGroup t2;
        @FXML
        private JFXTextField txtHLA;

        @FXML
        void savePressed(ActionEvent event) {
                boolean radioUnselected = (  (!positiveSCT.isSelected() && !negativeSCT.isSelected()) ||  (!positiveHLAT.isSelected() && !negativeHLAT.isSelected() ) || (!positiveECG.isSelected() && !negativeECG.isSelected()) ||  (!positiveEcho.isSelected() && !negativeEcho.isSelected())  );
                boolean numberCheck = true;

            String tmp = txtHLA.getText();
            char c[] = tmp.toCharArray();
            for(int i=0;i<c.length;i++) {
                    if(!Character.isDigit(c[i])) {
                            numberCheck = false;
                    }

            }
                boolean finalNumberCheck = numberCheck;
            if(numberCheck && !txtHLA.getText().isEmpty()) {
                    int n = Integer.parseInt(tmp);
                    boolean numberCheck2 = (n <= 12 && n >= 0);

                    finalNumberCheck = numberCheck2 && numberCheck;
            }

            if (radioUnselected || txtHLA.getText().isEmpty() || ! finalNumberCheck) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("please fill all the fields with valid data");
                alert.show();
                return;
            }


            String sct = (positiveSCT.isSelected()) ? "'positive'": "'negative'" ;
            String hlat = (positiveHLAT.isSelected()) ? "'positive'" : "'negative'";
            String echo= (positiveEcho.isSelected()) ? "'positive'" : "'negative'";
            String ecg = (positiveECG.isSelected()) ? "'positive'" : "'negative'";
            String hardD = (hardDiseases.isSelected()) ? "'yes'" : "'no'" ;
            String fit = (recipientValid.isSelected()) ? "'yes'" :  "'no'";

            //end of validation

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


                        st.executeUpdate("insert into test (test_number,test_name,donor_id,recipient_id,test_result) values(test_counter.nextval,'Serum Crossmatch',"+ donor.getDonor_id() + ", " + recipient.getRecipient_id() + ", " +  sct +      ")");
                        st2.executeUpdate("insert into test (test_number,test_name,donor_id,recipient_id,test_result) values(test_counter.nextval,'Human leukocyte antigen',"+ donor.getDonor_id() + ", " + recipient.getRecipient_id() + ", '"  + txtHLA.getText() +"' )");
                        st3.executeUpdate("insert into test (test_number,test_name,donor_id,recipient_id,test_result) values(test_counter.nextval,'Antibodies for HLA',"+ donor.getDonor_id() + ", " + recipient.getRecipient_id() + ", " +  hlat     + ")");
                        st4.executeUpdate("insert into test (test_number,test_name,donor_id,recipient_id,test_result) values(test_counter.nextval,'Electrocardiogram',"+ donor.getDonor_id() + ", " + recipient.getRecipient_id() + ", " + ecg +      ")");
                        st5.executeUpdate("insert into test (test_number,test_name,donor_id,recipient_id,test_result) values(test_counter.nextval,'Echocardiogram',"+ donor.getDonor_id() + ", " + recipient.getRecipient_id() +  ", " +  echo +    ")");
                        st6.executeUpdate("insert into test (test_number,test_name,donor_id,recipient_id,test_result) values(test_counter.nextval,'the recipient is fit',"+ donor.getDonor_id() + ", " + recipient.getRecipient_id() +  ", " +   fit +    ")");
                        st7.executeUpdate("insert into test (test_number,test_name,donor_id,recipient_id,test_result) values(test_counter.nextval,'clear of hard diseases',"+ donor.getDonor_id() + ", " + recipient.getRecipient_id() + ", " +  hardD +    ")");

                        con.commit();
                        con.close();

                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }




            //clearing the fields

                txtHLA.setText("");
                positiveECG.setSelected(false); positiveEcho.setSelected(false); positiveHLAT.setSelected(false); positiveSCT.setSelected(false);
                negativeECG.setSelected(false); negativeEcho.setSelected(false); negativeHLAT.setSelected(false); negativeSCT.setSelected(false);
                hardDiseases.setSelected(false);
                recipientValid.setSelected(false);

        }

    }







