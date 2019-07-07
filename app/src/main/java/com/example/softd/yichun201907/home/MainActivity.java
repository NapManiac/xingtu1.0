package com.example.softd.yichun201907.home;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.softd.yichun201907.DB.Entity;
import com.example.softd.yichun201907.R;
import com.example.softd.yichun201907.base.BaseActivity;
import com.example.softd.yichun201907.base.MyApp;
import com.example.softd.yichun201907.leadingAndLogin.Login;
import com.xuexiang.xui.utils.StatusBarUtils;
import com.xuexiang.xui.widget.dialog.DialogLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.softd.yichun201907.base.ActivityCollector.finishAll;

public class MainActivity extends BaseActivity {
    @BindView(R.id.vp_tab)
    ViewPager vpTab;
    @BindView(R.id.iv_message)
    ImageView ivMessage;
    @BindView(R.id.ll_message)
    LinearLayout llMessage;
    @BindView(R.id.iv_contact)
    ImageView ivContact;
    @BindView(R.id.ll_contact)
    LinearLayout llContact;
    @BindView(R.id.iv_discover)
    ImageView ivDiscover;
    @BindView(R.id.ll_discover)
    LinearLayout llDiscover;
    @BindView(R.id.iv_my)
    ImageView ivMy;
    @BindView(R.id.ll_my)
    LinearLayout llMy;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private List<Fragment> fragmentList = new ArrayList<>();

    //修改头像图标
    ImageView headImg;

    View header;


    public static MainActivity  mainActivity;

    public MainActivity() {
        mainActivity = this;
    }



    @Override
    public int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        //沉浸式状态栏
        StatusBarUtils.translucent(this);
        ButterKnife.bind(this);


        TasksFragment messageFragment = new TasksFragment();
        StarsFragment contactFragment = new StarsFragment();
        NoteFragment discoverFragment = new NoteFragment();
        TimelineFragment timelineFragment = new TimelineFragment();
        fragmentList.add(messageFragment);
        fragmentList.add(contactFragment);
        fragmentList.add(discoverFragment);
        fragmentList.add(timelineFragment);

        //给viewPage设置适配器，将四个fragment设置给它
        vpTab.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            //ViewPage每一页的视图（布局）
            @Override
            public Fragment getItem(int i) {
                return fragmentList.get(i);
            }

            //总共有多少页视图
            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        //监听滑动到了哪一项
        vpTab.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {//滑动到了哪一项
                iconChange(i);//让图标跟随滑动变动
                invalidateOptionsMenu();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        iconChange(0);//默认选中第一页（图标的变化）


        //修改头像
        header = navView.getHeaderView(0);
        headImg = header.findViewById(R.id.icon_image);
        headImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goNextActivity(MyInfoActivity.class);
            }
        });

        //滑动菜单逻辑

        navView.setCheckedItem(R.id.it_call);

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.it_exit: {

                        DialogLoader.getInstance().showConfirmDialog(
                                MainActivity.this,
                                "提示：退出登录",
                                "是",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        goNextActivity(Login.class);
                                        MyApp.clearCache();
                                        dialog.dismiss();
                                        finishAll();
                                    }
                                },
                                "否",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }
                        );

                        break;
                    }
                    default:
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }

        });

        toolbar.setTitle("待办");

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView username = header.findViewById(R.id.tv_username);
        TextView mail = header.findViewById(R.id.tv_mail);
        //


        if (MyApp.getUserInfo().getHeadUri().equals("")) {
            headImg.setImageResource(R.drawable.nav_icon);
        } else {
            headImg.setImageURI(Uri.parse(MyApp.getUserInfo().getHeadUri()));
        }

        username.setText(MyApp.getUserInfo().getName());
        mail.setText(MyApp.getUserInfo().getEmail());


    }

    @Override
    public void initData() {
    }


    //当某一项被选中的逻辑
    private void onItemSelected(int i) {
        iconChange(i);//处理图标的变化
        vpTab.setCurrentItem(i);//设置viewPage中选中哪一项
    }

    public void debugCollection() {

    }



    //处理图标的变化
    private void iconChange(int i) {
        resetIcon();//重置所有图标为灰色
        switch (i) {
            case 0:
                toolbar.setTitle("待办");

                ivMessage.setImageResource(R.mipmap.task_selected);
                break;
            case 1:
                toolbar.setTitle("拾星");
                ivContact.setImageResource(R.mipmap.plant_selected);
                break;
            case 2:
                toolbar.setTitle("备忘");
                ivDiscover.setImageResource(R.mipmap.write_selected);
                break;
            case 3:
                toolbar.setTitle("足迹");
                ivMy.setImageResource(R.mipmap.time_selected);
                break;
        }
    }

    //重置所有图标为灰色
    private void resetIcon() {
        ivMessage.setImageResource(R.mipmap.task_nomal);
        ivContact.setImageResource(R.mipmap.plant_nomal);
        ivDiscover.setImageResource(R.mipmap.write_normal);
        ivMy.setImageResource(R.mipmap.time_nomal);
    }

    @OnClick({R.id.ll_message, R.id.ll_contact, R.id.ll_discover, R.id.ll_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_message:
                onItemSelected(0);
                break;
            case R.id.ll_contact:
                onItemSelected(1);
                break;
            case R.id.ll_discover:
                onItemSelected(2);
                break;
            case R.id.ll_my:
                onItemSelected(3);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_stars, menu);
        switch (vpTab.getCurrentItem()) {
            case 0:
                menu.findItem(R.id.it_collection).setVisible(false);
                break;
            case 1:
                menu.findItem(R.id.it_collection).setVisible(true);
                if (MyApp.getTodayEntity().getIsCollection() == 2) {
                    menu.findItem(R.id.it_collection).setIcon(R.drawable.ic_is_collection);
                } else {
                    menu.findItem(R.id.it_collection).setIcon(R.drawable.ic_collection);
                }
                break;

            case 2:
                menu.findItem(R.id.it_collection).setVisible(false);
                break;
            case 3:
                menu.findItem(R.id.it_collection).setVisible(false);
                break;
        }


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.it_collection:
                if (MyApp.getTodayEntity().getIsCollection() == 1) {
                    item.setIcon(R.drawable.ic_is_collection);
                    Entity entity = new Entity();
                    entity.setIsCollection(2);
                    entity.updateAll("idid=?", MyApp.getTodayEntity().getIdid());

                    MyApp.getTodayEntity().setIsCollection(2);
                    toastShort("取消收藏");

                } else {
                    item.setIcon(R.drawable.ic_collection);
                    Entity entity = new Entity();
                    entity.setIsCollection(1);
                    entity.updateAll("idid=?", MyApp.getTodayEntity().getIdid());

                    MyApp.getTodayEntity().setIsCollection(1);
                    toastShort("收藏成功");
                }
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;

            default:
        }
        return true;
    }

}
