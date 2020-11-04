package com.fpp.status.activity.ten.five;

<<<<<<< HEAD
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fpp.status.R;
import com.fpp.status.activity.ten.five.costom.CMC;
import com.fpp.status.activity.ten.five.costom.CVV;
import com.fpp.status.utils.LogUtil;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
=======
import android.support.v7.app.AppCompatActivity;
>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c

/**
 * Description:  视频播放
 * Author: fpp
 * Date: 2018/7/20  8:43
 */

<<<<<<< HEAD
public class VideoActivity  extends AppCompatActivity
        implements CVV.VVC{

    private static final String TAG = "MainActivity";
    private static final String SEEK_POSITION_KEY = "SEEK_POSITION_KEY";
    private static final String VIDEO_URL = "http://img.xiaojiangjiakao.com/20180627/7podaodingdiantingchejiaocheng.mp4";

    CVV mVideoView;
    CMC mMediaController;

    View mBottomLayout;
    View mVideoLayout;
    TextView mStart;

    private int mSeekPosition;
    private int cachedHeight;
    private boolean isFullscreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        mVideoLayout = findViewById(R.id.video_layout);
        mBottomLayout = findViewById(R.id.bottom_layout);
        mVideoView = (CVV) findViewById(R.id.videoView);
        mMediaController = (CMC) findViewById(R.id.media_controller);
        mVideoView.setMediaController(mMediaController);
        setVideoAreaSize();
        mVideoView.setVideoViewCallback(this);
        mStart = (TextView) findViewById(R.id.start);

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSeekPosition > 0) {
                    mVideoView.seekTo(mSeekPosition);
                }
                mVideoView.start();
                mMediaController.setTitle("Big Buck Bunny");
            }
        });

        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                LogUtil.e(TAG, "onCompletion ");
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d(TAG, "onPause ");
        if (mVideoView != null && mVideoView.isPlaying()) {
            mSeekPosition = mVideoView.getCurrentPosition();
            LogUtil.d(TAG, "onPause mSeekPosition=" + mSeekPosition);
            mVideoView.pause();
        }
    }

    /**
     * 置视频区域大小
     */
    private void setVideoAreaSize() {
        mVideoLayout.post(new Runnable() {
            @Override
            public void run() {
                int width = mVideoLayout.getWidth();
                cachedHeight = (int) (width * 405f / 720f);
//                cachedHeight = (int) (width * 3f / 4f);
//                cachedHeight = (int) (width * 9f / 16f);
                ViewGroup.LayoutParams videoLayoutParams = mVideoLayout.getLayoutParams();
                videoLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                videoLayoutParams.height = cachedHeight;
                mVideoLayout.setLayoutParams(videoLayoutParams);
                mVideoView.setVideoPath(VIDEO_URL);
                mVideoView.requestFocus();
            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtil.d(TAG, "onSaveInstanceState Position=" + mVideoView.getCurrentPosition());
        outState.putInt(SEEK_POSITION_KEY, mSeekPosition);
    }

    @Override
    protected void onRestoreInstanceState(Bundle outState) {
        super.onRestoreInstanceState(outState);
        mSeekPosition = outState.getInt(SEEK_POSITION_KEY);
        LogUtil.d(TAG, "onRestoreInstanceState Position=" + mSeekPosition);
    }


    @Override
    public void onScaleChange(boolean isFullscreen) {
        this.isFullscreen = isFullscreen;
        if (isFullscreen) {
            ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            mVideoLayout.setLayoutParams(layoutParams);
            mBottomLayout.setVisibility(View.GONE);

        } else {
            ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = this.cachedHeight;
            mVideoLayout.setLayoutParams(layoutParams);
            mBottomLayout.setVisibility(View.VISIBLE);
        }

        switchTitleBar(!isFullscreen);
    }

    private void switchTitleBar(boolean show) {
       ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            if (show) {
                supportActionBar.show();
            } else {
                supportActionBar.hide();
            }
        }
    }

    @Override
    public void onPause(MediaPlayer mediaPlayer) {
        LogUtil.d(TAG, "onPause UniversalVideoView callback");
    }

    @Override
    public void onStart(MediaPlayer mediaPlayer) {
        LogUtil.d(TAG, "onStart UniversalVideoView callback");
    }

    @Override
    public void onBufferingStart(MediaPlayer mediaPlayer) {
        LogUtil.d(TAG, "onBufferingStart UniversalVideoView callback");
    }

    @Override
    public void onBufferingEnd(MediaPlayer mediaPlayer) {
        LogUtil.d(TAG, "onBufferingEnd UniversalVideoView callback");
    }

    @Override
    public void finishActivity() {

    }

    @Override
    public void onBackPressed() {
        if (this.isFullscreen) {
            mVideoView.setFullscreen(false);
        } else {
            super.onBackPressed();
        }
    }
=======
public class VideoActivity extends AppCompatActivity {
>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c

}

