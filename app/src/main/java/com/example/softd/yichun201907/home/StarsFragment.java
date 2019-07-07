package com.example.softd.yichun201907.home;


import android.app.Fragment;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.softd.yichun201907.DB.Entity;
import com.example.softd.yichun201907.R;
import com.example.softd.yichun201907.base.BaseFragment;
import com.example.softd.yichun201907.base.MyApp;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheet;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheetItemView;

import org.litepal.LitePal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class StarsFragment extends BaseFragment {


    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.tv_content)
    TextView tvContent;
    Unbinder unbinder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab_collection)
    FloatingActionButton fabCollection;


    public StarsFragment() {
        // Required empty public constructor
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_stars;
    }

    @Override
    protected void initView() {
        unbinder = ButterKnife.bind(this, getContentView());






        fabCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimpleBottomSheetGrid();


            }
        });
    }

    @Override
    protected void initData() {

        collapsingToolbar.setTitle(MyApp.getTodayEntity().getTitle());
        Glide.with(this).load(MyApp.getTodayEntity().getImgUrl()).into(ivHead);
        tvContent.setText(MyApp.getTodayEntity().getContent());
        tvContent.setTextSize(20);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void showSimpleBottomSheetGrid() {
        final int TAG_SHARE_WECHAT_FRIEND = 0;
        final int TAG_SHARE_WECHAT_MOMENT = 1;
        final int TAG_SHARE_WEIBO = 2;
        final int TAG_SHARE_CHAT = 3;
        final int TAG_SHARE_LOCAL = 4;
        BottomSheet.BottomGridSheetBuilder builder = new BottomSheet.BottomGridSheetBuilder(getActivity());
        builder
                .addItem(R.drawable.icon_more_operation_share_friend, "分享到微信", TAG_SHARE_WECHAT_FRIEND, BottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.drawable.icon_more_operation_share_moment, "分享到朋友圈", TAG_SHARE_WECHAT_MOMENT, BottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.drawable.icon_more_operation_share_weibo, "分享到微博", TAG_SHARE_WEIBO, BottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.drawable.icon_more_operation_share_chat, "分享到私信", TAG_SHARE_CHAT, BottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.drawable.icon_more_operation_save, "保存到本地", TAG_SHARE_LOCAL, BottomSheet.BottomGridSheetBuilder.SECOND_LINE)
                .setOnSheetItemClickListener(new BottomSheet.BottomGridSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(BottomSheet dialog, BottomSheetItemView itemView) {
                        dialog.dismiss();
                        int tag = (int) itemView.getTag();
                        toastShort("tag:" + tag + ", content:" + itemView.toString());
                    }
                }).build().show();


    }




}
