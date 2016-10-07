package controller;

import javafx.fxml.FXML;
import controller.interfaces.IRegistrationController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import model.AuthorizationLevel;
import model.MockDatabase;
import javafx.scene.control.Button;
import model.Profile;
import model.User;

/**
 * Created by AshimaGauba on 10/7/16.
 */
public class RegistrationController {
    @FXML
    Button welcreturn;

    @FXML
    Button create;

    @FXML
    TextField user;

    @FXML
    PasswordField pass;

    @FXML
    TextField account;

    /**
     * allow the user to register within the system
     *
     * */
    @FXML
    public void register() {
        String username = user.getText();
        String password = pass.getText();
        String userAuth = account.getText();
        AuthorizationLevel author = AuthorizationLevel.USER;
        if (userAuth.equalsIgnoreCase("Worker")) {
            author = AuthorizationLevel.WORKER;
        } else if (userAuth.equalsIgnoreCase("Manager")) {
            author = AuthorizationLevel.MANAGER;
        } else if (userAuth.equalsIgnoreCase("Administrator")) {
            author = AuthorizationLevel.ADMINISTRATOR;
        }

        MockDatabase.mockDatabase.addUser(username, password.hashCode(), author, new Profile("", "", ""));
        MockDatabase.mockDatabase.getUser(username);
        System.out.println(MockDatabase.mockDatabase.database.size());
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Authorization Level: " + userAuth);

        try {
            Stage stage = (Stage) welcreturn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/WelcomeScreen.fxml"));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * allow the user to go to back to the welcome screen
     *
     * */
    @FXML
    public void handleCancelPressed() {
        try {
            Stage stage = (Stage) welcreturn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/WelcomeScreen.fxml"));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
