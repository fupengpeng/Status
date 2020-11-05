package com.fpp.status.view.pulpdr.PullToRefresh;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Administrator on 2016/9/28.
 */
public abstract class RefreshHeaderCreator {

    protected View mRefreshView;

    /**
     * 下拉
     * @param distance 距离
     * @return 下拉距离，返回true表示可以继续下拉
     */
    public abstract boolean onStartPull(float distance, int lastState);

    /**
     * 松手刷新
     * @param distance 距离
     * @return 下拉距离，返回true表示可以继续下拉
     */
    public abstract boolean onReleaseToRefresh(float distance,int lastState);

    /**开始刷新*/
    public abstract void onStartRefreshing();

    /**刷新结束*/
    public abstract void onStopRefresh();

    public abstract View getRefreshView(Context context, RecyclerView recyclerView);


}
