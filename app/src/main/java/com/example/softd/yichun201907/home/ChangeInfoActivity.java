package com.example.softd.yichun201907.home;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.softd.yichun201907.DB.UserInfo;
import com.example.softd.yichun201907.R;
import com.example.softd.yichun201907.base.BaseActivity;
import com.example.softd.yichun201907.base.MyApp;
import com.example.softd.yichun201907.utils.RegexUtils;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ChangeInfoActivity extends BaseActivity {


    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_tel)
    EditText etTel;
    @BindView(R.id.btn_sure)
    RoundButton btnSure;
    @BindView(R.id.btn_cancel)
    RoundButton btnCancel;

    @Override
    public int initLayout() {
        return R.layout.activiti_change_info;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);

    }

    @Override
    public void initData() {
        tvUserName.setText(MyApp.getUserInfo().getName());
    }




    @OnClick({R.id.btn_sure, R.id.btn_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_sure:
                doChange(etEmail.getText().toString().trim(), etTel.getText().toString().trim());
                break;
            case R.id.btn_cancel:
                finish();
                break;
        }
    }
    private void doChange(String emailString, String phoneString) {
        if (!RegexUtils.checkEmail(emailString)) {
            toastLong("请填写正确的邮箱");
        } else if (!RegexUtils.checkPhone(phoneString)) {
            toastLong("请填写正确的手机号");
        } else {
            UserInfo userInfo = new UserInfo();
            userInfo.setEmail(emailString);
            userInfo.setTel(phoneString);
            userInfo.updateAll("name=?", MyApp.getUserInfo().getName());
            MyApp.getUserInfo().setEmail(emailString);
            MyApp.getUserInfo().setTel(phoneString);
            finish();
            toastShort("更新成功");
        }
    }
}
