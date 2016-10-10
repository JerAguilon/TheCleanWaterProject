package tests.logging;

import apploader.logger.SecurityLogger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by jeremy on 10/10/16.
 */
public class SecurityLoggerTest {

    @After
    public void after() {
        String folderName = "src/apploader/logger/tests";

        File folder = new File(folderName);

        for (File file : folder.listFiles()) {
            if (file.getName().equals(".gitkeep")) continue;

            if (!file.isDirectory()) {
                file.delete();
            }
        }
    }


    @Test
    public void firstEntryOnDate_ShouldCreateNewFile() {
        System.out.println(System.getProperty("user.dir"));

        Date timeInfo = Calendar.getInstance().getTime();

        SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd");

        String fileName = "src/apploader/logger/tests/" + date.format(timeInfo) + ".txt";

        File file = new File(fileName);

        List<String> result = new ArrayList<>();

        result.add("test");

        SecurityLogger.log(result);

        assertTrue(file.exists());
    }

}