package com.example.softd.yichun201907.home;


import android.app.Fragment;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.softd.yichun201907.R;
import com.example.softd.yichun201907.base.BaseFragment;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheet;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheetItemView;

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
    }

    @Override
    protected void initData() {
        collapsingToolbar.setTitle("你该努力了");
        Glide.with(this).load(R.drawable.head).into(ivHead);
        tvContent.setText("         放下你的浮躁，放下你的懒惰，放下你的三分钟热度，放空你禁不住诱惑的大脑，放开你容易被任何事物吸引的眼睛，放淡你什么都想聊两句八卦的嘴巴，静下心来好好做你该做的事，该好好努力了。\n         时间是最公平的，活一天就拥有24小时，差别只是珍惜。你若不相信努力和时光，时光一定第一个辜负你。有梦想就立刻行动，因为现在过的每一天，都是余生中最年轻的一天。");
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
