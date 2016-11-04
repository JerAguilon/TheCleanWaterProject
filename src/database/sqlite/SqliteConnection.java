package database.sqlite;

import java.sql.*;


/*
 * Created by jeremy on 10/17/16.
 */
@SuppressWarnings("TryWithIdenticalCatches")
class SqliteConnection {

    public static Connection connect() {

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:TheCleanWaterProject.sqlite");
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
