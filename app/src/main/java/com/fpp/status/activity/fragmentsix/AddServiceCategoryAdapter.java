package com.fpp.status.activity.fragmentsix;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.fpp.status.R;
import com.fpp.status.entity.LVOne;
import com.fpp.status.entity.LVTwo;
import com.fpp.status.entity.ServiceCategory;
import com.fpp.status.entity.ServiceItem;
import com.fpp.status.view.HorizontalListView;

import java.util.ArrayList;
import java.util.List;


/**
 * 添加服务条目分类列表
 */
public class AddServiceCategoryAdapter extends BaseAdapter {
    List<ServiceCategory> serviceCategoryList;
    Context context;
    private LayoutInflater mInflater = null;

    public AddServiceCategoryAdapter(Context context, List<ServiceCategory> serviceCategoryList) {
        //根据context上下文加载布局，这里的是Demo17Activity本身，即this
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.serviceCategoryList = serviceCategoryList;
    }

    @Override
    public int getCount() {
        //How many items are in the data set represented by this Adapter.
        //在此适配器中所代表的数据集中的条目数
        return serviceCategoryList.size();
    }

    @Override
    public Object getItem(int position) {
        // Get the data item associated with the specified position in the data set.
        //获取数据集中与指定索引对应的数据项
        return serviceCategoryList.get(position);
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
            convertView = mInflater.inflate(R.layout.list_view_item_select_soc_content, null);
            holder.tvLvItemSelectSocContentId = (TextView) convertView.findViewById(R.id.tv_lv_item_select_soc_content_id);
            holder.tvLvItemSelectSocContentName = (TextView) convertView.findViewById(R.id.tv_lv_item_select_soc_content_name);
            holder.hlvFragmentAddService = (HorizontalListView) convertView.findViewById(R.id.hlv_fragment_add_service);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvLvItemSelectSocContentId.setText(serviceCategoryList.get(position).getServiceCategoryID());
        holder.tvLvItemSelectSocContentName.setText(serviceCategoryList.get(position).getServiceCategoryName());
        final List<ServiceItem> serviceItemList = serviceCategoryList.get(position).getServiceItemList();
        final AddServiceItemAdapter addServiceItemAdapter = new AddServiceItemAdapter(context, serviceItemList);
        holder.hlvFragmentAddService.setAdapter(addServiceItemAdapter);
        //添加服务按钮点击事件
        addServiceItemAdapter.setOnItemAddClickListener(new AddServiceItemAdapter.OnItemAddListener() {
            @Override
            public void onAddClick(int position, AddServiceItemAdapter.ViewHolder holder) {
                addServiceItem(position, holder, addServiceItemAdapter, serviceItemList);
            }
        });
        //减少服务按钮点击事件
        addServiceItemAdapter.setOnItemDeleteClickListener(new AddServiceItemAdapter.OnItemDeleteListener() {
            @Override
            public void onDeleteClick(int position, AddServiceItemAdapter.ViewHolder holder) {
                deleteServiceItem(position, holder, addServiceItemAdapter, serviceItemList);
                List<ServiceItem> serviceItemNumList = new ArrayList<ServiceItem>();

                getServiceItemNumList(serviceItemNumList, serviceItemList);

            }
        });

        return convertView;
    }


    static class ViewHolder {
        TextView tvLvItemSelectSocContentId;
        TextView tvLvItemSelectSocContentName;
        HorizontalListView hlvFragmentAddService;

    }


    /**
     * 统计此分类的选定的数据--筛选选定的项目，统计出来
     *
     * @param serviceItemNumList 筛选过后的数据
     * @param serviceItems       删选数据源
     */
    private void getServiceItemNumList(List<ServiceItem> serviceItemNumList, List<ServiceItem> serviceItems) {
        serviceItemNumList.clear();
        for (ServiceItem serviceItem : serviceItems) {
            if (Integer.parseInt(serviceItem.getDiscount()) > 0) {
                serviceItemNumList.add(serviceItem);
            }
        }
    }

    /**
     * 添加
     *
     * @param position     第position个子条目
     * @param holder       子条目的布局
     * @param serviceItemAdapter 使用到的数据适配器
     * @param serviceItems       布局所要添加的数据
     */
    private void addServiceItem(int position, AddServiceItemAdapter.ViewHolder holder, AddServiceItemAdapter serviceItemAdapter, List<ServiceItem> serviceItems) {
        ServiceItem item = (ServiceItem) serviceItemAdapter.getItem(position);
        int number = Integer.parseInt(item.getDiscount());

        if (number == 1) {
            if (holder.ivLvItemSelectServiceRemove.getVisibility() == View.GONE) {
                holder.ivLvItemSelectServiceRemove.setVisibility(View.VISIBLE);
            }
            Toast.makeText(context, "相同的项目只能添加一次！", Toast.LENGTH_LONG).show();
        } else {
            if (holder.ivLvItemSelectServiceRemove.getVisibility() == View.VISIBLE) {
                holder.ivLvItemSelectServiceRemove.setVisibility(View.GONE);
            }
            number = 1;
            holder.tvLvItemSelectServiceAdd.setText(number + "");
            serviceItems.get(position).setDiscount(number + "");
        }

        serviceItemAdapter.notifyDataSetChanged();
    }

    /**
     * 减少
     *
     * @param position     第position个子条目
     * @param holder       子条目的布局
     * @param serviceItemAdapter 使用到的数据适配器
     * @param serviceItems       布局所要添加的数据
     */
    private void deleteServiceItem(int position, AddServiceItemAdapter.ViewHolder holder, AddServiceItemAdapter serviceItemAdapter, List<ServiceItem> serviceItems) {
        holder.tvLvItemSelectServiceAdd.setText("+");
        holder.ivLvItemSelectServiceRemove.setVisibility(View.GONE);
        int number = 0;
        serviceItems.get(position).setDiscount(number + "");
        serviceItemAdapter.notifyDataSetChanged();
    }


}
