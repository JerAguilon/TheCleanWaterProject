package database.sqlite;

import java.sql.*;

/*
 * Created by jeremy on 10/17/16.
 */
@Deprecated
class SqliteLoginManager extends Connectable {

    public boolean validate(String username, String pass) throws SQLException {
        Connection connection = SqliteConnection.connect();
        String query = "SELECT * FROM userlist WHERE username = ? and password = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, pass);

            if (resultSet.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
