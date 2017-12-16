package com.fpp.status.activity.fragmenteight.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 已完成
 */

public class AlreadyAccomplishFragment extends BaseFragment {

    /**
     * 时间格式
     */
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");

    /**
     * 当前页
     */
    private int currentPage;

    /**
     * 总页数
     */
    private int totalPage;



    /**
     * 空数据提示
     */
    @BindView(R.id.tv_fragment_already_accomplish_empty)
    TextView tvFragmentAlreadyAccomplishEmpty;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState, R.layout.fragment_already_accomplish);

        return view;
    }


}
