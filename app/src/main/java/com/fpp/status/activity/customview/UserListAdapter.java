package com.fpp.status.activity.customview;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fpp.status.R;
import com.fpp.status.entity.User;
import com.fpp.status.utils.LogUtils;

import java.util.List;

/**
 * @author fupengpeng
 * @description 店员列表适配器
 * @date 2018/3/7 0007 14:52
 */
public class UserListAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {

    LayoutInflater inflater;

    // 布局一
    final int TYPE_1 = 0;
    // 布局二
    final int TYPE_2 = 1;
    // 数据
    private List<User> mList;
    private CustomViewSixActivity userListActivity;

    public UserListAdapter(CustomViewSixActivity context, List<User> list) {
        this.userListActivity = context;
        this.mList = list;
    }

    /**
     * 子条目布局样式数量
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    /**
     * 设置那些个条目使用那些布局：根据position确定
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        // mList.size() - 1 position是从0开始计，而mList.size()是从1开始计
        if (position != mList.size() - 1) {  // 设置最后一条数据使用布局二，其它数据使用布局一
            return TYPE_1;
        } else {
            return TYPE_2;
        }
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    ViewHolder1 holder1 = null;
    ViewHolder2 holder2 = null;
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        if (convertView == null) {
            inflater = LayoutInflater.from(userListActivity);
            // 根据布局类型设置对应的布局
            switch (type) {
                case TYPE_1:
                    convertView = inflater.inflate(R.layout.list_view_item_user_list, parent, false);
                    holder1 = new ViewHolder1();
                    holder1.mTvLvItemBapProcessUserOne = (TextView) convertView.findViewById(R.id.tv_lv_item_select_worker_user_name);
                    holder1.cbLvItemSelectWorkerIs = (CheckBox) convertView.findViewById(R.id.cb_lv_item_select_worker_is);

                    convertView.setTag(holder1);
                    break;
                case TYPE_2:
                    convertView = inflater.inflate(R.layout.list_view_item_user_list_empty, parent, false);
                    holder2 = new ViewHolder2();
                    holder2.mTvLvItemBapProcessUserTwo = (LinearLayout) convertView.findViewById(R.id.ll_lv_item_select_worker_content);

                    convertView.setTag(holder2);
                    break;
            }

        } else {
            switch (type) {
                case TYPE_1:
                    holder1 = (ViewHolder1) convertView.getTag();
                    break;
                case TYPE_2:
                    holder2 = (ViewHolder2) convertView.getTag();
                    break;
            }
        }
        // 获取数据
        final User item = mList.get(position);
        // 根据布局样式设置数据
        switch (type) {
            case TYPE_1:
                holder1.mTvLvItemBapProcessUserOne.setText(
                        mList.get(position).getName() + "  --  typeOne  " + (position + 1));
                bindListItem(holder1, item);
                break;
            case TYPE_2:

                bindListItem(holder2, item);
                break;
        }

        // 子条目选定的判断以及选定的点击事件设置
        if (item != null) {
            // 判断是否选择
            if (item.isEnable()) {
                holder1.cbLvItemSelectWorkerIs.setChecked(true);
            } else {
                holder1.cbLvItemSelectWorkerIs.setChecked(false);
            }
            // 子条目中checkbox的点击事件监听
            holder1.cbLvItemSelectWorkerIs.setOnClickListener(new CheckBoxOnClick(item));
        }
        return convertView;
    }


    /**
     * checkbox 点击事件监听
     */
    class CheckBoxOnClick implements View.OnClickListener {

        User shopcartEntity;

        public CheckBoxOnClick(User shopcartEntity) {
            this.shopcartEntity = shopcartEntity;
        }

        @Override
        public void onClick(View view) {
            LogUtils.e("pw---", "onClick: " + "----0011----");
            CheckBox cb = (CheckBox) view;
            // 判断当前checkbox点击以后的状态，改变此条目数据的是否为选定状态
            if (cb.isChecked()) {
                LogUtils.e("pw---", "onClick: " + "----0011----true");
                shopcartEntity.setEnable(true);
            } else {
                LogUtils.e("pw---", "onClick: " + "----0011----false");
                shopcartEntity.setEnable(false);
            }

            // 改变此条目是否为选定状态后，进行一次选定状态的统计。
            // 判断所有的数据状态是否为选中，是的话，则设置全选为true
            //                             不是的话，则设置全选为false
            select();

        }

    }

    /**
     * 条目内数据设置
     *
     * @param holder
     * @param data
     */
    private void bindListItem(ViewHolder1 holder, User data) {
        LogUtils.e("pw---", "bindListItem: " + "----0016----");
//        holder.tvLvItemPwSelectName.setText(data.getServiceitemname());
//        holder.tvLvItemPwSelectPrice.setText("￥" + data.getPrice() + ".00");

        int _id = Integer.parseInt(data.getId());
        boolean selected = userListActivity.getmSelectState().get(_id, false);
        holder1.cbLvItemSelectWorkerIs.setChecked(selected);
    }

    private void bindListItem(ViewHolder2 holder, User data) {
        LogUtils.e("pw---", "bindListItem: " + "----001000----");
//        holder.tvLvItemPwSelectName.setText(data.getServiceitemname());
//        holder.tvLvItemPwSelectPrice.setText("￥" + data.getPrice() + ".00");

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
        if (position != mList.size()) {
            // 获取到点击条目数据
            User bean = mList.get(position);

            ViewHolder1 holder = (ViewHolder1) view.getTag();
            int _id = Integer.parseInt(bean.getId());

            boolean selected = !userListActivity.getmSelectState().get(_id, false);
            holder.cbLvItemSelectWorkerIs.toggle();

            LogUtils.e("pw---", "onItemClick: " + "----0017----");
            // 将CheckBox的选中状况记录下来
            mList.get(position).setEnable(holder.cbLvItemSelectWorkerIs.isChecked());
            // 调整选定条目
            if (holder.cbLvItemSelectWorkerIs.isChecked() == true) {

            } else {
                userListActivity.getmSelectState().delete(position);
            }
            if (userListActivity.getmSelectState().size() == mList.size()) {
                userListActivity.getCbAtvtSelectWorker().setChecked(true);
            } else {
                userListActivity.getCbAtvtSelectWorker().setChecked(false);
            }
        }
    }


    /**
     * 子条目点击选定
     */
    public void select() {
        // 清除idList中的pid
        userListActivity.getIdList().clear();
        // 遍历数据中选定的数据条目，统计并将其pid添加到idList集合中
        int count = 0;
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).isEnable()) {
                userListActivity.getIdList().add(mList.get(i).getId());
                count++;
            }
        }
        LogUtils.e("子条目点击选定，获取到选定的条目pid = " + userListActivity.getIdList().size());
        // 如果选定的条目数量和数据条目总数相同，则设置全选按钮选定
        // 如果不相同，则设置全选按钮不选定。
        // mList.size() - 1 子项点击统计时，不统计最后一条数据，最后一条数据只是为了占空间而已。
        if (count == mList.size() - 1) {
            userListActivity.getCbAtvtSelectWorker().setChecked(true);
        } else {
            userListActivity.getCbAtvtSelectWorker().setChecked(false);
        }
    }

    /**
     * 静态类viewholder
     */
    public static class ViewHolder1 {
        TextView mTvLvItemBapProcessUserOne;
        CheckBox cbLvItemSelectWorkerIs;
    }

    public static class ViewHolder2 {
        LinearLayout mTvLvItemBapProcessUserTwo;
    }
}
