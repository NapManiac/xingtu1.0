package com.example.softd.yichun201907.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * created by Dongkai on 2018/11/26
 */

public abstract class BaseActivity extends AppCompatActivity {
    /***是否显示标题栏*/
    private boolean isShowTitle = false;
    /***是否显示状态栏*/
    private boolean isShowStatus = true;
    /***封装toast对象，避免toast一直显示**/
    private static Toast toast;
    /***获取TAG的activity名称**/
    protected final String TAG = this.getClass().getSimpleName();

    /**
     * .全局调试开关，fragment中同样使用这个。
     */
    public static final boolean DEBUG = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isShowTitle) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
            //getSupportActionBar().hide();用了NoActionBar的主体，此处会报空指针，此处注释掉。
        }

        if (!isShowStatus) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }


        /*获取上次保存的主题*/
        //new UserThemeHelper(this).initTheme();

        //设置布局
        setContentView(initLayout());
        //初始化控件
        initView();
        //设置数据
        initData();
        log("*********Activity********" + TAG);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    /**
     * 设置布局
     *
     * @return 该活动的xml文件
     */
    public abstract int initLayout();

    /**
     * 初始化布局
     */
    public abstract void initView();

    /**
     * 设置数据
     */
    public abstract void initData();

    /**
     * 设置是否标题栏
     *
     * @param isShow true-->显示，false-->不显示
     */
    public void setTitle(boolean isShow) {
        isShowTitle = isShow;
    }

    /**
     * 设置是否显示状态栏
     *
     * @param isShow true-->显示，false-->不显示
     */
    public void setState(boolean isShow) {
        isShowStatus = isShow;
    }

    /**
     * 显示长toast
     *
     * @param msg 要显示的信息
     */
       public void toastLong(String msg) {
        if (null == toast) {
            toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
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
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
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
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    /**
     * activity跳转
     *
     * @param cls 要跳转的类
     * @param bundle 传递的数据
     */
    protected void getNextActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
