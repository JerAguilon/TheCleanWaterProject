package database.sqlite;

import model.Report;
import model.UserReport;
import model.WaterSourceCondition;
import model.WaterSourceType;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by jeremy on 10/18/16.
 */
public class SqliteReportManager extends Connectable {

    public Collection<UserReport> getReportList() throws SQLException {
        List<UserReport> output = new ArrayList<>();


        Connection connection = SqliteConnection.connect();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM userReports";

        try {
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String creationDate = resultSet.getString(1);
                String id  = resultSet.getString(2);
                String username = resultSet.getString(3);
                String location = resultSet.getString(4);
                int type = resultSet.getInt(5);
                int condition = resultSet.getInt(6);

                UserReport report = new UserReport(creationDate, username,
                        location, WaterSourceType.values()[type], WaterSourceCondition.values()[condition], id);
                output.add(report);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            resultSet.close();
        }

        return output;
    }


    public boolean addReport(Report report) {
        try {
            if (report instanceof UserReport) {
                addReport((UserReport) report);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }

    public void addReport(UserReport report) throws SQLException {
        int condition = report.getCondition().ordinal();
        int type = report.getType().ordinal();
        String location = report.getLocationColumn();
        String date = report.getDateColumn();
        String username = report.getUsernameColumn();

        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO userReports "
                + "(creationDate, username, location, type, condition) VALUES"
                + "(?,?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(query);


            preparedStatement.setString(1, date);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, location);
            preparedStatement.setInt(4, type);
            preparedStatement.setInt(5, condition);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }


    }

}
