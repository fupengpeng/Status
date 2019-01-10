package com.fpp.status.activity.ten.three;

import android.content.Context;
import android.util.AttributeSet;

import com.fpp.status.view.banner.view.BannerViewPager;

/**
 * Description:
 * Author: fpp
 * Date: 2018/7/13  17:26
 */

public class CarouselViewPager  extends BannerViewPager {
    // do something by yourself.

    public CarouselViewPager(Context context) {
        super(context);
        this.setPageMargin(40);
        this.setOffscreenPageLimit(4);
    }

    public CarouselViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setPageMargin(40);
        this.setOffscreenPageLimit(4);
    }

    @Override
    public void setPageMargin(int marginPixels) {
        super.setPageMargin(marginPixels);
    }

    //    @Override
//    public void setPadding(int left, int top, int right, int bottom) {
//        super.setPadding(20, 0, 20, 0);
//    }
//
//    @Override
//    public void setClipToPadding(boolean clipToPadding) {
//        super.setClipToPadding(clipToPadding);
//    }
}
