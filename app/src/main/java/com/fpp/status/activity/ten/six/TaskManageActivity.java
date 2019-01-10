package com.fpp.status.activity.ten.six;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fpp.status.R;
import com.fpp.status.base.BaseApplication;
import com.fpp.status.greendao.DownloadDataDao;
import com.fpp.status.view.download.data.DownloadData;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.interfaces.OnItemChildClickListener;

import com.othershe.dutil.db.Db;
import com.othershe.dutil.download.DownloadManger;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author: fpp
 * Date: 2018/7/27  15:54
 */

public class TaskManageActivity  extends AppCompatActivity {
    private String url1 = "http://img.xiaojiangjiakao.com/20180627/7podaodingdiantingchejiaocheng.mp4";//欢乐斗地主
    private String url2 = "http://img.xiaojiangjiakao.com/20180627/1bukebukan.mp4";//球球大作战
    private String url3 = "http://img.xiaojiangjiakao.com/20180627/1bukebukanzhengquejiashizishi.mp4";//节奏大师
    private String url4 = "http://img.xiaojiangjiakao.com/20180627/1haibuhuidaocherukunihaichakanzhege.mp4";//部落冲突
    private String url5 = "http://img.xiaojiangjiakao.com/20180627/1kemu3kaoshiliucheng.mp4";//捕鱼达人

    private String path = Environment.getExternalStorageDirectory() + "/DUtil/";

    private RecyclerView downloadList;
    private DownloadListAdapter downloadListAdapter;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_manage);

        mContext = this;

        DownloadManger.getInstance(mContext).setTaskPoolSize(2, 10);

        downloadList = (RecyclerView) findViewById(R.id.download_list);
        final List<DownloadData> datas = new ArrayList<>();
        if (Db.getInstance(mContext).getData(url1) != null){
            DownloadData downloadData =
                    BaseApplication.daoSession.getDownloadDataDao()
                            .queryBuilder().where(DownloadDataDao.Properties.Url.eq(url1)).unique();

            datas.add(downloadData);
        }else{
            datas.add(new DownloadData(url1, path, "欢乐斗地主.mp4"));
        }

        if (Db.getInstance(mContext).getData(url2) != null){
            DownloadData downloadData =
                    BaseApplication.daoSession.getDownloadDataDao()
                            .queryBuilder().where(DownloadDataDao.Properties.Url.eq(url2)).unique();
            datas.add(downloadData);
        }else{
            datas.add(new DownloadData(url2, path, "球球大作战.mp4"));
        }

        if (Db.getInstance(mContext).getData(url3) != null){
            DownloadData downloadData =
                    BaseApplication.daoSession.getDownloadDataDao()
                            .queryBuilder().where(DownloadDataDao.Properties.Url.eq(url3)).unique();
            datas.add(downloadData);
        }else{
            datas.add(new DownloadData(url3, path, "节奏大师.mp4"));
        }

        if (Db.getInstance(mContext).getData(url4) != null){
            DownloadData downloadData =
                    BaseApplication.daoSession.getDownloadDataDao()
                            .queryBuilder().where(DownloadDataDao.Properties.Url.eq(url4)).unique();
            datas.add(downloadData);
        }else{
            datas.add(new DownloadData(url4, path, "部落冲突.mp4"));
        }

        if (Db.getInstance(mContext).getData(url5) != null){
            DownloadData downloadData =
                    BaseApplication.daoSession.getDownloadDataDao()
                            .queryBuilder().where(DownloadDataDao.Properties.Url.eq(url5)).unique();
            datas.add(downloadData);
        }else{
            datas.add(new DownloadData(url5, path, "捕鱼达人.mp4"));
        }

        downloadListAdapter = new DownloadListAdapter(this, datas, false);

        //开始
        downloadListAdapter.setOnItemChildClickListener(R.id.start, new OnItemChildClickListener<DownloadData>() {
            @Override
            public void onItemChildClick(final ViewHolder viewHolder, final DownloadData data, int position) {
                DownloadManger.getInstance(mContext).start(data.getUrl());
            }
        });

        //暂停
        downloadListAdapter.setOnItemChildClickListener(R.id.pause, new OnItemChildClickListener<DownloadData>() {
            @Override
            public void onItemChildClick(ViewHolder viewHolder, DownloadData data, int position) {
                DownloadManger.getInstance(mContext).pause(data.getUrl());
            }
        });

        //继续
        downloadListAdapter.setOnItemChildClickListener(R.id.resume, new OnItemChildClickListener<DownloadData>() {
            @Override
            public void onItemChildClick(ViewHolder viewHolder, DownloadData data, int position) {
                DownloadManger.getInstance(mContext).resume(data.getUrl());
            }
        });

        //取消
        downloadListAdapter.setOnItemChildClickListener(R.id.cancel, new OnItemChildClickListener<DownloadData>() {
            @Override
            public void onItemChildClick(ViewHolder viewHolder, DownloadData data, int position) {
                DownloadManger.getInstance(mContext).cancel(data.getUrl());
            }
        });

        //重新开始
        downloadListAdapter.setOnItemChildClickListener(R.id.restart, new OnItemChildClickListener<DownloadData>() {
            @Override
            public void onItemChildClick(ViewHolder viewHolder, DownloadData data, int position) {
                DownloadManger.getInstance(mContext).restart(data.getUrl());
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        downloadList.setLayoutManager(layoutManager);
        downloadList.setAdapter(downloadListAdapter);
    }

    @Override
    protected void onDestroy() {
        DownloadManger.getInstance(mContext).destroy(url1, url2, url3, url4, url5);
        super.onDestroy();
    }
}
