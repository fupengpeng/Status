package com.fpp.status.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author fupengpeng
 * @description 自定义日历控件
 * @date 2018/3/22 0022 15:21
 */
public class CalendarView extends View implements View.OnClickListener {

    // 初始状态
    public static final int STATE_NORMAL = 0;
    // 放大状态
    public static final int STATE_AMPLIFICATION = 1;
    // 缩小状态
    public static final int STATE_SHRINK = 2;
    // 显示状态(默认初始状态)
    private int mState = STATE_NORMAL;
    // 定义画笔
    private Paint mPaint;
    // 用于获取文字的宽和高
    private Rect mBounds;


    // 计数值，每点击一次本控件，其值增加1
    private int mCount;

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 初始化画笔、Rect
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBounds = new Rect();


        // 本控件的点击事件
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (mState) {
            case 0:    // 正常状态
                /*---- 绘制背景一 ----*/
                // 设置画笔颜色
                mPaint.setColor(Color.WHITE);
                // 绘制一个填充色为蓝色的矩形
                canvas.drawRect(0, 0, getWidth(), getHeight() / 13 * 6, mPaint);

                /*---- 绘制背景二 ----*/
                // 设置画笔颜色
                mPaint.setColor(Color.GRAY);
                // 绘制一个填充色为蓝色的矩形
                canvas.drawRect(0, getHeight() / 13 * 6, getWidth(), getHeight() / 13 * 7, mPaint);

                /*---- 绘制背景三 ----*/
                // 设置画笔颜色
                mPaint.setColor(Color.WHITE);
                // 绘制一个填充色为蓝色的矩形
                canvas.drawRect(0, getHeight() / 13 * 7, getWidth(), getHeight(), mPaint);
                break;
            case 1:    // 放大状态
                /*---- 绘制背景一 ----*/
                // 设置画笔颜色
                mPaint.setColor(Color.WHITE);
                // 绘制一个填充色为蓝色的矩形
                canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
                break;
            case 2:    // 缩小状态
                /*---- 绘制背景一 ----*/
                // 设置画笔颜色
                mPaint.setColor(Color.WHITE);
                // 绘制一个填充色为蓝色的矩形
                canvas.drawRect(0, 0, getWidth(), getHeight() / 13, mPaint);

                /*---- 绘制背景二 ----*/
                // 设置画笔颜色
                mPaint.setColor(Color.GRAY);
                // 绘制一个填充色为蓝色的矩形
                canvas.drawRect(0, getHeight() / 13, getWidth(), getHeight() / 13 * 2, mPaint);

                /*---- 绘制背景三 ----*/
                // 设置画笔颜色
                mPaint.setColor(Color.WHITE);
                // 绘制一个填充色为蓝色的矩形
                canvas.drawRect(0, getHeight() / 13 * 2, getWidth(), getHeight(), mPaint);
                break;

        }


        /*---- 绘制文字 ----*/
        // 设置画笔颜色
        mPaint.setColor(Color.RED);
        // 设置画笔大小
        mPaint.setTextSize(50);
        String text = String.valueOf(mCount);
        // 获取文字的宽和高
        mPaint.getTextBounds(text, 0, text.length(), mBounds);
        float textWidth = mBounds.width();
        float textHeight = mBounds.height();

        // 绘制字符串
        canvas.drawText(text, getWidth() / 7, getHeight() / 13, mPaint);
    }

    @Override
    public void onClick(View v) {
        mCount++;

        // 重绘
        invalidate();
    }

}


