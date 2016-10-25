package controller;

import controller.interfaces.ILoginController;
import database.DatabaseFactory;
import database.IDatabase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by jeremy on 9/21/16.
 */
public class LoginController implements ILoginController {
    private TextField usernameBox;

    private TextField passwordBox;

    private int attemptCount = 0;

    public boolean isLockedOut() {
        return false;
    }

    public void validate() {
        if (!validateBoxes()) {
            sendLoginAlert("Please fill in fields appropriately.");
            return;
        }

        if (attemptCount == 3) {
            sendLoginAlert("Attempted logins exceeded. Please register if you haven't.");
            return;
        }
        IDatabase database = DatabaseFactory.getDatabase();

        if (database.getUser(usernameBox.getText()) != null) {

            if (database.getUser(usernameBox.getText()).PASS_HASH == passwordBox.getText().hashCode()) {
                try {

                    Stage stage = (Stage) usernameBox.getScene().getWindow();
                    Parent root = (Parent) FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));

                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.show();

                } catch(Exception e) {
                    e.printStackTrace();
                }
            } else {
                sendLoginAlert("Password incorrect. Attempted logins left: " + (2 - attemptCount));
                attemptCount++;
            }
        } else {
            sendLoginAlert("User doesn't exist.");
        }
    }

    public void cancel() {
        try {
            Stage stage = (Stage) usernameBox.getScene().getWindow();
            Parent root = (Parent) FXMLLoader.load(getClass().getResource("/view/WelcomeScreen.fxml"));
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void sendLoginAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean validateBoxes() {
        return !(usernameBox.getText().equals("") && passwordBox.getText().equals(""));
    }




}
