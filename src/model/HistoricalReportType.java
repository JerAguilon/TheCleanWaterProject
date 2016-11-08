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

    /**
     * Matches the String typed in to an appropriate enum value
     * @param value the String that entered in by the user
     * @return the HistoricalReportType enum value that matches the String
     */
    public static HistoricalReportType match(String value) {
        if(value.equals("Virus")) return VIRUS;
        if(value.equals("Contaminant")) return CONTAMINANT;

        return null;
    }
}
