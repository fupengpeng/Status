package com.fpp.status.activity.three.six.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fpp.status.R;
import com.fpp.status.activity.three.six.entity.Message;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Administrator on 2016/8/15.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.Holder>{
    private ArrayList<Message> list = new ArrayList<>();
    private Context context;

    public RVAdapter(Context context, List<Message> list) {
        this.list = (ArrayList<Message>) list;
        this.context = context;

        Log.e("-ss----", String.valueOf(list));
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

            Log.e("----sssssss---show----------", "");
            View view = LayoutInflater.from(context).inflate(R.layout.all_products_one_lines, parent, false);
            Holder holder = new Holder(view);
            Log.e("-------------------------------------", "++++++++++++++++++++++++++++++++++++");
            return holder;

    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.tvContent.setText(list.get(position).getName());
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public TextView tvContent;
        public TextView tvSalesVolume;
        public TextView tvDelete;
        public LinearLayout llLayout;

        public Holder(View itemView) {
            super(itemView);

            tvContent = (TextView) itemView.findViewById(R.id.all_products_sales_price);
            tvDelete = (TextView) itemView.findViewById(R.id.tv_elete);
            llLayout = (LinearLayout) itemView.findViewById(R.id.llLayout);
            tvSalesVolume = (TextView) itemView.findViewById(R.id.all_products_sales_volume);

        }
    }
}
