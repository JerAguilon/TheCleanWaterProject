package database;

import exceptions.UserException;
import model.*;

import java.util.Collection;
import java.util.List;

/**
 * Created by jeremy on 9/21/16.
 */
public interface IDatabase {
    User getUser(String name);

    void addUser(User user) throws UserException;

    boolean validate(String username, String pass);

    Report getReport(long id);
    Report getReport(Report report);
    boolean addReport(Report report);
    Collection<Report> getReportList();
    boolean deleteReport(long id);
    boolean deleteReport(Report report);
    boolean modifyReport(Report report, Report newReport);

}
