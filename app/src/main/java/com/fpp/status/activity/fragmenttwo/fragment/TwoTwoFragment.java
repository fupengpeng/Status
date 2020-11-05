package com.fpp.status.activity.fragmenttwo.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fpp.status.R;

/**
 * Created by fp on 2017/12/10.
 */

public class TwoTwoFragment extends Fragment {
     @Override
     public View onCreateView(LayoutInflater inflater,
                              @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         return inflater.inflate(R.layout.fragment_two_two, container, false);
     }

 }