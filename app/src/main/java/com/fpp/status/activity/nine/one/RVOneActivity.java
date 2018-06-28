package com.fpp.status.activity.nine.one;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.fpp.status.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:  普通 RecyclerView
 * Author: fpp
 * Date: 2018/6/8  16:52
 */

public class RVOneActivity extends AppCompatActivity {

    @BindView(R.id.prrv_atvt_rv_one)
    PullRefreshRecyclerView prrvAtvtRvOne;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_one);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {

    }

}
