package com.example.softd.yichun201907.leadingAndLogin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.softd.yichun201907.R;
import com.example.softd.yichun201907.base.BaseActivity;
import com.example.softd.yichun201907.utils.RegexUtils;

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
        String accountString = etAccount.getText().toString().trim();
        String passwordString = etPassword.getText().toString().trim();
        String emailString = etEmail.getText().toString().trim();
        String phoneString = etPhone.getText().toString().trim();
        // TODO: 2018/12/25 验证这些值是不是合法的
        doRegister(accountString, passwordString, emailString, phoneString);
    }

    //真正的注册逻辑
    private void doRegister(String accountString, String passwordString, String emailString, String phoneString) {
//        if(RegexUtils.checkAccount(accountString)&&RegexUtils.checkPassword(passwordString)
//                &&RegexUtils.checkEmail(emailString)&&RegexUtils.checkPhone(phoneString)){
//            // TODO: 2019/7/3 查询数据库中是否已经存在了该用户名，没有存在才能继续注册，否者提示账号已被占用。
//           //TODO:在此放置插入数据库的代码
//            toastLong("注册成功");
//            finish();
//        }else {
//            toastLong("输入不合法！");
//        }
        if (RegexUtils.checkAccount(accountString) && RegexUtils.checkPassword(passwordString)
                && RegexUtils.checkEmail(emailString) && RegexUtils.checkPhone(phoneString)) {
            // TODO: 2019/7/3 查询数据库中是否已经存在了该用户名，没有存在才能继续注册，否者提示账号已被占用。
            //TODO:在此放置插入数据库的代码
            toastLong("注册成功");
            finish();
        } else {
            if (!RegexUtils.checkAccount(accountString)) {
                toastLong("账户名需要6字符以上的字母数字下划线");
            } else if (!RegexUtils.checkPassword(passwordString)) {
                toastLong("账户名 需要6字符以上");
            } else if (!RegexUtils.checkEmail(emailString)) {
                toastLong("请填写正确的邮箱");
            } else if (!RegexUtils.checkPhone(phoneString)) {
                toastLong("请填写正确的手机号");
            }
        }
    }
}
