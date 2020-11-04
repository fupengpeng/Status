package com.fpp.status.transformer;

<<<<<<< HEAD
import android.view.View;

import androidx.viewpager.widget.ViewPager;

/**
 * Created by zhy on 16/5/7.
 */
public class NonPageTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {
=======
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by zhy on 16/5/7.
 */
public class NonPageTransformer implements ViewPager.PageTransformer
{
    @Override
    public void transformPage(View page, float position)
    {
>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c
        page.setScaleX(0.999f);//hack
    }

    public static final ViewPager.PageTransformer INSTANCE = new NonPageTransformer();
}
