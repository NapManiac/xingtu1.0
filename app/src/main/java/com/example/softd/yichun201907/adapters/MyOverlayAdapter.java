package com.example.softd.yichun201907.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.request.RequestOptions;
import com.example.library.BaseOverlayPageAdapter;
import com.example.softd.yichun201907.DB.Event;
import com.example.softd.yichun201907.R;
import com.example.softd.yichun201907.home.MainActivity;
import com.example.softd.yichun201907.home.StarsFragment;
import com.vivian.timelineitemdecoration.util.Util;

import java.util.ArrayList;
import java.util.List;

public class MyOverlayAdapter extends BaseOverlayPageAdapter {
    private LayoutInflater mInflater;
    List<Event> taskList = new ArrayList<>();
    List<String> encoList = new ArrayList<>();

    long down;
    long up;


    public MyOverlayAdapter(Context context, List<Event> eventList) {
        super(context, new RequestOptions().error(R.drawable.error).placeholder(R.drawable.placeholder));
        mInflater = LayoutInflater.from(context);
        encoList.add("对待生命，你不妨大胆一点，\n" +
                "因为我们始终要失去它。\n\n" +
                "——尼采");

        encoList.add("你不是爱的终点，\n" +
                "只是爱的源动力。\n\n" +
                "——赫尔曼·黑塞《堤契诺之歌》");

        encoList.add("要记住，这世上并不是所有人\n" +
                "都有你拥有的那些条件。\n\n" +
                "——菲茨杰拉德《了不起的盖茨比》");

        encoList.add("我在晨曦中吻你\n" +
                "耳听清晨群鸟的啼鸣。\n" +
                "目睹爬山虎爬上屋顶\n" +
                "——维马丁《九月的早晨》");

        encoList.add("人生如梦，我投入的却是真情\n" +
                "世界先爱了我，我不能不爱他。\n\n" +
                "——维马丁《九月的早晨》");



        for (int i = 0; i < eventList .size(); i++) {
            Event e = eventList.get(i);
            if(System.currentTimeMillis()/1000 > eventList.get(i).getEndTime())
                taskList.add(e);
        }
    }
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final View view = itemView();//获取根视图
        if (null == view) {
            throw new RuntimeException("you should set a item layout");
        }
        final ImageView iv = findImageView(view);
        if (null == iv) {
            throw new RuntimeException("you should set a item layout");
        }

        TextView s_time = view.findViewById(R.id.it_taskTime_start);
        TextView e_time = view.findViewById(R.id.it_taskTime_end);
        TextView taskName = view.findViewById(R.id.it_taskname);//对应Event的title
        TextView encorege = view.findViewById(R.id.tv_excute);
        TextView taskTodo = view.findViewById(R.id.tv_taskname);//要做的事

        Event emptyEvent;
        if(taskList.size() == 0) {//若无待办
            taskName.setText("暂无待办");
            taskTodo.setText("禅定");
            s_time.setText("开始时间：--:--");
            e_time.setText("结束时间：--:--");
            encorege.setText("星星那么多,为你而闪耀的\n总有那么几颗\n\n——dosusang");
        }
        else {
            final int p = position % taskList.size();
            emptyEvent = taskList.get(p);
            s_time.setText("开始时间："+ Util.LongtoStringFormat(1000*emptyEvent.getStartTime()));
            e_time.setText("结束时间："+Util.LongtoStringFormat(1000*emptyEvent.getEndTime()));
            taskName.setText(emptyEvent.getTitle());
            taskTodo.setText(emptyEvent.getEventName());//要做的事情
            encorege.setText(encoList.get(p%encoList.size()));
        }

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        down = (int)event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        up = (int)event.getY();
                        if(down-up>300) {
                            StarsFragment starsFragment = (StarsFragment) MainActivity.mainActivity.fragmentList.get(1);
                            starsFragment.showSimpleBottomSheetGrid();
                        }
                        break;


                }
                return true;
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    protected View itemView() {
        return mInflater.inflate(R.layout.item_cardviewpage, null);
    }


}
