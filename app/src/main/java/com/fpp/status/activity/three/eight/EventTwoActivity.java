package com.fpp.status.activity.three.eight;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.fpp.status.R;
import com.fpp.status.utils.LogUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author fupengpeng
 * @description 事件处理方式----回调
 * @data 2018/4/9 0009 9:21
 */

public class EventTwoActivity extends AppCompatActivity {
    @BindView(R.id.btn_atvt_event_two_true)
    ButtonTrue btnAtvtEventTwoTrue;
    @BindView(R.id.btn_atvt_event_two_false)
    ButtonFalse btnAtvtEventTwoFalse;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event_two);
        ButterKnife.bind(this);

        btnAtvtEventTwoFalse.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // 只处理按下键的事件
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    LogUtils.e("onKey", "setOnKeyListener");
                }
                // 返回false表明该事件会向外传播
                return false;
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        LogUtils.e("onKeyDown", "Activity");
        // 返回false表明该事件会向外传播
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 只处理抬起键的事件
        if (event.getAction() == KeyEvent.ACTION_UP) {
            LogUtils.e("onTouchEvent", "EventTwoActivity = ACTION_UP");
        }
        // 返回false表明该事件会向外传播
        return false;
    }

}
