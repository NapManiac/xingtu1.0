package com.example.softd.yichun201907.home;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.softd.yichun201907.DB.Event;
import com.example.softd.yichun201907.R;
import com.example.softd.yichun201907.adapters.MyOverlayAdapter;
import com.example.softd.yichun201907.base.BaseFragment;
import com.example.softd.yichun201907.base.MyApp;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheet;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheetItemView;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class TasksFragment extends BaseFragment {


    @BindView(R.id.vi_tasks)
    ViewPager vp;


    Unbinder unbinder;
    @BindView(R.id.fab_add)
    FloatingActionButton fabAdd;
    private String[] imgUrls = new String[]{"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1638079650,2146947483&fm=27&gp=0.jpg"
            , "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1297505592,1789076279&fm=27&gp=0.jpg"
            , "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556094815760&di=1abe539eb4691346c07dd44a6bba7383&imgtype=0&src=http%3A%2F%2Fpic.xoyo.com%2Fbbs%2F2010%2F11%2F30%2F10113010300bdf68f9f96b70e4.jpg"
            , "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3925233323,1705701801&fm=27&gp=0.jpg"
            , "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556094846679&di=fb63907df03214def9e4576149ac1b8e&imgtype=0&src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farticle%2Fc9257b7638df71ad54d17de9cc92034741c86236.jpg"
            , "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556094860210&di=56a4eba83e1fa1fee69787b35f5b2806&imgtype=0&src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farticle%2F5d3129cc0cc155f696a68eec5356d9387f4c517d.jpg"};


    public TasksFragment() {
        // Required empty public constructor
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_tasks;
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void initData() {
        List<Event> eventList = LitePal.select("*")
                .where("name = ?", MyApp.getUserInfo().getName())
                .find(Event.class);
        MyApp.setEvents(eventList);

        vp = getContentView().findViewById(R.id.vi_tasks);
        eventList = LitePal.select("*")
                .where("name = ?", MyApp.getUserInfo().getName())
                .find(Event.class);
        MyOverlayAdapter adapter = new MyOverlayAdapter(getContext(), eventList);//传入事件列表，会根据当前事件选择是否显示在待办卡组
        adapter.setImgUrlsAndBindViewPager(vp, imgUrls, 3);

        vp.setAdapter(adapter);
        vp.setCurrentItem(100000); //伪无限循环

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.fab_add)
    public void onViewClicked() {
        goNextActivity(AddTaskActivity.class);
    }

}
