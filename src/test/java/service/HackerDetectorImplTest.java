package service;

import model.IpLogLinesDetector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class HackerDetectorImplTest {

    @Autowired
    private static HackerDetector hackerDetector;
    @BeforeAll
    static void init(){
        hackerDetector = new HackerDetectorImpl();
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void parseLineFiveFailureCorrectly() {
        //arrange
        String l1="192.168.9.2,888888888,SIGNIN_FAILURE,Eduard.Sol";
        String l2="192.168.9.2,888888889,SIGNIN_FAILURE,Eduard.Sol";
        String l3="192.168.9.2,888888892,SIGNIN_FAILURE,Eduard.Sol";
        String l4="192.168.9.2,888888894,SIGNIN_FAILURE,Eduard.Sol";
        String l5="192.168.9.2,888888897,SIGNIN_FAILURE,Eduard.Sol";

        //act
        var r1 = hackerDetector.parseLine(l1);
        var r2 = hackerDetector.parseLine(l2);
        var r3 = hackerDetector.parseLine(l3);
        var r4 = hackerDetector.parseLine(l4);
        var r5 = hackerDetector.parseLine(l5);


        //assert
        assertNull(r1);
        assertNull(r2);
        assertNull(r3);
        assertNull(r4);
        assertEquals("192.168.9.2",r5);
    }

    @Test
    void parseLineFourFailureOneCorrectly() {
        //arrange
        String l1="192.168.9.2,888888888,SIGNIN_FAILURE,Eduard.Sol";
        String l2="192.168.9.2,888888889,SIGNIN_FAILURE,Eduard.Sol";
        String l3="192.168.9.2,888888892,SIGNIN_FAILURE,Eduard.Sol";
        String l4="192.168.9.2,888888894,SIGNIN_FAILURE,Eduard.Sol";
        String l5="192.168.9.2,888888897,SIGNIN_SUCCESS,Eduard.Sol";

        //act
        var r1 = hackerDetector.parseLine(l1);
        var r2 = hackerDetector.parseLine(l2);
        var r3 = hackerDetector.parseLine(l3);
        var r4 = hackerDetector.parseLine(l4);
        var r5 = hackerDetector.parseLine(l5);


        //assert
        assertNull(r1);
        assertNull(r2);
        assertNull(r3);
        assertNull(r4);
        assertNull(r5);
    }
}