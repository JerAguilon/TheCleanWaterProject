package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/*
 * Created by Ben Radock on 9/21/2016.
 */
public class WelcomeScreenController {

    @FXML
    Button loginButton;

    @FXML
    Button registerButton;



    /**
     * Close menu item event handler
     */
    @FXML
    private void handleCloseMenu() {
        System.exit(0);

    }

    /**
     * About menu item event handler
     */
    @FXML
    private void handleAboutMenu() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("M3 Individual Project");
        alert.setHeaderText("About");
        alert.setContentText("Student Registration with code from Marco Jakob\nWebsite: http://code.makery.ch");

        alert.showAndWait();

    }

    /**
     * allow the user to go to the login screen
     *
     * */
    @FXML
    public void login() {
        try {

            Stage stage = (Stage) loginButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("css/stylesheet.css");
            stage.setScene(scene);
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * allow the user to go to the registration screen
     *
     * */
    @FXML
    public void register() {
        try {
            Stage stage = (Stage) registerButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/RegistrationScreen.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("css/stylesheet.css");
            stage.setScene(scene);
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}
