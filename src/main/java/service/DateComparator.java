package service;

public class DateComparator {

    public static boolean isBetweenRange(long rangeInSeconds, long date1, long date2){
        if(date1 ==-1)
            return true;
        if(date2 <date1)
            return false;
        return  date2 > date1+rangeInSeconds ? false : true;
    }
}
