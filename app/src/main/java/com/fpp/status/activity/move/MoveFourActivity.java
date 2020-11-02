package com.fpp.status.activity.move;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.fpp.status.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

public class MoveFourActivity extends AppCompatActivity {


    String TAG = "TestActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_four);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        initView();
    }


    private void initView() {

//                第一、调用Scroller实例去产生一个偏移控制(对应于startScroll()方法)
//                第二、手动调用invalid() 方法去重新绘制，剩下的就是在 computeScroll () 里根据当前已经逝去的时间，获取当前
//                应该偏移的坐标(由Scroller实例对应的computeScrollOffset()计算而得)，
//                第三、当前应该偏移的坐标，调用scrollBy() 方法去缓慢移动至该坐标处。

//                        其中：onInterceptTouchEvent()主要功能是控制触摸事件的分发，例如是子视图的点击事件还是滑动事件。
//                        其他所有处理过程均在onTouchEvent()方法里实现了。
//                        1、屏幕的滑动要根据手指的移动而移动  ---- 主要实现在onTouchEvent()方法中
//                        2、当手指松开时，可能我们并没有完全滑动至某个屏幕上，这是我们需要手动判断当前偏移至去计算目标屏(当前屏或者
//                            前后屏)，并且优雅的偏移到目标屏(当然是用Scroller实例咯)。
//                        3、调用computeScroll ()去实现缓慢移动过程。


    }


}

