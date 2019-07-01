package com.example.softd.yichun201907.base;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/*
 * 用来记录活动状况，以便对活动的整体操作，如：退出登录等。
 * */

public class ActivityCollector {
    private static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                Log.d("ActivityCollector", "finish activity:" + activity.getClass().getSimpleName());
                activity.finish();
            }
        }
        activities.clear();
    }
}