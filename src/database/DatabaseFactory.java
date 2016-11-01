package database;

import apploader.ConfigLoader;
import database.mock.MockDatabaseWrapper;
import database.mongodb.MongoDatabaseWrapper;
import database.sqlite.SqliteDatabaseWrapper;

/**
 * Created by jeremy on 10/9/16.
 */
public class DatabaseFactory {

    public static IDatabase getDatabase() {

        String database = ConfigLoader.CONFIG.getProperty("database");
        if (database.equals("mock")) {
            return MockDatabaseWrapper.mockDatabase;
        }

        if (database.equals("sqlite")) {
            return SqliteDatabaseWrapper.database;
        }

        if (database.equals("mongo")) {
            return new MongoDatabaseWrapper("http://localhost:8080");
        }

        //should never get here
        return null;
    }


}
