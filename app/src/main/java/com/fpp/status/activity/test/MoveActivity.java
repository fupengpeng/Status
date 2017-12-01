package com.fpp.status.activity.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fpp.status.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoveActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.bt)
    Button bt;
    @BindView(R.id.root)
    ViewGroup root;

    private boolean isEditMode;


    private int startX;
    private int startY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {

        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isEditMode) {
                    bt.setText("点击模式");
                } else {
                    bt.setText("编辑模式");
                }
                isEditMode = !isEditMode;
            }
        });

        tv.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!isEditMode) {
                    return false;
                }
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // 获取手指按下的坐标
                        startX = (int) event.getRawX();
                        startY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        // 获取手指移动到了哪个点的坐标
                        int movingX = (int) event.getRawX();
                        int movingY = (int) event.getRawY();
                        // 相对于上一个点，手指在X和Y方向分别移动的距离
                        int dx = movingX - startX;
                        int dy = movingY - startY;
                        // 获取TextView上一次上 下 左 右各边与父控件的距离
                        int left = tv.getLeft();
                        int right = tv.getRight();
                        int top = tv.getTop();
                        int bottom = tv.getBottom();
                        // 设置本次TextView的上 下 左 右各边与父控件的距离
                        tv.layout(left + dx, top + dy, right + dx, bottom + dy);
                        // 本次移动的结尾作为下一次移动的开始
                        startX = (int) event.getRawX();
                        startY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                root.invalidate();
                return true;//如果返回true,从手指接触屏幕到手指离开屏幕，将不会触发点击事件。
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "TextView Click",Toast.LENGTH_LONG)
                        .show();
            }
        });


    }
}
