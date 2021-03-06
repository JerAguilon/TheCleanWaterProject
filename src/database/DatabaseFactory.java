package database;

import apploader.ConfigLoader;
import database.mock.MockDatabaseWrapper;
import database.mongodb.MongoDatabaseWrapper;

/*
 * Created by jeremy on 10/9/16.
 */
public class DatabaseFactory {

    public static IDatabase getDatabase() {

        String database = ConfigLoader.CONFIG.getProperty("database");
        if (database.equals("mock")) {
            return MockDatabaseWrapper.mockDatabase;
        }

        if (database.equals("sqlite")) {
            throw new RuntimeException("use of sqlite as a data store has been removed -Jeremy");
        }

        if (database.equals("mongo")) {
            return new MongoDatabaseWrapper();
        }

        //should never get here
        return null;
    }


}
