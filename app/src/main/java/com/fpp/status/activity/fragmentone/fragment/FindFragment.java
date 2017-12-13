package com.fpp.status.activity.fragmentone.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fpp.status.R;

/**
 * Created by fp on 2017/12/10.
 */

public class FindFragment extends Fragment {

    View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_find, null);
        }
        ((TextView) mView.findViewById(R.id.mTextView)).setText("聊天界面");
        return mView;
    }
}