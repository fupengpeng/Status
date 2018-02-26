package com.fpp.status.activity.popupwindow;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fpp.status.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;



/**
 * @description  轮播
 * @author  fupengpeng
 * @date  2018/2/25 0025 16:18
 */
public class PWOneActivity extends AppCompatActivity  implements ViewPager.OnPageChangeListener, View.OnTouchListener {

    public static final int VIEW_PAGER_DELAY = 2000;

    //viewpager适配器
    private MyPagerAdapter mAdapter;
    /**
     * 图片数据
     */
    private List<ImageView> mItems;
    /**
     * 引导页
     */
    private ImageView[] mBottomImages;

    private LinearLayout mBottomLiner;
    private ViewPager mViewPager;

    private int currentViewPagerItem;

    //是否自动播放
    private boolean isAutoPlay;

    private MyHandler mHandler;

    private Thread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwone);

        mHandler = new MyHandler(this);
        //配置轮播图ViewPager
        mViewPager = ((ViewPager) findViewById(R.id.live_view_pager));

        mItems = new ArrayList<>();
        mAdapter = new MyPagerAdapter(mItems, this);
        mViewPager.setAdapter(mAdapter);

        mViewPager.setOnTouchListener(this);

        mViewPager.addOnPageChangeListener(this);

        isAutoPlay = true;

        //TODO: 添加ImageView
        addImageView();
        mAdapter.notifyDataSetChanged();

        //设置底部指示点
        setBottomIndicator();


    }

    /**
     * 引导页图片view数据设置
     */
    private void addImageView(){

        ImageView view0 = new ImageView(this);
        view0.setImageResource(R.drawable.meinv04);

        ImageView view1 = new ImageView(this);
        view1.setImageResource(R.drawable.meinv05);

        ImageView view2 = new ImageView(this);
        view2.setImageResource(R.drawable.meinv06);

        view0.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view1.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view2.setScaleType(ImageView.ScaleType.CENTER_CROP);

        mItems.add(view0);
        mItems.add(view1);
        mItems.add(view2);

    }

    /**
     * 指示点设置
     */
    private void setBottomIndicator() {
        //获取指示点
        mBottomLiner = (LinearLayout) findViewById(R.id.live_indicator);
        //下方小圆点
        mBottomImages = new ImageView[mItems.size()];

        for (int i = 0; i < mBottomImages.length; i++) {
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            params.setMargins(5, 0, 5, 0);
            imageView.setLayoutParams(params);
            //如果当前是第一个 设置为选中状态
            if (i == 0) {
                imageView.setImageResource(R.drawable.indicator_select);
            } else {
                imageView.setImageResource(R.drawable.indicator_no_select);
            }
            mBottomImages[i] = imageView;
            //添加到父容器
            mBottomLiner.addView(imageView);
        }

        //让其在最大值的中间开始滑动, 一定要在 mBottomImages初始化之前完成
        int mid = MyPagerAdapter.MAX_SCROLL_VALUE / 2;
        mViewPager.setCurrentItem(mid);
        currentViewPagerItem = mid;

        //定时发送消息
        mThread = new Thread(){
            @Override
            public void run() {
                super.run();
                while (true) {
                    mHandler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(PWOneActivity.VIEW_PAGER_DELAY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };

        mThread.start();
    }






    ///////////////////////////////////////////////////////////////////////////
    // ViewPager的监听事件
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {

        currentViewPagerItem = position;
        if (mItems != null) {
            position %= mBottomImages.length;
            int total = mBottomImages.length;

            for (int i = 0; i < total; i++) {
                if (i == position) {
                    mBottomImages[i].setImageResource(R.drawable.indicator_select);
                } else {
                    mBottomImages[i].setImageResource(R.drawable.indicator_no_select);
                }
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                isAutoPlay = false;
                break;
            case MotionEvent.ACTION_UP:
                isAutoPlay = true;
                break;
        }
        return false;
    }

    ///////////////////////////////////////////////////////////////////////////
    // 为防止内存泄漏, 声明自己的Handler并弱引用Activity
    ///////////////////////////////////////////////////////////////////////////
    private static class MyHandler extends Handler {
        private WeakReference<PWOneActivity> mWeakReference;

        public MyHandler(PWOneActivity activity) {
            mWeakReference = new WeakReference<PWOneActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    PWOneActivity activity = mWeakReference.get();
                    if (activity.isAutoPlay) {
                        activity.mViewPager.setCurrentItem(++activity.currentViewPagerItem);
                    }

                    break;
            }

        }
    }
}