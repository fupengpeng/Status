package com.fpp.status.activity.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.fpp.status.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HandlerFourActivity extends AppCompatActivity {

    String TAG = "HandlerFourActivity";

    @BindView(R.id.btn_one_one)
    Button btnOneOne;
    @BindView(R.id.btn_one_two)
    Button btnOneTwo;
    @BindView(R.id.btn_one_three)
    Button btnOneThree;
    @BindView(R.id.btn_one_four)
    Button btnOneFour;
    @BindView(R.id.btn_one_five)
    Button btnOneFive;
    @BindView(R.id.btn_one_six)
    Button btnOneSix;
    @BindView(R.id.btn_one_seven)
    Button btnOneSeven;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_four);
        ButterKnife.bind(this);


    }

    // handler对象，用来接收消息~

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {  //这个是发送过来的消息
            // 处理从子线程发送过来的消息
            int arg1 = msg.arg1;  //获取消息携带的属性值
            int arg2 = msg.arg2;
            int what = msg.what;
            Object result = msg.obj;
            Log.e(TAG, "handleMessage: " + "-arg1--->>" + arg1 );
            Log.e(TAG, "handleMessage: " + "-arg2--->>" + arg2 );
            Log.e(TAG, "handleMessage: " + "-what--->>" + what );
            Log.e(TAG, "handleMessage: " + "-result--->>" + result );
            Bundle bundle = msg.getData(); // 用来获取消息里面的bundle数据
            Log.e(TAG, "handleMessage: " + "-getData--->>"
                    + bundle.getStringArray("strs").length );


        }
    };

    @OnClick({R.id.btn_one_one, R.id.btn_one_two, R.id.btn_one_three, R.id.btn_one_four, R.id.btn_one_five, R.id.btn_one_six, R.id.btn_one_seven})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_one_one:
                // 启动一个子线程
                new Thread(new MyThread()).start();  //一定要在这里面启动！
                break;
            case R.id.btn_one_two:
                break;
            case R.id.btn_one_three:
                break;
            case R.id.btn_one_four:
                break;
            case R.id.btn_one_five:
                break;
            case R.id.btn_one_six:
                break;
            case R.id.btn_one_seven:
                break;
        }
    }


    public class MyThread implements Runnable {
        @Override
        public void run() {   //run方法里面写要发送的消息对象，并对消息携带的信息进行定义！！
            // TODO Auto-generated method stub

            // 第一种方式：获取消息
            // Message message = Message.obtain();
            // message.what = 1;
            // message.arg1 = 2;
            // message.arg2 = 3;
            // message.obj = "jack";
            // handler.sendMessage(message);

            // 第二种方式
            // Message message = Message.obtain(handler);
            // message.what = 1;
            // message.arg1 = 2;
            // message.arg2 = 3;
            // message.obj = "jack";
            // //handler.sendMessage(message);
            // //此时在构造方法里面已经将message的target绑定了handler不需要再次发送了。
            // message.sendToTarget();

            // 第三种方式，和上面是没有区别的。。
            // Message message = Message.obtain(handler, 33);
            // message.arg1 = 2;
            // message.arg2 = 3;
            // message.obj = "jack";
            // message.sendToTarget();

            // 第4种方式这几种方式都是大同小异，只不过是内部封装了而已，使用的时候根据实际需要就可以了。
            Message message = Message.obtain(handler, 33, 2, 3, "hello");
            Bundle data = new Bundle();  //message也可以携带复杂一点的数据比如：bundle对象。
            data.putStringArray("strs", new String[]{"c", "c++", "java"});
            message.setData(data);
            message.sendToTarget(); // 不可忘！
        }
    }


}
