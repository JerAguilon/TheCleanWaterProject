package controller;

import apploader.LocalSession;
import controller.interfaces.IMainScreenController;
import database.DatabaseFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Report;
import model.WaterReport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by Janki on 9/21/2016.
 */
public class MainScreenController implements IMainScreenController {

    @FXML
    Button logout;

    @FXML
    Button profileedit;

    @FXML
    TableView<Report> userReportTable;

    @FXML
    TableColumn dateColumn;

    @FXML
    TableColumn locationColumn;

    @FXML
    TableColumn idColumn;

    @FXML
    TableColumn typeColumn;

    @FXML
    TableColumn conditionColumn;

    @FXML
    TableColumn usernameColumn;

    /**
     * allow the user to logout and go to the welcome screen again
     *
     * */

    @Override
    public void logout() throws Exception {
        try {
            Stage stage = (Stage) logout.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/WelcomeScreen.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("css/stylesheet.css");
            stage.setScene(scene);
            stage.show();

            LocalSession.currentUsername = null;

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * allow the user to go to the profile editing screen
     *
     * */
    @Override
    public void profile() throws Exception {
        try {
            Stage stage = (Stage) profileedit.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/ProfileEditorScreen.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("css/stylesheet.css");
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void submitWaterReport() {
        try {
            Stage stage = (Stage) logout.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/SubmitWaterReportScreen.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("css/stylesheet.css");
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void populateWaterReportsList() {
        List<Report> reports = getReports();
        System.out.println(((WaterReport) reports.get(0)).getConditionColumn());

        idColumn.setCellValueFactory(new PropertyValueFactory<WaterReport, Long>("idColumn"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<WaterReport, String>("locationColumn"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<WaterReport, String>("dateColumn"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<WaterReport, String>("typeColumn"));
        conditionColumn.setCellValueFactory(new PropertyValueFactory<WaterReport, String>("conditionColumn"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<WaterReport, String>("usernameColumn"));

        userReportTable.getItems().setAll(reports);
    }

    private List<Report> getReports() {
        Collection<Report> fullList = DatabaseFactory.getDatabase().getReportList();
        List<Report> filteredList = new ArrayList<>();

        for (Report report : fullList) {
            if (report instanceof WaterReport) {
                filteredList.add(report);
            }
        }

        return filteredList;

    }


}
