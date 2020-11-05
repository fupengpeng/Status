package com.fpp.status.activity.customview;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.fpp.status.R;
import com.fpp.status.entity.User;
import com.fpp.status.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by fupengpeng on 2017/12/6 0006.
 */

public class CustomViewSixActivity extends Activity {


    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_atvt_user_list_state)
    TextView tvAtvtUserListState;
    @BindView(R.id.et_atvt_user_list_job_number_name)
    EditText etAtvtUserListJobNumberName;
    @BindView(R.id.tv_atvt_user_list_search)
    TextView tvAtvtUserListSearch;
    @BindView(R.id.lv_atvt_user_list)
    ListView lvAtvtUserList;
    @BindView(R.id.tv_atvt_user_list_update)
    TextView tvAtvtUserListUpdate;
    @BindView(R.id.cb_atvt_select_worker)
    CheckBox cbAtvtSelectWorker;
    @BindView(R.id.tv_atvt_user_list_delete)
    TextView tvAtvtUserListDelete;



    /**
     * listview适配器
     */
    private UserListAdapter mUserListAdapter;
    /**
     * 批量模式下，用来记录当前选中状态
     */
    private SparseArray<Boolean> mSelectState = new SparseArray<Boolean>();

    /**
     * 适配器添加数据（用户服务项）
     */
    List<User> data;

    List<String> idList = new ArrayList<String>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_six);
        ButterKnife.bind(this);

        data = getUserData();
        //刷新页面数据
        refreshListView();


    }


    /**
     * 全选按钮点击事件
     */
    @OnClick(R.id.cb_atvt_select_worker)
    public void checkAll() {

        // 判断全选按钮是否选定，如果选定，则设置所有的数据条目都为选定状态，并且获取其pid，添加到idList集合中，更新适配器。
        //                       如果没有选定，设置所有的数据条目都为非选定状态，清除idList集合中的pid，更新适配器
        if (cbAtvtSelectWorker.isChecked()) {
            idList.clear();
            LogUtils.e("全部选定，获取所有的id，并进行保存，设置所有的子条目都为选定状态");
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setEnable(true);
                idList.add(data.get(i).getId());
            }
            LogUtils.e("idList 全部选定= " + idList.size());
            // 刷新
            mUserListAdapter.notifyDataSetChanged();
        } else {
            LogUtils.e("全部取消选定，清除所有获取到的id，并进行保存，设置所有的子条目都为非选定状态");
            idList.clear();
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setEnable(false);
            }
            LogUtils.e("idList 全部取消= " + idList.size());
            // 刷新
            mUserListAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 更新popupwindow列表页面数据
     */
    private void refreshListView() {
        if (mUserListAdapter == null) {
            mUserListAdapter = new UserListAdapter(this, data);
            lvAtvtUserList.setAdapter(mUserListAdapter);
            lvAtvtUserList.setOnItemClickListener(mUserListAdapter);
        } else {
            mUserListAdapter.notifyDataSetChanged();
        }
    }


    public List<User> getUserData() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId("" + i);
            user.setSelect(false);
            user.setEnable(false);
            user.setName("name " + i);
            user.setLevel("level " + i);
            user.setNickName("nick " + i);
            user.setGender("0");
            userList.add(user);
        }
        User user1 = new User();
        user1.setId("10");
        user1.setSelect(false);
        user1.setEnable(false);
        user1.setName("name 10" );
        user1.setLevel("level  10 " );
        user1.setNickName("nick  10 " );
        user1.setGender("0");
        userList.add(user1);

        return userList;
    }


    public TextView getToolbarTitle() {
        return toolbarTitle;
    }

    public void setToolbarTitle(TextView toolbarTitle) {
        this.toolbarTitle = toolbarTitle;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    public TextView getTvAtvtUserListState() {
        return tvAtvtUserListState;
    }

    public void setTvAtvtUserListState(TextView tvAtvtUserListState) {
        this.tvAtvtUserListState = tvAtvtUserListState;
    }

    public EditText getEtAtvtUserListJobNumberName() {
        return etAtvtUserListJobNumberName;
    }

    public void setEtAtvtUserListJobNumberName(EditText etAtvtUserListJobNumberName) {
        this.etAtvtUserListJobNumberName = etAtvtUserListJobNumberName;
    }

    public TextView getTvAtvtUserListSearch() {
        return tvAtvtUserListSearch;
    }

    public void setTvAtvtUserListSearch(TextView tvAtvtUserListSearch) {
        this.tvAtvtUserListSearch = tvAtvtUserListSearch;
    }

    public ListView getLvAtvtUserList() {
        return lvAtvtUserList;
    }

    public void setLvAtvtUserList(ListView lvAtvtUserList) {
        this.lvAtvtUserList = lvAtvtUserList;
    }

    public TextView getTvAtvtUserListUpdate() {
        return tvAtvtUserListUpdate;
    }

    public void setTvAtvtUserListUpdate(TextView tvAtvtUserListUpdate) {
        this.tvAtvtUserListUpdate = tvAtvtUserListUpdate;
    }

    public CheckBox getCbAtvtSelectWorker() {
        return cbAtvtSelectWorker;
    }

    public void setCbAtvtSelectWorker(CheckBox cbAtvtSelectWorker) {
        this.cbAtvtSelectWorker = cbAtvtSelectWorker;
    }

    public TextView getTvAtvtUserListDelete() {
        return tvAtvtUserListDelete;
    }

    public void setTvAtvtUserListDelete(TextView tvAtvtUserListDelete) {
        this.tvAtvtUserListDelete = tvAtvtUserListDelete;
    }

    public UserListAdapter getmUserListAdapter() {
        return mUserListAdapter;
    }

    public void setmUserListAdapter(UserListAdapter mUserListAdapter) {
        this.mUserListAdapter = mUserListAdapter;
    }

    public SparseArray<Boolean> getmSelectState() {
        return mSelectState;
    }

    public void setmSelectState(SparseArray<Boolean> mSelectState) {
        this.mSelectState = mSelectState;
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }

    public List<String> getIdList() {
        return idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
    }
}
