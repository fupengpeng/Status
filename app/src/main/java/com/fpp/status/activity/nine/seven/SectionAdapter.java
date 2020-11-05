package com.fpp.status.activity.nine.seven;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fpp.status.R;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
/**
 * Description:
 * Author: fp
 * Date: 2018/6/28  23:37
 */

public class SectionAdapter  extends RecyclerView.Adapter<SectionAdapter.ViewHolder> {

    private Context context;
    private List<SectionBean> list;//父层列表 （里面是 text + 子List（子list是image+text））

    public SectionAdapter(Context context, List<SectionBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_section, parent, false);
        return new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitle.setText("Item " + position);
        //把内层的RecyclerView 绑定在外层的onBindViewHolder
        // 先判断一下是不是已经设置了Adapter
        if (holder.mRecyclerView.getAdapter() == null) {
            holder.mRecyclerView.setAdapter(new TopicAdapter(context, list.get(position).getTopicBeans()));
        } else {
            holder.mRecyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * static ViewHolder
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTitle;//标题
        RecyclerView mRecyclerView; // 父层的 RecyclerView

        public ViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.tv_item_section_title);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.rv_item_section_topic_list);
            RecyclerView.LayoutManager manager = new GridLayoutManager(itemView.getContext(), 4);
            // 需要注意的是GridLayoutManager要设置setAutoMeasureEnabled(true)成自适应高度
            manager.setAutoMeasureEnabled(true);
            mRecyclerView.setLayoutManager(manager);
        }
    }

}
