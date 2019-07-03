package com.example.softd.yichun201907.Application;

import android.app.Application;
import android.util.Log;

import com.xuexiang.xui.XUI;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("application", "application onCreate: ");
        XUI.init(this); //初始化UI框架
        XUI.debug(true);  //开启UI框架调试日志
    }
}
