package com.fpp.status.view.videoview;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.MediaFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Looper;
import android.support.v4.util.Pair;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Vector;


/**
 * Description:
 * Author: fpp
 * Date: 2018/8/11  10:42
 */

public class VV  extends VideoView{
    private static final String TAG = "VideoView";


    public VV(Context context) {
        this(context, null);
    }

    public VV(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VV(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public VV(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

}

