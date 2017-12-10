package com.fpp.status.activity.fragmentone;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.fpp.status.R;
import com.fpp.status.activity.fragmentone.adapter.ViewPagerFragmentAdapter;
import com.fpp.status.activity.fragmentone.fragment.ChatFragment;
import com.fpp.status.activity.fragmentone.fragment.FindFragment;
import com.fpp.status.activity.fragmentone.fragment.FriendFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment   viewpager 联合使用
 */

public class OneFragmentActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity.TAG";
    public LinearLayout firstLinearLayout;
    public LinearLayout secondLinearLayout;
    public LinearLayout threeLinearLayout;
    ViewPager mViewPager;
    ViewPagerFragmentAdapter mViewPagerFragmentAdapter;
    FragmentManager mFragmentManager;
    List<Fragment> mFragmentList = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getSupportFragmentManager();
        setContentView(R.layout.activity_one_fragment);
        initFragmetList();
        mViewPagerFragmentAdapter = new ViewPagerFragmentAdapter(mFragmentManager, mFragmentList);
        initView();
        initViewPager();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void initViewPager() {
        mViewPager.addOnPageChangeListener(new ViewPagetOnPagerChangedLisenter());
        mViewPager.setAdapter(mViewPagerFragmentAdapter);
        mViewPager.setCurrentItem(0);
        updateBottomLinearLayoutSelect(true, false, false);
    }

    public void initFragmetList() {
        Fragment chat = new ChatFragment();
        Fragment friend = new FriendFragment();
        Fragment find = new FindFragment();
        mFragmentList.add(chat);
        mFragmentList.add(friend);
        mFragmentList.add(find);
    }

    public void initView() {
        mViewPager = (ViewPager) findViewById(R.id.ViewPagerLayout);
        firstLinearLayout = (LinearLayout) findViewById(R.id.firstLinearLayout);
        firstLinearLayout.setOnClickListener(this);
        secondLinearLayout = (LinearLayout) findViewById(R.id.secondLinearLayout);
        secondLinearLayout.setOnClickListener(this);
        threeLinearLayout = (LinearLayout) findViewById(R.id.threeLinearLayout);
        threeLinearLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.firstLinearLayout:
                mViewPager.setCurrentItem(0);
                updateBottomLinearLayoutSelect(true, false, false);
                break;
            case R.id.secondLinearLayout:
                mViewPager.setCurrentItem(1);
                updateBottomLinearLayoutSelect(false, true, false);
                break;
            case R.id.threeLinearLayout:
                mViewPager.setCurrentItem(2);
                updateBottomLinearLayoutSelect(false, false, true);
                break;
            default:
                break;
        }
    }

    private void updateBottomLinearLayoutSelect(boolean f, boolean s, boolean t) {
        firstLinearLayout.setSelected(f);
        secondLinearLayout.setSelected(s);
        threeLinearLayout.setSelected(t);
    }

    class ViewPagetOnPagerChangedLisenter implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //            Log.d(TAG,"onPageScrooled");
        }

        @Override
        public void onPageSelected(int position) {
            Log.e(TAG, "onPageSelected");
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            Log.e(TAG, "onPageScrollStateChanged");
        }
    }
}