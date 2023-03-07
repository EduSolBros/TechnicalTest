package service;

import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TimeCalculationTest {

    @Test
    void parseStringToDate() throws ParseException {
        //arrange
        String dateStr = "Sat, 13 Mar 2010 11:29:05 +0900";
        long expected = 1268447345000L;
        //act
        Date result = TimeCalculation.convertStringToDateRFC2822(dateStr);
        var time = result.getTime();

        //assert
        assertEquals(time,expected);


    }

    @Test
    void parseDateToString() throws ParseException {
        //arrange
        Date date = new Date(110,03,10);
        //act
        String result = TimeCalculation.convertDateRFC2822ToString(date);

        //assert
        assertEquals(result,"sáb, 10 abr. 2010 00:00:00 -0600");


    }

    @Test
    void minutesBetweenDates() throws ParseException {
        //arrange
        String dateStr1 = "Sat, 13 Mar 2010 11:29:05 +0800";
        String dateStr2 = "Sat, 13 Mar 2010 12:29:05 +0900";

        //act
        Date date1 = TimeCalculation.convertStringToDateRFC2822(dateStr1);
        Date date2 = TimeCalculation.convertStringToDateRFC2822(dateStr2);

        var result = TimeCalculation.minutesBetweenDates(date1,date2);

        //assert
        assertEquals(result,60);
    }

    @Test
    void minutesBetweenDatesDifferentTimeZones() throws ParseException {
        //arrange
        String dateStr1 = "Sat, 13 Mar 2010 11:29:05 +0800";
        String dateStr2 = "Sat, 13 Mar 2010 12:29:05 +0900";

        //act
        Date date1 = TimeCalculation.convertStringToDateRFC2822(dateStr1);
        Date date2 = TimeCalculation.convertStringToDateRFC2822(dateStr2);

        var result = TimeCalculation.minutesBetweenDates(date1,date2);

        //assert
        assertEquals(result,0);
    }

    @Test
    void minutesBetweenDatesAnotherDifferentTimeZones() throws ParseException {
        //arrange
        String dateStr1 = "Sat, 13 Mar 2010 11:29:05 +0800";
        String dateStr2 = "Sat, 13 Mar 2010 11:29:05 +0700";

        //act
        Date date1 = TimeCalculation.convertStringToDateRFC2822(dateStr1);
        Date date2 = TimeCalculation.convertStringToDateRFC2822(dateStr2);

        var result = TimeCalculation.minutesBetweenDates(date1,date2);

        //assert
        assertEquals(result,60);
    }
}