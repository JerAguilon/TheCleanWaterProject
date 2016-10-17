package database.sqlite;

/**
 * Created by jeremy on 10/17/16.
 */
public class SqliteDatabaseWrapper {
    public SqliteDatabaseWrapper database = new SqliteDatabaseWrapper();


    //FIELDS
    private SqliteLoginManager loginManager;



    private SqliteDatabaseWrapper() {
        loginManager = new SqliteLoginManager();
    }

    //TODO: Implement dependency injection
}
