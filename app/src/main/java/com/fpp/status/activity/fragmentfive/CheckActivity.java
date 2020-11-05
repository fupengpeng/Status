package com.fpp.status.activity.fragmentfive;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fpp.status.R;
import com.fpp.status.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description: 抽取CheckBoxAdapter
 * Author: fpp
 * Date: 2018/9/29  9:49
 */

public class CheckActivity extends AppCompatActivity {


    /**
     * 购物车商品列表
     */
    @BindView(R.id.lv_fragment_shopping_cart)
    ListView lvFragmentShoppingCart;

    @BindView(R.id.rl_fragment_shopping_cart_price_total)
    RelativeLayout rlFragmentShoppingCartPriceTotal;

    private CheckListAdapter mCheckListAdapter;// adapter

    private List<DataBean> mListDataBean = new ArrayList<DataBean>();// 数据


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_select);
        ButterKnife.bind(this);

        rlFragmentShoppingCartPriceTotal.setVisibility(View.GONE);
        mListDataBean = getData();
        LogUtil.e("refreshListView: " + "----0004----");
        if (mCheckListAdapter == null) {
            mCheckListAdapter = new CheckListAdapter(CheckActivity.this, mListDataBean);
            lvFragmentShoppingCart.setAdapter(mCheckListAdapter);
        } else {
            mCheckListAdapter.notifyDataSetChanged();
        }

    }


    /**
     * 获取商品数据
     */
    private List<DataBean> getData() {
        LogUtil.e("getData: " + "----00005----");
        List<DataBean> result = new ArrayList<DataBean>();
        DataBean data = null;
        for (int i = 1; i <= 100; i++) {
            data = new DataBean();
            data.setId(i);// 从最大Id的下一个开始
            data.setShopName("我的" + i + "店铺");
            data.setContent("我的购物车里面的第" + i + "个商品");
            data.setCarNum(1);
            data.setPrice(305.00);
            if (i == 2 || i == 10 || i == 40) {
                data.setSelect(true);
            } else {
                data.setSelect(false);
            }

            result.add(data);
        }
        return result;
    }


    /*----------  编辑按钮事件处理开始  ----------*/


    /*----------  编辑按钮事件处理结束  ----------*/

    public abstract class ListAdapter<T extends CheckBean> extends BaseAdapter {

        public List<T> list;
        public List<T> mSelectList = new ArrayList<>();
        public CheckBox mCheckBox;
        public Context mContext;
        ViewHolder holder = null;
        public ListAdapter(Context context,List<T> list) {
            this.list = list;
            this.mContext = context;
        }

        @Override
        public int getCount() {
            return list.size() > 0 ? list.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        @SuppressLint("ResourceType")
        @Override
        public  View getView(int position, View convertView, ViewGroup parent){

            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(getConvertView(), null);
                holder = new ViewHolder();

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            setData();



            // 选中操作
            holder.checkBox.setOnClickListener(new CheckBoxOnClick(position));

            return convertView;
        }

        public abstract int getConvertView();
        public abstract void setData();
        class CheckBoxOnClick implements View.OnClickListener {
            int position;

            public CheckBoxOnClick(int position) {
                this.position = position;
            }

            @Override
            public void onClick(View view) {
                LogUtil.e("onClick: " + "----0011----");
                CheckBox cb = (CheckBox) view;
                if (cb.isChecked()) {
                    list.get(position).setSelect(true);
                } else {
                    list.get(position).setSelect(false);
                }
                mSelectList.clear();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isSelect()) {
                        mSelectList.add(list.get(i));
                    }
                }
                if (mSelectList != null && mSelectList.size() > 0) {
                    LogUtil.e("选择统计  = " + mSelectList.size());
                }


            }

        }

        public class ViewHolder{
            public CheckBox checkBox;

            public ViewHolder(){}


        }
    }



    class CBListAdapter extends ListAdapter<DataBean> {


        public CBListAdapter(Context context, List<DataBean> list) {
            super(context, list);
        }

        @Override
        public int getConvertView() {
            return R.layout.item_fragment_shopping_cart_commodity_list;
        }

        @Override
        public void setData() {

        }

        class ViewHolder extends ListAdapter.ViewHolder{

            @BindView(R.id.tv_item_fragment_shopping_cart_commodity_list_name)
            TextView tvItemFragmentShoppingCartCommodityListName;
            @BindView(R.id.tv_item_fragment_shopping_cart_commodity_list_price)
            TextView tvItemFragmentShoppingCartCommodityListPrice;


            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }


        }
    }


  /**
     * 适配器
     */
    class CheckListAdapter extends BaseAdapter {
        private Context context;
        private List<DataBean> mListData;

        public CheckListAdapter(Context context, List<DataBean> mListData) {

            this.context = context;
            this.mListData = mListData;
        }


        @Override
        public int getCount() {
            return mListData.size();
        }

        @Override
        public Object getItem(int position) {
            return mListData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        ViewHolder holder = null;

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_fragment_shopping_cart_commodity_list, null);
                holder = new ViewHolder(convertView);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.llAddDelete.setVisibility(View.GONE);
            setCheckData(position);
            return convertView;
        }

        private void setCheckData(final int position) {
            holder.tvItemFragmentShoppingCartCommodityListName.setText(mListData.get(position).getContent());
            holder.tvItemFragmentShoppingCartCommodityListPrice.setText("￥" + mListData.get(position).getPrice());

            // 判断是否选择
            if (mListData.get(position).isSelect()) {
                holder.cbItemFragmentShoppingCartCommodityListSelect.setChecked(true);
            } else {
                holder.cbItemFragmentShoppingCartCommodityListSelect.setChecked(false);
            }

            // 选中操作
            holder.cbItemFragmentShoppingCartCommodityListSelect.setOnClickListener(new CheckBoxOnClick(position));

        }

        class CheckBoxOnClick implements View.OnClickListener {
            int position;

            public CheckBoxOnClick(int position) {
                this.position = position;
            }

            @Override
            public void onClick(View view) {
                LogUtil.e("onClick: " + "----0011----");
                CheckBox cb = (CheckBox) view;
                if (cb.isChecked()) {
                    mListData.get(position).setSelect(true);
                } else {
                    mListData.get(position).setSelect(false);
                }
                List<DataBean> abs = new ArrayList<>();
                abs.clear();
                for (int i = 0; i < mListData.size(); i++) {
                    if (mListData.get(i).isSelect()) {
                        abs.add(mListData.get(i));
                    }
                }
                if (abs != null && abs.size() > 0) {
                    LogUtil.e("选择统计  = " + abs.size());
                }


            }

        }

        class ViewHolder {
            @BindView(R.id.cb_item_fragment_shopping_cart_commodity_list_select)
            CheckBox cbItemFragmentShoppingCartCommodityListSelect;
            @BindView(R.id.tv_item_fragment_shopping_cart_commodity_list_name)
            TextView tvItemFragmentShoppingCartCommodityListName;
            @BindView(R.id.tv_item_fragment_shopping_cart_commodity_list_price)
            TextView tvItemFragmentShoppingCartCommodityListPrice;
            @BindView(R.id.ll_add_delete)
            LinearLayout llAddDelete;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }

        }

    }


}
