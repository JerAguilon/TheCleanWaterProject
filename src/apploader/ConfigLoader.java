package apploader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/*
 * Created by jeremy on 10/9/16.
 */
public class ConfigLoader {
    public static final Properties CONFIG = loadConfig();

    public static Properties loadConfig() {

        Properties config = new Properties();
        try {

            InputStream input = new FileInputStream("src/config.properties");

            config.load(input);


        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return config;
    }

}
