package database;

import model.AuthorizationLevel;
import model.Profile;
import model.Report;
import model.User;

import java.util.Collection;
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
    Collection<Report> getReportList();
    boolean deleteReport(long id);
    boolean deleteReport(Report report);

    boolean modifyReport(Report report, Report newReport);

}
