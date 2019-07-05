package com.example.softd.yichun201907.leadingAndLogin;

import android.support.v7.widget.RecyclerView;

import com.example.softd.yichun201907.R;
import com.example.softd.yichun201907.base.BaseActivity;
import com.xuexiang.xui.utils.StatusBarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Welcome extends BaseActivity {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;



    public int initLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {
        //沉浸式状态栏
        StatusBarUtils.translucent(this);
        ButterKnife.bind(this);

    }

    @Override
    public void initData() {

    }


}
