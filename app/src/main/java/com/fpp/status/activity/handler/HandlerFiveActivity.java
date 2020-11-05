package com.fpp.status.activity.handler;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.fpp.status.R;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class HandlerFiveActivity extends Activity implements OnClickListener {


    private EditText inputTime;
    private TextView showTime;
    private Button ensureTime,startTime,stopTime;
    private int i;//显示的倒计时数字
    private boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_five);
        initView();//实例化控件
    }
    private void initView() {
        inputTime = (EditText) findViewById(R.id.inputTime);
        showTime = (TextView) findViewById(R.id.showTime);
        ensureTime = (Button) findViewById(R.id.ensureTime);
        startTime = (Button) findViewById(R.id.startTime);
        stopTime = (Button) findViewById(R.id.stopTime);

        /**注册监听事件*/

        ensureTime.setOnClickListener(this);
        startTime.setOnClickListener(this);
        stopTime.setOnClickListener(this);
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            /**点击按钮事件监听*/

            case R.id.ensureTime:
                showTime.setText(inputTime.getText().toString());
                i=Integer.parseInt(inputTime.getText().toString());
                break;

            /**开始按钮事件监听*/

            case R.id.startTime:
                flag=true;
                startTime();
                break;

            /**停止按钮事件监听*/

            case R.id.stopTime:
                stopTime();
                break;
        }
    }
    final Handler handler=new Handler(){
        public void handleMessage(Message msg) {
            int p=msg.what;
            showTime.setText(p+"");
        };
    };
    /**开始计时方法*/
    private void startTime() {

        /**开启一个新线程*/

        new Thread(){
            public void run() {

                /**每睡眠1秒后发送Message给Handler处理*/

                for(int j=i;j>=0;j--){
                    if(flag==true){
                        try {
                            Thread.sleep(1000);
                            Message msg=new Message();
                            msg.what=j;//设置Message附带的参数
                            handler.sendMessage(msg);//发送Message对象给Handler
                            i=j;//将当前的时间传递给全局时间变量
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                }
            }
        }.start();
    }

    /**停止计时方法，通过设置boolean标志为false来停止*/

    @SuppressLint("NewApi")
    private void stopTime(){
        flag=false;
    };









//    private EditText inputTime;
//    private TextView showTime;
//    private Button ensureTime, startTime, stopTime;
//    private Timer timer = null;
//    private TimerTask task = null;
//    private int i;//显示的倒计时数字
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_handler_five);
//        initView();//实例化控件
//    }
//
//    /**
//     * 实例化控件方法
//     */
//
//    private void initView() {
//        inputTime = (EditText) findViewById(R.id.inputTime);
//        showTime = (TextView) findViewById(R.id.showTime);
//        ensureTime = (Button) findViewById(R.id.ensureTime);
//        startTime = (Button) findViewById(R.id.startTime);
//        stopTime = (Button) findViewById(R.id.stopTime);
//
//        /**注册监听事件*/
//        ensureTime.setOnClickListener(this);
//        startTime.setOnClickListener(this);
//        stopTime.setOnClickListener(this);
//    }


//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            /**当选择点击按钮的监听事件*/
//            case R.id.ensureTime:
//                showTime.setText(inputTime.getText().toString());
//                i = Integer.parseInt(inputTime.getText().toString());
//                break;
//            /**当选择开始计时按钮的监听事件*/
//
//            case R.id.startTime:
//                startTime();
//                break;
//            case R.id.stopTime:
//                stopTime();
//                break;
//        }
//    }


//    /**
//     * 当选择停止计时按钮的监听事件
//     */
//    private Handler handler = new Handler() {
//        /**重写handleMessage方法*/
//        @Override
//        public void handleMessage(Message msg) {
//            showTime.setText(msg.arg1 + "");
//            startTime();//执行计时方法
//        }
//    };

//    /**
//     * 开始计时方法
//     */
//
//    private void startTime() {
//        timer = new Timer();
//        task = new TimerTask() {
//            @Override
//            public void run() {
//                i--;
//                Message message = handler.obtainMessage();//获取Message对象
//                message.arg1 = i;//设置Message对象附带的参数
//                handler.sendMessage(message);//向主线程发送消息
//            }
//        };
//        timer.schedule(task, 1000);//执行计时器事件
//    }
//
//
//    /**
//     * 停止计时方法
//     */
//
//    private void stopTime() {
//        timer.cancel();//注销计时器事件
//    }



}