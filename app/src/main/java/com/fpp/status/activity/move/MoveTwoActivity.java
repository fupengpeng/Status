package com.fpp.status.activity.move;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.fpp.status.R;

import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoveTwoActivity extends AppCompatActivity {

    public String TAG = "MoveTwoActivity----";

    int left;
    int right;
    int top;
    int bottom;
    int heigth;
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
    @BindView(R.id.iv_atvt_main_left_top_page)
    ImageView ivAtvtMainLeftTopPage;
    @BindView(R.id.iv_atvt_main_right_top_qr_code)
    ImageView ivAtvtMainRightTopQrCode;


    private int startX;
    private int startY;

    private boolean isCenter = true;

    Timer timer = new Timer();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_two);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        initView();
//        setGestureListener();
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
                            if (top + dy <= 135){
                                Log.e(TAG, "onTouch: "+"135" );
                                llAtvtMainSuperstratum.layout(left, 135, right, bottom);
                                isCenter = false;
                            }else {
                                Log.e(TAG, "onTouch: "+"135-" );
                                llAtvtMainSuperstratum.layout(left, top + dy, right, bottom);
                            }
                        }else if(isCenter && (movingY - startY)>0){  //从中间向下移动
                            //在中间
                            Log.e(TAG, "onTouch: "+"135+" );

                            if (top >= 990 ){
                                llAtvtMainSuperstratum.layout(left, 990, right, bottom);
                            }else {
                                llAtvtMainSuperstratum.layout(left, top + dy, right, bottom);
                            }

//                            Animation oneAnimationS = AnimationUtils.loadAnimation(MoveActivity.this, R.anim.ll_atvt_main_black_anim_test_s   );
//                            tv.startAnimation(oneAnimationS);
                        }else if (!isCenter && (movingY - startY)<0){  //从上方向上移动
                            //在上方
                            Log.e(TAG, "onTouch: "+"1000+" );
                            if (top <= 135 ){
                                llAtvtMainSuperstratum.layout(left, 135, right, bottom);
                            }else {
                                llAtvtMainSuperstratum.layout(left, top + dy, right, bottom);
                            }

//                            Animation oneAnimationS = AnimationUtils.loadAnimation(MoveActivity.this, R.anim.ll_atvt_main_black_anim_test_x);
//                            tv.startAnimation(oneAnimationS);
                        }else if (!isCenter && (movingY - startY)>0){  //从上方方向下移动
                            //在上方
                            if (top + dy >= 990){
                                Log.e(TAG, "onTouch: "+"990" );
                                llAtvtMainSuperstratum.layout(left, 990, right, bottom);
                                isCenter = true;
                            }else {
                                Log.e(TAG, "onTouch: "+"990-" );
                                llAtvtMainSuperstratum.layout(left, top + dy, right, bottom);
                            }
                        }


                        // 本次移动的结尾作为下一次移动的开始
                        startX = (int) event.getRawX();
                        startY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e(TAG, "onTouch: " + "ACTION_UP");
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        Log.e(TAG, "onTouch: " + "ACTION_CANCEL");
                        break;
                    case MotionEvent.ACTION_OUTSIDE:
                        Log.e(TAG, "onTouch: " + "ACTION_OUTSIDE");
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        Log.e(TAG, "onTouch: " + "ACTION_POINTER_DOWN");
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        Log.e(TAG, "onTouch: " + "ACTION_POINTER_UP");
                        break;
                    case MotionEvent.ACTION_HOVER_MOVE:
                        Log.e(TAG, "onTouch: " + "ACTION_HOVER_MOVE");
                        break;
                    case MotionEvent.ACTION_SCROLL:
                        Log.e(TAG, "onTouch: " + "ACTION_SCROLL");
                        break;
                    case MotionEvent.ACTION_HOVER_ENTER:
                        Log.e(TAG, "onTouch: " + "ACTION_HOVER_ENTER");
                        break;
                    case MotionEvent.ACTION_HOVER_EXIT:
                        Log.e(TAG, "onTouch: " + "ACTION_HOVER_EXIT");
                        break;
                    case MotionEvent.ACTION_BUTTON_PRESS:
                        Log.e(TAG, "onTouch: " + "ACTION_BUTTON_PRESS");
                        break;
                    case MotionEvent.ACTION_BUTTON_RELEASE:
                        Log.e(TAG, "onTouch: " + "ACTION_BUTTON_RELEASE");
                        break;
                }
                Log.e(TAG, "onTouch: " + "--------------------");
                rlAtvtMainContent.invalidate();
                return true;//如果返回true,从手指接触屏幕到手指离开屏幕，将不会触发点击事件。
            }
        });


    }




    /*----------------------------滑动view-------------------------------*/


    private float mPosX, mPosY, mCurPosX, mCurPosY;//记录mListViewDevice 滑动的位置
    protected int mScrollX;    // 表示离视图起始位置的x水平方向的偏移量
    protected int mScrollY;    // 表示离视图起始位置的y垂直方向的偏移量
