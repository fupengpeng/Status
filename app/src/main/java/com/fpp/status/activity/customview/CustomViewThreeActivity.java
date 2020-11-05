package com.fpp.status.activity.customview;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;

import com.fpp.status.R;
import com.fpp.status.adapter.CustomListViewAdapter;
import com.fpp.status.utils.LogUtils;
import com.fpp.status.view.CustomViewThree;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class CustomViewThreeActivity extends AppCompatActivity {


    // 自定义Lv
    private CustomViewThree mCustomLv;
    // 自定义适配器
    private CustomListViewAdapter mAdapter;
    // 内容列表
    private List<String> contentList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_custom_view_three);

        initContentList();

        mCustomLv = (CustomViewThree) findViewById(R.id.custom_lv);
        mCustomLv.setOnDeleteListener(new CustomViewThree.OnDeleteListener() {

            @Override
            public void onDelete(int index) {
                contentList.remove(index);
                mAdapter.notifyDataSetChanged();
            }
        });

        mAdapter = new CustomListViewAdapter(this, 0, contentList);
        mCustomLv.setAdapter(mAdapter);
        mCustomLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtils.e("position = " + position);
            }
        });
    }

    // 初始化内容列表
    private void initContentList() {
        for (int i = 0; i < 20; i++) {
            contentList.add("内容项" + i);
        }
    }

    @Override
    public void onBackPressed() {
        if (mCustomLv.isDeleteShown()) {
            mCustomLv.hideDelete();
            return;
        }
        super.onBackPressed();
    }
}
