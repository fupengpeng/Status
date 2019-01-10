package com.fpp.status.presenter.usercenter;

/**
 * @description  登陆界面
 * @author  fupengpeng
 * @date  2018/2/26 0026 16:49
 */

public interface ILoginView {


    /**
     * 当登录成功
     */
    void loginSuccess(String data);
    /**
     * 请求失败
     */
    void failure(Exception e);

}
