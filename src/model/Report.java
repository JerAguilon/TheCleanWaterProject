package model;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import model.interfaces.Loggable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/*
 * Created by jeremy on 10/6/16.
 */
public abstract class Report implements Loggable {

    private LocalDateTime dateTime;
    private final SimpleStringProperty dateColumnProperty = new SimpleStringProperty();
    private final SimpleStringProperty idColumnProperty = new SimpleStringProperty();
    private final SimpleStringProperty usernameColumnProperty = new SimpleStringProperty();
    private final SimpleStringProperty locationColumnProperty = new SimpleStringProperty();

    /**
     * Constructor for a Report
     * @param dateTimeStr a string that has the date and the time
     * @param reporterName the name of the reporter
     * @param location the location of the reporter
     * @param id the id of the reporter
     */
    public Report(String dateTimeStr, String reporterName, String location, String id) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.dateTime = LocalDateTime.parse(dateTimeStr, formatter);
        dateColumnProperty.set(dateTime.format(formatter));
        this.usernameColumnProperty.set(reporterName);
        this.locationColumnProperty.set(location);
        this.idColumnProperty.set(id);
    }


    /**
     * Creates a report with the reporter name and the location
     * @param reporterName the name of the reporter
     * @param location the location of the reporter
     */
    public Report(String reporterName, String location) {
        this.dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateColumnProperty.set(dateTime.format(formatter));

        this.usernameColumnProperty.set(reporterName);
        this.locationColumnProperty.set(location);
    }

    //used for lookup in mockdatabase
    public Report(String ID) {
        this.idColumnProperty.set(ID);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDateColumn() {
        return dateColumnProperty.get();
    }

    public void setDateColumn(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateColumnProperty.set(dateTime.format(formatter));
    }

    public String getIdColumn() {
        return idColumnProperty.get();
    }

    public void setIdColumn(String reportID) {
        this.idColumnProperty.set(reportID);
    }

    public String getUsernameColumn() {
        return this.usernameColumnProperty.get();
    }

    public void setUsernameColumn(String reporterName) {
        this.usernameColumnProperty.set(reporterName);
    }

    public String getLocationColumn() {
        return locationColumnProperty.get();
    }

    public void setLocationColumn(String location) {
        locationColumnProperty.set(location);
    }

    public boolean equals(Object other) {
        if (!(other instanceof Report)) {
            return false;
        }

        Report that = (Report) other;

        if (this.idColumnProperty.get() == that.idColumnProperty.get()) return true;

        return false;
    }

    public int hashCode() {
        return this.idColumnProperty.get().hashCode();
    }

    public List<String> toLogList() {
        List<String> output = new ArrayList<>();

        String ID = String.format("\tReport ID:  %s", getIdColumn());
        String username = String.format("\tUsername: %s", getUsernameColumn());
        String location = String.format("\tLocation: %s", getLocationColumn());

        output.add(ID);
        output.add(username);
        output.add(location);

        return output;
    }
}
