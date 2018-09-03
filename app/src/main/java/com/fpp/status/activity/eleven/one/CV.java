package com.fpp.status.activity.eleven.one;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Description:
 * Author: fpp
 * Date: 2018/9/3  15:14
 */

public class CV extends View {
    private Paint mPaint;

    public CV(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制阴影，param1：模糊半径；param2：x轴大小：param3：y轴大小；param4：阴影颜色
        mPaint.setShadowLayer(10F, 0F, 50F, Color.RED);
        RectF rect = new RectF(0, 0, 200, 200);
        canvas.drawRoundRect(rect, (float) 75, (float) 75, mPaint);
    }
}
