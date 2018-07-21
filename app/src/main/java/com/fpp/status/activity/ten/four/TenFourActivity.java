package com.fpp.status.activity.ten.four;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fpp.status.R;
import com.fpp.status.activity.ten.three.GlideImageLoader;
import com.fpp.status.utils.LogUtil;
import com.fpp.status.utils.ToastUtils;
import com.fpp.status.view.RoundImageView;
import com.fpp.status.view.banner.Banner;
import com.fpp.status.view.banner.BannerConfig;
import com.fpp.status.view.banner.BannerScroller;
import com.fpp.status.view.banner.WeakHandler;
import com.fpp.status.view.banner.listener.OnBannerListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:
 * Author: fpp
 * Date: 2018/7/13  16:50
 */

public class TenFourActivity extends AppCompatActivity implements OnBannerListener {
    Banner bannerFragmentExamSubjectOne;
    @BindView(R.id.bvp_atvt_ten_three)
    ViewPager bvpAtvtTenThree;
    @BindView(R.id.riv)
    RoundImageView riv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ten_three);
        ButterKnife.bind(this);

        bannerFragmentExamSubjectOne = (Banner) findViewById(R.id.banner);
        List<String> images = new ArrayList<>();
        images.add("https://yun.xiaojiangjiakao.com/upload/admin/20180710/dff89b8512d47c57b1673d0b86017d1b.jpg");
        images.add("https://yun.xiaojiangjiakao.com/upload/admin/20180710/d4626ed0f35396945a48f4e4cece3f8a.jpg");
        images.add("https://yun.xiaojiangjiakao.com/upload/admin/20180710/7611e9ae1ba6dfdef232ae09e84b214b.jpg");
