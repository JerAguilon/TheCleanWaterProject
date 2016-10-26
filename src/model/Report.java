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

    /**
     * gets the date and time
     * @return the LocalDateTime
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * gets the DateColumnProperty String value
     * @return the dateColumnProperty String value
     */
    public String getDateColumn() {
        return dateColumnProperty.get();
    }

    /**
     * sets the date and time to a given dateTime
     * @param dateTime the date and time you would like to set it to
     */
    public void setDateColumn(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateColumnProperty.set(dateTime.format(formatter));
    }

    /**
     * gets the IdColumnProperty String value
     * @return the IdColumn String value
     */
    public String getIdColumn() {
        return idColumnProperty.get();
    }

    /**
     * sets the report ID to a given reportID
     * @param reportID the reportID you would like to set it to as a String
     */
    public void setIdColumn(String reportID) {
        this.idColumnProperty.set(reportID);
    }

    /**
     * gets the UsernameColumnProperty String value
     * @return the UsernameColumn String value
     */
    public String getUsernameColumn() {
        return this.usernameColumnProperty.get();
    }

    /**
     * sets the name of the reporter
     * @param reporterName the name of the reporter as a String
     */
    public void setUsernameColumn(String reporterName) {
        this.usernameColumnProperty.set(reporterName);
    }

    /**
     * gets the LocationColumnProperty String value
     * @return the LocationColumn String value
     */
    public String getLocationColumn() {
        return locationColumnProperty.get();
    }

    /**
     * sets the location of the reporter
     * @param location the location of the reporter
     */
    public void setLocationColumn(String location) {
        locationColumnProperty.set(location);
    }

    /**
     * checks if two objects are equal to one another
     * @param other the Object that we are comparing the calling object to
     * @return true if the objects are equal and false if they are not
     */
    public boolean equals(Object other) {
        if (!(other instanceof Report)) {
            return false;
        }

        Report that = (Report) other;

        if (this.idColumnProperty.get() == that.idColumnProperty.get()) return true;

        return false;
    }

    /**
     * calculates the hashcode
     * @return the hashcode as an int
     */
    public int hashCode() {
        return this.idColumnProperty.get().hashCode();
    }

    /**
     * logs the report
     * @return a List of Strings of the log
     */
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
