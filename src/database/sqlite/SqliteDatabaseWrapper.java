package database.sqlite;

import database.IDatabase;
import model.AuthorizationLevel;
import model.Profile;
import model.Report;
import model.User;

import java.util.Collection;

/**
 * Created by jeremy on 10/17/16.
 */
public class SqliteDatabaseWrapper implements IDatabase {
    public SqliteDatabaseWrapper database = new SqliteDatabaseWrapper();


    //FIELDS
    private SqliteLoginManager loginManager;



    private SqliteDatabaseWrapper() {
        loginManager = new SqliteLoginManager();
    }



    @Override
    public User getUser(String name) {
        return null;
    }

    @Override
    public boolean addUser(String username, int passHash, AuthorizationLevel auth, Profile profile) {
        return false;
    }

    @Override
    public boolean validate(String username, String pass) {
        return false;
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

    //TODO: Implement dependency injection
}
