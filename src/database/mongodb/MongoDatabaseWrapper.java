package database.mongodb;

import database.IDatabase;
import exceptions.UserException;
import model.Report;
import model.User;

import java.util.Collection;

/**
 * Created by jeremy on 10/23/16.
 */
public class MongoDatabaseWrapper implements IDatabase {
    private final String QUERYADDRESS;

    public MongoDatabaseWrapper(String queryAddress) {
        this.QUERYADDRESS = queryAddress;
    }


    @Override
    public User getUser(String name) {
        return null;
    }

    @Override
    public void addUser(User user) throws UserException {

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
}
