package com.fpp.status.activity.fourfive;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * @description  引导页适配器----二
 * @author  fupengpeng
 * @date  2018/2/25 0025 15:25
 */
public class GuidePagerAdapter extends PagerAdapter {
    ArrayList<ImageView> listImg;
    public GuidePagerAdapter(ArrayList<ImageView> listImg){
        this.listImg=listImg;
    }
    @Override
    public int getCount() {
        return listImg==null?0:listImg.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView img=listImg.get(position);
        container.addView(img);
        return img;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(listImg.get(position));
    }
}
