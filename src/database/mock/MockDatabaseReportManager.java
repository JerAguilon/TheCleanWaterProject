package database.mock;

import model.HistoricalReport;
import model.Report;
import model.UserReport;
import model.WorkerReport;

import java.util.*;

/*
 * Created by jeremy on 10/11/16.
 */
class MockDatabaseReportManager {
    private static long reportNumber = 0;

    private static final Map<Long, UserReport> reports = new HashMap<>();
    private static final Set<String> locations = new HashSet<>();

    private static final Map<Long, WorkerReport> workerReports = new HashMap<>();
    private static final Set<String> workerLocations = new HashSet<>();

    private static final Map<Long, HistoricalReport> histReports = new HashMap<>();


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

    public static boolean addWorkerReport(WorkerReport report) {
        if (workerLocations.contains(report.getLocationColumn())) {
            return false;
        } else {
            report.setIdColumn(Long.toString(reportNumber));
            workerReports.put(reportNumber, report);
            reportNumber++;
            return true;

        }
    }

    public static boolean addUserReport(UserReport report) {
        if (locations.contains(report.getLocationColumn())) {
            return false;
        } else {
            report.setIdColumn(Long.toString(reportNumber));
            reports.put(reportNumber, report);
            reportNumber++;
            return true;
        }
    }

    public static boolean addHistoricalReport(HistoricalReport report) {
        histReports.put(reportNumber, report);
        reportNumber++;
        return true;
    }

    public static Collection<UserReport> getUserReportList() {
        return reports.values();
    }

    public static Collection<WorkerReport> getWorkerReportList() {
        return workerReports.values();
    }

    public static Collection<HistoricalReport> getHistoricalReportList() {return histReports.values();}

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
        reports.put(Long.parseLong(report.getIdColumn()), newReport);

        return true;
    }
}
