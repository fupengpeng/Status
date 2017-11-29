package com.fpp.status;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.fpp.status.fragment.FourFragment;
import com.fpp.status.fragment.OneFragment;
import com.fpp.status.fragment.ThreeFragment;
import com.fpp.status.fragment.TwoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DLActivity extends AppCompatActivity implements OneFragment.FOneBtnClickListener,
        TwoFragment.FTwoBtnClickListener {


    @BindView(R.id.ll_parent)
    LinearLayout llParent;
    @BindView(R.id.btn_one)
    Button btnOne;
    @BindView(R.id.btn_two)
    Button btnTwo;
    @BindView(R.id.btn_three)
    Button btnThree;
    @BindView(R.id.btn_four)
    Button btnFour;

    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;
    private FourFragment fourFragment;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dl);
//        ButterKnife.bind(this);
//        setDefaultFragment();
//
//        oneFragment = new OneFragment();
//        FragmentManager fm = getFragmentManager();
//        FragmentTransaction tx = fm.beginTransaction();
//        tx.add(R.id.ll_parent, oneFragment, "ONE");
//        tx.commit();
//    }
//
//    @OnClick({R.id.ll_parent, R.id.btn_one, R.id.btn_two, R.id.btn_three, R.id.btn_four})
//    public void onViewClicked(View view) {
//        FragmentManager fm = getFragmentManager();
//        // 开启Fragment事务
//        FragmentTransaction transaction = fm.beginTransaction();
//        switch (view.getId()) {
//            case R.id.ll_parent:
//                if (oneFragment == null) {
//                    oneFragment = new OneFragment();
//                }
//                // 使用当前Fragment的布局替代id_content的控件
//                transaction.replace(R.id.ll_parent, oneFragment);
//                break;
//            case R.id.btn_one:
//                if (oneFragment == null) {
//                    oneFragment = new OneFragment();
//                }
//                // 使用当前Fragment的布局替代id_content的控件
//                transaction.replace(R.id.ll_parent, oneFragment);
//                break;
//            case R.id.btn_two:
//                if (twoFragment == null) {
//                    twoFragment = new TwoFragment();
//                }
//                // 使用当前Fragment的布局替代id_content的控件
//                transaction.replace(R.id.ll_parent, twoFragment);
//                break;
//            case R.id.btn_three:
//                if (threeFragment == null) {
//                    threeFragment = new ThreeFragment();
//                }
//                // 使用当前Fragment的布局替代id_content的控件
//                transaction.replace(R.id.ll_parent, threeFragment);
//                break;
//            case R.id.btn_four:
//                if (fourFragment == null) {
//                    fourFragment = new FourFragment();
//                }
//                // 使用当前Fragment的布局替代id_content的控件
//                transaction.replace(R.id.ll_parent, fourFragment);
//                break;
//        }
//        transaction.commit();
//    }
//    private void setDefaultFragment()
//    {
//        FragmentManager fm = getFragmentManager();
//        FragmentTransaction transaction = fm.beginTransaction();
//        oneFragment = new OneFragment();
//        transaction.replace(R.id.ll_parent, oneFragment);
//        transaction.commit();
//    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dl);
        ButterKnife.bind(this);

        oneFragment = new OneFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        tx.add(R.id.ll_parent, oneFragment, "ONE");
        tx.commit();
    }



    /**
     * FragmentOne 按钮点击时的回调
     */
    @Override
    public void onFOneBtnClick()
    {

        if (twoFragment == null)
        {
            twoFragment = new TwoFragment();
            twoFragment.setfTwoBtnClickListener(this);
        }
        FragmentManager fm = getFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        tx.replace(R.id.ll_parent, twoFragment, "TWO");
        tx.addToBackStack(null);
        tx.commit();
    }

    /**
     * FragmentTwo 按钮点击时的回调
     */
    @Override
    public void onFTwoBtnClick()
    {
        if (threeFragment == null)
        {
            threeFragment = new ThreeFragment();

        }
        FragmentManager fm = getFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        tx.hide(twoFragment);
        tx.add(R.id.ll_parent,threeFragment, "THREE");
        // tx.replace(R.id.id_content, fThree, "THREE");
        tx.addToBackStack(null);
        tx.commit();
    }



}
