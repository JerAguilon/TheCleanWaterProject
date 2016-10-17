package database.sqlite;

import java.sql.*;

/**
 * Created by jeremy on 10/17/16.
 */
public class SqliteLoginManager {
    Connection connection;

    public SqliteLoginManager () {
        connection = SqliteConnection.connect();
        if (connection == null) System.exit(1);
    }

    public boolean isConnected() {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args) throws SQLException {
        SqliteLoginManager lm = new SqliteLoginManager();

        System.out.println(lm.validate("user", "pass"));
    }

    public boolean validate(String username, String pass) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM userlist WHERE username = ? and password = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, pass);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            resultSet.close();
        }

        return false;
    }
}
