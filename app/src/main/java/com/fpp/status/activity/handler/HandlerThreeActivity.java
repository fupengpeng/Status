package com.fpp.status.activity.handler;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fpp.status.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HandlerThreeActivity extends AppCompatActivity {

    public static final int INT = 46;
    public static final int INT1 = 45;
    String TAG = "HandlerThreeActivity";
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.imageView1)
    ImageView imageView1;
    @BindView(R.id.tv_show_msg)
    TextView tvShowMsg;


    private Button button;
    private ImageView imageView;
    private String image_path = "http://www.deskcar.com/desktop/fengjing/200895150214/21.jpg";
    private final int IS_FINSIH = 1;
    private ProgressDialog dialog = null;


    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 3. 主线程接收到子线程发送的消息，处理UI
            refreshUI();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_three);
        ButterKnife.bind(this);


    }



    private Runnable mRunnable = new Runnable() {
        public void run() {
            while (true) {
                try {
                    // 2.计时，睡眠 1 毫秒给主线程发送一个消息
                    Thread.sleep(1000);
//                    Bundle data = new Bundle();  //message也可以携带复杂一点的数据比如：bundle对象。
//                    data.putString("msg","我是消息");
//                    message.setData(data);
//                    message.sendToTarget(); // 不可忘！
                    mHandler.sendMessage(mHandler.obtainMessage());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    private void refreshUI() {
        tvShowMsg.setText("接收到消息，更改ui");

    }


    @OnClick({R.id.button1, R.id.imageView1, R.id.tv_show_msg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:

                //1.开启子线程，执行任务
                new Thread(mRunnable).start();
                break;
            case R.id.imageView1:
                break;
            case R.id.tv_show_msg:
                break;
        }
    }
}
