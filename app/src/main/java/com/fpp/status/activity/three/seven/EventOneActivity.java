package com.fpp.status.activity.three.seven;

import android.app.Activity;
import android.os.Bundle;

import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;

/**
 * @author fupengpeng
 * @description 事件处理方式----监听（键盘按下事件）
 * @data 2018/4/8 0008 14:36
 */

public class EventOneActivity extends Activity {
    // 飞机移动速度
    private  int speed = 10;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去掉窗口标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 创建PlaneView
        final PlaneView planeView = new PlaneView(this);
        setContentView(planeView);
//        planeView.setBackgroundResource();
        
        // 获取窗口管理器
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        // 获取屏幕高、宽
        display.getMetrics(metrics);
        // 设置飞机的初始位置
        planeView.currentX = metrics.widthPixels / 2 ;
        planeView.currentY = metrics.heightPixels - 40 ;
        // 为planeView组件的键盘事件绑定监听器
        planeView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // 获取由那个键触发的事件
                switch (event.getKeyCode()){
                    case KeyEvent.KEYCODE_S:  // 下移
                        planeView.currentY += speed;
                        break;
                    case KeyEvent.KEYCODE_W:  // 上移
                        planeView.currentY -= speed;
                        break;
                    case KeyEvent.KEYCODE_A:  // 左移
                        planeView.currentX -= speed;
                        break;
                    case KeyEvent.KEYCODE_D:  // 右移
                        planeView.currentX += speed;
                        break;


                }
                // 通知planeView组件重绘
                planeView.invalidate();
                return true;
            }
        });



    }
}
