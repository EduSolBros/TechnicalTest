package service;

import model.IpLogLinesDetector;
import model.LogLine;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HackerDetectorImpl implements HackerDetector{


    private Map<String, IpLogLinesDetector> ipLogLinesMap;

    public HackerDetectorImpl(){
        this.ipLogLinesMap = new HashMap<>();
    }


    @Override
    public String parseLine(String line) {

        var logLine = LineConvertor.convertLineToLogLine(line);
        var ipLogLinesDetector = ipLogLinesMap.get(logLine.getIp());
        if (ipLogLinesDetector ==null){
            ipLogLinesDetector = new IpLogLinesDetector();
            this.ipLogLinesMap.put(logLine.getIp(), ipLogLinesDetector);
        }
        var result = ipLogLinesDetector.addLogLine(logLine);


        return !result ? logLine.getIp() : null;
    }

}
