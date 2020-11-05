package com.fpp.status.activity.fragmentthree.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fpp.status.R;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by fupengpeng on 2017/12/11 0011.
 */

public class AllFragment extends Fragment {

    View mView;

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_one, null);
        }

        /*
        1.获取服务开始时间
        2.获取当前时间
        3.当前时间除以1000，换算成秒
        4.当前时间减去服务开始时间
        5.展示
        6.每过一秒自加一秒
        7.设置textview
         */


        unbinder = ButterKnife.bind(this, mView);
        return mView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
