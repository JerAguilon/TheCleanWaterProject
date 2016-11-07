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


    private final SimpleStringProperty locationColumnProperty = new SimpleStringProperty();
    private final SimpleStringProperty hrtColumnProperty = new SimpleStringProperty();
    private final SimpleStringProperty dateColumnProperty = new SimpleStringProperty();

    public HistoricalReport(String location, HistoricalReportType hrt, Date date) {
        this.location = location;
        this.hrt = hrt;
        this.date = date;

        this.locationColumnProperty.set(location.toString());
        this.hrtColumnProperty.set(hrt.toString());
        this.dateColumnProperty.set(date.toString());
    }

    public String getLocation() {
        return this.location;
    }

    private HistoricalReportType getHistoricalReportType() {
        return this.hrt;
    }

    private Date getDate() {
        return this.date;
    }

    private String getLocationColumn() {
        return locationColumnProperty.get();
    }

    private String getHistoricalReportTypeColumn() {
        return hrtColumnProperty.get();
    }

    private String getDateColumn() {
        return dateColumnProperty.get();
    }
}
