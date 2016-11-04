package database.sqlite;

import java.sql.*;


/*
 * Created by jeremy on 10/17/16.
 */
class SqliteConnection {

    public static Connection connect() {

        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:TheCleanWaterProject.sqlite");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
