package com.fpp.status.activity.test;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fpp.status.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MoveActivity extends AppCompatActivity {

    String TAG = "MoveActivity";
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.bt)
    Button bt;
    @BindView(R.id.root)
    ViewGroup root;
    @BindView(R.id.btn)
    Button btn;
    boolean isCenter = true;

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


        /*
        点击事件：
            ACTION_DOWN
            ACTION_UP
        滑动事件：
            ACTION_DOWN
            多个ACTION_MOVE
            ACTION_UP




         */

        tv.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!isEditMode) {
                    return false;
                }
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
                        int left = tv.getLeft();
                        int right = tv.getRight();
                        int top = tv.getTop();
                        int bottom = tv.getBottom();
                        // 设置本次TextView的上 下 左 右各边与父控件的距离
                        if (isCenter && (movingY - startY)<0){  //从中间向上移动
                            //在中间
                            if (top + dy <= 300){
                                Log.e(TAG, "onTouch: "+"300" );
                                tv.layout(left, 300, right, bottom);
                                isCenter = false;
                            }else {
                                Log.e(TAG, "onTouch: "+"300-" );
                                tv.layout(left, top + dy, right, bottom);
                            }
                        }else if(isCenter && (movingY - startY)>0){  //从中间向下移动
                            //在中间
                            Log.e(TAG, "onTouch: "+"300+" );

                            if (top >= 1000 ){
                                tv.layout(left, 1000, right, bottom);
                            }else {
                                tv.layout(left, top + dy, right, bottom);
                            }

//                            Animation oneAnimationS = AnimationUtils.loadAnimation(MoveActivity.this, R.anim.ll_atvt_main_black_anim_test_s   );
//                            tv.startAnimation(oneAnimationS);
                        }else if (!isCenter && (movingY - startY)<0){  //从上方向上移动
                            //在上方
                            Log.e(TAG, "onTouch: "+"1000+" );
                            if (top <= 300 ){
                                tv.layout(left, 300, right, bottom);
                            }else {
                                tv.layout(left, top + dy, right, bottom);
                            }

//                            Animation oneAnimationS = AnimationUtils.loadAnimation(MoveActivity.this, R.anim.ll_atvt_main_black_anim_test_x);
//                            tv.startAnimation(oneAnimationS);
                        }else if (!isCenter && (movingY - startY)>0){  //从上方方向下移动
                            //在上方
                            if (top + dy >= 1000){
                                Log.e(TAG, "onTouch: "+"1000" );
                                tv.layout(left, 1000, right, bottom);
                                isCenter = true;
                            }else {
                                Log.e(TAG, "onTouch: "+"1000-" );
                                tv.layout(left, top + dy, right, bottom);
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
                root.invalidate();
                return true;//如果返回true,从手指接触屏幕到手指离开屏幕，将不会触发点击事件。
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "TextView Click", Toast.LENGTH_LONG)
                        .show();
            }
        });
        btn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });
        btn.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e(TAG, "onHover: " + "按下");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e(TAG, "onHover: " + "ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e(TAG, "onHover: " + "ACTION_UP");
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        Log.e(TAG, "onHover: " + "ACTION_CANCEL");
                        break;
                    case MotionEvent.ACTION_OUTSIDE:
                        Log.e(TAG, "onHover: " + "ACTION_OUTSIDE");
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        Log.e(TAG, "onHover: " + "ACTION_POINTER_DOWN");
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        Log.e(TAG, "onHover: " + "ACTION_POINTER_UP");
                        break;
                    case MotionEvent.ACTION_HOVER_MOVE:
                        Log.e(TAG, "onHover: " + "ACTION_HOVER_MOVE");
                        break;
                    case MotionEvent.ACTION_SCROLL:
                        Log.e(TAG, "onHover: " + "ACTION_SCROLL");
                        break;
                    case MotionEvent.ACTION_HOVER_ENTER:
                        Log.e(TAG, "onHover: " + "ACTION_HOVER_ENTER");
                        break;
                    case MotionEvent.ACTION_HOVER_EXIT:
                        Log.e(TAG, "onHover: " + "ACTION_HOVER_EXIT");
                        break;
                    case MotionEvent.ACTION_BUTTON_PRESS:
                        Log.e(TAG, "onHover: " + "ACTION_BUTTON_PRESS");
                        break;
                    case MotionEvent.ACTION_BUTTON_RELEASE:
                        Log.e(TAG, "onHover: " + "ACTION_BUTTON_RELEASE");
                        break;
                }

                return false;
            }
        });
        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
        btn.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

            }
        });
        btn.setOnGenericMotionListener(new View.OnGenericMotionListener() {
            @Override
            public boolean onGenericMotion(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e(TAG, "onGenericMotion: " + "按下");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e(TAG, "onGenericMotion: " + "ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e(TAG, "onGenericMotion: " + "ACTION_UP");
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        Log.e(TAG, "onGenericMotion: " + "ACTION_CANCEL");
                        break;
                    case MotionEvent.ACTION_OUTSIDE:
                        Log.e(TAG, "onGenericMotion: " + "ACTION_OUTSIDE");
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        Log.e(TAG, "onGenericMotion: " + "ACTION_POINTER_DOWN");
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        Log.e(TAG, "onGenericMotion: " + "ACTION_POINTER_UP");
                        break;
                    case MotionEvent.ACTION_HOVER_MOVE:
                        Log.e(TAG, "onGenericMotion: " + "ACTION_HOVER_MOVE");
                        break;
                    case MotionEvent.ACTION_SCROLL:
                        Log.e(TAG, "onGenericMotion: " + "ACTION_SCROLL");
                        break;
                    case MotionEvent.ACTION_HOVER_ENTER:
                        Log.e(TAG, "onGenericMotion: " + "ACTION_HOVER_ENTER");
                        break;
                    case MotionEvent.ACTION_HOVER_EXIT:
                        Log.e(TAG, "onGenericMotion: " + "ACTION_HOVER_EXIT");
                        break;
                    case MotionEvent.ACTION_BUTTON_PRESS:
                        Log.e(TAG, "onGenericMotion: " + "ACTION_BUTTON_PRESS");
                        break;
                    case MotionEvent.ACTION_BUTTON_RELEASE:
                        Log.e(TAG, "onGenericMotion: " + "ACTION_BUTTON_RELEASE");
                        break;
                }
                return false;
            }
        });
        btn.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e(TAG, "onKey: " + "按下");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e(TAG, "onKey: " + "ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e(TAG, "onKey: " + "ACTION_UP");
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        Log.e(TAG, "onKey: " + "ACTION_CANCEL");
                        break;
                    case MotionEvent.ACTION_OUTSIDE:
                        Log.e(TAG, "onKey: " + "ACTION_OUTSIDE");
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        Log.e(TAG, "onKey: " + "ACTION_POINTER_DOWN");
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        Log.e(TAG, "onKey: " + "ACTION_POINTER_UP");
                        break;
                    case MotionEvent.ACTION_HOVER_MOVE:
                        Log.e(TAG, "onKey: " + "ACTION_HOVER_MOVE");
                        break;
                    case MotionEvent.ACTION_SCROLL:
                        Log.e(TAG, "onKey: " + "ACTION_SCROLL");
                        break;
                    case MotionEvent.ACTION_HOVER_ENTER:
                        Log.e(TAG, "onKey: " + "ACTION_HOVER_ENTER");
                        break;
                    case MotionEvent.ACTION_HOVER_EXIT:
                        Log.e(TAG, "onKey: " + "ACTION_HOVER_EXIT");
                        break;
                    case MotionEvent.ACTION_BUTTON_PRESS:
                        Log.e(TAG, "onKey: " + "ACTION_BUTTON_PRESS");
                        break;
                    case MotionEvent.ACTION_BUTTON_RELEASE:
                        Log.e(TAG, "onKey: " + "ACTION_BUTTON_RELEASE");
                        break;
                }
                return false;
            }
        });
        btn.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e(TAG, "onEditorAction: " + "按下");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e(TAG, "onEditorAction: " + "ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e(TAG, "onEditorAction: " + "ACTION_UP");
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        Log.e(TAG, "onEditorAction: " + "ACTION_CANCEL");
                        break;
                    case MotionEvent.ACTION_OUTSIDE:
                        Log.e(TAG, "onEditorAction: " + "ACTION_OUTSIDE");
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        Log.e(TAG, "onEditorAction: " + "ACTION_POINTER_DOWN");
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        Log.e(TAG, "onEditorAction: " + "ACTION_POINTER_UP");
                        break;
                    case MotionEvent.ACTION_HOVER_MOVE:
                        Log.e(TAG, "onEditorAction: " + "ACTION_HOVER_MOVE");
                        break;
                    case MotionEvent.ACTION_SCROLL:
                        Log.e(TAG, "onEditorAction: " + "ACTION_SCROLL");
                        break;
                    case MotionEvent.ACTION_HOVER_ENTER:
                        Log.e(TAG, "onEditorAction: " + "ACTION_HOVER_ENTER");
                        break;
                    case MotionEvent.ACTION_HOVER_EXIT:
                        Log.e(TAG, "onEditorAction: " + "ACTION_HOVER_EXIT");
                        break;
                    case MotionEvent.ACTION_BUTTON_PRESS:
                        Log.e(TAG, "onEditorAction: " + "ACTION_BUTTON_PRESS");
                        break;
                    case MotionEvent.ACTION_BUTTON_RELEASE:
                        Log.e(TAG, "onEditorAction: " + "ACTION_BUTTON_RELEASE");
                        break;
                }


                return false;
            }
        });
        btn.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e(TAG, "onDrag: " + "按下");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e(TAG, "onDrag: " + "ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e(TAG, "onDrag: " + "ACTION_UP");
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        Log.e(TAG, "onDrag: " + "ACTION_CANCEL");
                        break;
                    case MotionEvent.ACTION_OUTSIDE:
                        Log.e(TAG, "onDrag: " + "ACTION_OUTSIDE");
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        Log.e(TAG, "onDrag: " + "ACTION_POINTER_DOWN");
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        Log.e(TAG, "onDrag: " + "ACTION_POINTER_UP");
                        break;
                    case MotionEvent.ACTION_HOVER_MOVE:
                        Log.e(TAG, "onDrag: " + "ACTION_HOVER_MOVE");
                        break;
                    case MotionEvent.ACTION_SCROLL:
                        Log.e(TAG, "onDrag: " + "ACTION_SCROLL");
                        break;
                    case MotionEvent.ACTION_HOVER_ENTER:
                        Log.e(TAG, "onDrag: " + "ACTION_HOVER_ENTER");
                        break;
                    case MotionEvent.ACTION_HOVER_EXIT:
                        Log.e(TAG, "onDrag: " + "ACTION_HOVER_EXIT");
                        break;
                    case MotionEvent.ACTION_BUTTON_PRESS:
                        Log.e(TAG, "onDrag: " + "ACTION_BUTTON_PRESS");
                        break;
                    case MotionEvent.ACTION_BUTTON_RELEASE:
                        Log.e(TAG, "onDrag: " + "ACTION_BUTTON_RELEASE");
                        break;
                }


                return false;
            }
        });
        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e(TAG, "onTouch: " + "按下");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e(TAG, "onTouch: " + "ACTION_MOVE");
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
                return false;
            }
        });


    }
}
