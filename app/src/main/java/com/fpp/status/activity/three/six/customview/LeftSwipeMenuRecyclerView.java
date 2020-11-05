package com.fpp.status.activity.three.six.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.fpp.status.activity.three.six.adapter.RVAdapter;
import com.fpp.status.utils.LogUtils;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by Administrator on 2017/6/16.
 */

public class LeftSwipeMenuRecyclerView extends RecyclerView {

    //删除按钮
    private TextView tvDelete;

    //item相应的布局
    private LinearLayout mItemLayout;

    //菜单的最大宽度
    private int mMaxLength;

    //上一次触摸行为的x坐标
    private int mLastX;
    //上一次触摸行为的y坐标
    private int mLastY;

    //当前触摸的item的位置
    private int mPosition;

    //是否在垂直滑动列表
    private boolean isDragging;

    //item是在否跟随手指移动
    private boolean isItemMoving;

    //item是否开始自动滑动
    private boolean isStartScroll;

    //左滑菜单状态   0：关闭 1：将要关闭 2：将要打开 3：打开
    private int mMenuState;

    private static int MENU_CLOSED = 0;
    private static int MENU_WILL_CLOSED = 1;
    private static int MENU_OPEN = 2;
    private static int MENU_WILL_OPEN = 3;

    //实现弹性滑动，恢复
    private Scroller mScroller;

    //构造函数
    public LeftSwipeMenuRecyclerView(Context context) {
        this(context, null);
    }

    public LeftSwipeMenuRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeftSwipeMenuRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context, new LinearInterpolator());
    }


    @Override
    public boolean onTouchEvent(MotionEvent e) {
        int x = (int) e.getX();
        int y = (int) e.getY();
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mMenuState == MENU_CLOSED) {  //关闭状态
                    //根据坐标获得view
                    View view = findChildViewUnder(x, y);
                    if (view == null) {
                        return false;
                    }
                    //获得这个view的ViewHolder
                    RVAdapter.Holder holder = (RVAdapter.Holder) getChildViewHolder(view);
                    //获得这个view的position
                    mPosition = holder.getAdapterPosition();
                    //获得这个view的整个布局
                    mItemLayout = holder.llLayout;
                    Log.e("-----sssssssss", "" + mItemLayout);
                    //获得这个view的加入购物车按钮
                    tvDelete = holder.tvDelete;

                    // 计算所有按钮的宽度
                    mMaxLength = tvDelete.getWidth();

                    //设置所有按钮的点击事件
                    tvDelete.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mItemLayout.scrollTo(0, 0);
                            LogUtils.e("点击删除按钮");

                            mMenuState = MENU_CLOSED;
                            mListener.delete(mPosition);
                            close();
                        }
                    });

                } else if (mMenuState == MENU_OPEN) {   //如果是打开状态，点击其他就把当前menu关闭掉
                    mScroller.startScroll(mItemLayout.getScrollX(), 0, -mMaxLength, 0, 200);
                    invalidate();
                    mMenuState = MENU_CLOSED;
                    close();
                    //该点击无效
                    return false;
                } else {
                    return false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                //计算偏移量
                int dx = mLastX - x;
                int dy = mLastY - y;

                //当前滑动的x
                int scrollx = mItemLayout.getScrollX();

                if (Math.abs(dx) > Math.abs(dy)) {
                    isItemMoving = true;
                    //超出左边界则始终保持不动
                    if (scrollx + dx <= 0) {
                        mItemLayout.scrollTo(0, 0);
                        //滑动无效
                        return false;
                        //超出右边界则始终保持不动
                    } else if (scrollx + dx >= mMaxLength) {
                        mItemLayout.scrollTo(mMaxLength, 0);
                        //滑动无效
                        return false;
                    }
                    //菜单随着手指移动
                    mItemLayout.scrollBy(dx, 0);
                    //如果水平移动距离大于30像素的话，recyclerView不会上下滑动
                } else if (Math.abs(dx) > 30) {
                    return true;
                }
                //如果菜单正在打开就不能上下滑动
                if (isItemMoving) {
                    mLastX = x;
                    mLastY = y;
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                //手指抬起时判断是否点击,静止且有Listener才能点击
//                if (!isItemMoving && !isDragging && mListener != null) {
//                    mListener.OnItemClick(mPosition);
//                }
                isItemMoving = false;

                //等一下要移动的距离
                int deltaX = 0;
                int upScrollx = mItemLayout.getScrollX();
                //滑动距离大于1/2menu长度就自动展开，否则就关掉
                if (upScrollx >= mMaxLength / 2) {
                    deltaX = mMaxLength - upScrollx;
                    mMenuState = MENU_WILL_OPEN;
                } else if (upScrollx < mMaxLength / 2) {
                    deltaX = -upScrollx;
                    mMenuState = MENU_WILL_CLOSED;
                }
                //知道我们为什么不直接把mMenuState赋值为MENU_OPEN或者MENU_CLOSED吗？因为滑动时有时间的，我们可以在滑动完成时才把状态改为已经完成
                mScroller.startScroll(upScrollx, 0, deltaX, 0, 200);
                isStartScroll = true;
                //刷新界面
                invalidate();
                break;
        }
        //只有更新mLastX，mLastY数据才会准确
        mLastX = x;
        mLastY = y;
        return super.onTouchEvent(e);
    }

    public void close() {
        invalidate();
        mMenuState = MENU_CLOSED;
    }

    @Override
    public void computeScroll() {
        //判断scroller是否完成滑动
        if (mScroller.computeScrollOffset()) {
            mItemLayout.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            //这个很重要
            invalidate();
            //如果已经完成就改变状态
        } else if (isStartScroll) {
            isStartScroll = false;
            if (mMenuState == MENU_WILL_CLOSED) {
                mMenuState = MENU_CLOSED;
            }
            if (mMenuState == MENU_WILL_OPEN) {
                mMenuState = MENU_OPEN;
            }
        }
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        //是否在上下滑动
        isDragging = state == SCROLL_STATE_DRAGGING;
    }

    //删除等按钮的监听
    public interface IOnItemActionListener {
        void delete(int position);
    }

    //声明删除按钮的监听
    private IOnItemActionListener mListener;

    //设置监听
    public void setOnItemActionListener(IOnItemActionListener onItemActionListener) {
        this.mListener = onItemActionListener;
    }

}
