package com.fpp.status.activity.twotwo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author fupengpeng
 * @description 描述
 * @data 2018/2/24 0024 11:42
 */

public class MyAdapter  extends FragmentPagerAdapter {

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
