package com.tps.cs26_project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javax.swing.text.html.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {



    @FXML
    private AnchorPane leftAnchor;

    @FXML
    private Button loginbutton;

    @FXML
    private ImageView login_image;

    @FXML
    private PasswordField passwordfield;

    @FXML
    private Label passwordlabel;

    @FXML
    private Button registerbutton;

    @FXML
    private AnchorPane rightAnchor;

    @FXML
    private TextField usernamefield;

    @FXML
    private Label usernamelabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loginbutton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Utilities.loginUser(event, usernamefield.getText(), passwordfield.getText());
            }
        });

        registerbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                    Utilities.SceneChange(event, "RegisterForm.fxml", "Sign up!", null, null);

            }
        });

    }
}
