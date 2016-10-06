package model;

import java.time.LocalDateTime;

/*
 * Created by jeremy on 10/6/16.
 */
public abstract class Report implements Comparable<Report> {
    private LocalDateTime dateTime;
    private long reportID;
    private String reporterName;
    private String location;


    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public long getReportID() {
        return reportID;
    }

    public void setReportID(long reportID) {
        this.reportID = reportID;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
