package com.fpp.status.activity.fragmentthree.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fpp.status.R;

/**
 * Created by fupengpeng on 2017/12/11 0011.
 */

public class AlreadyCancelFragment extends Fragment {

    View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_three, null);
        }
        return mView;
    }
}
