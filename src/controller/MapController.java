/*
package controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.net.URL;
import java.util.*;

import database.DatabaseFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Report;
import model.WaterReport;

public class MapController implements Initializable, MapComponentInitializedListener {

    @FXML
    private GoogleMapView mapView;

    @FXML
    private TextField addressTextField;

    private GoogleMap map;

    private GeocodingService geocodingService;

    private StringProperty address = new SimpleStringProperty();

    @FXML
    Button doneButton;





    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
        address.bind(addressTextField.textProperty());

    }


    @Override
    public void mapInitialized() {

        geocodingService = new GeocodingService();
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(33.749, -84.388))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);

        map = mapView.createMap(mapOptions);
        List<Report> markerList = this.getReports();
        String dateTime;
        String reporterName;
        String location;
        String type;
        String condition;

        for (Report r : markerList) {

            dateTime = r.getDateColumn();
            reporterName = r.getUsernameColumn();
            location = r.getLocationColumn();
            type = ((WaterReport) r).getType().toString();
            condition = ((WaterReport) r).getCondition().toString();

            List<String> coordinateList = Arrays.asList(location.split(","));

            LatLong newLoc = new LatLong(Double.parseDouble(coordinateList.get(0)), Double.parseDouble(coordinateList.get(1)));
            MarkerOptions markerOptions1 = new MarkerOptions();
            markerOptions1.position(newLoc);

            Marker newLocMarker = new Marker(markerOptions1);
            map.addMarker(newLocMarker);

            InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
            infoWindowOptions.content("<h2>Report Info</h2>"
                    + "Date/Time: + " + dateTime + "<br>"
                    + "Reporter:" + reporterName + "<br>"
                    + "Type:" + type + "<br>"
                    + "Condition:" + condition + "<br>");

            InfoWindow newLocInfoWindow = new InfoWindow(infoWindowOptions);
            newLocInfoWindow.open(map, newLocMarker);

    }



    }



    @FXML
    public void returnToMain() throws Exception {
        try {
            Stage stage = (Stage) doneButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));

            Scene scene = new Scene(root);
            //scene.getStylesheets().add("css/stylesheet.css");
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void addressTextFieldAction(ActionEvent event) {
        geocodingService.geocode(address.get(), (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if( status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if( results.length > 1 ) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            }

            map.setCenter(latLong);

        });

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
*/
