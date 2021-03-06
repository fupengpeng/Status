package com.fpp.status.activity.popupwindow;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.viewpager.widget.PagerAdapter;

/**
 * @description  引导页适配器
 * @author  fupengpeng
 * @date  2018/2/25 0025 13:36
 */

public class ViewPagerAdatper extends PagerAdapter {
    private List<View> mViewList ;

    public ViewPagerAdatper(List<View> mViewList ) {
        this.mViewList = mViewList;
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViewList.get(position));
        return mViewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewList.get(position));
    }
}
