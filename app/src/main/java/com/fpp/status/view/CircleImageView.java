package com.fpp.status.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Description:
 * Author: fpp
 * Date: 2018/7/16  17:50
 */

public class CircleImageView extends ImageView {

    float width,height;

    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (width > 12 && height > 12) {
            Path path = new Path();
            path.moveTo(12, 0);
            path.lineTo(width - 12, 0);
            path.quadTo(width, 0, width, 12);
            path.lineTo(width, height - 12);
            path.quadTo(width, height, width - 12, height);
            path.lineTo(12, height);
            path.quadTo(0, height, 0, height - 12);
            path.lineTo(0, 12);
            path.quadTo(0, 0, 12, 0);
            canvas.clipPath(path);
        }

        super.onDraw(canvas);
    }
}