package model;

import controller.RegistrationController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.Test;

import static org.junit.Assert.*;

/*
 * Created by Ben Radock on 11/9/2016.
 */
public class RegisterTest {

    @FXML
    TextField username;

    @FXML
    PasswordField password;

    @FXML
    PasswordField confirmPassword;

    @FXML
    TextField email;

    @FXML
    ComboBox auth;

    @FXML
    TextField address;

    @FXML
    TextField title;

    @Test
    public void passwordFieldsMismatch() {
        password.setText("A");
        confirmPassword.setText("B");
        RegistrationController rc = new RegistrationController();
        rc.register();
        //assertTrue();
    }

}
