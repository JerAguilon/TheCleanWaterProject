package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.MockDatabase;

/**
 * Created by jeremy on 9/21/16.
 */
public class LoginController implements ILoginController {
    @FXML
    private TextField usernameBox;

    @FXML
    private TextField passwordBox;


    public boolean isLockedOut() {
        return false;
    }

    @FXML
    public void validate() {
        if (MockDatabase.mockDatabase.checkIfExists(usernameBox.getText())) {

            if (MockDatabase.mockDatabase.checkPassword(usernameBox.getText(), passwordBox.getText())) {
                //TODO: implement logic here
            }
            //TODO: implement denial here
        } else {

        }


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Welcome.fxml"));
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
