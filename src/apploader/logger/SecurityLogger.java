package apploader.logger;

import apploader.ConfigLoader;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/*
 * Created by jeremy on 10/10/16.
 */
public class SecurityLogger {

    public static void log(List<String> result) {
        Date timeInfo = Calendar.getInstance().getTime();

        SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd");

        String fileName = date.format(timeInfo) + ".txt";

        if (ConfigLoader.CONFIG.getProperty("testMode").equals("true")) {
            fileName = "src/apploader/logger/tests/" + fileName;
        } else {
            fileName = "src/apploader/logger/logs/" + fileName;
        }

        File file = new File(fileName);

        SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");
        String currentTime = time.format(timeInfo);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);

            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(currentTime);
            bw.newLine();

            for (String item : result) {
                bw.write("\t" + item);
                bw.newLine();
            }

            bw.newLine();

            bw.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
