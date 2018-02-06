package com.fpp.status.activity.four;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fpp.status.R;
import com.fpp.status.entity.LoadMemberListResponseData;
import com.fpp.status.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by fupengpeng on 2017/12/16 0016.
 */

public class FourThreeActivity extends AppCompatActivity {


    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.ll_iv_content)
    LinearLayout llIvContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_four_three);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        initViewPager();

    }


    //viewpager页面
    List<View> viewPagerContentList = new ArrayList<View>();
    //viewpager提示
    List<View> viewPagerTitleList = new ArrayList<View>();
    //viewpager数据
    List<LoadMemberListResponseData> loadMLRDataList = new ArrayList<LoadMemberListResponseData>();

    /**
     * 初始化view
     */
    private void initViewPager() {

        getData();

        for (int i = 0; i < loadMLRDataList.size(); i++) {
            //获取viewpager界面所要添加的布局view
            View vpView = View.inflate(this, R.layout.view_pager_item_four_three, null);
            //获取viewpager界面所要添加的布局view中的textview并设置数据
            TextView tv = (TextView) vpView.findViewById(R.id.tv_atvt_billing_select_member_name);
            tv.setText(loadMLRDataList.get(i).getTruename());
            //添加到viewpager的数据中
            viewPagerContentList.add(vpView);

            //获取提示的布局
            View vpViewIv = View.inflate(this, R.layout.view_pager_item_four_three_iv, null);


            viewPagerTitleList.add(vpViewIv);

        }

        initLinearLayout();

        final ViewPagerViewAdapter viewPagerViewAdapter = new ViewPagerViewAdapter(viewPagerContentList);

        viewpager.setAdapter(viewPagerViewAdapter);

        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {

                if (position == viewPagerTitleList.size()-1){
                    LogUtils.e("onPageSelected----position = " + position + "  等于size-1"  );
                    viewPagerTitleList.get(position - 1).findViewById(R.id.ll_iv).setBackgroundResource(R.drawable.yuan_jiao_select_iv_bai);
                }else if (position == 0){
                    LogUtils.e("onPageSelected----position = " + position + "  等于0+1"  );
                    viewPagerTitleList.get(position + 1).findViewById(R.id.ll_iv).setBackgroundResource(R.drawable.yuan_jiao_select_iv_bai);
                }else {
                    LogUtils.e("onPageSelected----position = " + position + "  不等于size"  );
                    viewPagerTitleList.get(position - 1).findViewById(R.id.ll_iv).setBackgroundResource(R.drawable.yuan_jiao_select_iv_bai);
                    viewPagerTitleList.get(position + 1).findViewById(R.id.ll_iv).setBackgroundResource(R.drawable.yuan_jiao_select_iv_bai);
                }
                viewPagerTitleList.get(position).findViewById(R.id.ll_iv).setBackgroundResource(R.drawable.yuan_jiao_select_iv_hui);

            }

            @Override
            public void onPageScrollStateChanged(int state) {



            }
        });


    }



    /**
     * 获取数据
     */
    private void getData() {
        for (int i = 0; i < 5; i++) {
            LoadMemberListResponseData loadMLRData = new LoadMemberListResponseData();
            loadMLRData.setShopname("店铺名" + i);
            loadMLRData.setTruename("昵称" + i);
            loadMLRData.setLevel(i + "");
            loadMLRData.setLevelname("星级" + i);
            loadMLRData.setFaceurl("头像" + i);
            loadMLRDataList.add(loadMLRData);
        }
    }

    /**
     * 提示view添加
     */
    private void initLinearLayout() {

        for (int i = 0; i < viewPagerTitleList.size(); i++) {
            if (i == 0 ){
                viewPagerTitleList.get(i).findViewById(R.id.ll_iv).setBackgroundResource(R.drawable.yuan_jiao_select_iv_hui);
                llIvContent.addView(viewPagerTitleList.get(i));
            }else {
                viewPagerTitleList.get(i).findViewById(R.id.ll_iv).setBackgroundResource(R.drawable.yuan_jiao_select_iv_bai);
                llIvContent.addView(viewPagerTitleList.get(i));
            }

        }


    }

    /**
     * viewpager适配器
     */
    class ViewPagerViewAdapter extends PagerAdapter {
        List<View> viewPagerContentList;

        public ViewPagerViewAdapter(List<View> viewPagerContentList) {
            this.viewPagerContentList = viewPagerContentList;
        }

        @Override
        public int getCount() {

            if (viewPagerContentList != null && viewPagerContentList.size() > 0) {
                return viewPagerContentList.size();
            } else {
                return 0;
            }
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewPagerContentList.get(position));
            return viewPagerContentList.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }


}
