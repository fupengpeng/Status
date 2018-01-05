package com.fpp.status.activity.customview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fpp.status.R;
import com.fpp.status.entity.LoadOrderDetailsResponseData;
import com.fpp.status.entity.SelectOrderItem;
import com.fpp.status.utils.LogUtils;
import com.fpp.status.utils.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ListView 嵌套 ListView
 * 项目测试用
 */

public class CustomViewEightActivity extends Activity {

    @BindView(R.id.lv_atvt_service_order_particulars_content)
    ListView lvAtvtServiceOrderParticularsContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_eight);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {


        OrderParticularsItemContentAdapter orderParticularsItemContentAdapter = new OrderParticularsItemContentAdapter(this, getData());
        lvAtvtServiceOrderParticularsContent.setAdapter(orderParticularsItemContentAdapter);


    }

    public List<LoadOrderDetailsResponseData> getData() {
        List<LoadOrderDetailsResponseData> loadOrderDetailsResponseDataList = new ArrayList<LoadOrderDetailsResponseData>();
        for (int i = 0; i < 5; i++) {
            LoadOrderDetailsResponseData loadOrderDetailsResponseData = new LoadOrderDetailsResponseData();
            loadOrderDetailsResponseData.setId(i + "");
            loadOrderDetailsResponseData.setShopid("接单人id" + i);
            loadOrderDetailsResponseData.setShopname("接单人名称" + i);
            loadOrderDetailsResponseData.setShopimageurl("接单人头像" + i);
            loadOrderDetailsResponseData.setEnable("是");
            loadOrderDetailsResponseData.setShopgangwei("岗位");

            List<SelectOrderItem> selectOrderItemServiceList = new ArrayList<SelectOrderItem>();
            for (int j = 0; j < 3; j++) {
                SelectOrderItem selectOrderItem = new SelectOrderItem();
                selectOrderItem.setId("s项目id" + j);
                selectOrderItem.setId("s项目名称" + j);
                selectOrderItem.setNum(1);
                selectOrderItem.setPrice(25);
                selectOrderItemServiceList.add(selectOrderItem);
            }
            loadOrderDetailsResponseData.setOpsList(selectOrderItemServiceList);

            List<SelectOrderItem> selectOrderItemGoodsList = new ArrayList<SelectOrderItem>();
            for (int j = 0; j < 3; j++) {
                SelectOrderItem selectOrderItem = new SelectOrderItem();
                selectOrderItem.setId("g项目id" + j);
                selectOrderItem.setId("g项目名称" + j);
                selectOrderItem.setNum(3);
                selectOrderItem.setPrice(12);
                selectOrderItemGoodsList.add(selectOrderItem);
            }
            loadOrderDetailsResponseData.setOpgList(selectOrderItemGoodsList);

            loadOrderDetailsResponseDataList.add(loadOrderDetailsResponseData);

        }

        return loadOrderDetailsResponseDataList;

    }


    class OrderParticularsItemContentAdapter extends BaseAdapter {
        boolean isShow = false;
        List<LoadOrderDetailsResponseData> shopAssistantCategoryList;
        Context context;
        private LayoutInflater mInflater = null;

        public OrderParticularsItemContentAdapter(Context context, List<LoadOrderDetailsResponseData> shopAssistantCategoryList) {
            //根据context上下文加载布局，这里的是Demo17Activity本身，即this
            this.mInflater = LayoutInflater.from(context);
            this.context = context;
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
            final ViewHolder holder;
            //如果缓存convertView为空，则需要创建View
            if (convertView == null) {
                holder = new ViewHolder();
                //根据自定义的Item布局加载布局
                convertView = mInflater.inflate(R.layout.list_view_item_op_item_content, null);

                holder.ivLvItemOpItemContentUserPic = (ImageView) convertView.findViewById(R.id.iv_lv_item_op_item_content_user_pic);
                holder.tvLvItemOpItemContentUserName = (TextView) convertView.findViewById(R.id.tv_lv_item_op_item_content_user_name);
                holder.tvLvItemOpItemContentUserLevel = (TextView) convertView.findViewById(R.id.tv_lv_item_op_item_content_user_level);
                holder.ivLvItemOpItemContent = (ImageView) convertView.findViewById(R.id.iv_lv_item_op_item_content);
                holder.llLvItemOpItemContentShowServiceItem = (LinearLayout) convertView.findViewById(R.id.ll_lv_item_op_item_content_show_service_item);
                holder.lvLvItemOpItemContent = (ListView) convertView.findViewById(R.id.lv_lv_item_op_item_content);

                //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.ivLvItemOpItemContentUserPic.setImageResource(R.drawable.ic_account_circle_black_24dp);
            holder.tvLvItemOpItemContentUserName.setText(shopAssistantCategoryList.get(position).getShopname());
            holder.tvLvItemOpItemContentUserLevel.setText(shopAssistantCategoryList.get(position).getShopgangwei());

            final List<SelectOrderItem> selectOrderItemServiceList = shopAssistantCategoryList.get(position).getOpsList();
            final List<SelectOrderItem> selectOrderItemGoodsList = shopAssistantCategoryList.get(position).getOpgList();

            //订单添加的品项数据展示（合并两个集合中的数据后传递给适配器）
            final List<SelectOrderItem> allSelectOrderItemList = new ArrayList<SelectOrderItem>();
            if (selectOrderItemServiceList != null) {
                LogUtils.e("---------------------" + selectOrderItemServiceList.size());
                for (SelectOrderItem selectOrderItem : selectOrderItemServiceList) {
                    selectOrderItem.setPtype(2);
                    allSelectOrderItemList.add(selectOrderItem);
                }
            }
            if (selectOrderItemGoodsList != null) {
                LogUtils.e("---------------------" + selectOrderItemGoodsList.size());
                for (SelectOrderItem selectOrderItem : selectOrderItemGoodsList) {
                    selectOrderItem.setPtype(1);
                    allSelectOrderItemList.add(selectOrderItem);
                }
            }

            final OrderParticularsItemAdapter orderParticularsItemAdapter = new OrderParticularsItemAdapter(context, allSelectOrderItemList);
            holder.lvLvItemOpItemContent.setAdapter(orderParticularsItemAdapter);
            Utility.setListViewHeightBasedOnChildren(holder.lvLvItemOpItemContent);


            holder.llLvItemOpItemContentShowServiceItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isShow){
                        holder.lvLvItemOpItemContent.setVisibility(View.GONE);
                        isShow = false;
                    }else {
                        holder.lvLvItemOpItemContent.setVisibility(View.VISIBLE);
                        isShow = true;
                    }
                }
            });


            return convertView;
        }

        class ViewHolder {
            /**
             * 接单人头像
             */
            ImageView ivLvItemOpItemContentUserPic;
            /**
             * 接单人名称
             */
            TextView tvLvItemOpItemContentUserName;
            /**
             * 接单人岗位名称
             */
            TextView tvLvItemOpItemContentUserLevel;
            /**
             * 服务项和商品项展示提示
             */
            ImageView ivLvItemOpItemContent;
            /**
             * 服务项和商品项点击事件
             */
            LinearLayout llLvItemOpItemContentShowServiceItem;
            /**
             * 商品项和服务项
             */
            ListView lvLvItemOpItemContent;

        }
    }


    public class OrderParticularsItemAdapter extends com.fpp.status.adapter.BaseAdapter<SelectOrderItem> {

        public OrderParticularsItemAdapter(Context context, List<SelectOrderItem> data) {
            super(context, data);
        }

        //获取一个在数据集中指定索引的视图来显示数据
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            //如果缓存convertView为空，则需要创建View
            if (convertView == null) {
                //根据自定义的Item布局加载布局
                convertView = getLayoutInflater().inflate(R.layout.list_view_item_op_item, null);
                holder = new ViewHolder(convertView);
                //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            //设置数据
            SelectOrderItem item = getItem(position);

            if (item.getPtype() == 1) {  // 商品
                holder.tvLvItemOpItemName.setText(item.getGoodsname());
            } else {  // 服务条目
                holder.tvLvItemOpItemName.setText(item.getServiceitemname());
            }

            holder.tvLvItemOpItemNumber.setText(item.getNum() + "");
            holder.tvLvItemOpItemPrice.setText((int) item.getPrice() + "");
            holder.btnLvItemOpItemRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 2017/12/28 0028  点击删除
                }
            });
            return convertView;
        }

        class ViewHolder {
            @BindView(R.id.btn_lv_item_op_item_remove)
            Button btnLvItemOpItemRemove;
            @BindView(R.id.tv_lv_item_op_item_name)
            TextView tvLvItemOpItemName;
            @BindView(R.id.tv_lv_item_op_item_number)
            TextView tvLvItemOpItemNumber;
            @BindView(R.id.tv_lv_item_op_item_price)
            TextView tvLvItemOpItemPrice;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }


}