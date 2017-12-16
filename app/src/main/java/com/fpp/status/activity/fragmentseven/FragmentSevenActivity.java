package com.fpp.status.activity.fragmentseven;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fpp.status.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fupengpeng on 2017/12/16 0016.
 */

public class FragmentSevenActivity extends AppCompatActivity {


    public static final int INT = 90;
    public static final int INT1 = 48;


    //声明下拉刷新ListView组件
    private PullToRefreshListView myListView;
    //声明数据源
    private List<String> data;
    //声明适配器
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_seven);
        //获取下拉刷新ListView组件
        this.myListView = (PullToRefreshListView) findViewById(R.id.mylistview);
        //模拟数据
        this.data = new ArrayList<String>();
        data.add("JAVA");
        data.add("PHP");
        data.add("C++");
        data.add("C#");
        //实例化Adapter
        this.adapter = new ArrayAdapter<>(FragmentSevenActivity.this, android.R.layout.simple_list_item_1, data);
        //设置Adapter
        myListView.setAdapter(adapter);
        //设置支持上下拉动和监听
        myListView.setMode(PullToRefreshBase.Mode.BOTH);
        myListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {

            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                if (refreshView.isShownHeader()) {
                    //判断头布局是否可见，如果可见执行下拉刷新
                    //设置尾布局样式文字
                    myListView.getLoadingLayoutProxy().setRefreshingLabel("正在刷新");
                    myListView.getLoadingLayoutProxy().setPullLabel("下拉刷新数据");
                    myListView.getLoadingLayoutProxy().setReleaseLabel("释放开始刷新");
                    //模拟加载数据线程休息3秒
                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... params) {
                            try {
                                Thread.sleep(3000);
                                data.add("刷新数据1");
                                data.add("刷新数据2");
                                data.add("刷新数据3");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void result) {
                            super.onPostExecute(result);
                            //完成对下拉刷新ListView的更新操作
                            adapter.notifyDataSetChanged();
                            //将下拉视图收起
                            myListView.onRefreshComplete();
                        }
                    }.execute();
                }
                if (refreshView.isShownFooter()) {
                    //判断尾布局是否可见，如果可见执行上拉加载更多
                    //设置尾布局样式文字
                    myListView.getLoadingLayoutProxy().setRefreshingLabel("正在加载");
                    myListView.getLoadingLayoutProxy().setPullLabel("上拉加载更多");
                    myListView.getLoadingLayoutProxy().setReleaseLabel("释放开始加载");
                    //模拟加载数据线程休息3秒
                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... params) {
                            try {
                                Thread.sleep(3000);
                                data.add("更多数据1");
                                data.add("更多数据2");
                                data.add("更多数据3");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void result) {
                            super.onPostExecute(result);
                            //完成对下拉刷新ListView的更新操作
                            adapter.notifyDataSetChanged();
                            //将下拉视图收起
                            myListView.onRefreshComplete();
                        }
                    }.execute();


                }

            }
        });

    }


}
