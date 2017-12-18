package com.fpp.status.activity.fragmenteight.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.fpp.status.R;
import com.fpp.status.fragment.BaseFragment;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * 已取消
 */

public class AlreadyCancelFragment extends BaseFragment {


    /**
     * 空数据提示
     */
    @BindView(R.id.tv_fragment_already_cancel_empty)
    TextView tvFragmentAlreadyCancelEmpty;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState, R.layout.fragment_already_cancel);


        return view;
    }

}