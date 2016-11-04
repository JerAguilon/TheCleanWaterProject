package controller;

import apploader.LocalSession;
import apploader.logger.SecurityLogger;
import database.DatabaseFactory;
import database.responses.DatabaseException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.WorkerReport;
import model.WaterPurityCondition;
import model.AuthorizationLevel;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by Ashima on 11/01/16.
 */
public class SubmitWorkerReportScreenController {
    @FXML
    private
    Label author;

    @FXML
    private
    Label reportType;

    @FXML
    private
    TextField purityLocation;

    @FXML
    ComboBox purityCondition;

    @FXML
    /*
      initializes the water report screen
     */
    public void initialize() {

        setValues(purityCondition, WaterPurityCondition.values());

        reportType.setText("Purity Report");
        author.setText(LocalSession.currentUsername);

    }

    @FXML
    /*
      submits the water report screen provided it is filled
     */
    public void submit() {
        if (DatabaseFactory.getDatabase().getUser(author.getText()).getAuthorizationLevel().equals(AuthorizationLevel.USER)
                || DatabaseFactory.getDatabase().getUser(author.getText()).getAuthorizationLevel().equals(AuthorizationLevel.ADMINISTRATOR)) {
            sendAlert("You are not authorized to submit a report", Alert.AlertType.ERROR);
            return;
        }

        if (purityLocation.getText().isEmpty()) {
            sendAlert("Please fill in a location", Alert.AlertType.ERROR);
            return;
        }

        WaterPurityCondition wpc = WaterPurityCondition.match(purityCondition.getSelectionModel().getSelectedItem().toString());


        WorkerReport report = new WorkerReport(author.getText(), purityLocation.getText(), wpc);

        try {
            if (DatabaseFactory.getDatabase().addWorkerReport(report)) {
                sendAlert("Thank you for your submission", Alert.AlertType.CONFIRMATION);
            } else {
                sendAlert("Something went wrong in adding to the database", Alert.AlertType.ERROR);
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
            sendAlert(e.getMessage(), Alert.AlertType.ERROR);
        }

        SecurityLogger.log(report.toLogList());

        returnToMainScreen();
    }

    /**
     * sets the values in the ComboBox
     * @param cb the ComboBox in which we are changing values
     * @param values the values we want in the ComboBox
     */
    private void setValues(ComboBox cb, Object[] values) {
        cb.getItems().clear();
        List<String> list = new ArrayList<>();

        for (Object value : values) {
            list.add(value.toString());
        }

        cb.getItems().addAll(values);
        cb.getSelectionModel().select(0);

    }

    /**
     * sends the submission error message
     * @param message the message that should be printed as a String
     * @param type the type of alert
     */
    private void sendAlert(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("Submission Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    /*
      returns the user to the main screen
     */ private void returnToMainScreen() {
        try {
            Stage stage = (Stage) reportType.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("css/stylesheet.css");
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }



}
