package com.fpp.status.activity.eight.four;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.fpp.status.R;
import com.fpp.status.utils.LogUtils;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author fupengpeng
 * @description
 * @date 2018/4/20 0020 16:07
 */

public class GestureTwoActivity extends AppCompatActivity {

    // 定义手势检测器
    GestureDetector detector;
    @BindView(R.id.iv_atvt_gesture_two)
    ImageView ivAtvtGestureTwo;

    // 初始的图片资源
    Bitmap bitmap;
    // 定义图片的宽高
    int width,height;
    // 记录当前缩放比
    float currentScale = 1;
    // 控制图片缩放的Matrix对象
    Matrix matrix;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_two);
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
        // 创建手势检测器
        detector = new GestureDetector(this, onGestureListener);
        matrix = new Matrix();
        // 获取被缩放的源图片
        bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.nvshengtouxiang);
        // 获得位图宽
        width = bitmap.getWidth();
        // 获得位图高
        height = bitmap.getHeight();
        // 设置ImageView初始化时显示的图片
        ivAtvtGestureTwo.setImageBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.nvshengtouxiang));

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 将Activity上的触碰事件交给GestureDetector处理
        return detector.onTouchEvent(event);
    }

    GestureDetector.OnGestureListener onGestureListener = new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            LogUtils.e("----onDown----" + "  ----按下");
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            LogUtils.e("----onShowPress----" + "  ----按下未移动时");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            LogUtils.e("----onSingleTapUp----" + "  ----点击事件");
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            LogUtils.e("----onScroll----" + "  ----拖动事件");
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            LogUtils.e("----onLongPress----" + "  ----长按事件");
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            LogUtils.e("----onFling----" + "  ----滚动事件----拖动之后有速度才会产生滚动");
            velocityX = velocityX > 4000 ? 4000 : velocityX ;
            velocityX = velocityX < -4000 ? -4000 : velocityX ;
            // 根据手势的速度来计算缩放比，如果velocityX > 0 ,则放大图片，否则缩小图片
            currentScale += currentScale * velocityX / 4000.0f;
            // 保证 currentScale 不回等于 0
            currentScale = currentScale > 0.01 ? currentScale : 0.01f ;
            // 重置Matrix
            matrix.reset();
            // 缩放Matrix
            matrix.setScale(currentScale,currentScale,160 , 200 );
            BitmapDrawable tmp = (BitmapDrawable) ivAtvtGestureTwo.getDrawable();
            // 如果图片还未回收，先强制回收该图片
            if (!tmp.getBitmap().isRecycled()){
                tmp.getBitmap().recycle();
            }
            // 根据原始位图和Matrix创建新图片
            Bitmap bitmap2 = Bitmap.createBitmap(bitmap,0,0,width,height,matrix,true);
            // 显示新的位图
            ivAtvtGestureTwo.setImageBitmap(bitmap2);

            return true;
        }
    };



}
