package database;

import apploader.ConfigLoader;
import database.mock.MockDatabaseWrapper;
import database.sqlite.SqliteDatabaseWrapper;

/**
 * Created by jeremy on 10/9/16.
 */
public class DatabaseFactory {

    public static IDatabase getDatabase() {


        if (ConfigLoader.CONFIG.getProperty("database").equals("mock")) {
            return MockDatabaseWrapper.mockDatabase;
        }

        if (ConfigLoader.CONFIG.getProperty("database").equals("sqlite")) {
            return SqliteDatabaseWrapper.database;
        }

        //should never get here
        return null;
    }


}
