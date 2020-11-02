package com.fpp.status.activity.four.eight;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fpp.status.R;
import com.fpp.status.activity.popupwindow.MyPagerAdapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * @author fupengpeng
 * @description Handler消息传递机制
 * @data 2018/4/9 0009 11:47
 */

public class HandlerActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private PagerAdapter mAdapter;

    int[] imgRes = {R.drawable.shilipic, R.drawable.shilipic, R.drawable.shilipic};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_eight);

        mViewPager = (ViewPager) findViewById(R.id.vp_atvt_handler_eight);
        //设置Page间间距
//        mViewPager.setPageMargin(20);
        //设置缓存的页面数量
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mAdapter = new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = View.inflate(HandlerActivity.this,R.layout.item_vp_iv,null);
//                ImageView view = new ImageView(HandlerActivity.this);
                ImageView iv = (ImageView) view.findViewById(R.id.iv_item_vp_iv);
                iv.setImageResource(imgRes[position]);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public int getCount() {
                return imgRes.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object o) {
                return view == o;
            }
        });

    }
}
