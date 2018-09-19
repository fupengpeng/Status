package com.fpp.status.activity.eleven.three;

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
import android.widget.TextView;

import com.fpp.status.R;
import com.fpp.status.utils.Constant;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Description:  图表demo
 * Author: fpp
 * Date: 2018/9/19  9:32
 */

public class ChartActivity extends AppCompatActivity {

    @BindView(R.id.tl_aty_sms_add_student_title)
    TabLayout tlAtySmsAddStudentTitle;
    @BindView(R.id.vp_aty_sms_add_student_content)
    ViewPager vpAtySmsAddStudentContent;
    private ArrayList<Fragment> mFragmentList;
    private ArrayList<String> mTitleList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        ButterKnife.bind(this);

        setData();

    }
    private void setData() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(ChartOneFragment.newInstance(1));
        mTitleList = new ArrayList<>();
        mTitleList.add("科一");
//        mTitleList.add("科二)");
//        mTitleList.add("科三");
//        mTitleList.add("科四");

        // 设置缓存viewpager子界面数量（2：当前子界面前后各2个）
        vpAtySmsAddStudentContent.setOffscreenPageLimit(2);
        vpAtySmsAddStudentContent.setAdapter(new ExamFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList, mTitleList));
        tlAtySmsAddStudentTitle.setupWithViewPager(vpAtySmsAddStudentContent);

        reflex(tlAtySmsAddStudentTitle);
    }

    public void reflex(final TabLayout tabLayout) {
        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);

                    final float scale = getApplication().getResources().getDisplayMetrics().density;
                    int dp10 = (int) (18 * scale + 0.5f);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);
                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);
                        TextView mTextView = (TextView) mTextViewField.get(tabView);
                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }
                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width;
                        params.leftMargin = dp10;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * ViewPager适配器。
     */
    public class ExamFragmentPagerAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> list;
        private List<String> titles;

        public ExamFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list, List<String> titles) {
            super(fm);
            this.list = list;
            this.titles = titles;

        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Fragment getItem(int arg0) {
            return list.get(arg0);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }



}
