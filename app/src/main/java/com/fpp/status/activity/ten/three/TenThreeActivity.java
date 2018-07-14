package com.fpp.status.activity.ten.three;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fpp.status.R;
import com.fpp.status.utils.ToastUtils;
import com.fpp.status.utils.UIUtil;
import com.fpp.status.view.banner.Banner;
import com.fpp.status.view.banner.BannerConfig;
import com.fpp.status.view.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author: fpp
 * Date: 2018/7/13  16:50
 */

public class TenThreeActivity  extends AppCompatActivity implements OnBannerListener {
    Banner bannerFragmentExamSubjectOne;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ten_three);

        bannerFragmentExamSubjectOne = (Banner) findViewById(R.id.banner);
        List<String> images = new ArrayList<>();
        images.add("https://yun.xiaojiangjiakao.com/upload/admin/20180710/dff89b8512d47c57b1673d0b86017d1b.jpg");
        images.add("https://yun.xiaojiangjiakao.com/upload/admin/20180710/d4626ed0f35396945a48f4e4cece3f8a.jpg");
        images.add("https://yun.xiaojiangjiakao.com/upload/admin/20180710/7611e9ae1ba6dfdef232ae09e84b214b.jpg");

        //设置banner样式
        bannerFragmentExamSubjectOne.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        bannerFragmentExamSubjectOne.setImageLoader(new GlideImageLoader());
        //设置图片集合
        bannerFragmentExamSubjectOne.setImages(images);

        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(null);

        bannerFragmentExamSubjectOne.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ToastUtils.showLong(TenThreeActivity.this,"position = " + position);
            }
        });
        //banner设置方法全部调用完毕时最后调用
        bannerFragmentExamSubjectOne.start();
    }

    @Override
    public void OnBannerClick(int position) {
        ToastUtils.showLong(this,"position = " + position);
    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        bannerFragmentExamSubjectOne.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        bannerFragmentExamSubjectOne.stopAutoPlay();
    }
}

