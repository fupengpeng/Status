package com.fpp.status.view.pulpdr.PullToRefresh;

import android.content.Context;
<<<<<<< HEAD
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

=======
import android.support.v7.widget.RecyclerView;
import android.view.View;

>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c
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

<<<<<<< HEAD
    public abstract View getRefreshView(Context context, RecyclerView recyclerView);
=======
    public abstract View getRefreshView(Context context,RecyclerView recyclerView);
>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c


}
