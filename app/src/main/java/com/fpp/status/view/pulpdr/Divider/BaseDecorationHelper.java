package com.fpp.status.view.pulpdr.Divider;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fpp.status.view.pulpdr.AutoLoad.ALRecyclerView;
import com.fpp.status.view.pulpdr.HeaderAndFooter.HAFAdapter;
import com.fpp.status.view.pulpdr.PullToLoad.PullToLoadRecyclerView;
import com.fpp.status.view.pulpdr.PullToRefresh.PullToRefreshRecyclerView;


/**
 * Created by Administrator on 2016/9/25.
 */
public abstract class BaseDecorationHelper {

    public abstract void onDraw(Canvas c, RecyclerView parent, Drawable divider, int height, int width);

    public abstract void getItemOffsets(Rect outRect, View view, RecyclerView parent, int height, int width);

    /**
     * 是不是头部
     */
    protected boolean isHeaderView(RecyclerView parent, View view) {
        if (parent.getAdapter() != null && parent.getAdapter() instanceof HAFAdapter) {
            int position = parent.getChildAdapterPosition(view);
            return position < ((HAFAdapter) parent.getAdapter()).getHeadersCount();
        }
        return false;
    }

    /**
     * 是不是尾部
     */
    protected boolean isFooterView(RecyclerView parent, View view) {
        if (parent.getAdapter() != null && parent.getAdapter() instanceof HAFAdapter) {
            int position = parent.getChildAdapterPosition(view);
            final HAFAdapter adapter = (HAFAdapter) parent.getAdapter();
            return position >= adapter.getHeadersCount() + adapter.getRealItemCount();
        }
        return false;
    }

    /**是不是刷新头部*/
    protected boolean isRefreshView(RecyclerView parent, View view) {
        if (parent instanceof PullToRefreshRecyclerView) {
            int position = parent.getChildAdapterPosition(view);
            PullToRefreshRecyclerView pullToRefreshRecyclerView = (PullToRefreshRecyclerView) parent;
            return position <  pullToRefreshRecyclerView.getRefreshViewCount();
        }
        return false;
    }

    /**是不是加载尾部*/
    protected boolean isLoadView(RecyclerView parent, View view) {
        if (parent instanceof PullToLoadRecyclerView) {
            int position = parent.getChildAdapterPosition(view);
            PullToLoadRecyclerView pullToLoadRecyclerView = (PullToLoadRecyclerView) parent;
            return position >= parent.getAdapter().getItemCount() - pullToLoadRecyclerView.getLoadViewCount();
        } else if (parent instanceof ALRecyclerView) {
            int position = parent.getChildAdapterPosition(view);
            ALRecyclerView aLRecyclerView = (ALRecyclerView) parent;
            return position >= parent.getAdapter().getItemCount() - aLRecyclerView.getLoadViewCount();
        }
        return false;
    }

    /**获得头部数量*/
    protected int getHeadersCount(RecyclerView parent) {
        if (parent.getAdapter() instanceof HAFAdapter) {
            HAFAdapter hAFAdapter = (HAFAdapter) parent.getAdapter();
            return hAFAdapter.getHeadersCount();
        }
        return 0;
    }

    /**获得刷新头部数量*/
    protected int getRefreshViewCount(RecyclerView parent) {
        if (parent instanceof PullToRefreshRecyclerView) {
            PullToRefreshRecyclerView pullToRefreshRecyclerView = (PullToRefreshRecyclerView) parent;
            return pullToRefreshRecyclerView.getRefreshViewCount();
        }
        return 0;
    }

    /**获得尾部数量*/
    protected int getFootersCount(RecyclerView parent) {
        if (parent.getAdapter() instanceof HAFAdapter) {
            HAFAdapter hAFAdapter = (HAFAdapter) parent.getAdapter();
            return hAFAdapter.getFootersCount();
        }
        return 0;
    }

    /**获得加载尾部数量*/
    protected int getLoadViewCount(RecyclerView parent) {
        if (parent instanceof PullToLoadRecyclerView) {
            PullToLoadRecyclerView pullToLoadRecyclerView = (PullToLoadRecyclerView) parent;
            return pullToLoadRecyclerView.getLoadViewCount();
        } else if (parent instanceof ALRecyclerView) {
            ALRecyclerView aLRecyclerView = (ALRecyclerView) parent;
            return aLRecyclerView.getLoadViewCount();
        }
        return 0;
    }

}
