package com.fpp.status.activity.fragmentfour;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fpp.status.R;
import com.fpp.status.entity.LVOne;
import com.fpp.status.entity.LVTwo;
import com.fpp.status.view.HorizontalListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ListView嵌套ListView
 */

public class ListViewNestListViewActivity extends AppCompatActivity {

    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.tv_atvt_add_service_or_commodity_service_number)
    TextView tvAtvtAddServiceOrCommodityServiceNumber;
    @BindView(R.id.tv_atvt_add_service_or_commodity_commodity_number)
    TextView tvAtvtAddServiceOrCommodityCommodityNumber;
    @BindView(R.id.tv_atvt_add_service_or_commodity_affirm_add)
    TextView tvAtvtAddServiceOrCommodityAffirmAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lv_nest_lv);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        List<LVOne> lvOnes = getData();

        LVAdapter lvAdapter = new LVAdapter(this,lvOnes);
        lv.setAdapter(lvAdapter);


    }

    /**
     * 设置数据
     *
     * @return
     */
    public List<LVOne> getData() {
        List<LVOne> lvOnes = new ArrayList<LVOne>();
        for (int i = 0; i < 20; i++) {
            LVOne lvOne = new LVOne();
            lvOne.setLvOneId("one-id--" + i);
            lvOne.setLvOneName("one-name--" + i);
            lvOne.setLvOneNum(i + "");
            lvOne.setLvOneSelect(false);

            List<LVTwo> lvTwos = new ArrayList<LVTwo>();
            for (int j = 0; j < 20; j++) {
                LVTwo lvTwo = new LVTwo();
                lvTwo.setLvTwoId("two-id--" + j);
                lvTwo.setLvTwoName("two-name--" + j);
                lvTwo.setLvTwoNum("" + j);
                lvTwo.setLvTwoSelect(false);
                lvTwos.add(lvTwo);
            }
            lvOne.setLvTwos(lvTwos);

            lvOnes.add(lvOne);

        }
        return lvOnes;
    }


    class LVAdapter extends BaseAdapter {
        List<LVOne> lvOneList;
        Context context;
        private LayoutInflater mInflater = null;

        public LVAdapter(Context context, List<LVOne> lvOneList) {
            //根据context上下文加载布局，这里的是Demo17Activity本身，即this
            this.mInflater = LayoutInflater.from(context);
            this.context = context;
            this.lvOneList = lvOneList;
        }

        @Override
        public int getCount() {
            //How many items are in the data set represented by this Adapter.
            //在此适配器中所代表的数据集中的条目数
            return lvOneList.size();
        }

        @Override
        public Object getItem(int position) {
            // Get the data item associated with the specified position in the data set.
            //获取数据集中与指定索引对应的数据项
            return lvOneList.get(position);
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
                convertView = mInflater.inflate(R.layout.list_view_nest_lv_one, null);
                holder.tvLvNestLvOneId = (TextView) convertView.findViewById(R.id.tv_lv_nest_lv_one_id);
                holder.tvLvNestLvOneName = (TextView) convertView.findViewById(R.id.tv_lv_nest_lv_one_name);
                holder.lvLvNestLvOneLvItem = (HorizontalListView) convertView.findViewById(R.id.lv_lv_nest_lv_one_lv_item);
                //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tvLvNestLvOneId.setText(lvOneList.get(position).getLvOneId());
            holder.tvLvNestLvOneName.setText(lvOneList.get(position).getLvOneName());
            final List<LVTwo> lvTwos = lvOneList.get(position).getLvTwos();
            final LVTwoAdapter lvTwoAdapter = new LVTwoAdapter(context,lvTwos);
            holder.lvLvNestLvOneLvItem.setAdapter(lvTwoAdapter);
            //添加服务按钮点击事件
            lvTwoAdapter.setOnItemAddClickListener(new LVTwoAdapter.OnItemAddListener() {
                @Override
                public void onAddClick(int position, LVTwoAdapter.ViewHolder holder) {
                    addLVTwo(position, holder, lvTwoAdapter, lvTwos);


                }
            });
            //减少服务按钮点击事件
            lvTwoAdapter.setOnItemDeleteClickListener(new LVTwoAdapter.OnItemDeleteListener() {
                @Override
                public void onDeleteClick(int position, LVTwoAdapter.ViewHolder holder) {
                    deleteLVTwo(position,holder,lvTwoAdapter,lvTwos);
                    List<LVTwo> lvTwoNumList = new ArrayList<LVTwo>();

                    getLVTwoNumList(lvTwoNumList, lvTwos);

                }
            });

            return convertView;
        }

        /**
         * 统计此分类的选定的数据--筛选选定的项目，统计出来
         * @param lvTwoNumList  筛选过后的数据
         * @param lvTwos  删选数据源
         */
        private void getLVTwoNumList(List<LVTwo> lvTwoNumList, List<LVTwo> lvTwos) {
            lvTwoNumList.clear();
            for ( LVTwo lvTwo : lvTwos ) {
                if (Integer.parseInt(lvTwo.getLvTwoNum()) > 0 ){
                    lvTwoNumList.add(lvTwo);
                }
            }
        }

        /**
         * 添加
         * @param position  第position个子条目
         * @param holder  子条目的布局
         * @param lvTwoAdapter  使用到的数据适配器
         * @param lvTwos  布局所要添加的数据
         */
        private void addLVTwo(int position, LVTwoAdapter.ViewHolder holder, LVTwoAdapter lvTwoAdapter, List<LVTwo> lvTwos) {
            LVTwo item = (LVTwo) lvTwoAdapter.getItem(position);
            int number = Integer.parseInt(item.getLvTwoNum());

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
                lvTwos.get(position).setLvTwoNum(number + "");
            }

            lvTwoAdapter.notifyDataSetChanged();
        }

        /**
         * 减少
         * @param position  第position个子条目
         * @param holder  子条目的布局
         * @param lvTwoAdapter  使用到的数据适配器
         * @param lvTwos  布局所要添加的数据
         */
        private void deleteLVTwo(int position, LVTwoAdapter.ViewHolder holder, LVTwoAdapter lvTwoAdapter, List<LVTwo> lvTwos) {
            holder.tvLvItemSelectServiceAdd.setText("+");
            holder.ivLvItemSelectServiceRemove.setVisibility(View.GONE);
            int number = 0;
            lvTwos.get(position).setLvTwoNum(number + "");
            lvTwoAdapter.notifyDataSetChanged();
        }

        class ViewHolder {
            TextView tvLvNestLvOneId;
            TextView tvLvNestLvOneName;
            HorizontalListView lvLvNestLvOneLvItem;
        }
    }

    static class LVTwoAdapter extends BaseAdapter {
        List<LVTwo> lvTwoList;
        Context context;
        private LayoutInflater mInflater = null;

        public LVTwoAdapter(Context context, List<LVTwo> lvTwoList) {
            //根据context上下文加载布局，这里的是Demo17Activity本身，即this
            this.mInflater = LayoutInflater.from(context);
            this.context = context;
            this.lvTwoList = lvTwoList;
        }

        @Override
        public int getCount() {
            //How many items are in the data set represented by this Adapter.
            //在此适配器中所代表的数据集中的条目数
            return lvTwoList.size();
        }

        @Override
        public Object getItem(int position) {
            // Get the data item associated with the specified position in the data set.
            //获取数据集中与指定索引对应的数据项
            return lvTwoList.get(position);
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
                convertView = mInflater.inflate(R.layout.list_view_nest_lv_two, null);
                holder.tvLvItemSelectServicePrice = (TextView) convertView.findViewById(R.id.tv_lv_item_select_service_price);
                holder.tvLvItemSelectServiceName = (TextView) convertView.findViewById(R.id.tv_lv_item_select_service_name);
                holder.tvLvItemSelectServiceAdd = (TextView) convertView.findViewById(R.id.tv_lv_item_select_service_add);
                holder.ivLvItemSelectServiceRemove = (ImageView) convertView.findViewById(R.id.iv_lv_item_select_service_remove);

                //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tvLvItemSelectServicePrice.setText(lvTwoList.get(position).getLvTwoId());
            holder.tvLvItemSelectServiceName.setText(lvTwoList.get(position).getLvTwoName());

            if (lvTwoList.get(position).getLvTwoNum().equals("0") || lvTwoList.get(position).getLvTwoNum().equals("")  ){
                holder.tvLvItemSelectServiceAdd.setText("+");
                if (holder.ivLvItemSelectServiceRemove.getVisibility() != View.GONE){
                    holder.ivLvItemSelectServiceRemove.setVisibility(View.GONE);
                }
            }else {
                if (holder.ivLvItemSelectServiceRemove.getVisibility() == View.GONE){
                    holder.ivLvItemSelectServiceRemove.setVisibility(View.VISIBLE);
                }
                holder.tvLvItemSelectServiceAdd.setText(lvTwoList.get(position).getLvTwoNum());
            }

            //子条目中增加服务按钮的点击事件设置
            final ViewHolder finalHolder = holder;
            holder.tvLvItemSelectServiceAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemAddListener.onAddClick(position , finalHolder);
                }
            });
            //子条目中减少服务按钮的点击事件设置
            final ViewHolder finalHolder1 = holder;
            holder.ivLvItemSelectServiceRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemDeleteListener.onDeleteClick(position, finalHolder1);
                }
            });

            return convertView;
        }

        static class ViewHolder {
            TextView tvLvItemSelectServiceAdd;
            ImageView ivLvItemSelectServiceRemove;
            TextView tvLvItemSelectServiceName;
            TextView tvLvItemSelectServicePrice;
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
         * @param mOnItemAddListener
         */
        public void setOnItemAddClickListener(OnItemAddListener mOnItemAddListener) {
            this.mOnItemAddListener = mOnItemAddListener;
        }


    }


}
