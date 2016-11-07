package model;

/**
 * Created by Ragavi on 11/6/2016.
 */
public enum HistoricalReportType {
    VIRUS("Virus"), CONTAMINANT("Contaminant");

    private final String historicalReportType;

    HistoricalReportType (String historicalReportType) {
        this.historicalReportType = historicalReportType;
    }

    public String toString() {
        return historicalReportType;
    }


}
