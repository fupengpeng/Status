package com.fpp.status.presenter.impl;

import com.fpp.status.model.RequestListener;
import com.fpp.status.model.impl.LoginModel;
import com.fpp.status.model.interf.ILoginModel;
import com.fpp.status.presenter.interf.ILoginPresenter;
import com.fpp.status.presenter.usercenter.ILoginView;

/**
 * Description:
 * Author: fpp
 * Date: 2018/5/15  15:25
 */

public class LoginPresenter implements ILoginPresenter {
    private ILoginModel loginModel;
    private ILoginView loginView;
    public LoginPresenter (ILoginView loginView){
        this.loginView = loginView;
        this.loginModel = new LoginModel();
    }


    @Override
    public void login(String name, String password) {
        loginModel.login(name, password, new RequestListener<String>() {
            @Override
            public void success(String data) {
                loginView.loginSuccess(data);
            }

            @Override
            public void failure(Exception e) {
                loginView.failure(e);
            }
        });
    }


    public void onResume(){

    }
}
