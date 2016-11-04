package database.sqlite;

import database.IDatabase;
import database.responses.DatabaseException;
import exceptions.UserException;
import model.*;

import java.sql.SQLException;
import java.util.Collection;

/*
 * Created by jeremy on 10/17/16.
 */
public class SqliteDatabaseWrapper implements IDatabase {
    public static SqliteDatabaseWrapper database = new SqliteDatabaseWrapper();

    //FIELDS
    private SqliteLoginManager loginManager;

    private SqliteRegistrationManager registrationManager;

    private SqliteReportManager reportManager;
    //TODO: Implement dependency injection

    private SqliteDatabaseWrapper() {
        loginManager = new SqliteLoginManager();
        registrationManager = new SqliteRegistrationManager();
        reportManager = new SqliteReportManager();
    }



    @Override
    public User getUser(String name) {
        return registrationManager.getUser(name);
    }

    @Override
    public void addUser(User user) throws UserException {
        try {
            registrationManager.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean validate(String username, String pass) {
        try {
            return loginManager.validate(username, pass);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Report getReport(long id) {
        return null;
    }

    @Override
    public Report getReport(Report report) {
        return null;
    }

    @Override
    public boolean addUserReport(UserReport report) {
        try {
            reportManager.addReport(report);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addWorkerReport(WorkerReport report) throws DatabaseException {
        return false;
    }

    @Override
    public Collection<UserReport> getUserReportList() {
        try {
            return reportManager.getReportList();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Collection<WorkerReport> getWorkerReportList() throws DatabaseException {
        return null;
    }

    @Override
    public boolean deleteReport(long id) {
        return false;
    }

    @Override
    public boolean deleteReport(Report report) {
        return false;
    }

    @Override
    public boolean modifyUserReport(UserReport report, UserReport newReport) {
        return false;
    }
}
