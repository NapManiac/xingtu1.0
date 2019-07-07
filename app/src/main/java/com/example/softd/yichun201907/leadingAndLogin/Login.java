package com.example.softd.yichun201907.leadingAndLogin;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.softd.yichun201907.DB.Account;
import com.example.softd.yichun201907.DB.Entity;
import com.example.softd.yichun201907.DB.Event;
import com.example.softd.yichun201907.DB.UserInfo;
import com.example.softd.yichun201907.R;
import com.example.softd.yichun201907.base.BaseActivity;
import com.example.softd.yichun201907.base.MyApp;
import com.example.softd.yichun201907.home.MainActivity;
import com.xuexiang.xui.utils.StatusBarUtils;
import com.xuexiang.xui.widget.textview.marqueen.MarqueeFactory;
import com.xuexiang.xui.widget.textview.marqueen.MarqueeView;
import com.xuexiang.xui.widget.textview.marqueen.SimpleNoticeMF;

import org.litepal.LitePal;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.xuexiang.xui.XUI.getContext;

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
    @BindView(R.id.marquee_view)
    MarqueeView marqueeView;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    final List<String> datas = Arrays.asList("千里之行", "始于足下", "绳锯木断", "水滴石穿");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //沉浸式状态栏
        StatusBarUtils.translucent(this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        dropDB();
        addAdmin();



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

        //初始化滚动字幕条
        MarqueeFactory<TextView, String> marqueeFactory1 = new SimpleNoticeMF(getContext());
        marqueeView.setMarqueeFactory(marqueeFactory1);
        marqueeView.startFlipping();
        marqueeFactory1.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
            @Override
            public void onItemClickListener(MarqueeFactory.ViewHolder<TextView, String> holder) {
                toastLong(holder.getData());
            }
        });
        marqueeFactory1.setData(datas);

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
        String name = username.getText().toString();
        String password = userpassword.getText().toString();
        int result = isLogin(name, password);
        if (result == LOGIN_SUCCESS) {
            editor = pref.edit();
            if (checkRemerber.isChecked()) { // 检查复选框是否被选中
                editor.putBoolean("remember_password", true);
                if (checkAuto.isChecked()) {
                    editor.putBoolean("auto_login", true);
                } else {
                    editor.putBoolean("auto_login", false);
                }
                editor.putString("account", name);
                editor.putString("password", password);
            } else {
                editor.clear();
            }
            editor.apply();
            goNextActivity(MainActivity.class);
            toastShort("登录成功");

            initUserInfo(name);
            finish();
        } else if (result == LOGIN_PASSWORD_ERROR) {
            toastLong("用户名或密码错误");
        } else {
            toastLong("账号未注册");
        }
    }
    //初始化用户数据
    private void initUserInfo(String name) {
        UserInfo userInfo = LitePal.select("*")
                .where("name = ?", name)
                .find(UserInfo.class).get(0);
        MyApp.setUserInfo(userInfo);
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
    public void dropDB() {
        LitePal.deleteDatabase("DB");
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
        userInfo.setEmail("23333@qq.com");
        userInfo.save();

        Event event1 = new Event();
        event1.setTitle("指导组员");
        event1.setName("111111");
        event1.setEventName("幸苦地指导各位组员");
        event1.setStartTime(1497229200);
        event1.setEndTime(1497240000);
        event1.setStatus(Event.COMPLETE);
        event1.save();

        Event event2 = new Event();
        event2.setName("111111");
        event2.setTitle("写项目");
        event2.setEventName("幸苦地帮lxb改BUG");
        event2.setStartTime(1497243600);
        event2.setEndTime(1497247200);
        event2.setStatus(Event.INCOMPLETE);
        event2.save();

        Event event3 = new Event();
        event3.setTitle("答辩");
        event3.setName("111111");
        event3.setEventName("答辩");
        event3.setStartTime(1497249000);
        event3.setEndTime(1497252600);
        event3.setStatus(Event.NOT_START);
        event3.save();

        Entity today = new Entity();
        today.setIdid("20190707");
        today.setTitle("你该努力了");
        today.setIsCollection(0);
        today.setContent("         放下你的浮躁，放下你的懒惰，放下你的三分钟热度，放空你禁不住诱惑的大脑，放开你容易被任何事物吸引的眼睛，放淡你什么都想聊两句八卦的嘴巴，静下心来好好做你该做的事，该好好努力了。\n         时间是最公平的，活一天就拥有24小时，差别只是珍惜。你若不相信努力和时光，时光一定第一个辜负你。有梦想就立刻行动，因为现在过的每一天，都是余生中最年轻的一天。");
        today.setImgUrl(R.drawable.head);
        today.save();



    }

}
