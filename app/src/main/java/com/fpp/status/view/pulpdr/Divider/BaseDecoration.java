package com.fpp.status.view.pulpdr.Divider;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
<<<<<<< HEAD
import android.util.TypedValue;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

=======
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.View;

>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c
/**
 * Created by Administrator on 2016/9/25.
 */
public class BaseDecoration extends RecyclerView.ItemDecoration {


    private Context mContext;

    private Drawable mDivider;
    private int mHeight;
    private int mWidth;

    private BaseDecorationHelper mItemDecorationHelper;

    public BaseDecoration(Context mContext, int res) {
        this.mContext = mContext;
        this.mDivider = getDivider(res);
        this.mHeight = mDivider.getIntrinsicHeight() <= 0 ? dp2px(5) : mDivider.getIntrinsicHeight();
        this.mWidth = mDivider.getIntrinsicWidth() <= 0 ? dp2px(5) : mDivider.getIntrinsicWidth();
    }

    public BaseDecoration(Context mContext, int res, BaseDecorationHelper itemDecorationHelper) {
        this.mContext = mContext;
        this.mDivider = getDivider(res);
        this.mHeight = mDivider.getIntrinsicHeight() <= 0 ? dp2px(5) : mDivider.getIntrinsicHeight();
        this.mWidth = mDivider.getIntrinsicWidth() <= 0 ? dp2px(5) : mDivider.getIntrinsicWidth();
        this.mItemDecorationHelper = itemDecorationHelper;
    }

    public BaseDecoration(Context mContext, Drawable mDivider, BaseDecorationHelper itemDecorationHelper) {
        this.mContext = mContext;
        this.mDivider = mDivider;
        this.mHeight = mDivider.getIntrinsicHeight() <= 0 ? dp2px(5) : mDivider.getIntrinsicHeight();
        this.mWidth = mDivider.getIntrinsicWidth() <= 0 ? dp2px(5) : mDivider.getIntrinsicWidth();
        this.mItemDecorationHelper = itemDecorationHelper;
    }

    public BaseDecoration(Context mContext, int res, int mHeight, int mWidth) {
        this.mContext = mContext;
        this.mDivider = getDivider(res);
        this.mHeight = mHeight;
        this.mWidth = mWidth;
    }

    public BaseDecoration(Context mContext, int res, int mHeight, int mWidth, BaseDecorationHelper itemDecorationHelper) {
        this.mContext = mContext;
        this.mDivider = getDivider(res);
        this.mHeight = mHeight;
        this.mWidth = mWidth;
        this.mItemDecorationHelper = itemDecorationHelper;
    }

    public BaseDecoration(Context mContext, Drawable mDivider, int mHeight, int mWidth) {
        this.mContext = mContext;
        this.mDivider = mDivider;
        this.mHeight = mHeight;
        this.mWidth = mWidth;
    }

    public BaseDecoration(Context mContext, Drawable mDivider, int mHeight, int mWidth, BaseDecorationHelper itemDecorationHelper) {
        this.mContext = mContext;
        this.mDivider = mDivider;
        this.mHeight = mHeight;
        this.mWidth = mWidth;
        this.mItemDecorationHelper = itemDecorationHelper;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mItemDecorationHelper != null) {
            mItemDecorationHelper.onDraw(c, parent, mDivider, mHeight, mWidth);
            return;
        }

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            mItemDecorationHelper = new GridDecorationHelper();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            mItemDecorationHelper = new StaggeredDecorationHelper();
        } else if (layoutManager instanceof LinearLayoutManager) {
            mItemDecorationHelper = new LinearDecorationHelper();
        }
        if (mItemDecorationHelper != null)
            mItemDecorationHelper.onDraw(c, parent, mDivider, mHeight, mWidth);
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mItemDecorationHelper != null) {
            mItemDecorationHelper.getItemOffsets(outRect,view,parent,mHeight,mWidth);
            return;
        }

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            mItemDecorationHelper = new GridDecorationHelper();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            mItemDecorationHelper = new StaggeredDecorationHelper();
        } else if (layoutManager instanceof LinearLayoutManager) {
            mItemDecorationHelper = new LinearDecorationHelper();
        }
        if (mItemDecorationHelper != null)
            mItemDecorationHelper.getItemOffsets(outRect,view,parent,mHeight,mWidth);
    }

    private Drawable getDivider(int res) {
        Resources resources = mContext.getResources();
        return resources.getDrawable(res);
    }

    private int dp2px(int dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, mContext.getResources().getDisplayMetrics());
    }

}
