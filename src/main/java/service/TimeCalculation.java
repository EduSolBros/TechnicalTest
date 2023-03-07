package service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeCalculation {


    public static int minutesBetweenDates(Date date1, Date date2){
        long miliseconds = date2.getTime() - date1.getTime();
        int seconds = (int) miliseconds / 1000;
        int minutes = seconds/60;
        return minutes;
    }

    public static Date convertStringToDateRFC2822(String dateStr) throws ParseException {
        String pattern = "EEE, dd MMM yyyy HH:mm:ss Z"; //$NON-NLS-1$
        SimpleDateFormat format = new SimpleDateFormat(pattern,Locale.ENGLISH);
        format.setTimeZone(TimeZone.getDefault());
        return format.parse(dateStr);
    }

    public static String convertDateRFC2822ToString(Date date){
        String pattern = "EEE, dd MMM yyyy HH:mm:ss Z"; //$NON-NLS-1$
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        format.setTimeZone(TimeZone.getDefault());

        return format.format(date);
    }


}
