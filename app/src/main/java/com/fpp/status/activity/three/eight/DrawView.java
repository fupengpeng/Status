package com.fpp.status.activity.three.eight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author fupengpeng
 * @description 描述
 * @data 2018/4/9 0009 10:54
 */

public class DrawView extends View {
    public float currentX = 40;
    public float currentY = 50;
    // 定义画笔
    Paint p = new Paint();
    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 设置画笔颜色
        p.setColor(Color.RED);
        // 绘制圆
        canvas.drawCircle(currentX,currentY,15,p);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 设置view位置
        this.currentX = event.getX();
        this.currentY = event.getY();
        // 重绘
        this.invalidate();
        return true;
    }
}
