package database;

import apploader.ConfigLoader;
import database.mock.MockDatabaseWrapper;
import database.mongodb.MongoDatabaseWrapper;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Janki on 11/16/2016.
 */
public class DatabaseFactoryTest {

    @Test
    public void getDatabase_WhenConfigSaysMock_MockDatabaseIsReturned() {
        ConfigLoader.CONFIG.setProperty("database", "mock");
        IDatabase database = DatabaseFactory.getDatabase();
        assertTrue(database instanceof MockDatabaseWrapper);
    }

    @Test (expected=RuntimeException.class)
    public void getDatabase_WhenConfigSaysSqlite_RunTimeExceptionIsThrown() {
        ConfigLoader.CONFIG.setProperty("database", "sqlite");
        IDatabase database = DatabaseFactory.getDatabase();
    }

    @Test
    public void getDatabase_WhenConfigSaysMongo_MongoDatabaseIsReturned() {
        ConfigLoader.CONFIG.setProperty("database", "mongo");
        IDatabase database = DatabaseFactory.getDatabase();
        assertTrue(database instanceof MongoDatabaseWrapper);
    }

    @Test
    public void getDatabase_WhenConfigSaysFoo_NullIsReturned() {
        ConfigLoader.CONFIG.setProperty("database", "foo");
        IDatabase database = DatabaseFactory.getDatabase();
        assertTrue(database == null);
    }
}