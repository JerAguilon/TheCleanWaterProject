package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import model.AuthorizationLevel;
import model.MockDatabase;
import model.Profile;

/**
 * Created by Ashima on 09/30/16.
 */
public class ProfileController {

    @FXML
    Button home;

    @FXML
    Button save;

    @FXML
    TextField user;

    @FXML
    TextField email;

    @FXML
    TextField title;

    @FXML
    TextField address;

    /**
     * allow the user to go back to the main screen
     *
     * */
    public void handleCancelPressed() {
        try {
            Stage stage = (Stage) home.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * allow the user to save their personal information to their profile
     *
     * */
    public void saveInformation() {
        String username = user.getText();
        String em = email.getText();
        String personTitle = title.getText();
        String homeAddress = address.getText();

        MockDatabase.mockDatabase.getUser(username).setProfile(new Profile(em, homeAddress, personTitle));

        System.out.println("Username: " + username);
        System.out.println("Email: " + em);
        System.out.println("Title: " + personTitle);
        System.out.println("Address: " + homeAddress);

        try {
            Stage stage = (Stage) home.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
