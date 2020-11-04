package com.fpp.status.transformer;

import android.annotation.TargetApi;
import android.os.Build;
<<<<<<< HEAD
import android.view.View;

import androidx.viewpager.widget.ViewPager;

public class AlphaPageTransformer extends BasePageTransformer {
    private static final float DEFAULT_MIN_ALPHA = 0.5f;
    private float mMinAlpha = DEFAULT_MIN_ALPHA;

    public AlphaPageTransformer() {
    }

    public AlphaPageTransformer(float minAlpha) {
        this(minAlpha, NonPageTransformer.INSTANCE);
    }

    public AlphaPageTransformer(ViewPager.PageTransformer pageTransformer) {
        this(DEFAULT_MIN_ALPHA, pageTransformer);
    }

    public AlphaPageTransformer(float minAlpha, ViewPager.PageTransformer pageTransformer) {
=======
import android.support.v4.view.ViewPager;
import android.view.View;

public class AlphaPageTransformer extends BasePageTransformer
{
    private static final float DEFAULT_MIN_ALPHA = 0.5f;
    private float mMinAlpha = DEFAULT_MIN_ALPHA;

    public AlphaPageTransformer()
    {
    }

    public AlphaPageTransformer(float minAlpha)
    {
        this(minAlpha, NonPageTransformer.INSTANCE);
    }

    public AlphaPageTransformer(ViewPager.PageTransformer pageTransformer)
    {
        this(DEFAULT_MIN_ALPHA, pageTransformer);
    }

    public AlphaPageTransformer(float minAlpha, ViewPager.PageTransformer pageTransformer)
    {
>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c
        mMinAlpha = minAlpha;
        mPageTransformer = pageTransformer;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
<<<<<<< HEAD
    public void pageTransform(View view, float position) {
        view.setScaleX(0.999f);//hack

        if (position < -1) { // [-Infinity,-1)
            view.setAlpha(mMinAlpha);
        } else if (position <= 1) { // [-1,1]
=======
    public void pageTransform(View view, float position)
    {
        view.setScaleX( 0.999f);//hack

        if (position < -1)
        { // [-Infinity,-1)
            view.setAlpha(mMinAlpha);
        } else if (position <= 1)
        { // [-1,1]
>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c

            if (position < 0) //[0，-1]
            {           //[1,min]
                float factor = mMinAlpha + (1 - mMinAlpha) * (1 + position);
                view.setAlpha(factor);
            } else//[1，0]
            {
                //[min,1]
                float factor = mMinAlpha + (1 - mMinAlpha) * (1 - position);
                view.setAlpha(factor);
            }
<<<<<<< HEAD
        } else { // (1,+Infinity]
=======
        } else
        { // (1,+Infinity]
>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c
            view.setAlpha(mMinAlpha);
        }
    }
}
