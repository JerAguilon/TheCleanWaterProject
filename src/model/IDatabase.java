package model;

import java.util.List;

/**
 * Created by jeremy on 9/21/16.
 */
public interface IDatabase {
    User getUser(String name);
    boolean addUser(String username, int passHash, AuthorizationLevel auth, Profile profile);

    Report getReport(long id);
    Report getReport(Report report);
    boolean addReport(Report report);
    List<Report> getReportList();
    boolean deleteReport(long id);
    boolean deleteReport(Report report);

    boolean modifyReport(Report report, Report newReport);

}
