package com.example.softd.yichun201907.DB;

import org.litepal.crud.LitePalSupport;

public class Event extends LitePalSupport {

    private String name = "";

    private String eventName = "";

    private String title = "";

    private long startTime;

    private long endTime;

    private int status;

    public static final int COMPLETE = 1;

    public static final int INCOMPLETE = 2;

    public static final int NOT_START = 3;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
