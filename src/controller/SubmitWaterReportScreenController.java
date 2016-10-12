package controller;

import apploader.LocalSession;
import database.DatabaseFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeremy on 10/11/16.
 */
public class SubmitWaterReportScreenController {
    @FXML
    Label author;

    @FXML
    Label reportType;

    @FXML
    TextField purityLocation;

    @FXML
    ComboBox sourceCondition;

    @FXML
    ComboBox sourceType;

    @FXML
    public void initialize() {

        setValues(sourceCondition, WaterSourceCondition.values());
        setValues(sourceType, WaterSourceType.values());

        reportType.setText("Water Report");
        author.setText(LocalSession.currentUsername);

    }

    @FXML
    public void submit() {
        if (purityLocation.getText().isEmpty()) {
            sendAlert("Please fill in a location", Alert.AlertType.ERROR);
            return;
        }

        WaterSourceType wst = WaterSourceType.match(sourceType.getSelectionModel().getSelectedItem().toString());
        WaterSourceCondition wsc = WaterSourceCondition.match(sourceCondition.getSelectionModel().getSelectedItem().toString());


        Report report = new WaterReport(author.getText(), purityLocation.getText(), wst, wsc);

        if (DatabaseFactory.getDatabase().addReport(report)) {
            sendAlert("Thank you for your submission", Alert.AlertType.CONFIRMATION);
        } else {
            sendAlert("Something went wrong in adding to the database", Alert.AlertType.ERROR);
        }

        returnToMainScreen();
    }

    private void setValues(ComboBox cb, Object[] values) {
        cb.getItems().clear();
        List<String> list = new ArrayList<>();

        for (Object value : values) {
            list.add(value.toString());
        }

        cb.getItems().addAll(values);
        cb.getSelectionModel().select(0);

    }

    private void sendAlert(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("Submission Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void returnToMainScreen() {
        try {
            Stage stage = (Stage) reportType.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }



}
