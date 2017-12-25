package com.fpp.status.activity.fragmentthree;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.fpp.status.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThreeFragmentActivity extends AppCompatActivity {


    @BindView(R.id.tv_time)
    TextView tvTime;
    SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");


    private int i = 0;
    private int TIME = 1000;
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                tvTime.setText(Integer.toString(i++));
            }
            super.handleMessage(msg);
        };
    };
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            // 需要做的事:发送消息
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_three);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        timer.schedule(task,0,1000);

    }
}