package controller;

import apploader.LocalSession;
import controller.interfaces.IMainScreenController;
import database.DatabaseFactory;
import database.responses.DatabaseException;
import javafx.concurrent.Worker;
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
import model.WorkerReport;

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

    @FXML
    TableView<Report> workerReportTable;

    @FXML
    TableColumn dateCol;

    @FXML
    TableColumn locationCol;

    @FXML
    TableColumn idCol;

    @FXML
    TableColumn conditionCol;

    @FXML
    TableColumn usernameCol;

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
    /**
     * allows the user to open the MapScreen
     */
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
    /**
     * allows the user to view the SubmitWaterReportScreen
     */
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
    /**
     * populates the list of water reports
     */
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

    @FXML
    /**
     * allows the user to view the SubmitPurityReportScreen
     */
    public void submitPurityReport() {
        try {
            Stage stage = (Stage) logout.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/SubmitWorkerReportScreen.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("css/stylesheet.css");
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    /**
     * populates the list of water purity reports
     */
    public void populateWaterPurityReportsList() {
        Collection<WorkerReport> reports = getPurityReports();
        idColumn.setCellValueFactory(new PropertyValueFactory<UserReport, Long>("idCol"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<UserReport, String>("locationCol"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<UserReport, String>("dateCol"));
        conditionColumn.setCellValueFactory(new PropertyValueFactory<UserReport, String>("conditionCol"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<UserReport, String>("usernameCol"));

        workerReportTable.getItems().setAll(reports);
    }

    /**
     * get method for the water reports
     * @return a list of the UserReports
     */
    private List<UserReport> getReports() {
        Collection<UserReport> fullList = null;
        try {
            fullList = DatabaseFactory.getDatabase().getUserReportList();
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

    /**
     * get method for the water purity reports
     * @return a list of the PurityReports
     */
    private Collection<WorkerReport> getPurityReports() {
        Collection<WorkerReport> fullList = null;
        try {
            fullList = DatabaseFactory.getDatabase().getWorkerReportList();
        } catch (DatabaseException e) {
            e.printStackTrace();
            fullList = new ArrayList<>();
        }


        return fullList;

    }

}
