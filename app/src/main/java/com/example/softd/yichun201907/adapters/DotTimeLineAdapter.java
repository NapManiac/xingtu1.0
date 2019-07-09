package com.example.softd.yichun201907.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.softd.yichun201907.DB.Event;
import com.example.softd.yichun201907.R;
import com.vivian.timelineitemdecoration.util.Util;

import java.util.List;

/**
 * *          _       _
 * *   __   _(_)_   _(_) __ _ _ __
 * *   \ \ / / \ \ / / |/ _` | '_ \
 * *    \ V /| |\ V /| | (_| | | | |
 * *     \_/ |_| \_/ |_|\__,_|_| |_|
 * <p>
 * Created by vivian on 2017/6/9.
 */

public class DotTimeLineAdapter extends RecyclerView.Adapter<DotTimeLineAdapter.ViewHolder> {
    Context mContext;
    List<Event> mList;
    int[] colors = {0xffFFAD6C, 0xff62f434, 0xffdeda78, 0xff7EDCFF, 0xff58fdea, 0xfffdc75f};//颜色组

    public void setList(List<Event> list) {
        mList = list;
    }

    public DotTimeLineAdapter(Context context) {
        mContext = context;
    }

    public DotTimeLineAdapter(Context context, List<Event> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pop_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.time.setText(Util.LongtoStringFormat(1000 * mList.get(position).getEndTime()));
        holder.textView.setText(mList.get(position).getEventName());
        holder.time.setTextColor(colors[position % colors.length]);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView time;
        TextView textView;

        public ViewHolder(View view) {
            super(view);
            time = (TextView) view.findViewById(R.id.time);
            textView = (TextView) view.findViewById(R.id.text);
        }
    }

}
