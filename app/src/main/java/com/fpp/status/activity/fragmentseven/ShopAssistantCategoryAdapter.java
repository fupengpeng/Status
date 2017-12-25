package com.fpp.status.activity.fragmentseven;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fpp.status.R;
import com.fpp.status.entity.ShopAssistant;
import com.fpp.status.entity.ShopAssistantCategory;
import com.fpp.status.utils.Utility;

import java.util.List;


/**
 * 工作人员分类列表
 */
public class ShopAssistantCategoryAdapter extends BaseAdapter {
    List<ShopAssistantCategory> shopAssistantCategoryList;
    FragmentSevenActivity fragmentSevenActivity;
    private LayoutInflater mInflater = null;

    public ShopAssistantCategoryAdapter(FragmentSevenActivity fragmentSevenActivity, List<ShopAssistantCategory> shopAssistantCategoryList) {
        //根据context上下文加载布局，这里的是Demo17Activity本身，即this
        this.mInflater = LayoutInflater.from(fragmentSevenActivity);
        this.fragmentSevenActivity = fragmentSevenActivity;
        this.shopAssistantCategoryList = shopAssistantCategoryList;
    }

    @Override
    public int getCount() {
        //How many items are in the data set represented by this Adapter.
        //在此适配器中所代表的数据集中的条目数
        return shopAssistantCategoryList.size();
    }

    @Override
    public Object getItem(int position) {
        // Get the data item associated with the specified position in the data set.
        //获取数据集中与指定索引对应的数据项
        return shopAssistantCategoryList.get(position);
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        //如果缓存convertView为空，则需要创建View
        if (convertView == null) {
            holder = new ViewHolder();
            //根据自定义的Item布局加载布局
            convertView = mInflater.inflate(R.layout.list_view_item_select_waiter_classify, null);
            holder.tvLvItemSelectWaiter = (TextView) convertView.findViewById(R.id.tv_lv_item_select_waiter);
            holder.lvLvItemSelectWaiter = (ListView) convertView.findViewById(R.id.lv_lv_item_select_waiter);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvLvItemSelectWaiter.setText(shopAssistantCategoryList.get(position).getPostname());

        final List<ShopAssistant> shopAssistants = shopAssistantCategoryList.get(position).getUserlist();

        final ShopAssistantAdapter shopAssistantAdapter = new ShopAssistantAdapter(fragmentSevenActivity, shopAssistants);
        holder.lvLvItemSelectWaiter.setAdapter(shopAssistantAdapter);
        Utility.setListViewHeightBasedOnChildren(holder.lvLvItemSelectWaiter);

        holder.lvLvItemSelectWaiter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int p, long id) {
                for (ShopAssistantCategory shopAssistantCategory:shopAssistantCategoryList) {
                    for (ShopAssistant shopAssistant:shopAssistantCategory.getUserlist()) {
                        shopAssistant.setServe(false);
                    }
                }
                shopAssistantCategoryList.get(position).getUserlist().get(p).setServe(true);
                fragmentSevenActivity.getTvAtvtSelectWaiterAffirmTransferOrder().setBackgroundColor(Color.RED);
                ShopAssistantCategoryAdapter.this.notifyDataSetChanged();

            }
        });
        return convertView;
    }

    static class ViewHolder {
        TextView tvLvItemSelectWaiter;
        ListView lvLvItemSelectWaiter;
    }
}
