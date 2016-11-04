package database.mongodb;

import database.IDatabase;
import database.responses.DatabaseException;
import exceptions.UserException;
import model.WorkerReport;
import model.Report;
import model.User;
import model.UserReport;
import org.json.JSONException;

import java.io.IOException;
import java.util.Collection;

/*
 * Created by jeremy on 10/23/16.
 */
public class MongoDatabaseWrapper implements IDatabase {

    private final MongoUserManager userManager;
    private final MongoUserReportManager userReportManager;

    public MongoDatabaseWrapper(String queryAddress) {
        //String QUERYADDRESS = queryAddress;

        userManager = new MongoUserManager(queryAddress + "/api/users");
        userReportManager = new MongoUserReportManager(queryAddress + "/api/userreports");
    }


    @Override
    public User getUser(String name) {
        return null;
    }

    @Override
    public void addUser(User user) throws UserException, DatabaseException {
        try {
            userManager.addUser(user);
        } catch (IOException e) {
            e.printStackTrace();
            throw new DatabaseException(e.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public boolean validate(String username, String pass) throws DatabaseException {
        try {
            userManager.authenticate(username, pass);
        } catch (IOException e) {
            e.printStackTrace();
            throw new DatabaseException(e.getMessage());
        } catch (JSONException e) {
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
        } catch (IOException e) {
            e.printStackTrace();
            throw new DatabaseException(e.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
            throw new DatabaseException(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean addWorkerReport(WorkerReport report) throws DatabaseException {
        return false;
    }

    @Override
    public Collection<UserReport> getUserReportList() throws DatabaseException {
        try {
            return userReportManager.getReports();
        } catch (IOException e) {
            e.printStackTrace();
            throw new DatabaseException(e.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
            throw new DatabaseException(e.getMessage());
        }
        //should never get here
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
