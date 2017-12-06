package com.fpp.status.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fpp.status.R;
import com.fpp.status.activity.customview.CustomViewSixActivity;
import com.fpp.status.entity.ServiceItemData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 客户服务项适配器
 */
public class OrderParticularsAdapter extends BaseAdapter {
    List<ServiceItemData> data;
    Context context;
    private LayoutInflater mInflater = null;

    public OrderParticularsAdapter(Context context, List<ServiceItemData> data) {
        //根据context上下文加载布局，这里的是Demo17Activity本身，即this
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        //How many items are in the data set represented by this Adapter.
        //在此适配器中所代表的数据集中的条目数
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // Get the data item associated with the specified position in the data set.
        //获取数据集中与指定索引对应的数据项
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        //Get the row id associated with the specified position in the list.
        //获取在列表中与指定索引对应的行id
        return position;
    }

    //Get a View that displays the data at the specified position in the data set.
    //获取一个在数据集中指定索引的视图来显示数据
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        //如果缓存convertView为空，则需要创建View
        if (convertView == null) {
            holder = new ViewHolder();
            //根据自定义的Item布局加载布局
            convertView = mInflater.inflate(R.layout.list_view_item_op_service_item, null);
            holder.cbLvItemOpServiceItemRemove = (CheckBox) convertView.findViewById(R.id.cb_lv_item_op_service_item_remove);
            holder.tvLvItemOpServiceItemName = (TextView) convertView.findViewById(R.id.tv_lv_item_op_service_item_name);
            holder.tvLvItemOpServiceItemNumber = (TextView) convertView.findViewById(R.id.tv_lv_item_op_service_item_number);
            holder.tvLvItemOpServiceItemPrice = (TextView) convertView.findViewById(R.id.tv_lv_item_op_service_item_price);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvLvItemOpServiceItemName.setText(data.get(position).getName());
        holder.tvLvItemOpServiceItemNumber.setText(data.get(position).getNumber()+"");
        holder.tvLvItemOpServiceItemPrice.setText((int) data.get(position).getPrice()+"");

        return convertView;
    }

    static class ViewHolder {
        CheckBox cbLvItemOpServiceItemRemove;
        TextView tvLvItemOpServiceItemName;
        TextView tvLvItemOpServiceItemNumber;
        TextView tvLvItemOpServiceItemPrice;

    }
}