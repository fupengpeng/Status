package com.fpp.status.newmvp;


import android.os.Handler;
import android.os.Looper;

import com.fpp.status.basemvp.BasePresenter;
import com.fpp.status.model.RequestListener;
import com.fpp.status.model.impl.LoginModel;
import com.fpp.status.model.interf.ILoginModel;

import java.util.List;



public class NewMvpPresenter extends BasePresenter<NewMvpView> {

    private ILoginModel requestBiz;
    private Handler mHandler;

    public NewMvpPresenter() {
        requestBiz = new LoginModel();
        mHandler = new Handler(Looper.getMainLooper());
    }

    public void onResume(){
//        requestBiz.login(new RequestListener<String>() {
//            @Override
//            public void success(String data) {
//                mHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        mView.hideLoading();
//                    }
//                });
//            }
//
//            @Override
//            public void failure(Exception e) {
//                mView.showMessage("请求失败");
//            }
//        });
    }

}
