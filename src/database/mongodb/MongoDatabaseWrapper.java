package database.mongodb;

import database.IDatabase;
import database.responses.DatabaseException;
import exceptions.UserException;
import model.*;
import org.json.JSONException;

import java.io.IOException;
import java.util.Collection;

/*
 * Created by jeremy on 10/23/16.
 */
public class MongoDatabaseWrapper implements IDatabase {

    private final MongoUserManager userManager;
    private final MongoUserReportManager userReportManager;
    private final MongoWorkerReportManager workerReportManager;
    public MongoDatabaseWrapper() {
        //String QUERYADDRESS = queryAddress;

        userManager = new MongoUserManager("http://localhost:8080" + "/api/users");
        userReportManager = new MongoUserReportManager("http://localhost:8080" + "/api/userreports");
        workerReportManager = new MongoWorkerReportManager("http://localhost:8080" + "/api/workerreports");
    }


    @Override
    public User getUser(String name) throws DatabaseException {
        try {
            return userManager.getUser(name);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public void addUser(User user) throws UserException, DatabaseException {
        try {
            userManager.addUser(user);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public boolean validate(String username, String pass) throws DatabaseException {
        try {
            userManager.authenticate(username, pass);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            throw new DatabaseException(e.getMessage());
        }

        return true;
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
    public boolean addUserReport(UserReport report) throws DatabaseException {
        try {
            userReportManager.addUserReport(report);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            throw new DatabaseException(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean addWorkerReport(WorkerReport report) throws DatabaseException {
        try {
            workerReportManager.addReport(report);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            throw new DatabaseException(e.getMessage());
        }
        return true;
    }

    @Override
    public Collection<UserReport> getUserReportList() throws DatabaseException {
        try {
            return userReportManager.getReports();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            throw new DatabaseException(e.getMessage());
        }
        //should never get here
    }

    @Override
    public Collection<WorkerReport> getWorkerReportList() throws DatabaseException {
        try {
            return workerReportManager.getReports();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            throw new DatabaseException(e.getMessage());
        }
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
