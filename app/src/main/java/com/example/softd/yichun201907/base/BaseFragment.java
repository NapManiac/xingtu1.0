package com.example.softd.yichun201907.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/*
 * fragment的基类，封装常用方法
 * */
public abstract class BaseFragment extends Fragment {
    private         View    mContentView;
    private         Toast   toast;
    protected final String  TAG   = this.getClass().getSimpleName();
    /**
     * 调试开关,通activity中保持一致
     */
    protected final boolean DEBUG = BaseActivity.DEBUG;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(initLayout(), container, false);
        init();
        initView();
        initData();
        log("***********Fragment********" + TAG);
        return mContentView;
    }



    /**
     * 此方法用于返回Fragment设置ContentView的布局文件资源ID * * @return 布局文件资源ID
     */
    protected abstract int initLayout();

    /**
     * 一些View的相关操作
     */
    protected abstract void initView();

    /**
     * 一些Data的相关操作
     */
    protected abstract void initData();

    /**
     * 此方法用于初始化成员变量及获取Intent传递过来的数据 * 注意：这个方法中不能调用所有的View，因为View还没有被初始化，要使用View在initView方法中调用
     */
    protected void init() {
    }

    public View getContentView() {
        return mContentView;
    }


    /**
     * 显示长toast
     *
     * @param msg 要显示的信息
     */
    public void toastLong(String msg) {
        if (null == toast) {
            toast = Toast.makeText(getContext(), msg, Toast.LENGTH_LONG);
            toast.show();
        } else {
            toast.setText(msg);
            toast.show();
        }

    }

    /**
     * 显示短toast
     *
     * @param msg 要显示的信息
     */
    public void toastShort(String msg) {
        if (null == toast) {
            toast = Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            toast.setText(msg);
            toast.show();
        }
    }

    public void log(String msg) {
        if (DEBUG)
            Log.d(TAG, msg);
    }

    /**
     * activity跳转
     *
     * @param cls 要跳转的活动
     */
    protected void goNextActivity(Class<?> cls) {
        Intent intent = new Intent(getContext(), cls);
        startActivity(intent);
    }

    /**
     * activity跳转
     *
     * @param cls    要跳转的类
     * @param bundle 传递的数据
     */
    protected void getNextActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(getContext(), cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}