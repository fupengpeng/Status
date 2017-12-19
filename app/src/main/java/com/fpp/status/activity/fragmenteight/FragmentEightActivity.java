package com.fpp.status.activity.fragmenteight;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.fpp.status.R;
import com.fpp.status.activity.fragmenteight.fragment.AllFragment;
import com.fpp.status.activity.fragmenteight.fragment.AlreadyAccomplishFragment;
import com.fpp.status.activity.fragmenteight.fragment.AlreadyCancelFragment;
import com.fpp.status.activity.fragmenteight.fragment.UnderwayFragment;
import com.fpp.status.activity.fragmenteight.fragment.WaitAcceptFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fupengpeng on 2017/12/16 0016.
 */

public class FragmentEightActivity extends AppCompatActivity {


    @BindView(R.id.id_tablayout)
    TabLayout idTablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_eight);
        ButterKnife.bind(this);

        initView();
    }


    private List<String> mTitle = new ArrayList<String>();   //定义一个ArrayList来存放标题
    private List<Fragment> mFragment = new ArrayList<Fragment>();  // 声明一个list集合存放Fragment（数据源）

    //初始化V
    private void initView() {

        mTitle.add("全部");
        mTitle.add("待接单");
        mTitle.add("进行中");
        mTitle.add("已完成");
        mTitle.add("已取消");


        // 设置viewpager数据源
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
