package model;

import enums.Action;
import org.apache.commons.logging.Log;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IpLogLinesDetectorTest {

    private static IpLogLinesDetector ipLogLinesDetector;
    @BeforeAll
    static void init(){
        ipLogLinesDetector = new IpLogLinesDetector();
    }

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        ipLogLinesDetector.clearList();
    }

    @Test
    void addFirstLogLineSuccess() {
        //arrange
        LogLine lineToAdd = new LogLine("123",44444,Action.SIGNIN_SUCCESS,"Pablo");
        //act
        ipLogLinesDetector.addLogLine(lineToAdd);
        //assert
        assertEquals(1, ipLogLinesDetector.getLogLineList().size());
        assertEquals(0, ipLogLinesDetector.getFailedAttempts());
    }

    @Test
    void addFirstLogLineFailed() {
        //arrange
        LogLine lineToAdd = new LogLine("123",44444,Action.SIGNIN_FAILURE,"Pablo");
        //act
        ipLogLinesDetector.addLogLine(lineToAdd);
        //assert
        assertEquals(1, ipLogLinesDetector.getLogLineList().size());
        assertEquals(1, ipLogLinesDetector.getFailedAttempts());
    }
    @Test
    void addSecondLogLineFailed() {
        //arrange
        LogLine lineToAdd = new LogLine("123",44444,Action.SIGNIN_FAILURE,"Pablo");
        LogLine lineToAdd2 = new LogLine("123",44449,Action.SIGNIN_FAILURE,"Pablo");

        //act
        ipLogLinesDetector.addLogLine(lineToAdd);
        ipLogLinesDetector.addLogLine(lineToAdd2);

        //assert
        assertEquals(2, ipLogLinesDetector.getLogLineList().size());
        assertEquals(2, ipLogLinesDetector.getFailedAttempts());
    }

    @Test
    void addSecondLogLineFailedOutOfRange() {
        //arrange
        LogLine lineToAdd = new LogLine("123",44444,Action.SIGNIN_FAILURE,"Pablo");
        LogLine lineToAdd2 = new LogLine("123",444400000,Action.SIGNIN_FAILURE,"Pablo");

        //act
        ipLogLinesDetector.addLogLine(lineToAdd);
        ipLogLinesDetector.addLogLine(lineToAdd2);

        //assert
        assertEquals(2, ipLogLinesDetector.getLogLineList().size());
        assertEquals(1, ipLogLinesDetector.getFailedAttempts());
    }

    @Test
    void addSuccessLogLineAfterFailedLogLine() {
        //arrange
        LogLine lineToAdd = new LogLine("123",44444,Action.SIGNIN_FAILURE,"Pablo");
        LogLine lineToAdd2 = new LogLine("123",444400000,Action.SIGNIN_SUCCESS,"Pablo");

        //act
        ipLogLinesDetector.addLogLine(lineToAdd);
        ipLogLinesDetector.addLogLine(lineToAdd2);

        //assert
        assertEquals(2, ipLogLinesDetector.getLogLineList().size());
        assertEquals(0, ipLogLinesDetector.getFailedAttempts());
        assertEquals(-1, ipLogLinesDetector.getDateLastFailedAttempt());
    }

    @Test
    void fiveFailedAttempts() {
        //arrange
        var list = addLogLines();
        LogLine lastLineToAdd = new LogLine("123",333333+50, Action.SIGNIN_FAILURE,"jose");

        //act
        list.stream().forEach(x -> ipLogLinesDetector.addLogLine(x));
        var result = ipLogLinesDetector.addLogLine(lastLineToAdd);
        //assert
        assertFalse(result);
        assertEquals(5, ipLogLinesDetector.getLogLineList().size());
        assertEquals(5, ipLogLinesDetector.getFailedAttempts());
    }


    private List<LogLine> addLogLines(){
        List<LogLine> lines = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            lines.add(new LogLine("123",333333+i, Action.SIGNIN_FAILURE,"jose"));
        }
        return lines;
    }
}