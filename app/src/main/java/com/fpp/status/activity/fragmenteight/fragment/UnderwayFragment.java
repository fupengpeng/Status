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
 * 进行中
 */

public class UnderwayFragment extends BaseFragment {

    @BindView(R.id.tv_fragment_underway_empty)
    TextView tvFragmentUnderwayEmpty;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState, R.layout.fragment_underway);

        return view;
    }



}