//        Glide.with(getBaseContext()).load(images.get(0)).dontAnimate()
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
//                .centerCrop()
//                .into(riv);
//        riv.setBackgroundResource(R.drawable.shilipic);
        riv.setImageResource(R.drawable.nvshengtouxiang);

        //设置banner样式
        bannerFragmentExamSubjectOne.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        bannerFragmentExamSubjectOne.setImageLoader(new GlideImageLoader());
        //设置图片集合
        bannerFragmentExamSubjectOne.setImages(images);

        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(null);

        bannerFragmentExamSubjectOne.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ToastUtils.showLong(TenFourActivity.this, "position = " + position);
            }
        });
        //banner设置方法全部调用完毕时最后调用
        bannerFragmentExamSubjectOne.start();

        // TODO: 2018/7/16 viewpager实现
        initViewPager();

    }


    private int currentItem;
    private int count = 0;

    private ViewPager.OnPageChangeListener mOnPageChangeListener;

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        mOnPageChangeListener = onPageChangeListener;
    }

    /**
     * 返回真实的位置
     *
     * @param position
     * @return 下标从0开始
     */
    public int toRealPosition(int position) {
        int realPosition = (position - 1) % count;
        if (realPosition < 0)
            realPosition += count;
        return realPosition;
    }


    boolean isAutoPlay = true;

    public void initViewPager() {

        List<String> pics = new ArrayList<>();
        pics.add("https://yun.xiaojiangjiakao.com/upload/admin/20180710/7611e9ae1ba6dfdef232ae09e84b214b.jpg");
        pics.add("https://yun.xiaojiangjiakao.com/upload/admin/20180710/dff89b8512d47c57b1673d0b86017d1b.jpg");
        pics.add("https://yun.xiaojiangjiakao.com/upload/admin/20180710/d4626ed0f35396945a48f4e4cece3f8a.jpg");
        pics.add("https://yun.xiaojiangjiakao.com/upload/admin/20180710/7611e9ae1ba6dfdef232ae09e84b214b.jpg");
        pics.add("https://yun.xiaojiangjiakao.com/upload/admin/20180710/dff89b8512d47c57b1673d0b86017d1b.jpg");
        VPAdapter vpAdapter = new VPAdapter(this, pics);
        bvpAtvtTenThree.setAdapter(vpAdapter);
        bvpAtvtTenThree.setPageMargin(40);
        currentItem = 1;
        count = 3;
        bvpAtvtTenThree.setCurrentItem(1);
        bvpAtvtTenThree.setFocusable(true);
//        try {
//            bvpAtvtTenThree.setPageTransformer(true, transformer.newInstance());
//        } catch (Exception e) {
//            Log.e("", "Please set the PageTransformer class");
//        }

//        initViewPagerScroll();
        bvpAtvtTenThree.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                LogUtil.e("onScrollChange  "
//                        + "  v = " + v
//                        + "    scrollX = " + scrollX
//                        + "    scrollY = " + scrollY
//                        + "    oldScrollX = " + oldScrollX
//                        + "    oldScrollY = " + oldScrollY
//                );
            }
        });
        bvpAtvtTenThree.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                LogUtil.e("onPageScrolled  "
//                        + "  position = " + position
//                        + "    positionOffset = " + positionOffset
//                        + "    positionOffsetPixels = " + positionOffsetPixels
//                );
                if (mOnPageChangeListener != null) {
                    mOnPageChangeListener.onPageScrolled(toRealPosition(position), positionOffset, positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                LogUtil.e("onPageSelected  "
                        + "  position = " + position
                        + "   currentItem = " + currentItem
                        + "   count = " + count
                );

                currentItem = position;
                if (mOnPageChangeListener != null) {
                    mOnPageChangeListener.onPageSelected(toRealPosition(position));
                }

                if (position == 0) position = count;
                if (position > count) position = 1;
            }

            /**
             * 当前位置
             *
             * @param state
             */
            @Override
            public void onPageScrollStateChanged(int state) {
                LogUtil.e("onPageScrollStateChanged  "
                        + "  state = " + state
                        + "   currentItem = " + currentItem
                        + "   count = " + count
                );
//                if (mOnPageChangeListener != null) {
//                    mOnPageChangeListener.onPageScrollStateChanged(state);
//                }
                switch (state) {
                    case 0://No operation
                        if (currentItem == 0) {
                            bvpAtvtTenThree.setCurrentItem(count, false);
                        } else if (currentItem == count + 1) {
                            bvpAtvtTenThree.setCurrentItem(1, false);
                        }
                        break;
                    case 1://start Sliding
                        if (currentItem == count + 1) {
                            bvpAtvtTenThree.setCurrentItem(1, false);
                        } else if (currentItem == 0) {
                            bvpAtvtTenThree.setCurrentItem(count, false);
                        }
                        break;
                    case 2://end Sliding
                        break;
                }
            }
        });
        // 是否自动轮播
        if (isAutoPlay) {
            startAutoPlay();
        }

    }


    WeakHandler handler = new WeakHandler();
    private int delayTime = BannerConfig.TIME;

    private void startAutoPlay() {
        handler.removeCallbacks(task);
        handler.postDelayed(task, delayTime);
    }

    /**
     *
     */
    private final Runnable task = new Runnable() {
        @Override
        public void run() {
            LogUtil.e("task  run  " + "开始新的轮播任务"
                    + "   task = " + task.hashCode()
            );

            if (count > 1 && isAutoPlay) {
                currentItem = currentItem % (count + 1) + 1;
//                Log.i(tag, "curr:" + currentItem + " count:" + count);
                if (currentItem == 1) {
                    bvpAtvtTenThree.setCurrentItem(currentItem, false);
                    LogUtil.e("task  run  " + "添加轮播任务到消息队列中去"
                            + "   task = " + task.hashCode()
                    );
                    handler.post(task);
                } else {
                    LogUtil.e("task  run  " + "添加轮播任务到消息队列中去（根据已有的轮播下标添加轮播任务，" +
                            "并设置在 指定的时间之后开始  ）"
                            + "   task = " + task.hashCode()
                            + "   delayTime = " + delayTime
                    );
                    bvpAtvtTenThree.setCurrentItem(currentItem);
                    handler.postDelayed(task, delayTime);
                }
            }
        }
    };


    private int scrollTime = BannerConfig.DURATION;
    private BannerScroller mScroller;

    private void initViewPagerScroll() {

        LogUtil.e("initViewPagerScroll + " + " ");
        try {
            Field mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);
            mScroller = new BannerScroller(this);
            mScroller.setDuration(scrollTime);
            mField.set(bvpAtvtTenThree, mScroller);
        } catch (Exception e) {
            LogUtil.e("  ee = " + e.getMessage());
        }
    }

    @Override
    public void OnBannerClick(int position) {
        ToastUtils.showLong(this, "position = " + position);
    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        bannerFragmentExamSubjectOne.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        bannerFragmentExamSubjectOne.stopAutoPlay();
    }

    /**
     * viewpager适配器
     */
    class VPAdapter extends PagerAdapter {
        private LinkedList<View> mViewCache = null;
        private List<String> topicBeans;
        private Context mContext;

        public VPAdapter(Context context, List<String> topicBeans) {
            mViewCache = new LinkedList<>();
            this.topicBeans = topicBeans;
            this.mContext = context;
        }


        @Override
        public int getCount() {
            return topicBeans == null ? 0 : topicBeans.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ViewHolder holder = null;
            View convertView = null;
            if (mViewCache.size() == 0) {
                convertView = View.inflate(mContext, R.layout.item_vp_iv_round, null);
                holder = new ViewHolder();
                holder.ivItemExerciseOne = (ImageView) convertView.findViewById(R.id.iv_item_vp_iv_round);
                convertView.setTag(holder);
            } else {
                convertView = mViewCache.removeFirst();
                holder = (ViewHolder) convertView.getTag();
            }
            Glide.with(getBaseContext()).load(topicBeans.get(position)).dontAnimate()
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .centerCrop()
                    .into(holder.ivItemExerciseOne);

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

