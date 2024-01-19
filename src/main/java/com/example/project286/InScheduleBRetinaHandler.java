package com.example.project286;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InScheduleBRetinaHandler {

        DonorSchedule donor;
        RecipientSchedule recipient;

        @FXML
        private ToggleGroup d1;

        @FXML
        private ToggleGroup d2;

        @FXML
        private ToggleGroup d3;

        @FXML
        private JFXCheckBox hardDiseases;

        @FXML
        private JFXRadioButton negativeAT;

        @FXML
        private JFXRadioButton negativeECG;

        @FXML
        private JFXRadioButton negativeEcho;

        @FXML
        private JFXRadioButton positiveAT;

        @FXML
        private JFXRadioButton positiveECG;

        @FXML
        private JFXRadioButton positiveEcho;

        @FXML
        private JFXCheckBox recipientValid;

        @FXML
        private MFXButton save;

        @FXML
        private JFXTextField txtSize;

        @FXML
        private JFXTextField txtTissue;

        @FXML
        void savePressed(ActionEvent event) {
                boolean isUnselected = ( (!positiveAT.isSelected() && !negativeAT.isSelected() ) || (!positiveECG.isSelected() && !negativeECG.isSelected()) || (!positiveEcho.isSelected() && !negativeEcho.isSelected() ) )  ;

                //2nd txt
                boolean number2Check = true;

                String tmp2 = txtSize.getText();
                char c2[] = tmp2.toCharArray();
                for(int i=0;i<c2.length;i++) {
                        if(!Character.isDigit(c2[i])) {
                                number2Check = false;
                        }

                }
                boolean finalNumberCheck2 = number2Check && !txtSize.getText().isEmpty();
                if(number2Check && !txtSize.getText().isEmpty()) {
                        int n = Integer.parseInt(tmp2);
                        boolean number2Check2 = (n <= 100 && n >= 0);

                        finalNumberCheck2 = number2Check2 && number2Check;
                }
                //3rd txt
                boolean number3Check = true;

                String tmp3 = txtTissue.getText();
                char c3[] = tmp3.toCharArray();
                for(int i=0;i<c3.length;i++) {
                        if(!Character.isDigit(c3[i])) {
                                number3Check = false;
                        }

                }
                boolean finalNumberCheck3 = number3Check && !txtTissue.getText().isEmpty();
                if(number3Check && !txtTissue.getText().isEmpty()) {
                        int n = Integer.parseInt(tmp3);
                        boolean number3Check2 = (n <= 100 && n >= 0);

                        finalNumberCheck3 = number3Check2 && number3Check;
                }

                if(isUnselected  || !finalNumberCheck2 || !finalNumberCheck3) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("please fill all the fields with valid data");
                        alert.show();
                        return;
                }

                String hardD = (hardDiseases.isSelected()) ? "'positive'" : "'negative'" ;
                String fit = (recipientValid.isSelected()) ? "'positive'" :  "'negative'";


                //end of validation
                try {
                        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aydigg","123456");
                        con.setAutoCommit(false);
                        Statement st2 = con.createStatement();
                        Statement st3 = con.createStatement();
                        Statement st4 = con.createStatement();
                        Statement st5 = con.createStatement();
                        Statement st6 = con.createStatement();
                        Statement st7 = con.createStatement();
                        Statement st8 = con.createStatement();

                        String ecg = (positiveECG.isSelected()) ? "'positive'" : "'negative'";
                        String echo = (positiveEcho.isSelected()) ? "'positive'" : "'negative'";
                        String antibody= (positiveAT.isSelected()) ? "'positive'" : "'negative'";

                        st2.executeUpdate("insert into test (test_number,test_name,donor_id,recipient_id,test_result) values(test_counter.nextval,'tissue match',"+ donor.getDonor_id() + ", " + recipient.getRecipient_id() + ","+ "'" +  txtTissue.getText() +"' )");
                        st3.executeUpdate("insert into test (test_number,test_name,donor_id,recipient_id,test_result) values(test_counter.nextval,'eye size cor.',"+ donor.getDonor_id() + ", " + recipient.getRecipient_id() + ", '" + txtSize.getText()  + "')");
                        st4.executeUpdate("insert into test (test_number,test_name,donor_id,recipient_id,test_result) values(test_counter.nextval,'electrocardiogram',"+ donor.getDonor_id() + ", " + recipient.getRecipient_id() + "," + ecg + ")");
                        st5.executeUpdate("insert into test (test_number,test_name,donor_id,recipient_id,test_result) values(test_counter.nextval,'echocardiogram',"+ donor.getDonor_id() + ", " + recipient.getRecipient_id() +  "," + echo +  ")");
                        st6.executeUpdate("insert into test (test_number,test_name,donor_id,recipient_id,test_result) values(test_counter.nextval,'the recipient is fit',"+ donor.getDonor_id() + ", " + recipient.getRecipient_id() + "," +    fit +    ")");
                        st7.executeUpdate("insert into test (test_number,test_name,donor_id,recipient_id,test_result) values(test_counter.nextval,'clear of hard diseases',"+ donor.getDonor_id() + ", " + recipient.getRecipient_id() + "," +  hardD +  ")");
                        st8.executeUpdate("insert into test (test_number,test_name,donor_id,recipient_id,test_result) values(test_counter.nextval,'antibody test',"+ donor.getDonor_id() + ", " + recipient.getRecipient_id() + "," +  antibody +  ")");


                        con.commit();
                        con.close();



                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }



                //clearing the fields..............
                txtTissue.setText(""); txtSize.setText("");
                hardDiseases.setSelected(false); recipientValid.setSelected(false);
                positiveAT.setSelected(false); positiveEcho.setSelected(false); positiveECG.setSelected(false);
                negativeAT.setSelected(false); negativeECG.setSelected(false); negativeEcho.setSelected(false);





        }







}
