package com.fpp.status.activity.fragmenteight.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fpp.status.R;
import com.fpp.status.fragment.BaseFragment;


import androidx.annotation.Nullable;
import butterknife.BindView;

/**
 * 待接单
 */

public class WaitAcceptFragment extends BaseFragment {



    /**
     * 空数据提示
     */
    @BindView(R.id.tv_fragment_wait_accept_order_empty)
    TextView tvFragmentWaitAcceptOrderEmpty;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState, R.layout.fragment_wait_accept_order);


        return view;
    }


}
