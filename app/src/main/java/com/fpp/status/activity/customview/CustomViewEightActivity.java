package com.fpp.status.activity.customview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fpp.status.R;
import com.fpp.status.view.HorizontalListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fupengpeng on 2017/12/6 0006.
 */

public class CustomViewEightActivity extends Activity {

    @BindView(R.id.hlv_layout_tab_service_project)
    HorizontalListView hlvLayoutTabServiceProject;
    private List<Map<String, Object>> data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_eight);
        ButterKnife.bind(this);
        //获取将要绑定的数据设置到data中
        data = getData();
        MyAdapter adapter = new MyAdapter(this);
        hlvLayoutTabServiceProject.setAdapter(adapter);
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        for (int i = 0; i < 10; i++) {
            map = new HashMap<String, Object>();
            map.put("img", R.drawable.shilipic);
            map.put("title", "水疗");
            map.put("info", "2.5");
            list.add(map);
        }
        return list;
    }



    public class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater = null;

        private MyAdapter(Context context) {
            //根据context上下文加载布局，这里的是Demo17Activity本身，即this
            this.mInflater = LayoutInflater.from(context);
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
                convertView = mInflater.inflate(R.layout.list_view_item_select_service, null);
                holder.ivLvItemSelectServiceAdd = (ImageView) convertView.findViewById(R.id.iv_lv_item_select_service_add);
                holder.tvLvItemSelectServiceName = (TextView) convertView.findViewById(R.id.tv_lv_item_select_service_name);
                holder.tvLvItemSelectServicePrice = (TextView) convertView.findViewById(R.id.tv_lv_item_select_service_price);
                //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.ivLvItemSelectServiceAdd.setImageResource((Integer) data.get(position).get("img"));
            holder.tvLvItemSelectServiceName.setText((String) data.get(position).get("title"));
            holder.tvLvItemSelectServicePrice.setText((String) data.get(position).get("info"));

            return convertView;
        }

        //ViewHolder静态类
         class ViewHolder {
            ImageView ivLvItemSelectServiceAdd;
            TextView tvLvItemSelectServiceName;
            TextView tvLvItemSelectServicePrice;
        }
    }
}