package com.example.softd.yichun201907.leadingAndLogin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.softd.yichun201907.DB.Account;
import com.example.softd.yichun201907.DB.UserInfo;
import com.example.softd.yichun201907.R;
import com.example.softd.yichun201907.base.BaseActivity;
import com.example.softd.yichun201907.utils.RegexUtils;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Register extends BaseActivity {

    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.bt_register)
    Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.bt_register)
    public void onViewClicked() {
        String nameString = etAccount.getText().toString().trim();
        String passwordString = etPassword.getText().toString().trim();
        String emailString = etEmail.getText().toString().trim();
        String phoneString = etPhone.getText().toString().trim();
        // TODO: 2018/12/25 验证这些值是不是合法的
        doRegister(nameString, passwordString, emailString, phoneString);
    }

    //真正的注册逻辑
    private void doRegister(String nameString, String passwordString, String emailString, String phoneString) {

        if (!RegexUtils.checkAccount(nameString)) {
            toastLong("账户名需要6字符以上的字母数字下划线");
        } else if (!RegexUtils.checkPassword(passwordString)) {
            toastLong("账户名 需要6字符以上");
        } else if (!RegexUtils.checkEmail(emailString)) {
            toastLong("请填写正确的邮箱");
        } else if (!RegexUtils.checkPhone(phoneString)) {
            toastLong("请填写正确的手机号");
        } else {
            List<UserInfo> userInfos = LitePal.select("*")
                    .where("name = ?", nameString)
                    .find(UserInfo.class);
            if (userInfos.size() != 0) {
                toastShort("该用户名已被注册");
            } else {
                Account account = new Account();
                account.setName(nameString);
                account.setPassword(passwordString);
                account.save();

                UserInfo userInfo = new UserInfo();
                userInfo.setName(nameString);
                userInfo.setEmail(emailString);
                userInfo.setTel(phoneString);
                userInfo.save();

                finish();

            }
        }
    }
}
