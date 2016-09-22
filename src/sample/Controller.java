package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {

    @FXML
    public void login() {
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
