package com.fpp.status.activity.fragmenteight;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.fpp.status.R;
import com.fpp.status.activity.fragmenteight.fragment.AllFragment;
import com.fpp.status.activity.fragmenteight.fragment.AlreadyAccomplishFragment;
import com.fpp.status.activity.fragmenteight.fragment.AlreadyCancelFragment;
import com.fpp.status.activity.fragmenteight.fragment.UnderwayFragment;
import com.fpp.status.activity.fragmenteight.fragment.WaitAcceptFragment;
import com.fpp.status.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fupengpeng on 2017/12/16 0016.
 */

public class FragmentEightActivity extends AppCompatActivity {


    ArrayList<View> mViews;//定义一个ArrayList来存放view
    @BindView(R.id.id_tablayout)
    TabLayout idTablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private List<Fragment> list;// 声明一个list集合存放Fragment（数据源）
    private int currentPage = 0;// 初始化当前页为0（第一页）


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing_select);
        ButterKnife.bind(this);

//        init();

        initView();


    }


    /**
     * 初始化数据
     */
    private void init() {


        // 实例化对象
        list = new ArrayList<Fragment>();

        // 设置数据源
        AllFragment allFragment = new AllFragment();
        WaitAcceptFragment waitAcceptFragment = new WaitAcceptFragment();
        AlreadyAccomplishFragment alreadyAccomplishFragment = new AlreadyAccomplishFragment();
        AlreadyCancelFragment alreadyCancelFragment = new AlreadyCancelFragment();
        UnderwayFragment underwayFragment = new UnderwayFragment();

        list.add(allFragment);
        list.add(waitAcceptFragment);
        list.add(alreadyAccomplishFragment);
        list.add(alreadyCancelFragment);
        list.add(underwayFragment);


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
        viewpager.setAdapter(adapter);
        idTablayout.setupWithViewPager(viewpager);

        // 设置滑动监听
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

                currentPage = position;

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // 取得该控件的实例


            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
    }


    private List<String> mTitle = new ArrayList<String>();
    private List<Fragment> mFragment = new ArrayList<Fragment>();


    //初始化V
    private void initView() {

        mTitle.add("全部");
        mTitle.add("待接单");
        mTitle.add("进行中");
        mTitle.add("已完成");
        mTitle.add("已取消");


        // 设置数据源
        AllFragment allFragment = new AllFragment();
        WaitAcceptFragment waitAcceptFragment = new WaitAcceptFragment();
        AlreadyAccomplishFragment alreadyAccomplishFragment = new AlreadyAccomplishFragment();
        AlreadyCancelFragment alreadyCancelFragment = new AlreadyCancelFragment();
        UnderwayFragment underwayFragment = new UnderwayFragment();

        mFragment.add(allFragment);
        mFragment.add(waitAcceptFragment);
        mFragment.add(alreadyAccomplishFragment);
        mFragment.add(alreadyCancelFragment);
        mFragment.add(underwayFragment);


        MyAdapter adapter = new MyAdapter(getSupportFragmentManager(), mTitle, mFragment);
        viewpager.setAdapter(adapter);
        //为TabLayout设置ViewPager
        idTablayout.setupWithViewPager(viewpager);
        //使用ViewPager的适配器
        idTablayout.setTabsFromPagerAdapter(adapter);

    }

    class MyAdapter extends FragmentPagerAdapter {

        private List<String> title;
        private List<Fragment> views;

        public MyAdapter(FragmentManager fragmentManager, List<String> title, List<Fragment> views) {
            super(fragmentManager);
            this.title = title;
            this.views = views;
        }

        @Override
        public Fragment getItem(int position) {
            return views.get(position);
        }

        @Override
        public int getCount() {
            return views.size();
        }


        //配置标题的方法
        @Override
        public CharSequence getPageTitle(int position) {
            return title.get(position);
        }


    }
}
