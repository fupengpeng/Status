package com.fpp.status.activity.nine.one;

import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by fp on 2018/5/3.
 */

public class Test {

    MediaPlayer mediaPlayer = new MediaPlayer();

    public void test() {
        try {
            mediaPlayer.reset();
            // 装载下一首歌曲
            mediaPlayer.setDataSource("/mnt/sdcard/next.mp3");
            // 准备声音
            mediaPlayer.prepare();
            // 播放
            mediaPlayer.start();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


}
