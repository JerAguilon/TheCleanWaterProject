package database.mock;

import model.Report;
import model.UserReport;

import java.util.*;

/**
 * Created by jeremy on 10/11/16.
 */
public class MockDatabaseReportManager {
    private static long reportNumber = 0;

    private static Map<Long, UserReport> reports = new HashMap<>();
    private static Set<String> locations = new HashSet<>();


    public static Report getReport(long id) {
        return reports.get(id);
    }

    public static Report getReport(Report report) {
        for (Report value : reports.values()) {
            if (report.equals(value)) {
                return value;
            }
        }

        return null;
    }

    public static boolean addReport(UserReport report) {
        if (locations.contains(report.getLocationColumn())) {
            return false;
        } else {
            report.setIdColumn(reportNumber);
            reports.put(reportNumber, report);
            reportNumber++;
            return true;
        }
    }

    public static Collection<UserReport> getReportList() {
        return reports.values();
    }

    public static boolean deleteReport(long id) {
        if (!reports.containsKey(id)) return false;
        reports.remove(id);

        return true;
    }

    public static boolean deleteReport(Report report) {
        if (reports.containsKey(report.getIdColumn())) return false;
        reports.remove(report.getIdColumn());

        return true;
    }

    public static boolean modifyReport(UserReport report, UserReport newReport) {
        if (!reports.containsKey(report.getIdColumn())) return false;

        newReport.setIdColumn(report.getIdColumn());
        newReport.setDateColumn(report.getDateTime());
        reports.put(report.getIdColumn(), newReport);

        return true;
    }
}
