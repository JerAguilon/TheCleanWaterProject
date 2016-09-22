package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import sample.Main;

/**
 * Created by Ben Radock on 9/21/2016.
 */
public class WelcomeScreenController {

    /** reference back to mainApplication if needed */
    private Main mainApplication;

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

    /**
     * About menu item event handler
     */
    @FXML
    private void handleAboutMenu() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("M3 Individual Project");
        alert.setHeaderText("About");
        alert.setContentText("Student Registration with code from Marco Jakob\nWebsite: http://code.makery.ch");

        alert.showAndWait();

    }

}
