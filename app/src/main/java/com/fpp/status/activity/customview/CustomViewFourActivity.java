package com.fpp.status.activity.customview;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.Chronometer;

import com.fpp.status.R;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomViewFourActivity extends AppCompatActivity {

    @BindView(R.id.chronometer)
    Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_four);
        ButterKnife.bind(this);


        chronometer.setBase(new Date().getTime());//计时器清零
        int hour = (int) ((SystemClock.elapsedRealtime() - chronometer.getBase()) / 1000 / 60);
        chronometer.setFormat("0"+String.valueOf(hour)+":%s");
        chronometer.start();

    }
}
