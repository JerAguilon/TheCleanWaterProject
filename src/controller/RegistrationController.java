package controller;

import database.DatabaseFactory;
import database.responses.DatabaseException;
import exceptions.UserException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.AuthorizationLevel;
import model.Profile;
import model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AshimaGauba on 10/7/16.
 */
public class RegistrationController {
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

    @FXML
    /*
      initializes the registration screen
     */
    public void initialize() {
        List<String> values = new ArrayList<>();

        auth.getItems().clear();
        for (AuthorizationLevel auth : AuthorizationLevel.values()) {
            values.add(auth.toString());
        }
        auth.getItems().addAll(values);

        auth.getSelectionModel().select(0);
    }


    /**
     * allow the user to register within the system
     *
     * */
    @FXML
    public void register() {
        String username = this.username.getText();
        String password = this.password.getText();
        String confirmPassword = this.confirmPassword.getText();
        AuthorizationLevel auth = AuthorizationLevel.match(this.auth.getSelectionModel().getSelectedItem().toString());
        String address = this.address.getText();
        String title = this.title.getText();
        String email = this.email.getText();


        if (!password.equals(confirmPassword)) {
            sendAlert("Password fields don't match");
            return;
        }

        Profile profile = new Profile(email, address, title);

        try {
            User user = new User(username, password, auth, profile);
            DatabaseFactory.getDatabase().addUser(user);
        } catch (DatabaseException e) {
            sendAlert(e.getMessage());
            return;
        } catch (UserException e) {
            sendAlert(e.getMessage());
            return;
        }





        returnToWelcomeScreen();


    }

    /**
     * sends the alert if there is a registration error
     * @param message the message that the user sees if there is an error
     */
    private void sendAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Registration Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * allow the user to go to back to the welcome screen
     *
     * */
    @FXML
    public void returnToWelcomeScreen() {
        try {
            Stage stage = (Stage) confirmPassword.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/WelcomeScreen.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("css/stylesheet.css");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
