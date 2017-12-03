package com.fpp.status.activity.handler;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.Toast;

import com.fpp.status.R;
import com.fpp.status.activity.move.MoveThreeActivity;

import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoveTwoTestActivity extends AppCompatActivity {


    String TAG = "MoveTwoTestActivity";
    int left;
    int right;
    int top;
    int bottom;
    int dx;
    int dy;

    //手指按下的点为(x1, y1)手指离开屏幕的点为(x2, y2)
    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;


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
        setContentView(R.layout.activity_move_two_test);
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
        Animation oneAnimationS = AnimationUtils.loadAnimation(MoveTwoTestActivity.this, R.anim.ll_atvt_main_iv_anim_s);
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
                        x1 = event.getX();
                        y1 = event.getY();
                        Log.e(TAG, "onTouch: "+ "x1 = " + x1 + "    y1 = " + y1 + "-----------------------------");
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
                        if (isCenter && (movingY - startY)<0){  //从中间向上移动
                            //在中间
                            if (top + dy <= 0){
                                Log.e(TAG, "onTouch: "+"30" );
                                llAtvtMainSuperstratum.layout(left, 0, right, bottom);
                                isCenter = false;
                            }else {
                                Log.e(TAG, "onTouch: "+"30-" );
                                llAtvtMainSuperstratum.layout(left, top + dy, right, bottom);
                            }
                        }else if(isCenter && (movingY - startY)>0){  //从中间向下移动
                            //在中间
                            Log.e(TAG, "onTouch: "+"0+" );

                            if (top >= 855 ){
                                llAtvtMainSuperstratum.layout(left, 855, right, bottom);
                            }else {
                                llAtvtMainSuperstratum.layout(left, top + dy, right, bottom);
                            }

//                            Animation oneAnimationS = AnimationUtils.loadAnimation(MoveActivity.this, R.anim.ll_atvt_main_black_anim_test_s   );
//                            tv.startAnimation(oneAnimationS);
                        }else if (!isCenter && (movingY - startY)<0){  //从上方向上移动
                            //在上方
                            Log.e(TAG, "onTouch: "+"855+" );
                            if (top <= 0 ){
                                llAtvtMainSuperstratum.layout(left, 0, right, bottom);
                            }else {
                                llAtvtMainSuperstratum.layout(left, top + dy, right, bottom);
                            }

//                            Animation oneAnimationS = AnimationUtils.loadAnimation(MoveActivity.this, R.anim.ll_atvt_main_black_anim_test_x);
//                            tv.startAnimation(oneAnimationS);
                        }else if (!isCenter && (movingY - startY)>0){  //从上方方向下移动
                            //在上方
                            if (top + dy >= 855){
                                Log.e(TAG, "onTouch: "+"855" );
                                llAtvtMainSuperstratum.layout(left, 855, right, bottom);
                                isCenter = true;
                            }else {
                                Log.e(TAG, "onTouch: "+"855-" );
                                llAtvtMainSuperstratum.layout(left, top + dy, right, bottom);
                            }
                        }


                        // 本次移动的结尾作为下一次移动的开始
                        startX = (int) event.getRawX();
                        startY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:
                        //当手指离开的时候
                        Log.e(TAG, "onTouch: " + "ACTION_UP");

                    //当手指离开的时候
                        x2 = event.getX();
                        y2 = event.getY();
                        if(y1 - y2 > 10) {
                            Toast.makeText(MoveTwoTestActivity.this, "向上滑", Toast.LENGTH_SHORT).show();
                            llAtvtMainSuperstratum.scrollBy(0,500);


                        } else if(y2 - y1 > 10) {
                            Toast.makeText(MoveTwoTestActivity.this, "向下滑", Toast.LENGTH_SHORT).show();
                            llAtvtMainSuperstratum.scrollBy(0,-500);
                        } else if(x1 - x2 > 10) {
                            Toast.makeText(MoveTwoTestActivity.this, "向左滑", Toast.LENGTH_SHORT).show();
                        } else if(x2 - x1 > 10) {
                            Toast.makeText(MoveTwoTestActivity.this, "向右滑", Toast.LENGTH_SHORT).show();
                        }
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

//    Context mContext;
//    Scroller scroller = new Scroller(mContext);
//    //调用此方法滚动到目标位置
//    public void smoothScrollTo(int fx, int fy) {
//        int dx = fx - scroller.getFinalX();
//        int dy = fy - scroller.getFinalY();
//        smoothScrollBy(dx, dy);
//    }
//
//    //调用此方法设置滚动的相对偏移
//    public void smoothScrollBy(int dx, int dy) {
//
//        //设置mScroller的滚动偏移量
//        scroller.startScroll(scroller.getFinalX(), scroller.getFinalY(), dx, dy);
//        invalidate();//这里必须调用invalidate()才能保证computeScroll()会被调用，否则不一定会刷新界面，看不到滚动效果
//    }
//    public void computeScroll() {
//
//        //先判断mScroller滚动是否完成
//        if (scroller.computeScrollOffset()) {
//
//            //这里调用View的scrollTo()完成实际的滚动
//            scrollTo(scroller.getCurrX(), scroller.getCurrY());
//
//            //必须调用该方法，否则不一定能看到滚动效果
//            postInvalidate();
//        }
//        super.computeScroll();
//    }




}
