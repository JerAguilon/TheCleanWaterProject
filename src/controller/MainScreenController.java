package controller;

import controller.interfaces.IMainScreenController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Created by Janki on 9/21/2016.
 */
public class MainScreenController implements IMainScreenController {

    @FXML
    Button logout;

    @FXML
    Button profileedit;

    @Override
    public void logout() throws Exception {
        try {
            Stage stage = (Stage) logout.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/WelcomeScreen.fxml"));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void profile() throws Exception {
        try {
            Stage stage = (Stage) profileedit.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/ProfileEditorScreen.fxml"));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
