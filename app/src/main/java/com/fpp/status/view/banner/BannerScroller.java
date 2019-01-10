package com.fpp.status.view.banner;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import com.fpp.status.utils.LogUtil;

public class BannerScroller extends Scroller {
    private int mDuration = BannerConfig.DURATION;

    public BannerScroller(Context context) {
        super(context);
        LogUtil.e("BannerScroller + " + " ");
    }

    public BannerScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public BannerScroller(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        LogUtil.e("---startScroll + " + "---  startX = " + startX
                + "   startY = " + startY
                + "   dx = " + dx
                + "   dy = " + dy
                + "   duration = " + duration
        );
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        LogUtil.e("startScroll 后 + " + "---  startX = " + startX
                + "   startY = " + startY
                + "   dx = " + dx
                + "   dy = " + dy
        );
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    public void setDuration(int time) {
        LogUtil.e("setDuration = " + "---  设置时间   = " + time
        );
        mDuration = time;
    }

}
