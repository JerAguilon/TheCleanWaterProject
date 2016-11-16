package model;

import controller.RegistrationController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.Test;

/*
 * Created by Ben Radock on 11/9/2016.
 */
public class RegisterTest {

    @FXML
    TextField username;

    @FXML
    private
    PasswordField password;

    @FXML
    private
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
