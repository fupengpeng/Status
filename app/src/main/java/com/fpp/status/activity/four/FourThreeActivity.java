package com.fpp.status.activity.four;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.fpp.status.R;
import com.fpp.status.activity.fragmenteight.fragment.AllFragment;
import com.fpp.status.activity.fragmenteight.fragment.AlreadyAccomplishFragment;
import com.fpp.status.activity.fragmenteight.fragment.AlreadyCancelFragment;
import com.fpp.status.activity.fragmenteight.fragment.UnderwayFragment;
import com.fpp.status.activity.fragmenteight.fragment.WaitAcceptFragment;
import com.fpp.status.entity.LoadMemberListResponseData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fupengpeng on 2017/12/16 0016.
 */

public class FourThreeActivity extends AppCompatActivity {


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
        setContentView(R.layout.activity_four_three);
        ButterKnife.bind(this);

//        init();

//        initView();

        initViewPager();



    }


    List<View> viewPagerContentList = new ArrayList<View>();
    List<View> viewPagerTitleList = new ArrayList<View>();
    List<LoadMemberListResponseData> loadMLRDataList = new ArrayList<LoadMemberListResponseData>();
    private void initViewPager() {

        for (int i = 0; i < 10 ; i++) {
            LoadMemberListResponseData loadMLRData = new LoadMemberListResponseData();
            loadMLRData.setShopname("店铺名" + i);
            loadMLRData.setTruename("昵称"+i);
            loadMLRData.setLevel(i + "");
            loadMLRData.setLevelname("星级" + i);
            loadMLRData.setFaceurl("头像" + i);
            loadMLRDataList.add(loadMLRData);
        }

        for (int i = 0; i < loadMLRDataList.size(); i++) {
            View vpView = View.inflate(this,R.layout.view_pager_item_four_three,null);
            viewPagerContentList.add(vpView);
            View vpViewIv = View.inflate(this,R.layout.view_pager_item_four_three_iv,null);
            viewPagerTitleList.add(vpViewIv);
        }

        ViewPagerViewAdapter viewPagerViewAdapter = new ViewPagerViewAdapter(viewPagerContentList,viewPagerTitleList);

        viewpager.setAdapter(viewPagerViewAdapter);



    }
    class ViewPagerViewAdapter extends PagerAdapter{
        List<View> viewPagerContentList;
        List<View> viewPagerTitleList;

        public ViewPagerViewAdapter(List<View> viewPagerContentList, List<View> viewPagerTitleList) {
            this.viewPagerContentList = viewPagerContentList;
            this.viewPagerTitleList = viewPagerTitleList;
        }

        @Override
        public int getCount() {
            return viewPagerContentList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewPagerContentList.get(position));

            return viewPagerContentList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
            container.removeView(viewPagerContentList.get(position));

        }
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
