package com.fpp.status.transformer;

import android.annotation.TargetApi;
import android.os.Build;
<<<<<<< HEAD
import android.view.View;

import androidx.viewpager.widget.ViewPager;

=======
import android.support.v4.view.ViewPager;
import android.view.View;

>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c
/**
 * Created by zhy on 16/5/7.
 */
public abstract class BasePageTransformer implements ViewPager.PageTransformer
{
    protected ViewPager.PageTransformer mPageTransformer = NonPageTransformer.INSTANCE;
    public static final float DEFAULT_CENTER = 0.5f;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void transformPage(View view, float position)
    {
        if (mPageTransformer != null)
        {
            mPageTransformer.transformPage(view, position);
        }

        pageTransform(view, position);
    }

    protected abstract void pageTransform(View view, float position);


}
