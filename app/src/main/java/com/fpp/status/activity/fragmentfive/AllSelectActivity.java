package com.fpp.status.activity.fragmentfive;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.fpp.status.R;
import com.fpp.status.entity.ServiceItem;
import com.fpp.status.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 全选逻辑分析
 */

public class AllSelectActivity extends AppCompatActivity {

    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.cb_lv_item_pw_select_item)
    CheckBox cbLvItemPwSelectItem;
    @BindView(R.id.tv_atvt_add_service_or_commodity_service_number)
    TextView tvAtvtAddServiceOrCommodityServiceNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_select);
        ButterKnife.bind(this);

        initView();
        mListData = getData();
        refreshListView();

    }

    PwAdapter pwAdapter;
    List<ServiceItem> data;
    List<ServiceItem> mListData;
    private void initView() {
        data = getData();
        pwAdapter = new PwAdapter(this, data);
        listview.setAdapter(pwAdapter);
    }

    @OnClick(R.id.cb_lv_item_pw_select_item)
    public void onViewClicked() {
        if (cbLvItemPwSelectItem.isChecked()) {
            for (int i = 0; i < mListData.size(); i++) {
                mListData.get(i).setEnable(true);
            }
            // 刷新
            pwAdapter.notifyDataSetChanged();
        } else {
            for (int i = 0; i < mListData.size(); i++) {
                mListData.get(i).setEnable(false);
                // 刷新
                pwAdapter.notifyDataSetChanged();
            }
        }

    }


    /**
     * listview数据准备
     *
     * @return
     */
    public List<ServiceItem> getData() {
        List<ServiceItem> data = new ArrayList<ServiceItem>();
        for (int i = 1; i < 50; i++) {
            ServiceItem serviceItem = new ServiceItem();
            serviceItem.setServiceItemID("100" + i);
            serviceItem.setServiceItemName("服务名" + i);
            serviceItem.setDiscount("" + i);
            serviceItem.setSalePrice("" + i);
            serviceItem.setEnable(false);
            data.add(serviceItem);
        }
        return data;
    }

    /**
     * 更新popupwindow列表页面数据
     */
    private void refreshListView() {
        if (pwAdapter == null) {
            pwAdapter = new PwAdapter(this, mListData);
            listview.setAdapter(pwAdapter);
            listview.setOnItemClickListener(pwAdapter);
        } else {
            pwAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 子条目点击选定最后一个checkbox时，全选checkbox的变化
     */
    public void select() {
        int count = 0;
        for (int i = 0; i < mListData.size(); i++) {
            if (mListData.get(i).isEnable()) {
                count++;
            }
        }
        if (count == mListData.size()) {
            cbLvItemPwSelectItem.setChecked(true);
        } else {
            isSelect = true;
            cbLvItemPwSelectItem.setChecked(false);
        }

    }



    /**
     * 批量模式下，用来记录当前选中状态
     */
    private SparseArray<Boolean> mSelectState = new SparseArray<Boolean>();
    boolean isSelect = false;
    /**
     * 适配器
     */
    class PwAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {

        private LayoutInflater mInflater = null;
        private Context context;
        private List<ServiceItem> mListData;

        public PwAdapter(Context context, List<ServiceItem> mListData) {
            //根据context上下文加载布局，这里的是Activity本身，即this
            this.mInflater = LayoutInflater.from(context);
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
                convertView = getLayoutInflater().inflate(R.layout.list_view_item_all_select, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final ServiceItem item = mListData.get(position);
            bindListItem(holder, item);


            if (item != null) {
                // 判断是否选择
                if (item.isEnable()) {
                    holder.cbLvItemPwSelectItem.setChecked(true);
                } else {
                    holder.cbLvItemPwSelectItem.setChecked(false);
                }
                // 选中操作
                holder.cbLvItemPwSelectItem.setOnClickListener(new CheckBoxOnClick(item));

            }
            return convertView;
        }

        /**
         *  子条目checkbox 点击事件监听
         */
        class CheckBoxOnClick implements View.OnClickListener {
            ServiceItem shopcartEntity;

            public CheckBoxOnClick(ServiceItem shopcartEntity) {
                this.shopcartEntity = shopcartEntity;
            }

            @Override
            public void onClick(View view) {
                LogUtils.e("pw---", "onClick: " + "----0011----");
                CheckBox cb = (CheckBox) view;
                if (cb.isChecked()) {
                    shopcartEntity.setEnable(true);
                } else {
                    shopcartEntity.setEnable(false);
                }

                select();

            }

        }



        /**
         * 条目内数据设置
         *
         * @param holder
         * @param data
         */
        private void bindListItem(ViewHolder holder, ServiceItem data) {
            LogUtils.e("pw---", "bindListItem: " + "----0016----");
            holder.tvLvItemPwSelectName.setText(data.getServiceItemName());
            holder.tvLvItemPwSelectPrice.setText("￥" + data.getSalePrice() + ".00");
            holder.tvLvItemPwSelectNumber.setText("×" + data.getDiscount());

            int _id = Integer.parseInt(data.getServiceItemID());
            boolean selected = mSelectState.get(_id, false);
            holder.cbLvItemPwSelectItem.setChecked(selected);

        }

        /**
         * 子条目点击事件
         *
         * @param parent
         * @param view
         * @param position
         * @param id
         */
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            ServiceItem bean = mListData.get(position);

            ViewHolder holder = (ViewHolder) view.getTag();
            int _id = Integer.parseInt(bean.getServiceItemID());

            boolean selected = !mSelectState.get(_id, false);
            holder.cbLvItemPwSelectItem.toggle();

            LogUtils.e("pw---", "onItemClick: " + "----0017----");
            // 将CheckBox的选中状况记录下来
            mListData.get(position).setEnable(holder.cbLvItemPwSelectItem.isChecked());
            // 调整选定条目
            if (holder.cbLvItemPwSelectItem.isChecked() == true) {

            } else {
                mSelectState.delete(position);
            }

            if (mSelectState.size() == mListData.size()) {
                cbLvItemPwSelectItem.setChecked(true);
            } else {
                cbLvItemPwSelectItem.setChecked(false);
            }

        }


        class ViewHolder {
            @BindView(R.id.cb_lv_item_pw_select_item)
            CheckBox cbLvItemPwSelectItem;
            @BindView(R.id.tv_lv_item_pw_select_name)
            TextView tvLvItemPwSelectName;
            @BindView(R.id.tv_lv_item_pw_select_number)
            TextView tvLvItemPwSelectNumber;
            @BindView(R.id.tv_lv_item_pw_select_price)
            TextView tvLvItemPwSelectPrice;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }




}
