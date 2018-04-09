package com.fpp.status.activity.three.eight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Button;

import com.fpp.status.utils.LogUtils;

/**
 * @author fupengpeng
 * @description 描述
 * @data 2018/4/9 0009 9:18
 */

public class ButtonTrue extends Button {

    public ButtonTrue(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        this.setText("点击ButtonTrue，处理完事件，事件不外传。");
        LogUtils.e("ButtonTrue", "已经完全处理该事件，该事件不会向外传播");
        // 返回true，表明已经完全处理该事件，该事件不会向外传播
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        this.setText("触摸ButtonTrue，处理完事件，事件不外传。");
        LogUtils.e("onTouchEvent", "已经完全处理该事件，该事件不会向外传播");
        // 返回true，表明已经完全处理该事件，该事件不会向外传播
        return true;
    }
}
