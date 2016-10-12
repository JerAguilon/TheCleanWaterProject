package model;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * Created by jeremy on 10/6/16.
 */
public abstract class Report {

    private LocalDateTime dateTime;
    private final SimpleStringProperty dateColumnProperty = new SimpleStringProperty();
    private final SimpleLongProperty idColumnProperty = new SimpleLongProperty();
    private final SimpleStringProperty usernameColumnProperty = new SimpleStringProperty();
    private final SimpleStringProperty locationColumnProperty = new SimpleStringProperty();


    public Report(String reporterName, String location) {
        this.dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateColumnProperty.set(dateTime.format(formatter));

        this.usernameColumnProperty.set(reporterName);
        this.locationColumnProperty.set(location);
    }

    //used for lookup in mockdatabase
    public Report(long ID) {
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

    public long getIdColumn() {
        return idColumnProperty.get();
    }

    public void setIdColumn(long reportID) {
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
        return (int) this.idColumnProperty.get();
    }
}
