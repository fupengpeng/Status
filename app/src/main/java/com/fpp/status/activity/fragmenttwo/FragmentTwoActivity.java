package com.fpp.status.activity.fragmenttwo;

import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fpp.status.R;
import com.fpp.status.activity.fragmenttwo.fragment.TwoOneFragment;
import com.fpp.status.activity.fragmenttwo.fragment.TwoThreeFragment;
import com.fpp.status.activity.fragmenttwo.fragment.TwoTwoFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by fp on 2017/12/10.
 */

public class FragmentTwoActivity extends AppCompatActivity implements View.OnClickListener {
    String TAG = "FragmentTwoActivity";

    private ViewPager viewPager;// 声明一个viewpager对象
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private ImageView tabline;
    private List<Fragment> list;// 声明一个list集合存放Fragment（数据源）

    private int tabLineLength;// 1/3屏幕宽
    private int currentPage = 0;// 初始化当前页为0（第一页）

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(
                Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_fragment_two);
        // 初始化滑动条1/3
        initTabLine();

        // 初始化界面
        initView();
    }

    private void initTabLine() {
        // 获取显示屏信息
        Display display = getWindow().getWindowManager().getDefaultDisplay();
        // 得到显示屏宽度
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        // 1/3屏幕宽度
        tabLineLength = metrics.widthPixels / 3;
        // 获取控件实例
        tabline = (ImageView) findViewById(R.id.tabline);
        // 控件参数
        ViewGroup.LayoutParams lp = tabline.getLayoutParams();
        lp.width = tabLineLength;
        tabline.setLayoutParams(lp);
    }

    private void initView() {
        // 实例化对象
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);

        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);

        list = new ArrayList<Fragment>();

        // 设置数据源
        TwoOneFragment twoOneFragment = new TwoOneFragment();
        TwoTwoFragment twoTwoFragment = new TwoTwoFragment();
        TwoThreeFragment twoThreeFragment = new TwoThreeFragment();

        list.add(twoOneFragment);
        list.add(twoTwoFragment);
        list.add(twoThreeFragment);

        // 设置适配器
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return list.get(arg0);
            }
        };

        // 绑定适配器
        viewPager.setAdapter(adapter);

        // 设置滑动监听
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // 当页面被选择时，先讲3个textview的字体颜色初始化成黑
                tv1.setTextColor(Color.BLACK);
                tv2.setTextColor(Color.BLACK);
                tv3.setTextColor(Color.BLACK);

                // 再改变当前选择页（position）对应的textview颜色
                switch (position) {
                    case 0:
                        tv1.setTextColor(Color.rgb(51, 153, 0));
                        break;
                    case 1:
                        tv2.setTextColor(Color.rgb(51, 153, 0));
                        break;
                    case 2:
                        tv3.setTextColor(Color.rgb(51, 153, 0));
                        break;
                }

                currentPage = position;

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                Log.i("tuzi", arg0 + "," + arg1 + "," + arg2);

                // 取得该控件的实例
                LinearLayout.LayoutParams ll = (android.widget.LinearLayout.LayoutParams) tabline
                        .getLayoutParams();

                if (currentPage == 0 && arg0 == 0) { // 0->1移动(第一页到第二页)
                    ll.leftMargin = (int) (currentPage * tabLineLength + arg1 * tabLineLength);
                } else if (currentPage == 1 && arg0 == 1) { // 1->2移动（第二页到第三页）
                    ll.leftMargin = (int) (currentPage * tabLineLength + arg1 * tabLineLength);
                } else if (currentPage == 1 && arg0 == 0) { // 1->0移动（第二页到第一页）
                    ll.leftMargin = (int) (currentPage * tabLineLength - ((1 - arg1) * tabLineLength));
                } else if (currentPage == 2 && arg0 == 1) { // 2->1移动（第三页到第二页）
                    ll.leftMargin = (int) (currentPage * tabLineLength - (1 - arg1) * tabLineLength);
                }

                tabline.setLayoutParams(ll);

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });

        //设置点击页面变化


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv1:
                changeView(0);
                break;
            case R.id.tv2:
                changeView(1);
                break;

            case R.id.tv3:
                changeView(2);
                break;



        }

    }
    //手动设置ViewPager要显示的视图
    private void changeView(int desTab)
    {
        Log.e(TAG, "changeView: " + " ---------");
        viewPager.setCurrentItem(desTab, true);
    }
}