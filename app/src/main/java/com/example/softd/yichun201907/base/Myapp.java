package com.example.softd.yichun201907.base;


import com.xuexiang.xui.XUI;

import org.litepal.LitePalApplication;

public class Myapp extends LitePalApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        XUI.init(this); //初始化UI框架
        XUI.debug(true);  //开启UI框架调试日志
    }
}
