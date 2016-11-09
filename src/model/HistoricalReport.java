package model;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

/**
 * Created by Ragavi on 11/6/2016.
 */
public class HistoricalReport {
    private String location;
    private HistoricalReportType hrt;
    private Date date;
    private long ppm;


    private final SimpleStringProperty locationColumnProperty = new SimpleStringProperty();
    private final SimpleStringProperty hrtColumnProperty = new SimpleStringProperty();
    private final SimpleStringProperty dateColumnProperty = new SimpleStringProperty();

    public HistoricalReport(String location, HistoricalReportType hrt, Date date, long ppm) {
        this.location = location;
        this.hrt = hrt;
        this.date = date;
        this.ppm = ppm;

        this.locationColumnProperty.set(location.toString());
        this.hrtColumnProperty.set(hrt.toString());
        this.dateColumnProperty.set(date.toString());
    }

    public String getLocation() {
        return this.location;
    }

    public HistoricalReportType getHistoricalReportType() {
        return this.hrt;
    }

    public Date getDate() {
        return this.date;
    }

    public long getPPM() {return this.ppm; }

    private String getLocationColumn() {
        return locationColumnProperty.get();
    }

    private String getHistoricalReportTypeColumn() {
        return hrtColumnProperty.get();
    }

    private String getDateColumn() {
        return dateColumnProperty.get();
    }

    public String toString() {
        return String.format("Location: %s, ReportType: %s, ppm: %d", location, hrt, ppm);
    }

}
