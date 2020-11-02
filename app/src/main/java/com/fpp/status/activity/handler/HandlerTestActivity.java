package com.fpp.status.activity.handler;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fpp.status.R;
import com.fpp.status.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HandlerTestActivity extends AppCompatActivity {


    String TAG = "HandlerTestActivity";

    @BindView(R.id.iv_atvt_handler_test)
    ImageView ivAtvtHandlerTest;
    @BindView(R.id.tv_atvt_handler)
    TextView tvAtvtHandler;
    @BindView(R.id.et_atvt_handler)
    EditText etAtvtHandler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_test);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        initViewOne();
        initViewTwo();
    }

    @OnClick({R.id.iv_atvt_handler_test, R.id.tv_atvt_handler})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_atvt_handler_test:

                break;
            case R.id.tv_atvt_handler:
                LogUtils.e("--------------------");
                // 3-1、创建消息
                Message msg = new Message();
                msg.what = 1002;
                Bundle bundle = new Bundle();
                bundle.putInt(UPPER_NUM, Integer.parseInt(etAtvtHandler.getText().toString()));
                msg.setData(bundle);
                // 3-2、向新线程中的Handler发送消息
                mHandler.sendMessage(msg);
                break;
        }
    }

    @OnClick(R.id.tv_atvt_handler)
    public void onViewClicked() {
        LogUtils.e("--------------------");
        // 3-1、创建消息
        Message msg = new Message();
        msg.what = 1002;
        Bundle bundle = new Bundle();
        bundle.putInt(UPPER_NUM, Integer.parseInt(etAtvtHandler.getText().toString()));
        msg.setData(bundle);
        // 3-2、向新线程中的Handler发送消息
        mHandler.sendMessage(msg);
    }

    int[] imageIds = new int[]{
            R.drawable.guide_01,
            R.drawable.guide_02,
            R.drawable.guide_03
    };
    int currentImageId = 0;

    private void initViewOne() {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // 2、如果消息是本程序所发送的
                if (msg.what == 1001) {
                    // 3、动态修改所要显示的图片
                    ivAtvtHandlerTest.setImageResource(imageIds[currentImageId++ % imageIds.length]);
                }
            }
        };

        // 1、开启新线程设定指定时间间隔，发送消息给主线程
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // 发送空消息
                handler.sendEmptyMessage(1001);
            }
        }, 0, 1200);

    }


    private ChildThread mChildThread;

    private void initViewTwo() {
        // 2-1、初始化子线程
        mChildThread = new ChildThread();
        // 2-2、启动子线程
        mChildThread.start();
    }

    boolean isMoves = true;
    public static final String UPPER_NUM = "upper";
    private Handler mHandler;

    class ChildThread extends Thread {
        @Override
        public void run() {
            Log.e(TAG, "run: isMoves = " + isMoves);
            while (isMoves) {
                // 1-1、创建Looper对象
                Looper.prepare();
                // 1-2、实例化Handler对象，并接收消息，处理消息
                mHandler = new Handler() {
                    // 处理消息
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        // 主线程接收到子线程发送的消息，处理UI
                        if (msg.what == 1002) {
                            int upper = msg.getData().getInt(UPPER_NUM);
                            List<Integer> nums = new ArrayList<Integer>();
                            // 计算从2开始到upper的所有质数
                            outer:
                            for (int i = 2; i <= upper; i++) {
                                // 用i除以从2开始、到i的平方根的所有数
                                for (int j = 2; j <= Math.sqrt(i); j++) {
                                    // 如果可以整除，则表明这个数不是质数
                                    if (i != 2 && i % j == 0) {
                                        continue outer;
                                    }
                                }
                                nums.add(i);
                            }

                            LogUtils.e("显示所有的质数 ", " " + nums.toString());

                        }

                    }
                };
                // 1-3、启动取出消息方法，并将消息分发给对应的Handler进行处理
                Looper.loop();

            }
        }
    }


}
