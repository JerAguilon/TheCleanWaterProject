package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import sample.Main;

/**
 * Created by Ben Radock on 9/21/2016.
 */
public class WelcomeScreenController {

    /** reference back to mainApplication if needed */
    private Main mainApplication;

    //@FXML
    //private Button loginButton;

    /**
     * allow for calling back to the main application code if necessary
     * @param main   the reference to the FX Application instance
     * */
    public void setMainApp(Main main) {
        mainApplication = main;
    }

    /**
     * Close menu item event handler
     */
    @FXML
    private void handleCloseMenu() {
        System.exit(0);

    }

    @FXML
    private void initialize() throws Exception {
        /*System.out.print("in initialize");
        loginButton.setOnMousePressed(
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                loginButton.requestFocus();
            }
        });*/
    }

    @FXML
    public void loginButtonPressed(ActionEvent event) throws Exception {
        mainApplication.hashCode();
       mainApplication.showLoginScene();
    }

    /**
     * About menu item event handler
     */
    @FXML
    private void handleAboutMenu() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.showAndWait();

    }

}
