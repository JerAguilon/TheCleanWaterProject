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
import model.AuthorizationLevel;
import model.Report;
import model.UserReport;
import model.WorkerReport;

import java.util.ArrayList;
import java.util.Collection;

/*
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

    @FXML
    Button submitWorkerReport;

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
    /*
      allows the user to open the MapScreen
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
    /*
      allows the user to view the SubmitWaterReportScreen
     */
    public void submitWaterReport() {
        try {
            Stage stage = (Stage) logout.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/SubmitUserReportScreen.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("css/stylesheet.css");
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void populateWaterReports() {
        populateWaterReportsList();
        populateWaterPurityReportsList();
    }

    @FXML
    /**
     * populates the list of water reports
     */
    private void populateWaterReportsList() {
        Collection<UserReport> reports = getReports();

        idColumn.setCellValueFactory(new PropertyValueFactory<UserReport, Long>("idColumn"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<UserReport, String>("locationColumn"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<UserReport, String>("dateColumn"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<UserReport, String>("typeColumn"));
        conditionColumn.setCellValueFactory(new PropertyValueFactory<UserReport, String>("conditionColumn"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<UserReport, String>("usernameColumn"));

        userReportTable.getItems().setAll(reports);
    }

    @FXML
    /*
      allows the user to view the SubmitPurityReportScreen
     */
    public void submitPurityReport() {
        if(LocalSession.currentAuth.equals(AuthorizationLevel.USER)) {
            submitWorkerReport.setText("Users do not have access");
        } else {
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
    }

    @FXML
    /**
     * populates the list of water purity reports
     */
    private void populateWaterPurityReportsList() {
        Collection<WorkerReport> reports = getPurityReports();

        System.out.println(reports);
        idCol.setCellValueFactory(new PropertyValueFactory<WorkerReport, Long>("idColumn"));
        locationCol.setCellValueFactory(new PropertyValueFactory<WorkerReport, String>("locationColumn"));
        dateCol.setCellValueFactory(new PropertyValueFactory<WorkerReport, String>("dateColumn"));
        conditionCol.setCellValueFactory(new PropertyValueFactory<WorkerReport, String>("conditionColumn"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<WorkerReport, String>("usernameColumn"));

        workerReportTable.getItems().setAll(reports);
        System.out.println(workerReportTable.getItems());
    }

    /**
     * get method for the water reports
     * @return a list of the UserReports
     */
    private Collection<UserReport> getReports() {
        Collection<UserReport> fullList;
        try {
            fullList = DatabaseFactory.getDatabase().getUserReportList();
        } catch (DatabaseException e) {
            e.printStackTrace();
            fullList = new ArrayList<>();
        }

        return fullList;

    }

    /**
     * get method for the water purity reports
     * @return a list of the PurityReports
     */
    private Collection<WorkerReport> getPurityReports() {
        Collection<WorkerReport> fullList;
        try {
            fullList = DatabaseFactory.getDatabase().getWorkerReportList();
        } catch (DatabaseException e) {
            e.printStackTrace();
            fullList = new ArrayList<>();
        }


        return fullList;

    }

}
