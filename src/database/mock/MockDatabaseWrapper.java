package database.mock;

import database.IDatabase;
import exceptions.UserException;
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
        User user = null;
        try {
            user = new User("user", "pass", AuthorizationLevel.ADMINISTRATOR, profile);
        } catch (UserException e) {
            e.printStackTrace();
            return;
        }
        database.put("user", user);
    }

    @Override
    public User getUser(String name) {
        return database.get(name);
    }

    @Override
    public void addUser(User user) throws UserException {
        User test = getUser(user.USERNAME);
        if (getUser(user.USERNAME) != null) {
            throw new UserException("User already exists");
        }

        database.put(user.USERNAME, user);
    }

    @Override
    public boolean validate(String username, String pass) {
        User user = getUser(username);

        if (user == null) {
            return false;
        }

        if (user.PASSWORD.equals(pass)) {
            return true;
        }

        return false;
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
    public boolean addUserReport(UserReport report) {
        return MockDatabaseReportManager.addReport(report);
    }

    @Override
    public Collection<UserReport> getReportList() {
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
    public boolean modifyUserReport(UserReport report, UserReport newReport) {
        return MockDatabaseReportManager.modifyReport(report, newReport);
    }
}