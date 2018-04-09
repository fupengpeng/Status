package com.fpp.status.activity.move;

import android.content.Context;
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

public class MoveThreeActivity extends AppCompatActivity {


    String TAG = "TestActivity";
    int left;
    int right;
    int top;
    int bottom;
    int dx;
    int dy;

    //手指按下的点为(x1, y1)手指离开屏幕的点为(x2, y2)


    Context mContext;

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

    private boolean

            isCenter = true;

    Timer timer = new Timer();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_three);
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
    int i = 56;
    Thread moveThread = new Thread(new Runnable() {
        @Override
        public void run() {
            Log.e(TAG, "run: isMoves = " + isMoves);
            while (isMoves) {
                try {
                    Log.e(TAG, "run: " + "线程睡觉中   " + i);
                    // 2.计时，睡眠 1 毫秒给主线程发送一个消息

                    Thread.sleep(20);

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
            int top = Integer.parseInt(b.getString("i"));
            Log.e(TAG, "handleMessage:------------------ 接收到消息 = " + top);
            if (top >= 0) {
                refreshUI(top);
            } else {
                isMoves = false;
            }

        }
    };

    private void refreshUI(int top) {
        Log.e(TAG, "refreshUI: top = " + top * 15);
        Animation oneAnimationS = AnimationUtils.loadAnimation(MoveThreeActivity.this, R.anim.ll_atvt_main_iv_anim_s);
        ivAtvtMainOrder.startAnimation(oneAnimationS);
        llAtvtMainSuperstratum.layout(left, top * 15, right, bottom);
        llAtvtMainBlack.layout(left - 45, top * 15, right + 45, bottom);

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
                        Log.e(TAG, "onTouch: " + "按下");
                        // 获取手指按下的坐标
                        startX = (int) event.getRawX();
                        startY = (int) event.getRawY();
                        //当手指按下的时候

                        break;
                    case MotionEvent.ACTION_MOVE:
//                        Log.e(TAG, "onTouch: " + "ACTION_MOVE");
                        // 获取手指移动到了哪个点的坐标
                        int movingX = (int) event.getRawX();
                        int movingY = (int) event.getRawY();
                        // 相对于上一个点，手指在X和Y方向分别移动的距离
                        int dx = movingX - startX;
                        int dy = movingY - startY;
                        // 获取TextView上一次上 下 左 右各边与父控件的距离
                        int left = llAtvtMainSuperstratum.getLeft();
                        int right = llAtvtMainSuperstratum.getRight();
                        int top = llAtvtMainSuperstratum.getTop();
                        int bottom = llAtvtMainSuperstratum.getBottom();
                        // 设置本次TextView的上 下 左 右各边与父控件的距离
                        if (isCenter && dy < 0) {  //从中间向上移动
                            //在中间
                            if (top + dy <= 0) {
                                Log.e(TAG, "onTouch: " + "30");
                                llAtvtMainSuperstratum.layout(left, 0, right, bottom);
                                isCenter = false;
                            } else {
                                Log.e(TAG, "onTouch: " + "30-");
                                llAtvtMainSuperstratum.layout(left, top + dy, right, bottom);
                            }
                        } else if (isCenter && dy > 0) {  //从中间向下移动
                            //在中间
                            Log.e(TAG, "onTouch: " + "0+");

                            if (top >= 855) {
                                llAtvtMainSuperstratum.layout(left, 855, right, bottom);
                            } else {
                                llAtvtMainSuperstratum.layout(left, top + dy, right, bottom);
                            }

//                            Animation oneAnimationS = AnimationUtils.loadAnimation(MoveActivity.this, R.anim.ll_atvt_main_black_anim_test_s   );
//                            tv.startAnimation(oneAnimationS);
                        } else if (!isCenter && dy < 0) {  //从上方向上移动
                            //在上方
                            Log.e(TAG, "onTouch: " + "855+");
                            if (top <= 0) {
                                llAtvtMainSuperstratum.layout(left, 0, right, bottom);
                            } else {
                                llAtvtMainSuperstratum.layout(left, top + dy, right, bottom);
                            }

//                            Animation oneAnimationS = AnimationUtils.loadAnimation(MoveActivity.this, R.anim.ll_atvt_main_black_anim_test_x);
//                            tv.startAnimation(oneAnimationS);
                        } else if (!isCenter && dy > 0) {  //从上方方向下移动
                            //在上方
                            if (top + dy >= 855) {
                                Log.e(TAG, "onTouch: " + "855");
                                llAtvtMainSuperstratum.layout(left, 855, right, bottom);
                                isCenter = true;
                            } else {
                                Log.e(TAG, "onTouch: " + "855-");
                                llAtvtMainSuperstratum.layout(left, top + dy, right, bottom);
                            }
                        }


                        // 本次移动的结尾作为下一次移动的开始
                        startX = (int) event.getRawX();
                        startY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:


//                        Scroller scroller = new Scroller(mContext);
//
//                private void smoothScrollTo ( int dstX, int dstY){
//                    int scrollX = getScrollX();
//                    int delta = dstX - scrollX;
//                    scroller.startScroll(scrollX, 0, delta, 0, 1000);
//                    invalidate();
//                }
//
//                @Override
//                public void computeScroll () {
//                    if (scroller.computeScrollOffset()) {
//                        scrollTo(scroller.getCurrX(), scroller.getCurY());
//                        postInvalidate();
//                    }
//                }
//
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




                        //当手指离开的时候
                Log.e(TAG, "onTouch: " + "ACTION_UP");
                //当手指离开的时候

                break;
                case MotionEvent.ACTION_CANCEL:
                Log.e(TAG, "onTouch: " + "ACTION_CANCEL");
                break;



                }
                Log.e(TAG, "onTouch: " + "--------------------");
                rlAtvtMainContent.invalidate();
                return true;//如果返回true,从手指接触屏幕到手指离开屏幕，将不会触发点击事件。
            }
        });


    }


}
