package com.fpp.status.view.pulpdr.AutoLoad;

import android.content.Context;
<<<<<<< HEAD
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

=======
import android.support.v7.widget.RecyclerView;
import android.view.View;

>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c
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
