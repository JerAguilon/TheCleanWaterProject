package controller;

import apploader.LocalSession;
import database.DatabaseFactory;
import database.responses.DatabaseException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.HistoricalReport;
import model.HistoricalReportType;
import model.WorkerReport;

import java.util.*;

/**
 * Created by Ben Radock on 11/8/2016.
 */
public class GraphScreenController {
    @FXML
    Label graphTitle;

    @FXML
    TextField graphYear;

    @FXML
    TextField graphLocation;

    @FXML
    ComboBox graphVirusContam;

    @FXML
    ScatterChart chart;

    @FXML
    CategoryAxis xAxis;

    @FXML
    NumberAxis yAxis;

    @FXML
    /*
      initializes the registration screen
     */
    public void initialize(){
        //graphTitle.setText(graphLocation.getText());
        Collection<WorkerReport> reports = null;
        try {
            reports = DatabaseFactory.getDatabase().getWorkerReportList();
        } catch (DatabaseException e) {

        }
        XYChart.Series series1 = new XYChart.Series();

        chart.getXAxis().setLabel("Month");
        chart.getYAxis().setLabel("PPM");
        chart.setTitle("History Graph for " + LocalSession.currentGraphLocation + " in " + LocalSession.currentGraphYear);

        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(months)));

        yAxis.setLowerBound(0);
        yAxis.setUpperBound(1000000);
        yAxis.setTickUnit(10000);
        for(WorkerReport report : reports) {

            //Calendar cal = Calendar.getInstance();
            //cal.setTime(report.getDate());


            //int year = cal.get(Calendar.YEAR);
            int year = report.getDateTime().getYear();
            //int month = cal.get(Calendar.MONTH);
            int month = report.getDateTime().getMonthValue();
            if(LocalSession.currentGraphYear == year && LocalSession.currentGraphLocation.equals(report.getLocationColumn())) {
                if(LocalSession.currentHrtType.toString().equals("Virus")) {
                    series1.getData().add(new XYChart.Data(months[month - 1], Integer.parseInt(report.getVirusPPM())));
                } else {
                    series1.getData().add(new XYChart.Data(months[month - 1], Integer.parseInt(report.getContaminantPPM())));
                }
            }
        }

        chart.getData().addAll(series1);
    }

    @FXML
    /*
      returns the user to the main screen
     */
    private void returnToMainScreen() {
        try {
            Stage stage = (Stage) chart.getScene().getWindow();
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
