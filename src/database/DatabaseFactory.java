package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by jeremy on 10/9/16.
 */
public class DatabaseFactory {

    static Properties prop = new Properties();

    public static IDatabase getDatabase() {
        InputStream input = null;

        try {
            System.out.println(System.getProperty("user.dir"));
            input = new FileInputStream("src/config.properties");

            prop.load(input);

            if (prop.getProperty("database").equals("mock")) {
                return MockDatabase.mockDatabase;
            }


        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //should never get here
        return null;
    }


}
