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
public class OneFragment extends Fragment {


    @BindView(R.id.btn_fragment_one)
    Button btnFragmentOne;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_fragment_one)
    public void onViewClicked() {

//        TwoFragment fTwo = new TwoFragment();
//        FragmentManager fm = getFragmentManager();
//        FragmentTransaction tx = fm.beginTransaction();
//        tx.replace(R.id.ll_parent, fTwo, "TWO");
//        tx.addToBackStack(null);
//        tx.commit();


        /*------------Fragment与Activity之间的通信---------------*/

        if (getActivity() instanceof FOneBtnClickListener) {
            ((FOneBtnClickListener) getActivity()).onFOneBtnClick();
        }
        /*------------Fragment与Activity之间的通信---------------*/


    }


    /*------------Fragment与Activity之间的通信---------------*/
    /**
     * 设置按钮点击的回调
     * @author zhy
     *
     */
    public interface FOneBtnClickListener {
        void onFOneBtnClick();
    }




    /*------------Fragment与Activity之间的通信---------------*/



}
