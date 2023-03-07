package service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateComparatorTest {
    private static long RANGE= 5000;
    @Test
    void isInBetweenRange() {

        //arrange
        long date1 = 10000;
        long date2 = 12000;

        //act
        var result = DateComparator.isBetweenRange(RANGE,date1,date2);

        //assert
        assertTrue(result);
    }

    @Test
    void isOutBetweenRange() {

        //arrange
        long date1 = 10000;
        long date2 = 18000;

        //act
        var result = DateComparator.isBetweenRange(RANGE,date1,date2);

        //assert
        assertFalse(result);
    }

    @Test
    void areSameDate() {

        //arrange
        long date1 = 10000;
        long date2 = 10000;

        //act
        var result = DateComparator.isBetweenRange(RANGE,date1,date2);

        //assert
        assertTrue(result);
    }

    @Test
    void date2EsLowerThanDate1() {

        //arrange
        long date1 = 10000;
        long date2 = 9000;

        //act
        var result = DateComparator.isBetweenRange(RANGE,date1,date2);

        //assert
        assertFalse(result);
    }
}
