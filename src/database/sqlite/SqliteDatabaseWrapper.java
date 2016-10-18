package database.sqlite;

import database.IDatabase;
import exceptions.UserException;
import model.AuthorizationLevel;
import model.Profile;
import model.Report;
import model.User;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by jeremy on 10/17/16.
 */
public class SqliteDatabaseWrapper implements IDatabase {
    public static SqliteDatabaseWrapper database = new SqliteDatabaseWrapper();

    //FIELDS
    private SqliteLoginManager loginManager;

    private SqliteRegistrationManager registrationManager;

    //TODO: Implement dependency injection

    private SqliteDatabaseWrapper() {
        loginManager = new SqliteLoginManager();
        registrationManager = new SqliteRegistrationManager();
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
    public boolean addReport(Report report) {
        return false;
    }

    @Override
    public Collection<Report> getReportList() {
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
    public boolean modifyReport(Report report, Report newReport) {
        return false;
    }

}
