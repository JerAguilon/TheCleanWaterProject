package model;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by jeremy on 11/15/16.
 */
public class DateGeneratorTest {

    @Test
    public void generateDate_whenDBFormatIsPassedInDateIsGenerated() throws ParseException {
        String input = "2016-11-14T03:43:24.021Z";

        SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSXXX");
        Date expectedOutput = dbFormat.parse(input);
        Date date = DateGenerator.generateDate(input);
        assertEquals(date, expectedOutput);
    }

    @Test
    public void generateDate_whenJavaFormatISPassedInDateIsGenerated() throws ParseException {
        String input = "2016-10-11";

        SimpleDateFormat javaFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date expectedOutput = javaFormat.parse(input);
        Date date = DateGenerator.generateDate(input);
        assertEquals(date, expectedOutput);
    }

    @Test
    public void generateDate_whenSlashDelimitedFormatIsPassedInDateIsGenerated() throws ParseException {
        String input = "10/11/1997";

        SimpleDateFormat slashDelimitedFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date expectedOutput = slashDelimitedFormat.parse(input);
        Date date = DateGenerator.generateDate(input);
        assertEquals(date, expectedOutput);
    }

    @Test(expected=ParseException.class)
    public void generateDate_whenNullIsPassedInParseExceptionIsThrown() throws ParseException {

        DateGenerator.generateDate(null);
    }

    @Test(expected=ParseException.class)
    public void generateDate_whenEmptyStringIsPassedInParseExceptionIsThrown() throws ParseException {
        DateGenerator.generateDate("");

    }

    @Test(expected=ParseException.class)
    public void generateDate_whenNonconformingStringIsPassedInParseExceptionIsThrown() throws ParseException {
        DateGenerator.generateDate("Hello world");

    }
}