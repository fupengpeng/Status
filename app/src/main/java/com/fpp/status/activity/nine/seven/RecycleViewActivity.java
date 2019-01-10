package com.fpp.status.activity.nine.seven;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fpp.status.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author: fp
 * Date: 2018/6/28  23:14
 */

public class RecycleViewActivity extends AppCompatActivity {



    private List<SectionBean> sectionBeans;
    private SectionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        initView();

    }


    /**
     * 初始化view
     */
    private void initView() {
        getData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_atvt_section);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SectionAdapter(this,sectionBeans);
        recyclerView.setAdapter(adapter);

    }


    public void getData(){
        sectionBeans = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            SectionBean sectionBean = new SectionBean();
            sectionBean.setId("" + i);
            sectionBean.setTitle("章节 一 ");

            List<TopicBean> topicBeans = new ArrayList<>();
            for (int j = 0; j < 22; j++) {
                TopicBean topicBean = new TopicBean();
                topicBean.setId(i + "-" + j);
                topicBean.setIs_wrong(true);
                topicBeans.add(topicBean);
            }

            sectionBean.setTopicBeans(topicBeans);
            sectionBeans.add(sectionBean);

        }

    }

}
