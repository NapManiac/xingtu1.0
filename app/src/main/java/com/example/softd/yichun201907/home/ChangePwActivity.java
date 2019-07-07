package com.example.softd.yichun201907.home;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.softd.yichun201907.DB.Account;
import com.example.softd.yichun201907.R;
import com.example.softd.yichun201907.base.ActivityCollector;
import com.example.softd.yichun201907.base.BaseActivity;
import com.example.softd.yichun201907.base.MyApp;
import com.example.softd.yichun201907.leadingAndLogin.Login;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;

import org.litepal.LitePal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePwActivity extends BaseActivity {
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.et_psw)
    EditText etPsw;
    @BindView(R.id.et_psw_again)
    EditText etPswAgain;
    @BindView(R.id.btn_sure)
    RoundButton btnSure;
    @BindView(R.id.btn_cancel)
    RoundButton btnCancel;

    @Override
    public int initLayout() {
        return R.layout.activity_changepassworld;
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
                doChangePassword(etPsw.getText().toString().trim(), etPswAgain.getText().toString().trim());
                break;
            case R.id.btn_cancel:
                finish();
                break;
        }
    }

    private void doChangePassword(String pastPw, String newPw) {
        Account account = LitePal.select("*")
                .where("name=?", MyApp.getUserInfo().getName())
                .find(Account.class).get(0);
        if (!pastPw.equals(account.getPassword())) {
            toastShort("旧密码错误");
        } else if (pastPw.equals(newPw)) {
            toastShort("新密码和原密码相同");
        } else {
            account.setPassword(newPw);
            account.updateAll("name=?", MyApp.getUserInfo().getName());
            MyApp.clearCache();
            ActivityCollector.finishAll();
            goNextActivity(Login.class);
            toastShort("请重新登录");
        }
    }
}
