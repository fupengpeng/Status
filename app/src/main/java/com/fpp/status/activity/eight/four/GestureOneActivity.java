package com.fpp.status.activity.eight.four;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.fpp.status.R;
import com.fpp.status.utils.LogUtils;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author fupengpeng
 * @description 描述
 * @data 2018/4/20 0020 14:18
 */

public class GestureOneActivity extends AppCompatActivity {

    // 定义手势检测器
    GestureDetector detector;
    @BindView(R.id.btn_atvt_gesture_one_one)
    Button btnAtvtGestureOneOne;
    @BindView(R.id.btn_atvt_gesture_one_two)
    Button btnAtvtGestureOneTwo;
    @BindView(R.id.btn_atvt_gesture_one_three)
    Button btnAtvtGestureOneThree;
    @BindView(R.id.btn_atvt_gesture_one_four)
    Button btnAtvtGestureOneFour;
    @BindView(R.id.btn_atvt_gesture_one_five)
    Button btnAtvtGestureOneFive;
    @BindView(R.id.btn_atvt_gesture_one_six)
    Button btnAtvtGestureOneSix;
    @BindView(R.id.btn_atvt_gesture_one_seven)
    Button btnAtvtGestureOneSeven;
    @BindView(R.id.btn_atvt_gesture_one_eight)
    Button btnAtvtGestureOneEight;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_one);
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
            return false;
        }
    };

    @OnClick({R.id.btn_atvt_gesture_one_one, R.id.btn_atvt_gesture_one_two,
            R.id.btn_atvt_gesture_one_three, R.id.btn_atvt_gesture_one_four,
            R.id.btn_atvt_gesture_one_five, R.id.btn_atvt_gesture_one_six,
            R.id.btn_atvt_gesture_one_seven, R.id.btn_atvt_gesture_one_eight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_atvt_gesture_one_one:
                intent = new Intent(GestureOneActivity.this,GestureTwoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_atvt_gesture_one_two:
                break;
            case R.id.btn_atvt_gesture_one_three:
                break;
            case R.id.btn_atvt_gesture_one_four:
                break;
            case R.id.btn_atvt_gesture_one_five:
                break;
            case R.id.btn_atvt_gesture_one_six:
                break;
            case R.id.btn_atvt_gesture_one_seven:
                break;
            case R.id.btn_atvt_gesture_one_eight:
                break;
        }
    }
}
