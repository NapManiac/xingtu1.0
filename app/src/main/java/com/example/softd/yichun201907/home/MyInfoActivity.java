package com.example.softd.yichun201907.home;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.softd.yichun201907.DB.UserInfo;
import com.example.softd.yichun201907.R;
import com.example.softd.yichun201907.base.BaseActivity;
import com.example.softd.yichun201907.base.MyApp;
import com.xuexiang.xui.utils.StatusBarUtils;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class MyInfoActivity extends BaseActivity {

    public static final int SELECT_PHOTO_REQUEST_CODE = 1;

    //外存访问权限
    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    private static String[] PERMISSIONS_STORAGE = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};

    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_changePass)
    RoundButton btnChangePass;
    @BindView(R.id.btn_changeInfo)
    RoundButton btnChangeInfo;
    @BindView(R.id.et_username)
    TextView etUsername;
    @BindView(R.id.et_user_email)
    TextView etUserEmail;
    @BindView(R.id.et_user_tel)
    TextView etUserTel;


    @OnClick(R.id.iv_head)
    public void onViewClicked() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, SELECT_PHOTO_REQUEST_CODE);
    }


    @Override
    public int initLayout() {
        return R.layout.activity_my_info;
    }

    @Override
    public void initView() {
        //沉浸式状态栏
        StatusBarUtils.translucent(this);
        ButterKnife.bind(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        etUsername.setText("  " + MyApp.getUserInfo().getName());
        etUserEmail.setText("  " + MyApp.getUserInfo().getEmail());
        etUserTel.setText("  " + MyApp.getUserInfo().getTel());
    }

    @Override
    public void initData() {
        verifyStoragePermissions(MyInfoActivity.this);
        if (!MyApp.getUserInfo().getHeadUri().equals("")) {
            ivHead.setImageURI(Uri.parse(MyApp.getUserInfo().getHeadUri()));
        } else {
            ivHead.setImageResource(R.drawable.nav_icon);
        }
    }

    //外存申请权限函数
    public static void verifyStoragePermissions(Activity activity) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity, "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框

                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PHOTO_REQUEST_CODE && resultCode == RESULT_OK) {
            Uri uriPicture = data.getData();

            Glide.with(this)
                    .load(uriPicture)
                    .apply(bitmapTransform(new CircleCrop()))
                    .into(ivHead);

            MyApp.getUserInfo().setHeadUri(uriPicture.toString());
            UserInfo userInfo = new UserInfo();
            userInfo.setHeadUri(uriPicture.toString());
            userInfo.updateAll("name = ?", MyApp.getUserInfo().getName());

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_changeInfo, R.id.btn_changePass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_changeInfo:
                goNextActivity(ChangeInfoActivity.class);
                break;
            case R.id.btn_changePass:
                goNextActivity(ChangePwActivity.class);
                break;
        }
    }
}