//    private static final int FLING_MIN_DISTANCE = 20;//mListView  滑动最小距离
//    private static final int FLING_MIN_VELOCITY = 200;//mListView 滑动最大速度
//
//    /**
//     * @author jczmdeveloper
//     * @desp mListView 的手势监听
//     */
//    private GestureDetector.OnGestureListener listViewGestureListener = new GestureDetector.OnGestureListener() {
//
//        @Override
//        public boolean onSingleTapUp(MotionEvent e) {
//            // TODO Auto-generated method stub
//            return false;
//        }
//
//        @Override
//        public void onShowPress(MotionEvent e) {
//            // TODO Auto-generated method stub
//
//        }
//
//        @Override
//        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
//                                float distanceY) {
//            // TODO Auto-generated method stub
//            return false;
//        }
//
//        @Override
//        public void onLongPress(MotionEvent e) {
//            // TODO Auto-generated method stub
//
//        }
//
//        @Override
//        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
//                               float velocityY) {
//            // TODO Auto-generated method stub
//            //向上滑动
//            if (e1.getY() - e2.getY() > FLING_MIN_DISTANCE) {
//                // && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
////                if (mListViewDevice.getLastVisiblePosition() == mListView
////                        .getCount() - 1) {
////                    //do something
////                }
//            }
//            // 向下滑动
//            if (e2.getY() - e1.getY() > FLING_MIN_DISTANCE
//                    && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
//            }
//
//
//            return false;
//        }
//
//        @Override
//        public boolean onDown(MotionEvent e) {
//            // TODO Auto-generated method stub
//            return false;
//        }
//    };
//    /**
//     * @author jczmdeveloper
//     * @desp mListView 的手势探测器
//     */
//    GestureDetector listViewGesture = new GestureDetector(listViewGestureListener);
//    /**
//     * @author jczmdeveloper
//     * @desp mListView 的触摸监听
//     */
//    private View.OnTouchListener listViewOnTouchListener = new View.OnTouchListener() {
//
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            // TODO Auto-generated method stub
//            Activity listViewDeviceGesture = null;
//            return listViewDeviceGesture.onTouchEvent(event);
//        }
//    };
//

    /*=======================================*/

    /**
     * 设置上下滑动作监听器
     *
     * @author jczmdeveloper
     */
    private void setGestureListener() {
        llAtvtMainSuperstratum.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        // 手指落下的位置坐标
                        mPosX = event.getX();
                        mPosY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        // 移动的位置坐标
                        mCurPosX = event.getX();
                        mCurPosY = event.getY();

                        break;
                    case MotionEvent.ACTION_UP:
                        if (mCurPosY - mPosY > 0
                                && (Math.abs(mCurPosY - mPosY) > 25)) {
                            //向下滑動

//                            llAtvtMainSuperstratum.offsetLeftAndRight(100);
                            llAtvtMainSuperstratum.offsetTopAndBottom(100);


                            mScrollX = llAtvtMainSuperstratum.getScrollX();
                            mScrollY = llAtvtMainSuperstratum.getScrollY();
                            Log.e(TAG, "onTouch: " + "scroll: " + mScrollX + "," + mScrollY);

                            int[] positionOne = new int[2];
                            llAtvtMainSuperstratum.getLocationOnScreen(positionOne);
                            Log.e(TAG, "onTouch: " + "getLocationOnScreen: " + positionOne[0] + "," + positionOne[1]);

                            int[] positionTwo = new int[2];
                            llAtvtMainSuperstratum.getLocationInWindow(positionTwo);
                            Log.e(TAG, "onTouch: " + "getLocationInWindow: " + positionTwo[0] + "," + positionTwo[1]);

                            Rect viewRect = new Rect();
                            llAtvtMainSuperstratum.getGlobalVisibleRect(viewRect);
                            Log.e(TAG, "onTouch: " + "viewRect: " + viewRect);


//                            llAtvtMainSuperstratum.scrollTo();
//                            llAtvtMainSuperstratum.scrollBy();
                        } else if (mCurPosY - mPosY < 0
                                && (Math.abs(mCurPosY - mPosY) > 25)) {
                            //向上滑动

                        }

                        break;
                }
                return true;
            }

        });
    }

    @OnClick({R.id.iv_atvt_main_left_top_page, R.id.iv_atvt_main_right_top_qr_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_atvt_main_left_top_page:

//             scroll: 0,0
//             getLocationOnScreen: 45,1059
//             getLocationInWindow: 45,1059
//             viewRect: Rect(45, 1059 - 1035, 1812)
//
//             scroll: 0,0
//             getLocationOnScreen: 45,809
//             getLocationInWindow: 45,809
//             viewRect: Rect(45, 809 - 1035, 1812)

//                scroll: 0,0
//                getLocationOnScreen: 45,348
//                getLocationInWindow: 45,348
//                viewRect: Rect(45, 348 - 1035, 1812)
                //向下滑動
                mScrollX = llAtvtMainSuperstratum.getScrollX();
                mScrollY = llAtvtMainSuperstratum.getScrollY();
                Log.e(TAG, "onTouch: " + "scroll: " + mScrollX + "," + mScrollY);

                int[] positionOne = new int[2];
                llAtvtMainSuperstratum.getLocationOnScreen(positionOne);
                Log.e(TAG, "onTouch: " + "getLocationOnScreen: " + positionOne[0] + "," + positionOne[1]);

                int[] positionTwo = new int[2];
                llAtvtMainSuperstratum.getLocationInWindow(positionTwo);
                Log.e(TAG, "onTouch: " + "getLocationInWindow: " + positionTwo[0] + "," + positionTwo[1]);

                Rect viewRect = new Rect();
                llAtvtMainSuperstratum.getGlobalVisibleRect(viewRect);
                Log.e(TAG, "onTouch: " + "viewRect: " + viewRect);


                break;
            case R.id.iv_atvt_main_right_top_qr_code:
                break;
        }
    }


    /*

    protected int mScrollX;

    protected int mScrollY;

    public final int getScrollX() {
        return mScrollX;
    }

    public final int getScrollY() {
        return mScrollY;
    }
    mScrollX：表示离视图起始位置的x水平方向的偏移量
    mScrollY：表示离视图起始位置的y垂直方向的偏移量
    分别通过getScrollX() 和getScrollY()方法获得。
    注意：mScrollX和mScrollY指的并不是坐标，而是偏移量。

    scrollTo();
    public void scrollTo(int x, int y) {
        if (mScrollX != x || mScrollY != y) {
            int oldX = mScrollX;
            int oldY = mScrollY;
            mScrollX = x;
            mScrollY = y;
            invalidateParentCaches();
            onScrollChanged(mScrollX, mScrollY, oldX, oldY);
            if (!awakenScrollBars()) {
                postInvalidateOnAnimation();
            }
        }
    }
    如果偏移位置发生了改变，就会给mScrollX和mScrollY赋新值，改变当前位置。
    注意：x,y代表的不是坐标点，而是偏移量。
    例如：
    我要移动view到坐标点（100，100），那么我的偏移量就是(0,，0)  - （100，100） = （-100 ，-100）  ，
    我就要执行view.scrollTo(-100,-100),达到这个效果。

    scrollBy();
    public void scrollBy(int x, int y) {
        scrollTo(mScrollX + x, mScrollY + y);
    }
    它实际上是调用了scrollTo(mScrollX + x, mScrollY + y);
    mScrollX + x和mScrollY + y，即表示在原先偏移的基础上在发生偏移，通俗的说就是相对我们当前位置偏移。
    根据父类VIEW里面移动，如果移动到了超出的地方，就不会显示。
    查看上文中的示意图你就会知道大概。
     */

}
