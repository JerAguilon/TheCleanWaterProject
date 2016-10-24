package controller;

import apploader.LocalSession;
import controller.interfaces.IMainScreenController;
import database.DatabaseFactory;
import database.responses.DatabaseException;
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
import model.UserReport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    public void openMap() throws Exception {
        try {
            Stage stage = (Stage) profileedit.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/MapScreen.fxml"));

            Scene scene = new Scene(root);
            //scene.getStylesheets().add("css/stylesheet.css");
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
        List<UserReport> reports = getReports();

        idColumn.setCellValueFactory(new PropertyValueFactory<UserReport, Long>("idColumn"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<UserReport, String>("locationColumn"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<UserReport, String>("dateColumn"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<UserReport, String>("typeColumn"));
        conditionColumn.setCellValueFactory(new PropertyValueFactory<UserReport, String>("conditionColumn"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<UserReport, String>("usernameColumn"));

        userReportTable.getItems().setAll(reports);
    }

    private List<UserReport> getReports() {
        Collection<UserReport> fullList = null;
        try {
            fullList = DatabaseFactory.getDatabase().getReportList();
        } catch (DatabaseException e) {
            e.printStackTrace();
            fullList = new ArrayList<>();
        }
        List<UserReport> filteredList = new ArrayList<>();

        for (UserReport report : fullList) {
            filteredList.add(report);

        }

        return filteredList;

    }


}
