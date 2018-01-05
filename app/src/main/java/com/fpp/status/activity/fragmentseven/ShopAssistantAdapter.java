package com.fpp.status.activity.fragmentseven;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.fpp.status.R;
import com.fpp.status.adapter.BaseAdapter;
import com.fpp.status.entity.ShopAssistant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 工作人员列表
 */
public class ShopAssistantAdapter extends BaseAdapter<ShopAssistant> {

    public ShopAssistantAdapter(Context context, List<ShopAssistant> data) {
        super(context, data);
    }

    //获取一个在数据集中指定索引的视图来显示数据
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        //如果缓存convertView为空，则需要创建View
        if (convertView == null) {
            //根据自定义的Item布局加载布局
            convertView = getLayoutInflater().inflate(R.layout.list_view_item_select_waiter, null);
            holder = new ViewHolder(convertView);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //设置数据
        ShopAssistant item = getItem(position);

        holder.tvLvItemSelectWaiterUserName.setText(item.getUsername());
        holder.tvLvItemSelectWaiterUserId.setText(item.getUserid());
        holder.ivLvItemSelectWaiterUserImage.setImageResource(R.drawable.ic_account_circle_black_24dp);
        holder.tvLvItemSelectWaiterUserPosition.setText("AAA");
        if (item.isServe()){
            holder.cbLvItemSelectWaiter.setBackgroundResource(R.drawable.order_waiter_selected);
            holder.cbLvItemSelectWaiter.setChecked(item.isServe());
        }else {
            holder.cbLvItemSelectWaiter.setBackgroundResource(R.color.colorWhite);
            holder.cbLvItemSelectWaiter.setChecked(item.isServe());
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_lv_item_select_waiter_user_image)
        ImageView ivLvItemSelectWaiterUserImage;
        @BindView(R.id.tv_lv_item_select_waiter_user_name)
        TextView tvLvItemSelectWaiterUserName;
        @BindView(R.id.tv_lv_item_select_waiter_user_position)
        TextView tvLvItemSelectWaiterUserPosition;
        @BindView(R.id.tv_lv_item_select_waiter_user_id)
        TextView tvLvItemSelectWaiterUserId;
        @BindView(R.id.cb_lv_item_select_waiter)
        CheckBox cbLvItemSelectWaiter;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
