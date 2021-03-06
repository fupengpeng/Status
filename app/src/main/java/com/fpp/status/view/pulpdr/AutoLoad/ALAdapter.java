package com.fpp.status.view.pulpdr.AutoLoad;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;

import com.fpp.status.view.pulpdr.HeaderAndFooter.HAFAdapter;

import androidx.recyclerview.widget.RecyclerView;


/**
 * 自动加载适配器
 * @param <T>
 */
public class ALAdapter<T extends RecyclerView.Adapter> extends HAFAdapter {

    private static int ITEM_TYPE_LOAD = 30000000;

    private View mLoadView;

    protected T mRealAdapter;

    public ALAdapter(Context mContext, T mRealAdapter) {
        super(mContext, mRealAdapter);
        this.mRealAdapter = mRealAdapter;
    }

    public T getRealAdapter() {
        return mRealAdapter;
    }

    @Override
    public int getItemCount() {
        if (mLoadView != null)
            return super.getItemCount() + 1;
        return super.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoadPosition(position)) {
            return ITEM_TYPE_LOAD;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_LOAD)
            return new RecyclerView.ViewHolder(mLoadView) {};
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isLoadPosition(position)) return;
        super.onBindViewHolder(holder, position);
    }

    private boolean isLoadPosition(int position) {
        if (mLoadView == null) return false;
        return position == getItemCount()-1;
    }

    public void setLoadView(View loadView) {
        ITEM_TYPE_LOAD++;
        this.mLoadView = loadView;
        notifyItemChanged(getItemCount()-1);
    }

    public View getLoadView() {
        return this.mLoadView;
    }

}
