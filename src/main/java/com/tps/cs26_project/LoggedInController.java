package com.tps.cs26_project;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javax.swing.text.html.ImageView;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {

    @FXML
    private Label placeholder;

    @FXML
    private Button logoutbutton;

    @FXML
    private Label usernameplaceholder;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
            logoutbutton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Utilities.SceneChange(event,"LoginScene.fxml",null,
                    null, null);
                }
            });

    }

    public void showcaseUserInfo(String username, String emailaddress, String deliveryaddress) {
            usernameplaceholder.setText(username+""+ emailaddress+" "+ deliveryaddress);

    }


}
