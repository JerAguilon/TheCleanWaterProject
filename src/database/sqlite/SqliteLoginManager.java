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
}
