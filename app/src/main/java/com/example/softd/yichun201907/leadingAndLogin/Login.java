package com.example.softd.yichun201907.leadingAndLogin;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.softd.yichun201907.DB.Account;
import com.example.softd.yichun201907.DB.UserInfo;
import com.example.softd.yichun201907.R;
import com.example.softd.yichun201907.base.BaseActivity;
import com.example.softd.yichun201907.home.MainActivity;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

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
    @BindView(R.id.text_register)
    AppCompatTextView textRegister;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

//        dropTable();
//        addAdmin();



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
        if (auto_login) {
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
        int result = isLogin(account, password);
        if (result == LOGIN_SUCCESS) {
            editor = pref.edit();
            if (checkRemerber.isChecked()) { // 检查复选框是否被选中
                editor.putBoolean("remember_password", true);
                if (checkAuto.isChecked()) {
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
        } else if (result == LOGIN_PASSWORD_ERROR) {
            toastLong("用户名或密码错误");
        } else {
            toastLong("账号未注册");
        }
    }

    public int isLogin(String name, String password) {
        List<Account> account = LitePal.select("*")
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

    @OnClick(R.id.text_register)
    public void onRegViewClicked() {
        goNextActivity(Register.class);
    }

    //删除所有数据
    public void dropTable() {
        LitePal.deleteAll(Account.class);
        LitePal.deleteAll(UserInfo.class);
    }

    //添加管理员
    public void addAdmin() {

        Account account1 = new Account();
        account1.setName("111111");
        account1.setPassword("111111");
        account1.save();

        UserInfo userInfo = new UserInfo();
        userInfo.setName("111111");
        userInfo.setTel("15297828708");
        userInfo.setEmail("1394327107@qq.com");
        userInfo.save();
    }

}
