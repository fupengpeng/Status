package com.fpp.status.transformer;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fpp.status.R;
import com.fpp.status.utils.LogUtil;
import com.fpp.status.utils.UIUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * Description:
 * Author: fpp
 * Date: 2018/7/10  15:55
 */

public class CarouselActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnTouchListener {


    List<String> imageUrls = new ArrayList<>();

    private ExercisePagerAdapter pagerAdapter;
    public static final int VIEW_PAGER_DELAY = 2000;

    private ViewPager mViewPager;

    private int currentViewPagerItem;

    //是否自动播放
    private boolean isAutoPlay;

    private MyHandler mHandler;

    private Thread mThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carousel);

        // 轮播处理
        mHandler = new MyHandler(this);

        imageUrls.add("https://yun.xiaojiangjiakao.com/upload/admin/20180710/dff89b8512d47c57b1673d0b86017d1b.jpg");
        imageUrls.add("https://yun.xiaojiangjiakao.com/upload/admin/20180710/d4626ed0f35396945a48f4e4cece3f8a.jpg");
        imageUrls.add("https://yun.xiaojiangjiakao.com/upload/admin/20180710/7611e9ae1ba6dfdef232ae09e84b214b.jpg");

        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        mViewPager.setPageMargin(40);
        pagerAdapter = new ExercisePagerAdapter(this, imageUrls);
        mViewPager.setAdapter(pagerAdapter);

        mViewPager.setOnTouchListener(this);

        mViewPager.addOnPageChangeListener(this);

        isAutoPlay = true;


        mViewPager.setPageTransformer(true, new AlphaPageTransformer());
        //定时发送消息
        mThread = new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    mHandler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(CircleViewPagerActivity.VIEW_PAGER_DELAY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };

        mThread.start();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        String[] effects = this.getResources().getStringArray(R.array.magic_effect);
//        for (String effect : effects)
//            menu.add(effect);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        String title = item.getTitle().toString();
//        mViewPager.setAdapter(pagerAdapter);
//
//        if ("RotateDown".equals(title)) {
//            mViewPager.setPageTransformer(true, new RotateDownPageTransformer());
//        } else if ("RotateUp".equals(title)) {
//            mViewPager.setPageTransformer(true, new RotateUpPageTransformer());
//        } else if ("RotateY".equals(title)) {
//            mViewPager.setPageTransformer(true, new RotateYTransformer(45));
//        } else if ("Standard".equals(title)) {
////            mViewPager.setClipChildren(false);
//            mViewPager.setPageTransformer(true, NonPageTransformer.INSTANCE);
//        } else if ("Alpha".equals(title)) {
////            mViewPager.setClipChildren(false);
//            mViewPager.setPageTransformer(true, new AlphaPageTransformer());
//        } else if ("ScaleIn".equals(title)) {
//            mViewPager.setPageTransformer(true, new ScaleInTransformer());
//        } else if ("RotateDown and Alpha".equals(title)) {
//            mViewPager.setPageTransformer(true, new RotateDownPageTransformer(new AlphaPageTransformer()));
//        } else if ("RotateDown and Alpha And ScaleIn".equals(title)) {
//            mViewPager.setPageTransformer(true, new RotateDownPageTransformer(new AlphaPageTransformer(new ScaleInTransformer())));
//        }
//
//        setTitle(title);
//
//        return true;
//    }


    ///////////////////////////////////////////////////////////////////////////
    // ViewPager的监听事件
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        currentViewPagerItem = position;
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
        private WeakReference<CarouselActivity> mWeakReference;

        public MyHandler(CarouselActivity activity) {
            mWeakReference = new WeakReference<CarouselActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    CarouselActivity activity = mWeakReference.get();
                    if (activity.isAutoPlay) {
                        activity.mViewPager.setCurrentItem(++activity.currentViewPagerItem);
                    }

                    break;
            }

        }
    }


    class ExercisePagerAdapter extends PagerAdapter {

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public void startUpdate(ViewGroup container) {
            super.startUpdate(container);
            ViewPager viewPager = (ViewPager) container;
            int position = viewPager.getCurrentItem();
            if (position == 0) {
                position = getFirstItemPosition();
            } else if (position == getCount() - 1) {
                position = getLastItemPosition();
            }
            viewPager.setCurrentItem(position, false);

        }

        private int getRealCount() {
            return topicBeans.size();
        }

        private int getRealPosition(int position) {
            return position % getRealCount();
        }

        private int getFirstItemPosition() {
            return Integer.MAX_VALUE / getRealCount() / 2 * getRealCount();
        }

        private int getLastItemPosition() {
            return Integer.MAX_VALUE / getRealCount() / 2 * getRealCount() - 1;
        }

        private LinkedList<View> mViewCache = null;
        private List<String> topicBeans;
        private Context mContext;


        public ExercisePagerAdapter(Context context, List<String> topicBeans) {
            mViewCache = new LinkedList<>();
            this.topicBeans = topicBeans;
            this.mContext = context;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ViewHolder holder = null;
            View convertView = null;
            if (mViewCache.size() == 0) {
                convertView = View.inflate(mContext, R.layout.item_iv, null);
                holder = new ViewHolder();
                holder.ivItemExerciseOne = (ImageView) convertView.findViewById(R.id.iv_item_exercise_one);
                convertView.setTag(holder);
            } else {
                convertView = mViewCache.removeFirst();
                holder = (ViewHolder) convertView.getTag();
            }

            final int realPosition = getRealPosition(position);
            // 初始化viewholder
            Glide.with(mContext).load(topicBeans.get(realPosition))
                    .dontAnimate()
                    .centerCrop()
                    .into(holder.ivItemExerciseOne);
            holder.ivItemExerciseOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtil.e("点击事件  " + position);
                    UIUtil.toast("点击事件  " + position);
                }
            });

            container.addView(convertView);
            return convertView;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            mViewCache.add((View) object);
        }

        /**
         * ViewHolder
         */
        public final class ViewHolder {
            ImageView ivItemExerciseOne;
        }
    }

}
