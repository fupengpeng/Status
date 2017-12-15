package com.fpp.status.activity.fragmentsix;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fpp.status.R;
import com.fpp.status.adapter.BaseAdapter;
import com.fpp.status.entity.ServiceItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 分类列表下服务条目列表适配器
 */
class AddServiceItemAdapter extends BaseAdapter<ServiceItem> {

    public AddServiceItemAdapter(Context context, List<ServiceItem> data) {
        super(context, data);
    }

    //获取一个在数据集中指定索引的视图来显示数据
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        //如果缓存convertView为空，则需要创建View
        if (convertView == null) {
            //根据自定义的Item布局加载布局
            convertView = getLayoutInflater().inflate(R.layout.list_view_item_select_soc, null);
            holder = new ViewHolder(convertView);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //设置数据
        ServiceItem item = getItem(position);
        holder.tvLvItemSelectServicePrice.setText(item.getDiscount());
        holder.tvLvItemSelectServiceName.setText(item.getServiceItemName());
        //设置数量，根据数量判断减少的按钮是否显示
        if (item.getDiscount().equals("0") || item.getDiscount().equals("")) {
            holder.tvLvItemSelectServiceAdd.setText("+");
            if (holder.ivLvItemSelectServiceRemove.getVisibility() != View.GONE) {
                holder.ivLvItemSelectServiceRemove.setVisibility(View.GONE);
            }
        } else {
            if (holder.ivLvItemSelectServiceRemove.getVisibility() == View.GONE) {
                holder.ivLvItemSelectServiceRemove.setVisibility(View.VISIBLE);
            }
            holder.tvLvItemSelectServiceAdd.setText(item.getDiscount());
        }

        //子条目中增加服务按钮的点击事件设置
        final ViewHolder addHolder = holder;
        holder.tvLvItemSelectServiceAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemAddListener.onAddClick(position, addHolder);
            }
        });
        //子条目中减少服务按钮的点击事件设置
        final ViewHolder deleteHolder = holder;
        holder.ivLvItemSelectServiceRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemDeleteListener.onDeleteClick(position, deleteHolder);
            }
        });

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_lv_item_select_service_add)
        TextView tvLvItemSelectServiceAdd;
        @BindView(R.id.iv_lv_item_select_service_remove)
        ImageView ivLvItemSelectServiceRemove;
        @BindView(R.id.tv_lv_item_select_service_name)
        TextView tvLvItemSelectServiceName;
        @BindView(R.id.tv_lv_item_select_service_price)
        TextView tvLvItemSelectServicePrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    /**
     * 子条目删除按钮监听接口
     */
    private OnItemDeleteListener mOnItemDeleteListener;

    /**
     * 删除按钮的监听接口
     */
    public interface OnItemDeleteListener {
        void onDeleteClick(int position, ViewHolder holder);
    }

    /**
     * 子条目中删除按钮设置监听
     *
     * @param mOnItemDeleteListener
     */
    public void setOnItemDeleteClickListener(OnItemDeleteListener mOnItemDeleteListener) {
        this.mOnItemDeleteListener = mOnItemDeleteListener;
    }

    /**
     * 子条目添加按钮监听接口
     */
    private OnItemAddListener mOnItemAddListener;

    /**
     * 添加按钮的监听接口
     */
    public interface OnItemAddListener {
        void onAddClick(int position, ViewHolder holder);
    }

    /**
     * 子条目中添加按钮设置监听
     *
     * @param mOnItemAddListener
     */
    public void setOnItemAddClickListener(OnItemAddListener mOnItemAddListener) {
        this.mOnItemAddListener = mOnItemAddListener;
    }



}
