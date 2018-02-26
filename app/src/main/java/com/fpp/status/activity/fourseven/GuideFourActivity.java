package com.fpp.status.activity.fourseven;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.fpp.status.R;
import com.fpp.status.activity.popupwindow.DepthPageTransformer;
import com.fpp.status.activity.popupwindow.LoginActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @description  引导页----四
 * @author  fupengpeng
 * @date  2018/2/25 0025 16:10
 */

public class GuideFourActivity extends Activity {


    SharedPreferences preference;
    String PREFRENCE_NAME = "prefrence_setting";//SharedPreferences方法参数

    private ViewPager vpGuide;
    private Button btnStart;
    private LinearLayout llIndicator;
    private ImageView ivIndicator;

    private List<ImageView> imgs;
    private int[] imgIds = new int[] { R.drawable.meinv04, R.drawable.meinv05,
            R.drawable.meinv06 };

    private int pointDis; // 指示器的间距

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 去掉标题，在setContentView之前设置
        setContentView(R.layout.activity_guide_four);

        initVeiw();

        // TODO: 2018/2/26 0026 判断是否是第一次进入app，不是，则不再展示导航界面，直接进入登陆界面
        if (!getData()) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        initData();
        initViewPager();

    }

    private void initViewPager() {
        vpGuide.setAdapter(new MyPagerAdapter(imgs));
        vpGuide.setPageTransformer(true,new DepthPageTransformer());

        // 计算两个圆点之间的距离
        // measure-->layout(确定位置)-->draw（onCreate方法执行结束后才会执行此流程）
        // 视图树：监听layout方法结束的事件，位置确定好之后再获取圆点的间距
        ivIndicator.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    // layout方法执行后回调此方法
                    @Override
                    public void onGlobalLayout() {
                        pointDis = llIndicator.getChildAt(1).getLeft()
                                - llIndicator.getChildAt(0).getLeft();
                        // 移除，避免重复回调
                        ivIndicator.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                });

        vpGuide.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position == imgs.size() - 1) {
                    btnStart.setVisibility(View.VISIBLE);
                    AlphaAnimation anim = new AlphaAnimation(0, 1);
                    anim.setDuration(1);
                    btnStart.setAnimation(anim);
                }else{
                    btnStart.setVisibility(View.INVISIBLE);
                }
            }

            // position：当前位置；positionOffset：偏移量百分比；positionOffsetPixels：偏移量像素
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                int des = (int) (pointDis * positionOffset) + position
                        * pointDis;
                RelativeLayout.LayoutParams params =
                        (RelativeLayout.LayoutParams) ivIndicator
                        .getLayoutParams();
                params.setMargins(des,0,0,0);
//                params.leftMargin = des;
                ivIndicator.setLayoutParams(params);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        // 点击按钮，进入主Activity
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuideFourActivity.this,
                        LoginActivity.class);
                startActivity(intent);

                finish(); // 销毁当前Activity
            }
        });
    }

    private void initVeiw() {
        vpGuide = (ViewPager) this.findViewById(R.id.vp_guide);
        btnStart = (Button) this.findViewById(R.id.btn_start);
        btnStart.setVisibility(View.INVISIBLE);
        llIndicator = (LinearLayout) this.findViewById(R.id.ll_indicator);
        ivIndicator = (ImageView) this.findViewById(R.id.iv_indicator_selected);
    }

    //初始化数据
    private void initData() {
        imgs = new ArrayList<ImageView>();
        for (int i = 0; i < imgIds.length; i++) {
            ImageView img = new ImageView(this);
            img.setBackgroundResource(imgIds[i]);
            imgs.add(img);

            // 初始化指示器
            ImageView indicator = new ImageView(this);
            indicator.setImageResource(R.drawable.indicator_select);
            // 设置左边距
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            if (i > 0) { // 从第二个开始
                params.leftMargin = 10;
            }
            llIndicator.addView(indicator, params);
        }
    }


    /**
     * 操作SharedPreferences中的数据
     */
    private boolean getData() {
		/*
		 * 1.拿到SharedPreferences的对象
		 *     getSharedPreferences是ContextWrapper
		 *
		 *     name:文件名
		 *     mode:访问以及读写操作权限
		 * 2.读数据
		 */
		/*
		 *第一次进来读取数据，如果没有东西
		 */
        preference = getSharedPreferences(PREFRENCE_NAME, MODE_PRIVATE);
		/*
		 * key：所要读的数据对应的key值        dif
		 */
        boolean isFirst = preference.getBoolean("is_first", true);//拿到值
        return isFirst;
    }

    class MyPagerAdapter extends PagerAdapter {
        private List<ImageView> imgs;

        public MyPagerAdapter(List<ImageView> imgs) {
            this.imgs = imgs;
        }

        // item的个数
        @Override
        public int getCount() {
            return imgs.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        // 初始化item布局
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = imgs.get(position);
            container.addView(view);
            return view;
        }

        // 销毁item
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imgs.get(position));
        }

    }

}
