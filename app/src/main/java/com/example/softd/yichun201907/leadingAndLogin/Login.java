package com.example.softd.yichun201907.leadingAndLogin;

import android.content.SharedPreferences;
import android.graphics.Movie;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.AppCompatCheckBox;
import android.widget.Button;
import android.widget.EditText;

import com.example.softd.yichun201907.DB.Account;
import com.example.softd.yichun201907.R;
import com.example.softd.yichun201907.base.BaseActivity;
import com.example.softd.yichun201907.home.MainActivity;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends BaseActivity {

    static int LOGIN_SUCCESS = 1;
    static int LOGIN_PASSWORD_ERROR = 2;
    static int LOGIN_NO_REGISTER = 3;

    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.userpassword)
    EditText userpassword;
    @BindView(R.id.check_remerber)
    AppCompatCheckBox checkRemerber;
    @BindView(R.id.check_auto)
    AppCompatCheckBox checkAuto;
    @BindView(R.id.btn_login)
    Button btnLogin;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

//        LitePal.getDatabase();
//        Account account1 = new Account();
//        account1.setName("fu");
//        account1.setPassword("123456");
//        account1.save();


        //查看数据库中的账号
//        List<Account> allAccount = LitePal.findAll(Account.class);
//        for (Account account: allAccount) {
//            log(account.getName());
//            log(account.getPassword());
//        }



        pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean auto_login = pref.getBoolean("auto_login", false);
        boolean isRemember = pref.getBoolean("remember_password", false);
        if (isRemember) {
            // 将账号和密码都设置到文本框中
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            username.setText(account);
            userpassword.setText(password);
            checkRemerber.setChecked(true);

        }
        if(auto_login) {
            toastLong("自动登陆");
            checkAuto.setChecked(true);
        }
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

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        String account = username.getText().toString();
        String password = userpassword.getText().toString();
        // 如果账号是admin且密码是123456，就认为登录成功
        int result = isLogin(account, password);
        if (result == LOGIN_SUCCESS) {
            editor = pref.edit();
            if (checkRemerber.isChecked()) { // 检查复选框是否被选中
                editor.putBoolean("remember_password", true);
                if(checkAuto.isChecked()) {
                    editor.putBoolean("auto_login", true);
                } else {
                    editor.putBoolean("auto_login", false);
                }
                editor.putString("account", account);
                editor.putString("password", password);
            } else {
                editor.clear();
            }
            editor.apply();
            goNextActivity(MainActivity.class);
            toastShort("登录成功");
            finish();
        } else if (result == LOGIN_PASSWORD_ERROR){
            toastLong("用户名或密码错误");
        } else {
            toastLong("账号未注册");
        }
    }

    public int isLogin(String name, String password) {
        List<Account> account =  LitePal.select("*")
                .where("name = ?", name)
                .find(Account.class);
        if (account.size() == 0) {
            return LOGIN_NO_REGISTER;
        } else if (account.get(0).getPassword().equals(password)) {
            return LOGIN_SUCCESS;
        } else {
            return LOGIN_PASSWORD_ERROR;
        }

    }
}
