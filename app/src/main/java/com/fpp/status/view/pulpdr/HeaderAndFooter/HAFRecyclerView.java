package com.fpp.status.view.pulpdr.HeaderAndFooter;

import android.content.Context;


import android.util.AttributeSet;
import android.view.View;

import com.fpp.status.view.pulpdr.AutoLoad.ALAdapter;
import com.fpp.status.view.pulpdr.PullToLoad.PullToLoadAdapter;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;


/**
 * Created by Administrator on 2016/9/19.
 */
public class HAFRecyclerView extends RecyclerView {
    public HAFRecyclerView(Context context) {
        super(context);
        clearItemAnimator();
    }

    public HAFRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        clearItemAnimator();
    }

    public HAFRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        clearItemAnimator();
    }

    protected HAFAdapter mAdapter;
    protected Adapter mRealAdapter;

//    第一次加载时，如果有覆盖整块的加载中，第一次完成后就需要隐藏
    private View mLoadingView;
    private final HAFObserver mDataObserver = new HAFObserver(this, null, mRealAdapter, mAdapter, true);

    @Override
    public void setAdapter(Adapter adapter) {
        mRealAdapter = adapter;
        if (adapter instanceof ALAdapter)
            mRealAdapter = ((ALAdapter) adapter).getRealAdapter();
        if (adapter instanceof PullToLoadAdapter)
            mRealAdapter = ((PullToLoadAdapter) adapter).getRealAdapter();

        if (adapter instanceof HAFAdapter) {
            mAdapter = (HAFAdapter) adapter;
        }
        else {
            mAdapter = new HAFAdapter(getContext(),adapter);
        }
        super.setAdapter(mAdapter);
        mDataObserver.setAdapter(mAdapter);
        mDataObserver.setRealAdapter(mRealAdapter);
        mAdapter.registerAdapterDataObserver(mDataObserver);
        mDataObserver.onChanged();
    }

    public void clearItemAnimator() {
        ItemAnimator itemAnimator = getItemAnimator();
        itemAnimator.setAddDuration(0);
        itemAnimator.setRemoveDuration(0);
        itemAnimator.setChangeDuration(0);
        itemAnimator.setMoveDuration(0);
        if (itemAnimator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        }
    }

    public void addHeaderView(View view) {
        if (null == view) {
            throw new IllegalArgumentException("the view to add must not be null !");
        } else if (mAdapter == null) {
            throw new IllegalStateException("u must set a adapter first !");
        } else {
            mAdapter.addHeaderView(view);
        }
    }

    public void addFooterView(View view) {
        if (null == view) {
            throw new IllegalArgumentException("the view to add must not be null !");
        } else if (mAdapter == null) {
            throw new IllegalStateException("u must set a adapter first !");
        } else {
            mAdapter.addFooterView(view);
        }
    }

    public void removeHeaderView(View view) {
        if (null == view) {
            throw new IllegalArgumentException("the view to remove must not be null !");
        } else if (mAdapter == null) {
            throw new IllegalStateException("u must set a adapter first !");
        } else {
            mAdapter.removeHeaderView(view);
        }
    }
    public void removeFooterView(View view) {
        if (null == view) {
            throw new IllegalArgumentException("the view to remove must not be null !");
        } else if (mAdapter == null) {
            throw new IllegalStateException("u must set a adapter first !");
        } else {
            mAdapter.removeFooterView(view);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        if (null == mAdapter) {
            throw new IllegalStateException("u must set a adapter first !");
        } else {
            mAdapter.setOnItemClickListener(onItemClickListener);
        }
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        if (null == mAdapter) {
            throw new IllegalStateException("u must set a adapter first !");
        } else {
            mAdapter.setOnItemLongClickListener(onItemLongClickListener);
        }
    }


    /**获得真正的adapter*/
    public Adapter getRealAdapter() {
        return mRealAdapter;
    }

    /**设置空白view*/
    public void setEmptyView(View emptyView) {
        mDataObserver.setEmptyView(emptyView);
        mDataObserver.onChanged();
    }

    /**设置空白view*/
    public void setEmptyView(View emptyView,boolean showEmptyViewHasHF) {
        mDataObserver.setEmptyView(emptyView);
        mDataObserver.setShowEmptyViewHasHF(showEmptyViewHasHF);
        mDataObserver.onChanged();
    }

    public void setLoadingView(View loadingView) {
        this.mLoadingView = loadingView;
    }

    public void setLoadingViewGone() {
        if (mLoadingView != null)
            mLoadingView.setVisibility(GONE);
    }

}
