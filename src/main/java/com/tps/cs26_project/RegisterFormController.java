package com.tps.cs26_project;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterFormController implements Initializable {

    @FXML
    private Label registeraddress;

    @FXML
    private TextField registeraddressfield;

    @FXML
    private Label registercontact;

    @FXML
    private TextField registercontactfield;

    @FXML
    private Label registeremail;

    @FXML
    private TextField registeremailfield;

    @FXML
    private Label registerpassword;

    @FXML
    private TextField registerpasswordfield;

    @FXML
    private Button registerupdatebutton;

    @FXML
    private Label registerusername;

    @FXML
    private TextField registerusernamefield;

    @FXML
    private Button returntologinbutton;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        registerupdatebutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean username = registerusernamefield.getText().trim().isEmpty();
                boolean password = registerpasswordfield.getText().trim().isEmpty();
                boolean email = registeremailfield.getText().trim().isEmpty();
                boolean address = registeraddressfield.getText().trim().isEmpty();
                boolean contact = registercontactfield.getText().trim().isEmpty();
                int connumber = Integer.parseInt(registercontactfield.getText());
                String status = "Active";
                if (!username && !password && !email && !contact && !address) {

                    Utilities.registerUser(event, registerusernamefield.getText(),registerpasswordfield.getText(),status, connumber, registeremailfield.getText(), registeraddressfield.getText());


                }

                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lacking Information");
                    alert.setContentText("Please fill all the fields");
                    alert.show();
                }
            }

        });

        returntologinbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Utilities.SceneChange(event, "LoginScene.fxml", "Please log in!", null, null);
            }

        });
    }
}
