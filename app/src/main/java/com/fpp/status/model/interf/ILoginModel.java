package com.fpp.status.model.interf;

import com.fpp.status.model.RequestListener;

/**
 * Description:
 * Author: fpp
 * Date: 2018/5/15  15:24
 */

public interface ILoginModel {

    void login(String name , String password , RequestListener<String> requestListener);
}
