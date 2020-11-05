package com.fpp.status.activity.move;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.fpp.status.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MoveOneActivity extends AppCompatActivity implements
        GestureDetector.OnGestureListener {

    private int[] imgs = { R.drawable.meinv02, R.drawable.meinv03, R.drawable.meinv04,
            R.drawable.meinv05, R.drawable.meinv06 };
    private GestureDetector gestureDetector;
    private ViewFlipper viewFlipper;
    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_one);
        mActivity = this;
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);

        gestureDetector = new GestureDetector(this);

        for (int i = 0; i < imgs.length; i++) { // 添加图片源
            ImageView iv = new ImageView(this);
            iv.setImageResource(imgs[i]);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(iv, new LayoutParams(LayoutParams.FILL_PARENT,
                    LayoutParams.FILL_PARENT));
        }

        viewFlipper.setAutoStart(true); // 设置自动播放功能（点击事件，前自动播放）
        viewFlipper.setFlipInterval(3000);
        if (viewFlipper.isAutoStart() && !viewFlipper.isFlipping()) {
            viewFlipper.startFlipping();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        viewFlipper.stopFlipping(); // 点击事件后，停止自动播放
        viewFlipper.setAutoStart(false);
        return gestureDetector.onTouchEvent(event); // 注册手势事件
    }

    @Override
    public boolean onDown(MotionEvent arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float arg2,
                           float arg3) {
        if (e2.getX() - e1.getX() > 120) { // 从左向右滑动（左进右出）
            Animation rInAnim = AnimationUtils.loadAnimation(mActivity,
                    R.anim.push_right_in); // 向右滑动左侧进入的渐变效果（alpha 0.1 -> 1.0）
            Animation rOutAnim = AnimationUtils.loadAnimation(mActivity,
                    R.anim.push_right_out); // 向右滑动右侧滑出的渐变效果（alpha 1.0 -> 0.1）

            viewFlipper.setInAnimation(rInAnim);
            viewFlipper.setOutAnimation(rOutAnim);
            viewFlipper.showPrevious();
            return true;
        } else if (e2.getX() - e1.getX() < -120) { // 从右向左滑动（右进左出）
            Animation lInAnim = AnimationUtils.loadAnimation(mActivity,
                    R.anim.push_left_in); // 向左滑动左侧进入的渐变效果（alpha 0.1 -> 1.0）
            Animation lOutAnim = AnimationUtils.loadAnimation(mActivity,
                    R.anim.push_left_out); // 向左滑动右侧滑出的渐变效果（alpha 1.0 -> 0.1）

            viewFlipper.setInAnimation(lInAnim);
            viewFlipper.setOutAnimation(lOutAnim);
            viewFlipper.showNext();
            return true;
        }
        return true;
    }

    @Override
    public void onLongPress(MotionEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
                            float arg3) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onShowPress(MotionEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onSingleTapUp(MotionEvent arg0) {
        // TODO Auto-generated method stub
        return false;
    }
    /**
     一、自动播放
     ViewFlipper控件，是ImageView的容器，用于添加显示的图片资源，主要功能有两个：添加显示View和自动播放View
     通过实现父类android.view.ViewGroup的addView(View child, ViewGroup.LayoutParams params)添加View资源，即图片和填充样式

     启动自动播放View，可以通过设置如下三个成员函数实现：
     1、 setAutoStart(true)，设置是否自动播放功能，true为自动播放，false为不自动播放，开启自动播放设为true
     2、 setFlipInterval(int milliseconds)，设置View播放的时间间隔，如3000（3秒）
     3、 startFlipping()，开始自动播放
     停止自动播放View，设置成员函数如下：
     1、 stopFlipping()，停止自动播放
     2、 setAutoStart(false)，停止自动播放，设为false

     二、手势滑屏
     手势滑动屏幕动画，是通过android.view.GestureDetector类检测各种手势事件实现的，该类有两个回调接口（Interface）
     A、GestureDetector.OnDoubleTapListener，用来通知DoubleTap双击事件，类似于鼠标的双击事件，接口三个抽象回调函数如下
     1、onDoubleTap(MotionEvent e)：DoubleTap双击手势事件后通知（触发）
     2、onDoubleTapEvent(MotionEvent e)：DoubleTap双击手势事件之间通知（触发），包含down、up和move事件（这里指的是在双击之间发生的事件，例如在同一个地方双击会产生DoubleTap手势，而在DoubleTap手势里面还会发生down和up事件，这两个事件由该函数通知）
     3、onSingleTapConfirmed(MotionEvent e)：用来判定该次点击是SingleTap而不是DoubleTap，如果连续点击两次就是DoubleTap手势；那么如果只点击一次，系统等待一段时间后没有收到第二次点击则判定该次点击为SingleTap而不是DoubleTap，此时触发的就是SingleTapConfirmed事件

     B、GestureDetector.OnGestureListener，用来通知普通的手势事件（down、longPress、scroll、up等），接口具体的六个抽象回调函数如下
     1、onDown(MotionEvent e)：down事件，表示按下事件
     2、onSingleTapUp(MotionEvent e)：一次点击up事件，表示按下后的抬起事件
     3、onShowPress(MotionEvent e)：down事件发生而move或则up还没发生前触发该事件，此事件一般用于通知用户press按击事件已发生
     4、onLongPress(MotionEvent e)：长按事件，down事件后up事件前的一段时间间隔后（由系统分配，也可自定义），如果仍然按住屏幕则视为长按事件
     5、onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)：滑动手势事件，例如scroll事件后突然up，fling的速度大小由e每秒x和y改变大小决定
     6、onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)：在屏幕上拖动事件，即down按下点——scroll拖动——up抬起点的move移动事件

     本示例的滑动屏幕动画，仅用到了上面的GestureDetector.OnGestureListener及其onFling事件，具体实现步骤如下：
     1、Activity实现android.view.GestureDetector.OnGestureListener 监听接口，并声明gestureDetector = new GestureDetector(this); 用于监听手势事件
     2、在Activity的成员函数onTouchEvent(MotionEvent event)中，注册GestureDetector.OnGestureListener手势监听的gestureDetector.onTouchEvent(event);事件接口
     3、在GestureDetector.OnGestureListener回调函数onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) 中，实现滑屏动画
     */

}
