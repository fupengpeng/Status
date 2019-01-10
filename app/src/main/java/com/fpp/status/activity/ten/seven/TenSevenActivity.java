package com.fpp.status.activity.ten.seven;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.fpp.status.R;

import java.io.IOError;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description:
 * Author: fpp
 * Date: 2018/8/13  14:39
 */

public class TenSevenActivity extends AppCompatActivity {


    MediaPlayer mediaPlayer;

    int position;

    @BindView(R.id.surfaceView)
    SurfaceView surfaceView;
    @BindView(R.id.bofang)
    Button bofang;
    @BindView(R.id.zanting)
    Button zanting;
    @BindView(R.id.tingzhi)
    Button tingzhi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ten_seven);
        ButterKnife.bind(this);


        mediaPlayer = new MediaPlayer();
        surfaceView.getHolder().setKeepScreenOn(true);
        surfaceView.getHolder().addCallback(new SurfaceListener());

    }

    @Override
    protected void onPause() {
        if (mediaPlayer.isPlaying()) {
            // 保存当前播放位置
            position = mediaPlayer.getCurrentPosition();
            mediaPlayer.stop();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        // 停止播放
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        // 释放资源
        mediaPlayer.release();


        super.onDestroy();
    }


    @OnClick({R.id.bofang, R.id.zanting, R.id.tingzhi})
    public void onViewClicked(View view) {
        try {
            switch (view.getId()) {
                case R.id.bofang:
                    bofang();
                    break;
                case R.id.zanting:
                    if (mediaPlayer.isPlaying()){
                        mediaPlayer.pause();
                    }else {
                        mediaPlayer.start();
                    }
                    break;
                case R.id.tingzhi:
                    if (mediaPlayer.isPlaying()){
                        mediaPlayer.stop();
                    }

                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bofang() throws IOException {
        mediaPlayer.reset();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        // 设置需要播放的视频
        mediaPlayer.setDataSource("/storage/emulated/0/Download/烟花易冷---周杰伦.mp4");
        // 把视频画面输出到SurfaceView
        mediaPlayer.setDisplay(surfaceView.getHolder());
        mediaPlayer.prepare();
        // 获取窗口管理器
        WindowManager windowManager = getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        // 获取屏幕大小
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        // 设置视频保持横纵比缩放到占满整个屏幕
//        surfaceView.setLayoutParams(new WindowManager.LayoutParams(displayMetrics.widthPixels
//                , mediaPlayer.getVideoHeight() * displayMetrics.widthPixels / mediaPlayer.getVideoWidth()));

        mediaPlayer.start();


    }

    class SurfaceListener implements SurfaceHolder.Callback {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            if (position > 0) {
                try {
                    // 开始播放
                    bofang();
                    // 从指定位置开始
                    mediaPlayer.seekTo(position);
                    position = 0;


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }


}
