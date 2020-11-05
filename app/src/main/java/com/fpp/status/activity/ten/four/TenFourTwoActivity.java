package com.fpp.status.activity.ten.four;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.fpp.status.R;
import com.fpp.status.view.videoview.MC;
import com.fpp.status.view.videoview.VV;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.fpp.status.R;
import com.fpp.status.activity.ten.seven.TenSevenActivity;


import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.HEAD;

/**
 * Description:
 * Author: fpp
 * Date: 2018/8/11  9:56
 */

public class TenFourTwoActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String SEEK_POSITION_KEY = "SEEK_POSITION_KEY";
    private static final String VIDEO_URL = "http://img.xiaojiangjiakao.com/20180627/7podaodingdiantingchejiaocheng.mp4";

    @BindView(R.id.vv)
    VV vv;
    @BindView(R.id.tvCurrentTime)
    TextView tvCurrentTime;
    @BindView(R.id.seekBarVideo)
    SeekBar seekBarVideo;
    @BindView(R.id.tvTotalTime)
    TextView tvTotalTime;
    @BindView(R.id.seekBarSound)
    SeekBar seekBarSound;
    @BindView(R.id.seekBarLight)
    SeekBar seekBarLight;
    @BindView(R.id.btnForword)
    Button btnForword;
    @BindView(R.id.btnStop)
    Button btnStop;
    @BindView(R.id.btnGoOn)
    Button btnGoOn;
    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.btnQuickBack)
    Button btnQuickBack;
    @BindView(R.id.btnGetCurrent)
    Button btnGetCurrent;
    @BindView(R.id.btnQuickForword)
    Button btnQuickForword;
    @BindView(R.id.tvPlayTime)
    TextView tvPlayTime;
    @BindView(R.id.tvVideoInfo)
    TextView tvVideoInfo;

    private MC mc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ten_four_two);
        ButterKnife.bind(this);

        mc = new MC(this);
        String url =  "http://img.xiaojiangjiakao.com/20180627/7podaodingdiantingchejiaocheng.mp4";
        Uri uri = Uri.parse(url);
        vv.setVideoURI(uri);
        vv.setMediaController(mc);
        mc.setMediaPlayer(vv);

    }

    @OnClick({R.id.vv, R.id.tvCurrentTime, R.id.seekBarVideo, R.id.tvTotalTime, R.id.seekBarSound, R.id.seekBarLight, R.id.btnForword, R.id.btnStop, R.id.btnGoOn, R.id.btnNext, R.id.btnQuickBack, R.id.btnGetCurrent, R.id.btnQuickForword, R.id.tvPlayTime, R.id.tvVideoInfo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.vv:
                break;
            case R.id.tvCurrentTime:
                break;
            case R.id.seekBarVideo:
                break;
            case R.id.tvTotalTime:
                break;
            case R.id.seekBarSound:
                break;
            case R.id.seekBarLight:
                break;
            case R.id.btnForword:
                break;
            case R.id.btnStop:
                break;
            case R.id.btnGoOn:
                break;
            case R.id.btnNext:
                break;
            case R.id.btnQuickBack:
                break;
            case R.id.btnGetCurrent:
                break;
            case R.id.btnQuickForword:
                break;
            case R.id.tvPlayTime:
                break;
            case R.id.tvVideoInfo:
                break;
        }
    }


    /**
     * 多个按钮信息框
     **/
    private static final int DIALOG_1 = 2;
    /**列表框 **/

    private static final int DIALOG_2 = 3;
    /**进度条框 **/

    private static final int DIALOG_3 = 4;
    /**单项选择列表框 **/

    private static final int DIALOG_4 = 5;
    /**多项选择列表框 **/

    private static final int DIALOG_5 = 6;
    /**自定义布局 **/

    private static final int DIALOG_6 = 7;
    /**读取进度框 **/

    private static final int DIALOG_7 = 8;
    /**自定义布局 **/

    private static final int DIALOG_8 = 9;
    /**读取进度框 **/
       
    private static final int DIALOG_9 = 10;


    private ProgressDialog pDialog;
    private DatePickerDialog dDialog;
    private TimePickerDialog tDialog;
    private Calendar c;
    final String[] items = {"item0", "item1", "itme2", "item3", "itme4"};
    ArrayList<Integer> MultiChoiceID = new ArrayList<Integer>();

}
