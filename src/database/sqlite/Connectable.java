package database.sqlite;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by jeremy on 10/17/16.
 */
public class Connectable {

    Connection connection;

    public Connectable () {
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
