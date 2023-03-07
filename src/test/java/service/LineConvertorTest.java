package service;

import enums.Action;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.Line;

import static org.junit.jupiter.api.Assertions.*;

class LineConvertorTest {

    @Test
    void convertLineToLogLine() {

        //arrange
        String ip = "192.168.9.2";
        Action action = Action.SIGNIN_SUCCESS;
        long epochFormatDate = 888888888;
        String username ="Eduard.Sol";

        String lineToConvert="192.168.9.2,888888888,SIGNIN_SUCCESS,Eduard.Sol";


        //act
        var logLine = LineConvertor.convertLineToLogLine(lineToConvert);

        //assert
        assertEquals(ip,logLine.getIp());
        assertEquals(username,logLine.getUsername());
        assertEquals(epochFormatDate,logLine.getDateEpochFormat());
        assertEquals(action,logLine.getAction());


    }


    @Test()
    void convertLineToLogLineActionError() {

        //arrange
        String ip = "192.168.9.2";
        Action action = Action.SIGNIN_SUCCESS;
        long epochFormatDate = 888888888;
        String username ="Eduard.Sol";

        String lineToConvert="192.168.9.2,888888888,SIGNIN_SUCCESSs,Eduard.Sol";


        //act and assert
        Throwable e = assertThrows(IllegalArgumentException.class, () -> LineConvertor.convertLineToLogLine(lineToConvert));

    }

    @Test()
    void convertLineToLogLineArgumentMissing() {

        //arrange
        String ip = "192.168.9.2";
        Action action = Action.SIGNIN_SUCCESS;
        long epochFormatDate = 888888888;
        String username ="Eduard.Sol";

        String lineToConvert="192.168.9.2,888888888,SIGNIN_SUCCESS";


        //act and assert
        Throwable e = assertThrows(IndexOutOfBoundsException.class, () -> LineConvertor.convertLineToLogLine(lineToConvert));

    }
}