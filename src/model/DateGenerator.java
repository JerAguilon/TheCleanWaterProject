package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jeremy on 11/12/16.
 */
public class DateGenerator {
    public static Date generateDate(String date) throws ParseException {
        SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSXXX");
        SimpleDateFormat javaFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat slashDelimitedFormat = new SimpleDateFormat("MM/dd/yyyy");

        Date outputDate;

        try {
            outputDate = dbFormat.parse(date);
        } catch (ParseException ex) {
            try {
                outputDate = javaFormat.parse(date);
            } catch (ParseException e) {
                outputDate = slashDelimitedFormat.parse(date);
            }
        }

        return outputDate;
    }


    public static String dateToDBString(Date date) {
         SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSXXX");

        return dbFormat.format(date);

    }

    public static String dateToJavaString(Date date) {
        SimpleDateFormat javaFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return javaFormat.format(date);
    }


}
