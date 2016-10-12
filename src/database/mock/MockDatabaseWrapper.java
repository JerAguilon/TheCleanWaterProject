package database.mock;

import database.IDatabase;
import model.*;

import java.util.*;

/**
 * Created by jeremy on 9/21/16.
 */
public class MockDatabaseWrapper implements IDatabase {
    public Map<String, User> database = new HashMap<>();

    public static MockDatabaseWrapper mockDatabase = new MockDatabaseWrapper();

    private MockDatabaseWrapper(){

        Profile profile = new Profile("Test@test.com", "1234", "Sr.");
        User user = new User("user", "pass".hashCode(), AuthorizationLevel.ADMINISTRATOR, profile);
        database.put("user", user);
    }

    @Override
    public User getUser(String name) {
        return database.get(name);
    }

    @Override
    public boolean addUser(String username, int passHash, AuthorizationLevel auth, Profile profile) {

        User newUser =  new User(username, passHash, auth, profile);

        if (getUser(username) != null) {
            return false;
        }

        database.put(username, newUser);

        return true;
    }

    @Override
    public Report getReport(long id) {
        return MockDatabaseReportManager.getReport(id);
    }

    @Override
    public Report getReport(Report report) {
        return MockDatabaseReportManager.getReport(report);
    }

    @Override
    public boolean addReport(Report report) {
        return MockDatabaseReportManager.addReport(report);
    }

    @Override
    public Collection<Report> getReportList() {
        return MockDatabaseReportManager.getReportList();
    }

    @Override
    public boolean deleteReport(long id) {
        return MockDatabaseReportManager.deleteReport(id);
    }

    @Override
    public boolean deleteReport(Report report) {
        return MockDatabaseReportManager.deleteReport(report);
    }

    @Override
    public boolean modifyReport(Report report, Report newReport) {
        return MockDatabaseReportManager.modifyReport(report, newReport);
    }
}
