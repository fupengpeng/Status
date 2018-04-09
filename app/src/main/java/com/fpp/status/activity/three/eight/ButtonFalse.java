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
 * @data 2018/4/9 0009 9:15
 */

public class ButtonFalse extends Button{

    public ButtonFalse(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        this.setText("点击ButtonFalse，未处理完事件，事件外传。");
        LogUtils.e("ButtonFalse","未完全处理该事件，该事件依然向外传播");
        // 返回false，表明并未完全处理该事件，该事件依然向外传播
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        this.setText("触摸ButtonFalse，未处理完事件，事件外传。");
        // 只处理按下的事件
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            LogUtils.e("onTouchEvent", "ButtonFalse   =  ACTION_DOWN");
        }
        LogUtils.e("onTouchEvent", "未完全处理该事件，该事件依然向外传播");
        // 返回false，表明并未完全处理该事件，该事件依然向外传播
        return false;
    }

}
