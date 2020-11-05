package com.fpp.status.view.pulpdr.AutoLoad;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Administrator on 2016/9/30.
 */
public abstract class ALFooterCreator {

    /***
     * 获得footer
     */
    protected abstract View getLoadView(Context context, RecyclerView recyclerView);

    /***
     * 没有更多
     */
    protected abstract View getNoMoreView(Context context,RecyclerView recyclerView);

}
