package controller;

import apploader.LocalSession;
import database.DatabaseFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import model.Profile;
import model.User;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by Ashima on 09/30/16.
 */
public class ProfileController {

    @FXML
    Button home;

    @FXML
    Button save;

    @FXML
    TextField username;

    @FXML
    TextField email;

    @FXML
    TextField title;

    @FXML
    TextField address;

    @FXML
    Label auth;

    @FXML
    Label name;

    @FXML
    /*
      initializes the screen
     */
    public void initialize() {
        List<String> values = new ArrayList<>();

        /*auth.getItems().clear();
        for (AuthorizationLevel auth : AuthorizationLevel.values()) {
            values.add(auth.toString());
        }
        auth.getItems().addAll(values);

        auth.getSelectionModel().select(0);*/

        User user = DatabaseFactory.getDatabase().getUser(LocalSession.currentUsername);
        email.setText(user.PROFILE.EMAIL);
        title.setText(user.PROFILE.TITLE);
        address.setText(user.PROFILE.HOME_ADDRESS);

        //auth.getSelectionModel().select(auth.getItems().get(auth.getItems().indexOf(user.AUTH.toString())));
        auth.setText(user.AUTH.toString());
        name.setText(user.USERNAME);
    }

    /**
     * allow the user to go back to the main screen
     *
     * */
    public void handleCancelPressed() {
        try {
            Stage stage = (Stage) home.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("css/stylesheet.css");
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
        String em = this.email.getText();
        String personTitle = title.getText();
        String homeAddress = address.getText();

        DatabaseFactory.getDatabase().getUser(LocalSession.currentUsername).setProfile(new Profile(em, homeAddress, personTitle));

        try {
            Stage stage = (Stage) home.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add("css/stylesheet.css");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
