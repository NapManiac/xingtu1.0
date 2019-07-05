package com.example.softd.yichun201907.base;


import com.example.softd.yichun201907.DB.Entity;
import com.example.softd.yichun201907.DB.Event;
import com.example.softd.yichun201907.DB.UserInfo;
import com.xuexiang.xui.XUI;

import org.litepal.LitePalApplication;

import java.util.List;

public class MyApp extends LitePalApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        XUI.init(this);
        XUI.debug(true);
    }

    //账户信息
    private static UserInfo userInfo = null;
    //今日推文
    private static Entity todayEntity = null;
    //历史推文
    private static List<Entity> pastEntity = null;
    //收藏推文
    private static List<Entity> collectionEntity = null;

    private static List<Event> events = null;

    public static List<Event> getEvents() {
        return events;
    }

    public static void setEvents(List<Event> events) {
        MyApp.events = events;
    }



    public static UserInfo getUserInfo() {
        return userInfo;
    }

    public static void setUserInfo(UserInfo userInfo) {
        MyApp.userInfo = userInfo;
    }

    public static Entity getTodayEntity() {
        return todayEntity;
    }

    public static void setTodayEntity(Entity todayEntity) {
        MyApp.todayEntity = todayEntity;
    }

    public static List<Entity> getPastEntity() {
        return pastEntity;
    }

    public static void setPastEntity(List<Entity> pastEntity) {
        MyApp.pastEntity = pastEntity;
    }

    public static List<Entity> getCollectionEntity() {
        return collectionEntity;
    }

    public static void setCollectionEntity(List<Entity> collectionEntity) {
        MyApp.collectionEntity = collectionEntity;
    }
}
