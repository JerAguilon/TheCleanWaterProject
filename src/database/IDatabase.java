package database;

import database.responses.DatabaseException;
import exceptions.UserException;
import model.*;

import java.util.Collection;

/**
 * Created by jeremy on 9/21/16.
 */
public interface IDatabase {
    User getUser(String name);

    void addUser(User user) throws UserException, DatabaseException;

    boolean validate(String username, String pass) throws DatabaseException;

    Report getReport(long id);
    Report getReport(Report report);
    boolean addUserReport(UserReport report) throws DatabaseException;
    boolean addWorkerReport(WorkerReport report) throws DatabaseException;
    Collection<UserReport> getUserReportList() throws DatabaseException;
    Collection<WorkerReport> getPurityReportList() throws DatabaseException;
    boolean deleteReport(long id);
    boolean deleteReport(Report report);
    boolean modifyUserReport(UserReport report, UserReport newReport);

}
