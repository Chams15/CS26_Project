package com.tps.cs26_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Utilities {

        public static void SceneChange(ActionEvent event, String fxmlfile, String title, String username, String deliveryaddress) {
            Parent root = null;
            if (username != null && deliveryaddress != null && deliveryaddress != null) {

                try {
                    FXMLLoader loader = new FXMLLoader(Utilities.class.getResource(fxmlfile));
                    root = loader.load();
                    LoggedInController loggedInController = loader.getController();
                    loggedInController.showcaseUserInfo(title, username, deliveryaddress);


                } catch (IOException e) {
                    e.printStackTrace();
                }


            } else {

                try {
                    root = FXMLLoader.load(Utilities.class.getResource(fxmlfile));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle(title);
            stage.setScene(new Scene(root, 600, 400));
            stage.show();


        }

        public static void registerUser(ActionEvent event, String username, String password,String status, int contactnumber, String emailaddress, String deliveryaddress){

            Connection connection = null;

            PreparedStatement pstInsert = null;
            PreparedStatement pstCheckExist = null;
            ResultSet rs = null;

            try{
                connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tps_schema","root","cahucom123");

                pstCheckExist = connection.prepareStatement("select * from tps_schema.tableusers where username = ? ");
                pstCheckExist.setString(1,username);

                rs = pstCheckExist.executeQuery();

                if(rs.isBeforeFirst()){
                    System.out.println("User already exists");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Username already exists");
                    alert.show();

                }

                else {
                    String statusActive = "Active";
                    pstInsert = connection.prepareStatement("INSERT INTO `tps_schema`.`tableusers`(username,password,status,contactnum,emailaddress,deliveryaddress) VALUES(?,?,?,?,?,?)");
                    pstInsert.setString(1,username);
                    pstInsert.setString(2,password);
                    pstInsert.setString(3,statusActive);
                    pstInsert.setInt(4,contactnumber);
                    pstInsert.setString(5,emailaddress);
                    pstInsert.setString(6,deliveryaddress);
                    pstInsert.executeUpdate();
                    SceneChange(event,"PLACEHOLDER.fxml","PLACEHOLDER", username,deliveryaddress);
                }



            } catch (Exception e) {
                e.printStackTrace();
            }

            finally {
                if (rs != null) {
                    try{
                        rs.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();

                    }

                }
                if (pstInsert != null) {
                    try{
                        pstInsert.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
                if (pstCheckExist != null) {
                    try{
                        pstCheckExist.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null) {
                    try{
                        connection.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public static void loginUser(ActionEvent event, String username, String password){

            Connection connection = null;
            PreparedStatement pst = null;
            ResultSet rs = null;
            try {

                connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tps_schema", "root", "cahucom123");
                pst = connection.prepareStatement("SELECT password,deliveryaddress FROM `tps_schema`.`tableusers` WHERE username = ?");
                pst.setString(1,username);

                rs = pst.executeQuery();
                if(!rs.isBeforeFirst()){
                    System.out.println("User not found!");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("User not found!");
                    alert.show();
                }
                else {
                    while (rs.next()) {
                        String retriPassword = rs.getString("password");
                        String deliveryaddress = rs.getString("deliveryaddress");
                        if (retriPassword.equals(password)) {

                            SceneChange(event, "PLACEHOLDER.fxml","PLACEHOLDER", username,deliveryaddress);
                        }

                        else {
                            System.out.println("User not found!");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("User not found!");
                            alert.show();
                        }
                    }


                }
            }

            catch (Exception e) {
                e.printStackTrace();
            }

            finally {
                if (rs != null) {
                    try{
                        rs.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
                if (pst != null) {
                    try {
                        pst.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
}
