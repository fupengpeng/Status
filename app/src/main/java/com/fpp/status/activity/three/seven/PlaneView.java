package com.fpp.status.activity.three.seven;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.fpp.status.R;

/**
 * @author fupengpeng
 * @description 描述
 * @data 2018/4/8 0008 14:29
 */

public class PlaneView extends View {
    public float currentX;
    public float currentY;
    Bitmap plane;



    public PlaneView(Context context) {
        super(context);
        plane = BitmapFactory.decodeResource(context.getResources(), R.drawable.beforehand_appoint_particulars_self_motion);
        setFocusable(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 创建画笔
        Paint p = new Paint();
        canvas.drawBitmap(plane,currentX,currentY,p);

    }
}
