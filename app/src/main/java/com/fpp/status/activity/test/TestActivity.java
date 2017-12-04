package com.fpp.status.activity.test;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.fpp.status.R;

import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity {

    String TAG = "TestActivity";
    int left;
    int right;
    int top;
    int bottom;
    int dx;
    int dy;


    // 主页面
    @BindView(R.id.rl_atvt_main_content)
    RelativeLayout rlAtvtMainContent;

    // scroll栏
    @BindView(R.id.ll_atvt_main_superstratum)
    LinearLayout llAtvtMainSuperstratum;

    // 标题栏
    @BindView(R.id.ll_atvt_main_title)
    LinearLayout llAtvtMainTitle;

    // 图片栏
    @BindView(R.id.ll_atvt_main_iv)
    LinearLayout llAtvtMainIv;

    // 空白栏
    @BindView(R.id.ll_atvt_main_black)
    LinearLayout llAtvtMainBlack;

    // 图片栏内布局
    @BindView(R.id.rl_atvt_main_untreated_order)
    RelativeLayout rlAtvtMainUntreatedOrder;

    @BindView(R.id.ll_atvt_main_bg)
    LinearLayout llAtvtMainBg;
    @BindView(R.id.iv_atvt_main_order)
    ImageView ivAtvtMainOrder;

    private int startX;
    private int startY;

    private boolean isCenter = true;

    Timer timer = new Timer();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
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

    boolean isMoves = true;
    int i = 284;
    Thread moveThread = new Thread(new Runnable() {
        @Override
        public void run() {
            Log.e(TAG, "run: isMoves = " + isMoves);
            while (isMoves) {
                try {
                    Log.e(TAG, "run: " + "线程睡觉中   " + i);
                    // 2.计时，睡眠 1 毫秒给主线程发送一个消息

                    Thread.sleep(5);

                    Log.e(TAG, "run: " + "线程睡醒了");
                    Message msg = new Message();
                    Bundle b = new Bundle();
                    b.putString("i", String.valueOf(i));
                    msg.setData(b);
                    Log.e(TAG, "run: 发送消息 i  == " + i);
                    mHandler.sendMessage(msg);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });
    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 3. 主线程接收到子线程发送的消息，处理UI
            Bundle b = msg.getData();
            int topp = Integer.parseInt(b.getString("i"));

            if (topp < 0) {
                Log.e(TAG, "handleMessage:---------更改--------- 接收到消息 = " + topp);
                llAtvtMainSuperstratum.layout(left, 45, right, bottom);
                isMoves = false;
            } else {
                Log.e(TAG, "handleMessage:---------更新--------- 接收到消息 = " + topp);
                refreshUI(topp);
            }

        }
    };

    private void refreshUI(int top) {
        Log.e(TAG, "refreshUI: top = " + top * 5);
//        Animation oneAnimationS = AnimationUtils.loadAnimation(TestActivity.this, R.anim.ll_atvt_main_iv_anim_s);
//        ivAtvtMainOrder.startAnimation(oneAnimationS);
        llAtvtMainSuperstratum.layout(left, top * 5, right, bottom);
        llAtvtMainBlack.layout(left - 45, top * 5, right + 45, bottom);

        Log.e(TAG, "refreshUI:++ 前  i  =  " + i);
        i--;
        Log.e(TAG, "refreshUI: 后  i = " + i);


    }

    private void initView() {


        llAtvtMainSuperstratum.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // 获取手指按下的坐标
                        startX = (int) event.getRawX();
                        startY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:

                        // 获取TextView上一次上 下 左 右各边与父控件的距离
                        left = llAtvtMainSuperstratum.getLeft();
                        right = llAtvtMainSuperstratum.getRight();
                        top = llAtvtMainSuperstratum.getTop();
                        bottom = llAtvtMainSuperstratum.getBottom();

                        // 获取手指移动到了哪个点的坐标
                        int movingX = (int) event.getRawX();
                        int movingY = (int) event.getRawY();
                        // 相对于上一个点，手指在X和Y方向分别移动的距离
                        dx = movingX - startX;
                        dy = movingY - startY;

                        if (isCenter) {  // 在中间时
                            Log.e(TAG, "onTouch: ----" + "scrollview 在中间");


                            if (dy < 0) {  // 向上滑动
//                            if ((top + dy) < 135) {
//                                Log.e(TAG, "onTouch: " + " aaaaa");
//                                llAtvtMainBlack.layout(left - 45, 255, right + 45, bottom);
//                                llAtvtMainBg.layout(left - 45, 0, right + 45, bottom);
//                                llAtvtMainSuperstratum.layout(left, 135, right, bottom);
//
//                                isCenter = false;
//                            } else {
//                                Log.e(TAG, "onTouch: " + " bbbbb");
//                                llAtvtMainBlack.layout(left - 45, top + dy + 120, right + 45, bottom);
//                                llAtvtMainBg.layout(left - 45, 0, right + 45, bottom);
//                                llAtvtMainSuperstratum.layout(left, top + dy, right, bottom);
//
//                            }
                                moveThread.start();

                            }
                            if (dy > 0) {  // 向下滑动
                                // 动画效果
                                Log.e(TAG, "onTouch: ----" + "在顶部向下滑动    弹回990 处");

                            }


                        } else {

//                            if (dy < 0) {  // 向上滑动
//                                // 动画效果
//                                Log.e(TAG, "onTouch: ====" + "向上滑动  scrollview  滑动");
//                            }
////                            if (dy > 0) {  // 向下滑动
//                            if ((top + dy) > 990) {
//                                Log.e(TAG, "onTouch: " + " ccccc");
//                                llAtvtMainBlack.layout(left - 45, 1110, right + 45, bottom);
//                                llAtvtMainBg.layout(left - 45, 0, right + 45, bottom);
//                                llAtvtMainSuperstratum.layout(left, 990, right, bottom);
//
//                                isCenter = true;
//                            } else {
//                                Log.e(TAG, "onTouch: " + " ddddd");
//                                llAtvtMainBlack.layout(left - 45, top + dy + 120, right + 45, bottom);
//                                llAtvtMainBg.layout(left - 45, 0, right + 45, bottom);
//                                llAtvtMainSuperstratum.layout(left, top + dy, right, bottom);
//
//                            }
                            // 设置到中间去


//                            }

                        }

                        // 本次移动的结尾作为下一次移动的开始
                        startX = (int) event.getRawX();
                        startY = (int) event.getRawY();
                        Log.e(TAG, "onTouch: -=-=-= startX = " + startX + "  startY  =  " + startY);
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                Log.e(TAG, "onTouch: " + "--------------------");
                rlAtvtMainContent.invalidate();
                return true;//如果返回true,从手指接触屏幕到手指离开屏幕，将不会触发点击事件。
            }
        });


    }
}
