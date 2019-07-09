package com.example.softd.yichun201907.home;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.softd.yichun201907.DB.Event;
import com.example.softd.yichun201907.R;
import com.example.softd.yichun201907.base.BaseActivity;
import com.example.softd.yichun201907.base.BasisTimesUtils;
import com.example.softd.yichun201907.base.MyApp;
import com.vivian.timelineitemdecoration.util.Util;
import com.xuexiang.xui.utils.StatusBarUtils;
import com.xuexiang.xui.utils.Utils;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.edittext.MultiLineEditText;
import com.xuexiang.xui.widget.edittext.ValidatorEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddTaskActivity extends BaseActivity {

    ValidatorEditText etEdmin;
    @BindView(R.id.btn_time_end)
    LinearLayout btnTimeEnd;
    @BindView(R.id.btn_time_start)
    LinearLayout btnTimeStart;
    @BindView(R.id.et_task_title)
    MultiLineEditText etTaskTitle;
    @BindView(R.id.et_task_name)
    MultiLineEditText etTaskName;
    @BindView(R.id.tv_task_start)
    TextView tvTaskStart;
    @BindView(R.id.tv_task_end)
    TextView tvTaskEnd;

    @BindView(R.id.button)
    RoundButton btn_create;
    @BindView(R.id.tv_task_start_time)
    TextView tvTaskStartTime;
    @BindView(R.id.tv_task_end_time)
    TextView tvTaskEndTime;

    @Override
    public int initLayout() {
        return R.layout.activity_add_task;
    }


    @Override
    public void initView() {
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void initData() {
        String arr[] = BasisTimesUtils.getDeviceTime().split(" ");
//        Log.d("BBBsplite", "initView: "+BasisTimesUtils.getDeviceTime());
        String d[] = arr[0].split("-");
        String t[] = arr[1].split(":");
//
//        for(int i = 0; i < d.length; i++) {
//            Log.d("BBB"+d.length, "initView: "+d[i]);
//        }
//        for(int i = 0; i < t.length; i++) {
//            Log.d("BBB"+t.length, "initView: "+t[i]);
//        }
        if(tvTaskEnd != null) {
            tvTaskEnd.setText(String.format("时间：%d时%d分", Integer.parseInt(t[0]), Integer.parseInt(t[1])));
            tvTaskEndTime.setText(String.format("日期：%d年%d月%d日", Integer.parseInt(d[0]), (Integer.parseInt(d[1]) + 1), Integer.parseInt(d[2])));
            tvTaskStart.setText(String.format("时间：%d时%d分", Integer.parseInt(t[0]), Integer.parseInt(t[1])));
            tvTaskStartTime.setText(String.format("日期：%d年%d月%d日", Integer.parseInt(d[0]), (Integer.parseInt(d[1]) + 1), Integer.parseInt(d[2])));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        StatusBarUtils.translucent(this);


    }


    public void showTimePickerDialog(Context context, int themeResId, final TextView tv, Calendar calendar) {
        new TimePickerDialog(context
                , themeResId
                , new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                toastLong(String.format("时间：%d时%d分", hourOfDay, minute));
                tv.setText(String.format("%d:%d", hourOfDay, minute));
            }
        }
                // 设置初始日期
                , calendar.get(Calendar.HOUR_OF_DAY)
                , calendar.get(Calendar.MINUTE)
                , true)
                .show();
    }

    public void showDatePickerDialog(Context context, int themeResId, final TextView tv, Calendar calendar) {
        new DatePickerDialog(context
                , themeResId
                , new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                toastLong(String.format("日期：%d年%d月%d日", year, (monthOfYear + 1), dayOfMonth));
                tv.setText(String.format("%d-%d-%d", year, (monthOfYear + 1), dayOfMonth));
            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    @OnClick({R.id.btn_time_end, R.id.btn_time_start, R.id.button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_time_end:
                showTimePickerDialog(this, DatePickerDialog.THEME_DEVICE_DEFAULT_LIGHT, tvTaskStartTime, Calendar.getInstance());
                showDatePickerDialog(this, DatePickerDialog.THEME_DEVICE_DEFAULT_LIGHT, tvTaskStart, Calendar.getInstance());

                break;
            case R.id.btn_time_start:
                showTimePickerDialog(this, DatePickerDialog.THEME_DEVICE_DEFAULT_LIGHT, tvTaskEndTime, Calendar.getInstance());
                showDatePickerDialog(this, DatePickerDialog.THEME_DEVICE_DEFAULT_LIGHT, tvTaskEnd, Calendar.getInstance());

                break;
            case R.id.button:
                Event event = new Event();
                event.setTitle(etTaskTitle.getContentText());
                event.setName(MyApp.getUserInfo().getName());
                event.setEventName(etTaskName.getContentText());
                String d[] = ((String)tvTaskStart.getText()).split("-");
                String t[] = ((String)tvTaskStartTime.getText()).split(":");
                SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time=d[0]+"-"+d[1]+"-"+d[2]+" "+t[0]+":"+t[1]+":00";
                //Log.d(TAG, "onViewClicked: BBBBB"+time);
                Date date = null;
                try {
                    date = format.parse(time);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //toastLong(date.getTime()+"BBB");
                event.setStartTime(date.getTime()/1000);
                d = ((String)tvTaskEnd.getText()).split("-");
                t = ((String)tvTaskEndTime.getText()).split(":");
                format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                time=d[0]+"-"+d[1]+"-"+d[2]+" "+t[0]+":"+t[1]+":00";
                //Log.d(TAG, "onViewClicked: BBBBB"+time);
                date = null;
                try {
                    date = format.parse(time);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //toastLong(date.getTime()+"BBB");
                event.setEndTime(date.getTime()/1000);
                event.setStatus(Event.NOT_START);
                event.save();
                goNextActivity(MainActivity.class);
                break;
        }
    }
}
