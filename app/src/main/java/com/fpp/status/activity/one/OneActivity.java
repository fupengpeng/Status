package com.fpp.status.activity.one;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fpp.status.R;
import com.fpp.status.utils.ByteUtil;
import com.fpp.status.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OneActivity extends AppCompatActivity {

    @BindView(R.id.rv_aty_added_value_query)
    RecyclerView rvAtyAddedValueQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        ButterKnife.bind(this);

        initData();

        initAdapter();


    }

    private void initAdapter() {
        List<OrderInfo> orderInfoList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setAmount(i * 100);
            orderInfo.setSelect(i == 2);
            orderInfo.setOrderDate("20201109174001");
            orderInfo.setPayType("1");
            orderInfoList.add(orderInfo);
        }

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvAtyAddedValueQuery.setLayoutManager(llm);

        OrderAdapter orderAdapter = new OrderAdapter(this, orderInfoList);
        rvAtyAddedValueQuery.setAdapter(orderAdapter);
        orderAdapter.setOnCheckBoxClickListener((OrderAdapter.OnCheckBoxClickListener) () -> {
            LogUtil.e("enable:" + ((orderAdapter.getCheckedItem() != null) ? "check?  " + orderAdapter.getCheckedItem().toString() : " orderInfo is null"));
        });

    }

    private void initData() {

        List<OrderInfo> orderInfoList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setAmount(i * 100);
            orderInfo.setSelect(i == 2);
            orderInfo.setOrderDate("20201109174001");
            orderInfo.setPayType("1");
            orderInfoList.add(orderInfo);
        }

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvAtyAddedValueQuery.setLayoutManager(llm);

        final OrderRvAdapter orderRvAdapter = new OrderRvAdapter(this, orderInfoList);
        orderRvAdapter.setOnCheckBoxClickListener((checkBox, p) -> {
            for (int i = 0; i < orderInfoList.size(); i++) {
                if (orderInfoList.get(i).isSelect()) {
                    orderInfoList.get(i).setSelect(false);
                }
            }
            if (checkBox.isChecked()) {
                LogUtil.e("CheckBox  " + orderInfoList.get(p).toString());
            } else {
                LogUtil.e("CheckBox  orderInfo is null");
            }
            orderInfoList.get(p).setSelect(checkBox.isChecked());
            orderRvAdapter.notifyDataSetChanged();
        });
        orderRvAdapter.setOnItemClickListener((itemView, position) -> {
            for (int i = 0; i < orderInfoList.size(); i++) {
                orderInfoList.get(i).setSelect(false);
            }
            CheckBox cb = itemView.findViewById(R.id.cb_item_order);
            orderInfoList.get(position).setSelect(!cb.isChecked());
            if (cb.isChecked()) {
                LogUtil.e("Item  orderInfo is null");
            } else {
                LogUtil.e("Item  " + orderInfoList.get(position).toString());
            }
            orderRvAdapter.notifyDataSetChanged();
        });
        rvAtyAddedValueQuery.setAdapter(orderRvAdapter);


    }


    static class OrderRvAdapter extends RecyclerView.Adapter<OrderRvAdapter.ViewHolder> {

        private final String TAG = OrderRvAdapter.class.getSimpleName();

        private Context mContext;

        private List<OrderInfo> mList = new ArrayList<>();
        /**
         * Item点击监听
         */
        private OnItemClickListener mItemOnClickListener;

        interface OnItemClickListener {
            void onItemClick(View itemView, int position);
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mItemOnClickListener = onItemClickListener;
        }


        private OnCheckBoxClickListener onCheckBoxClickListener;

        interface OnCheckBoxClickListener {
            void onCheckBoxClick(CheckBox checkBox, int p);
        }

        public void setOnCheckBoxClickListener(OnCheckBoxClickListener onCheckBoxClickListener) {
            this.onCheckBoxClickListener = onCheckBoxClickListener;
        }


        public OrderRvAdapter(Context context, List<OrderInfo> list) {
            mContext = context;
            mList = list;
        }

        public void setDataList(List<OrderInfo> list) {
            Log.d(TAG, "setDataList: " + list.size());
            mList = list;
            notifyDataSetChanged();
        }

        public void setItemDataList(int position) {
            Log.d(TAG, "setItemDataList: " + position);
            for (int i = 0; i < mList.size(); i++) {
                mList.get(i).setSelect(false);
            }
            mList.get(position).setSelect(true);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_order, parent, false);
            return new ViewHolder(itemView);
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
            holder.tvItemOrderAmount.setText(ByteUtil.toAmountString(mList.get(position).getAmount() / 100f));
            holder.tvItemOrderDate.setText(mList.get(position).getOrderDate().substring(0, 8));
            holder.tvItemOrderTime.setText(mList.get(position).getOrderDate().substring(8, 14));
            String payType = "未知";
            if ("1".equals(mList.get(position).getPayType())) {
                payType = "支付宝";
            } else if ("2".equals(mList.get(position).getPayType())) {
                payType = "微信";
            } else if ("6".equals(mList.get(position).getPayType())) {
                payType = "现金";
            }
            holder.tvItemOrderType.setText(payType);

            if (Boolean.valueOf(mList.get(position).isSelect())) {
                holder.cbItemOrder.setChecked(true);
            } else {
                holder.cbItemOrder.setChecked(false);
            }
            if (position % 2 == 0) {
                holder.llItemOrderContent.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            } else {
                holder.llItemOrderContent.setBackgroundColor(mContext.getResources().getColor(R.color.blue));
            }


            holder.cbItemOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCheckBoxClickListener.onCheckBoxClick(holder.cbItemOrder, position);
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemOnClickListener.onItemClick(v, position);
                }
            });

        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size();
        }


        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.cb_item_order)
            CheckBox cbItemOrder;
            @BindView(R.id.tv_item_order_date)
            TextView tvItemOrderDate;
            @BindView(R.id.tv_item_order_time)
            TextView tvItemOrderTime;
            @BindView(R.id.tv_item_order_type)
            TextView tvItemOrderType;
            @BindView(R.id.tv_item_order_amount)
            TextView tvItemOrderAmount;
            @BindView(R.id.ll_item_order_content)
            LinearLayout llItemOrderContent;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }


    public static class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

        final String TAG = OrderAdapter.class.getSimpleName();

        private Context context;
        private List<OrderInfo> list;
        private OnCheckBoxClickListener onCheckBoxClickListener;
        private OrderInfo orderInfo;

        interface OnCheckBoxClickListener {
            void onCheckBoxClick();
        }

        public void setOnCheckBoxClickListener(OnCheckBoxClickListener onCheckBoxClickListener) {
            this.onCheckBoxClickListener = onCheckBoxClickListener;
        }

        public void setCheckItem(int position) {
            for (int i = 0; i < list.size(); i++) {
                if (i != position) {
                    list.get(i).setSelect(false);
                }
            }
            orderInfo = list.get(position);
            orderInfo.setSelect(!orderInfo.isSelect());
            if (!orderInfo.isSelect()) {
                orderInfo = null;
            }
            notifyDataSetChanged();
        }

        public OrderInfo getCheckedItem() {
            return orderInfo;
        }

        public OrderAdapter(Context context, List<OrderInfo> list) {
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
            return new OrderAdapter.ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
            viewHolder.tvItemOrderAmount.setText(ByteUtil.toAmountString(list.get(position).getAmount() / 100f));
            viewHolder.tvItemOrderDate.setText(list.get(position).getOrderDate().substring(0, 10));
            viewHolder.tvItemOrderTime.setText(list.get(position).getOrderDate().substring(11));
            viewHolder.tvItemOrderType.setText("1".equals(list.get(position).getPayType()) ? "现金" : "扫码");
            if (list.get(position).isSelect()) {
                viewHolder.cbItemOrder.setVisibility(View.VISIBLE);
            } else {
                viewHolder.cbItemOrder.setVisibility(View.INVISIBLE);
            }
            LogUtil.e(position + " is : " + list.get(position).isSelect());
            viewHolder.llItemOrderContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtil.e(position + " is : " + position);
                    setCheckItem(position);
                    onCheckBoxClickListener.onCheckBoxClick();
                }
            });
            if (position % 2 == 0) {
                viewHolder.llItemOrderContent.setBackgroundColor(context.getResources().getColor(R.color.blue));
            } else {
                viewHolder.llItemOrderContent.setBackgroundColor(context.getResources().getColor(R.color.white));
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.cb_item_order)
            CheckBox cbItemOrder;
            @BindView(R.id.tv_item_order_date)
            TextView tvItemOrderDate;
            @BindView(R.id.tv_item_order_time)
            TextView tvItemOrderTime;
            @BindView(R.id.tv_item_order_type)
            TextView tvItemOrderType;
            @BindView(R.id.tv_item_order_amount)
            TextView tvItemOrderAmount;
            @BindView(R.id.ll_item_order_content)
            LinearLayout llItemOrderContent;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }


}
