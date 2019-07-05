package com.example.softd.yichun201907.DB;

import org.litepal.crud.LitePalSupport;

public class Event extends LitePalSupport {

    //未开始
    public static final int NOT_START = 0;
    //完成
    public static final int COMPLETE = 1;
    //未完成
    public static final int INCOMPLETE = 2;


    private String name = "";

    private long startTime;

    private long endTime;

    private String eventName = "";

    private int status;

    public static int getNotStart() {
        return NOT_START;
    }

    public static int getCOMPLETE() {
        return COMPLETE;
    }

    public static int getINCOMPLETE() {
        return INCOMPLETE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
