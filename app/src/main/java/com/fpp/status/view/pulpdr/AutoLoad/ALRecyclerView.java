package com.fpp.status.view.pulpdr.AutoLoad;

import android.content.Context;
import android.graphics.Canvas;

import android.util.AttributeSet;
import android.view.View;

import com.fpp.status.view.pulpdr.DefaultHeaderAndFooterCreator.DALFooterCreator;
import com.fpp.status.view.pulpdr.PullToLoad.OnLoadListener;
import com.fpp.status.view.pulpdr.PullToRefresh.PullToRefreshRecyclerView;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * 自动加载RV
 */
public class ALRecyclerView extends PullToRefreshRecyclerView {

    public ALRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public ALRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ALRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private View mLoadView;
    private ALAdapter mAdapter;
    private RecyclerView.Adapter mRealAdapter;

    private boolean mIsLoading = false;
    private boolean mLoadMoreEnable = true;
    private boolean mNoMore = false;
    private View mNoMoreView;

    private ALFooterCreator mALFooterCreator;
    private OnLoadListener mOnLoadListener;


    private void init(Context context) {
        mALFooterCreator = new DALFooterCreator();
        mLoadView = mALFooterCreator.getLoadView(context,this);
        mNoMoreView = mALFooterCreator.getNoMoreView(context,this);
    }

    /**
     * 隐藏加载尾部
     */
    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);
        if (mLoadView == null) return;
//        若数据不满一屏
        if (getAdapter() == null) return;
        if (getChildCount() >= getAdapter().getItemCount()) {
            if (mLoadView.getVisibility() != GONE) {
                mLoadView.setVisibility(GONE);
            }
        } else {
            if (mLoadView.getVisibility() != VISIBLE) {
                mLoadView.setVisibility(VISIBLE);
            }
        }
    }


    @Override
    public void setAdapter(RecyclerView.Adapter adapter) {
        mRealAdapter = adapter;
        if (adapter instanceof ALAdapter) {
            mAdapter = (ALAdapter) adapter;
        } else{
            mAdapter = new ALAdapter(getContext(),adapter);
        }
        super.setAdapter(mAdapter);
        if (mLoadView != null) {
            mAdapter.setLoadView(mLoadView);
        }

    }

    /**注销监听，防止内存泄露*/
    @Override
    protected void onDetachedFromWindow() {
        if(mALFooterCreator != null && mALFooterCreator instanceof DALFooterCreator) {
            ((DALFooterCreator) mALFooterCreator).cancelListener();
        }
        super.onDetachedFromWindow();
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        if (state == RecyclerView.SCROLL_STATE_IDLE  && !mIsLoading && mLoadMoreEnable && mLoadView != null) {
            RecyclerView.LayoutManager layoutManager = getLayoutManager();
            int lastVisibleItemPosition;
            if (layoutManager instanceof GridLayoutManager) {
                lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                int[] into = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(into);
                lastVisibleItemPosition = findMax(into);
            } else {
                lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            }
            if (layoutManager.getChildCount() > 0
                    && lastVisibleItemPosition >= layoutManager.getItemCount() - 1 && layoutManager.getItemCount() > layoutManager.getChildCount() && !mNoMore ) {
                mIsLoading = true;
                if (mOnLoadListener != null)
                    mOnLoadListener.onStartLoading(mRealAdapter.getItemCount());
            }
        }
    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    /**完成加载*/
    public void completeLoad(int loadItemCount) {
        mIsLoading = false;
        setLoadingViewGone();

        int startItem = mRealAdapter.getItemCount() + mAdapter.getHeadersCount() - loadItemCount;
        mAdapter.notifyItemRangeInserted(startItem, loadItemCount);

    }

    /**没有更多*/
    public void setNoMore(boolean noMore) {
        mIsLoading = false;
        mNoMore = noMore;
        if (mNoMore) {
            if (mNoMoreView != null)
                mAdapter.setLoadView(mNoMoreView);
        }
        else if (mLoadView != null)
            mAdapter.setLoadView(mLoadView);
    }

    /**设置加载监听*/
    public void setOnLoadListener(OnLoadListener onLoadListener) {
        this.mOnLoadListener = onLoadListener;
    }

    /**获得加载中View和底部填充view的个数，用于绘制分割线*/
    public int getLoadViewCount() {
        if (mLoadView != null)
            return 1;
        return 0;
    }

    /**获得真正的adapter*/
    @Override
    public RecyclerView.Adapter getRealAdapter() {
        return mRealAdapter;
    }

    /**
     * 设置自定义的加载尾部
     */
    public void setAutoLoadViewCreator(ALFooterCreator aLFooterCreator) {
        if (aLFooterCreator == null) {
            throw new IllegalArgumentException("the ALFooterCreator u set must not be null");
        } else {
            this.mALFooterCreator = aLFooterCreator;
            mLoadView = aLFooterCreator.getLoadView(getContext(),this);
            mAdapter.setLoadView(mLoadView);
            mNoMoreView = aLFooterCreator.getNoMoreView(getContext(),this);
        }
    }

}
