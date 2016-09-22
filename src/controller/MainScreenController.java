package controller;

import controller.interfaces.IMainScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Janki on 9/21/2016.
 */
public class MainScreenController implements IMainScreenController {

    @Override
    public void logout() throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScreen.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
