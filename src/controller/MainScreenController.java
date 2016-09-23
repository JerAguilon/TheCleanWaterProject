package controller;

import controller.interfaces.IMainScreenController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.MockDatabase;

/**
 * Created by Janki on 9/21/2016.
 */
public class MainScreenController implements IMainScreenController {
    @FXML
    private Button logoutButton;


    @FXML
    public void logout() {
        try {

            Stage stage = (Stage) logoutButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/WelcomeScreen.fxml"));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
