package com.fpp.status.activity.handler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.fpp.status.R;

public class MHandlerTwoActivity extends AppCompatActivity {

    private static final int UPDATE = 1;
    Handler mHandler;
    TextView mText;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mhandler_two);
        mHandler = new Handler();//创建Handler
        mText = (TextView) findViewById(R.id.text0);//一个TextView
    }

    Runnable mRunnable0 = new Runnable()
    {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            mText.setText("This is Update from ohter thread, Mouse DOWN");
        }
    };


    private void updateUIByRunnable(){
        new Thread()
        {
            //Message msg = mHandler.obtainMessage();
            public void run()
            {

                //mText.setText("This is Update from ohter thread, Mouse DOWN");//这句将抛出异常
                mHandler.post(mRunnable0);
            }
        }.start();

    }


    private class MyHandler extends Handler
    {



        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch(msg.what)
            {
                case UPDATE://在收到消息时，对界面进行更新
                    mText.setText("This update by message");
                    break;
            }
        }
    }

    private void updateByMessage()
    {
        //匿名对象
        new Thread()
        {
            public void run()
            {
                //mText.setText("This is Update from ohter thread, Mouse DOWN");

                //UPDATE是一个自己定义的整数，代表了消息ID
                Message msg = mHandler.obtainMessage(UPDATE);
                mHandler.sendMessage(msg);
            }
        }.start();
    }
}
