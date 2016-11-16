package model;

import javafx.beans.property.SimpleStringProperty;
import model.interfaces.Loggable;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * Created by jeremy on 10/6/16.
 */
public abstract class Report implements Loggable {

    private Date date;
    private String id;
    private String username;
    private String location;

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
    Report(String dateTimeStr, String reporterName, String location, String id) throws ParseException {

        this.date = DateGenerator.generateDate(dateTimeStr);
        dateColumnProperty.set(DateGenerator.dateToJavaString(date));

        this.username = reporterName;
        this.location = location;
        this.id = id;

        this.usernameColumnProperty.set(reporterName);
        this.locationColumnProperty.set(location);
        this.idColumnProperty.set(id);
    }


    /**
     * Creates a report with the reporter name and the location
     * @param reporterName the name of the reporter
     * @param location the location of the reporter
     */
    Report(String reporterName, String location) {
        this.date = new Date();
        dateColumnProperty.set(DateGenerator.dateToJavaString(date));
        this.username = reporterName;
        this.location = location;
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
    public Date getDate() {
        return date;
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
     * @param date the date and time you would like to set it to
     */
    public void setDateColumn(Date date) {
        this.date = date;
        dateColumnProperty.set(DateGenerator.dateToJavaString(date));
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
        this.id = reportID;
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
        this.username = reporterName;
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
        this.location = location;
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

        return (this.idColumnProperty.get().equals(that.idColumnProperty.get()));
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
