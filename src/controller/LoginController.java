package controller;

import apploader.LocalSession;
import controller.interfaces.ILoginController;
import database.DatabaseFactory;
import database.IDatabase;
import database.responses.DatabaseException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/*
 * Created by jeremy on 9/21/16.
 */
public class LoginController implements ILoginController {
    @FXML
    private TextField usernameBox;

    @FXML
    private PasswordField passwordBox;

    private int attemptCount = 0;

    /**
     * tells whether or not the user is locked out
     * @return the boolean value true if locked out, and false if not
     */
    public boolean isLockedOut() {
        return false;
    }

    @FXML
    /*
      validates the user
     */
    public void validateLogin() {
        if (!validateBoxes()) {
            sendLoginAlert("Please fill in fields appropriately.");
            return;
        }

        if (attemptCount == 3) {
            sendLoginAlert("Attempted logins exceeded. Please register if you haven't.");
            return;
        }
        IDatabase database = DatabaseFactory.getDatabase();

        String username = usernameBox.getText();
        String password = passwordBox.getText();

        try {
            if (database != null && database.validate(username, password)) {

                try {

                    Stage stage = (Stage) usernameBox.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));

                    Scene scene = new Scene(root);
                    scene.getStylesheets().add("css/stylesheet.css");
                    stage.setScene(scene);
                    stage.show();

                    LocalSession.currentUsername = usernameBox.getText();
                    System.out.println(LocalSession.currentUsername);
                    LocalSession.currentAuth = database.getUser(usernameBox.getText()).getAuthorizationLevel();

                } catch(Exception e) {
                    e.printStackTrace();
                }

            } else {
                sendLoginAlert("Invalid username or password. Attempts left: " + (2 - attemptCount));
                attemptCount++;
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
            sendLoginAlert(e.getMessage());
        }
    }


    @FXML
    /*
     * allows the user to return back to the welcome screen
     */
    public void cancel() {
        try {
            Stage stage = (Stage) usernameBox.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/WelcomeScreen.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("css/stylesheet.css");
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * sends an alert if there is a login error
     * @param message the message that the user sees
     */
    private void sendLoginAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * validates the user information
     * @return true if the user is a registered user, false if not
     */
    private boolean validateBoxes() {
        return !(usernameBox.getText().equals("") && passwordBox.getText().equals(""));
    }




}