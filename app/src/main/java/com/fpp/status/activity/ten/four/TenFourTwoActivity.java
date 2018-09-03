package com.fpp.status.activity.ten.four;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

/**
 * Description:
 * Author: fpp
 * Date: 2018/8/11  9:56
 */

public class TenFourTwoActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String SEEK_POSITION_KEY = "SEEK_POSITION_KEY";
    private static final String VIDEO_URL = "http://img.xiaojiangjiakao.com/20180627/7podaodingdiantingchejiaocheng.mp4";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ten_four_two);
        ButterKnife.bind(this);

        String url = "http://img.xiaojiangjiakao.com/20180627/7podaodingdiantingchejiaocheng.mp4";


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
