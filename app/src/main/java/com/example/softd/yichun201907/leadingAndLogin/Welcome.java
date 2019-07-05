package com.example.softd.yichun201907.leadingAndLogin;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.softd.yichun201907.R;
import com.example.softd.yichun201907.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Welcome extends BaseActivity {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

    }

    public int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }


}
