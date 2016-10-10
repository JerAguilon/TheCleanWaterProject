package apploader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by jeremy on 10/9/16.
 */
public class ConfigLoader {
    public static final Properties CONFIG = new Properties();

    public static void loadConfig() {
        try {
            InputStream input = new FileInputStream("src/config.properties");

            CONFIG.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
