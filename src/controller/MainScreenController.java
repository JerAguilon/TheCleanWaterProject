package controller;

import apploader.LocalSession;
import controller.interfaces.IMainScreenController;
import database.DatabaseFactory;
import database.responses.DatabaseException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    @FXML
    TextField graphYear;

    @FXML
    TextField graphLocation;

    @FXML
    ComboBox graphVirusContam;

    @FXML
    Button displayGraph;

// --Commented out by Inspection START (11/15/2016 4:47 PM):
//    @FXML
//    private
//    Button histReportButton;
// --Commented out by Inspection STOP (11/15/2016 4:47 PM)

    @FXML
    TableColumn contaminantPPMColumn;

    @FXML
    TableColumn virusPPMColumn;

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
      initializes the registration screen
     */
    public void initialize() {
        List<String> values = new ArrayList<>();

        graphVirusContam.getItems().clear();
        for (HistoricalReportType hrt : HistoricalReportType.values()) {
            values.add(hrt.toString());
        }
        graphVirusContam.getItems().addAll(values);

        graphVirusContam.getSelectionModel().select(0);
    }


    @FXML
    public void displayGraph() {
        /*if(graphYear.getText().equals("") || graphLocation.getText().equals("")) {

        }*/
        if(LocalSession.currentAuth != AuthorizationLevel.MANAGER) {
            displayGraph.setText("Must be a manager to create graph.");
        } else if(graphLocation.getText().equals("") && graphYear.getText().equals("")) {
            sendAlert("Multiple fields empty. Please fill in fields to proceed.");
        } else if(graphLocation.getText().equals("")) {
            sendAlert("Location field is empty. Please fill in field to proceed.");
        } else if (graphYear.getText().equals("")) {
            sendAlert("Year field is empty. Please fill in field to proceed.");
        }
        else
        {
            LocalSession.currentGraphLocation = graphLocation.getText();
            LocalSession.currentGraphYear = Integer.parseInt(graphYear.getText());
            LocalSession.currentHrtType = HistoricalReportType.match(graphVirusContam.getSelectionModel().getSelectedItem().toString());
            try {
                Stage stage = (Stage) profileedit.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/view/GraphScreen.fxml"));

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
// --Commented out by Inspection START (11/15/2016 4:47 PM):
//    @FXML
//    /*
//        allows the user to view the SubmitHistoricalReportScreen
//     */
//    public void submitHistoricalReport() {
//        if(LocalSession.currentAuth != AuthorizationLevel.MANAGER) {
//            histReportButton.setText("Must be a manager to create graph.");
//        } else {
//            try {
//                Stage stage = (Stage) logout.getScene().getWindow();
//                Parent root = FXMLLoader.load(getClass().getResource("/view/SubmitHistoricalReportScreen.fxml"));
//
//                Scene scene = new Scene(root);
//                scene.getStylesheets().add("css/stylesheet.css");
//                stage.setScene(scene);
//                stage.show();
//            } catch(Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
// --Commented out by Inspection STOP (11/15/2016 4:47 PM)

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
    /*
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
        //LocalSession.currentAuth = AuthorizationLevel.WORKER;
        if(LocalSession.currentAuth.equals(AuthorizationLevel.USER)) {
            submitWorkerReport.setText("Users do not have access");
        }
        else {
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
    /*
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
        contaminantPPMColumn.setCellValueFactory(new PropertyValueFactory<WorkerReport, Integer>("contaminantPPMColumn"));
        virusPPMColumn.setCellValueFactory(new PropertyValueFactory<WorkerReport, Integer>("virusPPMColumn"));


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

    /**
     * sends the submission error message
     * @param message the message that should be printed as a String
     *
     */
    private void sendAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Submission Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
