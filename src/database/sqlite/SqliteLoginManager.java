package database.sqlite;

import java.sql.*;

/**
 * Created by jeremy on 10/17/16.
 */
public class SqliteLoginManager extends Connectable {

    public static void main(String[] args) throws SQLException {
        SqliteLoginManager lm = new SqliteLoginManager();

    }

    public boolean validate(String username, String pass) throws SQLException {
        Connection connection = SqliteConnection.connect();
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
