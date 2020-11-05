package com.fpp.status.activity.ten.three;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.fpp.status.R;
import com.fpp.status.view.banner.loader.ImageLoader;

import java.security.MessageDigest;

import androidx.annotation.NonNull;

/**
 * Created by Jelly on 2018/5/29.
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        /**
         注意：
         1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
         2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
         传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
         切记不要胡乱强转！
         */

        //Glide 加载图片简单用法
        Glide.with(context)
                .load(path)
                .placeholder(R.mipmap.default_banner)
                .error(R.mipmap.default_banner)
//                .transform(new GlideCircleTransform(context,GlideImageLoaderConfig.CIRCULAR_BEAD,5))
                .into(imageView);

    }


    final static class GlideImageLoaderConfig{
        public static final int CIRCULAR_BEAD = 100001;
        public static final int ROUND = 100002;
    }
}
