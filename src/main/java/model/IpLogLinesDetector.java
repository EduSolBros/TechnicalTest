package model;

import consts.Consts;
import enums.Action;
import service.DateComparator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IpLogLinesDetector {
    private List<LogLine> logLineList;
    private int failedAttempts;
    private long dateLastFailedAttempt;

    public IpLogLinesDetector(List<LogLine> logLineList, int failedAttempts) {
        this.logLineList = logLineList;
        this.failedAttempts = failedAttempts;
        this.dateLastFailedAttempt = -1;
    }

    public IpLogLinesDetector() {
        this.logLineList = new ArrayList<>();
        this.failedAttempts = 0 ;
        this.dateLastFailedAttempt = -1;
    }

    public boolean addLogLine(LogLine logLine){
        this.logLineList.add(logLine);
        switch (logLine.getAction()) {
            case SIGNIN_FAILURE:
                if (DateComparator.isBetweenRange(Consts.MAX_RANGE_BETWEEN_DATES_IN_SECONDS, this.dateLastFailedAttempt,logLine.getDateEpochFormat())){
                    this.failedAttempts++;
                    this.dateLastFailedAttempt = logLine.getDateEpochFormat();
                    if (failedAttempts >= Consts.MAX_FAILED_ATTEMPTS) {
                        return false;
                    }
                }
                break;
            case SIGNIN_SUCCESS:
                restartValues();
                break;
        }
         return true;
    }

    public List<LogLine> getLogLineList() {
        return logLineList;
    }

    private void restartValues(){
        this.failedAttempts = Consts.INITIAL_ATTEMPTS_VALUE;
        this.dateLastFailedAttempt = Consts.INITIAL_DATE_LAST_ATTEMPT_FAILED_VALUE;
    }

    public void clearList() {
        this.logLineList.clear();
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(int failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public long getDateLastFailedAttempt() {
        return dateLastFailedAttempt;
    }

    public void setDateLastFailedAttempt(long dateLastFailedAttempt) {
        this.dateLastFailedAttempt = dateLastFailedAttempt;
    }
}
