package com.fpp.status.activity.nine.seven;

import android.content.Context;
<<<<<<< HEAD
=======
import android.support.v7.widget.RecyclerView;
>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fpp.status.R;

import java.util.List;

<<<<<<< HEAD
import androidx.recyclerview.widget.RecyclerView;

=======
>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c
/**
 * Description:
 * Author: fp
 * Date: 2018/6/28  23:42
 */

public class TopicAdapter  extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {

    private Context context;
    private List<TopicBean> list; // List 集合（里面是image+text）

    /**
     * 构造函数
     * @param context
     * @param list
     */
    public TopicAdapter(Context context, List<TopicBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_topic, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TopicBean info = list.get(position);
        holder.textInfo.setText(info.getId());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * static ViewHolder
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageInfo;
        TextView textInfo;

        public ViewHolder(View itemView) {
            super(itemView);
            imageInfo = (ImageView) itemView.findViewById(R.id.iv_item_topic_pic);
            textInfo = (TextView) itemView.findViewById(R.id.tv_item_topic_id);
        }
    }


}
