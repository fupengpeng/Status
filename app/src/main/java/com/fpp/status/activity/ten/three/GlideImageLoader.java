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


    public class GlideCircleTransform extends BitmapTransformation {

        private int mImageType;
        private int mBorderRadius;
        public GlideCircleTransform(Context context,int imageType,int borderRadius) {
            mImageType = imageType;
            mBorderRadius = borderRadius;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform
                , int outWidth, int outHeight) {
            if (mImageType == GlideImageLoaderConfig.CIRCULAR_BEAD){
                return circularBead(pool , toTransform);
            }else {
                return round(pool, toTransform);
            }
        }

        private Bitmap circularBead(BitmapPool pool, Bitmap toTransform) {
            if (toTransform == null) return null;
            int size = Math.min(toTransform.getWidth(), toTransform.getHeight());

            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(getShader(toTransform));
            paint.setAntiAlias(true);
            // 圆角范围
            RectF rectF= new RectF(0, 0, toTransform.getWidth(), toTransform.getHeight());
            canvas.drawRoundRect(rectF, mBorderRadius, mBorderRadius, paint);
            return result;
        }

        private BitmapShader getShader(Bitmap toTransform) {
            // 缩放矩阵
            Matrix matrix = new Matrix();
            // 渲染器,使用图片填充形状
            BitmapShader bitmapShader = new BitmapShader(toTransform, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            float scale = 1.0f;
            scale = Math.max(
                    toTransform.getWidth() * 1.0f / toTransform.getWidth()
                    , toTransform.getHeight() * 1.0f / toTransform.getHeight());

            matrix.setScale(scale, scale);
            bitmapShader.setLocalMatrix(matrix);

            return bitmapShader;
        }
        private Bitmap round(BitmapPool pool, Bitmap source) {
            if (source == null) return null;
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            // TODO this could be acquired from the pool too
            Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);
            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return result;
        }

        public String getId() {
            return getClass().getName();
        }


        @Override
        public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

        }
    }
    final static class GlideImageLoaderConfig{
        public static final int CIRCULAR_BEAD = 100001;
        public static final int ROUND = 100002;
    }
}
