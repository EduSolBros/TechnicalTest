package service;

import enums.Action;
import model.LogLine;

public class LineConvertor {


    public static LogLine convertLineToLogLine(String line){
        var args = line.split(",");
        return new LogLine(args[0], Long.parseLong(args[1]), Action.valueOf(args[2]),args[3]);
    }




}
