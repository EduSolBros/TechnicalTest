package model;

import enums.Action;

public class LogLine {

    private String ip;
    private long dateEpochFormat;
    private Action action;

    private String username;


    public LogLine(String ip, long dateEpochFormat, Action action, String username) {
        this.ip = ip;
        this.dateEpochFormat = dateEpochFormat;
        this.action = action;
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getDateEpochFormat() {
        return dateEpochFormat;
    }

    public void setDateEpochFormat(long dateEpochFormat) {
        this.dateEpochFormat = dateEpochFormat;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
