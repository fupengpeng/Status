package com.fpp.status.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fpp.status.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 */
public class TwoFragment extends Fragment {

    @BindView(R.id.btn_fragment_two)
    Button btnFragmentTwo;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_fragment_two)
    public void onViewClicked() {

//        ThreeFragment fThree = new ThreeFragment();
//        FragmentManager fm = getFragmentManager();
//        FragmentTransaction tx = fm.beginTransaction();
//        tx.hide(this);  //隐藏当前fragment
//        tx.add(R.id.ll_parent , fThree, "THREE");  //添加threefragment
////      tx.replace(R.id.id_content, fThree, "THREE");
//        tx.addToBackStack(null);
//        tx.commit();


        /*------------Fragment与Activity之间的通信---------------*/
        if(fTwoBtnClickListener != null)
        {
            fTwoBtnClickListener.onFTwoBtnClick();
        }
        /*------------Fragment与Activity之间的通信---------------*/

    }



    /*------------Fragment与Activity之间的通信---------------*/

    private FTwoBtnClickListener fTwoBtnClickListener ;

    public interface FTwoBtnClickListener
    {
        void onFTwoBtnClick();
    }
    //设置回调接口
    public void setfTwoBtnClickListener(FTwoBtnClickListener fTwoBtnClickListener)
    {
        this.fTwoBtnClickListener = fTwoBtnClickListener;
    }



    /*------------Fragment与Activity之间的通信---------------*/






}
