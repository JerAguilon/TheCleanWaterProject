package database.mock;

import database.IDatabase;
import database.responses.DatabaseException;
import exceptions.UserException;
import model.*;

import java.util.*;

/*
 * Created by jeremy on 9/21/16.
 */
public class MockDatabaseWrapper implements IDatabase {
    private final Map<String, User> database = new HashMap<>();

    public static final MockDatabaseWrapper mockDatabase = new MockDatabaseWrapper();

    private MockDatabaseWrapper(){

        Profile profile = new Profile("Test@test.com", "1234", "Sr.");
        User user;
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
        //User test = getUser(user.USERNAME);
        if (getUser(user.USERNAME) != null) {
            throw new UserException("User already exists");
        }

        database.put(user.USERNAME, user);
    }

    @Override
    public boolean validate(String username, String pass) {
        User user = getUser(username);

        return user != null && user.PASSWORD.equals(pass);

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
        return MockDatabaseReportManager.addUserReport(report);
    }

    @Override
    public boolean addWorkerReport(WorkerReport report) throws DatabaseException {
        return MockDatabaseReportManager.addWorkerReport(report);
    }

// --Commented out by Inspection START (11/15/2016 4:47 PM):
//    public boolean addHistoricalReport(HistoricalReport report) throws DatabaseException {
//        return MockDatabaseReportManager.addHistoricalReport(report);
//    }
// --Commented out by Inspection STOP (11/15/2016 4:47 PM)

    @Override
    public Collection<UserReport> getUserReportList() {
        return MockDatabaseReportManager.getUserReportList();
    }

    @Override
    public Collection<WorkerReport> getWorkerReportList() throws DatabaseException {
        return MockDatabaseReportManager.getWorkerReportList();
    }

// --Commented out by Inspection START (11/15/2016 4:47 PM):
//    public Collection<HistoricalReport> getHistoricalReportList() throws DatabaseException {
//        return MockDatabaseReportManager.getHistoricalReportList();
//    }
// --Commented out by Inspection STOP (11/15/2016 4:47 PM)

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
