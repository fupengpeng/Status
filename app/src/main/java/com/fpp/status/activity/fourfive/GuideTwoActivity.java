package com.fpp.status.activity.fourfive;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.fpp.status.MainActivity;
import com.fpp.status.R;
import com.fpp.status.activity.popupwindow.DepthPageTransformer;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

/**
 * @description  引导页----二
 * @author  fupengpeng
 * @date  2018/2/25 0025 15:20
 */
public class GuideTwoActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    /**
     * 图片资源id
     */
    public int[] mPicture = {R.drawable.meinv04, R.drawable.meinv05, R.drawable.meinv06, R.drawable.meinv07};
    ImageView mTvFristPoint;//第一个点
    ImageView mTvSecondPoint;//第二个点
    ImageView mTvThridPoint;//第三个点
    ImageView mTvFourPoint;//第四个点

    RelativeLayout mRelativeLayout;
    Button mBtnPass;
    ImageView[] mTvArray = new ImageView[4];
    String PREFRENCE_NAME = "prefrence_setting";//SharedPreferences方法参数
    SharedPreferences preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        /**
         * 1.找到控件
         * 2.数据源
         * 3.拿到适配器
         * 4.适配器和控件绑定       setAdapter
         */
        setContentView(R.layout.activity_guide_two);//加载布局

        initView();

        mRelativeLayout.setBackgroundColor(getResources().getColor(R.color.btn_background));
        if (!getData()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
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

    /**
     * 初始化控件和绑定事件
     */
    protected void initView() {
        //1.初始化控件
        ViewPager pager = (ViewPager) findViewById(R.id.vp_guide);
        mTvFristPoint = (ImageView) findViewById(R.id.iv_guide_first);
        mTvSecondPoint = (ImageView) findViewById(R.id.iv_guide_second);
        mTvThridPoint = (ImageView) findViewById(R.id.iv_guide_thrid);
        mTvFourPoint = (ImageView) findViewById(R.id.iv_guide_four);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.rl_guide_background);

        mTvArray[0] = mTvFristPoint;
        mTvArray[1] = mTvSecondPoint;
        mTvArray[2] = mTvThridPoint;
        mTvArray[3] = mTvFourPoint;
        mBtnPass = (Button) findViewById(R.id.btn_guide_pass);
        //2.数据源
        ArrayList<ImageView> listImg = new ArrayList<ImageView>();
        for (int i = 0; i < mPicture.length; i++) {
            ImageView img = new ImageView(this);
            img.setImageResource(mPicture[i]);
            listImg.add(img);
        }
        GuidePagerAdapter adapter = new GuidePagerAdapter(listImg);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(this);//监听页面切换
        mBtnPass.setOnClickListener(this);

        pager.setPageTransformer(true,new DepthPageTransformer());
    }

    /**
     * 页面滚动状态的一个监听
     * 1    滑动
     * 2    弹性运动    复位
     * 0    停止
     */
    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    /**
     * arg0      所操作页面的下标
     * arg1      所滑动的偏移百分比[0,1);
     * arg2      滑动的偏移像素
     */
    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    /**
     * 所选择的页面
     * 切换图片
     */
    @Override
    public void onPageSelected(int arg0) {
        for (ImageView tv : mTvArray) {
            mBtnPass.setVisibility(View.GONE);
            tv.setImageResource(R.drawable.indicator_select);
        }
        mTvArray[arg0].setImageResource(R.drawable.indicator_no_select);
        if (arg0 == 0) {
            mRelativeLayout.setBackgroundColor(getResources().getColor(R.color.btn_background));
        }
        if (arg0 == 1) {
            mRelativeLayout.setBackgroundColor(getResources().getColor(R.color.btn_background));
        }
        if (arg0 == 2) {
            mRelativeLayout.setBackgroundColor(getResources().getColor(R.color.btn_background));
        }
        if (arg0 == 3) {
            mRelativeLayout.setBackgroundColor(getResources().getColor(R.color.btn_background));
            mBtnPass.setVisibility(View.VISIBLE);
        }

    }

    /**
     * 第一次进行加载，之后就不需要加载
     * @param v
     */
    @Override
    public void onClick(View v) {

        SharedPreferences.Editor edt = preference.edit();
        edt.putBoolean("is_first", false);
        edt.commit();

        // 跳转
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}