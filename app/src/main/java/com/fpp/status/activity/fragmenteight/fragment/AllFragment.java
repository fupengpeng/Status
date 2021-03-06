package com.fpp.status.activity.fragmenteight.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fpp.status.R;
import com.fpp.status.fragment.BaseFragment;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 全部
 */

public class AllFragment extends BaseFragment {


    /**
     * 空数据提示
     */
    @BindView(R.id.tv_data_empty)
    TextView tvDataEmpty;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState, R.layout.fragment_all);

        return view;
    }



}
