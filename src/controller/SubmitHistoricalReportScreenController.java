package controller;

import apploader.LocalSession;
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
import model.HistoricalReport;
import model.HistoricalReportType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ben Radock on 11/6/2016.
 */
public class SubmitHistoricalReportScreenController {

    @FXML
    Label author;

    @FXML
    Label reportType;

    @FXML
    ComboBox histReportCondition;

    @FXML
    TextField histReportLocation;

    @FXML
    TextField histReportDay;

    @FXML
    TextField histReportMonth;

    @FXML
    TextField histReportYear;

    @FXML
    TextField ppmField;

    @FXML
    /*
      initializes the water report screen
     */
    public void initialize() {
        setValues(histReportCondition, HistoricalReportType.values());

        reportType.setText("Historical Report");
        author.setText(LocalSession.currentUsername);

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

    @FXML
    public void submit() {
        if(histReportDay.getText().isEmpty() || histReportMonth.getText().isEmpty() || histReportYear.getText().isEmpty()) {
            sendAlert("Please fill out all date fields", Alert.AlertType.ERROR);
            return;
        } else if(Integer.parseInt(histReportMonth.getText()) > 13 || Integer.parseInt(histReportMonth.getText()) < 1) {
            sendAlert("Month value is invalid. Please enter a number 1-12.", Alert.AlertType.ERROR);
            return;
        }


        HistoricalReportType hrt = HistoricalReportType.match(histReportCondition.getSelectionModel().getSelectedItem().toString());
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String year = histReportYear.getText();
        String month = histReportMonth.getText();
        String day = histReportDay.getText();

        if (!year.matches("\\d+") || !month.matches("\\d+") || !day.matches("\\d+")) {
            sendAlert("Date fields aren't valid", Alert.AlertType.ERROR);
            return;
        }
        String formattedDate  = String.format("%02d/%02d/%04d",
                Integer.parseInt(month), Integer.parseInt(day), Integer.parseInt(year));

        Date date = null;
        try {
            date = formatter.parse(formattedDate);
        } catch (ParseException e) {
            sendAlert("Unabled to parse data fields", Alert.AlertType.ERROR);
            return;
        }
        System.out.println(date);
        HistoricalReport report = new HistoricalReport(histReportLocation.getText(), hrt, date, Long.parseLong(ppmField.getText()));

        try {
            if (DatabaseFactory.getDatabase().addHistoricalReport(report)) {
                sendAlert("Thank you for your submission", Alert.AlertType.CONFIRMATION);
            } else {
                sendAlert("Something went wrong in adding to the database", Alert.AlertType.ERROR);
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
            sendAlert(e.getMessage(), Alert.AlertType.ERROR);
        }

        returnToMainScreen();
    }

    @FXML
    /*
      returns the user to the main screen
     */
    private void returnToMainScreen() {
        try {
            Stage stage = (Stage) author.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("css/stylesheet.css");
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * sends the submission error message
     * @param message the message that should be printed as a String
     * @param type the type of alert
     */
    private void sendAlert(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("Submission");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
