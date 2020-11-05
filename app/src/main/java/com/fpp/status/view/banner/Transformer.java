package com.fpp.status.view.banner;


import com.fpp.status.view.banner.transformer.DefaultTransformer;
import com.fpp.status.view.banner.transformer.AccordionTransformer;
import com.fpp.status.view.banner.transformer.BackgroundToForegroundTransformer;
import com.fpp.status.view.banner.transformer.CubeInTransformer;
import com.fpp.status.view.banner.transformer.CubeOutTransformer;
import com.fpp.status.view.banner.transformer.DepthPageTransformer;
import com.fpp.status.view.banner.transformer.FlipHorizontalTransformer;
import com.fpp.status.view.banner.transformer.FlipVerticalTransformer;
import com.fpp.status.view.banner.transformer.ForegroundToBackgroundTransformer;
import com.fpp.status.view.banner.transformer.RotateDownTransformer;
import com.fpp.status.view.banner.transformer.RotateUpTransformer;
import com.fpp.status.view.banner.transformer.ScaleInOutTransformer;
import com.fpp.status.view.banner.transformer.StackTransformer;
import com.fpp.status.view.banner.transformer.TabletTransformer;
import com.fpp.status.view.banner.transformer.ZoomInTransformer;
import com.fpp.status.view.banner.transformer.ZoomOutSlideTransformer;
import com.fpp.status.view.banner.transformer.ZoomOutTranformer;

import androidx.viewpager.widget.ViewPager;

public class Transformer {
    public static Class<? extends ViewPager.PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends ViewPager.PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;

}
